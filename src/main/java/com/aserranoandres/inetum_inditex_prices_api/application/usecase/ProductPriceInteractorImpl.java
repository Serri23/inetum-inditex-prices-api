package com.aserranoandres.inetum_inditex_prices_api.application.usecase;

import com.aserranoandres.inetum_inditex_prices_api.domain.model.ProductPrice;
import com.aserranoandres.inetum_inditex_prices_api.domain.port.ProductPriceRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Implementation of the IProductPriceInteractor interface.
 */
@Component
@RequiredArgsConstructor
public class ProductPriceInteractorImpl implements IProductPriceInteractor {

    /**
     * The repository for accessing product price data.
     */
    private final ProductPriceRepositoryPort productPriceRepositoryPort;

    /**
     * Finds the product price based on product ID, brand ID, and application date.
     * @param applicationDate The date and time when the price is to be applied.
     * @param productId The identifier of the product.
     * @param brandId The identifier of the brand.
     * @return The product price information.
     */
    @Override
    public ProductPrice findApplicableProductPrice(LocalDateTime applicationDate, Long productId, Long brandId) {
        return productPriceRepositoryPort.findApplicableProductPrice(applicationDate, productId, brandId);
    }
}
