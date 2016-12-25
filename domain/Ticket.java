package domain;

public class Ticket {
    
    private String ticketID;
    private Booking booking;
    private Schedule schedule;
    private String seatNo;
    private String passengerName;
    private String passengerContactNo;
    private char status;
    
    public Ticket() {
    }
    
    public Ticket(String ticketID, Booking booking, Schedule schedule, String seatNo, String passengerName, String passengerContactNo, char status) {
        this.ticketID = ticketID;
        this.booking = booking;
        this.schedule = schedule;
        this.seatNo = seatNo;
        this.passengerName = passengerName;
        this.passengerContactNo = passengerContactNo;
        this.status = status;
    }
    
    public Ticket(String ticketID, Schedule schedule, String seatNo, String passengerName, String passengerContactNo) {
        this.ticketID = ticketID;
        this.schedule = schedule;
        this.seatNo = seatNo;
        this.passengerName = passengerName;
        this.passengerContactNo = passengerContactNo;
        this.status = 'A';
    }
    
    public String getTicketID() {
        return ticketID;
    }
    
    public Booking getBooking() {
        return booking;
    }
    
    public Schedule getSchedule() {
        return schedule;
    }
    
    public String getSeatNo() {
        return seatNo;
    }
    
    public String getPassengerName() {
        return passengerName;
    }
    
    public String getPassengerContactNo() {
        return passengerContactNo;
    }
    
    public char getStatus() {
        return status;
    }
}