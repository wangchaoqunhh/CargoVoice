package com.cargo.basecommon.bean;

import java.io.Serializable;

public class GsonBean implements Serializable {

    /**
     * categoryCode : ROA;RAI;COU;FSA;SEA
     * categoryDescription :
     * code : LCL
     * nameEn : Less Container Load
     * nameCn : 散货
     * isMostUsed : true
     */

    private String categoryCode;
    private String categoryName;
    private String categoryDescription;
    private String code;
    private String nameEn;
    private String nameCn;
    private boolean isMostUsed;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isMostUsed() {
        return isMostUsed;
    }

    public void setMostUsed(boolean mostUsed) {
        isMostUsed = mostUsed;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

}
