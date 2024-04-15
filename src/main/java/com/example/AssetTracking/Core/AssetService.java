package com.example.AssetTracking.Core;

import com.example.AssetTracking.Data.AssetSummary;
import com.example.AssetTracking.Data.GetAllAssetsResponse;
import com.example.AssetTracking.Persistence.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssetService {

    @Autowired
    AssetRepository assetRepository;

    public GetAllAssetsResponse getAllAssets() {
        final var assets = assetRepository.findAll();

        GetAllAssetsResponse response = new GetAllAssetsResponse();

        for (var asset : assets) {
            response.addAsset(new AssetSummary(asset.getCost(), asset.getDepreciationRate(), asset.getPurchaseDate(), asset.getTitle()));
        }

        return response;
    }


    public long GetAssetCount() {
        Long a = assetRepository.size();
        return a;
    }
}

