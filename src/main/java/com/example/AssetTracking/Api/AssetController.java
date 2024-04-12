package com.example.AssetTracking.Api;

import com.example.AssetTracking.Core.AssetService;
import com.example.AssetTracking.Data.GetAllAssetsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/assets")
public class AssetController {

    @Autowired
    AssetService assetService;

    @GetMapping("/all")
    public ResponseEntity<GetAllAssetsResponse> finall(){
        return ResponseEntity.ok(assetService.getAllAssests());
    }
}
