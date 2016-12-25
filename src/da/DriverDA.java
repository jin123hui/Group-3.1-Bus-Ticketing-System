/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import domain.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author CJH
 */
public class DriverDA {
    
    private String host = "jdbc:derby://localhost:1527/CJHDB";
    private String user = "cjh";
    private String password = "cjh";
    private String tableName = "DRIVER";
    private Connection conn;
    private PreparedStatement stmt;
    
    public DriverDA() {
        createConnection();       
    }
    
    public Driver getRecord(String driverID) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE driver_id = ?";
        Driver driver = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, driverID);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                driver = new Driver(driverID, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getString(7).charAt(0));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        return driver;
    }
    public ArrayList<Driver> getAllRecord() {
        String queryStr = "SELECT * FROM " + tableName;
        ArrayList<Driver> driver = new ArrayList<Driver>();
        try {
            stmt = conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                driver.add(new Driver(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), Double.parseDouble(rs.getString(6)), rs.getString(7).charAt(0)));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        return driver;
    }

    public void insertRecord(Driver driver) {
        String insertStr = "INSERT INTO " + tableName + " VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            stmt = conn.prepareStatement(insertStr);
            stmt.setString(1, driver.getDriverID());
            stmt.setString(2, driver.getDriverName());
            stmt.setString(3, driver.getAddress());
            stmt.setString(4, driver.getContactNo());
            stmt.setString(5, driver.getIcNo());
            stmt.setString(6, Double.toString(driver.getSalary()));
            stmt.setString(7, Character.toString(driver.getStatus()));
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "New Driver record had been added");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Driver lastID(char staffType) //throws SQLException
    {
        Driver driver = null;
        String queryStr = "SELECT * FROM " + tableName + " WHERE DRIVER_ID LIKE '" + staffType + "%' ORDER BY DRIVER_ID DESC";
        try {
            stmt = conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                driver = new Driver(rs.getString(1));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return driver;
    }

    public void updateRecord(Driver driver) {
        String insertStr1 = "UPDATE " + tableName + " SET ADDRESS = ?, CONTACT_NO = ?, SALARY = ? WHERE driver_id = ?";
        try {
            stmt = conn.prepareStatement(insertStr1);
            stmt.setString(1, driver.getAddress());
            stmt.setString(2, driver.getContactNo());
            stmt.setString(3, Double.toString(driver.getSalary()));
            stmt.setString(4, driver.getDriverID());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Driver (" + driver.getDriverName() + ") record had been updated");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void ActivateOrDeavtivate(Driver driver) {
        String insertStr1 = "UPDATE " + tableName + " SET STATUS=? WHERE driver_ID= ?";
        if (driver.getStatus() == 'A') {
            try {
                stmt = conn.prepareStatement(insertStr1);
                stmt.setString(1, Character.toString(driver.getStatus()));
                stmt.setString(2, driver.getDriverID());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Record had been activated");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            try {
                stmt = conn.prepareStatement(insertStr1);
                stmt.setString(1, Character.toString(driver.getStatus()));
                stmt.setString(2, driver.getDriverID());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Record had been deactivated");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public ArrayList<Driver> searchByName(String name) {
        
        String queryStr = "SELECT * FROM " + tableName + " WHERE driver_name = ? ORDER BY driver_id";
        ArrayList<Driver> memberList = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                memberList.add(new Driver(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6),rs.getString(7).charAt(0)));
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);           
        }
        return memberList;
    }
    public Driver searchByIC(String ic) {
        
        String queryStr = "SELECT * FROM " + tableName + " WHERE icno = ? ORDER BY driver_id";
        Driver memberList =null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, ic);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
               memberList=new Driver(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6),rs.getString(7).charAt(0));
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);           
        }
        return memberList;
    }
    public ArrayList<Driver> searchByStatus(String status) {
        
        String queryStr = "SELECT * FROM " + tableName + " WHERE status = ? ORDER BY driver_id";
        ArrayList<Driver> memberList = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
               memberList.add(new Driver(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6),rs.getString(7).charAt(0)));
             }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);           
        }
        return memberList;
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
