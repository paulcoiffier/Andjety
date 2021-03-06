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
public class OracleListObjects {
    public static ArrayList<String> listeOracleTables(connectionObj conObj) {

        String serverName = "", portNumber = "";

        try {

            Connection connection = null;
            // Load the JDBC driver
            String driverName = "oracle.jdbc.driver.OracleDriver";
            Class.forName(driverName);

            serverName = conObj.getDbServeur();
            portNumber = "1521";
            String url = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":" + conObj.getDbDatabase();
            //System.out.println("URL : " + url);
            connection = DriverManager.getConnection(url, conObj.getDbUser(), conObj.getDbPassword());

            ArrayList<String> listeOfResults = new ArrayList<String>();
            // Liste des tables du schéma
            String query = "select table_name from all_tables where OWNER='" + conObj.getDbUser().toUpperCase() + "'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            //System.out.println("Requete : " + query);
            while (rs.next()) {
                String id = rs.getString(1);
                listeOfResults.add(id);
                //System.out.println("Resultat : " + id);
            }
            return listeOfResults;
            // Connexion DB OK
        } catch (ClassNotFoundException e) {
            System.out.println("Impossible de localiser le driver JDBC Oracle");
            JOptionPane.showMessageDialog(null, "Impossible de localiser le driver JDBC Oracle", "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (SQLException e) {
            System.out.println("Connexion à  la base de données impossible : " + e.getMessage() + " : détails : " + serverName + " port " + portNumber);
            JOptionPane.showMessageDialog(null, "Connexion à  la base de donnéees impossible : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public static ArrayList<String> listeOracleVues(connectionObj conObj) {

        String serverName = "", portNumber = "";

        try {

            Connection connection = null;
            // Load the JDBC driver
            String driverName = "oracle.jdbc.driver.OracleDriver";
            Class.forName(driverName);

            serverName = conObj.getDbServeur();
            portNumber = "1521";
            String url = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":" + conObj.getDbDatabase();
            //System.out.println("URL : " + url);
            connection = DriverManager.getConnection(url, conObj.getDbUser(), conObj.getDbPassword());

            ArrayList<String> listeOfResults = new ArrayList<String>();
            // Liste des tables du schéma
            String query = "select view_name from all_views where OWNER='" + conObj.getDbUser().toUpperCase() + "'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            //System.out.println("Requete : " + query);
            while (rs.next()) {
                String id = rs.getString(1);
                listeOfResults.add(id);
                //System.out.println("Resultat : " + id);
            }
            return listeOfResults;
            // Connexion DB OK
        } catch (ClassNotFoundException e) {
            System.out.println("Impossible de localiser le driver JDBC Oracle");
            JOptionPane.showMessageDialog(null, "Impossible de localiser le driver JDBC Oracle", "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (SQLException e) {
            System.out.println("Connexion à  la base de données impossible : " + e.getMessage() + " : détails : " + serverName + " port " + portNumber);
            JOptionPane.showMessageDialog(null, "Connexion à  la base de donnéees impossible : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public static ArrayList<String> listeOracleIndexes(connectionObj conObj) {

        String serverName = "", portNumber = "";

        try {

            Connection connection = null;
            // Load the JDBC driver
            String driverName = "oracle.jdbc.driver.OracleDriver";
            Class.forName(driverName);

            serverName = conObj.getDbServeur();
            portNumber = "1521";
            String url = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":" + conObj.getDbDatabase();
            //System.out.println("URL : " + url);
            connection = DriverManager.getConnection(url, conObj.getDbUser(), conObj.getDbPassword());

            ArrayList<String> listeOfResults = new ArrayList<String>();
            // Liste des tables du schéma
            String query = "select index_name from all_indexes where OWNER='" + conObj.getDbUser().toUpperCase() + "'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            //System.out.println("Requete : " + query);
            while (rs.next()) {
                String id = rs.getString(1);
                listeOfResults.add(id);
                //System.out.println("Resultat : " + id);
            }
            return listeOfResults;
            // Connexion DB OK
        } catch (ClassNotFoundException e) {
            System.out.println("Impossible de localiser le driver JDBC Oracle");
            JOptionPane.showMessageDialog(null, "Impossible de localiser le driver JDBC Oracle", "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (SQLException e) {
            System.out.println("Connexion à  la base de données impossible : " + e.getMessage() + " : détails : " + serverName + " port " + portNumber);
            JOptionPane.showMessageDialog(null, "Connexion à  la base de donnéees impossible : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public static ArrayList<String> listeOracleTriggers(connectionObj conObj) {

        String serverName = "", portNumber = "";

        try {

            Connection connection = null;
            // Load the JDBC driver
            String driverName = "oracle.jdbc.driver.OracleDriver";
            Class.forName(driverName);

            serverName = conObj.getDbServeur();
            portNumber = "1521";
            String url = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":" + conObj.getDbDatabase();
            //System.out.println("URL : " + url);
            connection = DriverManager.getConnection(url, conObj.getDbUser(), conObj.getDbPassword());

            ArrayList<String> listeOfResults = new ArrayList<String>();
            // Liste des tables du schéma
            String query = "select trigger_name from all_triggers where OWNER='" + conObj.getDbUser().toUpperCase() + "'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            //System.out.println("Requete : " + query);
            while (rs.next()) {
                String id = rs.getString(1);
                listeOfResults.add(id);
                //System.out.println("Resultat : " + id);
            }
            return listeOfResults;
            // Connexion DB OK
        } catch (ClassNotFoundException e) {
            System.out.println("Impossible de localiser le driver JDBC Oracle");
            JOptionPane.showMessageDialog(null, "Impossible de localiser le driver JDBC Oracle", "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (SQLException e) {
            System.out.println("Connexion à  la base de données impossible : " + e.getMessage() + " : détails : " + serverName + " port " + portNumber);
            JOptionPane.showMessageDialog(null, "Connexion à  la base de donnéees impossible : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public static ArrayList<String> listeOracleProcedures(connectionObj conObj) {

        String serverName = "", portNumber = "";

        try {

            Connection connection = null;
            // Load the JDBC driver
            String driverName = "oracle.jdbc.driver.OracleDriver";
            Class.forName(driverName);

            serverName = conObj.getDbServeur();
            portNumber = "1521";
            String url = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":" + conObj.getDbDatabase();
            //System.out.println("URL : " + url);
            connection = DriverManager.getConnection(url, conObj.getDbUser(), conObj.getDbPassword());

            ArrayList<String> listeOfResults = new ArrayList<String>();
            // Liste des tables du schéma
            String query = "select procedure_name from all_procedures where OWNER='" + conObj.getDbUser().toUpperCase() + "'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            //System.out.println("Requete : " + query);
            while (rs.next()) {
                String id = rs.getString(1);
                listeOfResults.add(id);
                //System.out.println("Resultat : " + id);
            }
            return listeOfResults;
            // Connexion DB OK
        } catch (ClassNotFoundException e) {
            System.out.println("Impossible de localiser le driver JDBC Oracle");
            JOptionPane.showMessageDialog(null, "Impossible de localiser le driver JDBC Oracle", "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (SQLException e) {
            System.out.println("Connexion à  la base de données impossible : " + e.getMessage() + " : détails : " + serverName + " port " + portNumber);
            JOptionPane.showMessageDialog(null, "Connexion à  la base de donnéees impossible : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public static ArrayList<String> listeOracleFonctions(connectionObj conObj) {

        String serverName = "", portNumber = "";

        try {

            Connection connection = null;
            // Load the JDBC driver
            String driverName = "oracle.jdbc.driver.OracleDriver";
            Class.forName(driverName);

            serverName = conObj.getDbServeur();
            portNumber = "1521";
            String url = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":" + conObj.getDbDatabase();
            //System.out.println("URL : " + url);
            connection = DriverManager.getConnection(url, conObj.getDbUser(), conObj.getDbPassword());

            ArrayList<String> listeOfResults = new ArrayList<String>();
            // Liste des tables du schéma
            String query = "select object_name from all_objects where OBJECT_TYPE IN ('FUNCTION') and OWNER='" + conObj.getDbUser().toUpperCase() + "'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            //System.out.println("Requete : " + query);
            while (rs.next()) {
                String id = rs.getString(1);
                listeOfResults.add(id);
                //System.out.println("Resultat : " + id);
            }
            return listeOfResults;
            // Connexion DB OK
        } catch (ClassNotFoundException e) {
            System.out.println("Impossible de localiser le driver JDBC Oracle");
            JOptionPane.showMessageDialog(null, "Impossible de localiser le driver JDBC Oracle", "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (SQLException e) {
            System.out.println("Connexion à  la base de données impossible : " + e.getMessage() + " : détails : " + serverName + " port " + portNumber);
            JOptionPane.showMessageDialog(null, "Connexion à  la base de donnéees impossible : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public static ArrayList<String> listeOraclePackages(connectionObj conObj) {

        String serverName = "", portNumber = "";

        try {

            Connection connection = null;
            // Load the JDBC driver
            String driverName = "oracle.jdbc.driver.OracleDriver";
            Class.forName(driverName);

            serverName = conObj.getDbServeur();
            portNumber = "1521";
            String url = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":" + conObj.getDbDatabase();
            //System.out.println("URL : " + url);
            connection = DriverManager.getConnection(url, conObj.getDbUser(), conObj.getDbPassword());

            ArrayList<String> listeOfResults = new ArrayList<String>();
            // Liste des tables du schéma
            String query = "select object_name from all_objects where OBJECT_TYPE IN ('PACKAGE') and OWNER='" + conObj.getDbUser().toUpperCase() + "'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            //System.out.println("Requete : " + query);
            while (rs.next()) {
                String id = rs.getString(1);
                listeOfResults.add(id);
                //System.out.println("Resultat : " + id);
            }
            return listeOfResults;
            // Connexion DB OK
        } catch (ClassNotFoundException e) {
            System.out.println("Impossible de localiser le driver JDBC Oracle");
            JOptionPane.showMessageDialog(null, "Impossible de localiser le driver JDBC Oracle", "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (SQLException e) {
            System.out.println("Connexion à  la base de données impossible : " + e.getMessage() + " : détails : " + serverName + " port " + portNumber);
            JOptionPane.showMessageDialog(null, "Connexion à  la base de donnéees impossible : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public static ArrayList<String> listeOracleSequences(connectionObj conObj) {

        String serverName = "", portNumber = "";

        try {

            Connection connection = null;
            // Load the JDBC driver
            String driverName = "oracle.jdbc.driver.OracleDriver";
            Class.forName(driverName);

            serverName = conObj.getDbServeur();
            portNumber = "1521";
            String url = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":" + conObj.getDbDatabase();
            //System.out.println("URL : " + url);
            connection = DriverManager.getConnection(url, conObj.getDbUser(), conObj.getDbPassword());

            ArrayList<String> listeOfResults = new ArrayList<String>();
            // Liste des tables du schéma
            String query = "select object_name from all_objects where OBJECT_TYPE IN ('SEQUENCE') and OWNER='" + conObj.getDbUser().toUpperCase() + "'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            //System.out.println("Requete : " + query);
            while (rs.next()) {
                String id = rs.getString(1);
                listeOfResults.add(id);
                //System.out.println("Resultat : " + id);
            }
            return listeOfResults;
            // Connexion DB OK
        } catch (ClassNotFoundException e) {
            System.out.println("Impossible de localiser le driver JDBC Oracle");
            JOptionPane.showMessageDialog(null, "Impossible de localiser le driver JDBC Oracle", "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (SQLException e) {
            System.out.println("Connexion à  la base de données impossible : " + e.getMessage() + " : détails : " + serverName + " port " + portNumber);
            JOptionPane.showMessageDialog(null, "Connexion à  la base de donnéees impossible : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
