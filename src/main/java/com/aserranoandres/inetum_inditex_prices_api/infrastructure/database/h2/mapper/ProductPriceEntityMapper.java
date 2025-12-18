package com.aserranoandres.inetum_inditex_prices_api.infrastructure.database.h2.mapper;


import com.aserranoandres.inetum_inditex_prices_api.domain.model.ProductPrice;
import com.aserranoandres.inetum_inditex_prices_api.infrastructure.database.h2.entity.ProductPriceEntity;

/**
 * Mapper class for converting ProductPriceEntity to ProductPrice domain model.
 */
public class ProductPriceEntityMapper {

    /**
     * Converts a ProductPriceEntity to a ProductPrice domain model.
     * @param productPriceEntity The ProductPriceEntity to be converted.
     * @return The corresponding ProductPrice domain model.
     */
    public static ProductPrice fromEntityToProductPrice(ProductPriceEntity productPriceEntity) {
        return new ProductPrice(
                productPriceEntity.getBrandId(),
                productPriceEntity.getStartDate(),
                productPriceEntity.getEndDate(),
                productPriceEntity.getPriceList(),
                productPriceEntity.getProductId(),
                productPriceEntity.getPriority(),
                productPriceEntity.getPrice(),
                productPriceEntity.getCurrency()
        );
    }
}
