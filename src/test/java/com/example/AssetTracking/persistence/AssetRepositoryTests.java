package com.example.AssetTracking.persistence;

import com.example.AssetTracking.Domain.Asset;
import com.example.AssetTracking.Persistence.AssetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AssetRepositoryTests {

    @Autowired
    private AssetRepository subject;

    @Test
    public void testDependencyInjected(){
        assertNotNull(subject);

    }
    @Mock
    private AssetRepository mockAssetRepository;

    private Asset asset1;
    private Asset asset2;

    @BeforeEach
    public void setUp(){
        asset1 = new Asset();
        asset1.setTitle("Asset 1");
        asset1.setCost(BigDecimal.valueOf(1000));
        asset1.setDepreciationRate(BigDecimal.valueOf(5));
        asset1.setPurchaseDate(LocalDate.now().minusYears(1));


        asset2 = new Asset();
        asset2.setTitle("Asset 2");
        asset2.setCost(BigDecimal.valueOf(2000));
        asset2.setDepreciationRate(BigDecimal.valueOf(2));
        asset2.setPurchaseDate(LocalDate.now().minusYears(4));

        when(mockAssetRepository.findAll()).thenReturn(Arrays.asList(asset1,asset2));
        mockAssetRepository.saveAll(Arrays.asList(asset1,asset2));
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSize(){
        long size = mockAssetRepository.size();
        assertEquals(2,size);
    }


}
