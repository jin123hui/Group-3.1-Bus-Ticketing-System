/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import domain.Price;
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
public class PriceDA {
    
    private String host = "jdbc:derby://localhost:1527/CJHDB";
    private String user = "cjh";
    private String password = "cjh";
    private String tableName = "PRICE";
    private Connection conn;
    private PreparedStatement stmt;
    
    public PriceDA() {
        createConnection();
    }
    
    public Price getRecord(String priceID) {
        
        String queryStr = "SELECT * FROM " + tableName + " WHERE price_id = ?";
        Price price = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, priceID);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                price = new Price(priceID, rs.getDouble(2));
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return price;
    }
    public Price lastID(char priceType) //throws SQLException
    {
        Price price = null;
        String queryStr = "SELECT * FROM " + tableName + " WHERE PRICE_ID LIKE '" + priceType + "%' ORDER BY PRICE_ID DESC";
        try {
            stmt = conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                price = new Price(rs.getString(1));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return price;
    }
    public void insertRecord(Price price){
        String insertStr = "INSERT INTO " + tableName + " VALUES(?, ?)";
        try {
            stmt = conn.prepareStatement(insertStr);
            stmt.setString(1, price.getPriceID());
            stmt.setString(2, Double.toString(price.getPrice()));
            stmt.executeUpdate();
            //JOptionPane.showMessageDialog(null, "New Price record had been added");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    public ArrayList<Price> getAllRecord(){
        String queryStr = "SELECT * FROM " + tableName ;
        ArrayList<Price> price = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                price.add(new Price(rs.getString(1), rs.getDouble(2)));
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return price;
    }    
    public ArrayList<Price> searchByRange(String str1,String str2){
        String queryStr = "SELECT * FROM " + tableName+" WHERE Price BETWEEN ? AND ?";
        ArrayList<Price> price = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, str1);
            stmt.setString(2, str2);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                price.add(new Price(rs.getString(1), rs.getDouble(2)));
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return price;
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
