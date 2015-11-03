/*
 * Andjety 2.0 - Code by Paul Coiffier - 2012/2013
 * 
 */
package com.mobissime.andjety.dbUtils;

import com.mobissime.andjety.utils.OptionsParser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Paul Coiffier
 */
public class UraConnexion {

    public String erreurConnexion;

    public Connection getMysqlCon() {

        OptionsParser optionsParse = new OptionsParser();
        optionsParse.parseXml();

        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://" + optionsParse.serveur + "/base_srv", optionsParse.user, optionsParse.password);

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
