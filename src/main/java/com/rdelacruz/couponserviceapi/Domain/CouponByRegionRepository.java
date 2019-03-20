package com.rdelacruz.couponserviceapi.Domain;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponByRegionRepository extends CassandraRepository<CouponByRegion, String> {
    @Cacheable("couponsByRegion")
    Slice<CouponByRegion> findAllByRegionName(String regionName, Pageable pageable);

}
