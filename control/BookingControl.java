/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import da.BookingDA;
import domain.Booking;
import domain.Customer;
import domain.Staff;
import java.util.ArrayList;

/**
 *
 * @author Student
 */
public class BookingControl {
    
    private BookingDA database;
    
    public BookingControl() {
        database = new BookingDA();
    }
    
    public Booking selectRecord(String bookingID) {
        return database.getRecord(bookingID);
    }
    
    public Booking selectLatestRecord() {
        return database.getLatestRecord();
    }
    
    public ArrayList<Booking> selectAllRecord() {
        return database.getAllRecord();
    }
    
    public ArrayList<Booking> selectRecordByStaff(Staff staff) {
        return database.getRecordByStaff(staff);
    }
    
    public ArrayList<Booking> selectRecordByCustomer(Customer customer) {
        return database.getRecordByCustomer(customer);
    }
    
    public ArrayList<Booking> selectRecordByTime(String time) {
        return database.getRecordByTime(time);
    }
    
    public ArrayList<Booking> selectRecordByTimeTime(String aTime, String bTime) {
        return database.getRecordByTimeTime(aTime, bTime);
    }
    
    public ArrayList<Booking> selectRecordByDate(String date) {
        return database.getRecordByDate(date);
    }
    
    public ArrayList<Booking> selectRecordByDateDate(String date1, String date2) {
        return database.getRecordByDateDate(date1, date2);
    }
    
    public ArrayList<Booking> selectRecordByDateTime(String date, String time) {
        return database.getRecordByDateTime(date, time);
    }
    
    public ArrayList<Booking> selectRecordByDateTimeDateTime(String date1, String aTime, String date2, String bTime) {
        return database.getRecordByDateTimeDateTime(date1, aTime, date2, bTime);
    }
    
    public void insertRecord(Booking booking) {
        database.addRecord(booking);
    }
}
