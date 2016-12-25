/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

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
public class StaffDA {
    
    private String host = "jdbc:derby://localhost:1527/CJHDB";
    private String user = "cjh";
    private String password = "cjh";
    private String tableName = "STAFF";
    private Connection conn;
    private PreparedStatement stmt;
    
    public StaffDA() {
        createConnection();
    }
    
    public Staff getRecord(String staffID) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE staff_id = ?";
        Staff staff = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, staffID);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                staff = new Staff(staffID, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getString(7), rs.getString(8).charAt(0));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        return staff;
    }
    
    public Staff getRecordByIC(String ic) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE icno = ?";
        Staff staff = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, ic);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                staff = new Staff(ic, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getString(7), rs.getString(8).charAt(0));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        return staff;
    }
    
    public void updateRecord2(Staff staff) {
        
        String queryRecord = "UPDATE " + tableName + " SET staff_name = ?, address = ?, contact_no = ?, icno = ?, salary = ?, password = ?, status = ? WHERE staff_id = ?";
        try {
            stmt = conn.prepareStatement(queryRecord);
            stmt.setString(1, staff.getStaffName());
            stmt.setString(2, staff.getAddress());
            stmt.setString(3, staff.getContactNo());
            stmt.setString(4, staff.getIcNo());
            stmt.setString(5, Double.toString(staff.getSalary()));
            stmt.setString(6, staff.getPassword());
            stmt.setString(7, staff.getStatus()+"");
            stmt.setString(8, staff.getStaffID());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    public ArrayList<Staff> getAllRecordForDeactivate() {
        String queryStr = "SELECT * FROM " + tableName;
        ArrayList<Staff> staff = new ArrayList<Staff>();
        try {
            stmt = conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                staff.add(new Staff(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getString(7), rs.getString(8).charAt(0)));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        return staff;
    }

    public boolean checkStaffID(String staffID) {
        String queryStr = "SELECT * FROM " + tableName + " where staff_id = ?";
        Staff staff = new Staff();
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, staffID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                staff = new Staff(rs.getString("staff_id"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        if (staff.getStaffID().equals(staffID)) {
            return true;
        } else {
            return false;
        }
    }

    public Staff lastID(char staffType) //throws SQLException
    {
        Staff staff = null;
        String queryStr = "SELECT * FROM " + tableName + " WHERE STAFF_ID LIKE '" + staffType + "%' ORDER BY STAFF_ID DESC";
        try {
            stmt = conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                staff = new Staff(rs.getString(1));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return staff;
    }

    public void addRecord(Staff staff) {
        String insertStr = "INSERT INTO " + tableName + " VALUES(?, ?, ?, ?,?,?,?,?)";
        try {
            stmt = conn.prepareStatement(insertStr);
            stmt.setString(1, staff.getStaffID());
            stmt.setString(2, staff.getStaffName());
            stmt.setString(3, staff.getAddress());
            stmt.setString(4, staff.getContactNo());
            stmt.setString(5, staff.getIcNo());
            stmt.setString(6, Double.toString(staff.getSalary()));
            stmt.setString(7, staff.getPassword());
            stmt.setString(8, Character.toString(staff.getStatus()));
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "New Staff record had been added");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateRecord(Staff staff) {
        String insertStr1 = "UPDATE " + tableName + " SET ADDRESS = ?, CONTACT_NO = ?, PASSWORD = ?,SALARY = ? WHERE staff_ID= ?";
        String insertStr2 = "UPDATE " + tableName + " SET  ADDRESS = ?, CONTACT_NO = ?,SALARY = ? WHERE staff_ID= ?";
        if (!staff.getPassword().isEmpty()) {
            try {
                stmt = conn.prepareStatement(insertStr1);
                stmt.setString(1, staff.getAddress());
                stmt.setString(2, staff.getContactNo());
                stmt.setString(3, staff.getPassword());
                stmt.setString(4, Double.toString(staff.getSalary()));
                stmt.setString(5, staff.getStaffID());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Staff (" + staff.getStaffName() + ") record had been updated");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            try {
                stmt = conn.prepareStatement(insertStr2);
                stmt.setString(1, staff.getAddress());
                stmt.setString(2, staff.getContactNo());
                stmt.setString(3, Double.toString(staff.getSalary()));
                stmt.setString(4, staff.getStaffID());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Staff (" + staff.getStaffName() + ") record had been updated");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void ActivateOrDeavtivate(Staff staff) {
        String insertStr1 = "UPDATE " + tableName + " SET STATUS=? WHERE staff_ID= ?";
        if (staff.getStatus() == 'A') {
            try {
                stmt = conn.prepareStatement(insertStr1);
                stmt.setString(1, Character.toString(staff.getStatus()));
                stmt.setString(2, staff.getStaffID());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Record had been activated");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            try {
                stmt = conn.prepareStatement(insertStr1);
                stmt.setString(1, Character.toString(staff.getStatus()));
                stmt.setString(2, staff.getStaffID());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Record had been deactivated");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }

    }
    public ArrayList<Staff> searchByName(String name) {
        
        String queryStr = "SELECT * FROM " + tableName + " WHERE staff_name = ? ORDER BY Staff_ID";
        ArrayList<Staff> memberList = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                memberList.add(new Staff(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6),rs.getString(7),rs.getString(8).charAt(0)));
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);           
        }
        return memberList;
    }
    public Staff searchByIC(String ic) {
        
        String queryStr = "SELECT * FROM " + tableName + " WHERE icno = ? ORDER BY Staff_ID";
        Staff memberList =null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, ic);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                memberList=new Staff(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6),rs.getString(7),rs.getString(8).charAt(0));
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);           
        }
        return memberList;
    }
    public ArrayList<Staff> searchByStatus(String status) {
        
        String queryStr = "SELECT * FROM " + tableName + " WHERE status = ? ORDER BY Staff_ID";
        ArrayList<Staff> memberList = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                memberList.add(new Staff(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6),rs.getString(7),rs.getString(8).charAt(0)));
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);           
        }
        return memberList;
    }
    public ArrayList<Staff> getAllRecord() {
        String queryStr = "SELECT * FROM " + tableName;
        ArrayList<Staff> staff = new ArrayList<Staff>();
        try {
            stmt = conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
               staff.add(new Staff(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getString(7), rs.getString(8).charAt(0)));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        return staff;

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
