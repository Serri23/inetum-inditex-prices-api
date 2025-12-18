package com.aserranoandres.inetum_inditex_prices_api.provider;

import com.aserranoandres.inetum_inditex_prices_api.infrastructure.database.h2.entity.ProductPriceEntity;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Stream;

public class ProductPriceEntityProviderTest implements ArgumentsProvider {

    @Override
    public Stream<Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                // Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
                Arguments.of(
                        LocalDateTime.of(2020, 6, 14, 10, 0, 0),
                        new ProductPriceEntity(
                                null,
                                1L,
                                LocalDateTime.of(2020, 6, 14, 0, 0, 0),
                                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                                1L,
                                35455L,
                                0,
                                new BigDecimal("35.50"),
                                "EUR"
                        )
                ),
                // Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
                Arguments.of(
                        LocalDateTime.of(2020, 6, 14, 16, 0, 0),
                        new ProductPriceEntity(
                                null,
                                1L,
                                LocalDateTime.of(2020, 6, 14, 15, 0, 0),
                                LocalDateTime.of(2020, 6, 14, 18, 30, 0),
                                2L,
                                35455L,
                                1,
                                new BigDecimal("25.45"),
                                "EUR"
                        )
                ),
                // Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
                Arguments.of(
                        LocalDateTime.of(2020, 6, 14, 21, 0, 0),
                        new ProductPriceEntity(
                                null,
                                1L,
                                LocalDateTime.of(2020, 6, 14, 0, 0, 0),
                                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                                1L,
                                35455L,
                                0,
                                new BigDecimal("35.50"),
                                "EUR"
                        )
                ),
                // Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
                Arguments.of(
                        LocalDateTime.of(2020, 6, 15, 10, 0, 0),
                        new ProductPriceEntity(
                                null,
                                1L,
                                LocalDateTime.of(2020, 6, 15, 0, 0, 0),
                                LocalDateTime.of(2020, 6, 15, 11, 0, 0),
                                3L,
                                35455L,
                                1,
                                new BigDecimal("30.50"),
                                "EUR"
                        )
                ),
                // Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
                Arguments.of(
                        LocalDateTime.of(2020, 6, 16, 21, 0, 0),
                        new ProductPriceEntity(
                                null,
                                1L,
                                LocalDateTime.of(2020, 6, 15, 16, 0, 0),
                                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                                4L,
                                35455L,
                                1,
                                new BigDecimal("38.95"),
                                "EUR"
                        )
                )
        );
    }
}
