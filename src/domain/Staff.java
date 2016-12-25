package domain;

public class Staff {

    private String staffID;
    private String staffName;
    private String address;
    private String contactNo;
    private String icNo;
    private double salary;
    private String password;
    private char status;

    public Staff() {
    }

    public Staff(String staffID, String staffName, String address, String contactNo, String icNo, double salary, String password, char status) {
        this.staffID = staffID;
        this.staffName = staffName;
        this.address = address;
        this.contactNo = contactNo;
        this.icNo = icNo;
        this.salary = salary;
        this.password = password;
        this.status = status;
    }

    public Staff(String staffID) {
        this.staffID = staffID;
    }

    public Staff(String staffID, char status) {
        this.staffID = staffID;
        this.status = status;
    }

    public Staff(String staffID, String icNo) {
        this.staffID = staffID;
        this.icNo = icNo;
    }

    public String getStaffID() {
        return staffID;
    }

    public String getStaffName() {
        return staffName;
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

    public String getPassword() {
        return password;
    }

    public char getStatus() {
        return status;
    }
}
