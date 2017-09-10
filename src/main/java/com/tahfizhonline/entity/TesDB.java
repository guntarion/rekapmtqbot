package com.tahfizhonline.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TesDB {

    public static final String DB_DNAME = "mtqdb.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/Users/guntar/Desktop/" + DB_DNAME;
    public static final String TABLE_SANTRI = "santrireguler";
    public static final String COLUMN_IDSANTRI = "id_santri";
    public static final String COLUMN_NAMA = "nama";
    public static final String COLUMN_USERNAME = "username";

    public void tesDatabase() {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(SantriReguler.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // create a student object
            System.out.println("Creating new student object...");
            SantriReguler tempStudent = new SantriReguler(353, "Amanda", "@amanda");

            // start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving Santri...");
            session.save(tempStudent);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }

    public void cobaMasukinData() {
        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();
//            cobaInsertSantri(statement, 353, "Amanda", "@amanda");
//            cobaUpdateSantri(statement, 353, "Ananta");
            cobaDeleteSantri(statement, 123);
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }


    private static void cobaInsertSantri(Statement statement, int id, String nama, String username) throws SQLException {
        statement.execute("INSERT INTO " + TABLE_SANTRI +
                " (" + COLUMN_IDSANTRI + ", " +
                COLUMN_NAMA + ", " +
                COLUMN_USERNAME +
                " ) " +
                "VALUES(" + id + ", '" + nama + "', '" + username + "')");
    }

    private static void cobaUpdateSantri(Statement statement, int id, String nama) throws SQLException {
        statement.execute("UPDATE " + TABLE_SANTRI +
                " SET " + COLUMN_NAMA + "='" + nama + "' WHERE id_santri="+ id);
    }

    private static void cobaDeleteSantri(Statement statement, int id) throws SQLException {
        statement.execute("DELETE FROM " + TABLE_SANTRI + " WHERE id_santri="+ id);
    }



}
