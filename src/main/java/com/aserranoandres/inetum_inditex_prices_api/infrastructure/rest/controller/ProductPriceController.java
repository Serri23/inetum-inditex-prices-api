package com.aserranoandres.inetum_inditex_prices_api.infrastructure.rest.controller;

import com.aserranoandres.inetum_inditex_prices_api.application.usecase.IProductPriceInteractor;
import com.aserranoandres.inetum_inditex_prices_api.infrastructure.rest.dto.ProductPriceDto;
import com.aserranoandres.inetum_inditex_prices_api.infrastructure.rest.mapper.ProductPriceDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 *  Controller for handling product price-related REST API requests.
 */
@RestController
@RequestMapping("/api/product-prices")
@AllArgsConstructor
public class ProductPriceController {

     /**
     * Service for handling price-related operations.
     */
    private final IProductPriceInteractor productPriceInteractor;

    /**
     * Gets the applicable product price based on the provided parameters.
     * @param applicationDate The date and time when the price is to be applied.
     * @param productId The identifier of the product.
     * @param brandId The identifier of the brand.
     * @return The applicable product price information.
     */
    @Operation(
            summary = "Get price by application date, product ID, and brand ID",
            description = "Retrieves the price for a given application date, product ID, and brand ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Price retrieved successfully"),
                    @ApiResponse(responseCode = "400", description = "Bad Request - Missing or invalid parameters"),
                    @ApiResponse(responseCode = "404", description = "Not Found - Price not found for the given parameters"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error - Unexpected error occurred")
            }
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductPriceDto getApplicableProductPrice(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
            @RequestParam Long  productId,
            @RequestParam Long brandId){

        return ProductPriceDtoMapper.fromProductPriceToDto(
                productPriceInteractor.findApplicableProductPrice(applicationDate, productId, brandId)
        );
    }
}