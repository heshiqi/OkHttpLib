package com.android.hsq.netlib.callback;

import okhttp3.Call;

/**
 * Created by hsq on 2016/6/28.
 */
public interface RequestListener<T> {

    public void onSuccess(T data,Object tag);

    public void onError(Call call, Exception e,Object tag);

    public void inProgress(float progress, long total,Object tag);


    public static RequestListener DEFAULT_REQUEST_LISTENER = new RequestListener<Object>()
    {


        @Override
        public void onSuccess(Object data,Object tag) {

        }

        @Override
        public void onError(Call call, Exception e,Object tag) {

        }

        @Override
        public void inProgress(float progress, long total,Object tag) {

        }
    };

}
