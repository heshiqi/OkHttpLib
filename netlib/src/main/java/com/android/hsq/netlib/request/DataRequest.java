package com.android.hsq.netlib.request;

import org.json.JSONException;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * Created by hsq on 2016/6/28.
 */
public abstract class DataRequest<T> {

    protected final String TAG=getClass().getName();

    public static final int FLAG_ALLOW_GZIP = 1;

    protected String mUrl;
    protected Map<String, String> mParams;
    protected Map<String, String> mHeaders;

    /**
     * 标记是否支持压缩
     * {@link #FLAG_ALLOW_GZIP}
     */
    protected int flags;

    protected DataRequest(String url,Map<String, String> params, Map<String, String> headers)
    {
        this.mUrl = url;
        this.mParams = params;
        this.mHeaders = headers;
    }

    /**
     * 如果是post请求 返回数据实体类 get返回null
     * @return
     */
    public abstract RequestBody buildRequestBody();

    /**
     * 网络请求成功后 解析返回的数据
     * @param headers  响应头
     * @param resultJson  请求返回的数据
     * @param statusCode  响应码
     * @return
     */
    public abstract T parseResult(Map<String, List<String>> headers, String resultJson, int statusCode) throws JSONException;

    /**
     *  每个请求类在初始化后,最终要调用此方法进行构建
     * @return
     */
    public abstract DataRequest build();

    public abstract Object getTag();

    protected abstract void setFlags();

    public int getFlags(){
        return flags;
    }

    public String getUrl(){
        return mUrl;
    }

    public Map<String, String> getmHeaders() {
        return mHeaders;
    }


}
