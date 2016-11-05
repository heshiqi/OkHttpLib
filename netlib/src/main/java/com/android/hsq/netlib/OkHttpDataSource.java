package com.android.hsq.netlib;

import com.android.hsq.netlib.callback.RequestListener;
import com.android.hsq.netlib.request.DataRequest;
import com.android.hsq.netlib.request.WrapRequestBody;
import com.android.hsq.netlib.upstream.HttpDataSource;
import com.android.hsq.netlib.util.Assertions;
import com.android.hsq.netlib.util.Exceptions;
import com.android.hsq.netlib.util.Exceptions.HttpDataSourceException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpDataSource implements HttpDataSource {


    private final OkHttpClient okHttpClient;
    private final String userAgent;
    private final RequestListener listener;
    private final CacheControl cacheControl;

    private DataRequest dataSpec;
    private Response response;
    private boolean opened;


    public OkHttpDataSource(OkHttpClient client, String userAgent) {
        this(client, userAgent, null);
    }


    public OkHttpDataSource(OkHttpClient client, String userAgent, RequestListener listener) {
        this(client, userAgent, listener, null);
    }


    public OkHttpDataSource(OkHttpClient client, String userAgent, RequestListener listener,
                            CacheControl cacheControl) {
        this.okHttpClient = Assertions.checkNotNull(client);
        this.userAgent = Assertions.checkNotEmpty(userAgent);
        this.listener = listener;
        this.cacheControl = cacheControl;
    }

    @Override
    public Map<String, List<String>> getResponseHeaders() {
        return response == null ? null : response.headers().toMultimap();
    }

    @Override
    public long syncOpen(DataRequest dataSpec) throws HttpDataSourceException {
        this.dataSpec = dataSpec;
        Request request = makeRequest(dataSpec);
        Call call = null;
        try {
            call = okHttpClient.newCall(request);
            response = call.execute();
        } catch (IOException e) {
            throw new HttpDataSourceException("Unable to connect to " + dataSpec.getUrl(), e,
                    dataSpec, HttpDataSourceException.TYPE_OPEN);
        }

        int responseCode = response.code();

        // Check for a valid response code.
        if (!response.isSuccessful()) {
            Map<String, List<String>> headers = request.headers().toMultimap();
            closeConnectionQuietly();
            throw new Exceptions.InvalidResponseCodeException(responseCode, headers, dataSpec);
        }

        // Determine the length of the data to be read, after skipping.
        long contentLength = response.body().contentLength();

        opened = true;
        if (listener != null) {
            try {
                listener.onSuccess(dataSpec.parseResult(getResponseHeaders(), response.body().string(), responseCode), dataSpec.getTag());
            } catch (Exception e) {
                closeConnectionQuietly();
                throw new HttpDataSourceException("Unable to read to " + dataSpec.getUrl(), e,
                        dataSpec, HttpDataSourceException.TYPE_READ);
            }
        }

        return contentLength;
    }

    @Override
    public void open(final DataRequest dataSpec) throws HttpDataSourceException {
        openCall(dataSpec);
    }

    @Override
    public Call openCall(final DataRequest dataSpec) throws HttpDataSourceException {
        this.dataSpec = dataSpec;
        Request request = makeRequest(dataSpec);
        Call c=okHttpClient.newCall(request);
        c.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (listener != null) {
                    listener.onError( e, dataSpec.getTag());
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (call.isCanceled()) {
                        sendFailResultCallback(new HttpDataSourceException("Canceled!", dataSpec, HttpDataSourceException.TYPE_CANCELED),dataSpec.getTag());
                        return;
                    }

                    if (!response.isSuccessful()) {
                        sendFailResultCallback(new HttpDataSourceException("request failed , reponse's code is : " + response.code(), dataSpec, HttpDataSourceException.TYPE_OPEN),dataSpec.getTag());
                        return;
                    }

                    if (listener != null) {
                        listener.onSuccess(dataSpec.parseResult(getResponseHeaders(), response.body().string(), response.code()), dataSpec.getTag());
                    }
                } catch (Exception e) {
                    sendFailResultCallback( e, dataSpec.getTag());
                } finally {
                    if (response.body() != null)
                        response.body().close();
                }
            }
        });
        return c;
    }

    public void sendFailResultCallback(final Exception e, Object tag) {
        if (listener == null) return;
        listener.onError( e, tag);
    }

    /**
     * 创建一个连接
     */
    private Request makeRequest(DataRequest dataSpec) {
        boolean allowGzip = (dataSpec.getFlags() & DataRequest.FLAG_ALLOW_GZIP) != 0;

        HttpUrl url = HttpUrl.parse(dataSpec.getUrl());
        Request.Builder builder = new Request.Builder().url(url);
        if (cacheControl != null) {
            builder.cacheControl(cacheControl);
        }
        appendHeaders(builder, dataSpec.getmHeaders());
        builder.addHeader("User-Agent", userAgent);
        if (!allowGzip) {
            builder.addHeader("Accept-Encoding", "identity");
        }
        RequestBody requestBody = dataSpec.buildRequestBody();
        if (requestBody != null) {
            builder.post(wrapRequestBody(requestBody));
        }
        return builder.build();
    }

    private RequestBody wrapRequestBody(RequestBody requestBody) {
        if (listener == null) return requestBody;
        WrapRequestBody countingRequestBody = new WrapRequestBody(requestBody, new WrapRequestBody.Listener() {
            @Override
            public void onRequestProgress(final long bytesWritten, final long contentLength) {

                listener.inProgress(bytesWritten * 1.0f / contentLength, contentLength,dataSpec.getTag());

            }
        });
        return countingRequestBody;
    }

    protected void appendHeaders(Request.Builder builder, Map<String, String> headers) {
        Headers.Builder headerBuilder = new Headers.Builder();
        if (headers == null || headers.isEmpty()) {
            return;
        }

        for (String key : headers.keySet()) {
            headerBuilder.add(key, headers.get(key));
        }
        builder.headers(headerBuilder.build());
    }

    public Response getResponse() {
        return response;
    }

    /**
     * Closes the current connection quietly, if there is one.
     */
    private void closeConnectionQuietly() {
        response.body().close();
        response = null;
    }

    @Override
    public void close() throws HttpDataSourceException {
        if (opened) {
            opened = false;
            closeConnectionQuietly();
        }
    }

}
