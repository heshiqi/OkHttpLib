package com.hsq.dome;

import com.android.hsq.netlib.request.GetRequest;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/29.
 */
public class TestDataRequest extends GetRequest<TestEntity>{

    public static final String TAG="TestDataRequest";
    public TestDataRequest(String url) {
        super(url);
    }

    @Override
    public TestEntity parseResult(Map<String, List<String>> headers, String resultJson, int statusCode) {
           TestEntity entity=new TestEntity();
        entity.data=resultJson;
        entity.code=statusCode;
        return entity;
    }

    @Override
    public Object getTag() {
        return TAG;
    }

    @Override
    protected void setFlags() {

    }
}
