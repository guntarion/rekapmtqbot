package com.tahfizhonline.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteJDBCDriverConnection {

    public static final String DB_DNAME = "mtqdb.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/Users/guntar/Desktop/" + DB_DNAME;

    public static void connect() {
        Connection conn = null;
        try {



            // db parameters
//            String url = "jdbc:sqlite:/Users/guntar/Desktop/mtqdb.db";
            // create a connection to the database
            conn = DriverManager.getConnection(CONNECTION_STRING);

//            Connection conn = DriverManager.getConnection(CONNECTION_STRING);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
