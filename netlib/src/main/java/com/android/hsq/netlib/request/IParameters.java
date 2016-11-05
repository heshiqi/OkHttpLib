package com.android.hsq.netlib.request;

import java.util.Map;

/**
 * Created by hsq on 2016/6/28.
 */
public interface IParameters {

    /**
     * 设置请求参数
     * @param params
     */
    void params(Map<String, String> params);

    /**
     *设置请求参数
     * @param key
     * @param val
     */
    void addParams(String key, String val);
}
