/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import da.TicketDA;
import domain.Booking;
import domain.Schedule;
import domain.Ticket;
import java.util.ArrayList;

/**
 *
 * @author Student
 */
public class TicketControl {
    
    private TicketDA database;
    
    public TicketControl() {
        database = new TicketDA();
    }
    
    public Ticket selectRecord(String ticketID) {
        return database.getRecord(ticketID);
    }
    
    public ArrayList<Ticket> selectRecordBySchedule(Schedule schedule) {
        return database.getRecordBySchedule(schedule);
    }
    
    public Ticket selectRecordByScheduleSeat(Schedule schedule, int seatNo) {
        return database.getRecordByScheduleSeat(schedule, seatNo);
    }
    
    public ArrayList<Ticket> selectRecordByBooking(Booking booking) {
        return database.getRecordByBooking(booking);
    }
    
    public Ticket selectLatestRecord() {
        return database.getLatestRecord();
    }
    
    public ArrayList<Booking> selectTicketBookingRecord(Schedule schedule) {
        return database.getTicketBookingRecord(schedule);
    }
    
    public ArrayList<Ticket> selectAllRecord() {
        return database.getAllRecord();
    }
    
    public void insertRecord(Ticket ticket) {
        database.addRecord(ticket);
    }
    
    public void cancelRecordStatus(Ticket ticket) {
        database.deactiveRecordStatus(ticket);
    }
}
