package com.example.AssetTracking.Persistence;

import com.example.AssetTracking.Domain.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A utility class to populate assets.
 */
@Repository
public class AssetPopulator implements CommandLineRunner {

    @Autowired
    AssetRepository assetRepository;

    /**
     * Populate assets with sample data.
     */
    public void populateAssets() {

        LocalDate localDate = LocalDate.now();

        for (int i = 1; i <= 2; i++) {
            assetRepository.save(new Asset(BigDecimal.valueOf(i)
                    , BigDecimal.valueOf(i)
                    , localDate
                    , "title" + i));
        }
    }

    @Override
    public void run(String... args) throws Exception {
        populateAssets();
    }
}
