package com.lwb.cargovoice.module.api;

import com.cargo.basecommon.base.BaseResponse;
import com.cargo.basecommon.bean.EnquiryAddGoalRequest;
import com.lwb.cargovoice.module.mvp.entity.request.AIRequest;
import com.lwb.cargovoice.module.mvp.entity.request.OneTouchRequest;
import com.lwb.cargovoice.module.mvp.entity.response.AIResponse;
import com.lwb.cargovoice.module.mvp.entity.response.AddHarvestInfoResponse;
import com.lwb.cargovoice.module.mvp.entity.response.BillListResponse;
import com.lwb.cargovoice.module.mvp.entity.response.BookingSpaceDetailsResponse;
import com.lwb.cargovoice.module.mvp.entity.response.BookingSpaceListResponse;
import com.lwb.cargovoice.module.mvp.entity.response.EnquiryDetailsResponse;
import com.lwb.cargovoice.module.mvp.entity.response.EnquiryListResponse;
import com.lwb.cargovoice.module.mvp.entity.response.FreightDetailsResponse;
import com.lwb.cargovoice.module.mvp.entity.response.FreightListResponse;
import com.lwb.cargovoice.module.mvp.entity.response.OneTouchResponse;
import com.lwb.cargovoice.module.mvp.entity.response.SelectRegionBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CargoVoiceApi {
    String api = "api-icargo";

    //询价 三件套 列表 、添加 、 详情  S-004
    @GET(api + "/inquiry/list/summary")
    Observable<BaseResponse<List<EnquiryListResponse>>> enquiryList(@Query("pageNumber") int pageNumber, @Query("pageSize") int pageSize);

    // S-005
    @GET(api + "/inquiry/detail/{id}")
    Observable<BaseResponse<EnquiryDetailsResponse>> enquiryDetails(@Path("id") String id);

    // S-006
    @POST(api + "/inquiry/create")
    Observable<BaseResponse<String>> enquiryCreate(@Body EnquiryAddGoalRequest request);

    //订舱 三件套 列表 、添加 、详情  S-007
    @GET(api + "/ibooking/list/summary")
    Observable<BaseResponse<List<BookingSpaceListResponse>>> bookingSpaceList(@Query("pageNumber") int pageNumber, @Query("pageSize") int pageSize);

    // S-008
    @GET(api + "/ibooking/detail/{id}")
    Observable<BaseResponse<BookingSpaceDetailsResponse>> bookingSpaceDetails(@Path("id") String id);

    // S-009
    @POST(api + "/ibooking/create")
    Observable<BaseResponse<String>> bookingSpaceCreate(@Body EnquiryAddGoalRequest request);

    //货运 列表  S-010
    @GET(api + "/cw1-shipment/list/summary")
    Observable<BaseResponse<List<FreightListResponse>>> freightList(@Query("pageNumber") int pageNumber, @Query("pageSize") int pageSize);

    //货运 列表 S-010 进口
    @GET(api + "/cw1-shipment/list/import-summary")
    Observable<BaseResponse<List<FreightListResponse>>> freightImportList(@Query("pageNumber") int pageNumber, @Query("pageSize") int pageSize);

    //货运 列表 S-010 出口
    @GET(api + "/cw1-shipment/list/export-summary")
    Observable<BaseResponse<List<FreightListResponse>>> freightExportList(@Query("pageNumber") int pageNumber, @Query("pageSize") int pageSize);

    // S-011
    @GET(api + "/cw1-shipment/detail/{id}")
    Observable<BaseResponse<FreightDetailsResponse>> freightDetails(@Path("id") String id);

    //组织机构 发货人的
    @GET(api + "/organization/address/list/my")
    Observable<BaseResponse<List<AddHarvestInfoResponse>>> organization();

    //组织机构 收货人的
    @GET(api + "/organization/address/list/delivery")
    Observable<BaseResponse<List<AddHarvestInfoResponse>>> organizationConsignee();

    //s-012 账单列表
    @GET(api + "/cw1-transaction/list/summary")
    Observable<BaseResponse<List<BillListResponse>>> billList(@Query("pageNumber") int pageNumber, @Query("pageSize") int pageSize);

    //s-012 账单列表 已结
    @GET(api + "/cw1-transaction/list/closed-summary")
    Observable<BaseResponse<List<BillListResponse>>> billClosedList(@Query("pageNumber") int pageNumber, @Query("pageSize") int pageSize);

    //s-012 账单列表 未结
    @GET(api + "/cw1-transaction/list/open-summary")
    Observable<BaseResponse<List<BillListResponse>>> billOpenList(@Query("pageNumber") int pageNumber, @Query("pageSize") int pageSize);

    //s-003 一键订舱 按钮
    @POST(api + "/ibooking/one-touch")
    Observable<OneTouchResponse> oneTouch(@Body OneTouchRequest request);

    // S-013 智能语音
    @POST("/eno-ai/webhooks/rest/webhook")
    Observable<List<AIResponse>> aiHook(@Body AIRequest request);


    @GET(api + "/location/country/list")
    Observable<BaseResponse<List<SelectRegionBean>>> locationCountry();

    @GET(api + "/location/province/list")
    Observable<BaseResponse<List<SelectRegionBean>>> locationProvince(@Query("countryCode") String countryCode);

    @GET(api + "/location/city/list")
    Observable<BaseResponse<List<SelectRegionBean>>> locationCity(@Query("countryCode") String countryCode, @Query("provinceCode") String provinceCode);
}
