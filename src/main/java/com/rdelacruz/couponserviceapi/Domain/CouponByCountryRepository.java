package com.rdelacruz.couponserviceapi.Domain;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponByCountryRepository extends CassandraRepository<CouponByCountry, String> {
    @Cacheable("couponsByCountry")
    Slice<CouponByCountry> findAllByCountryName(String countryName, Pageable pageable);

}
