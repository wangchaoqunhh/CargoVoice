package com.cargo.basecommon.bean;

import com.cargo.basecommon.base.BaseApplication;

import java.util.ArrayList;
import java.util.List;

public class EnquiryAddGoalRequest {

    /**
     * additionalSvcList : [null]
     * businessInfo : {"containerModeCode":"string","containerModeDesc":"string","transportModeCode":"string","transportModeDesc":"string"}
     * commodityList : [{"commodityCode":"string","commodityDesc":"string","createTime":"2020-10-29T02:16:27.520Z","goodsDesc":"string","hsCode":"string","id":"string","imoClass":"string","mark":"string","packQty":0,"packTypeCode":"string","packTypeDesc":"string","updateTime":"2020-10-29T02:16:27.521Z","volume":0,"volumeUnitCode":"string","volumeUnitDesc":"string","weight":0,"weightUnitCode":"string","weightUnitDesc":"string"}]
     * consignee : {"address1":"string","addressType":"string","category":0,"city":"string","companyName":"string","contact":"string","countryCode":"string","countryName":"string","createTime":"2020-10-29T02:16:27.521Z","email":"string","id":"string","phone":"string","postcode":"string","state":"string","updateTime":"2020-10-29T02:16:27.521Z"}
     * containerList : [{"containerCount":0,"containerNumber":"string","containerTypeCode":"string","containerTypeDesc":"string","createTime":"2020-10-29T02:16:27.522Z","id":"string","isShipperOwned":true,"updateTime":"2020-10-29T02:16:27.522Z"}]
     * locationInfo : {"departureDate":"string","portOfDestinationCode":"string","portOfDestinationName":"string","portOfOriginCode":"string","portOfOriginName":"string"}
     * transportService : {"incoTermCode":"string","incoTermDesc":"string","serviceLevelCode":"string","serviceLevelDesc":"string"}
     */

    private BusinessInfoBean businessInfo;
    private ConsigneeBean consignee;//收货人
    private ConsigneeBean consignor;//发货人
    private LocationInfoBean locationInfo;
    private TransportServiceBean transportService;
    private List<AdditionalSvcListBean> additionalSvcList;
    private List<CommodityListBean> commodityList;//货件信息
    private List<ContainerListBean> containerList;//集装箱

    private EnquiryAddGoalRequest(BusinessInfoBean businessInfo, ConsigneeBean consignee, ConsigneeBean consignor, LocationInfoBean locationInfo, TransportServiceBean transportService, List<CommodityListBean> commodityList, List<ContainerListBean> containerList) {
        this.businessInfo = businessInfo;
        this.consignor = consignor;
        this.consignee = consignee;
        this.locationInfo = locationInfo;
        this.transportService = transportService;
        this.commodityList = commodityList;
        this.containerList = containerList;
    }

    public static void newInstance(BaseApplication application) {
        application.setRequest(new EnquiryAddGoalRequest(
                new EnquiryAddGoalRequest.BusinessInfoBean(),
                new EnquiryAddGoalRequest.ConsigneeBean(),
                new EnquiryAddGoalRequest.ConsigneeBean(),
                new EnquiryAddGoalRequest.LocationInfoBean(),
                new EnquiryAddGoalRequest.TransportServiceBean(),
                new ArrayList<>(),
                new ArrayList<>()));
    }

    public EnquiryAddGoalRequest() {
    }

    public BusinessInfoBean getBusinessInfo() {
        return businessInfo;
    }

    public void setBusinessInfo(BusinessInfoBean businessInfo) {
        this.businessInfo = businessInfo;
    }

    public ConsigneeBean getConsignee() {
        return consignee;
    }

    public void setConsignee(ConsigneeBean consignee) {
        this.consignee = consignee;
    }

    public ConsigneeBean getConsignor() {
        return consignor;
    }

    public void setConsignor(ConsigneeBean consignor) {
        this.consignor = consignor;
    }

    public LocationInfoBean getLocationInfo() {
        return locationInfo;
    }

    public void setLocationInfo(LocationInfoBean locationInfo) {
        this.locationInfo = locationInfo;
    }

    public TransportServiceBean getTransportService() {
        return transportService;
    }

    public void setTransportService(TransportServiceBean transportService) {
        this.transportService = transportService;
    }

    public List<AdditionalSvcListBean> getAdditionalSvcList() {
        return additionalSvcList;
    }

    public void setAdditionalSvcList(List<AdditionalSvcListBean> additionalSvcList) {
        this.additionalSvcList = additionalSvcList;
    }

    public List<CommodityListBean> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<CommodityListBean> commodityList) {
        this.commodityList = commodityList;
    }

    public List<ContainerListBean> getContainerList() {
        return containerList;
    }

    public void setContainerList(List<ContainerListBean> containerList) {
        this.containerList = containerList;
    }

    public static class BusinessInfoBean {
        /**
         * containerModeCode : string
         * containerModeDesc : string
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
        private String transportModeCode;
        private String transportModeDesc;

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

    public static class ConsigneeBean {
        /**
         * address1 : string
         * addressType : string
         * category : 0
         * city : string
         * companyName : string
         * contact : string
         * countryCode : string
         * countryName : string
         * createTime : 2020-10-29T02:16:27.521Z
         * email : string
         * id : string
         * phone : string
         * postcode : string
         * state : string
         * updateTime : 2020-10-29T02:16:27.521Z
         */

        private String address1;
        private String addressType;
        private int category;
        private String city;
        private String companyName;
        private String contact;
        private String countryCode;
        private String countryName;
        private String createTime;
        private String email;
        private String id;
        private String phone;
        private String postcode;
        private String state;//省 code
        private String stateName;//省 名字 （自己用不用传）
        private String updateTime;

        public String getStateName() {
            return stateName;
        }

        public void setStateName(String stateName) {
            this.stateName = stateName;
        }

        public String getAddress1() {
            return address1;
        }

        public void setAddress1(String address1) {
            this.address1 = address1;
        }

        public String getAddressType() {
            return addressType;
        }

        public void setAddressType(String addressType) {
            this.addressType = addressType;
        }

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }

    public static class LocationInfoBean {
        /**
         * departureDate : string
         * portOfDestinationCode : string
         * portOfDestinationName : string
         * portOfOriginCode : string
         * portOfOriginName : string
         */

        private String departureDate;
        private String portOfDestinationCode;
        private String portOfDestinationName;
        private String portOfOriginCode;
        private String portOfOriginName;

        public String getDepartureDate() {
            return departureDate;
        }

        public void setDepartureDate(String departureDate) {
            this.departureDate = departureDate;
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
    }

    public static class TransportServiceBean {
        /**
         * incoTermCode : string
         * incoTermDesc : string
         * serviceLevelCode : string
         * serviceLevelDesc : string
         */

        private String incoTermCode;
        private String incoTermDesc;
        private String serviceLevelCode;
        private String serviceLevelDesc;

        public String getIncoTermCode() {
            return incoTermCode;
        }

        public void setIncoTermCode(String incoTermCode) {
            this.incoTermCode = incoTermCode;
        }

        public String getIncoTermDesc() {
            return incoTermDesc;
        }

        public void setIncoTermDesc(String incoTermDesc) {
            this.incoTermDesc = incoTermDesc;
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
    }

    @Deprecated
    public static class AdditionalSvcListBean {
        /**
         * createTime : 2020-11-04T05:43:17.669Z
         * id : string
         * svcCode : string
         * svcCodeDesc : string
         * updateTime : 2020-11-04T05:43:17.670Z
         */

        private String createTime;
        private String id;
        private String svcCode;
        private String svcCodeDesc;
        private String updateTime;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSvcCode() {
            return svcCode;
        }

        public void setSvcCode(String svcCode) {
            this.svcCode = svcCode;
        }

        public String getSvcCodeDesc() {
            return svcCodeDesc;
        }

        public void setSvcCodeDesc(String svcCodeDesc) {
            this.svcCodeDesc = svcCodeDesc;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }

    public static class CommodityListBean {
        /**
         * commodityCode : string
         * commodityDesc : string
         * createTime : 2020-10-29T02:16:27.520Z
         * goodsDesc : string
         * hsCode : string
         * id : string
         * imoClass : string
         * mark : string
         * packQty : 0
         * packTypeCode : string
         * packTypeDesc : string
         * updateTime : 2020-10-29T02:16:27.521Z
         * volume : 0
         * volumeUnitCode : string
         * volumeUnitDesc : string
         * weight : 0
         * weightUnitCode : string
         * weightUnitDesc : string
         */

        private String commodityCode;
        private String commodityDesc;
        private String createTime;
        private String goodsDesc;
        private String hsCode;
        private String id;
        private String imoClass;
        private String mark;
        private String packQty;
        private String packTypeCode;
        private String packTypeDesc;
        private String updateTime;
        private String volume;
        private String volumeUnitCode;
        private String volumeUnitDesc;
        private String weight;
        private String weightUnitCode;
        private String weightUnitDesc;

        public String getCommodityCode() {
            return commodityCode;
        }

        public void setCommodityCode(String commodityCode) {
            this.commodityCode = commodityCode;
        }

        public String getCommodityDesc() {
            return commodityDesc;
        }

        public void setCommodityDesc(String commodityDesc) {
            this.commodityDesc = commodityDesc;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getGoodsDesc() {
            return goodsDesc;
        }

        public void setGoodsDesc(String goodsDesc) {
            this.goodsDesc = goodsDesc;
        }

        public String getHsCode() {
            return hsCode;
        }

        public void setHsCode(String hsCode) {
            this.hsCode = hsCode;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImoClass() {
            return imoClass;
        }

        public void setImoClass(String imoClass) {
            this.imoClass = imoClass;
        }

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }

        public String getPackQty() {
            return packQty;
        }

        public void setPackQty(String packQty) {
            this.packQty = packQty;
        }

        public String getPackTypeCode() {
            return packTypeCode;
        }

        public void setPackTypeCode(String packTypeCode) {
            this.packTypeCode = packTypeCode;
        }

        public String getPackTypeDesc() {
            return packTypeDesc;
        }

        public void setPackTypeDesc(String packTypeDesc) {
            this.packTypeDesc = packTypeDesc;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getVolume() {
            return volume;
        }

        public void setVolume(String volume) {
            this.volume = volume;
        }

        public String getVolumeUnitCode() {
            return volumeUnitCode;
        }

        public void setVolumeUnitCode(String volumeUnitCode) {
            this.volumeUnitCode = volumeUnitCode;
        }

        public String getVolumeUnitDesc() {
            return volumeUnitDesc;
        }

        public void setVolumeUnitDesc(String volumeUnitDesc) {
            this.volumeUnitDesc = volumeUnitDesc;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getWeightUnitCode() {
            return weightUnitCode;
        }

        public void setWeightUnitCode(String weightUnitCode) {
            this.weightUnitCode = weightUnitCode;
        }

        public String getWeightUnitDesc() {
            return weightUnitDesc;
        }

        public void setWeightUnitDesc(String weightUnitDesc) {
            this.weightUnitDesc = weightUnitDesc;
        }
    }

    public static class ContainerListBean {
        /**
         * containerCount : 0
         * containerNumber : string
         * containerTypeCode : string
         * containerTypeDesc : string
         * createTime : 2020-10-29T02:16:27.522Z
         * id : string
         * isShipperOwned : true
         * updateTime : 2020-10-29T02:16:27.522Z
         */

        private int containerCount;
        private String containerNumber;
        private String containerTypeCode;
        private String containerTypeDesc;
        private String createTime;
        private String id;
        private boolean isShipperOwned;
        private String updateTime;

        public int getContainerCount() {
            return containerCount;
        }

        public void setContainerCount(int containerCount) {
            this.containerCount = containerCount;
        }

        public String getContainerNumber() {
            return containerNumber;
        }

        public void setContainerNumber(String containerNumber) {
            this.containerNumber = containerNumber;
        }

        public String getContainerTypeCode() {
            return containerTypeCode;
        }

        public void setContainerTypeCode(String containerTypeCode) {
            this.containerTypeCode = containerTypeCode;
        }

        public String getContainerTypeDesc() {
            return containerTypeDesc;
        }

        public void setContainerTypeDesc(String containerTypeDesc) {
            this.containerTypeDesc = containerTypeDesc;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public boolean isShipperOwned() {
            return isShipperOwned;
        }

        public void setShipperOwned(boolean shipperOwned) {
            isShipperOwned = shipperOwned;
        }
    }
}
