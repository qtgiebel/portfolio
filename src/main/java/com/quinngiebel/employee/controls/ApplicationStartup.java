package com.quinngiebel.employee.controls;

import com.quinngiebel.utilities.PropertiesLoader;
import com.quinngiebel.employee.persistence.EmployeeDirectory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.Properties;

/**
 * This class sets attributes in the servlet context upon startup.
 * 
 * @author Quinn Giebel
 */
@WebServlet(name = "applicationStartup", urlPatterns = { "/application-startup" }, loadOnStartup = 1)
public class ApplicationStartup extends HttpServlet implements PropertiesLoader {
    private Properties properties;

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * This method loads the properties from the config file and adds them, along
     * with the employee directory and success message to the servlet context.
     * 
     * @exception ServletException if there is a Servlet failure
     */
    @Override
    public void init() throws ServletException {
        super.init();

        try {
            ServletContext context = getServletContext();

            properties = loadProperties("/control.properties");
            EmployeeDirectory directory = new EmployeeDirectory(properties);

            context.setAttribute("controlProperties", properties);
            context.setAttribute("directory", directory);
            context.setAttribute("message", "Employee added successfully.");
        } catch (ServletException e) {
            logger.error("Servlet Exception", e);
        } catch (Exception e) {
            logger.error("General Exception", e);
        }
    }
}