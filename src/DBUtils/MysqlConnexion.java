/*
 * Andjety 2.0 - Code by Paul Coiffier - 2012/2013
 * 
 */
package DBUtils;

import Utils.OptionsParser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Paul Coiffier
 */
public class MysqlConnexion {

    public String erreurConnexion;

    public Connection getMysqlCon(String serveur, String base, String user, String password) {

        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://" + serveur + "/" + base, user, password);
            erreurConnexion = "";
        } catch (SQLException ex) {
            erreurConnexion = ex.getMessage().toString();
        } catch (InstantiationException ex) {
            erreurConnexion = ex.getMessage().toString();
        } catch (IllegalAccessException ex) {
            erreurConnexion = ex.getMessage().toString();
        } catch (ClassNotFoundException ex) {
            erreurConnexion = ex.getMessage().toString();
        }
        return connection;
    }
}
