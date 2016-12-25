/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import control.BusControl;
import control.DriverControl;
import control.RouteControl;
import domain.Bus;
import domain.Route;
import domain.Schedule;
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
public class ScheduleDA {

    private String host = "jdbc:derby://localhost:1527/CJHDB";
    private String user = "cjh";
    private String password = "cjh";
    private String tableName = "SCHEDULE";
    private Connection conn;
    private PreparedStatement stmt;
    private DriverControl dControl;
    private RouteControl rControl;
    private BusControl bControl;

    public ScheduleDA() {
        createConnection();
        dControl = new DriverControl();
        rControl = new RouteControl();
        bControl = new BusControl();
    }

    public Schedule getRecord(String scheduleID) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE schedule_id = ?";
        Schedule schedule = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, scheduleID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                schedule = new Schedule(scheduleID, dControl.selectRecord(rs.getString(2)), rControl.selectRecord(rs.getString(3)), bControl.selectRecord(rs.getString(4)), rs.getString(5), rs.getString(6), rs.getString(7).charAt(0));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        return schedule;
    }

    public Schedule getRecordByBusDateTime(Bus bus, String departureDate, String departureTime) {

        String queryStr = "SELECT * FROM " + tableName + " WHERE bus_id = ? AND departure_date = ? AND departure_time = ?";
        Schedule schedule = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, bus.getBusID());
            stmt.setString(2, departureDate);
            stmt.setString(3, departureTime);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                schedule = new Schedule(rs.getString(1), dControl.selectRecord(rs.getString(2)), rControl.selectRecord(rs.getString(3)), bControl.selectRecord(rs.getString(4)), rs.getString(5), rs.getString(6), rs.getString(7).charAt(0));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return schedule;
    }

    public ArrayList<Schedule> getRecordByRouteAndDate(Route route, String departureDate) {

        String queryStr = "SELECT * FROM " + tableName + " WHERE route_id = ? AND departure_date = ? ORDER BY departure_time";
        ArrayList<Schedule> scheduleList = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, route.getRouteID());
            stmt.setString(2, departureDate);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                scheduleList.add(new Schedule(rs.getString(1), dControl.selectRecord(rs.getString(2)), rControl.selectRecord(rs.getString(3)), bControl.selectRecord(rs.getString(4)), rs.getString(5), rs.getString(6), rs.getString(7).charAt(0)));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return scheduleList;
    }

    public ArrayList<Schedule> getRecordByRouteAndDateSortByBus(Route route, String departureDate) {

        String queryStr = "SELECT * FROM " + tableName + " WHERE route_id = ? AND departure_date = ? ORDER BY bus_id";
        ArrayList<Schedule> scheduleList = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, route.getRouteID());
            stmt.setString(2, departureDate);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                scheduleList.add(new Schedule(rs.getString(1), dControl.selectRecord(rs.getString(2)), rControl.selectRecord(rs.getString(3)), bControl.selectRecord(rs.getString(4)), rs.getString(5), rs.getString(6), rs.getString(7).charAt(0)));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return scheduleList;
    }

    public ArrayList<Schedule> getRecordByRoute(Route route) {

        String queryStr = "SELECT * FROM " + tableName + " WHERE route_id = ?";
        ArrayList<Schedule> scheduleList = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, route.getRouteID());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                scheduleList.add(new Schedule(rs.getString(1), dControl.selectRecord(rs.getString(2)), rControl.selectRecord(rs.getString(3)), bControl.selectRecord(rs.getString(4)), rs.getString(5), rs.getString(6), rs.getString(7).charAt(0)));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return scheduleList;
    }

    public ArrayList<Schedule> getRecordByDateForRoute(String departureDate) {

        String queryStr = "SELECT route_id FROM " + tableName + " WHERE departure_date = ? GROUP BY route_id";
        ArrayList<Schedule> scheduleList = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, departureDate);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                scheduleList.add(new Schedule(rControl.selectRecord(rs.getString(1))));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return scheduleList;
    }

    public ArrayList<Schedule> selectRecordByDate(String departureDate) {

        String queryStr = "SELECT * FROM " + tableName + " WHERE departure_date = ? ORDER BY departure_date";
        ArrayList<Schedule> scheduleList = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, departureDate);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                scheduleList.add(new Schedule(rs.getString(1), dControl.selectRecord(rs.getString(2)), rControl.selectRecord(rs.getString(3)), bControl.selectRecord(rs.getString(4)), rs.getString(5), rs.getString(6), rs.getString(7).charAt(0)));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return scheduleList;
    }

    public ArrayList<Schedule> getAllRecord() {

        String queryStr = "SELECT * FROM " + tableName;
        ArrayList<Schedule> scheduleList = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                scheduleList.add(new Schedule(rs.getString(1), dControl.selectRecord(rs.getString(2)), rControl.selectRecord(rs.getString(3)), bControl.selectRecord(rs.getString(4)), rs.getString(5), rs.getString(6), rs.getString(7).charAt(0)));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return scheduleList;
    }

    public Schedule lastID(String busType) //throws SQLException
    {
        Schedule schedule = null;
        String queryStr = "SELECT * FROM " + tableName + " WHERE schedule_id LIKE '" + busType + "%' ORDER BY schedule_id DESC ";
        try {
            stmt = conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                schedule = new Schedule(rs.getString(1));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return schedule;
    }

    public void insertRecord(Schedule schedule) {
        String insertStr = "INSERT INTO " + tableName + " VALUES(?, ?, ?, ?,?,?,?)";
        try {
            stmt = conn.prepareStatement(insertStr);
            stmt.setString(1, schedule.getScheduleID());
            stmt.setString(2, schedule.getDriver().getDriverID());
            stmt.setString(3, schedule.getRoute().getRouteID());
            stmt.setString(4, schedule.getBus().getBusID());
            stmt.setString(5, schedule.getDepartureTime());
            stmt.setString(6, schedule.getDepartureDate());
            stmt.setString(7, Character.toString(schedule.getStatus()));
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "New Schedule record had been added");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateRecord(Schedule schedule, String status) {

        String insertStr1 = "UPDATE " + tableName + " SET driver_id=? , bus_id=? WHERE schedule_id= ?";
        String insertStr2 = "UPDATE " + tableName + " SET status=? WHERE schedule_id= ?";
        if (status.isEmpty()) {
            try {
                stmt = conn.prepareStatement(insertStr1);
                stmt.setString(1, schedule.getDriver().getDriverID());
                stmt.setString(2, schedule.getBus().getBusID());
                stmt.setString(3, schedule.getScheduleID());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Schedule (" + schedule.getScheduleID() + ") record had been updated");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else if (status.equals("A")) {
            try {
                stmt = conn.prepareStatement(insertStr2);
                stmt.setString(1, status);
                stmt.setString(2, schedule.getScheduleID());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Schedule (" + schedule.getScheduleID() + ") record had been activated");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else if (status.equals("D")) {

            try {
                stmt = conn.prepareStatement(insertStr2);
                stmt.setString(1, status);
                stmt.setString(2, schedule.getScheduleID());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Schedule (" + schedule.getScheduleID() + ") record had been deactivated");
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
