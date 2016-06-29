package com.android.hsq.netlib;

import com.android.hsq.netlib.callback.RequestListener;
import com.android.hsq.netlib.request.DataRequest;
import com.android.hsq.netlib.util.Exceptions;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by hsq on 2016/6/24.
 *
 * 网络请求管理类
 */
public class HttpClientManage {

    public static final String USER_AGENT="android me";
    public static final int CONNECT_TIME_OUT=10;
    public static final int READ_TIME_OUT=20;


    private static HttpClientManage mHttpClientManage;
    private OkHttpClient mOkHttpClient = new OkHttpClient();
    ;


    public static HttpClientManage getInstance() {
        final HttpClientManage httpClientManage = mHttpClientManage;
        if (httpClientManage == null) {
            synchronized (HttpClientManage.class) {
                if (httpClientManage == null)
                    mHttpClientManage = new HttpClientManage();
            }
        }
        return mHttpClientManage;
    }

    private HttpClientManage() {
        mOkHttpClient = new OkHttpClient.Builder().connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS).readTimeout(READ_TIME_OUT, TimeUnit.SECONDS).build();
    }

    /**
     * 同步执行网路请求操作
     * @param dataRequest
     * @param listener
     * @throws com.android.hsq.netlib.util.Exceptions.HttpDataSourceException
     */
    public void SyncExecuteRequest(DataRequest dataRequest, RequestListener listener) throws Exceptions.HttpDataSourceException {
        OkHttpDataSource dataSource = new OkHttpDataSource(mOkHttpClient,USER_AGENT, listener);
        dataSource.syncOpen(dataRequest);
        dataSource.close();
    }

    /**
     * 异步执行网络请求操作
     * @param dataRequest
     * @param listener
     * @throws com.android.hsq.netlib.util.Exceptions.HttpDataSourceException
     */
    public void executeRequest(DataRequest dataRequest, RequestListener listener) throws Exceptions.HttpDataSourceException {
        OkHttpDataSource dataSource = new OkHttpDataSource(mOkHttpClient, USER_AGENT, listener);
        dataSource.open(dataRequest);
    }
}
