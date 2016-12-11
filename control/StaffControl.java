/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import da.StaffDA;
import domain.Staff;
import java.util.ArrayList;

/**
 *
 * @author Student
 */
public class StaffControl {

    private StaffDA database;

    public StaffControl() {
        database = new StaffDA();
    }

    public Staff selectRecord(String staffID) {
        return database.getRecord(staffID);
    }

    public Staff selectRecordByIC(String ic) {
        return database.getRecordByIC(ic);
    }

    public void modifyRecord(Staff staff) {
        database.updateRecord2(staff);
    }

    //Get all staff ic no

    public ArrayList<Staff> getAllStaff() {
        return database.getAllRecord();
    }

    //get all staff id

    public boolean checkStaffID(String staffID) {
        return database.checkStaffID(staffID);
    }

    public void addRecord(Staff staff) {
        database.addRecord(staff);
    }

    public void updateRecord(Staff staff) {
        database.updateRecord(staff);
    }

    public void activateOrDeactivateStaff(Staff staff) {
        database.ActivateOrDeavtivate(staff);
    }

    public ArrayList<Staff> getAllRecordForDeactivate() {
        return database.getAllRecordForDeactivate();
    }

    public ArrayList<Staff> searchByName(String name) {
        return database.searchByName(name);
    }

    public ArrayList<Staff> searchByStatus(String status) {
        return database.searchByStatus(status);
    }

    public Staff searchByIC(String ic) {
        return database.searchByIC(ic);
    }
}
