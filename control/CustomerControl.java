/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import da.CustomerDA;
import domain.Customer;
import java.util.ArrayList;

/**
 *
 * @author Student
 */
public class CustomerControl {
    
    private CustomerDA database;
    
    public CustomerControl() {
        database = new CustomerDA();
    }
    
    public Customer selectRecord(String custID) {
        return database.getRecord(custID);
    }
    
    public Customer selectRecordByNameContactICNo(String custName, String contactNo, String ICNo) {
        return database.getRecordByNameContactICNo(custName, contactNo, ICNo);
    }
    
    public ArrayList<Customer> selectRecordByName(String custName) {
        return database.getRecordByName(custName);
    }
    
    public ArrayList<Customer> selectRecordByContactNo(String contact) {
        return database.getRecordByContactNo(contact);
    } 
    
    public Customer selectRecordByIC(String icno) {
        return database.getRecordByIC(icno);
    }
    
    public ArrayList<Customer> selectRecordByPoint(String point) {
        return database.getRecordByPoint(point);
    } 
    
    public Customer selectLatestRecord() {
        return database.getLatestRecord();
    }
    
    public ArrayList<Customer> selectAllRecord() {
        return database.getAllRecord();
    }
    
    public ArrayList<Customer> selectAllMemberRecord() {
        return database.getAllMemberRecord();
    }
    
    public void insertRecord(Customer customer) {
        database.addRecord(customer);
    }
    
    public void increasePoint(Customer customer, int point) {
        database.addPoint(customer, point);
    }
    
    public void decreasePoint(Customer customer, int point) {
        database.deducePoint(customer, point);
    }
    
    public void modifyRecord(Customer customer) {
        database.updateRecord(customer);
    }
    
    public void modifyMemberRecord(Customer customer) {
        database.updateMemberRecord(customer);
    }
    
    public void customerToMember(Customer customer) {
        database.custToMem(customer);
    }
}
