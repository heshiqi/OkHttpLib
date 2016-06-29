package com.hsq.dome;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.dome.R;
import com.android.hsq.netlib.HttpClientManage;
import com.android.hsq.netlib.callback.RequestListener;
import com.android.hsq.netlib.util.Exceptions;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity{

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button= (Button) findViewById(R.id.select_photo);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    HttpClientManage.getInstance().executeRequest(new TestDataRequest("http://www.baidu.com"), new RequestListener<TestEntity>() {
                        @Override
                        public void onSuccess(TestEntity data, Object tag) {
                            Log.d("hh","data="+data.data);
                            Log.d("hh","code="+data.code);
                        }

                        @Override
                        public void onError(Call call, Exception e, Object tag) {

                        }

                        @Override
                        public void inProgress(float progress, long total, Object tag) {

                        }
                    });
                } catch (Exceptions.HttpDataSourceException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
