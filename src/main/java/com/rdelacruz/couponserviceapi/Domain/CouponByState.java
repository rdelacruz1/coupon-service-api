package com.rdelacruz.couponserviceapi.Domain;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Objects;

@Table ("coupons_by_state")
public class CouponByState {
    @PrimaryKeyColumn(
            name = "stateName",
            ordinal = 0,
            type = PrimaryKeyType.PARTITIONED,
            ordering = Ordering.DESCENDING)
    private String stateName;

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

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public CouponByState stateName(String stateName) {
        this.setStateName(stateName);
        return this;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
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

    public CouponByState description(String description) {
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
        CouponByState couponByState = (CouponByState) o;
        return Objects.equals(stateName, couponByState.stateName) &&
                Objects.equals(description, couponByState.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stateName, description);
    }

    @Override
    public String toString() {
        return "CouponByState{" +
                "regionName=" + regionName +
                ", description='" + description + '\'' +
                ", cityName=" + cityName +
                ", stateName" + stateName +
                ", businessName=" + businessName;
    }
}
