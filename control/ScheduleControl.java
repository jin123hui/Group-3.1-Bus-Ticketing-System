/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import da.ScheduleDA;
import domain.Bus;
import domain.Route;
import domain.Schedule;
import java.util.ArrayList;

/**
 *
 * @author CJH
 */
public class ScheduleControl {

    private ScheduleDA database;

    public ScheduleControl() {
        database = new ScheduleDA();
    }

    public Schedule selectRecord(String scheduleID) {
        return database.getRecord(scheduleID);
    }

    public Schedule selectRecordByBusDateTime(Bus bus, String departureDate, String departureTime) {
        return database.getRecordByBusDateTime(bus, departureDate, departureTime);
    }

    public ArrayList<Schedule> selectRecordByRouteAndDate(Route route, String departureDate) {
        return database.getRecordByRouteAndDate(route, departureDate);
    }

    public ArrayList<Schedule> selectRecordByRouteAndDateSortByBus(Route route, String departureDate) {
        return database.getRecordByRouteAndDateSortByBus(route, departureDate);
    }

    public ArrayList<Schedule> selectRecordByRoute(Route route) {
        return database.getRecordByRoute(route);
    }

    public ArrayList<Schedule> selectRecordByDateForRoute(String departureDate) {
        return database.getRecordByDateForRoute(departureDate);
    }

    public void insertRecord(Schedule schedule) {
        database.insertRecord(schedule);
    }

    public void updateRecord(Schedule schedule, String status) {
        database.updateRecord(schedule, status);
    }

    public ArrayList<Schedule> getAllRecord() {
        return database.getAllRecord();
    }

    public ArrayList<Schedule> selectRecordByDate(String departureDate) {
        return database.selectRecordByDate(departureDate);
    }
}
