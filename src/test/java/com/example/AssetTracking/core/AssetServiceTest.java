package com.example.AssetTracking.core;

import com.example.AssetTracking.data.SaveAssetRequest;
import com.example.AssetTracking.domain.Asset;
import com.example.AssetTracking.persistence.AssetPopulator;
import com.example.AssetTracking.persistence.AssetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class AssetServiceTest {


    @Mock
    private AssetRepository assetRepository;

    @InjectMocks
    private AssetService assetService;

    /**
     * Initializes Mockito annotations before each test method.
     */
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void CurrentValueOfAllAssetsTests() {

        List<Asset> assets=new ArrayList<>();

        Asset asset1=new Asset("Laptop",
                BigDecimal.valueOf(1000)
                , BigDecimal.valueOf(10)
                , LocalDate.now()
        );
        assets.add(asset1);

     Asset asset2=new Asset("Laptop",
             BigDecimal.valueOf(1000)
             , BigDecimal.valueOf(10)
             , LocalDate.now()
     );
        assets.add(asset2);

     Asset asset3=new Asset("Laptop",
             BigDecimal.valueOf(1000)
             , BigDecimal.valueOf(10)
             , LocalDate.now()
     );
        assets.add(asset3);

     Asset asset4=new Asset("Laptop",
             BigDecimal.valueOf(1000)
             , BigDecimal.valueOf(10)
             , LocalDate.now()
     );
        assets.add(asset4);

     Asset asset5=new Asset("Laptop",
             BigDecimal.valueOf(1000)
             , BigDecimal.valueOf(10)
             , LocalDate.now()
     );
        assets.add(asset5);

     Assertions.assertEquals(BigDecimal.valueOf(5000.00).setScale(2, BigDecimal.ROUND_HALF_UP),assetService.CurrentValueOfAllAssets(assets));
    }

    @Test
    public void testAddAsset() {
        SaveAssetRequest request=new SaveAssetRequest("Laptop",
                BigDecimal.valueOf(1000),
                BigDecimal.valueOf(10),
                LocalDate.now());

        Assertions.assertFalse( !assetService.save(request).isnull());

    }

    @Test
    void depreciatedValue_ReturnsCorrectValue() {

        Asset asset=new Asset( "title",BigDecimal.valueOf(1001), BigDecimal.valueOf(10),LocalDate.now().minusMonths(8));
        BigDecimal depreciatedValue = assetService.depreciatedValue(asset);

        assertEquals(BigDecimal.valueOf(936.43), depreciatedValue);
    }

    @Test
    public void testInvalidRequest_BlankDate() {
        SaveAssetRequest blankDateRequest = new SaveAssetRequest(
                "Laptop",
                BigDecimal.valueOf(1000),
                BigDecimal.valueOf(16),
                LocalDate.now().plusYears(3L)
        );

        Assertions.assertFalse( assetService.save(blankDateRequest).isnull());
    }

    /**
     * Tests the isValidRequest method of the AssetService class with a blank name.
     */

    @Test
    public void testInvalidRequest_BlankTitle() {
        SaveAssetRequest blankDateRequest = new SaveAssetRequest(
                null,
                BigDecimal.valueOf(1000),
                BigDecimal.valueOf(16),
                LocalDate.now().plusYears(3L)
        );

        Assertions.assertFalse( assetService.save(blankDateRequest).isnull());
    }

    /**
     * Tests the isValidRequest method of the AssetService class with an invalid cost.
     */

    @Test
    public void testInvalidRequest_InvalidCost() {
        SaveAssetRequest blankDateRequest = new SaveAssetRequest(
                "Laptop",
                BigDecimal.valueOf(-1),
                BigDecimal.valueOf(16),
                LocalDate.now().plusYears(3L)
        );

        Assertions.assertFalse( assetService.save(blankDateRequest).isnull());
    }

    /**
     * Tests the isValidRequest method of the AssetService class with an invalid depreciation rate.
     */

    @Test
    public void testInvalidRequest_InvalidDepreciationRate() {
        SaveAssetRequest blankDateRequest = new SaveAssetRequest(
                "Laptop",
                BigDecimal.valueOf(1000),
                BigDecimal.valueOf(-2),
                LocalDate.now().plusYears(3L)
        );

        Assertions.assertFalse( assetService.save(blankDateRequest).isnull());
    }



}
