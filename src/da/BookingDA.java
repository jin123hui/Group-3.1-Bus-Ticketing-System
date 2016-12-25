/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import control.CustomerControl;
import control.StaffControl;
import domain.Booking;
import domain.Customer;
import domain.Staff;
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
public class BookingDA {
    
    private String host = "jdbc:derby://localhost:1527/CJHDB";
    private String user = "cjh";
    private String password = "cjh";
    private String tableName = "BOOKING";
    private Connection conn;
    private PreparedStatement stmt;
    private StaffControl sControl;
    private CustomerControl cControl;
    
    public BookingDA() {
        createConnection();
        sControl = new StaffControl();
        cControl = new CustomerControl();
    }
    
    public Booking getRecord(String bookingID) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE booking_id = ?";
        Booking booking = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, bookingID);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Customer customer = null;
                if(cControl.selectRecord(rs.getString(3))!=null) {
                    customer = cControl.selectRecord(rs.getString(3));
                    booking = new Booking(bookingID, sControl.selectRecord(rs.getString(2)), customer, rs.getString(4), rs.getString(5));
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        return booking;
    }
    
    public Booking getLatestRecord() {
        
        String queryStr = "SELECT * FROM " + tableName + " ORDER BY booking_id DESC";
        Booking booking = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()) {
                booking = new Booking(rs.getString(1), sControl.selectRecord(rs.getString(2)), cControl.selectRecord(rs.getString(3)), rs.getString(4), rs.getString(5));                  
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);       
        }
        return booking;
    }
    
    public ArrayList<Booking> getAllRecord() {
        
        String queryStr = "SELECT * FROM " + tableName + " ORDER BY booking_id";
        ArrayList<Booking> bookingList = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {               
                Customer customer = null;
                if(cControl.selectRecord(rs.getString(3))!=null) {
                    customer = cControl.selectRecord(rs.getString(3));
                    bookingList.add(new Booking(rs.getString(1), sControl.selectRecord(rs.getString(2)), customer, rs.getString(4), rs.getString(5)));
                }
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);           
        }
        return bookingList;
    }
    
    public ArrayList<Booking> getRecordByStaff(Staff staff) {
        
        String queryStr = "SELECT * FROM " + tableName + " WHERE staff_id = ?";
        ArrayList<Booking> bookingList = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, staff.getStaffID());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {               
                Customer customer = null;
                if(cControl.selectRecord(rs.getString(3))!=null) {
                    customer = cControl.selectRecord(rs.getString(3));
                    bookingList.add(new Booking(rs.getString(1), sControl.selectRecord(rs.getString(2)), customer, rs.getString(4), rs.getString(5)));
                }
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);           
        }
        return bookingList;
    }
    
    public ArrayList<Booking> getRecordByCustomer(Customer customer) {
        
        String queryStr = "SELECT * FROM " + tableName + " WHERE cust_id = ?";
        ArrayList<Booking> bookingList = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, customer.getCustID());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {                              
                bookingList.add(new Booking(rs.getString(1), sControl.selectRecord(rs.getString(2)), customer, rs.getString(4), rs.getString(5)));
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);           
        }
        return bookingList;
    }
    
    public ArrayList<Booking> getRecordByTime(String time) {
        
        String queryStr = "SELECT * FROM " + tableName + " WHERE booking_time = ?";
        ArrayList<Booking> bookingList = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, time);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {                              
                Customer customer = null;
                if(cControl.selectRecord(rs.getString(3))!=null) {
                    customer = cControl.selectRecord(rs.getString(3));
                    bookingList.add(new Booking(rs.getString(1), sControl.selectRecord(rs.getString(2)), customer, rs.getString(4), rs.getString(5)));
                }
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);           
        }
        return bookingList;
    }
    
    public ArrayList<Booking> getRecordByTimeTime(String aTime, String bTime) {
        
        String queryStr = "SELECT * FROM " + tableName + " WHERE booking_time BETWEEN ? AND ?";
        ArrayList<Booking> bookingList = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, aTime);
            stmt.setString(2, bTime);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {                              
                Customer customer = null;
                if(cControl.selectRecord(rs.getString(3))!=null) {
                    customer = cControl.selectRecord(rs.getString(3));
                    bookingList.add(new Booking(rs.getString(1), sControl.selectRecord(rs.getString(2)), customer, rs.getString(4), rs.getString(5)));
                }
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);           
        }
        return bookingList;
    }
    
    public ArrayList<Booking> getRecordByDate(String date) {
        
        String queryStr = "SELECT * FROM " + tableName + " WHERE booking_date = ?";
        ArrayList<Booking> bookingList = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, date);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {                              
                Customer customer = null;
                if(cControl.selectRecord(rs.getString(3))!=null) {
                    customer = cControl.selectRecord(rs.getString(3));
                    bookingList.add(new Booking(rs.getString(1), sControl.selectRecord(rs.getString(2)), customer, rs.getString(4), rs.getString(5)));
                }
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);           
        }
        return bookingList;
    }
    
    public ArrayList<Booking> getRecordByDateDate(String date1, String date2) {
        
        String queryStr = "SELECT * FROM " + tableName + " WHERE booking_date BETWEEN ? AND ?";
        ArrayList<Booking> bookingList = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, date1);
            stmt.setString(2, date2);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {                              
                Customer customer = null;
                if(cControl.selectRecord(rs.getString(3))!=null) {
                    customer = cControl.selectRecord(rs.getString(3));
                    bookingList.add(new Booking(rs.getString(1), sControl.selectRecord(rs.getString(2)), customer, rs.getString(4), rs.getString(5)));
                }
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);           
        }
        return bookingList;
    }
    
    public ArrayList<Booking> getRecordByDateTime(String date, String time) {
        
        String queryStr = "SELECT * FROM " + tableName + " WHERE booking_date = ? AND booking_time = ?";
        ArrayList<Booking> bookingList = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, date);
            stmt.setString(2, time);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {                              
                Customer customer = null;
                if(cControl.selectRecord(rs.getString(3))!=null) {
                    customer = cControl.selectRecord(rs.getString(3));
                    bookingList.add(new Booking(rs.getString(1), sControl.selectRecord(rs.getString(2)), customer, rs.getString(4), rs.getString(5)));
                }
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);           
        }
        return bookingList;
    }
    
    public ArrayList<Booking> getRecordByDateTimeDateTime(String date1, String aTime, String date2, String bTime) {
        
        String queryStr = "SELECT * FROM " + tableName + " WHERE booking_date BETWEEN ? AND ? AND booking_time BETWEEN ? AND ?";
        ArrayList<Booking> bookingList = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, date1);
            stmt.setString(2, date2);
            stmt.setString(3, aTime);
            stmt.setString(4, bTime);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {                              
                Customer customer = null;
                if(cControl.selectRecord(rs.getString(3))!=null) {
                    customer = cControl.selectRecord(rs.getString(3));
                    bookingList.add(new Booking(rs.getString(1), sControl.selectRecord(rs.getString(2)), customer, rs.getString(4), rs.getString(5)));
                }
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);           
        }
        return bookingList;
    }
    
    public void addRecord(Booking booking) {
        String queryStr = "INSERT INTO " + tableName + " VALUES (?, ?, ?, ?, ?)";
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, booking.getBookingID());            
            stmt.setString(2, booking.getStaff().getStaffID());
            stmt.setString(3, booking.getCustomer().getCustID());
            stmt.setString(4, booking.getBookingTime());
            stmt.setString(5, booking.getBookingDate());            
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
