package com.quinngiebel.employee.persistence;

import com.quinngiebel.employee.entities.Employee;

import com.quinngiebel.employee.entities.Search;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This class allows the application to search and add to a database of
 * employees.
 * 
 * @author Quinn Giebel
 */
public class EmployeeDirectory {
    private Properties properties;

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * This constructor sets the properties variable.
     * 
     * @param properties The properties that must be referenced.
     */
    public EmployeeDirectory(Properties properties) {
        this.properties = properties;
    }

    /**
     * This method determines the type of search to be performed.
     * 
     * @param searchType The field to search by.
     * @param searchTerm The value to search for.
     * @return A Search object containing the list of search results.
     */
    public Search searchEmployee(String searchType, String searchTerm) {
        Search result = new Search();

        result.setSearchTerm(searchTerm);
        result.setSearchType(searchType);

        runSearch(result);

        return result;
    }

    /**
     * This method connects to the database and searches by employee ID.
     * 
     * @param result The Search object that will hold the results.
     */
    private void runSearch(Search result) {
        try (Connection con = connectToDB()) {
            sendPreparedStatement(con, result);
        } catch (Exception e) {
            logger.error("Error connecting to database...", e);
        }
    }

    /**
     * This method creates and executes a prepared statement to search the database.
     * 
     * @param con    The connection to the database.
     * @param result The Search object that will hold the results.
     */
    private void sendPreparedStatement(Connection con, Search result) {
        PreparedStatement searchStatement = null;
        try {
            searchStatement = prepareSearch(searchStatement, con, result);
            findEmployees(searchStatement, result);
        } catch (Exception e) {
            logger.error("General exception", e);
        } finally {
            try {
                if (searchStatement != null) {
                    searchStatement.close();
                }
            } catch (SQLException sqlE) {
                logger.error("Error closing prepared statement", sqlE);
            } catch (Exception e) {
                logger.error("General Exception", e);
            }
        }
    }

    /**
     * This method find the employees to add to the search object.
     * 
     * @param searchStatement The search query.
     * @param result          The Search object that will hold the results.
     */
    private void findEmployees(PreparedStatement searchStatement, Search result) {
        try (ResultSet queryReturn = searchStatement.executeQuery()) {
            addFoundEmployee(queryReturn, result);
        } catch (SQLException e) {
            logger.error("Error executing query", e);
        }
    }

    /**
     * This method adds each found employee to the search object.
     * 
     * @param queryReturn The ResultSet to access the returned rows.
     * @param result      The Search object that will hold the results.
     */
    private void addFoundEmployee(ResultSet queryReturn, Search result) {
        try {
            if (queryReturn.next()) {
                result.setEmployeeFound(true);
                Employee emp;
                do {
                    emp = new Employee();

                    emp.setEid(queryReturn.getString("emp_id"));
                    emp.setFirstName(queryReturn.getString("first_name"));
                    emp.setLastName(queryReturn.getString("last_name"));
                    emp.setSsn(queryReturn.getString("ssn"));
                    emp.setDept(queryReturn.getString("dept"));
                    emp.setRoom(queryReturn.getString("room"));
                    emp.setPhone(queryReturn.getString("phone"));

                    result.addFoundEmployee(emp);
                } while (queryReturn.next());
            }
        } catch (SQLException e) {
            logger.error("Error reading result set", e);
        }
    }

    /**
     * This method creates a prepared statement to search through the database.
     * 
     * @param searchStatement The query to be prepared.
     * @param con             The connection to the database.
     * @param result          The Search object that will hold the results.
     * @return The prepared search query.
     */
    private PreparedStatement prepareSearch(PreparedStatement searchStatement, Connection con, Search result) {
        try {
            searchStatement = con.prepareStatement(
                    "select * from employees where " + result.getSearchType() + " = " + result.getSearchTerm());
        } catch (SQLException e) {
            logger.error("Error preparing search query", e);
        }

        return searchStatement;
    }

    /**
     * This method adds an employee to the database.
     * 
     * @param firstName The employee's first name.
     * @param lastName  The employee's last name.
     * @param ssn       The employee's social security number.
     * @param dept      The employee's department.
     * @param room      The employee's room number.
     * @param phone     The employee's phone number.
     * @return A boolean indicating if the employee was added successfully.
     */
    public boolean addEmployee(String firstName, String lastName, String ssn, String dept, String room, String phone) {
        boolean retVal = false;

        try (Connection con = connectToDB()) {
            PreparedStatement insertEmployee = null;

            try {
                insertEmployee = prepareInsert(con, insertEmployee, firstName, lastName, ssn, dept, room, phone);
                insertEmployee.executeUpdate();
                retVal = true;
            } catch (SQLException sqlE) {
                logger.error("Error inserting new record", sqlE);
            } finally {
                try {
                    if (insertEmployee != null) {
                        insertEmployee.close();
                    }
                } catch (Exception e) {
                    logger.error("Error closing prepared statement", e);
                }
            }
        } catch (Exception e) {
            logger.error("General Exception", e);
        }

        return retVal;
    }

    /**
     * This method creates a prepared statement to add an employee the database.
     * 
     * @param con             The connection to the database.
     * @param insertStatement The query to be prepared.
     * @param firstName       The employee's first name.
     * @param lastName        The employee's last name.
     * @param ssn             The employee's social security number.
     * @param dept            The employee's department.
     * @param room            The employee's room number.
     * @param phone           The employee's phone number.
     * @return The prepared insert query.
     */
    private PreparedStatement prepareInsert(Connection con, PreparedStatement insertStatement, String firstName,
            String lastName, String ssn, String dept,
            String room, String phone) {

        try {
            insertStatement = con.prepareStatement(
                    "insert into employees (first_name, last_name, ssn, dept, room, phone) values (?, ?, ?, ?, ?, ?)");

            insertStatement.setString(1, firstName);
            insertStatement.setString(2, lastName);
            insertStatement.setString(3, ssn);
            insertStatement.setString(4, dept);
            insertStatement.setString(5, room);
            insertStatement.setString(6, phone);

        } catch (SQLException sqlE) {
            logger.error("Error preparing insert statement", sqlE);
        } catch (Exception e) {
            logger.error("General Error", e);
        }

        return insertStatement;
    }

    /**
     * This method connects to a database.
     * 
     * @return The connection to the database.
     */
    private Connection connectToDB() {
        Connection con;

        try {
            Class.forName(properties.getProperty("driver"));
            String dbName = properties.getProperty("RDS_DB_NAME");
            String userName = properties.getProperty("RDS_USERNAME");
            String password = properties.getProperty("RDS_PASSWORD");
            String hostname = properties.getProperty("RDS_HOSTNAME");
            String port = properties.getProperty("RDS_PORT");
            String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password="
                    + password;
            con = DriverManager.getConnection(jdbcUrl);
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
