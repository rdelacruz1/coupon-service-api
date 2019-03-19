package com.rdelacruz.couponserviceapi.Domain;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponByCountryRepository extends CassandraRepository<CouponByCountry, String> {
    List<CouponByCountry> findAllByCountryName(String countryName);

}
