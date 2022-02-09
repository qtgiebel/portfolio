package com.quinngiebel.employee.entities;

import java.util.ArrayList;
import com.quinngiebel.employee.entities.Employee;

/**
 * This class is a javabean that stores search data.
 * 
 * @author Quinn Giebel
 */
public class Search {
    private String searchType;
    private String searchTerm;
    private ArrayList<Employee> results;
    private boolean employeeFound;

    /**
     * This constructor initializes the list of results and sets employeeFound to
     * false by default.
     */
    public Search() {
        results = new ArrayList<Employee>();
        employeeFound = false;
    }

    /**
     * This method adds an Employee object to the list of results.
     * 
     * @param employee The new Employee to add.
     */
    public void addFoundEmployee(Employee employee) {
        results.add(employee);
    }

    /**
     * This method retrieves the search type.
     * 
     * @return The search type.
     */
    public String getSearchType() {
        return this.searchType;
    }

    /**
     * This method sets searchType to a new value.
     * 
     * @param searchType The new value of searchType.
     */
    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    /**
     * This method retrieves the search term.
     * 
     * @return The search term.
     */
    public String getSearchTerm() {
        return this.searchTerm;
    }

    /**
     * This method sets searchTerm to a new value.
     * 
     * @param searchTerm The new value of searchTerm.
     */
    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     * This method retrieves the list of search results.
     * 
     * @return The list of search results.
     */
    public ArrayList<Employee> getResults() {
        return this.results;
    }

    /**
     * This method sets results to a new value.
     * 
     * @param results The new value of results.
     */
    public void setResults(ArrayList<Employee> results) {
        this.results = results;
    }

    /**
     * This method return if any results were found.
     * 
     * @return If any results were found or not.
     */
    public boolean isEmployeeFound() {
        return this.employeeFound;
    }

    /**
     * This method sets employeeFound to a new value.
     * 
     * @param employeeFound The new value of employeeFound.
     */
    public void setEmployeeFound(boolean employeeFound) {
        this.employeeFound = employeeFound;
    }
}
