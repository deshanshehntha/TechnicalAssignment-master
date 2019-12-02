package com.exercise.demo.service;

import com.exercise.demo.model.CalculatedPriceRequestObject;
import com.exercise.demo.model.PriceTableResponseObject;
import com.exercise.demo.model.Product;
import com.exercise.demo.util.Constants;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * Common Calculator Class
 * <p>
 * Independent From Products
 */

public class PriceGenerator {


    public ArrayList<PriceTableResponseObject> generatePriceList(Product product) {
        ArrayList<PriceTableResponseObject> priceMap = new ArrayList<>();
        product.setSingleUnitPrice(getSingleUnitPrice(product.getUnitsPerCartoon(), product.getPricePerCartoon()));
        for (int i = 1; i <= 50; i++) {
            priceMap.add(generatePrice(i, product));
        }
        return priceMap;
    }

    private PriceTableResponseObject generatePrice(int qty, Product product) {
        if ((qty % product.getUnitsPerCartoon()) == 0) {
            int noOfCartoons = qty / product.getUnitsPerCartoon();
            PriceTableResponseObject obj = new PriceTableResponseObject();
            obj.setType(product.getProductName());
            obj.setNoOfUnits(qty);
            if (noOfCartoons > 3) {
                obj.setPrice(noOfCartoons * (product.getPricePerCartoon() * 0.9));
                obj.setSetSurgePrice((noOfCartoons * (product.getPricePerCartoon() * 0.9)) - (noOfCartoons * product.getSingleUnitPrice()));

            } else {
                obj.setPrice(noOfCartoons * product.getPricePerCartoon());
                obj.setSetSurgePrice((noOfCartoons * (product.getPricePerCartoon())) - (noOfCartoons * product.getSingleUnitPrice()));

            }
            return obj;
        } else {
            int noOfCartoons = (int) qty / product.getUnitsPerCartoon();
            int noOfSingleUnits = qty - noOfCartoons * product.getUnitsPerCartoon();
            double totalPrice;
            if (noOfCartoons > 3) {
                totalPrice = (noOfCartoons * (product.getPricePerCartoon() * 0.9) + (
                        noOfSingleUnits * product.getSingleUnitPrice()));
            } else {
                totalPrice = (noOfCartoons * product.getPricePerCartoon()) + (
                        noOfSingleUnits * product.getSingleUnitPrice());
            }
            PriceTableResponseObject obj = new PriceTableResponseObject();
            obj.setType(product.getProductName());
            obj.setNoOfUnits(qty);
            obj.setPrice(totalPrice);
            obj.setSetSurgePrice(totalPrice - noOfSingleUnits * product.getSingleUnitPrice());
            return obj;
        }
    }


    public PriceTableResponseObject getCalculatedPrice(CalculatedPriceRequestObject obj, Product product) {
        PriceTableResponseObject priceTableResponseObject = new PriceTableResponseObject();
        if (obj.isQtyType()) {
            if (obj.getQty() > 3) {
                BigDecimal total = new BigDecimal(obj.getQty()).multiply(BigDecimal.valueOf(product.getPricePerCartoon())).multiply(BigDecimal.valueOf(0.9));
                priceTableResponseObject.setPrice(total.doubleValue());
                BigDecimal realPrice = new BigDecimal(product.getPricePerCartoon()).multiply(BigDecimal.valueOf(product.getUnitsPerCartoon())).multiply(BigDecimal.valueOf(obj.getQty()));
                priceTableResponseObject.setSetSurgePrice(total.doubleValue() - realPrice.doubleValue());
                return priceTableResponseObject;
            } else {
                BigDecimal total = new BigDecimal(obj.getQty()).multiply(BigDecimal.valueOf(product.getPricePerCartoon()));
                priceTableResponseObject.setPrice(total.doubleValue());
                BigDecimal realPrice = new BigDecimal(product.getPricePerCartoon()).multiply(BigDecimal.valueOf(product.getUnitsPerCartoon())).multiply(BigDecimal.valueOf(obj.getQty()));
                priceTableResponseObject.setSetSurgePrice(total.doubleValue() - realPrice.doubleValue());
                return priceTableResponseObject;
            }
        } else {
            return generatePrice(obj.getQty(), product);
        }
    }




        private double getSingleUnitPrice(int units, double cartoonPrice){
            BigDecimal getSingleUnitPrices = new BigDecimal(cartoonPrice);
            getSingleUnitPrices = BigDecimal.valueOf(getSingleUnitPrices.multiply(BigDecimal.valueOf(1.3)).divide(BigDecimal.valueOf(units), 3, RoundingMode.CEILING).doubleValue());
            return getSingleUnitPrices.doubleValue();

        }
    }



