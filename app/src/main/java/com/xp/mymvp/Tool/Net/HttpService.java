package com.xp.mymvp.Tool.Net;

import com.xp.mymvp.Bean.ActivityListBean;
import com.xp.mymvp.Tool.MyConstants;

import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 *
 * Created by CheYR-T-XiangP on 2017/5/18.
 */

public interface HttpService {
    //上传图片和描述
    @FormUrlEncoded
    @POST("上传的地址")
    Observable<String> uploadUserFile(@Part("fileName") RequestBody description, @Part("file\"; filename=\"image.png\"")RequestBody img);

    @FormUrlEncoded
    @POST(MyConstants.ACTIVITY_LIST)
    Observable<ActivityListBean> getList(@Field("argEncPara") String argEncPara);


}
