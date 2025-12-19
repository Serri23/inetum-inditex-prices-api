package com.aserranoandres.inetum_inditex_prices_api.unit.application.usecase;

import com.aserranoandres.inetum_inditex_prices_api.application.usecase.IProductPriceInteractor;
import com.aserranoandres.inetum_inditex_prices_api.application.usecase.ProductPriceInteractorImpl;
import com.aserranoandres.inetum_inditex_prices_api.domain.model.ProductPrice;
import com.aserranoandres.inetum_inditex_prices_api.domain.port.ProductPriceRepositoryPort;
import com.aserranoandres.inetum_inditex_prices_api.provider.ProductPriceProviderTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductPriceInteractorImplTest {

    private ProductPriceRepositoryPort productPriceRepositoryPort;

    @BeforeEach
    void setUp() {
        productPriceRepositoryPort = mock(ProductPriceRepositoryPort.class);
    }

    @ParameterizedTest
    @ArgumentsSource(ProductPriceProviderTest.class)
    void findApplicableProductPriceTest(LocalDateTime applicationDate, ProductPrice mockedProductPrice) {
        // Given
        IProductPriceInteractor productPriceInteractor = new ProductPriceInteractorImpl(productPriceRepositoryPort);

        when(productPriceRepositoryPort.findApplicableProductPrice(applicationDate,
                mockedProductPrice.getProductId(), mockedProductPrice.getBrandId()))
                .thenReturn(mockedProductPrice);

        // When
        ProductPrice result = productPriceInteractor.findApplicableProductPrice(applicationDate,
                mockedProductPrice.getProductId(), mockedProductPrice.getBrandId());

        // Then
        verify(productPriceRepositoryPort, times(1))
                .findApplicableProductPrice(applicationDate,
                        mockedProductPrice.getProductId(), mockedProductPrice.getBrandId());
        assertEquals(mockedProductPrice, result);
    }
}
