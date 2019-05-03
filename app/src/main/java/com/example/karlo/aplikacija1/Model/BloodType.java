package com.example.karlo.aplikacija1.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by karlo on 14.8.2018..
 */


public class BloodType {

    @SerializedName("ID")
    private int ID;

    @SerializedName("BloodUnitID")
    private int bloodUnitID;

    @SerializedName("Type")
    private String type;


    public BloodType(int ID, int bloodUnitID, String type) {
        this.ID = ID;
        this.bloodUnitID = bloodUnitID;
        this.type = type;
    }

    public BloodType(BloodType bloodType) {
        this.ID = bloodType.getID();
        this.bloodUnitID = bloodType.getBloodUnitID();
        this.type = bloodType.getType();
    }

    public int getID() {
        return ID;
    }

    public int getBloodUnitID() {
        return bloodUnitID;
    }

    public String getType() {
        return type;
    }

}
