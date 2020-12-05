package com.lwb.cargovoice.module.mvp.entity.request;

public class OneTouchRequest {

    //询价后一键订舱请求：JSON格式形如：{“id”：“xxxxx”}，说明：id为询价报文返回的数据ID
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
