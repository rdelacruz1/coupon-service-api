# coupon-service-api

Clone, build and run instructions: 

1. Set `GRADLE_HOME` to cloned project location `.home` folder to avoid other system defaults. 

2. In order to run the server, type ./gradlew bootRun command from the project directory on the mac terminal. It may get stuck at 75%, ignore it, the api is running. 

3. The API will automatically execute a `startup-data.sql` file at startup that will initialize the database. 


Cassandra Data modeling: 

The Data model was optimized for number of reads by allowing data replication and increasing number of writes. In Cassandra writes are cheap, so it is always a good idea to trade writes in order to access the least amount of partitions per query possible.  There is one table for every query in order to read everything from as few partitions as possible. Cassandra stores partitions in different nodes so it is expensive to read data from multiple partitions. 

By using compound primary keys, we make sure that all the records that have the same partition key, reside in the same partition. For example, we have a coupons_by_city table (column family) whose partition key is cityName. What this means is that all the records (coupons) that have the same cityName partition key will be kept in the same partition.  

By keeping different tables, with different partition keys for each find query, we make sure to only access one partition when fetching coupons by city, region, state or country. 

 

Using the API: 

There are 6 main endpoints available for use. They are: 

`addBusiness()`

issueCoupon() 

findCouponsByCity() 

findCouponsByRegion() 

findCouponsByState() 

findCouponsByCountry() 


Using addBusiness endpoint:  

Endpoint path:  demo/addBusiness. 

Parameters: id UUID, String name 

Request example from terminal: 

```sh
curl --header "Content-Type: application/json"   --request POST   --data '{"id":"3c79e27e-6c3c-4e6c-b8ba-401e6a5ee39b","name":"tesla"}'   http://localhost:8080/demo/addBusiness 
```

 

Using issueCoupon endpoint: 

Endpoint path:  demo/issueCoupon. 

Parameters: String description, String businessName, String cityName, String regionName, String stateName, String countryName 

Request example from terminal: curl --header "Content-Type: application/json"   --request POST   --data '{"description":"Uber discount","businessName":"Uber","cityName":"San Francisco","regionName":"West Bay","stateName":"California","countryName":"US"}'   http://localhost:8080/demo/issueCoupon  

 

Using findCouponsByCity endpoint: 

Endpoint path:  demo/ findCouponsByCity 

Parameter: String cityName, String page, String limit 

Request example from browser: http://localhost:8080/demo/findCouponsByCity?cityName=San%20Jose 


Using findCouponsByRegion endpoint: 

Endpoint path:  demo/findCouponsByRegion 

Parameter: String regionName 

Request example from browser: http://localhost:8080/demo/findCouponsByRegion?regionName=West%20Bay 

 
Using findCouponsByState endpoint: 

Endpoint path:  demo/findCouponsByState 

Parameter: String stateName 

Request example from browser: http://localhost:8080/demo/findCouponsByState?stateName=California 

 

Using findCouponsByCountry endpoint: 

Endpoint path:  demo/findCouponsByCountry 

Parameter: String countryName 

Request example from browser: http://localhost:8080/demo/findCouponsByCountry?countryName=US 
