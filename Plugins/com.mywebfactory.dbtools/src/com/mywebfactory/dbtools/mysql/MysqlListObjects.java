/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mywebfactory.dbtools.mysql;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import com.mywebfactory.objects.connectionObj;

/**
 *
 * @author Polo
 */
public class MysqlListObjects {

    public static ArrayList<String> listeMysqlTables(connectionObj conObj) {
        try {

            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);

            Connection conn = DriverManager.getConnection("jdbc:mysql://" + conObj.getDbServeur() + "/"
                    + conObj.getDbDatabase() + "?" + "user=" + conObj.getDbUser() + "&password=" + conObj.getDbPassword());

            ArrayList<String> listeOfResults = new ArrayList<String>();
            // Liste des tables du schéma
            String query = "select TABLE_NAME from INFORMATION_SCHEMA.TABLES where TABLE_SCHEMA='" + conObj.getDbDatabase() + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString(1);
                listeOfResults.add(id);
            }
            return listeOfResults;
            // Connexion DB OK
        } catch (ClassNotFoundException e) {
            System.out.println("Impossible de localiser le driver JDBC Mysql");
            JOptionPane.showMessageDialog(null, "Impossible de localiser le driver JDBC Mysql", "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (SQLException e) {
            System.out.println("Connexion à  la base de données impossible : " + e.getMessage() + " : détails : " + e.getMessage().toString());
            JOptionPane.showMessageDialog(null, "Connexion à  la base de donnéees impossible : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public static ArrayList<String> listeMysqlVues(connectionObj conObj) {
        try {

            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);

            Connection conn = DriverManager.getConnection("jdbc:mysql://" + conObj.getDbServeur() + "/"
                    + conObj.getDbDatabase() + "?" + "user=" + conObj.getDbUser() + "&password=" + conObj.getDbPassword());

            ArrayList<String> listeOfResults = new ArrayList<String>();
            // Liste des tables du schéma
            String query = "select TABLE_NAME from INFORMATION_SCHEMA.VIEWS where TABLE_SCHEMA='" + conObj.getDbDatabase() + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString(1);
                listeOfResults.add(id);
            }
            return listeOfResults;
            // Connexion DB OK
        } catch (ClassNotFoundException e) {
            System.out.println("Impossible de localiser le driver JDBC Mysql");
            JOptionPane.showMessageDialog(null, "Impossible de localiser le driver JDBC Mysql", "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (SQLException e) {
            System.out.println("Connexion à  la base de données impossible : " + e.getMessage() + " : détails : " + e.getMessage().toString());
            JOptionPane.showMessageDialog(null, "Connexion à  la base de donnéees impossible : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public static ArrayList<String> listeMysqlIndexes(connectionObj conObj) {
        try {

            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);

            Connection conn = DriverManager.getConnection("jdbc:mysql://" + conObj.getDbServeur() + "/"
                    + conObj.getDbDatabase() + "?" + "user=" + conObj.getDbUser() + "&password=" + conObj.getDbPassword());

            ArrayList<String> listeOfResults = new ArrayList<String>();
            // Liste des tables du schéma
            String query = "SELECT  table_name,index_name,GROUP_CONCAT(column_name ORDER BY seq_in_index) AS 'Columns' FROM information_schema.statistics WHERE table_schema = '" + conObj.getDbDatabase() + "' GROUP BY 1,2;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString(1);
                listeOfResults.add(id);
            }
            return listeOfResults;
            // Connexion DB OK
        } catch (ClassNotFoundException e) {
            System.out.println("Impossible de localiser le driver JDBC Mysql");
            JOptionPane.showMessageDialog(null, "Impossible de localiser le driver JDBC Mysql", "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (SQLException e) {
            System.out.println("Connexion à  la base de données impossible : " + e.getMessage() + " : détails : " + e.getMessage().toString());
            JOptionPane.showMessageDialog(null, "Connexion à  la base de donnéees impossible : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public static ArrayList<String> listeMysqlTriggers(connectionObj conObj) {
        try {

            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);

            Connection conn = DriverManager.getConnection("jdbc:mysql://" + conObj.getDbServeur() + "/"
                    + conObj.getDbDatabase() + "?" + "user=" + conObj.getDbUser() + "&password=" + conObj.getDbPassword());

            ArrayList<String> listeOfResults = new ArrayList<String>();
            // Liste des tables du schéma
            String query = "select trigger_name from information_schema.triggers where TRIGGER_SCHEMA='" + conObj.getDbDatabase() + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString(1);
                listeOfResults.add(id);
            }
            return listeOfResults;
            // Connexion DB OK
        } catch (ClassNotFoundException e) {
            System.out.println("Impossible de localiser le driver JDBC Mysql");
            JOptionPane.showMessageDialog(null, "Impossible de localiser le driver JDBC Mysql", "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (SQLException e) {
            System.out.println("Connexion à  la base de données impossible : " + e.getMessage() + " : détails : " + e.getMessage().toString());
            JOptionPane.showMessageDialog(null, "Connexion à  la base de donnéees impossible : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
