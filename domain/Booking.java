package domain;

public class Booking {
    
    private String bookingID;
    private Staff staff;
    private Customer customer;
    private String bookingTime;
    private String bookingDate;
    
    public Booking() {
    }
    
    public Booking(String bookingID, Staff staff, Customer customer, String bookingTime, String bookingDate) {
        this.bookingID = bookingID;
        this.staff = staff;
        this.customer = customer;
        this.bookingTime = bookingTime;
        this.bookingDate = bookingDate;
    }
    
    public String getBookingID() {
        return bookingID;
    }
    
    public Staff getStaff() {
        return staff;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public String getBookingTime() {
        return bookingTime;
    }
    
    public String getBookingDate() {
        return bookingDate;
    }
}