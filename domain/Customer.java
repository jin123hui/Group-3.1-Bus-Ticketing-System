package domain;

public class Customer {

    private String custID;
    private String custName;
    private String contactNo;
    private String icNo;
    private char status;
    private int point;
    
    public Customer() {
    }
    
    public Customer(String custID, String custName, String contactNo, String icNo, char status, int point) {
        this.custID = custID;
        this.custName = custName;
        this.contactNo = contactNo;
        this.icNo = icNo;
        this.status = status;
        this.point = point;
    }
    
    public Customer(String custID, String custName, String contactNo, String icNo) {
        this.custID = custID;
        this.custName = custName;
        this.contactNo = contactNo;
        this.icNo = icNo;  
        this.status = ' ';
        this.point = 0;
    }
    
    public Customer(String custID, String custName, String contactNo, String icNo, int point) {
        this.custID = custID;
        this.custName = custName;
        this.contactNo = contactNo;
        this.icNo = icNo;  
        this.status = 'M';
        this.point = point;
    }
    
    public String getCustID() {
        return custID;
    }
    
    public String getCustName() {
        return custName;
    }
    
    public String getContactNo() {
        return contactNo;
    }
    
    public String getIcNo() {
        return icNo;
    }
    
    public char getStatus() {
        return status;
    }
    
    public int getPoint() {
        return point;
    }
}