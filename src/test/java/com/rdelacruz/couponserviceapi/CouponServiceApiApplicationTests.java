package com.rdelacruz.couponserviceapi;

import com.rdelacruz.couponserviceapi.Controller.CouponController;
import com.rdelacruz.couponserviceapi.Domain.*;
import com.rdelacruz.couponserviceapi.Services.CouponService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.mockito.*;
import com.datastax.driver.core.utils.UUIDs;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(CouponController.class)
@ContextConfiguration(classes = CassandraConfiguration.class,
        initializers = EmbeddedCassandraInitializer.class)
@SpringBootTest
public class CouponServiceApiApplicationTests {
    private Business _business;
    private List<Business> _businessList;

    private Coupon _coupon;
    private List<Coupon> _couponList;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CouponController couponController;

    @Mock
    private CouponService couponService;

    @Before
    private void setup(){
        _business = new Business();
        _business.setBusinessName("testCompany");
        _business.setId(UUID.randomUUID());
        _businessList = new ArrayList<>();
        _businessList.add(_business);

        _coupon = new Coupon();
        _coupon.setCouponDescription("5% off discount");
        _coupon.setBusinessName("testCompany");
        _coupon.setCityName("San Jose");
        _coupon.setRegionName("West Bay");
        _coupon.setStateName("California");
        _coupon.setCountryName("US");
        _couponList.add(_coupon);

    }

    private void mockAllFieldsResponse() {
        when(couponService.findAllBusinesses()).thenReturn(_businessList);
        when(couponService.createCoupon(_coupon));
    }

    @Test
    public void contextLoads() {

    }

    //TODO: test for adding invalid business record (empty business_name or null business_name or null id)
    //TODO: test for adding invalid coupon record
    //TODO: test adding a coupon
    //TODO: test finding coupons by city
    //TODO: test finding coupons by region
    //TODO: test finding coupons by state
    //TODO: test fining coupons by country



}
