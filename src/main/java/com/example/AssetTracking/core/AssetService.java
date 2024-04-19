package com.example.AssetTracking.core;

import com.example.AssetTracking.data.*;
import com.example.AssetTracking.domain.Asset;
import com.example.AssetTracking.persistence.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

        // Handle division by zero
        if (rate.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        // Adjust scale for rounding
        rate = rate.divide(BigDecimal.valueOf(12), 4, RoundingMode.HALF_UP);

        // Calculate period
        Period period = Period.between(purchaseDate, currentDate);
        final int years = period.getYears();
        final int months = period.getMonths();

        // Calculate time in months
        final int time = years * 12 + months;

        // Calculate depreciated value
        BigDecimal ratePercentage = rate.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP);
        BigDecimal depreciationFactor = BigDecimal.ONE.subtract(ratePercentage);
        BigDecimal depreciatedValue = depreciationFactor.pow(time).multiply(cost);

        return depreciatedValue.setScale(2, RoundingMode.HALF_UP);
    }


    /**
     * Get all assets saved in the tracker.
     *
     * @return all the assets.
     */
    public GetAllAssetsResponse getAllAssets(){

        List<Asset> assets=assetRepository.findAll();;

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
    /*
    public GetAllAssetsResponse getAllAssets() {
        int page = 1;
        int pageSize = 20;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("purchaseDate").descending());
        Page<Asset> assetsPage = assetRepository.findAll(pageable);

        List<AssetSummary> assetSummaries = new ArrayList<>();
        for (Asset asset : assetsPage) {
            assetSummaries.add(new AssetSummary(
                    asset.getCost(),
                    asset.getDepreciationRate(),
                    depreciatedValue(asset).setScale(2, RoundingMode.HALF_UP),
                    asset.getPurchaseDate(),
                    asset.getTitle(),
                    asset.getId()
            ));
        }

        return new GetAllAssetsResponse(assetSummaries);
    }
     */

    public GetAllAssetSummaryResponse getAssetSummary() {
        return new GetAllAssetSummaryResponse(
                getAssetCount(),
                getCostOfAllAssets(),
                getCurrentValueOfAllAssets()
        );
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

    public BigDecimal getCurrentValueOfAllAssets() {
        final var assets = assetRepository.findAll();

        return  CurrentValueOfAllAssets(assets);
    }
    /**
     * Get the current value of all the assets.
     *
     * @return The current value of all assets.
     */
     BigDecimal CurrentValueOfAllAssets(final List<Asset> assets) {

        BigDecimal sum = BigDecimal.valueOf(0);
        for (var asset : assets) {
            sum = sum.add(depreciatedValue(asset));
        }
        return sum.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Saves an asset based on the provided asset summary request.
     *
     * @param request The asset summary request containing details of the asset to be saved.
     */
    public SaveAssetResponse save(SaveAssetRequest request) {

        int errorcount=0;

        String[] response = new String[4];
        if(request==null)
        {
            response[0] = "Asset request cannot be null";
            errorcount++;
        }
        if (request.getTitle() == null) {
            response[0] = "Asset title cannot be null";
            errorcount++;
        }
        else response[0] = "";
        if (request.getCost().compareTo(BigDecimal.ZERO) <= 0) {
            response[1] = "Cost must be greater than zero";
            errorcount++;
        } else if (request.getDepreciationRate().compareTo(BigDecimal.ZERO) < 0) {
            response[1] = "Depreciation rate must be non-negative";
            errorcount++;
        }
        else response[1] = "";
        if (request.getPurchaseDate().isAfter(LocalDate.now())) {
            response[2] = "Purchase date cannot be in the future";
            errorcount++;
        }
        else response[2] = "";
        if (request.getDepreciationRate() == null){
            response[3] = "The depreciation rate of the asset cannot be null";
            errorcount++;
        }
        response[3] = "";
        if(errorcount==0) {
            assetRepository.save(new Asset(request.getTitle(),
                    request.getCost(),
                    request.getDepreciationRate(),
                    request.getPurchaseDate()));
        }
        return new SaveAssetResponse(response);
    }

}
