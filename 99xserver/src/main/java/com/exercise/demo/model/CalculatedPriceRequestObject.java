package com.exercise.demo.model;

/**
 * Created By DeshanW
 *
 * CalculatedPriceRequestObject
 **/

public class CalculatedPriceRequestObject {

    private int  qty;
    private String product;

    private boolean qtyType;

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String produuct) {
        this.product = produuct;
    }

    public boolean isQtyType() {
        return qtyType;
    }

    public void setQtyType(boolean qtyType) {
        this.qtyType = qtyType;
    }


}
