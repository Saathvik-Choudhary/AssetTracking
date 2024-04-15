package com.example.AssetTracking.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Create a summary for asset management.
 */
public class AssetSummary {
    private final BigDecimal cost;

    private final BigDecimal depreciationRate;

    private final LocalDate purchaseDate;

    private final String title;

    public AssetSummary(BigDecimal cost
            , BigDecimal depreciationRate
            , LocalDate purchaseDate
            , String title) {
        this.cost = cost;
        this.depreciationRate = depreciationRate;
        this.purchaseDate = purchaseDate;
        this.title = title;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public BigDecimal getDepreciationRate() {
        return depreciationRate;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public String getTitle() {
        return title;
    }
}
