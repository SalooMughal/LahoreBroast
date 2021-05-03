package com.lb.lahorebroast.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgetPasswordResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public class Data {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("business_id")
        @Expose
        private Integer businessId;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("supplier_business_name")
        @Expose
        private Object supplierBusinessName;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("contact_id")
        @Expose
        private String contactId;
        @SerializedName("contact_status")
        @Expose
        private String contactStatus;
        @SerializedName("tax_number")
        @Expose
        private Object taxNumber;
        @SerializedName("city")
        @Expose
        private Object city;
        @SerializedName("state")
        @Expose
        private Object state;
        @SerializedName("country")
        @Expose
        private Object country;
        @SerializedName("landmark")
        @Expose
        private Object landmark;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("landline")
        @Expose
        private Object landline;
        @SerializedName("alternate_number")
        @Expose
        private Object alternateNumber;
        @SerializedName("pay_term_number")
        @Expose
        private Object payTermNumber;
        @SerializedName("pay_term_type")
        @Expose
        private Object payTermType;
        @SerializedName("credit_limit")
        @Expose
        private Object creditLimit;
        @SerializedName("created_by")
        @Expose
        private Integer createdBy;
        @SerializedName("total_rp")
        @Expose
        private Integer totalRp;
        @SerializedName("total_rp_used")
        @Expose
        private Integer totalRpUsed;
        @SerializedName("total_rp_expired")
        @Expose
        private Integer totalRpExpired;
        @SerializedName("is_default")
        @Expose
        private Integer isDefault;
        @SerializedName("shipping_address")
        @Expose
        private Object shippingAddress;
        @SerializedName("position")
        @Expose
        private Object position;
        @SerializedName("customer_group_id")
        @Expose
        private Integer customerGroupId;
        @SerializedName("custom_field1")
        @Expose
        private String customField1;
        @SerializedName("custom_field2")
        @Expose
        private Object customField2;
        @SerializedName("custom_field3")
        @Expose
        private Object customField3;
        @SerializedName("custom_field4")
        @Expose
        private Object customField4;
        @SerializedName("deleted_at")
        @Expose
        private Object deletedAt;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getBusinessId() {
            return businessId;
        }

        public void setBusinessId(Integer businessId) {
            this.businessId = businessId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getSupplierBusinessName() {
            return supplierBusinessName;
        }

        public void setSupplierBusinessName(Object supplierBusinessName) {
            this.supplierBusinessName = supplierBusinessName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getContactId() {
            return contactId;
        }

        public void setContactId(String contactId) {
            this.contactId = contactId;
        }

        public String getContactStatus() {
            return contactStatus;
        }

        public void setContactStatus(String contactStatus) {
            this.contactStatus = contactStatus;
        }

        public Object getTaxNumber() {
            return taxNumber;
        }

        public void setTaxNumber(Object taxNumber) {
            this.taxNumber = taxNumber;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public Object getState() {
            return state;
        }

        public void setState(Object state) {
            this.state = state;
        }

        public Object getCountry() {
            return country;
        }

        public void setCountry(Object country) {
            this.country = country;
        }

        public Object getLandmark() {
            return landmark;
        }

        public void setLandmark(Object landmark) {
            this.landmark = landmark;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Object getLandline() {
            return landline;
        }

        public void setLandline(Object landline) {
            this.landline = landline;
        }

        public Object getAlternateNumber() {
            return alternateNumber;
        }

        public void setAlternateNumber(Object alternateNumber) {
            this.alternateNumber = alternateNumber;
        }

        public Object getPayTermNumber() {
            return payTermNumber;
        }

        public void setPayTermNumber(Object payTermNumber) {
            this.payTermNumber = payTermNumber;
        }

        public Object getPayTermType() {
            return payTermType;
        }

        public void setPayTermType(Object payTermType) {
            this.payTermType = payTermType;
        }

        public Object getCreditLimit() {
            return creditLimit;
        }

        public void setCreditLimit(Object creditLimit) {
            this.creditLimit = creditLimit;
        }

        public Integer getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(Integer createdBy) {
            this.createdBy = createdBy;
        }

        public Integer getTotalRp() {
            return totalRp;
        }

        public void setTotalRp(Integer totalRp) {
            this.totalRp = totalRp;
        }

        public Integer getTotalRpUsed() {
            return totalRpUsed;
        }

        public void setTotalRpUsed(Integer totalRpUsed) {
            this.totalRpUsed = totalRpUsed;
        }

        public Integer getTotalRpExpired() {
            return totalRpExpired;
        }

        public void setTotalRpExpired(Integer totalRpExpired) {
            this.totalRpExpired = totalRpExpired;
        }

        public Integer getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(Integer isDefault) {
            this.isDefault = isDefault;
        }

        public Object getShippingAddress() {
            return shippingAddress;
        }

        public void setShippingAddress(Object shippingAddress) {
            this.shippingAddress = shippingAddress;
        }

        public Object getPosition() {
            return position;
        }

        public void setPosition(Object position) {
            this.position = position;
        }

        public Integer getCustomerGroupId() {
            return customerGroupId;
        }

        public void setCustomerGroupId(Integer customerGroupId) {
            this.customerGroupId = customerGroupId;
        }

        public String getCustomField1() {
            return customField1;
        }

        public void setCustomField1(String customField1) {
            this.customField1 = customField1;
        }

        public Object getCustomField2() {
            return customField2;
        }

        public void setCustomField2(Object customField2) {
            this.customField2 = customField2;
        }

        public Object getCustomField3() {
            return customField3;
        }

        public void setCustomField3(Object customField3) {
            this.customField3 = customField3;
        }

        public Object getCustomField4() {
            return customField4;
        }

        public void setCustomField4(Object customField4) {
            this.customField4 = customField4;
        }

        public Object getDeletedAt() {
            return deletedAt;
        }

        public void setDeletedAt(Object deletedAt) {
            this.deletedAt = deletedAt;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }
    }
}
