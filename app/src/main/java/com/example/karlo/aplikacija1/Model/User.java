package com.example.karlo.aplikacija1.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by karlo on 23.8.2018..
 */

public class User {

    @SerializedName("ID")
    private int ID;

    @SerializedName("BloodTypeID")
    private int bloodTypeID;

    @SerializedName("Email")
    private String email;

    @SerializedName("Password")
    private String password;

    @SerializedName("FirstName")
    private String firstName;

    @SerializedName("LastName")
    private String lastName;

    @SerializedName("DateOfBirth")
    private String dateOfBirth;

    @SerializedName("DateOfLastDonation")
    private String dateOfLastDonation;

    public User(int ID, int bloodTypeID, String email, String password, String firstName, String lastName, String dateOfBirth, String dateOfLastDonation) {
        this.ID = ID;
        this.bloodTypeID = bloodTypeID;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.dateOfLastDonation = dateOfLastDonation;
    }

    public int getID() {
        return ID;
    }

    public int getBloodTypeID() {
        return bloodTypeID;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth.substring(0, 10);
    }

    public String getDateOfLastDonation() {
        return dateOfLastDonation.substring(0, 10);
    }
}
