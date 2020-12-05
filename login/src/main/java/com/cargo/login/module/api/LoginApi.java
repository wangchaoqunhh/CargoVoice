package com.cargo.login.module.api;

import com.cargo.basecommon.base.BaseResponse;
import com.cargo.login.module.mvp.entity.request.GetTelCodeRequest;
import com.cargo.login.module.mvp.entity.request.LoginRequest;
import com.cargo.login.module.mvp.entity.request.HolderBean;
import com.cargo.login.module.mvp.entity.response.CarOrderResponse;
import com.cargo.login.module.mvp.entity.response.LoginResponse;
import com.cargo.login.module.mvp.entity.response.PersonalInfoResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LoginApi {


    /**
     * 获取短信验证码
     * @param mobile 手机号
     * @return
     */
    @GET("api-user/sms/verify-code/login/{mobile}")
    Observable<BaseResponse> getVerifyCode(@Path("mobile") String mobile);

    /**
     * 获取临时Token
     * @return
     */
    @POST("api-uaa/oauth/mobile/preauth")
    Observable<BaseResponse<LoginResponse>> getPreToken(@Query("mobile")String mobile,@Query("verifyCode") String  verifyCode);

    /**
     * 获取货代列表
     * @param mobile 手机号
     * @return
     */
    @GET("api-user/users/holders/{mobile}")
    Observable<BaseResponse<List<HolderBean>>> getHolderList(@Path("mobile") String mobile);

    /**
     * 获取临时Token
     * @param mobile 手机号
     * @return
     */
    @POST("api-uaa/oauth/mobile/postauth")
    Observable<BaseResponse<LoginResponse>> getOfficialToken(@Query("mobile")String mobile);




















    /**
     * 忘记密码
     * @param request
     * @return
     */
    @PUT("api-user/register/users/reset")
    Observable<BaseResponse> resetPwd(@Body HolderBean request);

    /**
     * 校验短信验证码
     * @param request
     * @return
     */
    @PUT("api-user/sms/code/verify")
    Observable<BaseResponse> checkTelCode(@Body GetTelCodeRequest request);

    /**
     * 根据手机号注册
     * @param request
     * @return
     */
    @POST("api-user/register/users/mobile")
    Observable<BaseResponse> register(@Body HolderBean request);

    /**
     * 根据用户名密码/验证码登录
     * @param request
     * @return
     */
    @POST("api-uaa/oauth/mobile/token")
    Observable<BaseResponse<LoginResponse>> login(@Body LoginRequest request);

    /**
     * 设置新密码
     * @param request
     * @return
     */
    @POST("api-user/users/password")
    Observable<BaseResponse> setPassword(@Body HolderBean request);

    /**
     * 设置新密码
     * @return
     */
    @POST("api-uaa/oauth/mobile/token")
    Observable<BaseResponse<LoginResponse>> login(@Query("mobile")String username,@Query("password") String  password);

    /**
     * 查询车辆估价、订单列表
     */
    @GET("api-carscrap/car-basic-information/personal/vehicle/list/{owner}")
    Observable<BaseResponse<List<CarOrderResponse>>> getOrderList(@Path("owner") String owner);


    /**
     * 查询B端用户类型
     */
    @GET("api-carscrap/personalinfo/{userId}")
    Observable<BaseResponse<PersonalInfoResponse>> getLoginType(@Path("userId") String userId);

}
