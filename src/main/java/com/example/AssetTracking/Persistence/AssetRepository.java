package com.example.AssetTracking.Persistence;

import com.example.AssetTracking.Domain.Asset;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

import java.util.ArrayList;
import java.util.List;


/**
 * A collection of {@link Asset}s.
 */
@Repository
public interface AssetRepository extends CrudRepository<Asset, Long>, JpaSpecificationExecutor<Asset> {

    /**
     * Calculate the total cost of all assets.
     *
     * @return The total cost of all assets.
     */
    @Query("SELECT SUM(cost) FROM Asset")
    BigDecimal costOfAllAssets();
}
