/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import domain.BusType;
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
public class BusTypeDA {
    
    private String host = "jdbc:derby://localhost:1527/CJHDB";
    private String user = "cjh";
    private String password = "cjh";
    private String tableName = "BUSTYPE";
    private Connection conn;
    private PreparedStatement stmt;
    
    public BusTypeDA() {
        createConnection();
    }
    
    public BusType getRecord(String busTypeID) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE bustype_id = ?";
        BusType busType = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, busTypeID);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                busType = new BusType(busTypeID, rs.getString(2), rs.getInt(3));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        return busType;
    }
     public BusType getBusID(String type) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE bustype = ?";
        BusType busType = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, type);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                busType = new BusType(rs.getString(1), rs.getString(2), rs.getInt(3));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        return busType;
    }
    public ArrayList<BusType> getAllRecord(){
        String queryStr = "SELECT * FROM " + tableName;
        ArrayList<BusType> busT = new ArrayList<BusType>();
        try {
            stmt = conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                busT.add(new BusType(rs.getString(1), rs.getString(2),rs.getInt(3)));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        return busT;
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
