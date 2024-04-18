package com.example.AssetTracking.Core;

import com.example.AssetTracking.Data.AssetSummary;
import com.example.AssetTracking.Data.GetAllAssetSummaryResponse;
import com.example.AssetTracking.Data.GetAllAssetsResponse;
import com.example.AssetTracking.Domain.Asset;
import com.example.AssetTracking.Persistence.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Collections;
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
    public GetAllAssetsResponse getAllAssets(){
//        Collections<Asset> assets=new arrayList<>;
//                assetRepository.findAll(); // Retrieve assets

        List<Asset> assets = new ArrayList<>( assetRepository.getAll());

        assets.sort(new AssetSortComparator());


        GetAllAssetsResponse response = new GetAllAssetsResponse();

        for (var asset : assets) {
            response.addAsset(new AssetSummary(asset.getCost()
                    , asset.getDepreciationRate()
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
        return assetRepository.size();
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

        return assetRepository.currentValueOfAllAssets().setScale(2, RoundingMode.HALF_UP);
    }

    public void save(AssetSummary request) {
        System.out.println("this was called");
        assetRepository.save(new Asset(request.getTitle()
                , request.getCost()
                , request.getDepreciationRate()
                , request.getPurchaseDate()));
    }

}
