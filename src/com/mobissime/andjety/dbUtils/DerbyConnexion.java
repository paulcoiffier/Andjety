/*
 * Andjety 3.0 - Code by Paul Coiffier - 2012 - 2015
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
public class DerbyConnexion {
    public String erreurConnexion;

    public Connection getMysqlCon() {

        OptionsParser optionsParse = new OptionsParser();
        optionsParse.parseXml();

        Connection connection = null;
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            connection = DriverManager.getConnection("jdbc:derby://localhost/andjety;user=andjety;password=Polo021287");

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