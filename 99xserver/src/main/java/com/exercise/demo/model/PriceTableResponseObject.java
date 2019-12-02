package com.exercise.demo.model;


/**
 * Created By DeshanW
 *
 * Table Response Object
 **/

public class PriceTableResponseObject {

    private String type;
    private int noOfUnits;
    private double price;
    private double setSurgePrice;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNoOfUnits() {
        return noOfUnits;
    }

    public void setNoOfUnits(int noOfUnits) {
        this.noOfUnits = noOfUnits;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSetSurgePrice() {
        return setSurgePrice;
    }

    public void setSetSurgePrice(double setSurgePrice) {
        this.setSurgePrice = setSurgePrice;
    }
}
