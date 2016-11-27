package domain;

public class BusType {
    
    private String busTypeID;
    private String busType;
    private int capacity;
    
    public BusType() {
    }
    
    public BusType(String busTypeID, String busType, int capacity) {
        this.busTypeID = busTypeID;
        this.busType = busType;
        this.capacity = capacity;
    }
    
    public String getBusTypeID() {
        return busTypeID;
    }
    
    public String getBusType() {
        return busType;
    }
    
    public int getCapacity() {
        return capacity;
    }
}