package com.lwb.cargovoice.module.mvp.entity.response;


import java.util.List;

public class BillListResponse {

    /**
     * jobKey : string
     * number : string
     * osTotal : 0
     * outstandingAmount : 0
     * shipmentList : [{"containerModeCode":"string","containerModeDesc":"string","goodsDesc":"string","goodsValue":"string","goodsValueCurrencyCode":"string","goodsValueCurrencyDesc":"string","portOfDestinationCode":"string","portOfDestinationName":"string","portOfOriginCode":"string","portOfOriginName":"string","serviceLevelCode":"string","serviceLevelDesc":"string","totalVolume":0,"totalVolumeUnitCode":"string","totalVolumeUnitDesc":"string","totalWeight":0,"totalWeightUnitCode":"string","totalWeightUnitDesc":"string","transportModeCode":"string","transportModeDesc":"string"}]
     * transactionDate : string
     */

    private String jobKey;
    private String number;
    private String osTotal;
    private String outstandingAmount;
    private String transactionDate;
    private List<ShipmentListBean> shipmentList;

    public String getJobKey() {
        return jobKey;
    }

    public void setJobKey(String jobKey) {
        this.jobKey = jobKey;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOsTotal() {
        return osTotal;
    }

    public void setOsTotal(String osTotal) {
        this.osTotal = osTotal;
    }

    public String getOutstandingAmount() {
        return outstandingAmount;
    }

    public void setOutstandingAmount(String outstandingAmount) {
        this.outstandingAmount = outstandingAmount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public List<ShipmentListBean> getShipmentList() {
        return shipmentList;
    }

    public void setShipmentList(List<ShipmentListBean> shipmentList) {
        this.shipmentList = shipmentList;
    }

    public static class ShipmentListBean {
        /**
         * containerModeCode : string
         * containerModeDesc : string
         * goodsDesc : string
         * goodsValue : string
         * goodsValueCurrencyCode : string
         * goodsValueCurrencyDesc : string
         * portOfDestinationCode : string
         * portOfDestinationName : string
         * portOfOriginCode : string
         * portOfOriginName : string
         * serviceLevelCode : string
         * serviceLevelDesc : string
         * totalVolume : 0
         * totalVolumeUnitCode : string
         * totalVolumeUnitDesc : string
         * totalWeight : 0
         * totalWeightUnitCode : string
         * totalWeightUnitDesc : string
         * transportModeCode : string
         * transportModeDesc : string
         */

        private String containerModeCode;
        private String containerModeDesc;
        private String goodsDesc;
        private String goodsValue;
        private String goodsValueCurrencyCode;
        private String goodsValueCurrencyDesc;
        private String portOfDestinationCode;
        private String portOfDestinationName;
        private String portOfOriginCode;
        private String portOfOriginName;
        private String serviceLevelCode;
        private String serviceLevelDesc;
        private String totalVolume;
        private String totalVolumeUnitCode;
        private String totalVolumeUnitDesc;
        private String totalWeight;
        private String totalWeightUnitCode;
        private String totalWeightUnitDesc;
        private String transportModeCode;
        private String transportModeDesc;
        private boolean isShow;

        public boolean isShow() {
            return isShow;
        }

        public void setShow(boolean show) {
            isShow = show;
        }

        public String getContainerModeCode() {
            return containerModeCode;
        }

        public void setContainerModeCode(String containerModeCode) {
            this.containerModeCode = containerModeCode;
        }

        public String getContainerModeDesc() {
            return containerModeDesc;
        }

        public void setContainerModeDesc(String containerModeDesc) {
            this.containerModeDesc = containerModeDesc;
        }

        public String getGoodsDesc() {
            return goodsDesc;
        }

        public void setGoodsDesc(String goodsDesc) {
            this.goodsDesc = goodsDesc;
        }

        public String getGoodsValue() {
            return goodsValue;
        }

        public void setGoodsValue(String goodsValue) {
            this.goodsValue = goodsValue;
        }

        public String getGoodsValueCurrencyCode() {
            return goodsValueCurrencyCode;
        }

        public void setGoodsValueCurrencyCode(String goodsValueCurrencyCode) {
            this.goodsValueCurrencyCode = goodsValueCurrencyCode;
        }

        public String getGoodsValueCurrencyDesc() {
            return goodsValueCurrencyDesc;
        }

        public void setGoodsValueCurrencyDesc(String goodsValueCurrencyDesc) {
            this.goodsValueCurrencyDesc = goodsValueCurrencyDesc;
        }

        public String getPortOfDestinationCode() {
            return portOfDestinationCode;
        }

        public void setPortOfDestinationCode(String portOfDestinationCode) {
            this.portOfDestinationCode = portOfDestinationCode;
        }

        public String getPortOfDestinationName() {
            return portOfDestinationName;
        }

        public void setPortOfDestinationName(String portOfDestinationName) {
            this.portOfDestinationName = portOfDestinationName;
        }

        public String getPortOfOriginCode() {
            return portOfOriginCode;
        }

        public void setPortOfOriginCode(String portOfOriginCode) {
            this.portOfOriginCode = portOfOriginCode;
        }

        public String getPortOfOriginName() {
            return portOfOriginName;
        }

        public void setPortOfOriginName(String portOfOriginName) {
            this.portOfOriginName = portOfOriginName;
        }

        public String getServiceLevelCode() {
            return serviceLevelCode;
        }

        public void setServiceLevelCode(String serviceLevelCode) {
            this.serviceLevelCode = serviceLevelCode;
        }

        public String getServiceLevelDesc() {
            return serviceLevelDesc;
        }

        public void setServiceLevelDesc(String serviceLevelDesc) {
            this.serviceLevelDesc = serviceLevelDesc;
        }

        public String getTotalVolume() {
            return totalVolume;
        }

        public void setTotalVolume(String totalVolume) {
            this.totalVolume = totalVolume;
        }

        public String getTotalVolumeUnitCode() {
            return totalVolumeUnitCode;
        }

        public void setTotalVolumeUnitCode(String totalVolumeUnitCode) {
            this.totalVolumeUnitCode = totalVolumeUnitCode;
        }

        public String getTotalVolumeUnitDesc() {
            return totalVolumeUnitDesc;
        }

        public void setTotalVolumeUnitDesc(String totalVolumeUnitDesc) {
            this.totalVolumeUnitDesc = totalVolumeUnitDesc;
        }

        public String getTotalWeight() {
            return totalWeight;
        }

        public void setTotalWeight(String totalWeight) {
            this.totalWeight = totalWeight;
        }

        public String getTotalWeightUnitCode() {
            return totalWeightUnitCode;
        }

        public void setTotalWeightUnitCode(String totalWeightUnitCode) {
            this.totalWeightUnitCode = totalWeightUnitCode;
        }

        public String getTotalWeightUnitDesc() {
            return totalWeightUnitDesc;
        }

        public void setTotalWeightUnitDesc(String totalWeightUnitDesc) {
            this.totalWeightUnitDesc = totalWeightUnitDesc;
        }

        public String getTransportModeCode() {
            return transportModeCode;
        }

        public void setTransportModeCode(String transportModeCode) {
            this.transportModeCode = transportModeCode;
        }

        public String getTransportModeDesc() {
            return transportModeDesc;
        }

        public void setTransportModeDesc(String transportModeDesc) {
            this.transportModeDesc = transportModeDesc;
        }
    }
}

