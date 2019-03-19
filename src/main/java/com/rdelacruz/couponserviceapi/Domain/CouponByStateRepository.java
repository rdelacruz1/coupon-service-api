package com.rdelacruz.couponserviceapi.Domain;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponByStateRepository extends CassandraRepository<CouponByState, String> {
    List<CouponByState> findAllByStateName(String stateName);

}
