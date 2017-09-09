package com.tahfizhonline.entity;

import java.sql.Connection;
import java.sql.DriverManager;

public class TesJDBC {
    String jdbcUrl = "jdbc:sqlite:mtqdb.db";
    String user = "";
    String pass = "";

    public TesJDBC() {
        try {
            System.out.println("Connecting to database: " + jdbcUrl);

            Connection myConn =
                    DriverManager.getConnection(jdbcUrl, user, pass);

            System.out.println("Connection successful!!!");

        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }




}
