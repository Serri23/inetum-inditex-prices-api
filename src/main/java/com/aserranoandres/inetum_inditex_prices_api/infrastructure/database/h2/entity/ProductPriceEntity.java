package com.aserranoandres.inetum_inditex_prices_api.infrastructure.database.h2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents a price entity in the system.
 * This class maps to the 'prices' table in the database.
 */
@Entity
@Table(name = "prices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPriceEntity {

    /**
     * Unique identifier for the price record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Identifier for the brand associated with the price.
     */
    @Column(name = "brand_id")
    private Long brandId;

    /**
     * Start date and time for the price validity.
     */
    @Column(name = "start_date")
    private LocalDateTime startDate;

    /**
     * End date and time for the price validity.
     */
    @Column(name = "end_date")
    private LocalDateTime endDate;

    /**
     * Identifier for the price list.
     */
    @Column(name = "price_list")
    private Long priceList;

    /**
     * Identifier for the product associated with the price.
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     * Priority of the price in case of multiple prices for the same product.
     */
    @Column(name = "priority")
    private Integer priority;

    /**
     * Price amount for the product.
     */
    @Column(name = "price")
    private BigDecimal price;

    /**
     * Currency in which the price is denominated.
     */
    @Column(name = "currency")
    private String currency;
}