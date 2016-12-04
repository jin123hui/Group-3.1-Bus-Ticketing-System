/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import control.BookingControl;
import control.ScheduleControl;
import domain.Booking;
import domain.Schedule;
import domain.Ticket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Student
 */
public class TicketDA {
    
    private String host = "jdbc:derby://localhost:1527/CJHDB";
    private String user = "cjh";
    private String password = "cjh";
    private String tableName = "TICKET";
    private Connection conn;
    private PreparedStatement stmt;
    private BookingControl bControl;
    private ScheduleControl sControl;
    
    public TicketDA() {
        createConnection();
        bControl = new BookingControl();
        sControl = new ScheduleControl();
    }
    
    public Ticket getRecord(String ticketID) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE ticket_id = ?";
        Ticket ticket = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, ticketID);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                ticket = new Ticket(ticketID, bControl.selectRecord(rs.getString(2)), sControl.selectRecord(rs.getString(3)), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7).charAt(0));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        return ticket;
    }
    
    public ArrayList<Ticket> getRecordBySchedule(Schedule schedule) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE schedule_id = ?";
        ArrayList<Ticket> ticketList = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, schedule.getScheduleID());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {           
                ticketList.add(new Ticket(rs.getString(1), bControl.selectRecord(rs.getString(2)), schedule, rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7).charAt(0)));
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);           
        }
        return ticketList;
    }
    
    public Ticket getRecordByScheduleSeat(Schedule schedule, int seatNo) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE schedule_id = ? AND seat_no = ?";
        Ticket ticket = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, schedule.getScheduleID());
            stmt.setString(2, seatNo+"");
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {           
                ticket = new Ticket(rs.getString(1), bControl.selectRecord(rs.getString(2)), schedule, rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7).charAt(0));
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);           
        }
        return ticket;
    }
    
    public ArrayList<Ticket> getRecordByBooking(Booking booking) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE booking_id = ?";
        ArrayList<Ticket> ticketList = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, booking.getBookingID());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {           
                ticketList.add(new Ticket(rs.getString(1), booking, sControl.selectRecord(rs.getString(3)), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7).charAt(0)));
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);           
        }
        return ticketList;
    }
    
    public Ticket getLatestRecord() {
        
        String queryStr = "SELECT * FROM " + tableName + " ORDER BY ticket_id DESC";
        Ticket ticket = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()) {
                ticket = new Ticket(rs.getString(1), bControl.selectRecord(rs.getString(2)), sControl.selectRecord(rs.getString(3)), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7).charAt(0));                  
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);       
        }
        return ticket;
    }
    
    public ArrayList<Booking> getTicketBookingRecord(Schedule schedule) {
        
        String queryStr = "SELECT booking_id FROM " + tableName + " WHERE schedule_id = ? GROUP BY booking_id";
        ArrayList<Booking> bookingList = new ArrayList<>();
        try{
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, schedule.getScheduleID());
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                bookingList.add(bControl.selectRecord(rs.getString(1)));
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);       
        }
        return bookingList;     
    }
    
    public ArrayList<Ticket> getAllRecord() {
        
        String queryStr = "SELECT * FROM " + tableName;
        ArrayList<Ticket> ticketList = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                ticketList.add(new Ticket(rs.getString(1), bControl.selectRecord(rs.getString(2)), sControl.selectRecord(rs.getString(3)), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7).charAt(0)));                  
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);       
        }
        return ticketList;
    }
    
    public void addRecord(Ticket ticket) {
        String queryStr = "INSERT INTO " + tableName + " VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, ticket.getTicketID());            
            stmt.setString(2, ticket.getBooking().getBookingID());
            stmt.setString(3, ticket.getSchedule().getScheduleID());
            stmt.setString(4, ticket.getSeatNo());
            stmt.setString(5, ticket.getPassengerName()); 
            stmt.setString(6, ticket.getPassengerContactNo());
            stmt.setString(7, ticket.getStatus()+"");
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }    
    }
    
    public void deactiveRecordStatus(Ticket ticket) {
        String queryRecord = "UPDATE " + tableName + " SET status = ? WHERE ticket_id = ?";
        try {
            stmt = conn.prepareStatement(queryRecord);
            stmt.setString(1, "C");
            stmt.setString(2, ticket.getTicketID());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void createConnection() {
        try {
            conn = DriverManager.getConnection(host, user, password);
            System.out.println("***TRACE: Connection established.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
