package com.aserranoandres.inetum_inditex_prices_api.unit.infrastructure.database.h2.adapter;

import com.aserranoandres.inetum_inditex_prices_api.domain.model.ProductPrice;
import com.aserranoandres.inetum_inditex_prices_api.infrastructure.database.h2.adapter.ProductPriceRepositoryAdapter;
import com.aserranoandres.inetum_inditex_prices_api.infrastructure.database.h2.entity.ProductPriceEntity;
import com.aserranoandres.inetum_inditex_prices_api.infrastructure.database.h2.repository.ProductPriceJpaRepository;
import com.aserranoandres.inetum_inditex_prices_api.provider.ProductPriceEntityProviderTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ProductPriceRepositoryAdapterTest {

    ProductPriceJpaRepository productPriceJpaRepository;

    @BeforeEach
    void setUp() {
        productPriceJpaRepository = mock(ProductPriceJpaRepository.class);
    }

    @ParameterizedTest
    @ArgumentsSource(ProductPriceEntityProviderTest.class)
    void findApplicableProductPriceTest(LocalDateTime applicationDate, ProductPriceEntity mockedProductPriceEntity) {
        //GIVEN
        ProductPriceRepositoryAdapter productPriceRepositoryAdapter = new ProductPriceRepositoryAdapter(productPriceJpaRepository);
        given(productPriceJpaRepository.findFirstApplicableProductPrice(
                mockedProductPriceEntity.getProductId(),
                mockedProductPriceEntity.getBrandId(),
                applicationDate)).willReturn(Optional.of(mockedProductPriceEntity));
        //WHEN
        ProductPrice productPrice = productPriceRepositoryAdapter.findApplicableProductPrice(applicationDate,
                mockedProductPriceEntity.getProductId(),
                mockedProductPriceEntity.getBrandId());
        //THEN
        assertEquals(mockedProductPriceEntity.getProductId(), productPrice.getProductId());
        assertEquals(mockedProductPriceEntity.getBrandId(), productPrice.getBrandId());
        assertEquals(mockedProductPriceEntity.getPriceList(), productPrice.getPriceList());
        assertEquals(mockedProductPriceEntity.getStartDate(), productPrice.getStartDate());
        assertEquals(mockedProductPriceEntity.getEndDate(), productPrice.getEndDate());
        assertEquals(mockedProductPriceEntity.getPrice(), productPrice.getPriceValue());
        assertEquals(mockedProductPriceEntity.getCurrency(), productPrice.getCurrency());
    }
}
