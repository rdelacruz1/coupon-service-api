package com.rdelacruz.couponserviceapi;

import com.rdelacruz.couponserviceapi.Domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CassandraConfiguration.class,
        initializers = EmbeddedCassandraInitializer.class)
@SpringBootTest
public class CouponServiceApiApplicationTests {
    private Business _business;
    private UUID _uuid;
    private List<Business> _businessList;

    private Coupon _coupon;

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private CouponByCityRepository couponByCityRepository;

    @Autowired
    private CouponByRegionRepository couponByRegionRepository;

    @Autowired
    private CouponByStateRepository couponByStateRepository;

    @Autowired
    private CouponByCountryRepository couponByCountryRepository;

    @Before
    public void setup(){
        _business = new Business();
        _businessList = new ArrayList<>();
        _business.setBusinessName("testCompany");
        _uuid = UUID.randomUUID();
        _business.setId(_uuid);
        _businessList.add(_business);

        _coupon = new Coupon();
        _coupon.setCouponDescription("5% off discount");
        _coupon.setBusinessName("testCompany");
        _coupon.setCityName("San Jose");
        _coupon.setRegionName("West Bay");
        _coupon.setStateName("California");
        _coupon.setCountryName("US");
    }

    @Test
    public void testOperations() {
        // add business test
        {
            Business business = businessRepository.insert(_business);
            Assert.assertEquals("testCompany", business.getName());
            Assert.assertEquals(_uuid, business.getId());
        }

        // find coupons by city test1
        {
            Slice<CouponByCity> coupons = couponByCityRepository.findAllByCityName("San Jose",
                    CassandraPageRequest.of(0,10));
            Assert.assertEquals(10, coupons.getSize());
            List<CouponByCity> couponsContent = coupons.getContent();
            Assert.assertEquals(2, couponsContent.size());
            Assert.assertEquals("Airbnb", couponsContent.get(0).getBusinessName());
        }

        // find coupons by city test2
        {
            Slice<CouponByCity> coupons = couponByCityRepository.findAllByCityName("Oakland",
                    CassandraPageRequest.of(0,10));
            List<CouponByCity> couponsContent = coupons.getContent();
            Assert.assertEquals(1, couponsContent.size());
            Assert.assertEquals("Uber", couponsContent.get(0).getBusinessName());
        }

        // find coupons by city test3
        {
            Slice<CouponByCity> coupons = couponByCityRepository.findAllByCityName("Cupertino",
                    CassandraPageRequest.of(0,10));
            List<CouponByCity> couponsContent = coupons.getContent();
            Assert.assertEquals(1, couponsContent.size());
            Assert.assertEquals("Apple", couponsContent.get(0).getBusinessName());
        }

        // find coupons by region test1
        {
            Slice<CouponByRegion> coupons = couponByRegionRepository.findAllByRegionName("West Bay",
                    CassandraPageRequest.of(0,10));
            List<CouponByRegion> couponsContent = coupons.getContent();
            Assert.assertEquals(2, couponsContent.size());
            Assert.assertEquals("Airbnb", couponsContent.get(1).getBusinessName());
        }

        // find coupons by region test2
        {
            Slice<CouponByRegion> coupons = couponByRegionRepository.findAllByRegionName("East Bay",
                    CassandraPageRequest.of(0,10));
            List<CouponByRegion> couponsContent = coupons.getContent();
            Assert.assertEquals(1, couponsContent.size());
            Assert.assertEquals("Uber", couponsContent.get(0).getBusinessName());
        }

        // find coupons by state test
        {
            Slice<CouponByState> coupons = couponByStateRepository.findAllByStateName("California",
                    CassandraPageRequest.of(0,10));
            List<CouponByState> couponsContent = coupons.getContent();
            Assert.assertEquals(3, couponsContent.size());
            Assert.assertEquals("Airbnb", couponsContent.get(2).getBusinessName());
        }

        // find coupons by country test
        {
            Slice<CouponByCountry> coupons = couponByCountryRepository.findAllByCountryName("US",
                    CassandraPageRequest.of(0,10));
            List<CouponByCountry> couponsContent = coupons.getContent();
            Assert.assertEquals(3, couponsContent.size());
            Assert.assertEquals("Airbnb", couponsContent.get(2).getBusinessName());
        }

    }


    //TODO: test coupon service for adding invalid business record (empty business_name or null business_name or null id)
    //TODO: test coupon service for adding invalid coupon record (i.e coupon "" as cityName)
    //TODO: test coupon service finding coupons by city
    //TODO: We could also test the controllers by mocking the services response. Example below:

//    @MockBean
//    private CouponService couponService;

//    private void mockAllFieldsResponse() {
//        when(couponService.createBusiness(_business)).thenReturn(_business);
//        when(couponService.findAllBusinesses()).thenReturn(_businessList);
//        when(couponService.createCoupon(any())).thenReturn(_coupon);
//    }

//    @Test
//    public void issueCouponTest() throws Exception {
//        mockAllFieldsResponse();
//        this.mvc.perform(post("/issueCoupon")
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(asJsonString(_coupon)))
//                .andExpect(status().isOk());
//    }

//    @Test
//    public void createBusinessTest() throws Exception {
//        mockAllFieldsResponse();
//        this.mvc.perform(post("/addBusiness")
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(asJsonString(_business)))
//                .andExpect(status().isOk());
//    }

    /*
     * converts a Java object into JSON representation
     */
//    public static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}
