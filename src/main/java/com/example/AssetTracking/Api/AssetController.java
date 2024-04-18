package com.example.AssetTracking.Api;

import com.example.AssetTracking.Core.AssetService;
import com.example.AssetTracking.Data.AssetSummary;
import com.example.AssetTracking.Data.GetAllAssetSummaryResponse;
import com.example.AssetTracking.Data.GetAllAssetsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/assets")
public class AssetController {

    @Autowired
    AssetService assetService;

    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity<GetAllAssetsResponse> findAll() {
        return ResponseEntity.ok(assetService.getAllAssets());
    }

    @CrossOrigin
    @PostMapping("/save")
    public void save(@RequestBody AssetSummary request){
        assetService.save(request);
    }
    @CrossOrigin
    @GetMapping("/assetSummary")
    public ResponseEntity<GetAllAssetSummaryResponse> assetSummary(){
        return ResponseEntity.ok(assetService.getAssetSummary());
    }


}
