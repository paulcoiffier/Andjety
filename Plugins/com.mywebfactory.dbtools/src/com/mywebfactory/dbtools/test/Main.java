package com.mywebfactory.dbtools.test;

import com.mywebfactory.dbtools.gui.DBVisualizerPanel;
import java.util.ArrayList;
import javax.swing.JFrame;
import com.mywebfactory.objects.connectionObj;

/**
 *
 * @author Polo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ArrayList<connectionObj> arrayOfConnections = new ArrayList<connectionObj>();
        connectionObj newOracleConn = new connectionObj();
        connectionObj newMysqlConn = new connectionObj();
        
        newOracleConn.setDbName("Localhost Oracle");
        newOracleConn.setDbServeur("localhost");
        newOracleConn.setDbType("ORACLE");
        newOracleConn.setDbUser("carbase");
        newOracleConn.setDbPassword("carbase");
        newOracleConn.setDbDatabase("XE");
        
        newMysqlConn.setDbName("Localhost Mysql");
        newMysqlConn.setDbServeur("localhost");
        newMysqlConn.setDbType("MYSQL");
        newMysqlConn.setDbUser("root");
        newMysqlConn.setDbPassword("password");
        newMysqlConn.setDbDatabase("test");
        
        arrayOfConnections.add(newMysqlConn);
        arrayOfConnections.add(newOracleConn);
        
        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DBVisualizerPanel jpane = new DBVisualizerPanel(arrayOfConnections);
        jf.setContentPane(jpane);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        jf.setLocation(
                (screenSize.width - jf.getWidth()) / 2,
                (screenSize.height - jf.getHeight()) / 2);

        jf.setTitle("My Web Factory - Editeur");
        jf.setExtendedState(jf.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        jf.show();
    }
}
