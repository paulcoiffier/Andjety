/*
 * Andjety 3.0 - Code by Paul Coiffier - 2012 - 2015
 * 
 */
package com.mobissime.andjety.dbUtils;

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
            erreurConnexion = ex.getMessage();
        } catch (InstantiationException ex) {
            erreurConnexion = ex.getMessage();
        } catch (IllegalAccessException ex) {
            erreurConnexion = ex.getMessage();
        } catch (ClassNotFoundException ex) {
            erreurConnexion = ex.getMessage();
        }
        return connection;
    }
}
