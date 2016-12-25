package domain;

public class Driver {

    private String driverID;
    private String driverName;
    private String address;
    private String contactNo;
    private String icNo;
    private double salary;
    private char status;

    public Driver() {
    }

    public Driver(String driverID, String driverName, String address, String contactNo, String icNo, double salary, char status) {
        this.driverID = driverID;
        this.driverName = driverName;
        this.address = address;
        this.contactNo = contactNo;
        this.icNo = icNo;
        this.salary = salary;
        this.status = status;
    }

    public Driver(String driverID) {
        this.driverID = driverID;

    }

    public Driver(String driverID, char status) {
        this.driverID = driverID;
        this.status = status;

    }

    public Driver(String driverID, String driverName) {
        this.driverID = driverID;
        this.driverName = driverName;

    }

    public String getDriverID() {
        return driverID;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getAddress() {
        return address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getIcNo() {
        return icNo;
    }

    public double getSalary() {
        return salary;
    }

    public char getStatus() {
        return status;
    }
}
