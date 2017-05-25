package com.xp.mymvc.Tool.Net;

import com.xp.mymvc.MyApplication;
import com.xp.mymvc.Tool.MyConstants;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by CheYR-T-XiangP on 2017/5/18.
 */

public class HttpManager {

    //定义基本地址
    public static final String HOST = MyConstants.SERVER_URL;
    //设置连接超时的值
    private static final int TIMEOUT = 15;
    //声明HttpService对象
    protected HttpService httpService;
    //声明HttpManager对象
    private volatile static HttpManager httpManager;

    protected HttpManager() {
        //新建一个文件用来缓存网络请求
        File cacheDirectory = new File(MyApplication.getAppContext()
                .getCacheDir(), "HttpCache");
        //实例化一个OkHttpClient.Builder
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //设置连接超时
        builder.connectTimeout(TIMEOUT, TimeUnit.SECONDS);
        //设置从主机读信息超时
        builder.readTimeout(TIMEOUT, TimeUnit.SECONDS);
        //设置写信息超时
        builder.writeTimeout(TIMEOUT, TimeUnit.SECONDS);
        //设置缓存文件
        builder.cache(new Cache(cacheDirectory, 10 * 1024 * 1024));
        //设置okhttp拦截器，这样做的好处是可以为你的每一个
        //retrofit2的网络请求都增加相同的head头信息，而不用每一个请求都写头信息
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json; charset=UTF-8")
                        .addHeader("Connection", "keep-alive")
                        .addHeader("Accept", "*/*")
                        .header("Cache-Control", String.format("public, max-age=%d", 60))
                        .removeHeader("Pragma")
                        .build();
                return chain.proceed(request);
            }
        });

        Retrofit.Builder rBuilder = new Retrofit.Builder()
                .client(builder.build())
                //如果网络访问返回的是json字符串，使用gson转换器
                .addConverterFactory(DecodeConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
                //此处顺序不能和上面对调，否则不能同时兼容普通字符串和Json格式字符串
                //为了支持rxjava,需要添加下面这个把 Retrofit 转成RxJava可用的适配类
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(getHost());
        httpService = rBuilder.build().create(HttpService.class);
    }

    /**
     * 如果有不同的请求HOST可继承此类并Override
     *
     * @return
     */
    protected String getHost() {
        return HOST;
    }

    public HttpService getHttpService() {
        return httpService;
    }

    //使用单例模式
    public static HttpManager getInstance() {
        if (httpManager == null) {
            synchronized (HttpManager.class) {
                if (httpManager == null) {
                    httpManager = new HttpManager();
                }
            }
        }
        return httpManager;
    }

    /**
     * 处理http请求——常规
     *
     * @param pCall
     * @param pCallback
     */
    public void doHttpRequest(Call pCall, Callback pCallback) {
        Call call = pCall;
        call.enqueue(pCallback);
    }

    /**
     * 处理http请求——RX
     *
     * @param pObservable
     * @param pSubscriber
     */
    public void doHttpRequest(Observable pObservable, Subscriber pSubscriber) {
        Observable observable = pObservable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(pSubscriber);
    }

    /**
     * json方式传参
     * 将json格式的字符串转换成RequestBody
     *
     * @param pBody
     * @return
     */
    public RequestBody getPostBody(String pBody) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), pBody);
        return body;
    }

}
