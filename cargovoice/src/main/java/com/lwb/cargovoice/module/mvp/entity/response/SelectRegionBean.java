package com.lwb.cargovoice.module.mvp.entity.response;


import java.io.Serializable;

public class SelectRegionBean implements Serializable {

    /**
     * code : string
     * desc : string
     * name : string
     */

    private String code;
    private String name;
    private String desc;
    private String letters;

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

