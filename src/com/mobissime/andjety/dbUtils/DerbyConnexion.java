/*
 * Andjety 3.0 - Code by Paul Coiffier - 2012 - 2015
 * 
 */
package com.mobissime.andjety.dbUtils;

import com.mobissime.andjety.Constants;
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
            connection = DriverManager.getConnection(Constants.derbyDatabaseUrl);

        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
            erreurConnexion = ex.getMessage();
        }
        return connection;
    }
    
}