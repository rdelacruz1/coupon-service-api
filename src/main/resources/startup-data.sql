CREATE TABLE coupon_service.coupons_by_city (description text, businessName text, cityName text, PRIMARY KEY (cityName, businessName, description));
CREATE TABLE coupon_service.coupons_by_region (description text, businessName text, cityName text, regionName text, PRIMARY KEY (regionName, cityName, businessName, description));
CREATE TABLE coupon_service.coupons_by_state (description text, businessName text, cityName text, regionName text, stateName text, PRIMARY KEY (stateName, cityName, regionName, businessName, description));
CREATE TABLE coupon_service.coupons_by_country (description text, businessName text, cityName text, regionName text, stateName text, countryName text, PRIMARY KEY (countryName, cityName, regionName, stateName, businessName, description));

CREATE TABLE coupon_service.business (id UUID, name text, PRIMARY KEY (id));
CREATE INDEX ON coupon_service.business (name);

INSERT INTO coupon_service.business (id, name) VALUES (3c79e27e-6c3c-4e6c-b8ba-401e6a5ee46b, 'Apple');
INSERT INTO coupon_service.business (id, name) VALUES (3c79e27e-6c3c-4e6c-b8ba-401e6a5ee47b, 'Uber');
INSERT INTO coupon_service.business (id, name) VALUES (3c79e27e-6c3c-4e6c-b8ba-401e6a5ee48b, 'Airbnb');

INSERT INTO coupon_service.coupons_by_city (description, businessName, cityName) VALUES ('Airbnb 5% off coupon', 'Airbnb', 'San Jose');
INSERT INTO coupon_service.coupons_by_region (description, businessName, cityName, regionName) VALUES ('Airbnb 5% off coupon', 'Airbnb', 'San Jose', 'West Bay');
INSERT INTO coupon_service.coupons_by_state (description, businessName, cityName, regionName, stateName) VALUES ('Airbnb 5% off coupon', 'Airbnb', 'San Jose', 'West Bay', 'California');
INSERT INTO coupon_service.coupons_by_country (description, businessName, cityName, regionName, stateName, countryName) VALUES ('Airbnb 5% off coupon', 'Airbnb', 'San Jose', 'West Bay', 'California', 'US');

INSERT INTO coupon_service.coupons_by_city (description, businessName, cityName) VALUES ('Uber 5% off coupon', 'Uber', 'Oakland');
INSERT INTO coupon_service.coupons_by_region (description, businessName, cityName, regionName) VALUES ('Uber 5% off coupon', 'Uber', 'Oakland', 'East Bay');
INSERT INTO coupon_service.coupons_by_state (description, businessName, cityName, regionName, stateName) VALUES ('Uber 5% off coupon', 'Uber', 'Oakland', 'East Bay', 'California');
INSERT INTO coupon_service.coupons_by_country (description, businessName, cityName, regionName, stateName, countryName) VALUES ('Uber 5% off coupon', 'Uber', 'Oakland', 'East Bay', 'California', 'US');

INSERT INTO coupon_service.coupons_by_city (description, businessName, cityName) VALUES ('Apple 5% off coupon', 'Apple', 'Cupertino');
INSERT INTO coupon_service.coupons_by_region (description, businessName, cityName, regionName) VALUES ('Apple 5% off coupon', 'Apple', 'Cupertino', 'West Bay');
INSERT INTO coupon_service.coupons_by_state (description, businessName, cityName, regionName, stateName) VALUES ('Apple 5% off coupon', 'Apple', 'Cupertino', 'West Bay', 'California');
INSERT INTO coupon_service.coupons_by_country (description, businessName, cityName, regionName, stateName, countryName) VALUES ('Apple 5% off coupon', 'Apple', 'Cupertino', 'West Bay', 'California', 'US');
