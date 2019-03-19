package com.rdelacruz.couponserviceapi.Domain;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Objects;
import java.util.UUID;

@Table
public class Business {
    @PrimaryKeyColumn(
            name = "id",
            ordinal = 0,
            type = PrimaryKeyType.PARTITIONED,
            ordering = Ordering.DESCENDING)
    private UUID id;
    @Column
    private String name;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Business id(UUID id) {
        this.setId(id);
        return this;
    }

    public String getName() {
        return name;
    }

    public void setBusinessName(String name) {
        this.name = name;
    }

    public Business name(String name) {
        this.setBusinessName(name);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Business business = (Business) o;
        return Objects.equals(id, business.id) &&
                Objects.equals(name, business.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Business{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
