package com.android.hsq.netlib.request;

import android.net.Uri;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.RequestBody;

/**
 * Created by hsq on 2016/6/28.
 */
public abstract class GetRequest<T> extends DataRequest<T> implements IParameters {

    public GetRequest(String url) {
        this(url, null, null);
    }


    public GetRequest(String url, Map<String, String> params) {
        this(url,params, null);
    }

    public GetRequest(String url, Map<String, String> params, Map<String, String> headers) {
        super(url,params, headers);
    }


    @Override
    public RequestBody buildRequestBody() {
        return null;
    }

    @Override
    public void params(Map<String, String> params) {
         mParams=params;
    }

    @Override
    public void addParams(String key, String val) {
        if (this.mParams == null)
        {
            mParams = new LinkedHashMap<>();
        }
        mParams.put(key, val);
    }

    protected String appendParams(String url, Map<String, String> params)
    {
        if (url == null || params == null || params.isEmpty())
        {
            return url;
        }
        Uri.Builder builder = Uri.parse(url).buildUpon();
        Set<String> keys = params.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext())
        {
            String key = iterator.next();
            builder.appendQueryParameter(key, params.get(key));
        }
        return builder.build().toString();
    }

    @Override
    public DataRequest build() {
        if (mParams != null)
        {
            mUrl = appendParams(mUrl, mParams);
        }

        return this;
    }
}
