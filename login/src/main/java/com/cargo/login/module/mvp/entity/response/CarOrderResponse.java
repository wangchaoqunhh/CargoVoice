package com.cargo.login.module.mvp.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

public class CarOrderResponse implements Parcelable {


    /**
     * vehicleName : 名爵CSA6463NDAR多用途乘用车
     * carStatus : C01
     * orderStatusDesc : 需要对应回显
     * fullName : 李文博
     * telephone : 123
     * bankName : 建设银行
     * OrderInfo : {"evaluatedPrice":1,"orderAmount":1,"orderId":11,"orderStatus":"T06","vehicleIdNumber":"","plateNumber":"12"}
     * evaluatedPrice : 1.0
     * firstLicenseTime : 2019-11-24
     * orderAreasName :
     * carStatusDesc : 需要对应回显
     * drivingMileage : 12
     * bankNo : string
     * id : 45
     * vehicleId : 20
     * orderAreasId : 1
     */

    private String vehicleName="";
    private String seriesName="";
    private String carStatus="";
    private String orderStatusDesc="";
    private String fullName="";
    private String telephone="";
    private String bankName="";
    private OrderInfoBean OrderInfo;
    private String evaluatedPrice="";
    private String firstLicenseTime="";
    private String orderAreasName="";
    private String carStatusDesc="";
    private String drivingMileage="";
    private String bankNo="";
    private String id="";
    private String vehicleId="";
    private String orderAreasId="";

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    public String getOrderStatusDesc() {
        return orderStatusDesc;
    }

    public void setOrderStatusDesc(String orderStatusDesc) {
        this.orderStatusDesc = orderStatusDesc;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public OrderInfoBean getOrderInfo() {
        return OrderInfo;
    }

    public void setOrderInfo(OrderInfoBean orderInfo) {
        OrderInfo = orderInfo;
    }

    public String getEvaluatedPrice() {
        return evaluatedPrice;
    }

    public void setEvaluatedPrice(String evaluatedPrice) {
        this.evaluatedPrice = evaluatedPrice;
    }

    public String getFirstLicenseTime() {
        return firstLicenseTime;
    }

    public void setFirstLicenseTime(String firstLicenseTime) {
        this.firstLicenseTime = firstLicenseTime;
    }

    public String getOrderAreasName() {
        return orderAreasName;
    }

    public void setOrderAreasName(String orderAreasName) {
        this.orderAreasName = orderAreasName;
    }

    public String getCarStatusDesc() {
        return carStatusDesc;
    }

    public void setCarStatusDesc(String carStatusDesc) {
        this.carStatusDesc = carStatusDesc;
    }

    public String getDrivingMileage() {
        return drivingMileage;
    }

    public void setDrivingMileage(String drivingMileage) {
        this.drivingMileage = drivingMileage;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getOrderAreasId() {
        return orderAreasId;
    }

    public void setOrderAreasId(String orderAreasId) {
        this.orderAreasId = orderAreasId;
    }


    public static class OrderInfoBean implements Parcelable {
        /**
         * evaluatedPrice : 1.0
         * orderAmount : 1.0
         * orderId : 11
         * orderStatus : T06
         * vehicleIdNumber :
         * plateNumber : 12
         */

        private String evaluatedPrice="";
        private String orderAmount="";
        private String orderId="";
        private String orderStatus="";
        private String vehicleIdNumber="";
        private String plateNumber="";

        public String getEvaluatedPrice() {
            return evaluatedPrice;
        }

        public void setEvaluatedPrice(String evaluatedPrice) {
            this.evaluatedPrice = evaluatedPrice;
        }

        public String getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(String orderAmount) {
            this.orderAmount = orderAmount;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getVehicleIdNumber() {
            return vehicleIdNumber;
        }

        public void setVehicleIdNumber(String vehicleIdNumber) {
            this.vehicleIdNumber = vehicleIdNumber;
        }

        public String getPlateNumber() {
            return plateNumber;
        }

        public void setPlateNumber(String plateNumber) {
            this.plateNumber = plateNumber;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.evaluatedPrice);
            dest.writeString(this.orderAmount);
            dest.writeString(this.orderId);
            dest.writeString(this.orderStatus);
            dest.writeString(this.vehicleIdNumber);
            dest.writeString(this.plateNumber);
        }

        public OrderInfoBean() {
        }

        protected OrderInfoBean(Parcel in) {
            this.evaluatedPrice = in.readString();
            this.orderAmount = in.readString();
            this.orderId = in.readString();
            this.orderStatus = in.readString();
            this.vehicleIdNumber = in.readString();
            this.plateNumber = in.readString();
        }

        public static final Creator<OrderInfoBean> CREATOR = new Creator<OrderInfoBean>() {
            @Override
            public OrderInfoBean createFromParcel(Parcel source) {
                return new OrderInfoBean(source);
            }

            @Override
            public OrderInfoBean[] newArray(int size) {
                return new OrderInfoBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.vehicleName);
        dest.writeString(this.seriesName);
        dest.writeString(this.carStatus);
        dest.writeString(this.orderStatusDesc);
        dest.writeString(this.fullName);
        dest.writeString(this.telephone);
        dest.writeString(this.bankName);
        dest.writeParcelable(this.OrderInfo, flags);
        dest.writeString(this.evaluatedPrice);
        dest.writeString(this.firstLicenseTime);
        dest.writeString(this.orderAreasName);
        dest.writeString(this.carStatusDesc);
        dest.writeString(this.drivingMileage);
        dest.writeString(this.bankNo);
        dest.writeString(this.id);
        dest.writeString(this.vehicleId);
        dest.writeString(this.orderAreasId);
    }

    public CarOrderResponse() {
    }

    protected CarOrderResponse(Parcel in) {
        this.vehicleName = in.readString();
        this.seriesName = in.readString();
        this.carStatus = in.readString();
        this.orderStatusDesc = in.readString();
        this.fullName = in.readString();
        this.telephone = in.readString();
        this.bankName = in.readString();
        this.OrderInfo = in.readParcelable(OrderInfoBean.class.getClassLoader());
        this.evaluatedPrice = in.readString();
        this.firstLicenseTime = in.readString();
        this.orderAreasName = in.readString();
        this.carStatusDesc = in.readString();
        this.drivingMileage = in.readString();
        this.bankNo = in.readString();
        this.id = in.readString();
        this.vehicleId = in.readString();
        this.orderAreasId = in.readString();
    }

    public static final Creator<CarOrderResponse> CREATOR = new Creator<CarOrderResponse>() {
        @Override
        public CarOrderResponse createFromParcel(Parcel source) {
            return new CarOrderResponse(source);
        }

        @Override
        public CarOrderResponse[] newArray(int size) {
            return new CarOrderResponse[size];
        }
    };
}