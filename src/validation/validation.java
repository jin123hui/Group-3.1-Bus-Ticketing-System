/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

/**
 *
 * @author JianHow
 */
import control.*;
import domain.Bus;
import domain.Driver;
import domain.Staff;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class validation {

    private StaffControl staffC;
    private DriverControl driverC;
    private BusControl busC;
    public validation() {
        staffC = new StaffControl();
        driverC = new DriverControl();
        busC=new BusControl();
    }
 
    public String validateName(String name) {
         if(!name.matches("^[A-Z a-z]*$")){
             return "Please enter character only";
         }
        if (name.equals("")) {
            return "Enter a Staff name.Name cannot be empty";
        }
        if (name.length() > 30) {
            return "Max length of characters for name is 30.";
        } else {
            return "";
        }
    }

    public String validateICNo(String icNum) {
        //throw icNum to database to check
        ArrayList<Staff> staff = staffC.getAllStaff();
        ArrayList<Driver> driver = driverC.getAllRecord();
        if (!icNum.isEmpty()) {
            for (Staff s : staff) {
                if (s.getIcNo().equals(icNum)) {
                    return "This IC Num had been contain in database";
                }
                for (Driver d : driver) {
                    if (d.getIcNo().equals(icNum)) {
                        return "This IC Num had been contain in database";
                    }
                }
            }
        } else if (icNum.equalsIgnoreCase("")) {
            return "Enter an IC number.";
        }
        if (!icNum.matches("[\\d]{6}-[\\d]{2}-[\\d]{4}")) {
            return "Invalid IC No.(Eg: XXXXXX-XX-XXXX).";
        }else {
            String date = icNum.substring(0, 6);
            int errorCounter = 0;
            Date valiDate = null;
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
                format.setLenient(false);
                valiDate = format.parse(date);
            } catch (Exception ex) {
                errorCounter++;
            }
            if (errorCounter != 0) {
                return "Invalid ic no format.";
            }
        }
        
        return "";
    }

    public String validateContactNo(String contactNo) {
        if (contactNo.equals("")) {
            return "Enter a telephone number.";
        }
        if (!contactNo.matches("[\\d]{3}-[\\d]{7,8}")) {
            return "Invalid Contact No.(Eg:01X-XXXXXXX)";
        }
        return "";
    }

    public String validateAddress(String address) {
        if (address.equals("")) {
            return "Enter an address.";
        }
        if (address.length() > 80) {
            return "Max length of characters for address is 80.";
        } else {
            return "";
        }
    }

    public String validateSalary(String salary) {
        if(!salary.matches("^(\\.\\d{1,2}|\\d{1,5}\\.?\\d{0,2})$")){
                return "Salary Field (Please enter digit or number only!!!!)";
        }
        if (salary.isEmpty()) {
            return "Enter amount of salary";
        } else {
            return "";
        }
    }

    public String validatePassword(String str1, String str2) {
        if (!str1.equals(str2)) {
            return "Password not equal!!!Please re-enter your password";
        }else if(str1.length()<5 || str1.length()>20 ){
            return "Password length must between 7 to 20";
        }else {
            return "";
        }
    }

    public String validateStaffID(String staffID) {
        if (!staffID.matches("[S,M][\\d]{4}")) {
            return "Invalid ID format !!! (Eg.S0001)";
        } else {
            boolean check = staffC.checkStaffID(staffID);
            if (check == true) {
                return "";
            } else {
                return "No Record Found!!!";
            }
        }
    }
        public String validateDriverID(String driverID) {
            Driver driver=driverC.selectRecord(driverID);
        
        if (!driverID.matches("[D][\\d]{4}")) {
            return "Invalid ID format !!! (Eg.D0001)";
        }
       if(driver.getDriverID().isEmpty()){
           return "No Record Found!!!";
        }
        return "";
    }
        public String validatePlateNo(String plateNo){
            ArrayList<Bus> bus=busC.getAllRecord();
            if(plateNo.isEmpty()){
                return "Please enter a plate no !!!";
            }
            if(plateNo.length()>7){
                return "Plate No out of range. Eg(ADD3346)";
            }
            for(Bus b:bus){
                if(b.getBusPlateNo().equals(plateNo.toUpperCase())){
                    return "Plate No duplicate in database";
                }
            }
            return "";
        }
    

}
