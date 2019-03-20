package com.rdelacruz.couponserviceapi.Domain;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponByStateRepository extends CassandraRepository<CouponByState, String> {
    @Cacheable("couponsByState")
    Slice<CouponByState> findAllByStateName(String stateName, Pageable pageable);

}
