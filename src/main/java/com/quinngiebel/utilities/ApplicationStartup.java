package com.quinngiebel.utilities;

import com.quinngiebel.admin.persistence.PieceDao;
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
@WebServlet(name = "ApplicationStartup", urlPatterns = { "/application-startup" }, loadOnStartup = 1)
public class ApplicationStartup extends HttpServlet implements PropertiesLoader {

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
            Properties properties = loadProperties("/cognito.properties");

            context.setAttribute("CLIENT_ID", properties.getProperty("client.id"));
            context.setAttribute("CLIENT_SECRET", properties.getProperty("client.secret"));
            context.setAttribute("OAUTH_URL", properties.getProperty("oauthURL"));
            context.setAttribute("LOGIN_URL", properties.getProperty("loginURL"));
            context.setAttribute("LOGOUT_URL", properties.getProperty("logoutURL"));
            context.setAttribute("REDIRECT_URL", properties.getProperty("redirectURL"));
            context.setAttribute("REVOKE_ACCESS_URL", properties.getProperty("revokeAccessURL"));
            context.setAttribute("REGION", properties.getProperty("region"));
            context.setAttribute("POOL_ID", properties.getProperty("poolId"));

            properties = loadProperties("/api.properties");

            context.setAttribute("S3_API_URL", properties.getProperty("api.url"));
            context.setAttribute("CDN_DOMAIN", properties.getProperty("cdn.url"));

            PieceDao hibernateSpool = new PieceDao();
            hibernateSpool.getById(1);

        } catch (ServletException e) {
            logger.error("Servlet Exception", e);
        } catch (Exception e) {
            logger.error("General Exception", e);
        }
    }
}