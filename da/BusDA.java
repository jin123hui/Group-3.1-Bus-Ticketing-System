/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import control.BusTypeControl;
import domain.Bus;
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
public class BusDA {

    private String host = "jdbc:derby://localhost:1527/CJHDB";
    private String user = "cjh";
    private String password = "cjh";
    private String tableName = "BUS";
    private Connection conn;
    private PreparedStatement stmt;
    private BusTypeControl bControl;

    public BusDA() {
        createConnection();
        bControl = new BusTypeControl();
    }

    public Bus getRecord(String busID) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE bus_id = ?";
        Bus bus = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, busID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                bus = new Bus(busID, bControl.selectRecord(rs.getString(2)), rs.getString(3), rs.getString(4).charAt(0));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        return bus;
    }
    public ArrayList<Bus> getAllRecordByStatus(String status) {
        String queryStr = "SELECT * FROM " + tableName+" where status = ?";
        ArrayList<Bus> bus = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                bus.add(new Bus(rs.getString(1), bControl.selectRecord(rs.getString(2)), rs.getString(3), rs.getString(4).charAt(0)));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        return bus;
    }

    public ArrayList<Bus> getAllRecord() {
        String queryStr = "SELECT * FROM " + tableName;
        ArrayList<Bus> bus = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                bus.add(new Bus(rs.getString(1), bControl.selectRecord(rs.getString(2)), rs.getString(3), rs.getString(4).charAt(0)));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        return bus;
    }

    public Bus lastID(char busType) //throws SQLException
    {
        Bus bus = null;
        String queryStr = "SELECT * FROM " + tableName + " WHERE bus_id LIKE '" + busType + "%' ORDER BY bus_id DESC ";
        try {
            stmt = conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                bus = new Bus(rs.getString(1));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return bus;
    }

    public void insertRecord(Bus bus) {
        String insertStr = "INSERT INTO " + tableName + " VALUES(?, ?, ?, ?)";
        try {
            stmt = conn.prepareStatement(insertStr);
            stmt.setString(1, bus.getBusID());
            stmt.setString(2, bus.getBusType().getBusTypeID());
            stmt.setString(3, bus.getBusPlateNo());
            stmt.setString(4, Character.toString(bus.getStatus()));
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "New Bus record had been added");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateRecord(Bus bus, char status) {
        String insertStr1 = "UPDATE " + tableName + " SET status = ? WHERE bus_id= ?";

        if (status == 'U') {
            try {
                stmt = conn.prepareStatement(insertStr1);
                stmt.setString(2, bus.getBusID());
                stmt.setString(1, "U");
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Bus (" + bus.getBusPlateNo() + ") record had been updated");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else if (status == 'M') {
            try {
                stmt = conn.prepareStatement(insertStr1);
                stmt.setString(2, bus.getBusID());
                stmt.setString(1, "M");
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Staff (" + bus.getBusPlateNo() + ") record had been updated");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
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
