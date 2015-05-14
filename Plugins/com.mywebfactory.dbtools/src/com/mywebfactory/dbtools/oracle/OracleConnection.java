/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mywebfactory.dbtools.oracle;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import com.mywebfactory.objects.connectionObj;

/**
 *
 * @author Polo
 */
public class OracleConnection {

    public static boolean test_connection(String username, String password, String serveur, String sid) {

        String serverName = "", portNumber = "";

        try {

            Connection connection = null;
            // Load the JDBC driver
            String driverName = "oracle.jdbc.driver.OracleDriver";
            Class.forName(driverName);

            serverName = serveur;
            portNumber = "1521";
            String url = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":" + sid;
            //System.out.println("URL : " + url);
            connection = DriverManager.getConnection(url, username, password);
            return true;
            // Connexion DB OK
            //
        } catch (ClassNotFoundException e) {
            System.out.println("Impossible de localiser le driver JDBC Oracle");
            JOptionPane.showMessageDialog(null, "Impossible de localiser le driver JDBC Oracle", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (SQLException e) {
            System.out.println("Connexion à  la base de données impossible : " + e.getMessage() + " : détails : " + serverName + " port " + portNumber);
            JOptionPane.showMessageDialog(null, "Connexion à  la base de donnéees impossible : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}