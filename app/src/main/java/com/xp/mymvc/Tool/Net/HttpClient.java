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
    public static HttpClient httpClient;

    public HttpClient() {
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
        HashMap<String, String> map = new HashMap<>();
        map.put("current", "" + i);
        map.put("pageSize", "" + pageSize);
        String encodeStr = toEncode(map);
        map.clear();
        toSubscribe(HttpManager.getInstance().getHttpService().getList(encodeStr), subscriber);
//        toSubscribe(HttpManager.getInstance().getHttpService().getListMap(map), subscriber);
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
