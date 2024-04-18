package com.example.AssetTracking.persistence;

import com.example.AssetTracking.persistence.AssetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AssetRepositoryTests {

    @Autowired
    private AssetRepository subject;

    @Test
    public void testDependencyInjected(){
        assertNotNull(subject);

    }


}
