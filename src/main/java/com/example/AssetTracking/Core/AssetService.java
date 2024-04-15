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

    public GetAllAssetsResponse getAllAssests(){
        final var assests=assetRepository.findAll();

        GetAllAssetsResponse response=new GetAllAssetsResponse();

        for(var asset:assests){
            response.addAsset(new AssetSummary( asset.getCost(),asset.getDepreciationRate(),asset.getPurchaseDate(),asset.getTitle()));
        }

        return response;
    }


    public int GetAssetCount()
    {
        Long a =assetRepository.size();
    }
    /*
    public AssetSummary summaryContainer(){

    }

     */
}
