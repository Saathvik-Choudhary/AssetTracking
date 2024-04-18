package com.example.AssetTracking.core;

import com.example.AssetTracking.domain.Asset;

import java.time.LocalDate;
import java.util.Comparator;

public class AssetSortComparator implements Comparator<Asset> {

    @Override
    public int compare(Asset obj1, Asset obj2) {
        // Implement your comparison logic here
        // This example compares two objects by their string value
        return compareTo(obj2.getPurchaseDate(),obj1.getPurchaseDate());
    }

    public int compareTo(LocalDate date1,LocalDate date2) {

        if (date1.isBefore(date2)) {
            return 1;
        } else if (date1.isEqual(date2)) {
            return 0;
        }
        return -1;
    }
}
