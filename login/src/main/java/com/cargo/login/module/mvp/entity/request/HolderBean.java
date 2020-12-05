package com.cargo.login.module.mvp.entity.request;

import android.os.Parcel;
import android.os.Parcelable;

public class HolderBean implements Parcelable {

    /**
     * checkPassword : string
     * id : 0
     * mobile : string
     * newPassword : string
     * oldPassword : string
     * openId : string
     * type : string
     * verificationCode : string
     */

    private String holderName;
    private String clientId;
    private String clientSecret;

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.holderName);
        dest.writeString(this.clientId);
        dest.writeString(this.clientSecret);
    }

    public HolderBean() {
    }

    protected HolderBean(Parcel in) {
        this.holderName = in.readString();
        this.clientId = in.readString();
        this.clientSecret = in.readString();
    }

    public static final Parcelable.Creator<HolderBean> CREATOR = new Parcelable.Creator<HolderBean>() {
        @Override
        public HolderBean createFromParcel(Parcel source) {
            return new HolderBean(source);
        }

        @Override
        public HolderBean[] newArray(int size) {
            return new HolderBean[size];
        }
    };
}
