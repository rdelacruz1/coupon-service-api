package com.rdelacruz.couponserviceapi.Domain;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponByCityRepository extends CassandraRepository<CouponByCity, String> {
    List<CouponByCity> findAllByCityName(String cityName);

}
