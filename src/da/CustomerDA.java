/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import domain.Customer;
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
public class CustomerDA {
    
    private String host = "jdbc:derby://localhost:1527/CJHDB";
    private String user = "cjh";
    private String password = "cjh";
    private String tableName = "CUSTOMER";
    private Connection conn;
    private PreparedStatement stmt;
    
    public CustomerDA() {
        createConnection();
    }
    
    public Customer getRecord(String custID) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE cust_id = ?";
        Customer customer = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, custID);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                if(rs.getString(6)==null&&rs.getString(5)==null) {
                    customer = new Customer(custID, rs.getString(2), rs.getString(3), rs.getString(4));            
                } else {
                    customer = new Customer(custID, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5).charAt(0), rs.getInt(6)); 
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        return customer;
    }
    
    public Customer getRecordByNameContactICNo(String custName, String contactNo, String ICNo) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE cust_name = ? AND contact_no = ? AND icno = ?";
        Customer customer = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, custName);
            stmt.setString(2, contactNo);
            stmt.setString(3, ICNo);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                if(rs.getString(6)==null&&rs.getString(5)==null) {
                    customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));            
                } else {
                    customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5).charAt(0), rs.getInt(6)); 
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        return customer;
    }
    
    public ArrayList<Customer> getRecordByName(String custName) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE cust_name = ?";
        ArrayList<Customer> customerList = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, custName);           
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                if(rs.getString(6)==null&&rs.getString(5)==null) {
                    customerList.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));            
                } else {
                    customerList.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5).charAt(0), rs.getInt(6))); 
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        return customerList;
    }
    
    public ArrayList<Customer> getRecordByContactNo(String contact) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE contact_no = ?";
        ArrayList<Customer> customerList = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, contact);           
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                if(rs.getString(6)==null&&rs.getString(5)==null) {
                    customerList.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));            
                } else {
                    customerList.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5).charAt(0), rs.getInt(6))); 
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        return customerList;
    }
    
    public Customer getRecordByIC(String icno) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE icno = ?";
        Customer customer = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, icno);           
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                if(rs.getString(6)==null&&rs.getString(5)==null) {
                    customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));            
                } else {
                    customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5).charAt(0), rs.getInt(6)); 
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }        
        
        return customer;
    }
    
    public ArrayList<Customer> getRecordByPoint(String point) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE point = ?";
        ArrayList<Customer> customerList = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, point);           
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                if(rs.getString(6)==null&&rs.getString(5)==null) {
                    customerList.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));            
                } else {
                    customerList.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5).charAt(0), rs.getInt(6))); 
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        return customerList;
    }
    
    public Customer getLatestRecord() {
        
        String queryStr = "SELECT * FROM " + tableName + " ORDER BY cust_id DESC";
        Customer customer = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()) {
                if(rs.getString(6)==null&&rs.getString(5)==null) {
                    customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));            
                } else {
                    customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5).charAt(0), rs.getInt(6)); 
                }             
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);       
        }
        return customer;
    }
    
    public ArrayList<Customer> getAllRecord() {
        
        String queryStr = "SELECT * FROM " + tableName + " ORDER BY cust_id";
        ArrayList<Customer> customerList = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                if(rs.getString(6)==null&&rs.getString(5)==null) {
                    customerList.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));            
                } else {
                    customerList.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5).charAt(0), rs.getInt(6))); 
                }                           
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);           
        }
        return customerList;
    }
    
    public ArrayList<Customer> getAllMemberRecord() {
        
        String queryStr = "SELECT * FROM " + tableName + " WHERE status = ? ORDER BY cust_id";
        ArrayList<Customer> customerList = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, "M");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {                
                customerList.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5).charAt(0), rs.getInt(6)));                                           
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);           
        }
        return customerList;
    }
    
    public void addRecord(Customer customer) {
        String queryStr = "INSERT INTO " + tableName + " VALUES (?, ?, ?, ?, ?, ?)";
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, customer.getCustID());            
            stmt.setString(2, customer.getCustName());
            stmt.setString(3, customer.getContactNo());
            stmt.setString(4, customer.getIcNo());
            if(customer.getStatus()==' ') {
                stmt.setString(5, null);
                stmt.setString(6, null);
            } else {
                stmt.setString(5, customer.getStatus()+"");
                stmt.setString(6, Integer.toString(customer.getPoint()));
            }           
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }    
    }
    
    public void addPoint(Customer customer, int point) {
        
        String queryRecord = "UPDATE " + tableName + " SET point = ? WHERE cust_id = ?";
        try {
            stmt = conn.prepareStatement(queryRecord);
            stmt.setString(1, Integer.toString(customer.getPoint() + point));
            stmt.setString(2, customer.getCustID());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void deducePoint(Customer customer, int point) {
        
        String queryRecord = "UPDATE " + tableName + " SET point = ? WHERE cust_id = ?";
        try {
            stmt = conn.prepareStatement(queryRecord);
            stmt.setString(1, Integer.toString(point));
            stmt.setString(2, customer.getCustID());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void updateRecord(Customer cust) {
        
        String queryRecord = "UPDATE " + tableName + " SET cust_name = ?, contact_no = ?, icno = ? WHERE cust_id = ?";
        try {
            stmt = conn.prepareStatement(queryRecord);
            stmt.setString(1, cust.getCustName());
            stmt.setString(2, cust.getContactNo());
            stmt.setString(3, cust.getIcNo());
            stmt.setString(4, cust.getCustID());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void updateMemberRecord(Customer cust) {
        
        String queryRecord = "UPDATE " + tableName + " SET cust_name = ?, contact_no = ?, icno = ?, point = ? WHERE cust_id = ?";
        try {
            stmt = conn.prepareStatement(queryRecord);
            stmt.setString(1, cust.getCustName());
            stmt.setString(2, cust.getContactNo());
            stmt.setString(3, cust.getIcNo());
            stmt.setString(4, Integer.toString(cust.getPoint()));
            stmt.setString(5, cust.getCustID());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void custToMem(Customer cust) {
        String queryRecord = "UPDATE " + tableName + " SET status = ?, point = ? WHERE cust_id = ?";
        try {
            stmt = conn.prepareStatement(queryRecord);
            stmt.setString(1, "M");
            stmt.setString(2, "0");
            stmt.setString(3, cust.getCustID());           
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
