package com.aserranoandres.inetum_inditex_prices_api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Domain model representing the price of a product.
 */
@Getter
@AllArgsConstructor
public class ProductPrice {

    /**
     * The identifier of the brand.
     */
    private Long brandId;

    /**
     * The start date and time when the price is valid.
     */
    private LocalDateTime startDate;

    /**
     * The end date and time when the price is valid.
     */
    private LocalDateTime endDate;

    /**
     * The identifier of the price list.
     */
    private Long priceList;

    /**
     * The identifier of the product.
     */
    private Long productId;

    /**
     * The priority of the price.
     */
    private Integer priority;

    /**
     * The price value.
     */
    private BigDecimal priceValue;

    /**
     * The currency of the price.
     */
    private String currency;
}
