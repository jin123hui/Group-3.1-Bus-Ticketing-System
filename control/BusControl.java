/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import da.BusDA;
import domain.Bus;
import java.util.ArrayList;

/**
 *
 * @author CJH
 */
public class BusControl {
    
    private BusDA database;
    
    public BusControl() {
        database = new BusDA();
    }
    
    public Bus selectRecord(String busID) {
        
        return database.getRecord(busID);
    }
    public ArrayList<Bus> getAllRecord() {
        
        return database.getAllRecord();
    }
     public ArrayList<Bus> getAllRecordByStatus(String status) {
        
        return database.getAllRecordByStatus(status);
    }
    public void insertRecord(Bus bus){
        database.insertRecord(bus);
    }
    public void updateRecord(Bus bus,char status){
        database.updateRecord(bus,status);
    }
}
