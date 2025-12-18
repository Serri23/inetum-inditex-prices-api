package com.aserranoandres.inetum_inditex_prices_api.unit.infrastructure.rest.controller;

import com.aserranoandres.inetum_inditex_prices_api.application.usecase.IProductPriceInteractor;
import com.aserranoandres.inetum_inditex_prices_api.infrastructure.database.h2.entity.ProductPriceEntity;
import com.aserranoandres.inetum_inditex_prices_api.infrastructure.database.h2.mapper.ProductPriceEntityMapper;
import com.aserranoandres.inetum_inditex_prices_api.infrastructure.rest.controller.ProductPriceController;
import com.aserranoandres.inetum_inditex_prices_api.infrastructure.rest.dto.ProductPriceDto;
import com.aserranoandres.inetum_inditex_prices_api.provider.ProductPriceEntityProviderTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ProductPriceControllerTest {

    private IProductPriceInteractor productPriceInteractor;
    private ProductPriceController productPriceController;

    @BeforeEach
    void setUp() {
        productPriceInteractor = mock(IProductPriceInteractor.class);
        productPriceController = new ProductPriceController(productPriceInteractor);
    }

    @ParameterizedTest
    @ArgumentsSource(ProductPriceEntityProviderTest.class)
    void test(LocalDateTime applicationDate, ProductPriceEntity mockedProductPriceEntity){
        // given
        given(productPriceInteractor.findApplicableProductPrice(applicationDate,mockedProductPriceEntity.getProductId(),
                mockedProductPriceEntity.getBrandId())).willReturn(ProductPriceEntityMapper.fromEntityToProductPrice(mockedProductPriceEntity));

        // when
        ProductPriceDto productPriceDto = productPriceController.getApplicableProductPrice(applicationDate,mockedProductPriceEntity.getProductId(),
                mockedProductPriceEntity.getBrandId());

        // then
        verify(productPriceInteractor, times(1)).findApplicableProductPrice(applicationDate,
                mockedProductPriceEntity.getProductId(), mockedProductPriceEntity.getBrandId());
        assertEquals(mockedProductPriceEntity.getProductId(), productPriceDto.getProductId());
        assertEquals(mockedProductPriceEntity.getBrandId(), productPriceDto.getBrandId());
        assertEquals(mockedProductPriceEntity.getPriceList(), productPriceDto.getPriceList());
        assertEquals(mockedProductPriceEntity.getStartDate(), productPriceDto.getStartDate());
        assertEquals(mockedProductPriceEntity.getEndDate(), productPriceDto.getEndDate());
        assertEquals(mockedProductPriceEntity.getPrice(), productPriceDto.getPriceValue());
    }
}
