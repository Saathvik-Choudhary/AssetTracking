package com.example.AssetTracking.Persistence;

import com.example.AssetTracking.Domain.Asset;
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

    default Long size(){
        Iterable<Asset> ids=findAll();
        Long sum= 0L;
        for(var id : ids)
        {
            sum++;
        }
        return (sum);
    }

    default BigDecimal costOfAllAssets(){
        Iterable<Asset> ids=findAll();
        BigDecimal sum = BigDecimal.valueOf(0);

        for(var id : ids) {
            sum = sum.add(id.getCost());
        }

        return (sum);
    }

    default BigDecimal valueOfAllAssests(){
        Iterable<Asset> ids=findAll();
        BigDecimal sum = BigDecimal.valueOf(0);

        for(var id : ids) {
            sum = sum.add(depreciatedValue(id));
        }

        return (sum);
    }

    default BigDecimal depreciatedValue(Asset asset){
        BigDecimal rate=asset.getDepreciationRate();
        BigDecimal cost=asset.getCost();
        LocalDate purchaseDate=asset.getPurchaseDate();
        LocalDate currentDate = LocalDate.now();

        rate=rate.divide(BigDecimal.valueOf(12),2);

        Period period = Period.between(purchaseDate, currentDate);

        int years = period.getYears();
        int months = period.getMonths();

        int time=years*12 +months;

        BigDecimal depreciatedValue=cost.multiply( (BigDecimal.valueOf(1).subtract (rate.divide(BigDecimal.valueOf(100),2))).pow(time));

        return depreciatedValue;
    }


    default int getYears(Asset asset){

        LocalDate purchaseDate=asset.getPurchaseDate();
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(purchaseDate, currentDate);

        int years = period.getYears();
        int months = period.getMonths();

        return years;
    }

    default int getMonths(Asset asset){

        LocalDate purchaseDate=asset.getPurchaseDate();
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(purchaseDate, currentDate);

        int months = period.getMonths();

        return months;
    }
}
