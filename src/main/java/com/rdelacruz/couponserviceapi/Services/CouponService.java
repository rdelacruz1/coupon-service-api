package com.rdelacruz.couponserviceapi.Services;

import com.datastax.driver.core.utils.UUIDs;
import com.rdelacruz.couponserviceapi.Domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CouponService {
    private final BusinessRepository businessRepository;
    private final CouponByCityRepository couponByCityRepository;
    private final CouponByRegionRepository couponByRegionRepository;
    private final CouponByStateRepository couponByStateRepository;
    private final CouponByCountryRepository couponByCountryRepository;

    @Autowired
    public CouponService(BusinessRepository businessRepository, CouponByCityRepository couponByCityRepository,
            CouponByRegionRepository couponByRegionRepository, CouponByStateRepository couponByStateRepository,
            CouponByCountryRepository couponByCountryRepository) {
        this.businessRepository = businessRepository;
        this.couponByCityRepository = couponByCityRepository;
        this.couponByRegionRepository = couponByRegionRepository;
        this.couponByStateRepository = couponByStateRepository;
        this.couponByCountryRepository = couponByCountryRepository;
    }

    public Slice<CouponByCity> findAllCouponsByCityName(String cityName, int pageIndex, int resultsOnPage) {
        return couponByCityRepository.findAllByCityName(cityName, CassandraPageRequest.of(pageIndex, resultsOnPage));
    }

    public Slice<CouponByState> findAllCouponsByStateName(String stateName, int pageIndex, int resultsOnPage) {
        return couponByStateRepository.findAllByStateName(stateName, CassandraPageRequest.of(pageIndex, resultsOnPage));
    }

    public Slice<CouponByRegion> findAllCouponsByRegionName(String regionName, int pageIndex, int resultsOnPage) {
        return couponByRegionRepository.findAllByRegionName(regionName, CassandraPageRequest.of(pageIndex, resultsOnPage));
    }

    public Slice<CouponByCountry> findAllCouponsByCountryName(String countryName, int pageIndex, int resultsOnPage) {
        return couponByCountryRepository.findAllByCountryName(countryName, CassandraPageRequest.of(pageIndex, resultsOnPage));
    }

    public List<Business> findAllBusinesses() {
        return businessRepository.findAll();
    }

    public List<CouponByCountry> findAllCoupons() {
        return couponByCountryRepository.findAll();
    }

    public Business createBusiness(Business business) {
        if (null == business.getName() || "".equalsIgnoreCase(business.getName())) {
            throw new IllegalArgumentException("Business record is invalid.");
        }
        business.setId(UUIDs.timeBased());
        return businessRepository.insert(business);
    }

    public Coupon createCoupon(Coupon coupon) {
        if ("".equalsIgnoreCase(coupon.getCityName()) || "".equalsIgnoreCase(coupon.getRegionName())
        || "".equalsIgnoreCase(coupon.getStateName()) || "".equalsIgnoreCase(coupon.getCountryName())) {
            throw new IllegalArgumentException("coupon record is invalid.");
        }
        // constructing couponByCity from coupon
        CouponByCity couponByCity = new CouponByCity();
        couponByCity.setCouponDescription(coupon.getDescription());
        couponByCity.setBusinessName(coupon.getBusinessName());
        couponByCity.setCityName(coupon.getCityName());

        // constructing couponByRegion from coupon
        CouponByRegion couponByRegion = new CouponByRegion();
        couponByRegion.setCouponDescription(coupon.getDescription());
        couponByRegion.setBusinessName(coupon.getBusinessName());
        couponByRegion.setCityName(coupon.getCityName());
        couponByRegion.setRegionName(coupon.getRegionName());

        // constructing couponByState from coupon
        CouponByState couponByState = new CouponByState();
        couponByState.setCouponDescription(coupon.getDescription());
        couponByState.setBusinessName(coupon.getBusinessName());
        couponByState.setCityName(coupon.getCityName());
        couponByState.setRegionName(coupon.getRegionName());
        couponByState.setStateName(coupon.getStateName());

        // constructing couponByCountry from coupon
        CouponByCountry couponByCountry = new CouponByCountry();
        couponByCountry.setBusinessName(coupon.getBusinessName());
        couponByCountry.setCityName(coupon.getCityName());
        couponByCountry.setCouponDescription(coupon.getDescription());
        couponByCountry.setRegionName(coupon.getRegionName());
        couponByCountry.setStateName(coupon.getStateName());
        couponByCountry.setCountryName(coupon.getCountryName());

        couponByCityRepository.insert(couponByCity);
        couponByRegionRepository.insert(couponByRegion);
        couponByStateRepository.insert(couponByState);
        couponByCountryRepository.insert(couponByCountry);

        return coupon;
    }

    public Optional<Business> findByName(String name) {
        return businessRepository.findByName(name);
    }

    public Optional<Business> findById(UUID id) {
        return businessRepository.findById(id);
    }

}