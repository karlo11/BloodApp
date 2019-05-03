package com.example.karlo.aplikacija1.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by karlo on 19.8.2018..
 */

public class BloodUnit {

    @SerializedName("ID")
    private int ID;

    @SerializedName("NumberOfUnits")
    private int numberOfUnits;


    public BloodUnit(int ID, int numberOfUnits) {
        this.ID = ID;
        this.numberOfUnits = numberOfUnits;
    }

    public int getID() {
        return ID;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }
}
