package com.example.AssetTracking.Core;

import com.example.AssetTracking.Domain.Asset;
import com.example.AssetTracking.Persistence.AssetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AssetServiceTest {

    @Autowired
    AssetRepository assetRepository;

    @Autowired
    AssetService assetService;

    @Test
    public void assetCountTest(){
        Assertions.assertEquals(10,assetService.getAssetCount());

        assetRepository.save(new Asset());

    }

    @Test
    public void assetSummaryTest(){

        Assertions.assertEquals();
    }
}