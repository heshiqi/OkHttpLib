package com.hsq.dome.request;

import android.util.Log;

import com.android.hsq.netlib.request.GetRequest;

import org.json.JSONException;

import java.util.List;
import java.util.Map;

/**
 * 获取房间信息
 * Created by zhf on 2016/10/14.
 */
public class GetLiveRoomServan extends GetRequest<LiveRoomModel> {

    private String roomId;

    public GetLiveRoomServan(String url,String roomId) {
        super(url);
        this.roomId=roomId;
        /*
        params.add(new BasicNameValuePair("uc_ticket", UserHelper.isLogin()? UserHelper.getUser().getUserToken():""));
        params.add(new BasicNameValuePair("autohomeua", AppConfig.AUTOHOMEUA));
        params.add(new BasicNameValuePair("imei", DeviceHelper.getIMEI()));
        params.add(new BasicNameValuePair("netmode", NetUtil.getNetworkType(PluginContext.getInstance().getContext())));
        params.add(new BasicNameValuePair("_appid", AppConfig.APP_ID));
        java.lang.String stamp = TimeStampHelper.getTimeStamp();
        params.add(new BasicNameValuePair("_timestamp", stamp));
        java.lang.String _sign = SignHelper.getInterfaceSign(params);
        params.add(new BasicNameValuePair("_sign", _sign));
         */
        addParams("roomid","2051");
        addParams("uc_ticket","65347ad749784c9db9800902151ced9e00def95f");
        addParams("autohomeua","Android\\t4.4.4\\tautohome\\t7.3.0\\tAndroid");
        addParams("imei","864895028442604");
        addParams("netmode","NETWORK_TYPE_WIFI");
        addParams("_appid","live.app.android");
        addParams("_sign","672067E21448272E8D40F1187003881A");

    }




    @Override
    public LiveRoomModel parseResult(Map<String, List<String>> headers, String resultJson, int statusCode) throws JSONException {
        Log.d("hh",resultJson.toString());
        return null;
    }

    @Override
    public Object getTag() {
        return TAG;
    }

    @Override
    protected void setFlags() {
        flags=FLAG_ALLOW_GZIP;

    }

//    public GetLiveRoomServan(String roomId) {
//        this.roomId = roomId;
//    }
//
//    public void getRoomInfo(ResponseListener<NetModel<LiveRoomModel>> responseListener) {
//
//        List<NameValuePair> params = new LinkedList<NameValuePair>();
//
//        params.add(new BasicNameValuePair("roomid", roomId ));
//
//        addCommonParams(params);
//
//        URLFormatter formatter = new URLFormatter(URLFormatter.EParamType.URL_PARAM, URLFormatter.EParamInfo.NULL, params, UrlConstant.GET_LIVE_ROOM_URL);
//        getData(formatter.getFormatUrl(), responseListener);
//
//    }
//
//    @Override
//    public NetModel<LiveRoomModel> parseData(String jsonTxt) throws JSONException {
//
////        JSONObject root = new JSONObject(jsonTxt);
////        String token = root.getString("token");
//
//        //LiveBaseModel baseModel = parseJsonS(jsonTxt);
//
//        NetModel<LiveRoomModel> netModel = GsonUtil.parseJson(jsonTxt,
//                new TypeToken<NetModel<LiveRoomModel>>() {}.getType());
//
//        return netModel;
//    }
//
//    @Override
//    public int getMethod() {
//        return Method.GET;
//    }
//
//    public String getCachekey() {
//        return "GetLiveRoomServan";
//    }
}
