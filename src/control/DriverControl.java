/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import da.DriverDA;
import domain.Driver;
import java.util.ArrayList;

/**
 *
 * @author CJH
 */
public class DriverControl {

    private DriverDA database;

    public DriverControl() {
        database = new DriverDA();
    }

    public Driver selectRecord(String driverID) {

        return database.getRecord(driverID);
    }

    public void insetRecord(Driver driver) {
        database.insertRecord(driver);
    }

    public void updateRecord(Driver driver) {
        database.updateRecord(driver);
    }

    public ArrayList<Driver> getAllRecord() {
        return database.getAllRecord();
    }

    public void activateOrDeactivateStaff(Driver driver) {
        database.ActivateOrDeavtivate(driver);
    }

    public ArrayList<Driver> searchByName(String name) {
        return database.searchByName(name);
    }

    public ArrayList<Driver> searchByStatus(String status) {
        return database.searchByStatus(status);
    }

    public Driver searchByIC(String ic) {
        return database.searchByIC(ic);
    }
}
