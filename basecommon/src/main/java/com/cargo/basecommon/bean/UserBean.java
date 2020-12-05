package com.cargo.basecommon.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class UserBean {


    /**
     * id : 1
     * userId : 1
     * userName : testAA
     * userType : APP
     * fullName : zhangsan
     * certType : false
     * certNumber : 1023424
     * realName : true
     * telephone : 131343214
     * email : null
     * headPictureUrl : null
     * isReceive : false
     * addressList : [{"id":1,"createTime":"2019-11-23 14:40:11","updateTime":"2019-11-23 14:40:11","reserved1":null,"reserved2":null,"district":null,"province":null,"city":"北京","county":null,"detailedAddress":"名爵CSA6463NDAR多用途乘用车","userName":"test","telephone":"9876544","cityId":null,"isDeleted":false,"personalInfoId":1}]
     * bankList : [{"id":2,"createTime":null,"updateTime":"2019-11-27 10:40:31","reserved1":null,"reserved2":null,"accountNumber":"123","accountType":1,"bankName":"建设银行","bankNo":"6227003028940029516","accountAmount":7901,"availableAmount":219422.22,"freezingAmount":55557555,"isDeleted":false,"personalInfoId":1,"isDefault":true,"personName":null}]
     * wechat : false
     */

    private String id;
    private String userId;
    private String userName;
    private String userType;
    private String fullName;
    private boolean certType;
    private String certNumber;
    private boolean realName;
    private String telephone;
    private String email;
    private String headPictureUrl;
    private boolean isReceive;
    private String nickname;
    private boolean wechat;
    private List<AddressListBean> addressList;
    private List<BankListBean> bankList;

    public boolean isReceive() {
        return isReceive;
    }

    public void setReceive(boolean receive) {
        isReceive = receive;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isCertType() {
        return certType;
    }

    public void setCertType(boolean certType) {
        this.certType = certType;
    }

    public String getCertNumber() {
        return certNumber;
    }

    public void setCertNumber(String certNumber) {
        this.certNumber = certNumber;
    }

    public boolean isRealName() {
        return realName;
    }

    public void setRealName(boolean realName) {
        this.realName = realName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadPictureUrl() {
        return headPictureUrl;
    }

    public void setHeadPictureUrl(String headPictureUrl) {
        this.headPictureUrl = headPictureUrl;
    }

    public boolean isIsReceive() {
        return isReceive;
    }

    public void setIsReceive(boolean isReceive) {
        this.isReceive = isReceive;
    }

    public boolean isWechat() {
        return wechat;
    }

    public void setWechat(boolean wechat) {
        this.wechat = wechat;
    }

    public List<AddressListBean> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<AddressListBean> addressList) {
        this.addressList = addressList;
    }

    public List<BankListBean> getBankList() {
        return bankList;
    }

    public void setBankList(List<BankListBean> bankList) {
        this.bankList = bankList;
    }

    public static class AddressListBean implements Parcelable {
        /**
         * id : 1
         * createTime : 2019-11-23 14:40:11
         * updateTime : 2019-11-23 14:40:11
         * reserved1 : null
         * reserved2 : null
         * district : null
         * province : null
         * city : 北京
         * county : null
         * detailedAddress : 名爵CSA6463NDAR多用途乘用车
         * userName : test
         * telephone : 9876544
         * cityId : null
         * isDeleted : false
         * personalInfoId : 1
         */

        private String id;
        private String createTime;
        private String updateTime;
        private String reserved1;
        private String reserved2;
        private String district;
        private String province;
        private String city;
        private String county;
        private String detailedAddress;
        private String userName;
        private String telephone;
        private String cityId;
        private boolean isDeleted;
        private String personalInfoId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getReserved1() {
            return reserved1;
        }

        public void setReserved1(String reserved1) {
            this.reserved1 = reserved1;
        }

        public String getReserved2() {
            return reserved2;
        }

        public void setReserved2(String reserved2) {
            this.reserved2 = reserved2;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public String getDetailedAddress() {
            return detailedAddress;
        }

        public void setDetailedAddress(String detailedAddress) {
            this.detailedAddress = detailedAddress;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public boolean isDeleted() {
            return isDeleted;
        }

        public void setDeleted(boolean deleted) {
            isDeleted = deleted;
        }

        public String getPersonalInfoId() {
            return personalInfoId;
        }

        public void setPersonalInfoId(String personalInfoId) {
            this.personalInfoId = personalInfoId;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.createTime);
            dest.writeString(this.updateTime);
            dest.writeString(this.reserved1);
            dest.writeString(this.reserved2);
            dest.writeString(this.district);
            dest.writeString(this.province);
            dest.writeString(this.city);
            dest.writeString(this.county);
            dest.writeString(this.detailedAddress);
            dest.writeString(this.userName);
            dest.writeString(this.telephone);
            dest.writeString(this.cityId);
            dest.writeByte(this.isDeleted ? (byte) 1 : (byte) 0);
            dest.writeString(this.personalInfoId);
        }

        public AddressListBean() {
        }

        protected AddressListBean(Parcel in) {
            this.id = in.readString();
            this.createTime = in.readString();
            this.updateTime = in.readString();
            this.reserved1 = in.readString();
            this.reserved2 = in.readString();
            this.district = in.readString();
            this.province = in.readString();
            this.city = in.readString();
            this.county = in.readString();
            this.detailedAddress = in.readString();
            this.userName = in.readString();
            this.telephone = in.readString();
            this.cityId = in.readString();
            this.isDeleted = in.readByte() != 0;
            this.personalInfoId = in.readString();
        }

        public static final Creator<AddressListBean> CREATOR = new Creator<AddressListBean>() {
            @Override
            public AddressListBean createFromParcel(Parcel source) {
                return new AddressListBean(source);
            }

            @Override
            public AddressListBean[] newArray(int size) {
                return new AddressListBean[size];
            }
        };
    }

    public static class BankListBean implements MultiItemEntity, Parcelable {
        public static final int title = 1;
        public static final int normal = 2;
        /**
         * id : 2
         * createTime : null
         * updateTime : 2019-11-27 10:40:31
         * reserved1 : null
         * reserved2 : null
         * accountNumber : 123
         * accountType : 1
         * bankName : 建设银行
         * bankNo : 6227003028940029516
         * accountAmount : 7901
         * availableAmount : 219422.22
         * freezingAmount : 55557555
         * isDeleted : false
         * personalInfoId : 1
         * isDefault : true
         * personName : null
         */

        private String id;
        private String createTime;
        private String updateTime;
        private String reserved1;
        private String reserved2;
        private String accountNumber;
        private String accountType;
        private String bankName;
        private String bankNo;
        private String accountAmount;
        private double availableAmount;
        private String freezingAmount;
        private boolean isDeleted;
        private String personalInfoId;
        private boolean isDefault;
        private String personName;
        private int itemType;


        public static int getTitle() {
            return title;
        }

        public static int getNormal() {
            return normal;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getReserved1() {
            return reserved1;
        }

        public void setReserved1(String reserved1) {
            this.reserved1 = reserved1;
        }

        public String getReserved2() {
            return reserved2;
        }

        public void setReserved2(String reserved2) {
            this.reserved2 = reserved2;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public String getAccountType() {
            return accountType;
        }

        public void setAccountType(String accountType) {
            this.accountType = accountType;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getBankNo() {
            return bankNo;
        }

        public void setBankNo(String bankNo) {
            this.bankNo = bankNo;
        }

        public String getAccountAmount() {
            return accountAmount;
        }

        public void setAccountAmount(String accountAmount) {
            this.accountAmount = accountAmount;
        }

        public double getAvailableAmount() {
            return availableAmount;
        }

        public void setAvailableAmount(double availableAmount) {
            this.availableAmount = availableAmount;
        }

        public String getFreezingAmount() {
            return freezingAmount;
        }

        public void setFreezingAmount(String freezingAmount) {
            this.freezingAmount = freezingAmount;
        }

        public boolean isDeleted() {
            return isDeleted;
        }

        public void setDeleted(boolean deleted) {
            isDeleted = deleted;
        }

        public String getPersonalInfoId() {
            return personalInfoId;
        }

        public void setPersonalInfoId(String personalInfoId) {
            this.personalInfoId = personalInfoId;
        }

        public boolean isDefault() {
            return isDefault;
        }

        public void setDefault(boolean aDefault) {
            isDefault = aDefault;
        }

        public String getPersonName() {
            return personName;
        }

        public void setPersonName(String personName) {
            this.personName = personName;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }

        public static Creator<BankListBean> getCREATOR() {
            return CREATOR;
        }

        @Override
        public int getItemType() {
            return itemType;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.createTime);
            dest.writeString(this.updateTime);
            dest.writeString(this.reserved1);
            dest.writeString(this.reserved2);
            dest.writeString(this.accountNumber);
            dest.writeString(this.accountType);
            dest.writeString(this.bankName);
            dest.writeString(this.bankNo);
            dest.writeString(this.accountAmount);
            dest.writeDouble(this.availableAmount);
            dest.writeString(this.freezingAmount);
            dest.writeByte(this.isDeleted ? (byte) 1 : (byte) 0);
            dest.writeString(this.personalInfoId);
            dest.writeByte(this.isDefault ? (byte) 1 : (byte) 0);
            dest.writeString(this.personName);
            dest.writeInt(this.itemType);
        }

        public BankListBean() {
        }

        protected BankListBean(Parcel in) {
            this.id = in.readString();
            this.createTime = in.readString();
            this.updateTime = in.readString();
            this.reserved1 = in.readString();
            this.reserved2 = in.readString();
            this.accountNumber = in.readString();
            this.accountType = in.readString();
            this.bankName = in.readString();
            this.bankNo = in.readString();
            this.accountAmount = in.readString();
            this.availableAmount = in.readDouble();
            this.freezingAmount = in.readString();
            this.isDeleted = in.readByte() != 0;
            this.personalInfoId = in.readString();
            this.isDefault = in.readByte() != 0;
            this.personName = in.readString();
            this.itemType = in.readInt();
        }

        public static final Creator<BankListBean> CREATOR = new Creator<BankListBean>() {
            @Override
            public BankListBean createFromParcel(Parcel source) {
                return new BankListBean(source);
            }

            @Override
            public BankListBean[] newArray(int size) {
                return new BankListBean[size];
            }
        };
    }
}
