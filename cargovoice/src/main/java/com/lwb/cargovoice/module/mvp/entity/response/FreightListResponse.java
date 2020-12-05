package com.lwb.cargovoice.module.mvp.entity.response;


import java.util.List;

public class FreightListResponse {

    /**
     * additionalReferenceList : [{"createTime":"2020-11-01T08:36:23.333Z","id":"string","referenceNumber":"string","typeCode":"string","typeDesc":"string","updateTime":"2020-11-01T08:36:23.333Z"}]
     * containerList : [{"arrivalCartageComplete":"string","containerCount":0,"containerNumber":"string","containerTypeCode":"string","containerTypeDesc":"string","createTime":"2020-11-01T08:36:23.333Z","departureEstimatedPickup":"string","id":"string","updateTime":"2020-11-01T08:36:23.333Z"}]
     * containerModeCode : string
     * containerModeDesc : string
     * createTime : 2020-11-01T08:36:23.333Z
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
     * milestoneList : [{"actualDate":"string","createTime":"2020-11-01T08:36:23.333Z","description":"string","estimatedDate":"string","id":"string","updateTime":"2020-11-01T08:36:23.333Z"}]
     * neutralMaster : true
     * noteList : [{"createTime":"2020-11-01T08:36:23.333Z","customDescription":true,"description":"string","id":"string","noteText":"string","updateTime":"2020-11-01T08:36:23.333Z"}]
     * orderList : [{"createTime":"2020-11-01T08:36:23.333Z","id":"string","orderDateTime":"string","orderNumber":"string","statusCode":"string","statusDesc":"string","updateTime":"2020-11-01T08:36:23.333Z"}]
     * outerPacks : 0
     * outerPacksPackageTypeCode : string
     * outerPacksPackageTypeDesc : string
     * ownerRef : string
     * packingLineList : [{"containerNumber":"string","createTime":"2020-11-01T08:36:23.333Z","goodsDesc":"string","id":"string","packQty":0,"packTypeCode":"string","packTypeDesc":"string","updateTime":"2020-11-01T08:36:23.333Z"}]
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
     * transportLegList : [{"actualArrival":"string","actualDeparture":"string","createTime":"2020-11-01T08:36:23.333Z","id":"string","legType":"string","transportMode":"string","updateTime":"2020-11-01T08:36:23.333Z","vesselName":"string","voyageFlightNo":"string"}]
     * transportModeCode : string
     * transportModeDesc : string
     * updateTime : 2020-11-01T08:36:23.333Z
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
    private List<AdditionalReferenceListBean> additionalReferenceList;
    private List<ContainerListBean> containerList;
    private List<MilestoneListBean> milestoneList;
    private List<NoteListBean> noteList;
    private List<OrderListBean> orderList;
    private List<PackingLineListBean> packingLineList;
    private List<TransportLegListBean> transportLegList;

    public String getLastestMilestoneDesc() {
        return lastestMilestoneDesc;
    }

    public void setLastestMilestoneDesc(String lastestMilestoneDesc) {
        this.lastestMilestoneDesc = lastestMilestoneDesc;
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
         * createTime : 2020-11-01T08:36:23.333Z
         * id : string
         * referenceNumber : string
         * typeCode : string
         * typeDesc : string
         * updateTime : 2020-11-01T08:36:23.333Z
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
         * createTime : 2020-11-01T08:36:23.333Z
         * departureEstimatedPickup : string
         * id : string
         * updateTime : 2020-11-01T08:36:23.333Z
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
         * createTime : 2020-11-01T08:36:23.333Z
         * description : string
         * estimatedDate : string
         * id : string
         * updateTime : 2020-11-01T08:36:23.333Z
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
         * createTime : 2020-11-01T08:36:23.333Z
         * customDescription : true
         * description : string
         * id : string
         * noteText : string
         * updateTime : 2020-11-01T08:36:23.333Z
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
         * createTime : 2020-11-01T08:36:23.333Z
         * id : string
         * orderDateTime : string
         * orderNumber : string
         * statusCode : string
         * statusDesc : string
         * updateTime : 2020-11-01T08:36:23.333Z
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
         * createTime : 2020-11-01T08:36:23.333Z
         * goodsDesc : string
         * id : string
         * packQty : 0
         * packTypeCode : string
         * packTypeDesc : string
         * updateTime : 2020-11-01T08:36:23.333Z
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
         * createTime : 2020-11-01T08:36:23.333Z
         * id : string
         * legType : string
         * transportMode : string
         * updateTime : 2020-11-01T08:36:23.333Z
         * vesselName : string
         * voyageFlightNo : string
         */

        private String actualArrival;
        private String actualDeparture;
        private String createTime;
        private String id;
        private String legType;
        private String transportMode;
        private String updateTime;
        private String vesselName;
        private String voyageFlightNo;

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
    }
}

