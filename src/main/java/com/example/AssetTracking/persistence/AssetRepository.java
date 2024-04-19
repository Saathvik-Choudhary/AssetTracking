package com.example.AssetTracking.persistence;

import com.example.AssetTracking.domain.Asset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;


/**
 * A collection of {@link Asset}s.
 */
@Repository
public interface AssetRepository extends JpaRepository<Asset, Long>, JpaSpecificationExecutor<Asset> {

    /**
     * Calculate the total cost of all assets.
     *
     * @return The total cost of all assets.
     */
    @Query("SELECT SUM(cost) FROM Asset")
    BigDecimal costOfAllAssets();

    Page<Asset> findAll(Pageable pageable);
}
