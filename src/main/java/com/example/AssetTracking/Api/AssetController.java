package com.example.AssetTracking.Api;

import com.example.AssetTracking.Core.AssetService;
import com.example.AssetTracking.Data.AssetSummary;
import com.example.AssetTracking.Data.GetAllAssetSummaryResponse;
import com.example.AssetTracking.Data.GetAllAssetsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/assets")
public class AssetController {

    @Autowired
    AssetService assetService;

    @GetMapping("/all")
    public ResponseEntity<GetAllAssetsResponse> findAll() {
        return ResponseEntity.ok(assetService.getAllAssets());
    }

    @PutMapping("/save")
    public void save(AssetSummary request){
        assetService.save(request);
    }
    @GetMapping("/assetSummary")
    public ResponseEntity<List<BigDecimal>> assetSummary(){
        GetAllAssetSummaryResponse response=assetService.getAssetSummary();
        List<BigDecimal> r=new ArrayList<>();
        r.add(BigDecimal.valueOf(response.getCount()));
        r.add(response.getValue());
        r.add(response.getCost());
        return ResponseEntity.ok(r);
    }


}
