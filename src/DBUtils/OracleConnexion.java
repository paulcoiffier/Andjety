/*
 * Andjety 2.0 - Code by Paul Coiffier - 2012/2013
 * 
 */
package DBUtils;

import Utils.OptionsParser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paul Coiffier
 */
public class OracleConnexion {

    public String erreurConnexion;

    public Connection getOracleCon(String user, String password, String instance, String hostnameip) {

        OptionsParser optionsParse = new OptionsParser();
        optionsParse.parseXml();
        erreurConnexion = "";

        Connection connection = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@" + hostnameip + ":1521:" + instance;
            System.out.println("URL : " + url);
            connection = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OracleConnexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            erreurConnexion = ex.getMessage().toString();
        } 
        return connection;
    }
}
