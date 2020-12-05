package com.cargo.login.module.mvp.entity.request;

public class GetTelCodeRequest {

    /**
     * codeType : string
     * telephone : 13352404200
     * verificationCode : string
     */

    private String codeType;
    private String telephone;
    private String verificationCode;

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
