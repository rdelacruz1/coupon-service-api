package com.rdelacruz.couponserviceapi.Domain;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Objects;

@Table ("coupons_by_country")
public class CouponByCountry {
    @PrimaryKeyColumn(
            name = "countryName",
            ordinal = 0,
            type = PrimaryKeyType.PARTITIONED,
            ordering = Ordering.DESCENDING)
    private String countryName;

    @PrimaryKeyColumn(name = "description", ordinal = 0, type = PrimaryKeyType.CLUSTERED,
                     ordering = Ordering.DESCENDING)
    private String description;

    @PrimaryKeyColumn(name = "businessName", ordinal = 0, type = PrimaryKeyType.CLUSTERED,
                     ordering = Ordering.DESCENDING)
    private String businessName;

    @PrimaryKeyColumn(name = "cityName", ordinal = 0, type = PrimaryKeyType.CLUSTERED,
            ordering = Ordering.DESCENDING)
    private String cityName;

    @PrimaryKeyColumn(name = "regionName", ordinal = 0, type = PrimaryKeyType.CLUSTERED,
            ordering = Ordering.DESCENDING)
    private String regionName;

    @PrimaryKeyColumn(name = "stateName", ordinal = 0, type = PrimaryKeyType.CLUSTERED,
            ordering = Ordering.DESCENDING)
    private String stateName;

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public CouponByCountry countryName(String countryName) {
        this.setCountryName(countryName);
        return this;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }


    public void setCouponDescription(String description) {
        this.description = description;
    }

    public CouponByCountry description(String description) {
        this.setCouponDescription(description);
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessName() {
        return businessName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CouponByCountry couponByCountry = (CouponByCountry) o;
        return Objects.equals(countryName, couponByCountry.countryName) &&
                Objects.equals(description, couponByCountry.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryName, description);
    }

    @Override
    public String toString() {
        return "CouponByState{" +
                "regionName=" + regionName +
                ", description='" + description + '\'' +
                ", cityName=" + cityName +
                ", stateName=" + stateName +
                ", countryName=" + countryName +
                ", businessName=" + businessName;
    }
}
