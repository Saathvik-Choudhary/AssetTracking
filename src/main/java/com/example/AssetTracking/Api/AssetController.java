package com.example.AssetTracking.Api;

import com.example.AssetTracking.Core.AssetService;
import com.example.AssetTracking.Data.AssetSummary;
import com.example.AssetTracking.Data.GetAllAssetsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/assets")
public class AssetController {

    @Autowired
    AssetService assetService;

    @GetMapping("/all")
    public ResponseEntity<GetAllAssetsResponse> findall() {
        return ResponseEntity.ok(assetService.getAllAssets());
    }

    @PostMapping("/save")
    public void save(AssetSummary request){
        assetService.save(request);
    }
}
