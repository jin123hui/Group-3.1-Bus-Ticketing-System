package domain;

public class Bus {

    private String busID;
    private BusType busType;
    private String busPlateNo;
    private char status;

    public Bus() {
    }

    public Bus(String busID) {
        this.busID = busID;
    }

    public Bus(String busID, BusType busType, String busPlateNo, char status) {
        this.busID = busID;
        this.busType = busType;
        this.busPlateNo = busPlateNo;
        this.status = status;
    }

    public String getBusID() {
        return busID;
    }

    public BusType getBusType() {
        return busType;
    }

    public String getBusPlateNo() {
        return busPlateNo;
    }

    public char getStatus() {
        return status;
    }
}
