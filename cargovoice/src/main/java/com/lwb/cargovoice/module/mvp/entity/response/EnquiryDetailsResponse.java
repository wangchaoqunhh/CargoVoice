package com.lwb.cargovoice.module.mvp.entity.response;


import java.util.List;

public class EnquiryDetailsResponse {

    /**
     * additionalSvcList : [null]
     * basicInfo : {"inquiryCreateTime":"2020-10-28T08:53:21.411Z","inquiryNo":"string","quotedPrice":0,"status":0}
     * businessInfo : {"containerModeCode":"string","containerModeDesc":"string","transportModeCode":"string","transportModeDesc":"string"}
     * commodityList : [{"commodityCode":"string","commodityDesc":"string","createTime":"2020-10-28T08:53:21.411Z","goodsDesc":"string","hsCode":"string","id":"string","imoClass":"string","mark":"string","packQty":0,"packTypeCode":"string","packTypeDesc":"string","updateTime":"2020-10-28T08:53:21.411Z","volume":0,"volumeUnitCode":"string","volumeUnitDesc":"string","weight":0,"weightUnitCode":"string","weightUnitDesc":"string"}]
     * containerList : [{"containerCount":0,"containerNumber":"string","containerTypeCode":"string","containerTypeDesc":"string","createTime":"2020-10-28T08:53:21.411Z","id":"string","isShipperOwned":true,"updateTime":"2020-10-28T08:53:21.411Z"}]
     * locationInfo : {"departureDate":"string","portOfDestinationCode":"string","portOfDestinationName":"string","portOfOriginCode":"string","portOfOriginName":"string"}
     * organizationList : [{"address1":"string","addressType":"string","category":0,"city":"string","companyName":"string","contact":"string","countryCode":"string","countryName":"string","createTime":"2020-10-28T08:53:21.411Z","email":"string","id":"string","phone":"string","postcode":"string","state":"string","updateTime":"2020-10-28T08:53:21.411Z"}]
     * transportService : {"incoTermCode":"string","incoTermDesc":"string","serviceLevelCode":"string","serviceLevelDesc":"string"}
     */

    private BasicInfoBean basicInfo;
    private BusinessInfoBean businessInfo;
    private LocationInfoBean locationInfo;
    private TransportServiceBean transportService;
    private JobCostingBean jobCosting;
    private List<Object> additionalSvcList;
    private List<CommodityListBean> commodityList;
    private List<ContainerListBean> containerList;
    private List<OrganizationListBean> organizationList;

    public JobCostingBean getJobCosting() {
        return jobCosting;
    }

    public void setJobCosting(JobCostingBean jobCosting) {
        this.jobCosting = jobCosting;
    }

    public BasicInfoBean getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(BasicInfoBean basicInfo) {
        this.basicInfo = basicInfo;
    }

    public BusinessInfoBean getBusinessInfo() {
        return businessInfo;
    }

    public void setBusinessInfo(BusinessInfoBean businessInfo) {
        this.businessInfo = businessInfo;
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

    public List<Object> getAdditionalSvcList() {
        return additionalSvcList;
    }

    public void setAdditionalSvcList(List<Object> additionalSvcList) {
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

    public List<OrganizationListBean> getOrganizationList() {
        return organizationList;
    }

    public void setOrganizationList(List<OrganizationListBean> organizationList) {
        this.organizationList = organizationList;
    }

    public static class BasicInfoBean {
        /**
         * inquiryCreateTime : 2020-10-28T08:53:21.411Z
         * inquiryNo : string
         * quotedPrice : 0
         * status : 0
         */

        private String inquiryCreateTime;
        private String inquiryNo;
        private int quotedPrice;
        private String status;
        private String statusCode;
        private String statusDesc;

        public String getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(String statusCode) {
            this.statusCode = statusCode;
        }

        public String getStatusDesc() {
            return statusDesc;
        }

        public void setStatusDesc(String statusDesc) {
            this.statusDesc = statusDesc;
        }

        public String getInquiryCreateTime() {
            return inquiryCreateTime;
        }

        public void setInquiryCreateTime(String inquiryCreateTime) {
            this.inquiryCreateTime = inquiryCreateTime;
        }

        public String getInquiryNo() {
            return inquiryNo;
        }

        public void setInquiryNo(String inquiryNo) {
            this.inquiryNo = inquiryNo;
        }

        public int getQuotedPrice() {
            return quotedPrice;
        }

        public void setQuotedPrice(int quotedPrice) {
            this.quotedPrice = quotedPrice;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class BusinessInfoBean {
        /**
         * containerModeCode : string
         * containerModeDesc : string
         * transportModeCode : string
         * transportModeDesc : string
         */

        private String containerModeCode;
        private String containerModeDesc;
        private String transportModeCode;
        private String transportModeDesc;
        private int totalVolume;
        private String totalVolumeUnitCode;
        private String totalVolumeUnitDesc;
        private int totalWeight;
        private String totalWeightUnitCode;
        private String totalWeightUnitDesc;

        public int getTotalVolume() {
            return totalVolume;
        }

        public void setTotalVolume(int totalVolume) {
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

        public int getTotalWeight() {
            return totalWeight;
        }

        public void setTotalWeight(int totalWeight) {
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

    public static class CommodityListBean {
        /**
         * commodityCode : string
         * commodityDesc : string
         * createTime : 2020-10-28T08:53:21.411Z
         * goodsDesc : string
         * hsCode : string
         * id : string
         * imoClass : string
         * mark : string
         * packQty : 0
         * packTypeCode : string
         * packTypeDesc : string
         * updateTime : 2020-10-28T08:53:21.411Z
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
         * createTime : 2020-10-28T08:53:21.411Z
         * id : string
         * isShipperOwned : true
         * updateTime : 2020-10-28T08:53:21.411Z
         */

        private String containerCount;
        private String containerNumber;
        private String containerTypeCode;
        private String containerTypeDesc;
        private String createTime;
        private String id;
        private boolean isShipperOwned;
        private String updateTime;

        public String getContainerCount() {
            return containerCount;
        }

        public void setContainerCount(String containerCount) {
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

        public boolean isIsShipperOwned() {
            return isShipperOwned;
        }

        public void setIsShipperOwned(boolean isShipperOwned) {
            this.isShipperOwned = isShipperOwned;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }

    public static class OrganizationListBean {
        /**
         * address1 : string
         * addressType : string
         * category : 0
         * city : string
         * companyName : string
         * contact : string
         * countryCode : string
         * countryName : string
         * createTime : 2020-10-28T08:53:21.411Z
         * email : string
         * id : string
         * phone : string
         * postcode : string
         * state : string
         * updateTime : 2020-10-28T08:53:21.411Z
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
        private String state;
        private String stateName;//自己用
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

    public static class JobCostingBean {
        /**
         * chargeLineList : [{"chargeCodeDesc":"string","costGSTVATTaxTypeCode":"string","costLocalAmount":0,"createTime":"2020-11-10T12:45:22.574Z","id":"string","sellOSCurrencyCode":"string","sellOSCurrencyDesc":"string","updateTime":"2020-11-10T12:45:22.574Z"}]
         * createTime : 2020-11-10T12:45:22.574Z
         * currencyCode : string
         * currencyDesc : string
         * id : string
         * totalWIP : 0
         * updateTime : 2020-11-10T12:45:22.574Z
         */

        private String createTime;
        private String currencyCode;
        private String currencyDesc;
        private String id;
        private String totalWIP;
        private String updateTime;
        private List<ChargeLineListBean> chargeLineList;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCurrencyCode() {
            return currencyCode;
        }

        public void setCurrencyCode(String currencyCode) {
            this.currencyCode = currencyCode;
        }

        public String getCurrencyDesc() {
            return currencyDesc;
        }

        public void setCurrencyDesc(String currencyDesc) {
            this.currencyDesc = currencyDesc;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTotalWIP() {
            return totalWIP;
        }

        public void setTotalWIP(String totalWIP) {
            this.totalWIP = totalWIP;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public List<ChargeLineListBean> getChargeLineList() {
            return chargeLineList;
        }

        public void setChargeLineList(List<ChargeLineListBean> chargeLineList) {
            this.chargeLineList = chargeLineList;
        }

        public static class ChargeLineListBean {
            /**
             * chargeCodeDesc : string
             * costGSTVATTaxTypeCode : string
             * costLocalAmount : 0
             * createTime : 2020-11-10T12:45:22.574Z
             * id : string
             * sellOSCurrencyCode : string
             * sellOSCurrencyDesc : string
             * updateTime : 2020-11-10T12:45:22.574Z
             */

            private String chargeCodeDesc;
            private String costGSTVATTaxTypeCode;
            private String costLocalAmount;
            private String createTime;
            private String id;
            private String sellOSCurrencyCode;
            private String sellOSCurrencyDesc;
            private String updateTime;

            public String getChargeCodeDesc() {
                return chargeCodeDesc;
            }

            public void setChargeCodeDesc(String chargeCodeDesc) {
                this.chargeCodeDesc = chargeCodeDesc;
            }

            public String getCostGSTVATTaxTypeCode() {
                return costGSTVATTaxTypeCode;
            }

            public void setCostGSTVATTaxTypeCode(String costGSTVATTaxTypeCode) {
                this.costGSTVATTaxTypeCode = costGSTVATTaxTypeCode;
            }

            public String getCostLocalAmount() {
                return costLocalAmount;
            }

            public void setCostLocalAmount(String costLocalAmount) {
                this.costLocalAmount = costLocalAmount;
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

            public String getSellOSCurrencyCode() {
                return sellOSCurrencyCode;
            }

            public void setSellOSCurrencyCode(String sellOSCurrencyCode) {
                this.sellOSCurrencyCode = sellOSCurrencyCode;
            }

            public String getSellOSCurrencyDesc() {
                return sellOSCurrencyDesc;
            }

            public void setSellOSCurrencyDesc(String sellOSCurrencyDesc) {
                this.sellOSCurrencyDesc = sellOSCurrencyDesc;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }
        }
    }
}

