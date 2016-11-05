package com.hsq.dome;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.android.dome.R;
import com.android.hsq.netlib.HttpClientManage;
import com.android.hsq.netlib.callback.RequestListener;
import com.android.hsq.netlib.util.Exceptions;
import com.hsq.dome.request.GetLiveRoomServan;
import com.hsq.dome.request.LiveRoomModel;

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
                    HttpClientManage.getInstance().executeRequest(new GetLiveRoomServan("http://clientlive.api.autohome.com.cn/api/live/room", "2051").build(), new RequestListener<LiveRoomModel>() {
                        @Override
                        public void onSuccess(LiveRoomModel data, Object tag) {

                        }

                        @Override
                        public void onError(Exception e, Object tag) {

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
