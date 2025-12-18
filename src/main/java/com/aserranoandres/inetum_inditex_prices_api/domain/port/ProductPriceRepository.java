package com.aserranoandres.inetum_inditex_prices_api.domain.port;

import com.aserranoandres.inetum_inditex_prices_api.domain.model.ProductPrice;

/**
 * Port interface for retrieving product price information.
 */
public interface ProductPriceRepository {

    /**
     * Finds the product price based on product ID, brand ID, and application date.
     * @param productId The identifier of the product.
     * @param brandId The identifier of the brand.
     * @param applicationDate The date and time when the price is to be applied.
     * @return The product price information.
     */
    ProductPrice findProductPrice(Long productId, Long brandId, String applicationDate);
}
