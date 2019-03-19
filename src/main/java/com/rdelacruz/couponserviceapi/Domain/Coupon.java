package com.rdelacruz.couponserviceapi.Domain;

import java.util.Objects;

public class Coupon {

    private String cityName;

    private String description;

    private String businessName;

    private String regionName;

    private String stateName;

    private String countryName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Coupon cityName(String cityName) {
        this.setCityName(cityName);
        return this;
    }

    public void setCouponDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Coupon description(String description) {
        this.setCouponDescription(description);
        return this;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coupon coupon = (Coupon) o;
        return Objects.equals(cityName, coupon.cityName) &&
                Objects.equals(description, coupon.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityName, description);
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "cityName=" + cityName +
                "regionName=" + regionName +
                "stateName=" + stateName +
                "countryName" + countryName +
                "businessName" + businessName +
                ", description='" + description + '\'' +
                '}';
    }
}
