package com.example.AssetTracking.Persistence;

import com.example.AssetTracking.Domain.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public class AssetPopulator implements CommandLineRunner {

    @Autowired
    AssetRepository assetRepository;

    public void populateAssests(){

        LocalDate localDate=LocalDate.now();

        for(int i=1;i<=100;i++) {
            assetRepository.save(new Asset(BigDecimal.valueOf(i), BigDecimal.valueOf(i), localDate, "title"+i));
        }
    }

    @Override
    public void run(String... args) throws Exception {
        populateAssests();
    }
}
