package com.aserranoandres.inetum_inditex_prices_api.infrastructure.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) representing the price of a product.
 */
@Getter
@Setter
@AllArgsConstructor
public class ProductPriceDto {

    /**
     * The identifier of the product.
     */
    @Schema(example = "35455", description = "Product ID")
    private Long productId;

    /**
     * The identifier of the brand.
     */
    @Schema(example = "1", description = "Brand ID")
    private Long brandId;

    /**
     * The identifier of the price list.
     */
    @Schema(example = "1", description = "Price List ID")
    private Long priceList;

    /**
     * The start date and time when the price is valid.
     */
    @Schema(example = "2020-06-14T00:00:00", description = "Start Date and Time")
    private LocalDateTime startDate;

    /**
     * The end date and time when the price is valid.
     */
    @Schema(example = "2020-12-31T23:59:59", description = "End Date and Time")
    private LocalDateTime endDate;

    /**
     * The price value.
     */
    @Schema(example = "35.50", description = "Price Value")
    private BigDecimal priceValue;
}
