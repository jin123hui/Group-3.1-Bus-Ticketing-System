/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import control.PriceControl;
import domain.Route;
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
public class RouteDA {
    
    private String host = "jdbc:derby://localhost:1527/CJHDB";
    private String user = "cjh";
    private String password = "cjh";
    private String tableName = "ROUTE";
    private Connection conn;
    private PreparedStatement stmt;
    private PriceControl pControl;
    
    public RouteDA() {
        createConnection();
        pControl = new PriceControl();
    }
    
    public ArrayList<Route> getAllRecord() {
        
        String queryStr = "SELECT * FROM " + tableName + " ORDER BY route_id";
        ArrayList<Route> routeList = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                routeList.add(new Route(rs.getString(1), pControl.selectRecord(rs.getString(2)), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6).charAt(0)));            
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);           
        }
        return routeList;
    }
    
    public Route getRecord(String routeID) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE route_id = ?";
        Route route = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, routeID);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                route = new Route(routeID, pControl.selectRecord(rs.getString(2)), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6).charAt(0));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        return route;
    }
    
    public Route getRecordByDestination(String destination) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE destination = ?";
        Route route = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, destination);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                route = new Route(rs.getString(1), pControl.selectRecord(rs.getString(2)), destination, rs.getInt(4), rs.getInt(5), rs.getString(6).charAt(0));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        return route;
    }
     public void insertRecord(Route route) {
        String insertStr = "INSERT INTO " + tableName + " VALUES(?, ?, ?, ?,?,?)";
        try {
            stmt = conn.prepareStatement(insertStr);
            stmt.setString(1, route.getRouteID());
            stmt.setString(2, route.getPrice().getPriceID());
            stmt.setString(3, route.getDestination());
            stmt.setString(4, Integer.toString(route.getDistance()));
            stmt.setString(5, Integer.toString(route.getTimeRequired()));
            stmt.setString(6, Character.toString(route.getStatus()));
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "New Route record had been added");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
     public void activateOrDeactivateRoute(Route route) {
        String insertStr1 = "UPDATE " + tableName + " SET STATUS=? WHERE route_id= ?";
        if (route.getStatus() == 'A') {
            try {
                stmt = conn.prepareStatement(insertStr1);
                stmt.setString(1, Character.toString(route.getStatus()));
                stmt.setString(2, route.getRouteID());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Record had been activated");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            try {
                stmt = conn.prepareStatement(insertStr1);
                stmt.setString(1, Character.toString(route.getStatus()));
                stmt.setString(2, route.getRouteID());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Record had been deactivated");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public Route lastID(char busType) //throws SQLException
    {
        Route route = null;
        String queryStr = "SELECT * FROM " + tableName + " WHERE route_id LIKE '" + busType + "%' ORDER BY route_id DESC ";
        try {
            stmt = conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                route = new Route(rs.getString(1));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return route;
    }

    public void updateRecord(ArrayList<Route> route, String upBy, String range1, String range2, String destination) {
        String insertStr1 = "UPDATE " + tableName + " SET price_id = ? WHERE destination=?";
        String insertStr2 = "UPDATE " + tableName + " SET price_id= ? WHERE route_id=?";
        if (upBy.equals("Destination")) {
            try {
                stmt = conn.prepareStatement(insertStr1);

                for (Route r : route) {
                    stmt.setString(1, r.getPrice().getPriceID());
                    stmt.setString(2, r.getDestination());
                    stmt.executeUpdate();

                }
                JOptionPane.showMessageDialog(null, "Route (" + destination + ") record had been updated");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else if (upBy.equals("Distance")) {
            try {
                stmt = conn.prepareStatement(insertStr2);
                for (Route r : route) {
                    stmt.setString(1, r.getPrice().getPriceID());
                    stmt.setString(2, r.getRouteID());

                    stmt.executeUpdate();
                }

                JOptionPane.showMessageDialog(null, "Route between (" + range1 + " to " + range2 + ") record had been updated");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public ArrayList<Route> getAllRecordByDistance(String str1, String str2) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE distance BETWEEN ? AND ?";
        ArrayList<Route> route = new ArrayList<Route>();
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, str1);
            stmt.setString(2, str2);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                route.add(new Route(rs.getString(1), pControl.selectRecord(rs.getString(2)), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6).charAt(0)));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return route;

    }

    public ArrayList<Route> getRecordByPrice(String str1, String str2) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE price BETWEEN ? AND ?";
        ArrayList<Route> route = new ArrayList<Route>();
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, str1);
            stmt.setString(2, str2);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                route.add(new Route(rs.getString(1), pControl.selectRecord(rs.getString(2)), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6).charAt(0)));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return route;

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
