package com.aserranoandres.inetum_inditex_prices_api.infrastructure.rest.mapper;


import com.aserranoandres.inetum_inditex_prices_api.domain.model.ProductPrice;
import com.aserranoandres.inetum_inditex_prices_api.infrastructure.rest.dto.ProductPriceDto;

/**
 * Mapper class for converting ProductPrice domain model to ProductPriceDto.
 */
public class ProductPriceDtoMapper {

    /**
     * Converts a ProductPrice domain model to a ProductPriceDto.
     * @param productPrice The ProductPrice domain model.
     * @return The corresponding ProductPriceDto.
     */
    public static ProductPriceDto fromProductPriceToDto(ProductPrice productPrice) {
        return new ProductPriceDto(
                productPrice.getProductId(),
                productPrice.getBrandId(),
                productPrice.getPriceList(),
                productPrice.getStartDate(),
                productPrice.getEndDate(),
                productPrice.getPriceValue()
        );
    }
}
