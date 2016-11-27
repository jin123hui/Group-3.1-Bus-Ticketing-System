/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import da.BusTypeDA;
import domain.BusType;
import java.util.ArrayList;

/**
 *
 * @author CJH
 */
public class BusTypeControl {

    private BusTypeDA database;

    public BusTypeControl() {
        database = new BusTypeDA();
    }

    public BusType selectRecord(String busTypeID) {

        return database.getRecord(busTypeID);
    }

    public BusType selectRecordBasedOnType(String type) {

        return database.getBusID(type);
    }

    public ArrayList<BusType> getAllRecord() {
        return database.getAllRecord();
    }
}
