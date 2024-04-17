package com.example.AssetTracking.persistence;

import com.example.AssetTracking.Domain.Asset;
import com.example.AssetTracking.Persistence.AssetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AssetRepositoryTests {

    @Autowired
    private AssetRepository subject;

    @Test
    public void testDependencyInjected(){
        assertNotNull(subject);

    }

    @Mock
    private AssetRepository mockAssetRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSize(){
        List<Asset> assets = Arrays.asList(
                new Asset("Asset 1", BigDecimal.valueOf(1000),BigDecimal.valueOf(5), LocalDate.now()),
                new Asset("Asset 2", BigDecimal.valueOf(2000),BigDecimal.valueOf(10), LocalDate.now())
        );
        when(mockAssetRepository.findAll()).thenReturn(assets);

        Long size = mockAssetRepository.size();
        assertEquals(2L, size);
    }
}
