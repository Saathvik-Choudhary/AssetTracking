package com.example.AssetTracking.data;

import java.util.Objects;

public class SaveAssetResponse {

    public String[] response=new String[4];

    public SaveAssetResponse(String[] input) {
        this.response=input;
    }

    public String[] getResponse() {
        return response;
    }

    public boolean isnull(){

        for(int i=0;i<4;i++)
        {
            if(!Objects.equals(response[i], ""))
                return false;
        }

        return true;
    }

}
