package com.example.AssetTracking.core;

import com.example.AssetTracking.data.AssetSummary;
import com.example.AssetTracking.data.GetAllAssetSummaryResponse;
import com.example.AssetTracking.data.GetAllAssetsResponse;
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

        rate = rate.divide(BigDecimal.valueOf(12), 2);

        Period period = Period.between(purchaseDate, currentDate);

        final int years = period.getYears();
        final int months = period.getMonths();

        final int time = years * 12 + months;

        return cost.multiply((BigDecimal.valueOf(1).subtract(rate.divide(BigDecimal.valueOf(100), 2))).pow(time));

    }

    /**
     * Get all assets saved in the tracker.
     *
     * @return all the assets.
     */
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

    /**
     * Get the current value of all the assets.
     *
     * @return The current value of all assets.
     */
    public BigDecimal getCurrentValueOfAllAssets() {
        final var assets = assetRepository.findAll();
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
    public void save(AssetSummary request) {
        if (request == null) {
            throw new IllegalArgumentException("Asset summary cannot be null");
        }
        if (request.getCost().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Cost must be greater than zero");
        }
        if (request.getDepreciationRate().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Depreciation rate must be non-negative");
        }
        if (request.getPurchaseDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Purchase date cannot be in the future");
        }

        assetRepository.save(new Asset(request.getTitle(),
                request.getCost(),
                request.getDepreciationRate(),
                request.getPurchaseDate()));
    }
}
