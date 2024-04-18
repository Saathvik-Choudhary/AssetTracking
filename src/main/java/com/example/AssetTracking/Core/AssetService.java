package com.example.AssetTracking.Core;

import com.example.AssetTracking.Data.AssetSummary;
import com.example.AssetTracking.Data.GetAllAssetSummaryResponse;
import com.example.AssetTracking.Data.GetAllAssetsRequest;
import com.example.AssetTracking.Data.GetAllAssetsResponse;
import com.example.AssetTracking.Domain.Asset;
import com.example.AssetTracking.Persistence.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.Period;
import java.math.BigDecimal;
import java.util.*;
import java.math.RoundingMode;

/**
 * Business operation on assets.
 */
@Service
public class AssetService {

    @Autowired
    AssetRepository assetRepository;

    /**
     * Get all assets saved in the tracker.
     *
     * @return all the assets.
     */
    public GetAllAssetsResponse getAllAssets( final GetAllAssetsRequest ){
//        Collections<Asset> assets=new arrayList<>;
//                assetRepository.findAll(); // Retrieve assets


        final var record = assetRepository.findAll();

        List<Asset> assets=new ArrayList<>();

        for(var asset:record) {
            assets.add(asset);
        }


        assets.sort(new AssetSortComparator());


        GetAllAssetsResponse response = new GetAllAssetsResponse();

        for (var asset : assets) {
            response.addAsset(new AssetSummary(asset.getCost()
                    , asset.getDepreciationRate()
                    , depreciatedValue(asset).setScale(2,RoundingMode.HALF_UP)
                    , asset.getPurchaseDate()
                    , asset.getTitle()
                    , asset.getId()));
        }

        return response;
    }

    public GetAllAssetSummaryResponse getAssetSummary(){
        return(new GetAllAssetSummaryResponse(getAssetCount(),getCostOfAllAssets(),getCurrentValueOfAllAssets()));
    }

    /**
     * Get the total number of assets saved in tracker.
     *
     * @return The count of tracker.
     */
    public Long getAssetCount() {
        return assetRepository.count();
    }

    /**
     * Get the total cost of the assets.
     *
     * @return The total cost of the assets.
     */
    public BigDecimal getCostOfAllAssets() {
        return assetRepository.costOfAllAssets();
    }

    /**
     * Get the current value of all the assets.
     *
     * @return The current value of all assets.
     */
    public BigDecimal getCurrentValueOfAllAssets() {
        final Iterable<Asset> ids = assetRepository.findAll();
        BigDecimal sum = BigDecimal.valueOf(0);
        for (var id : ids) {
            sum = sum.add(depreciatedValue(id));
        }
        return sum.setScale(2,RoundingMode.HALF_UP);
    }

    public void save(AssetSummary request) {
        System.out.println("this was called");
        assetRepository.save(new Asset(request.getTitle()
                , request.getCost()
                , request.getDepreciationRate()
                , request.getPurchaseDate()));
    }


    /**
     * Calculates the depreciated value of an asset.
     *
     * @param asset The asset for which to calculate the depreciated value.
     * @return The depreciated value of the asset.
     */
    public BigDecimal depreciatedValue(Asset asset) {
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


}
