package com.example.AssetTracking.api;

import com.example.AssetTracking.core.AssetService;
import com.example.AssetTracking.data.AssetSummary;
import com.example.AssetTracking.data.GetAllAssetSummaryResponse;
import com.example.AssetTracking.data.GetAllAssetsResponse;
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
        System.out.println("put was called");
        assetService.save(request);
    }
    @CrossOrigin
    @GetMapping("/assetSummary")
    public ResponseEntity<GetAllAssetSummaryResponse> assetSummary(){
        return ResponseEntity.ok(assetService.getAssetSummary());
    }


}
