package com.example.AssetTracking.persistence;

import com.example.AssetTracking.domain.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

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
        LocalDate purchaseDate = LocalDate.now().minusYears(1);
        Random random = new Random();

        for (int i = 1; i <= 10; i++) {
            purchaseDate = purchaseDate.minusDays(random.nextInt(10));
            assetRepository.save(new Asset("title" + i,
                    BigDecimal.valueOf(i),
                    BigDecimal.valueOf(10),
                    purchaseDate
            ));
        }
    }

    @Override
    public void run(String... args) throws Exception {
        populateAssets();
    }
}
