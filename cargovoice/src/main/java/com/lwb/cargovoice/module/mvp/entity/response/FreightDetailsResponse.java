package com.lwb.cargovoice.module.mvp.entity.response;


import java.util.List;

public class FreightDetailsResponse {

    /**
     * additionalReferenceList : [{"createTime":"2020-11-01T09:55:58.145Z","id":"string","referenceNumber":"string","typeCode":"string","typeDesc":"string","updateTime":"2020-11-01T09:55:58.145Z"}]
     * containerList : [{"arrivalCartageComplete":"string","containerCount":0,"containerNumber":"string","containerTypeCode":"string","containerTypeDesc":"string","createTime":"2020-11-01T09:55:58.145Z","departureEstimatedPickup":"string","id":"string","updateTime":"2020-11-01T09:55:58.145Z"}]
     * containerModeCode : string
     * containerModeDesc : string
     * createTime : 2020-11-01T09:55:58.145Z
     * eta : string
     * etd : string
     * forwardingShptNumber : string
     * goodsDesc : string
     * goodsValue : 0
     * goodsValueCurrencyCode : string
     * goodsValueCurrencyDesc : string
     * hblawbChargesDisplayCode : string
     * hblawbChargesDisplayDesc : string
     * id : string
     * milestoneList : [{"actualDate":"string","createTime":"2020-11-01T09:55:58.145Z","description":"string","estimatedDate":"string","id":"string","updateTime":"2020-11-01T09:55:58.145Z"}]
     * neutralMaster : true
     * noteList : [{"createTime":"2020-11-01T09:55:58.145Z","customDescription":true,"description":"string","id":"string","noteText":"string","updateTime":"2020-11-01T09:55:58.145Z"}]
     * orderList : [{"createTime":"2020-11-01T09:55:58.145Z","id":"string","orderDateTime":"string","orderNumber":"string","statusCode":"string","statusDesc":"string","updateTime":"2020-11-01T09:55:58.145Z"}]
     * outerPacks : 0
     * outerPacksPackageTypeCode : string
     * outerPacksPackageTypeDesc : string
     * ownerRef : string
     * packingLineList : [{"containerNumber":"string","createTime":"2020-11-01T09:55:58.145Z","goodsDesc":"string","id":"string","packQty":0,"packTypeCode":"string","packTypeDesc":"string","updateTime":"2020-11-01T09:55:58.145Z"}]
     * portOfDestinationCode : string
     * portOfDestinationName : string
     * portOfOriginCode : string
     * portOfOriginName : string
     * quoteNumber : string
     * releaseTypeCode : string
     * releaseTypeDesc : string
     * serviceLevelCode : string
     * serviceLevelDesc : string
     * shippedOnBoardCode : string
     * shippedOnBoardDesc : string
     * totalVolume : 0
     * totalVolumeUnitCode : string
     * totalVolumeUnitDesc : string
     * totalWeight : 0
     * totalWeightUnitCode : string
     * totalWeightUnitDesc : string
     * transportLegList : [{"actualArrival":"string","actualDeparture":"string","createTime":"2020-11-01T09:55:58.145Z","id":"string","legType":"string","transportMode":"string","updateTime":"2020-11-01T09:55:58.145Z","vesselName":"string","voyageFlightNo":"string"}]
     * transportModeCode : string
     * transportModeDesc : string
     * updateTime : 2020-11-01T09:55:58.145Z
     * wayBillNumber : string
     */

    private String containerModeCode;
    private String containerModeDesc;
    private String createTime;
    private String eta;
    private String etd;
    private String forwardingShptNumber;
    private String goodsDesc;
    private String goodsValue;
    private String goodsValueCurrencyCode;
    private String goodsValueCurrencyDesc;
    private String hblawbChargesDisplayCode;
    private String hblawbChargesDisplayDesc;
    private String id;
    private boolean neutralMaster;
    private String outerPacks;
    private String outerPacksPackageTypeCode;
    private String outerPacksPackageTypeDesc;
    private String ownerRef;
    private String portOfDestinationCode;
    private String portOfDestinationName;
    private String portOfOriginCode;
    private String portOfOriginName;
    private String quoteNumber;
    private String releaseTypeCode;
    private String releaseTypeDesc;
    private String serviceLevelCode;
    private String serviceLevelDesc;
    private String shippedOnBoardCode;
    private String shippedOnBoardDesc;
    private String totalVolume;
    private String totalVolumeUnitCode;
    private String totalVolumeUnitDesc;
    private String totalWeight;
    private String totalWeightUnitCode;
    private String totalWeightUnitDesc;
    private String transportModeCode;
    private String transportModeDesc;
    private String updateTime;
    private String wayBillNumber;
    private String lastestMilestoneDesc;
    private LocalProcessingBean localProcessing;
    private List<AdditionalReferenceListBean> additionalReferenceList;//包含的参考信息列表
    private List<ContainerListBean> containerList;//包含的集装箱信息列表
    private List<MilestoneListBean> milestoneList;//包含的里程碑列表
    private List<NoteListBean> noteList;//包含的备注信息列表
    private List<OrderListBean> orderList;//包含的订单信息列表
    private List<PackingLineListBean> packingLineList;//包含的商品信息列表
    private List<TransportLegListBean> transportLegList;//运输信息

    public String getLastestMilestoneDesc() {
        return lastestMilestoneDesc;
    }

    public void setLastestMilestoneDesc(String lastestMilestoneDesc) {
        this.lastestMilestoneDesc = lastestMilestoneDesc;
    }

    public LocalProcessingBean getLocalProcessing() {
        return localProcessing;
    }

    public void setLocalProcessing(LocalProcessingBean localProcessing) {
        this.localProcessing = localProcessing;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getEtd() {
        return etd;
    }

    public void setEtd(String etd) {
        this.etd = etd;
    }

    public String getForwardingShptNumber() {
        return forwardingShptNumber;
    }

    public void setForwardingShptNumber(String forwardingShptNumber) {
        this.forwardingShptNumber = forwardingShptNumber;
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

    public String getHblawbChargesDisplayCode() {
        return hblawbChargesDisplayCode;
    }

    public void setHblawbChargesDisplayCode(String hblawbChargesDisplayCode) {
        this.hblawbChargesDisplayCode = hblawbChargesDisplayCode;
    }

    public String getHblawbChargesDisplayDesc() {
        return hblawbChargesDisplayDesc;
    }

    public void setHblawbChargesDisplayDesc(String hblawbChargesDisplayDesc) {
        this.hblawbChargesDisplayDesc = hblawbChargesDisplayDesc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isNeutralMaster() {
        return neutralMaster;
    }

    public void setNeutralMaster(boolean neutralMaster) {
        this.neutralMaster = neutralMaster;
    }

    public String getOuterPacks() {
        return outerPacks;
    }

    public void setOuterPacks(String outerPacks) {
        this.outerPacks = outerPacks;
    }

    public String getOuterPacksPackageTypeCode() {
        return outerPacksPackageTypeCode;
    }

    public void setOuterPacksPackageTypeCode(String outerPacksPackageTypeCode) {
        this.outerPacksPackageTypeCode = outerPacksPackageTypeCode;
    }

    public String getOuterPacksPackageTypeDesc() {
        return outerPacksPackageTypeDesc;
    }

    public void setOuterPacksPackageTypeDesc(String outerPacksPackageTypeDesc) {
        this.outerPacksPackageTypeDesc = outerPacksPackageTypeDesc;
    }

    public String getOwnerRef() {
        return ownerRef;
    }

    public void setOwnerRef(String ownerRef) {
        this.ownerRef = ownerRef;
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

    public String getQuoteNumber() {
        return quoteNumber;
    }

    public void setQuoteNumber(String quoteNumber) {
        this.quoteNumber = quoteNumber;
    }

    public String getReleaseTypeCode() {
        return releaseTypeCode;
    }

    public void setReleaseTypeCode(String releaseTypeCode) {
        this.releaseTypeCode = releaseTypeCode;
    }

    public String getReleaseTypeDesc() {
        return releaseTypeDesc;
    }

    public void setReleaseTypeDesc(String releaseTypeDesc) {
        this.releaseTypeDesc = releaseTypeDesc;
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

    public String getShippedOnBoardCode() {
        return shippedOnBoardCode;
    }

    public void setShippedOnBoardCode(String shippedOnBoardCode) {
        this.shippedOnBoardCode = shippedOnBoardCode;
    }

    public String getShippedOnBoardDesc() {
        return shippedOnBoardDesc;
    }

    public void setShippedOnBoardDesc(String shippedOnBoardDesc) {
        this.shippedOnBoardDesc = shippedOnBoardDesc;
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getWayBillNumber() {
        return wayBillNumber;
    }

    public void setWayBillNumber(String wayBillNumber) {
        this.wayBillNumber = wayBillNumber;
    }

    public List<AdditionalReferenceListBean> getAdditionalReferenceList() {
        return additionalReferenceList;
    }

    public void setAdditionalReferenceList(List<AdditionalReferenceListBean> additionalReferenceList) {
        this.additionalReferenceList = additionalReferenceList;
    }

    public List<ContainerListBean> getContainerList() {
        return containerList;
    }

    public void setContainerList(List<ContainerListBean> containerList) {
        this.containerList = containerList;
    }

    public List<MilestoneListBean> getMilestoneList() {
        return milestoneList;
    }

    public void setMilestoneList(List<MilestoneListBean> milestoneList) {
        this.milestoneList = milestoneList;
    }

    public List<NoteListBean> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<NoteListBean> noteList) {
        this.noteList = noteList;
    }

    public List<OrderListBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderListBean> orderList) {
        this.orderList = orderList;
    }

    public List<PackingLineListBean> getPackingLineList() {
        return packingLineList;
    }

    public void setPackingLineList(List<PackingLineListBean> packingLineList) {
        this.packingLineList = packingLineList;
    }

    public List<TransportLegListBean> getTransportLegList() {
        return transportLegList;
    }

    public void setTransportLegList(List<TransportLegListBean> transportLegList) {
        this.transportLegList = transportLegList;
    }

    public static class AdditionalReferenceListBean {
        /**
         * createTime : 2020-11-01T09:55:58.145Z
         * id : string
         * referenceNumber : string
         * typeCode : string
         * typeDesc : string
         * updateTime : 2020-11-01T09:55:58.145Z
         */

        private String createTime;
        private String id;
        private String referenceNumber;
        private String typeCode;
        private String typeDesc;
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

        public String getReferenceNumber() {
            return referenceNumber;
        }

        public void setReferenceNumber(String referenceNumber) {
            this.referenceNumber = referenceNumber;
        }

        public String getTypeCode() {
            return typeCode;
        }

        public void setTypeCode(String typeCode) {
            this.typeCode = typeCode;
        }

        public String getTypeDesc() {
            return typeDesc;
        }

        public void setTypeDesc(String typeDesc) {
            this.typeDesc = typeDesc;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }

    public static class ContainerListBean {
        /**
         * arrivalCartageComplete : string
         * containerCount : 0
         * containerNumber : string
         * containerTypeCode : string
         * containerTypeDesc : string
         * createTime : 2020-11-01T09:55:58.145Z
         * departureEstimatedPickup : string
         * id : string
         * updateTime : 2020-11-01T09:55:58.145Z
         */

        private String arrivalCartageComplete;
        private String containerCount;
        private String containerNumber;
        private String containerTypeCode;
        private String containerTypeDesc;
        private String createTime;
        private String departureEstimatedPickup;
        private String id;
        private String updateTime;

        public String getArrivalCartageComplete() {
            return arrivalCartageComplete;
        }

        public void setArrivalCartageComplete(String arrivalCartageComplete) {
            this.arrivalCartageComplete = arrivalCartageComplete;
        }

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

        public String getDepartureEstimatedPickup() {
            return departureEstimatedPickup;
        }

        public void setDepartureEstimatedPickup(String departureEstimatedPickup) {
            this.departureEstimatedPickup = departureEstimatedPickup;
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
    }

    public static class MilestoneListBean {
        /**
         * actualDate : string
         * createTime : 2020-11-01T09:55:58.145Z
         * description : string
         * estimatedDate : string
         * id : string
         * updateTime : 2020-11-01T09:55:58.145Z
         */

        private String actualDate;
        private String createTime;
        private String description;
        private String estimatedDate;
        private String id;
        private String updateTime;

        public String getActualDate() {
            return actualDate;
        }

        public void setActualDate(String actualDate) {
            this.actualDate = actualDate;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getEstimatedDate() {
            return estimatedDate;
        }

        public void setEstimatedDate(String estimatedDate) {
            this.estimatedDate = estimatedDate;
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
    }

    public static class NoteListBean {
        /**
         * createTime : 2020-11-01T09:55:58.145Z
         * customDescription : true
         * description : string
         * id : string
         * noteText : string
         * updateTime : 2020-11-01T09:55:58.145Z
         */

        private String createTime;
        private boolean customDescription;
        private String description;
        private String id;
        private String noteText;
        private String updateTime;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public boolean isCustomDescription() {
            return customDescription;
        }

        public void setCustomDescription(boolean customDescription) {
            this.customDescription = customDescription;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNoteText() {
            return noteText;
        }

        public void setNoteText(String noteText) {
            this.noteText = noteText;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }

    public static class OrderListBean {
        /**
         * createTime : 2020-11-01T09:55:58.145Z
         * id : string
         * orderDateTime : string
         * orderNumber : string
         * statusCode : string
         * statusDesc : string
         * updateTime : 2020-11-01T09:55:58.145Z
         */

        private String createTime;
        private String id;
        private String orderDateTime;
        private String orderNumber;
        private String statusCode;
        private String statusDesc;
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

        public String getOrderDateTime() {
            return orderDateTime;
        }

        public void setOrderDateTime(String orderDateTime) {
            this.orderDateTime = orderDateTime;
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }

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

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }

    public static class PackingLineListBean {
        /**
         * containerNumber : string
         * createTime : 2020-11-01T09:55:58.145Z
         * goodsDesc : string
         * id : string
         * packQty : 0
         * packTypeCode : string
         * packTypeDesc : string
         * updateTime : 2020-11-01T09:55:58.145Z
         */

        private String containerNumber;
        private String createTime;
        private String goodsDesc;
        private String id;
        private String packQty;
        private String packTypeCode;
        private String packTypeDesc;
        private String updateTime;

        public String getContainerNumber() {
            return containerNumber;
        }

        public void setContainerNumber(String containerNumber) {
            this.containerNumber = containerNumber;
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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
    }

    public static class TransportLegListBean {
        /**
         * actualArrival : string
         * actualDeparture : string
         * createTime : 2020-11-01T09:55:58.145Z
         * id : string
         * legType : string
         * transportMode : string
         * updateTime : 2020-11-01T09:55:58.145Z
         * vesselName : string
         * voyageFlightNo : string
         */

        private String actualArrival;
        private String actualDeparture;
        private String createTime;
        private String id;
        private String legType;
        private String transportMode;
        private String transportModeDesc;
        private String updateTime;
        private String vesselName;
        private String voyageFlightNo;
        private String carrierName;
        private String carrierCode;
        private CarrierBean carrier;

        public String getTransportModeDesc() {
            return transportModeDesc;
        }

        public void setTransportModeDesc(String transportModeDesc) {
            this.transportModeDesc = transportModeDesc;
        }

        public String getCarrierName() {
            return carrierName;
        }

        public void setCarrierName(String carrierName) {
            this.carrierName = carrierName;
        }

        public String getCarrierCode() {
            return carrierCode;
        }

        public void setCarrierCode(String carrierCode) {
            this.carrierCode = carrierCode;
        }

        public CarrierBean getCarrier() {
            return carrier;
        }

        public void setCarrier(CarrierBean carrier) {
            this.carrier = carrier;
        }

        public String getActualArrival() {
            return actualArrival;
        }

        public void setActualArrival(String actualArrival) {
            this.actualArrival = actualArrival;
        }

        public String getActualDeparture() {
            return actualDeparture;
        }

        public void setActualDeparture(String actualDeparture) {
            this.actualDeparture = actualDeparture;
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

        public String getLegType() {
            return legType;
        }

        public void setLegType(String legType) {
            this.legType = legType;
        }

        public String getTransportMode() {
            return transportMode;
        }

        public void setTransportMode(String transportMode) {
            this.transportMode = transportMode;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getVesselName() {
            return vesselName;
        }

        public void setVesselName(String vesselName) {
            this.vesselName = vesselName;
        }

        public String getVoyageFlightNo() {
            return voyageFlightNo;
        }

        public void setVoyageFlightNo(String voyageFlightNo) {
            this.voyageFlightNo = voyageFlightNo;
        }

        public static class CarrierBean {
            /**
             * id : null
             * createTime : null
             * updateTime : null
             * addressType : null
             * address1 : null
             * address2 : null
             * city : null
             * companyName : null
             * contact : null
             * countryCode : null
             * countryName : null
             * organizationCode : null
             * state : null
             * stateName : null
             */

            private Object id;
            private Object createTime;
            private Object updateTime;
            private Object addressType;
            private Object address1;
            private Object address2;
            private Object city;
            private Object companyName;
            private Object contact;
            private Object countryCode;
            private Object countryName;
            private Object organizationCode;
            private Object state;
            private Object stateName;

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public Object getAddressType() {
                return addressType;
            }

            public void setAddressType(Object addressType) {
                this.addressType = addressType;
            }

            public Object getAddress1() {
                return address1;
            }

            public void setAddress1(Object address1) {
                this.address1 = address1;
            }

            public Object getAddress2() {
                return address2;
            }

            public void setAddress2(Object address2) {
                this.address2 = address2;
            }

            public Object getCity() {
                return city;
            }

            public void setCity(Object city) {
                this.city = city;
            }

            public Object getCompanyName() {
                return companyName;
            }

            public void setCompanyName(Object companyName) {
                this.companyName = companyName;
            }

            public Object getContact() {
                return contact;
            }

            public void setContact(Object contact) {
                this.contact = contact;
            }

            public Object getCountryCode() {
                return countryCode;
            }

            public void setCountryCode(Object countryCode) {
                this.countryCode = countryCode;
            }

            public Object getCountryName() {
                return countryName;
            }

            public void setCountryName(Object countryName) {
                this.countryName = countryName;
            }

            public Object getOrganizationCode() {
                return organizationCode;
            }

            public void setOrganizationCode(Object organizationCode) {
                this.organizationCode = organizationCode;
            }

            public Object getState() {
                return state;
            }

            public void setState(Object state) {
                this.state = state;
            }

            public Object getStateName() {
                return stateName;
            }

            public void setStateName(Object stateName) {
                this.stateName = stateName;
            }
        }

    }

    public static class LocalProcessingBean {
        /**
         * additionalServiceList : [{"booked":"string","completed":"string","contractorId":"string","createTime":"2020-11-13T00:26:41.329Z","duration":"string","id":"string","locationId":"string","reference":"string","serviceCodeCode":"string","serviceCodeDescription":"string","serviceCount":0,"serviceNote":"string","updateTime":"2020-11-13T00:26:41.329Z"}]
         * createTime : 2020-11-13T00:26:41.329Z
         * deliveryCartageAdvised : string
         * deliveryCartageCompleted : string
         * deliveryRequiredBy : string
         * estimatedPickup : string
         * fclAvailable : string
         * id : string
         * lclAvailable : string
         * pickupCartageAdvised : string
         * pickupCartageCompleted : string
         * pickupRequiredBy : string
         * prStringOptionForPackagesOnAWBCode : string
         * prStringOptionForPackagesOnAWBDesc : string
         * updateTime : 2020-11-13T00:26:41.329Z
         */

        private String createTime;
        private String deliveryCartageAdvised;//
        private String deliveryCartageCompleted;//
        private String deliveryRequiredBy;//
        private String estimatedPickup;//
        private String fclAvailable;//
        private String fclStorageCommences;//
        private String id;
        private String lclAvailable;//
        private String lclStorageCommences;//
        private String pickupCartageAdvised;//
        private String pickupCartageCompleted;//
        private String pickupRequiredBy;//
        private String prStringOptionForPackagesOnAWBCode;
        private String prStringOptionForPackagesOnAWBDesc;
        private String updateTime;
        private List<AdditionalServiceListBean> additionalServiceList;

        public String getFclStorageCommences() {
            return fclStorageCommences;
        }

        public void setFclStorageCommences(String fclStorageCommences) {
            this.fclStorageCommences = fclStorageCommences;
        }

        public String getLclStorageCommences() {
            return lclStorageCommences;
        }

        public void setLclStorageCommences(String lclStorageCommences) {
            this.lclStorageCommences = lclStorageCommences;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDeliveryCartageAdvised() {
            return deliveryCartageAdvised;
        }

        public void setDeliveryCartageAdvised(String deliveryCartageAdvised) {
            this.deliveryCartageAdvised = deliveryCartageAdvised;
        }

        public String getDeliveryCartageCompleted() {
            return deliveryCartageCompleted;
        }

        public void setDeliveryCartageCompleted(String deliveryCartageCompleted) {
            this.deliveryCartageCompleted = deliveryCartageCompleted;
        }

        public String getDeliveryRequiredBy() {
            return deliveryRequiredBy;
        }

        public void setDeliveryRequiredBy(String deliveryRequiredBy) {
            this.deliveryRequiredBy = deliveryRequiredBy;
        }

        public String getEstimatedPickup() {
            return estimatedPickup;
        }

        public void setEstimatedPickup(String estimatedPickup) {
            this.estimatedPickup = estimatedPickup;
        }

        public String getFclAvailable() {
            return fclAvailable;
        }

        public void setFclAvailable(String fclAvailable) {
            this.fclAvailable = fclAvailable;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLclAvailable() {
            return lclAvailable;
        }

        public void setLclAvailable(String lclAvailable) {
            this.lclAvailable = lclAvailable;
        }

        public String getPickupCartageAdvised() {
            return pickupCartageAdvised;
        }

        public void setPickupCartageAdvised(String pickupCartageAdvised) {
            this.pickupCartageAdvised = pickupCartageAdvised;
        }

        public String getPickupCartageCompleted() {
            return pickupCartageCompleted;
        }

        public void setPickupCartageCompleted(String pickupCartageCompleted) {
            this.pickupCartageCompleted = pickupCartageCompleted;
        }

        public String getPickupRequiredBy() {
            return pickupRequiredBy;
        }

        public void setPickupRequiredBy(String pickupRequiredBy) {
            this.pickupRequiredBy = pickupRequiredBy;
        }

        public String getPrStringOptionForPackagesOnAWBCode() {
            return prStringOptionForPackagesOnAWBCode;
        }

        public void setPrStringOptionForPackagesOnAWBCode(String prStringOptionForPackagesOnAWBCode) {
            this.prStringOptionForPackagesOnAWBCode = prStringOptionForPackagesOnAWBCode;
        }

        public String getPrStringOptionForPackagesOnAWBDesc() {
            return prStringOptionForPackagesOnAWBDesc;
        }

        public void setPrStringOptionForPackagesOnAWBDesc(String prStringOptionForPackagesOnAWBDesc) {
            this.prStringOptionForPackagesOnAWBDesc = prStringOptionForPackagesOnAWBDesc;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public List<AdditionalServiceListBean> getAdditionalServiceList() {
            return additionalServiceList;
        }

        public void setAdditionalServiceList(List<AdditionalServiceListBean> additionalServiceList) {
            this.additionalServiceList = additionalServiceList;
        }

        public static class AdditionalServiceListBean {
            /**
             * booked : string
             * completed : string
             * contractorId : string
             * createTime : 2020-11-13T00:26:41.329Z
             * duration : string
             * id : string
             * locationId : string
             * reference : string
             * serviceCodeCode : string
             * serviceCodeDescription : string
             * serviceCount : 0
             * serviceNote : string
             * updateTime : 2020-11-13T00:26:41.329Z
             */

            private String booked;
            private String completed;
            private String contractorId;
            private String createTime;
            private String duration;
            private String id;
            private String locationId;
            private String reference;
            private String serviceCodeCode;
            private String serviceCodeDescription;
            private String serviceCount;
            private String serviceNote;
            private String updateTime;

            public String getBooked() {
                return booked;
            }

            public void setBooked(String booked) {
                this.booked = booked;
            }

            public String getCompleted() {
                return completed;
            }

            public void setCompleted(String completed) {
                this.completed = completed;
            }

            public String getContractorId() {
                return contractorId;
            }

            public void setContractorId(String contractorId) {
                this.contractorId = contractorId;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLocationId() {
                return locationId;
            }

            public void setLocationId(String locationId) {
                this.locationId = locationId;
            }

            public String getReference() {
                return reference;
            }

            public void setReference(String reference) {
                this.reference = reference;
            }

            public String getServiceCodeCode() {
                return serviceCodeCode;
            }

            public void setServiceCodeCode(String serviceCodeCode) {
                this.serviceCodeCode = serviceCodeCode;
            }

            public String getServiceCodeDescription() {
                return serviceCodeDescription;
            }

            public void setServiceCodeDescription(String serviceCodeDescription) {
                this.serviceCodeDescription = serviceCodeDescription;
            }

            public String getServiceCount() {
                return serviceCount;
            }

            public void setServiceCount(String serviceCount) {
                this.serviceCount = serviceCount;
            }

            public String getServiceNote() {
                return serviceNote;
            }

            public void setServiceNote(String serviceNote) {
                this.serviceNote = serviceNote;
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

