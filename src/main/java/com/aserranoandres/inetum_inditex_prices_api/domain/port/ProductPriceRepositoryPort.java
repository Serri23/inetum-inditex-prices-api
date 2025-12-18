package com.aserranoandres.inetum_inditex_prices_api.domain.port;

import com.aserranoandres.inetum_inditex_prices_api.domain.model.ProductPrice;

import java.time.LocalDateTime;

/**
 * Port interface for retrieving product price information.
 */
public interface ProductPriceRepositoryPort {

    /**
     * Finds the product price based on product ID, brand ID, and application date.
     * @param applicationDate The date and time when the price is to be applied.
     * @param productId The identifier of the product.
     * @param brandId The identifier of the brand.
     * @return The product price information.
     */
    ProductPrice findApplicableProductPrice(LocalDateTime applicationDate, Long productId, Long brandId);
}
