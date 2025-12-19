package com.aserranoandres.inetum_inditex_prices_api.infrastructure.database.h2.repository;

import com.aserranoandres.inetum_inditex_prices_api.infrastructure.database.h2.entity.ProductPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Repository for ProductPriceEntity.
 */
@Repository
public interface ProductPriceJpaRepository extends JpaRepository<ProductPriceEntity, Long> {

    /**
     * Finds the first applicable product price based on productId, brandId, and application date.
     * Returns the price with the highest priority that is valid on the given application date.
     *
     * @param productId ID of the product
     * @param brandId ID of the brand
     * @param applicationDate Date to check price applicability
     * @return Optional containing the ProductPriceEntity with highest priority, or empty if not found
     */
    @Query(value = "SELECT * FROM prices " +
                   "WHERE product_id = :productId " +
                   "AND brand_id = :brandId " +
                   "AND :applicationDate BETWEEN start_date AND end_date " +
                   "ORDER BY priority DESC " +
                   "LIMIT 1",
           nativeQuery = true)
    Optional<ProductPriceEntity> findFirstApplicableProductPrice(
            @Param("productId") Long productId,
            @Param("brandId") Long brandId,
            @Param("applicationDate") LocalDateTime applicationDate
    );
}
