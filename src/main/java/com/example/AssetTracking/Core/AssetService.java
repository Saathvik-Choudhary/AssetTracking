package com.example.AssetTracking.Core;

import com.example.AssetTracking.Data.AssetSummary;
import com.example.AssetTracking.Data.GetAllAssetSummaryResponse;
import com.example.AssetTracking.Data.GetAllAssetsResponse;
import com.example.AssetTracking.Domain.Asset;
import com.example.AssetTracking.Persistence.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Business operation on assets.
 */
@Service
public class AssetService {

    @Autowired
    AssetRepository assetRepository;

<<<<<<< HEAD
    /**
     * Get all assets saved in the tracker.
     *
     * @return all the assets.
     */
    public GetAllAssetsResponse getAllAssets() {
        final var assets = assetRepository.findAll();
=======
    public GetAllAssetsResponse getAllAssets(){
        final var assets=assetRepository.findAll();
>>>>>>> 4d9e2fe7ebfae5a4b49d8abf4092b2a327e810e6

        GetAllAssetsResponse response = new GetAllAssetsResponse();

<<<<<<< HEAD
        for (var asset : assets) {
            response.addAsset(new AssetSummary(asset.getCost()
                    , asset.getDepreciationRate()
                    , asset.getPurchaseDate()
                    , asset.getTitle()));
=======
        for(var asset:assets){
            response.addAsset(new AssetSummary( asset.getTitle(),asset.getCost(),asset.getPurchaseDate(),asset.getDepreciationRate()));
>>>>>>> 4d9e2fe7ebfae5a4b49d8abf4092b2a327e810e6
        }

        return response;
    }

    public GetAllAssetSummaryResponse getAssetSummary(){
        return(new GetAllAssetSummaryResponse(getAssetCount(),getCostOfAllAssets(),getValueOfAssets()));
    }

<<<<<<< HEAD
    /**
     * Get the total number of assets saved in tracker.
     *
     * @return The count of tracker.
     */
    public Long GetAssetCount() {
=======
    public Long getAssetCount() {
>>>>>>> 4d9e2fe7ebfae5a4b49d8abf4092b2a327e810e6
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

<<<<<<< HEAD
        return assetRepository.currentValueOfAllAssets();
=======
        return assetRepository.valueOfAllAssets();
    }

    public void save(AssetSummary request) {
        assetRepository.save(new Asset(request.getCost(),request.getDepreciationRate(),request.getPurchaseDate(),request.getTitle()));
>>>>>>> 4d9e2fe7ebfae5a4b49d8abf4092b2a327e810e6
    }

}
