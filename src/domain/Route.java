package domain;

public class Route {

    private String routeID;
    private Price price;
    private String destination;
    private int distance;
    private int timeRequired;
    private char status;

    public Route() {
    }

    public Route(String routeID, Price price, String destination, int distance, int timeRequired, char status) {
        this.routeID = routeID;
        this.price = price;
        this.destination = destination;
        this.distance = distance;
        this.timeRequired = timeRequired;
        this.status = status;
    }

    public Route(String routeID) {
        this.routeID = routeID;

    }

    public Route(String routeID, char status) {
        this.routeID = routeID;
        this.status = status;
    }

    public String getRouteID() {
        return routeID;
    }

    public Price getPrice() {
        return price;
    }

    public String getDestination() {
        return destination;
    }

    public int getDistance() {
        return distance;
    }

    public int getTimeRequired() {
        return timeRequired;
    }

    public char getStatus() {
        return status;
    }
}
