package com.example.AssetTracking.Persistence;

import com.example.AssetTracking.Data.AssetSummary;
import com.example.AssetTracking.Domain.Asset;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

/**
 * A collection of {@link Asset}s.
 */
@Repository
public interface AssetRepository extends CrudRepository<Asset, Long>, JpaSpecificationExecutor<Asset> {

    /**
     * Calculate the size of asset collection.
     *
     * @return The size of asset collection.
     */
    default Long size() {
        final Iterable<Asset> ids = findAll();
        Long sum = 0L;
        for (var id : ids) {
            sum++;
        }
        return (sum);
    }
    /*
    @Query(value = "FROM Asset ORDER BY purchase_date")
    public Collection<Asset> findAll();

     */

    /*
    default Collection<Asset> findAll(){
        Collection<Asset> assets=findAll();

        Sort DEFAULT_SORT=Sort.by("purchase_date");

        Specification<Asset> specification=((root, query, criteriaBuilder) -> criteriaBuilder.);
        //Specification<Asset> specification=((root, query, builder) -> (Predicate) builder.asc(root.get("purchase_date")));
    }
     */

    /*
    public Collection<Asset> getSortedAll(){
        Sort sort = Sort.by(Sort.Direction.DESC, "yourProperty");

        return findAll();
    }

     */


    /**
     * Calculate the total cost of all assets.
     *
     * @return The total cost of all assets.
     */

    default BigDecimal costOfAllAssets() {
        final Iterable<Asset> ids = findAll();
        BigDecimal sum = BigDecimal.valueOf(0);
        for (var id : ids) {
            sum = sum.add(id.getCost());
        }
        return (sum);
    }

    /**
     * Calculates the current value of all assets, considering depreciation.
     *
     * @return The current value of all assets.
     */
    default BigDecimal currentValueOfAllAssets() {
        final Iterable<Asset> ids = findAll();
        BigDecimal sum = BigDecimal.valueOf(0);
        for (var id : ids) {
            sum = sum.add(depreciatedValue(id));
        }
        return (sum);
    }

    /**
     * Calculates the depreciated value of an asset.
     *
     * @param asset The asset for which to calculate the depreciated value.
     * @return The depreciated value of the asset.
     */
    default BigDecimal depreciatedValue(Asset asset) {
        BigDecimal rate = asset.getDepreciationRate();
        final BigDecimal cost = asset.getCost();
        final LocalDate purchaseDate = asset.getPurchaseDate();
        final LocalDate currentDate = LocalDate.now();

        rate = rate.divide(BigDecimal.valueOf(12), 2);

        Period period = Period.between(purchaseDate, currentDate);

        final int years = period.getYears();
        final int months = period.getMonths();

        final int time = years * 12 + months;

        return cost.multiply((BigDecimal.valueOf(1).subtract(rate.divide(BigDecimal.valueOf(100), 2))).pow(time));

    }


    /**
     * Calculates the number of years since the asset's purchase date.
     *
     * @param asset The asset for which to calculate the number of years.
     * @return The number of years since the asset's purchase date.
     */
    default int getYears(Asset asset) {

        final LocalDate purchaseDate = asset.getPurchaseDate();
        final LocalDate currentDate = LocalDate.now();
        final Period period = Period.between(purchaseDate, currentDate);

        return period.getYears();

    }

    /**
     * Calculates the number of months since the asset's purchase date.
     *
     * @param asset The asset for which to calculate the number of months.
     * @return The number of months since the asset's purchase date.
     */
    default int getMonths(Asset asset) {

        final LocalDate purchaseDate = asset.getPurchaseDate();
        final LocalDate currentDate = LocalDate.now();
        final Period period = Period.between(purchaseDate, currentDate);

        return period.getMonths();

    }
}
