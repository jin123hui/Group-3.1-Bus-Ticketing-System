/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import da.*;
import domain.*;

/**
 *
 * @author JianHow
 */
public class Function {

    private StaffDA staffID;
    private DriverDA driverID;
    private PriceDA priceID;
    private RouteDA routeID;
    private ScheduleDA scheduleID;
    private BusDA busID;
    private RouteDA routeDA;

    public Function() {
        staffID = new StaffDA();
        driverID = new DriverDA();
        priceID = new PriceDA();
        routeID = new RouteDA();
        scheduleID = new ScheduleDA();
        busID = new BusDA();
        routeDA = new RouteDA();
    }

    public String setStaffID(char staffType) {

        Staff a = staffID.lastID(staffType);
        String lastID = a.getStaffID();
        if (lastID.charAt(0) == 'M') {
            if (!lastID.equals("")) {
                String empty = "";
                int temporaryID;
                for (int i = 1; i < lastID.length(); i++) {
                    empty += Character.toString(lastID.charAt(i));
                }
                temporaryID = Integer.parseInt(empty);
                temporaryID++;
                String temporaryID2 = String.valueOf(String.format("%04d", temporaryID));
                String newID = "M" + temporaryID2;

                return newID;
            } else {
                return "M0001";
            }
        } else if (lastID.charAt(0) == 'S') {
            if (!lastID.equals("")) {
                String empty = "";
                int temporaryID;
                for (int i = 1; i < lastID.length(); i++) {
                    empty += Character.toString(lastID.charAt(i));
                }
                temporaryID = Integer.parseInt(empty);
                temporaryID++;
                String temporaryID2 = String.valueOf(String.format("%04d", temporaryID));
                String newID = "S" + temporaryID2;

                return newID;
            } else {
                return "S0001";
            }
        }
        return "";
    }

    public String setDriverID(char driverType) {

        Driver a = driverID.lastID(driverType);
        String lastID = a.getDriverID();
        if (!lastID.equals("")) {
            String empty = "";
            int temporaryID;
            for (int i = 1; i < lastID.length(); i++) {
                empty += Character.toString(lastID.charAt(i));
            }
            temporaryID = Integer.parseInt(empty);
            temporaryID++;
            String temporaryID2 = String.valueOf(String.format("%04d", temporaryID));
            String newID = "D" + temporaryID2;

            return newID;
        } else {
            return "D0001";
        }
    }

    public String setPriceID(char priceType) {

        Price a = priceID.lastID(priceType);
        String lastID = a.getPriceID();
        if (!lastID.equals("")) {
            String empty = "";
            int temporaryID;
            for (int i = 1; i < lastID.length(); i++) {
                empty += Character.toString(lastID.charAt(i));
            }
            temporaryID = Integer.parseInt(empty);
            temporaryID++;
            String temporaryID2 = String.valueOf(String.format("%04d", temporaryID));
            String newID = "P" + temporaryID2;

            return newID;
        } else {
            return "P0001";
        }
    }

    public String setBusID(char busType) {

        Bus a = busID.lastID(busType);
        String lastID = a.getBusID();
        if (!lastID.equals("")) {
            String empty = "";
            int temporaryID;
            for (int i = 1; i < lastID.length(); i++) {
                empty += Character.toString(lastID.charAt(i));
            }
            temporaryID = Integer.parseInt(empty);
            temporaryID++;
            String temporaryID2 = String.valueOf(String.format("%04d", temporaryID));
            String newID = "B" + temporaryID2;

            return newID;
        } else {
            return "B0001";
        }
    }

    public String setRouteID(char routeType) {

        Route a = routeDA.lastID(routeType);
        String lastID = a.getRouteID();
        if (!lastID.equals("")) {
            String empty = "";
            int temporaryID;
            for (int i = 1; i < lastID.length(); i++) {
                empty += Character.toString(lastID.charAt(i));
            }
            temporaryID = Integer.parseInt(empty);
            temporaryID++;
            String temporaryID2 = String.valueOf(String.format("%04d", temporaryID));
            String newID = "R" + temporaryID2;

            return newID;
        } else {
            return "R0001";
        }
    }
    public String setScheduleID(String type) {

        Schedule a = scheduleID.lastID(type);
        String lastID = a.getScheduleID();
        if (!lastID.equals("")) {
            String empty = "";
            int temporaryID;
            for (int i = 2; i < lastID.length(); i++) {
                empty += Character.toString(lastID.charAt(i));
            }
            temporaryID = Integer.parseInt(empty);
            temporaryID++;
            String temporaryID2 = String.valueOf(String.format("%06d", temporaryID));
            String newID = "SC" + temporaryID2;

            return newID;
        } else {
            return "SC000001";
        }
    }
}
