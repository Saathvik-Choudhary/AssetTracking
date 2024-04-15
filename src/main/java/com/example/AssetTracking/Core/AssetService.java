package com.example.AssetTracking.Core;

import com.example.AssetTracking.Data.AssetSummary;
import com.example.AssetTracking.Data.GetAllAssetsResponse;
import com.example.AssetTracking.Domain.Asset;
import com.example.AssetTracking.Persistence.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AssetService {

    @Autowired
    AssetRepository assetRepository;

    public GetAllAssetsResponse getAllAssets(){
        final var assests=assetRepository.findAll();

        GetAllAssetsResponse response=new GetAllAssetsResponse();

        for(var asset:assests){
            response.addAsset(new AssetSummary( asset.getCost(),asset.getDepreciationRate(),asset.getPurchaseDate(),asset.getTitle()));
        }

        return response;
    }


    public Long GetAssetCount() {
        return assetRepository.size();
    }

    public BigDecimal getCostOfAllAssets(){
        return assetRepository.costOfAllAssets();
    }

    public BigDecimal getValueOfAssets(){

        return assetRepository.valueOfAllAssets();
    }

    public void save(AssetSummary request) {
        assetRepository.save(new Asset(request.getCost(),request.getDepreciationRate(),request.getPurchaseDate(),request.getTitle()));
    }
}
