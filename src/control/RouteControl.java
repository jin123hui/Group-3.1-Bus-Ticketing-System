/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import da.RouteDA;
import domain.Route;
import java.util.ArrayList;

/**
 *
 * @author CJH
 */
public class RouteControl {

    private RouteDA database;

    public RouteControl() {
        database = new RouteDA();
    }

    public ArrayList<Route> selectAllRecord() {

        return database.getAllRecord();
    }

    public Route selectRecord(String routeID) {

        return database.getRecord(routeID);
    }

    public Route selectRecordByDestination(String destination) {

        return database.getRecordByDestination(destination);
    }

    public void insertRecord(Route route) {
        database.insertRecord(route);
    }

    public void activateOrDeactivateRoute(Route route) {
        database.activateOrDeactivateRoute(route);
    }

    public ArrayList<Route> getAllRecord() {

        return database.getAllRecord();
    }

    public ArrayList<Route> getRecordByDistance(String str1, String str2) {

        return database.getAllRecordByDistance(str1, str2);
    }

    public ArrayList<Route> getRecordByPrice(String str1, String str2) {

        return database.getRecordByPrice(str1, str2);
    }

    public void updateRecord(ArrayList<Route> route, String upBy, String range1, String range2, String destination) {
        database.updateRecord(route, upBy, range1, range2, destination);
    }
}
