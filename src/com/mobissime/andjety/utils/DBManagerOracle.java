/*
 * Andjety 2.0 - Code by Paul Coiffier - 2012/2013
 * 
 */
package com.mobissime.andjety.utils;

import java.sql.CallableStatement;
import java.sql.Connection;

/**
 *
 * @author Paul Coiffier
 */
public class DBManagerOracle {

    public static void print_dbms_output(Connection conn) {
        System.out.println("Dumping DBMS_OUTPUT to System.out : ");
        try {
            CallableStatement stmt = conn.prepareCall("{call sys.dbms_output.get_line(?,?)}");
            stmt.registerOutParameter(1, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(2, java.sql.Types.NUMERIC);
            int status = 0;
            do {
                stmt.execute();
                System.out.println(" " + stmt.getString(1));
                status = stmt.getInt(2);
            } while (status == 0);
            System.out.println("End of dbms_output!");
        } catch (Exception e) {
            System.out.println("Problem occurred during dump of dbms_output! " + e.toString());
        }
    }

    public static void print_dbms_output(Connection conn, CallableStatement cstmt) {
        System.out.println("Dumping DBMS_OUTPUT to System.out : ");
        try {
            CallableStatement stmt = conn.prepareCall("{call sys.dbms_output.get_line(?,?)}");
            stmt.registerOutParameter(1, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(2, java.sql.Types.NUMERIC);
            int status = 0;
            do {
                stmt.execute();
                System.out.println(" " + stmt.getString(1));
                status = stmt.getInt(2);
            } while (status == 0);
            System.out.println("End of dbms_output!");
        } catch (Exception e) {
            System.out.println("Problem occurred during dump of dbms_output! " + e.toString());
        }
    }
}
