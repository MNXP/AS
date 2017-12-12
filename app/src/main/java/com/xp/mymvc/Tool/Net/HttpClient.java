package com.xp.mymvc.Tool.Net;

import com.google.gson.Gson;
import com.xp.mymvc.Bean.ActivityListBean;
import com.xp.mymvc.Tool.Util.DES3Util;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by CheYR-T-XiangP on 2017/5/18.
 */

public class HttpClient {
    private static HttpClient httpClient;
    private static HashMap<String, String> map;
    private static Gson gson;

    private HttpClient() {
    }

    public static HttpClient getInstance() {
        if (httpClient == null) {
            synchronized (HttpClient.class) {
                httpClient = new HttpClient();
            }
        }
        return httpClient;
    }


    /**
     * @param subscriber
     */
    public void getList(int i, int pageSize, Subscriber<ActivityListBean> subscriber) {
        init();
        map.put("current", "" + i);
        map.put("pageSize", "" + pageSize);
        toSubscribe(HttpManager.getInstance().getHttpService().getList(toEncode(map)), subscriber);
    }





    private static void init() {
        if (map == null)
            map = new HashMap<>();
        else
            map.clear();
        if (gson == null)
            gson = new Gson();
    }


    private String toEncode(Map<String,String> map){
        Gson gson = new Gson();
        String json = gson.toJson(map);
        String encodeStr = "";
        try {
            encodeStr = DES3Util.encode(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    private <T> void toSubscribe(Observable<T> observable, Subscriber<T> subscriber) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
