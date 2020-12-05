package com.lwb.cargovoice.module.mvp.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class AIResponse {

    String recipient_id;
    List<Order> custom;
    List<ButtonItem> buttons;
    String text;

    public List<ButtonItem> getButtons() {
        return buttons;
    }

    public void setButtons(List<ButtonItem> buttons) {
        this.buttons = buttons;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRecipient_id() {
        return recipient_id;
    }

    public void setRecipient_id(String recipient_id) {
        this.recipient_id = recipient_id;
    }

    public List<Order> getCustom() {
        return custom;
    }

    public void setCustom(List<Order> custom) {
        this.custom = custom;
    }

    public static class ButtonItem {
        private String title;
        private String payload;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPayload() {
            return payload;
        }

        public void setPayload(String payload) {
            this.payload = payload;
        }
    }

    public static class Order implements Parcelable {

        /**
         * commodity_code : GEN
         * commodity_currency : CNY
         * commodity_description : WATER
         * commodity_gross_weight : 38
         * commodity_package_quantity : 18
         * commodity_package_unit : CAS
         * commodity_unit_price : 99
         * commodity_volume : 28
         * commodity_volume_unit : M3
         * commodity_weight_unit : G
         * consignee_organization_code : APPCOMLAX
         * consignee_organization_name : APPLE
         * consignor_organization_code : ENOCHESZX
         * consignor_organization_name : ENO (CHENGCHENG JUCHUANG TECHNOLOGY (BEIJING),LTD
         * container_mode : FCL
         * container_quantity : 1
         * container_type : 20gp
         * etd : 2020-10-20T00:00:00.000+08:00
         * incoterms : FOB
         * port_of_destination : 青岛港
         * port_of_departure : 上海港
         * requested_slot : additional_service_code
         * service_level : D2D
         * transport : SEA
         * FE_flag : true
         */

        private String commodity_code;              //货件类别（显示需转换为名称）
        private String commodity_currency;          //货币单位（显示需转换为名称）
        private String commodity_description;       //商品描述
        private String commodity_gross_weight;      //商品重量
        private String commodity_package_quantity;  //商品数量
        private String commodity_package_unit_code; //数量单位
        private String commodity_unit_price;        //货件单价
        private String commodity_volume;            //商品体积
        private String commodity_volume_unit_code;  //体积单位
        private String commodity_weight_unit_code;  //重量单位
        private String consignee_organization_code; //收货人代码
        private String consignee_organization_name; //收货人名称
        private String consignor_organization_code; //发货人代码
        private String consignor_organization_name; //发货人名称
        private String container_mode;              //装箱方式
        private String container_quantity;          //箱量
        private String container_type;              //箱型
        private String etd;                         //预计起运日期
        private String incoterms;                   //贸易条款
        private String port_of_destination;         //目的港
        private String port_of_departure;           //起运港
        private String requested_slot;
        private String service_level_code;          //服务级别
        private String transport;                   //运输方式
        private String FE_flag;

        //-----------------------------------------------后加的字段---------------------------------------------------------

        private boolean commodity_dangerous_chemical_flag;//危险品标识
        private String commodity_hs_code;                //HS编码
        private String commodity_point_temp;             //温度
        private String commodity_temp_unit;              //温度单位
        private String consignee_address_type;
        private String consignor_address_type;
        private String delivery_address_type;
        private String delivery_addressline1;   //收货地址行1
        private String delivery_addressline2;   //收货地址行2
        private String delivery_city;           //收货城市
        private String delivery_state;          //收货州省
        private String delivery_state_code;     //收货州省代码
        private String delivery_country;        //收货国家
        private String delivery_country_code;   //收货国家代码
        private String delivery_equipment_needed;   //收货服务标识
        private String delivery_organization_code;  //收货人代码
        private String delivery_organization_name;  //收货人名称 （公司名）
        private String delivery_contact;            //收货联系方式
        private String delivery_phone;              //收货联系电话
        private String delivery_postcode;           //收货邮编
        private String mark;                        //唛头
        private String notify_address_type;
        private String notify_organization_name;    //通知人名称
        private String pickup_address_type;
        private String pickup_addressline1;         //发货地址行1
        private String pickup_addressline2;         //发货地址行2
        private String pickup_city;                 //发货城市
        private String pickup_state;                //发货州省
        private String pickup_state_code;           //发货州省代码
        private String pickup_country;              //发货人国家
        private String pickup_country_code;         //发货人国家代码
        private String pickup_equipment_needed;     //发货服务标识
        private String pickup_organization_code;    //发货人代码
        private String pickup_organization_name;    //发货人名称 （公司名）
        private String pickup_phone;                //发货联系电话
        private String pickup_contact;              //发货联系方式
        private String pickup_postcode;             //发货邮编
        private String port_of_destination_code;    //目的港代码
        private String port_of_destination_country; //目的港所在国家
        private String port_of_departure_code;      //出发港代码
        private String port_of_departure_country;   //出发港所在国家

        public String getPickup_contact() {
            return pickup_contact;
        }

        public void setPickup_contact(String pickup_contact) {
            this.pickup_contact = pickup_contact;
        }

        public String getPickup_country() {
            return pickup_country;
        }

        public void setPickup_country(String pickup_country) {
            this.pickup_country = pickup_country;
        }

        public String getPickup_country_code() {
            return pickup_country_code;
        }

        public void setPickup_country_code(String pickup_country_code) {
            this.pickup_country_code = pickup_country_code;
        }

        public String getCommodity_code() {
            return commodity_code;
        }

        public void setCommodity_code(String commodity_code) {
            this.commodity_code = commodity_code;
        }

        public String getCommodity_currency() {
            return commodity_currency;
        }

        public void setCommodity_currency(String commodity_currency) {
            this.commodity_currency = commodity_currency;
        }

        public String getCommodity_description() {
            return commodity_description;
        }

        public void setCommodity_description(String commodity_description) {
            this.commodity_description = commodity_description;
        }

        public String getCommodity_gross_weight() {
            return commodity_gross_weight;
        }

        public void setCommodity_gross_weight(String commodity_gross_weight) {
            this.commodity_gross_weight = commodity_gross_weight;
        }

        public String getCommodity_package_quantity() {
            return commodity_package_quantity;
        }

        public void setCommodity_package_quantity(String commodity_package_quantity) {
            this.commodity_package_quantity = commodity_package_quantity;
        }

        public String getCommodity_package_unit_code() {
            return commodity_package_unit_code;
        }

        public void setCommodity_package_unit_code(String commodity_package_unit_code) {
            this.commodity_package_unit_code = commodity_package_unit_code;
        }

        public String getCommodity_unit_price() {
            return commodity_unit_price;
        }

        public void setCommodity_unit_price(String commodity_unit_price) {
            this.commodity_unit_price = commodity_unit_price;
        }

        public String getCommodity_volume() {
            return commodity_volume;
        }

        public void setCommodity_volume(String commodity_volume) {
            this.commodity_volume = commodity_volume;
        }

        public String getCommodity_volume_unit_code() {
            return commodity_volume_unit_code;
        }

        public void setCommodity_volume_unit_code(String commodity_volume_unit_code) {
            this.commodity_volume_unit_code = commodity_volume_unit_code;
        }

        public String getCommodity_weight_unit_code() {
            return commodity_weight_unit_code;
        }

        public void setCommodity_weight_unit_code(String commodity_weight_unit_code) {
            this.commodity_weight_unit_code = commodity_weight_unit_code;
        }

        public String getConsignee_organization_code() {
            return consignee_organization_code;
        }

        public void setConsignee_organization_code(String consignee_organization_code) {
            this.consignee_organization_code = consignee_organization_code;
        }

        public String getConsignee_organization_name() {
            return consignee_organization_name;
        }

        public void setConsignee_organization_name(String consignee_organization_name) {
            this.consignee_organization_name = consignee_organization_name;
        }

        public String getConsignor_organization_code() {
            return consignor_organization_code;
        }

        public void setConsignor_organization_code(String consignor_organization_code) {
            this.consignor_organization_code = consignor_organization_code;
        }

        public String getConsignor_organization_name() {
            return consignor_organization_name;
        }

        public void setConsignor_organization_name(String consignor_organization_name) {
            this.consignor_organization_name = consignor_organization_name;
        }

        public String getContainer_mode() {
            return container_mode;
        }

        public void setContainer_mode(String container_mode) {
            this.container_mode = container_mode;
        }

        public String getContainer_quantity() {
            return container_quantity;
        }

        public void setContainer_quantity(String container_quantity) {
            this.container_quantity = container_quantity;
        }

        public String getContainer_type() {
            return container_type;
        }

        public void setContainer_type(String container_type) {
            this.container_type = container_type;
        }

        public String getEtd() {
            return etd;
        }

        public void setEtd(String etd) {
            this.etd = etd;
        }

        public String getIncoterms() {
            return incoterms;
        }

        public void setIncoterms(String incoterms) {
            this.incoterms = incoterms;
        }

        public String getPort_of_destination() {
            return port_of_destination;
        }

        public void setPort_of_destination(String port_of_destination) {
            this.port_of_destination = port_of_destination;
        }

        public String getPort_of_departure() {
            return port_of_departure;
        }

        public void setPort_of_departure(String port_of_departure) {
            this.port_of_departure = port_of_departure;
        }

        public String getRequested_slot() {
            return requested_slot;
        }

        public void setRequested_slot(String requested_slot) {
            this.requested_slot = requested_slot;
        }

        public String getService_level_code() {
            return service_level_code;
        }

        public void setService_level_code(String service_level_code) {
            this.service_level_code = service_level_code;
        }

        public String getTransport() {
            return transport;
        }

        public void setTransport(String transport) {
            this.transport = transport;
        }

        public String getFE_flag() {
            return FE_flag;
        }

        public void setFE_flag(String FE_flag) {
            this.FE_flag = FE_flag;
        }

        public boolean isCommodity_dangerous_chemical_flag() {
            return commodity_dangerous_chemical_flag;
        }

        public void setCommodity_dangerous_chemical_flag(boolean commodity_dangerous_chemical_flag) {
            this.commodity_dangerous_chemical_flag = commodity_dangerous_chemical_flag;
        }

        public String getCommodity_hs_code() {
            return commodity_hs_code;
        }

        public void setCommodity_hs_code(String commodity_hs_code) {
            this.commodity_hs_code = commodity_hs_code;
        }

        public String getCommodity_point_temp() {
            return commodity_point_temp;
        }

        public void setCommodity_point_temp(String commodity_point_temp) {
            this.commodity_point_temp = commodity_point_temp;
        }

        public String getCommodity_temp_unit() {
            return commodity_temp_unit;
        }

        public void setCommodity_temp_unit(String commodity_temp_unit) {
            this.commodity_temp_unit = commodity_temp_unit;
        }

        public String getConsignee_address_type() {
            return consignee_address_type;
        }

        public void setConsignee_address_type(String consignee_address_type) {
            this.consignee_address_type = consignee_address_type;
        }

        public String getConsignor_address_type() {
            return consignor_address_type;
        }

        public void setConsignor_address_type(String consignor_address_type) {
            this.consignor_address_type = consignor_address_type;
        }

        public String getDelivery_address_type() {
            return delivery_address_type;
        }

        public void setDelivery_address_type(String delivery_address_type) {
            this.delivery_address_type = delivery_address_type;
        }

        public String getDelivery_addressline1() {
            return delivery_addressline1;
        }

        public void setDelivery_addressline1(String delivery_addressline1) {
            this.delivery_addressline1 = delivery_addressline1;
        }

        public String getDelivery_addressline2() {
            return delivery_addressline2;
        }

        public void setDelivery_addressline2(String delivery_addressline2) {
            this.delivery_addressline2 = delivery_addressline2;
        }

        public String getDelivery_city() {
            return delivery_city;
        }

        public void setDelivery_city(String delivery_city) {
            this.delivery_city = delivery_city;
        }

        public String getDelivery_state() {
            return delivery_state;
        }

        public void setDelivery_state(String delivery_state) {
            this.delivery_state = delivery_state;
        }

        public String getDelivery_state_code() {
            return delivery_state_code;
        }

        public void setDelivery_state_code(String delivery_state_code) {
            this.delivery_state_code = delivery_state_code;
        }

        public String getDelivery_country() {
            return delivery_country;
        }

        public void setDelivery_country(String delivery_country) {
            this.delivery_country = delivery_country;
        }

        public String getDelivery_country_code() {
            return delivery_country_code;
        }

        public void setDelivery_country_code(String delivery_country_code) {
            this.delivery_country_code = delivery_country_code;
        }

        public String getDelivery_equipment_needed() {
            return delivery_equipment_needed;
        }

        public void setDelivery_equipment_needed(String delivery_equipment_needed) {
            this.delivery_equipment_needed = delivery_equipment_needed;
        }

        public String getDelivery_organization_code() {
            return delivery_organization_code;
        }

        public void setDelivery_organization_code(String delivery_organization_code) {
            this.delivery_organization_code = delivery_organization_code;
        }

        public String getDelivery_organization_name() {
            return delivery_organization_name;
        }

        public void setDelivery_organization_name(String delivery_organization_name) {
            this.delivery_organization_name = delivery_organization_name;
        }

        public String getDelivery_contact() {
            return delivery_contact;
        }

        public void setDelivery_contact(String delivery_contact) {
            this.delivery_contact = delivery_contact;
        }

        public String getDelivery_phone() {
            return delivery_phone;
        }

        public void setDelivery_phone(String delivery_phone) {
            this.delivery_phone = delivery_phone;
        }

        public String getDelivery_postcode() {
            return delivery_postcode;
        }

        public void setDelivery_postcode(String delivery_postcode) {
            this.delivery_postcode = delivery_postcode;
        }

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }

        public String getNotify_address_type() {
            return notify_address_type;
        }

        public void setNotify_address_type(String notify_address_type) {
            this.notify_address_type = notify_address_type;
        }

        public String getNotify_organization_name() {
            return notify_organization_name;
        }

        public void setNotify_organization_name(String notify_organization_name) {
            this.notify_organization_name = notify_organization_name;
        }

        public String getPickup_address_type() {
            return pickup_address_type;
        }

        public void setPickup_address_type(String pickup_address_type) {
            this.pickup_address_type = pickup_address_type;
        }

        public String getPickup_addressline1() {
            return pickup_addressline1;
        }

        public void setPickup_addressline1(String pickup_addressline1) {
            this.pickup_addressline1 = pickup_addressline1;
        }

        public String getPickup_addressline2() {
            return pickup_addressline2;
        }

        public void setPickup_addressline2(String pickup_addressline2) {
            this.pickup_addressline2 = pickup_addressline2;
        }

        public String getPickup_city() {
            return pickup_city;
        }

        public void setPickup_city(String pickup_city) {
            this.pickup_city = pickup_city;
        }

        public String getPickup_state() {
            return pickup_state;
        }

        public void setPickup_state(String pickup_state) {
            this.pickup_state = pickup_state;
        }

        public String getPickup_state_code() {
            return pickup_state_code;
        }

        public void setPickup_state_code(String pickup_state_code) {
            this.pickup_state_code = pickup_state_code;
        }

        public String getPickup_equipment_needed() {
            return pickup_equipment_needed;
        }

        public void setPickup_equipment_needed(String pickup_equipment_needed) {
            this.pickup_equipment_needed = pickup_equipment_needed;
        }

        public String getPickup_organization_code() {
            return pickup_organization_code;
        }

        public void setPickup_organization_code(String pickup_organization_code) {
            this.pickup_organization_code = pickup_organization_code;
        }

        public String getPickup_organization_name() {
            return pickup_organization_name;
        }

        public void setPickup_organization_name(String pickup_organization_name) {
            this.pickup_organization_name = pickup_organization_name;
        }

        public String getPickup_phone() {
            return pickup_phone;
        }

        public void setPickup_phone(String pickup_phone) {
            this.pickup_phone = pickup_phone;
        }

        public String getPickup_postcode() {
            return pickup_postcode;
        }

        public void setPickup_postcode(String pickup_postcode) {
            this.pickup_postcode = pickup_postcode;
        }

        public String getPort_of_destination_code() {
            return port_of_destination_code;
        }

        public void setPort_of_destination_code(String port_of_destination_code) {
            this.port_of_destination_code = port_of_destination_code;
        }

        public String getPort_of_destination_country() {
            return port_of_destination_country;
        }

        public void setPort_of_destination_country(String port_of_destination_country) {
            this.port_of_destination_country = port_of_destination_country;
        }

        public String getPort_of_departure_code() {
            return port_of_departure_code;
        }

        public void setPort_of_departure_code(String port_of_departure_code) {
            this.port_of_departure_code = port_of_departure_code;
        }

        public String getPort_of_departure_country() {
            return port_of_departure_country;
        }

        public void setPort_of_departure_country(String port_of_departure_country) {
            this.port_of_departure_country = port_of_departure_country;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.commodity_code);
            dest.writeString(this.commodity_currency);
            dest.writeString(this.commodity_description);
            dest.writeString(this.commodity_gross_weight);
            dest.writeString(this.commodity_package_quantity);
            dest.writeString(this.commodity_package_unit_code);
            dest.writeString(this.commodity_unit_price);
            dest.writeString(this.commodity_volume);
            dest.writeString(this.commodity_volume_unit_code);
            dest.writeString(this.commodity_weight_unit_code);
            dest.writeString(this.consignee_organization_code);
            dest.writeString(this.consignee_organization_name);
            dest.writeString(this.consignor_organization_code);
            dest.writeString(this.consignor_organization_name);
            dest.writeString(this.container_mode);
            dest.writeString(this.container_quantity);
            dest.writeString(this.container_type);
            dest.writeString(this.etd);
            dest.writeString(this.incoterms);
            dest.writeString(this.port_of_destination);
            dest.writeString(this.port_of_departure);
            dest.writeString(this.requested_slot);
            dest.writeString(this.service_level_code);
            dest.writeString(this.transport);
            dest.writeString(this.FE_flag);
            dest.writeByte(this.commodity_dangerous_chemical_flag ? (byte) 1 : (byte) 0);
            dest.writeString(this.commodity_hs_code);
            dest.writeString(this.commodity_point_temp);
            dest.writeString(this.commodity_temp_unit);
            dest.writeString(this.consignee_address_type);
            dest.writeString(this.consignor_address_type);
            dest.writeString(this.delivery_address_type);
            dest.writeString(this.delivery_addressline1);
            dest.writeString(this.delivery_addressline2);
            dest.writeString(this.delivery_city);
            dest.writeString(this.delivery_state);
            dest.writeString(this.delivery_state_code);
            dest.writeString(this.delivery_country);
            dest.writeString(this.delivery_country_code);
            dest.writeString(this.delivery_equipment_needed);
            dest.writeString(this.delivery_organization_code);
            dest.writeString(this.delivery_organization_name);
            dest.writeString(this.delivery_contact);
            dest.writeString(this.delivery_phone);
            dest.writeString(this.delivery_postcode);
            dest.writeString(this.mark);
            dest.writeString(this.notify_address_type);
            dest.writeString(this.notify_organization_name);
            dest.writeString(this.pickup_address_type);
            dest.writeString(this.pickup_addressline1);
            dest.writeString(this.pickup_addressline2);
            dest.writeString(this.pickup_city);
            dest.writeString(this.pickup_state);
            dest.writeString(this.pickup_state_code);
            dest.writeString(this.pickup_country);
            dest.writeString(this.pickup_country_code);
            dest.writeString(this.pickup_equipment_needed);
            dest.writeString(this.pickup_organization_code);
            dest.writeString(this.pickup_organization_name);
            dest.writeString(this.pickup_phone);
            dest.writeString(this.pickup_contact);
            dest.writeString(this.pickup_postcode);
            dest.writeString(this.port_of_destination_code);
            dest.writeString(this.port_of_destination_country);
            dest.writeString(this.port_of_departure_code);
            dest.writeString(this.port_of_departure_country);
        }

        public Order() {
        }

        protected Order(Parcel in) {
            this.commodity_code = in.readString();
            this.commodity_currency = in.readString();
            this.commodity_description = in.readString();
            this.commodity_gross_weight = in.readString();
            this.commodity_package_quantity = in.readString();
            this.commodity_package_unit_code = in.readString();
            this.commodity_unit_price = in.readString();
            this.commodity_volume = in.readString();
            this.commodity_volume_unit_code = in.readString();
            this.commodity_weight_unit_code = in.readString();
            this.consignee_organization_code = in.readString();
            this.consignee_organization_name = in.readString();
            this.consignor_organization_code = in.readString();
            this.consignor_organization_name = in.readString();
            this.container_mode = in.readString();
            this.container_quantity = in.readString();
            this.container_type = in.readString();
            this.etd = in.readString();
            this.incoterms = in.readString();
            this.port_of_destination = in.readString();
            this.port_of_departure = in.readString();
            this.requested_slot = in.readString();
            this.service_level_code = in.readString();
            this.transport = in.readString();
            this.FE_flag = in.readString();
            this.commodity_dangerous_chemical_flag = in.readByte() != 0;
            this.commodity_hs_code = in.readString();
            this.commodity_point_temp = in.readString();
            this.commodity_temp_unit = in.readString();
            this.consignee_address_type = in.readString();
            this.consignor_address_type = in.readString();
            this.delivery_address_type = in.readString();
            this.delivery_addressline1 = in.readString();
            this.delivery_addressline2 = in.readString();
            this.delivery_city = in.readString();
            this.delivery_state = in.readString();
            this.delivery_state_code = in.readString();
            this.delivery_country = in.readString();
            this.delivery_country_code = in.readString();
            this.delivery_equipment_needed = in.readString();
            this.delivery_organization_code = in.readString();
            this.delivery_organization_name = in.readString();
            this.delivery_contact = in.readString();
            this.delivery_phone = in.readString();
            this.delivery_postcode = in.readString();
            this.mark = in.readString();
            this.notify_address_type = in.readString();
            this.notify_organization_name = in.readString();
            this.pickup_address_type = in.readString();
            this.pickup_addressline1 = in.readString();
            this.pickup_addressline2 = in.readString();
            this.pickup_city = in.readString();
            this.pickup_state = in.readString();
            this.pickup_state_code = in.readString();
            this.pickup_country = in.readString();
            this.pickup_country_code = in.readString();
            this.pickup_equipment_needed = in.readString();
            this.pickup_organization_code = in.readString();
            this.pickup_organization_name = in.readString();
            this.pickup_phone = in.readString();
            this.pickup_contact = in.readString();
            this.pickup_postcode = in.readString();
            this.port_of_destination_code = in.readString();
            this.port_of_destination_country = in.readString();
            this.port_of_departure_code = in.readString();
            this.port_of_departure_country = in.readString();
        }

        public static final Parcelable.Creator<Order> CREATOR = new Parcelable.Creator<Order>() {
            @Override
            public Order createFromParcel(Parcel source) {
                return new Order(source);
            }

            @Override
            public Order[] newArray(int size) {
                return new Order[size];
            }
        };
    }

}
