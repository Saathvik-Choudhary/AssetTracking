package com.example.AssetTracking.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class GetAllAssetsResponse {
    private Collection<AssetSummary> record;

    public void addAsset(AssetSummary assetSummary){
        if (record == null) {
            record = new ArrayList<>();
        }

        record.add(assetSummary);
    }

    @JsonProperty
    public Collection<AssetSummary> getRecord() {
        return Collections.unmodifiableCollection(record);
    }
}
