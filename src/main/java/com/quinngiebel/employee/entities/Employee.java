package com.quinngiebel.employee.entities;

/**
 * This class stores information for an Employee.
 * 
 * @author Quinn Giebel
 */
public class Employee {
    private String eid;
    private String firstName;
    private String lastName;
    private String ssn;
    private String dept;
    private String room;
    private String phone;

    /**
     * This constructor instantiates an empty employee.
     */
    public Employee() {
    }

    /**
     * This method creates a string that displays all of the employee data as html
     * table data.
     * 
     * @return The formatted string.
     */
    @Override
    public String toString() {

        return "EmployeeID: " + eid + "\nLast Name: " + lastName + "\nFirst Name: " + firstName + "<\nSSN: " + ssn
                + "\nDepartment: " + dept + "\nRoom Number: " + room + "\nPhone Number: " + phone + "\n";
    }

    /**
     * This method returns the value of empiD.
     * 
     * @return The employee's ID
     */
    public String getEid() {
        return this.eid;
    }

    /**
     * Set the value of eid.
     * 
     * @param eid The new value of eid.
     */
    public void setEid(String eid) {
        this.eid = eid;
    }

    /**
     * This method returns the value of firstName.
     * 
     * @return The employee's first name.
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Set the value of firstName.
     * 
     * @param firstName The new value of firstName.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * This method returns the value of lastName.
     * 
     * @return The employee's last name.
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Set the value of lastName.
     * 
     * @param lastName The new value of lastName.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * This method returns the value of ssn.
     * 
     * @return The employee's SSN.
     */
    public String getSsn() {
        return this.ssn;
    }

    /**
     * Set the value of ssn.
     * 
     * @param ssn The new value of ssn.
     */
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    /**
     * This method returns the value of dept.
     * 
     * @return The employee's department.
     */
    public String getDept() {
        return this.dept;
    }

    /**
     * Set the value of dept.
     * 
     * @param dept The new value of dept.
     */
    public void setDept(String dept) {
        this.dept = dept;
    }

    /**
     * This method returns the value of room.
     * 
     * @return The employee's room number.
     */
    public String getRoom() {
        return this.room;
    }

    /**
     * Set the value of room.
     * 
     * @param room The new value of room.
     */
    public void setRoom(String room) {
        this.room = room;
    }

    /**
     * This method returns the value of phone.
     * 
     * @return The employee's phone number.
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * Set the value of phone.
     * 
     * @param phone The new value of phone.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

}
