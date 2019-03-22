package com.rdelacruz.couponserviceapi.Domain;

import org.springframework.cache.annotation.CachePut;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Pageable;

@Repository
public interface CouponByCityRepository extends CassandraRepository<CouponByCity, String> {
    @CachePut("couponsByCity")
    Slice<CouponByCity> findAllByCityName(String cityName, Pageable pageable);

}
