package com.lwb.cargovoice.module.mvp.entity.response;

import java.util.List;

public class OneTouchResponse {

    /**
     * app_id : string
     * data : {}
     * data_list : [{}]
     * err_code : 0
     * err_msg : string
     */

    private String app_id;
    private DataBean data;
    private int err_code;
    private String err_msg;
    private List<DataListBean> data_list;

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getErr_code() {
        return err_code;
    }

    public void setErr_code(int err_code) {
        this.err_code = err_code;
    }

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }

    public List<DataListBean> getData_list() {
        return data_list;
    }

    public void setData_list(List<DataListBean> data_list) {
        this.data_list = data_list;
    }

    public static class DataBean {
    }

    public static class DataListBean {
    }
}
