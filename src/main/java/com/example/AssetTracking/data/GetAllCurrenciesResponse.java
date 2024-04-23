package com.example.AssetTracking.data;

import com.example.AssetTracking.core.InputCurrency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetAllCurrenciesResponse {

    private final List<InputCurrency> currencies;

    public GetAllCurrenciesResponse() {
        this.currencies = Arrays.asList(InputCurrency.values());
    }

    public List<InputCurrency> getCurrencies() {
        return new ArrayList<>(currencies);
    }
}
