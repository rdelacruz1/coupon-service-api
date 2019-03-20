package com.rdelacruz.couponserviceapi.Controller;

import com.rdelacruz.couponserviceapi.Domain.*;
import com.rdelacruz.couponserviceapi.Services.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path="/demo")
public class CouponController {
    private final CouponService couponService;

    @Autowired
    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }


    @PostMapping(path = "/addBusiness", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Business> create(@RequestBody Business business) {
        try {
            return ResponseEntity.ok(couponService.createBusiness(business));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(path = "/issueCoupon", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Coupon> create(@RequestBody Coupon coupon) {
        try {
            return ResponseEntity.ok(couponService.createCoupon(coupon));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(path="/findCouponsByCity")
    public @ResponseBody ResponseEntity<Iterable<CouponByCity>> findCouponsByCity(@RequestParam String cityName) {
        try {
            return ResponseEntity.ok(couponService.findAllCouponsByCityName(cityName, 0, 10));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(path="/findCouponsByState")
    public @ResponseBody ResponseEntity<Iterable<CouponByState>> findCouponsByState(@RequestParam String stateName) {
        try {
            return ResponseEntity.ok(couponService.findAllCouponsByStateName(stateName, 0,10));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(path="/findCouponsByRegion")
    public @ResponseBody ResponseEntity<Iterable<CouponByRegion>> findCouponsByRegion(@RequestParam String regionName) {
        try {
            return ResponseEntity.ok(couponService.findAllCouponsByRegionName(regionName, 0,10));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(path="/findCouponsByCountry")
    public @ResponseBody ResponseEntity<Iterable<CouponByCountry>> findCouponsByCountry(@RequestParam String countryName) {
        try {
            return ResponseEntity.ok(couponService.findAllCouponsByCountryName(countryName, 0,10));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(path="/getAllBusinesses")
    public @ResponseBody Iterable<Business> getAllBusinesses() {
        return couponService.findAllBusinesses();
    }

    @GetMapping(path="/getAllCoupons")
    public @ResponseBody Iterable<CouponByCountry> getAllCoupons() {
        return couponService.findAllCoupons();
    }

}