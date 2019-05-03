package com.example.karlo.aplikacija1.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by karlo on 14.8.2018..
 */

public class Location {

    @SerializedName("ID")
    private int ID;

    @SerializedName("Address")
    private String address;

    @SerializedName("NameOfFoundation")
    private String nameOfFoundation;

    @SerializedName("DateOfAction")
    private String dateOfAction;

    @SerializedName("TimeFrom")
    private String timeFrom;

    @SerializedName("TimeTo")
    private String timeTo;


    public Location(int ID, String address, String nameOfFoundation, String dateOfAction, String timeFrom, String timeTo) {
        this.ID = ID;
        this.address = address;
        this.nameOfFoundation = nameOfFoundation;
        this.dateOfAction = dateOfAction;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        //"TimeTo": "14.08.2018 07:00",
//        this.dateOfAction = timeFrom.substring(0, 9);
//        this.timeFrom = timeFrom.substring(11, 15);
//        this.timeTo = timeFrom.substring(11, 15);
//        try {
//            Log.d("prije", timeFrom.toString());
////            timeFrom.toString().replace("T", " ");
////            Log.d("poslije", timeFrom.toString());
//            this.timeFrom = timeFrom.substring(0, 10);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        try {
//            Log.d("prije", timeTo.toString());
////            timeTo.toString().replace("T", " ");
////            Log.d("poslije", timeTo.toString());
//            this.timeTo = new SimpleDateFormat("HH:mm:ss").parse(timeTo.toString());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        try {
//            Log.d("prije", dateOfAction.toString());
////            dateOfAction.toString().replace("T", " ");
////            Log.d("poslije", dateOfAction.toString());
//            this.dateOfAction = new SimpleDateFormat("dd/MM/yyyy").parse(dateOfAction.toString());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

    }

    public int getID() {
        return ID;
    }

    public String getAddress() {
        return address;
    }

    public String getNameOfFoundation() {
        return nameOfFoundation;
    }

    public String getDateOfAction() {
        return dateOfAction.substring(0, 10);
    }

    public String getTimeFrom() {
        return timeFrom.substring(0, 5);
    }

    public String getTimeTo() {
        return timeTo.substring(0, 5);
    }
//
//    public String getShortTimeTo() {
//        return timeTo.substring(0, 6);
//    }
//
//    public String getShortTimeFrom() {
//        return timeFrom.substring(0, 6);
//    }
//
//    public String getShortDateOfAction() {
//        return dateOfAction.substring(0, 10);
//    }

    public String getShortTimesOfAction() {
        return getTimeFrom() + " - " + getTimeTo();
    }
}
