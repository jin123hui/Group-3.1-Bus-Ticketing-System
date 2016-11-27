/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import da.PriceDA;
import domain.Price;
import java.util.ArrayList;

/**
 *
 * @author CJH
 */
public class PriceControl {

    private PriceDA database;

    public PriceControl() {
        database = new PriceDA();
    }

    public Price selectRecord(String priceID) {
        return database.getRecord(priceID);
    }

    public void insertRecord(Price price) {
        database.insertRecord(price);
    }

    public ArrayList<Price> getAllRecord() {
        return database.getAllRecord();
    }

    public ArrayList<Price> searchByRange(String str1, String str2) {
        return database.searchByRange(str1, str2);
    }
}
