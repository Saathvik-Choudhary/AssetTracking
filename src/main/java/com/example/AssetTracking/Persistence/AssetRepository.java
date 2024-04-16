package com.example.AssetTracking.Persistence;

import com.example.AssetTracking.Data.AssetSummary;
import com.example.AssetTracking.Domain.Asset;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;

@Repository
public interface AssetRepository extends CrudRepository<Asset,Long>, JpaSpecificationExecutor<Asset> {

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


    default Long size(){
        final Iterable<Asset> ids=findAll();
        Long sum= 0L;
        for(var id : ids)
        {
            sum++;
        }
        return (sum);
    }

    default BigDecimal costOfAllAssets(){
        final Iterable<Asset> ids=findAll();
        BigDecimal sum = BigDecimal.valueOf(0);

        for(var id : ids) {
            sum = sum.add(id.getCost());
        }

        return (sum);
    }

    default BigDecimal valueOfAllAssets(){
        final Iterable<Asset> ids=findAll();
        BigDecimal sum = BigDecimal.valueOf(0);

        for(var id : ids) {
            sum = sum.add(depreciatedValue(id));
        }

        return (sum);
    }

    default BigDecimal depreciatedValue(Asset asset){
        BigDecimal rate=asset.getDepreciationRate();
        final BigDecimal cost=asset.getCost();
        final LocalDate purchaseDate=asset.getPurchaseDate();
        final LocalDate currentDate = LocalDate.now();

        rate = rate.divide(BigDecimal.valueOf(12),2);

        Period period = Period.between(purchaseDate, currentDate);

        final int years = period.getYears();
        final int months = period.getMonths();

        final int time=years*12 +months;

        return cost.multiply( (BigDecimal.valueOf(1).subtract (rate.divide(BigDecimal.valueOf(100),2))).pow(time));

    }


    default int getYears(Asset asset){

        final LocalDate purchaseDate=asset.getPurchaseDate();
        final LocalDate currentDate = LocalDate.now();
        final Period period = Period.between(purchaseDate, currentDate);

        return period.getYears();

    }

    default int getMonths(Asset asset){

        final LocalDate purchaseDate=asset.getPurchaseDate();
        final LocalDate currentDate = LocalDate.now();
        final Period period = Period.between(purchaseDate, currentDate);

        return period.getMonths();

    }
}
