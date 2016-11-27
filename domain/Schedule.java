package domain;

public class Schedule {

    private String scheduleID;
    private Driver driver;
    private Route route;
    private Bus bus;
    private String departureTime;
    private String departureDate;
    private char status;

    public Schedule() {
    }

    public Schedule(String scheduleID) {
        this.scheduleID = scheduleID;
    }

    public Schedule(String scheduleID, Driver driver, Route route, Bus bus, String departureTime, String departureDate, char status) {
        this.scheduleID = scheduleID;
        this.driver = driver;
        this.route = route;
        this.bus = bus;
        this.departureTime = departureTime;
        this.departureDate = departureDate;
        this.status = status;
    }

    public Schedule(Route route) {
        this.route = route;
    }

    public String getScheduleID() {
        return scheduleID;
    }

    public Driver getDriver() {
        return driver;
    }

    public Route getRoute() {
        return route;
    }

    public Bus getBus() {
        return bus;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public char getStatus() {
        return status;
    }
}
