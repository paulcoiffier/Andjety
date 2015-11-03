/*
 * Andjety 3.0 - Code by Paul Coiffier - 2012 - 2015
 * 
 */
package com.mobissime.andjety.dbUtils;

import com.mobissime.andjety.utils.OptionsParser;
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

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OracleConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
}
