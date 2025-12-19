package com.aserranoandres.inetum_inditex_prices_api.infrastructure.database.h2.adapter;


import com.aserranoandres.inetum_inditex_prices_api.domain.model.ProductPrice;
import com.aserranoandres.inetum_inditex_prices_api.domain.port.ProductPriceRepositoryPort;
import com.aserranoandres.inetum_inditex_prices_api.infrastructure.database.h2.mapper.ProductPriceEntityMapper;
import com.aserranoandres.inetum_inditex_prices_api.infrastructure.database.h2.repository.ProductPriceJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Adapter for ProductPriceRepositoryPort using H2 database.
 */
@Component
@RequiredArgsConstructor
public class ProductPriceRepositoryAdapter implements ProductPriceRepositoryPort {

    /**
     * The JPA repository for accessing product price data.
     */
    private final ProductPriceJpaRepository productPriceJpaRepository;

    /**
     * @param applicationDate The date and time when the price is to be applied.
     * @param productId The identifier of the product.
     * @param brandId The identifier of the brand.
     * @return The product price information.
     */
    @Override
    public ProductPrice findApplicableProductPrice(LocalDateTime applicationDate, Long productId, Long brandId) {
        return productPriceJpaRepository
                .findFirstApplicableProductPrice(productId, brandId, applicationDate)
                .map(ProductPriceEntityMapper::fromEntityToProductPrice)
                .orElseThrow(() -> new RuntimeException("Product price not found"));
    }
}
