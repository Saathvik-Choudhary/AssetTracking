package com.example.AssetTracking.core;

import com.example.AssetTracking.domain.Asset;
import com.example.AssetTracking.persistence.AssetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AssetServiceTest {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private AssetService assetService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        assetService = new AssetService();
        assetService.assetRepository = assetRepository;
    }
    @Test
    void depreciatedValue() {
        Asset asset = new Asset("Test Asset"
                , new BigDecimal("1000")
                , new BigDecimal("1")
                , LocalDate.now().minusYears(1));
        BigDecimal result = assetService.depreciatedValue(asset);
        assertNotNull(result);
        assertTrue(result.compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    void getAllAssets() {
    }

    @Test
    void getAssetSummary() {
    }

    @Test
    void getAssetCount() {
    }

    @Test
    void getCostOfAllAssets() {
    }

    @Test
    void getCurrentValueOfAllAssets() {
    }

    @Test
    void save() {
    }
}