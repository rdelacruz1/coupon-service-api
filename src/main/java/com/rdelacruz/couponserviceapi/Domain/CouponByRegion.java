package com.rdelacruz.couponserviceapi.Domain;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Objects;

@Table ("coupons_by_region")
public class CouponByRegion {
    @PrimaryKeyColumn(
            name = "regionName",
            ordinal = 0,
            type = PrimaryKeyType.PARTITIONED,
            ordering = Ordering.DESCENDING)
    private String regionName;

    @Column
    private String description;

    @Column
    private String businessName;

    @Column
    private String cityName;

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public CouponByRegion regionName(String regionName) {
        this.setRegionName(regionName);
        return this;
    }

    public String getRegionName() {
        return regionName;
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

    public CouponByRegion description(String description) {
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
        CouponByRegion couponByRegion = (CouponByRegion) o;
        return Objects.equals(regionName, couponByRegion.regionName) &&
                Objects.equals(description, couponByRegion.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regionName, description);
    }

    @Override
    public String toString() {
        return "CouponByRegion{" +
                "regionName=" + regionName +
                ", description='" + description + '\'' +
                ", cityName=" + cityName +
                ", businessName=" + businessName;
    }
}
