package com.aserranoandres.inetum_inditex_prices_api.e2e;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class ProductPriceE2ETest {

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }


    @Test
    void test1_requestAt10On14thForProduct35455Brand1() {
        given()
                .queryParam("applicationDate", "2020-06-14T10:00:00")
                .queryParam("productId", "35455")
                .queryParam("brandId", "1")
        .when()
                .get("/api/product-prices")
        .then()
                .log()
                .body()
                .assertThat()
                .body("productId", equalTo(35455))
                .body("brandId", equalTo(1))
                .body("priceList", equalTo(1))
                .body("priceValue", equalTo(35.5f))
                .statusCode(200);
    }

    @Test
    void test2_requestAt16On14thForProduct35455Brand1() {
        given()
                .queryParam("applicationDate", "2020-06-14T16:00:00")
                .queryParam("productId", "35455")
                .queryParam("brandId", "1")
        .when()
                .get("/api/product-prices")
        .then()
                .log()
                .body()
                .assertThat()
                .body("productId", equalTo(35455))
                .body("brandId", equalTo(1))
                .body("priceList", equalTo(2))
                .body("priceValue", equalTo(25.45f))
                .statusCode(200);
    }

    @Test
    void test3_requestAt21On14thForProduct35455Brand1() {
        given()
                .queryParam("applicationDate", "2020-06-14T21:00:00")
                .queryParam("productId", "35455")
                .queryParam("brandId", "1")
        .when()
                .get("/api/product-prices")
        .then()
                .log()
                .body()
                .assertThat()
                .body("productId", equalTo(35455))
                .body("brandId", equalTo(1))
                .body("priceList", equalTo(1))
                .body("priceValue", equalTo(35.5f))
                .statusCode(200);
    }


    @Test
    void test4_requestAt10On15thForProduct35455Brand1() {
        given()
                .queryParam("applicationDate", "2020-06-15T10:00:00")
                .queryParam("productId", "35455")
                .queryParam("brandId", "1")
        .when()
                .get("/api/product-prices")
        .then()
                .log()
                .body()
                .assertThat()
                .body("productId", equalTo(35455))
                .body("brandId", equalTo(1))
                .body("priceList", equalTo(3))
                .body("priceValue", equalTo(30.5f))
                .statusCode(200);
    }

    @Test
    void test5_requestAt21On16thForProduct35455Brand1() {
        given()
                .queryParam("applicationDate", "2020-06-16T21:00:00")
                .queryParam("productId", "35455")
                .queryParam("brandId", "1")
        .when()
                .get("/api/product-prices")
        .then()
                .log()
                .body()
                .assertThat()
                .body("productId", equalTo(35455))
                .body("brandId", equalTo(1))
                .body("priceList", equalTo(4))
                .body("priceValue", equalTo(38.95f))
                .statusCode(200);
    }

    @Test
    void testMissingApplicationDateParameter() {
        given()
                .queryParam("productId", "35455")
                .queryParam("brandId", "1")
        .when()
                .get("/api/product-prices")
        .then()
                .log()
                .body()
                .assertThat()
                .statusCode(400);
    }

    @Test
    void testMissingProductIdParameter() {
        given()
                .queryParam("applicationDate", "2020-06-14T10:00:00")
                .queryParam("brandId", "1")
        .when()
                .get("/api/product-prices")
        .then()
                .log()
                .body()
                .assertThat()
                .statusCode(400);
    }

    @Test
    void testMissingBrandIdParameter() {
        given()
                .queryParam("applicationDate", "2020-06-14T10:00:00")
                .queryParam("productId", "35455")
        .when()
                .get("/api/product-prices")
        .then()
                .log()
                .body()
                .assertThat()
                .statusCode(400);
    }

    @Test
    void testInvalidDateFormat() {
        given()
                .queryParam("applicationDate", "invalid-date")
                .queryParam("productId", "35455")
                .queryParam("brandId", "1")
        .when()
                .get("/api/product-prices")
        .then()
                .log()
                .body()
                .assertThat()
                .statusCode(400);
    }

    @Test
    void testInvalidProductIdFormat() {
        given()
                .queryParam("applicationDate", "2020-06-14T10:00:00")
                .queryParam("productId", "invalid")
                .queryParam("brandId", "1")
        .when()
                .get("/api/product-prices")
        .then()
                .log()
                .body()
                .assertThat()
                .statusCode(400);
    }
}
