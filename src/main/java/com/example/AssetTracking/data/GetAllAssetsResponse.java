package com.example.AssetTracking.data;

import com.example.AssetTracking.data.AssetSummary;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Create a response containing a collection of asset summaries.
 */
public class GetAllAssetsResponse {
    private Collection<AssetSummary> record;

    /**
     * Adds an asset summary to the response.
     *
     * @param assetSummary The asset summary to add.
     */
    public void addAsset(AssetSummary assetSummary) {
        if (record == null) {
            record = new ArrayList<>();
        }

        record.add(assetSummary);
    }

    /**
     * Get the collection of asset summaries.
     *
     * @return Unmodifiable collection of asset summaries.
     */
    @JsonProperty
    public Collection<AssetSummary> getRecord() {
        return Collections.unmodifiableCollection(record);
    }
}
