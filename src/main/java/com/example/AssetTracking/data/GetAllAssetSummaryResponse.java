package com.example.AssetTracking.data;

import java.math.BigDecimal;

public class GetAllAssetSummaryResponse {

    private final Long count;

    private final BigDecimal cost;

    private final BigDecimal value;

    public GetAllAssetSummaryResponse(Long count, BigDecimal cost, BigDecimal value) {
        this.count = count;
        this.cost = cost;
        this.value = value;
    }

    public Long getCount() {
        return count;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public BigDecimal getValue() {
        return value;
    }
}
