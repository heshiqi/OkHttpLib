package com.hsq.dome.request;

import java.io.Serializable;


/**
 * 房间信息
 * Created by zhf on 2016/10/13.
 */

public class LiveRoomModel implements Serializable {

    public static final int
            LIVE_STATE_NOT_START = 0,//预告
            LIVE_STATE_LIVING = 1,//直播中
            LIVE_STATE_REPLAY = 2,//重播
            LIVE_STATE_PAUSE = 3;// 暂停

    public static final int DEFAULT_INTERVAL_TIME = 10;//秒

    /**** 服务器变量区 start ****/

    private int id; //房间号

    private String title; //房间名称

    private String cover; //封面图

    private int publish_status;// 直播状态 0 预告 1 直播中 2 重播 3 暂停

    private int anchor_id;// 主播ID

    private String anchor_name;//主播名字

    private int uid;// 当前用户ID

    private int sex;//主播性别

    private int room_status;//直播聊天室状态 0关闭 1开启

    private int recommended;// 0 不推荐 1推荐至app

    private String avator;// 主播头像

    private String start_time;//开始时间

    private String real_stime;// 实际开始时间

    private String end_time;// 结束时间

    private String description;//直播简述

    private int show_type;// 展示类型 1左上 2居中 3右上

    private String hdl_url;// 直播地址flv格式

    private String hls_url;// 直播地址m3u8格式

    private String replay_hls_url; // 重播地址m3u8格式

    private String replay_hdl_url; //重播地址flv格式

    private int view_count;//  观看人数

    private int applause_count;//鼓掌次数

    private RongYunTokenModel rong_cloud;//融云

    private int forbid_speaking; //1：被禁言， 0：未被禁言

    private int interval_time; //轮询时间（单位：秒）
    private int is_admin;//是否为管理员  1 是管理员 0 不是

    /**** 服务器变量区 end ****/


    /**** 自定义变量区 start ****/


    /**** 自定义变量区 end ****/

    public int getRoom_status() {
        return room_status;
    }

    public void setRoom_status(int room_status) {
        this.room_status = room_status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getPublish_status() {
        return publish_status;
    }

    public void setPublish_status(int publish_status) {
        this.publish_status = publish_status;
    }

    public int getAnchor_id() {
        return anchor_id;
    }

    public void setAnchor_id(int anchor_id) {
        this.anchor_id = anchor_id;
    }

    public String getAnchor_name() {
        return anchor_name;
    }

    public void setAnchor_name(String anchor_name) {
        this.anchor_name = anchor_name;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getRecommended() {
        return recommended;
    }

    public void setRecommended(int recommended) {
        this.recommended = recommended;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public String getStart_time() {
        return start_time;
    }


    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getReal_stime() {
        return real_stime;
    }

    public void setReal_stime(String real_stime) {
        this.real_stime = real_stime;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getShow_type() {
        return show_type;
    }

    public void setShow_type(int show_type) {
        this.show_type = show_type;
    }


    public String getLiveUrl(){
        return hdl_url;
    }
    public void setHdl_url(String hdl_url) {
        this.hdl_url = hdl_url;
    }


    public void setHls_url(String hls_url) {
        this.hls_url = hls_url;
    }

    public String getReplay_hls_url() {
        return replay_hls_url;
    }

    public void setReplay_hls_url(String replay_hls_url) {
        this.replay_hls_url = replay_hls_url;
    }

    public String getReplay_hdl_url() {
        return replay_hdl_url;
    }

    public void setReplay_hdl_url(String replay_hdl_url) {
        this.replay_hdl_url = replay_hdl_url;
    }

    public int getView_count() {
        return view_count;
    }

    public String getViewCountStr() {
        String str = "";
        if (view_count == 0) {
            //
        } else if (view_count > 9999) {
            java.text.DecimalFormat df = new java.text.DecimalFormat("#.#");
            str = df.format(view_count / 10000f) + "万位观众";
        } else {
            str = view_count + "位观众";
        }
        return str;
    }
    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public int getApplause_count() {
        return applause_count;
    }

    public void setApplause_count(int applause_count) {
        this.applause_count = applause_count;
    }

    public void setRong_cloud(RongYunTokenModel rong_cloud) {
        this.rong_cloud = rong_cloud;
    }

    public RongYunTokenModel getRong_cloud() {
        return rong_cloud;
    }

    public void setForbid_speaking(int forbid_speaking) {
        this.forbid_speaking = forbid_speaking;
    }

    public int getForbid_speaking() {
        return forbid_speaking;
    }

    public void setInterval_time(int interval_time) {
        this.interval_time = interval_time;
    }

    public int getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(int is_admin) {
        this.is_admin = is_admin;
    }

    public int getInterval_time() {
        if (interval_time <= 0){
            return DEFAULT_INTERVAL_TIME;
        }
        return interval_time;
    }
}
