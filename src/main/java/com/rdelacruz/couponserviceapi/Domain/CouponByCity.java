package com.rdelacruz.couponserviceapi.Domain;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Objects;

@Table ("coupons_by_city")
public class CouponByCity {
    @PrimaryKeyColumn(
            name = "cityName",
            ordinal = 0,
            type = PrimaryKeyType.PARTITIONED,
            ordering = Ordering.DESCENDING)
    private String cityName;

    @Column
    private String description;

    @Column
    private String businessName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public CouponByCity cityName(String cityName) {
        this.setCityName(cityName);
        return this;
    }

    public void setCouponDescription(String description) {
        this.description = description;
    }

    public CouponByCity description(String description) {
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
        CouponByCity couponByCity = (CouponByCity) o;
        return Objects.equals(cityName, couponByCity.cityName) &&
                Objects.equals(description, couponByCity.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityName, description);
    }

    @Override
    public String toString() {
        return "CouponByCity{" +
                "cityName=" + cityName +
                ", description='" + description + '\'' +
                '}';
    }
}
