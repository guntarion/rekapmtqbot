package com.tahfizhonline.entitydefinition;

import com.tahfizhonline.MTQBotRekap;
import com.tahfizhonline.fungsidukung.OperasiTanggal;
import org.apache.log4j.Logger;
import com.vdurmont.emoji.EmojiParser;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;


public class SqliteJdbc {

    final static Logger logger = Logger.getLogger(SqliteJdbc.class);
    private static org.apache.log4j.Logger log = Logger.getLogger(MTQBotRekap.class);

//    final Properties p = new Properties(); p.setProperty("journal_mode", "WAL"); DriverManager.getConnection("jdbc:sqlite:file.db", p);

    public static final String DB_DNAME = "mtqdb.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/Users/guntar/Desktop/" + DB_DNAME;

    public static final String TABEL_SANTRI = "santrireguler";
    public static final String TABEL_OKUPANSI = "okupansikelas";

    public static final String KOLOM_ID_OKUPANSI = "id_okupansi";

    public static final String COLUMN_IDSANTRI = "id_santri";
    public static final String COLUMN_NAMA = "nama";
    public static final String COLUMN_USERNAME = "username";


    public void createKelasOkupansi(String namaKelas) {
        try {
            String indikasiNamaKelas =  namaKelas.substring(0,2);
            String indikasiKategori = namaKelas.substring(2,3);
            String indikasiGender = namaKelas.substring(3,4);

            System.out.println(indikasiNamaKelas);
            System.out.println(indikasiKategori);
            System.out.println(indikasiGender);

            String kategoriKelas = "";
            if (indikasiKategori.indexOf("a") >=0 || indikasiKategori.indexOf("A") >=0) {
                kategoriKelas = "A";
            }
            if (indikasiKategori.indexOf("b") >=0 || indikasiKategori.indexOf("B") >=0) {
                kategoriKelas = "B";
            }

            String kategoriGender = "";
            if (indikasiGender.indexOf("i") >=0 || indikasiGender.indexOf("I") >=0) {
                kategoriGender = "1";
            }
            if (indikasiGender.indexOf("a") >=0 || indikasiGender.indexOf("A") >=0) {
                kategoriGender = "2";
            }

//            String genderInner = "";
//            if (gender.equals("1")) genderInner="i"; else genderInner="a";

            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();
            statement.execute("INSERT INTO okupansikelas (id_okupansi, kelas, terisi, kategori) " +
                    "VALUES ('" + indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-01', '"
                    + indikasiNamaKelas + kategoriKelas + indikasiGender + "', 0, '" + kategoriKelas + "')");
            statement.execute("INSERT INTO okupansikelas (id_okupansi, kelas, terisi, kategori) " +
                    "VALUES ('" + indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-02', '"
                    + indikasiNamaKelas + kategoriKelas + indikasiGender + "', 0, '" + kategoriKelas + "')");
            statement.execute("INSERT INTO okupansikelas (id_okupansi, kelas, terisi, kategori) " +
                    "VALUES ('" + indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-03', '"
                    + indikasiNamaKelas + kategoriKelas + indikasiGender + "', 0, '" + kategoriKelas + "')");
            statement.execute("INSERT INTO okupansikelas (id_okupansi, kelas, terisi, kategori) " +
                    "VALUES ('" + indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-04', '"
                    + indikasiNamaKelas + kategoriKelas + indikasiGender + "', 0, '" + kategoriKelas + "')");
            statement.execute("INSERT INTO okupansikelas (id_okupansi, kelas, terisi, kategori) " +
                    "VALUES ('" + indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-05', '"
                    + indikasiNamaKelas + kategoriKelas + indikasiGender + "', 0, '" + kategoriKelas + "')");
            statement.execute("INSERT INTO okupansikelas (id_okupansi, kelas, terisi, kategori) " +
                    "VALUES ('" + indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-06', '"
                    + indikasiNamaKelas + kategoriKelas + indikasiGender + "', 0, '" + kategoriKelas + "')");
            statement.execute("INSERT INTO okupansikelas (id_okupansi, kelas, terisi, kategori) " +
                    "VALUES ('" + indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-07', '"
                    + indikasiNamaKelas + kategoriKelas + indikasiGender + "', 0, '" + kategoriKelas + "')");
            statement.execute("INSERT INTO okupansikelas (id_okupansi, kelas, terisi, kategori) " +
                    "VALUES ('" + indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-08', '"
                    + indikasiNamaKelas + kategoriKelas + indikasiGender + "', 0, '" + kategoriKelas + "')");
            statement.execute("INSERT INTO okupansikelas (id_okupansi, kelas, terisi, kategori) " +
                    "VALUES ('" + indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-09', '"
                    + indikasiNamaKelas + kategoriKelas + indikasiGender + "', 0, '" + kategoriKelas + "')");
            statement.execute("INSERT INTO okupansikelas (id_okupansi, kelas, terisi, kategori) " +
                    "VALUES ('" + indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-10', '"
                    + indikasiNamaKelas + kategoriKelas + indikasiGender + "', 0, '" + kategoriKelas + "')");

            statement.close();
            conn.close();

        } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }
    }

    public void registerKelas(String namaKelas, long alamatGrup) {
        String indikasiNamaKelas =  namaKelas.substring(0,2);
        String indikasiKategori = namaKelas.substring(2,3);
        String indikasiGender = namaKelas.substring(3,4);

        String kategoriKelas = "";
        if (indikasiKategori.indexOf("a") >=0 || indikasiKategori.indexOf("A") >=0) {
            kategoriKelas = "A";
        }
        if (indikasiKategori.indexOf("b") >=0 || indikasiKategori.indexOf("B") >=0) {
            kategoriKelas = "B";
        }

        String kategoriGender = "";
        if (indikasiGender.indexOf("i") >=0 || indikasiGender.indexOf("I") >=0) {
            kategoriGender = "1";
        }
        if (indikasiGender.indexOf("a") >=0 || indikasiGender.indexOf("A") >=0) {
            kategoriGender = "2";
        }

        String namaKelasBentukan = indikasiNamaKelas + kategoriKelas + indikasiGender;

        try {


            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();
            // Bikin Kelas
            statement.execute("INSERT INTO infokelas (nama_kelas, alamat, status) VALUES ('"
                    + namaKelasBentukan + "', "+ alamatGrup + ", 1)");

            // Bikin Okupansi
            statement.execute("INSERT INTO okupansikelas (id_okupansi, kelas, terisi, kategori) " +
                    "VALUES ('" + indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-01', '"
                    + indikasiNamaKelas + kategoriKelas + indikasiGender + "', 0, '" + kategoriKelas + "')");
            statement.execute("INSERT INTO okupansikelas (id_okupansi, kelas, terisi, kategori) " +
                    "VALUES ('" + indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-02', '"
                    + indikasiNamaKelas + kategoriKelas + indikasiGender + "', 0, '" + kategoriKelas + "')");
            statement.execute("INSERT INTO okupansikelas (id_okupansi, kelas, terisi, kategori) " +
                    "VALUES ('" + indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-03', '"
                    + indikasiNamaKelas + kategoriKelas + indikasiGender + "', 0, '" + kategoriKelas + "')");
            statement.execute("INSERT INTO okupansikelas (id_okupansi, kelas, terisi, kategori) " +
                    "VALUES ('" + indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-04', '"
                    + indikasiNamaKelas + kategoriKelas + indikasiGender + "', 0, '" + kategoriKelas + "')");
            statement.execute("INSERT INTO okupansikelas (id_okupansi, kelas, terisi, kategori) " +
                    "VALUES ('" + indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-05', '"
                    + indikasiNamaKelas + kategoriKelas + indikasiGender + "', 0, '" + kategoriKelas + "')");
            statement.execute("INSERT INTO okupansikelas (id_okupansi, kelas, terisi, kategori) " +
                    "VALUES ('" + indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-06', '"
                    + indikasiNamaKelas + kategoriKelas + indikasiGender + "', 0, '" + kategoriKelas + "')");
            statement.execute("INSERT INTO okupansikelas (id_okupansi, kelas, terisi, kategori) " +
                    "VALUES ('" + indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-07', '"
                    + indikasiNamaKelas + kategoriKelas + indikasiGender + "', 0, '" + kategoriKelas + "')");
            statement.execute("INSERT INTO okupansikelas (id_okupansi, kelas, terisi, kategori) " +
                    "VALUES ('" + indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-08', '"
                    + indikasiNamaKelas + kategoriKelas + indikasiGender + "', 0, '" + kategoriKelas + "')");
            statement.execute("INSERT INTO okupansikelas (id_okupansi, kelas, terisi, kategori) " +
                    "VALUES ('" + indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-09', '"
                    + indikasiNamaKelas + kategoriKelas + indikasiGender + "', 0, '" + kategoriKelas + "')");
            statement.execute("INSERT INTO okupansikelas (id_okupansi, kelas, terisi, kategori) " +
                    "VALUES ('" + indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-10', '"
                    + indikasiNamaKelas + kategoriKelas + indikasiGender + "', 0, '" + kategoriKelas + "')");

            statement.close();
            conn.close();
        } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }

    }

    public void enableKelas(String namaKelas, long alamatGrup) {

        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();
            statement.execute("UPDATE infokelas SET alamat=" + alamatGrup + " WHERE nama_kelas='" + namaKelas + "'");
            statement.execute("UPDATE infokelas SET status=1 WHERE nama_kelas='" + namaKelas + "'");
            statement.close();
            conn.close();
        } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }
    }

    public void disableKelas(String namaKelas) {
        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();
            statement.execute("UPDATE infokelas SET status=0 WHERE nama_kelas='" + namaKelas + "'");
            statement.execute("UPDATE infokelas SET alamat=0 WHERE nama_kelas='" + namaKelas + "'");
            statement.close();
            conn.close();
        } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }
    }

    public String listSeluruhKelas() {
        String listKelas = "";
        int counter = 0;
        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();

            try {
                StringBuilder sb = new StringBuilder("Kelas | Status\n---------------\n");
                ResultSet qListKelas = statement.executeQuery("SELECT * FROM infokelas");
                while (qListKelas.next()) {
                    counter++;
                    sb.append(qListKelas.getString(2)
                            + " (" + String.valueOf(qListKelas.getInt("status")) + ") \n");
                }
                qListKelas.close();
                sb.append("---------------\n");
                sb.append("Total = " + counter);
                listKelas = sb.toString();
            } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }


        } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }

        return listKelas;
    }

    public void deleteKelasTerpilih(String namaKelas) {
        String indikasiNamaKelas =  namaKelas.substring(0,2);
        String indikasiKategori = namaKelas.substring(2,3);
        String indikasiGender = namaKelas.substring(3,4);

        String kategoriKelas = "";
        if (indikasiKategori.indexOf("a") >=0 || indikasiKategori.indexOf("A") >=0) {
            kategoriKelas = "A";
        }
        if (indikasiKategori.indexOf("b") >=0 || indikasiKategori.indexOf("B") >=0) {
            kategoriKelas = "B";
        }

        String kategoriGender = "";
        if (indikasiGender.indexOf("i") >=0 || indikasiGender.indexOf("I") >=0) {
            kategoriGender = "1";
        }
        if (indikasiGender.indexOf("a") >=0 || indikasiGender.indexOf("A") >=0) {
            kategoriGender = "2";
        }
        String namaKelasBentukan = indikasiNamaKelas + kategoriKelas + indikasiGender;

        System.out.println(namaKelas);
        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();
            statement.execute("DELETE FROM infokelas WHERE nama_kelas='" + namaKelasBentukan + "'");
            statement.execute("DELETE FROM okupansikelas WHERE id_okupansi='" + indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-01'");
            statement.execute("DELETE FROM okupansikelas WHERE id_okupansi='" + indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-02'");
            statement.execute("DELETE FROM okupansikelas WHERE id_okupansi='" + indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-03'");
            statement.execute("DELETE FROM okupansikelas WHERE id_okupansi='" + indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-04'");
            statement.execute("DELETE FROM okupansikelas WHERE id_okupansi='" + indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-05'");
            statement.execute("DELETE FROM okupansikelas WHERE id_okupansi='" + indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-06'");
            statement.execute("DELETE FROM okupansikelas WHERE id_okupansi='" + indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-07'");
            statement.execute("DELETE FROM okupansikelas WHERE id_okupansi='" + indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-08'");
            statement.execute("DELETE FROM okupansikelas WHERE id_okupansi='" + indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-09'");
            statement.execute("DELETE FROM okupansikelas WHERE id_okupansi='" + indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-10'");
            statement.close();
            conn.close();
        } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }
    }

    public static void insertSantriToKelas(String id_okupansi, String userName)  {
        try {
            String kategoriKelas =  id_okupansi.substring(2,3);

            int infoTerisi = 1;
            if (userName.equals("?")) {
                infoTerisi = 0; userName = "(EMPTY)";
            }
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();
            statement.execute("UPDATE okupansikelas SET userName='" + userName + "' WHERE id_okupansi='" + id_okupansi + "'");
            statement.execute("UPDATE okupansikelas SET terisi=" + infoTerisi + " WHERE id_okupansi='" + id_okupansi + "'");
            statement.execute("UPDATE okupansikelas SET kategori='" + kategoriKelas + "' WHERE id_okupansi='" + id_okupansi + "'");
            statement.execute("UPDATE okupansikelas SET nama_santri='" + userName + "' WHERE id_okupansi='" + id_okupansi + "'");
            statement.execute("UPDATE okupansikelas SET ghoib_frek=0 WHERE id_okupansi='" + id_okupansi + "'");
            statement.execute("UPDATE okupansikelas SET ghoib_kapan='' WHERE id_okupansi='" + id_okupansi + "'");
            statement.execute("UPDATE okupansikelas SET udzur_frek=0 WHERE id_okupansi='" + id_okupansi + "'");
            statement.execute("UPDATE okupansikelas SET udzur_kapan='' WHERE id_okupansi='" + id_okupansi + "'");
            statement.execute("UPDATE okupansikelas SET is_imtihan=0 WHERE id_okupansi='" + id_okupansi + "'");
            statement.execute("UPDATE okupansikelas SET today_setoran=0 WHERE id_okupansi='" + id_okupansi + "'");
            statement.execute("UPDATE okupansikelas SET today_mrjharian=0 WHERE id_okupansi='" + id_okupansi + "'");
            statement.execute("UPDATE okupansikelas SET today_simak=0 WHERE id_okupansi='" + id_okupansi + "'");
            statement.execute("UPDATE okupansikelas SET status_setoran=0 WHERE id_okupansi='" + id_okupansi + "'");
            statement.execute("UPDATE okupansikelas SET p_no_suroh=0 WHERE id_okupansi='" + id_okupansi + "'");
            statement.execute("UPDATE okupansikelas SET p_ayat_begin=0 WHERE id_okupansi='" + id_okupansi + "'");
            statement.execute("UPDATE okupansikelas SET p_ayat_end=0 WHERE id_okupansi='" + id_okupansi + "'");
            statement.execute("UPDATE okupansikelas SET mrjap_suroh_begin=114 WHERE id_okupansi='" + id_okupansi + "'");
            statement.execute("UPDATE okupansikelas SET mrjap_suroh_end=114 WHERE id_okupansi='" + id_okupansi + "'");
            statement.execute("UPDATE okupansikelas SET mrjap_ayat_begin=1 WHERE id_okupansi='" + id_okupansi + "'");
            statement.execute("UPDATE okupansikelas SET mrjap_ayat_end=1 WHERE id_okupansi='" + id_okupansi + "'");
            statement.execute("UPDATE okupansikelas SET mrjap_setor=0 WHERE id_okupansi='" + id_okupansi + "'");
            statement.execute("UPDATE okupansikelas SET mrjap_simak=0 WHERE id_okupansi='" + id_okupansi + "'");
            statement.execute("UPDATE okupansikelas SET is_wajib_mrjharian=0 WHERE id_okupansi='" + id_okupansi + "'");

            statement.close();
            conn.close();

        } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }
    }

    public void insertBulkSantriToKelas(String namaKelas, String userNames) {
        String[] daftarNama = userNames.split(",");
        String[] daftarIDokupansi = new String[10];
        // 05bi
//        System.out.println("Masuk di sini");
//        System.out.println("----" + daftarNama[0] + " " + daftarNama[9]);
        String indikasiNamaKelas =  namaKelas.substring(0,2);
        String indikasiKategori = namaKelas.substring(2,3);
        String indikasiGender = namaKelas.substring(3,4);

        String kategoriKelas = "";
        if (indikasiKategori.indexOf("a") >=0 || indikasiKategori.indexOf("A") >=0) {
            kategoriKelas = "A";
        }
        if (indikasiKategori.indexOf("b") >=0 || indikasiKategori.indexOf("B") >=0) {
            kategoriKelas = "B";
        }

        String kategoriGender = "";
        if (indikasiGender.indexOf("i") >=0 || indikasiGender.indexOf("I") >=0) {
            kategoriGender = "1";
        }
        if (indikasiGender.indexOf("a") >=0 || indikasiGender.indexOf("A") >=0) {
            kategoriGender = "2";
        }

        // buat id okupansinya
        daftarIDokupansi[0] = indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-" + "01";
        daftarIDokupansi[1] = indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-" + "02";
        daftarIDokupansi[2] = indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-" + "03";
        daftarIDokupansi[3] = indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-" + "04";
        daftarIDokupansi[4] = indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-" + "05";
        daftarIDokupansi[5] = indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-" + "06";
        daftarIDokupansi[6] = indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-" + "07";
        daftarIDokupansi[7] = indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-" + "08";
        daftarIDokupansi[8] = indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-" + "09";
        daftarIDokupansi[9] = indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-" + "10";

        // lakukan pengisian
        for (int i = 0; i < 10; i++) {
            insertSantriToKelas(daftarIDokupansi[i], daftarNama[i]);
            System.out.println(daftarIDokupansi[i] + " - " + daftarNama[i]);
        }
    }

    public String listingSlotKelasOkupansi(String namaKelas) {

        String indikasiNamaKelas =  namaKelas.substring(0,2);
        String indikasiKategori = namaKelas.substring(2,3);
        String indikasiGender = namaKelas.substring(3,4);

        String kategoriKelas = "";
        if (indikasiKategori.indexOf("a") >=0 || indikasiKategori.indexOf("A") >=0) {
            kategoriKelas = "A";
        }
        if (indikasiKategori.indexOf("b") >=0 || indikasiKategori.indexOf("B") >=0) {
            kategoriKelas = "B";
        }

        String namaKelasBentukan = indikasiNamaKelas + kategoriKelas + indikasiGender;

        String listOkupansi = "";
        String statusBooking = "";

        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();

            try {
                StringBuilder sb = new StringBuilder("Okupansi | Status\n-------------------------\n");
                ResultSet qListOkupansi = statement.executeQuery("SELECT * FROM okupansikelas WHERE kelas='"
                        + namaKelasBentukan + "'");

                while (qListOkupansi.next()) {
                    int getStatus = qListOkupansi.getInt("terisi");
                    if (getStatus==1) statusBooking = "[BOOKED]"; else statusBooking = "[----------]";

                    sb.append(qListOkupansi.getString("id_okupansi")
                            + " - " + statusBooking + "\n");
                }
                qListOkupansi.close();
                sb.append("-------------------------\n");
                listOkupansi = sb.toString();
            } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }


        } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }
        return listOkupansi;
    }

    // TODO: remove user dari grup
    public void removeSantriFromGroup(String userName) {

    }

    // TODO: check whether santri is listed in database
    public void checkSantriOnDatabase(String userName) {

    }

    // TODO: kasih notifikasi ke santri bhw di bermasalah (udzur dan ghoib) dan juga ke grup pengurus
    public void notifSantriDiaBermasalah(String userName) {

    }

    public void removeSantriFromKelas(String userName) {
        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();
            statement.execute("UPDATE okupansikelas SET terisi=0 WHERE username='" + userName + "'");
            statement.close(); conn.close();
        } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }
    }

    public static void insertSetoranHarian(String username, String namaSantri,int noSuroh, int awalAyat, int akhirAyat) {
        int berapaKaliSetorToday = 0;
        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();

            ResultSet qEksistingSetoran = statement.executeQuery("SELECT today_setoran, username FROM okupansikelas WHERE username='" + username + "'");
            if (qEksistingSetoran.next()) {
                boolean found = qEksistingSetoran.getBoolean("username");
                if (found) {

                } else {

                }
            }
            //            System.out.println(qEksistingSetoran.getInt("today_setoran"));
            berapaKaliSetorToday = qEksistingSetoran.getInt("today_setoran");
            berapaKaliSetorToday++;
            qEksistingSetoran.close();


            statement.execute("UPDATE okupansikelas SET today_setoran=" + berapaKaliSetorToday + " WHERE username='" + username + "'");
            statement.execute("UPDATE okupansikelas SET nama_santri='" + namaSantri + "' WHERE username='" + username + "'");
            statement.execute("UPDATE okupansikelas SET p_no_suroh=" + noSuroh + " WHERE username='" + username + "'");
            statement.execute("UPDATE okupansikelas SET p_ayat_begin=" + awalAyat + " WHERE username='" + username + "'");
            statement.execute("UPDATE okupansikelas SET p_ayat_end=" + akhirAyat + " WHERE username='" + username + "'");
            statement.execute("UPDATE okupansikelas SET mrjap_suroh_end=" + noSuroh + " WHERE username='" + username + "'");
            statement.execute("UPDATE okupansikelas SET mrjap_ayat_end=" + akhirAyat + " WHERE username='" + username + "'");

            if (noSuroh==77) {
                statement.execute("UPDATE okupansikelas SET is_wajib_mrjharian=1 WHERE username='" + username + "'");
            }

            statement.close();
            conn.close();

        } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }
    }

    public void simakSetoran(String userName) {
        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();
            statement.execute("UPDATE okupansikelas SET today_simak=1 WHERE username='" + userName + "'");
            statement.close(); conn.close();
        } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }
    }

    public void mrjSetoran(String userName, int noHalaman) {
        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();
            statement.execute("UPDATE okupansikelas SET today_mrjharian=1 WHERE username='" + userName + "'");
            statement.execute("UPDATE okupansikelas SET mrj_halaman=" + noHalaman + " WHERE username='" + userName + "'");
            statement.close(); conn.close();
        } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }
    }

    public void setUdzurSakit(String userName) {
        OperasiTanggal operasiTanggal = new OperasiTanggal();
        String kapanSajaUdzur = "";
        int berapaKaliUdzur = 0;

        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();

            ResultSet result = statement.executeQuery("SELECT udzur_kapan, udzur_frek FROM okupansikelas WHERE username='" + userName + "'");
            kapanSajaUdzur = result.getString("udzur_kapan");
            System.out.println("berapa kali udzur = " + kapanSajaUdzur);
            StringBuilder sb = new StringBuilder(kapanSajaUdzur);
            sb.append(" " + operasiTanggal.getTodayDate());

            berapaKaliUdzur = result.getInt("udzur_frek");
            System.out.println("berapa kali udzur = " + berapaKaliUdzur);
            berapaKaliUdzur = berapaKaliUdzur + 1;

            statement.execute("UPDATE okupansikelas SET today_setoran=10 WHERE username='" + userName + "'");
            statement.execute("UPDATE okupansikelas SET udzur_frek=" + berapaKaliUdzur + " WHERE username='" + userName + "'");
            statement.execute("UPDATE okupansikelas SET udzur_kapan='"+ sb.toString() + "' WHERE username='" + userName + "'");

            result.close(); statement.close(); conn.close();
        } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }
    }

    public void setGhoib(String userName) {
        OperasiTanggal operasiTanggal = new OperasiTanggal();
        String kapanSajaGhoib = "";
        int berapaKaliGhoib = 0;

        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();

            ResultSet result = statement.executeQuery("SELECT ghoib_kapan, ghoib_frek FROM okupansikelas WHERE username='" + userName + "'");
            kapanSajaGhoib = result.getString("ghoib_kapan");
            System.out.println("kapan saja ghoib = " + kapanSajaGhoib);
            StringBuilder sb = new StringBuilder(kapanSajaGhoib);
            sb.append(" " + operasiTanggal.getTodayDate());

            berapaKaliGhoib = result.getInt("ghoib_frek");
            System.out.println("berapa kali ghoib = " + berapaKaliGhoib);
            berapaKaliGhoib = berapaKaliGhoib + 1;

            statement.execute("UPDATE okupansikelas SET ghoib_frek=" + berapaKaliGhoib + " WHERE username='" + userName + "'");
            statement.execute("UPDATE okupansikelas SET ghoib_kapan='"+ sb.toString() + "' WHERE username='" + userName + "'");

            result.close(); statement.close(); conn.close();
        } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }
    }

    public void setGhoibPoinSeparuh(String userName) {
        OperasiTanggal operasiTanggal = new OperasiTanggal();
        String kapanSajaGhoib = "";
        float berapaKaliGhoib = 0;

        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();

            ResultSet result = statement.executeQuery("SELECT ghoib_kapan, ghoib_frek FROM okupansikelas WHERE username='" + userName + "'");
            kapanSajaGhoib = result.getString("ghoib_kapan");
            System.out.println("kapan saja ghoib = " + kapanSajaGhoib);
            StringBuilder sb = new StringBuilder(kapanSajaGhoib);
            sb.append(" " + operasiTanggal.getTodayDate());

            berapaKaliGhoib = result.getInt("ghoib_frek");
            System.out.println("berapa kali ghoib = " + berapaKaliGhoib);
            berapaKaliGhoib = berapaKaliGhoib + 0.5f;

            statement.execute("UPDATE okupansikelas SET ghoib_frek=" + berapaKaliGhoib + " WHERE username='" + userName + "'");
            statement.execute("UPDATE okupansikelas SET ghoib_kapan='"+ sb.toString() + "' WHERE username='" + userName + "'");

            result.close(); statement.close(); conn.close();
        } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }
    }

    public void setValueUdzurSantri(String userName, int valueModif) {
        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();
            statement.execute("UPDATE okupansikelas SET udzur_frek=" + valueModif + " WHERE username='" + userName + "'");
            statement.close(); conn.close();
        } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }
    }

    public void setValueGhoibSantri(String userName, int valueModif) {
        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();
            statement.execute("UPDATE okupansikelas SET ghoib_frek=" + valueModif + " WHERE username='" + userName + "'");
            statement.close(); conn.close();
        } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }
    }

    public void resetValueSetoran() {
        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();
            statement.execute("UPDATE okupansikelas SET today_setoran=0");
            statement.close(); conn.close();
        } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }
    }

    public void resetValueMRJ() {
        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();
            statement.execute("UPDATE okupansikelas SET today_mrjharian=0");
            statement.execute("UPDATE okupansikelas SET today_simak=0");
            statement.close(); conn.close();
        } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }
    }

    public void resetPresensiBulanan() {
        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();
            statement.execute("UPDATE okupansikelas SET ghoib_frek=0");
            statement.execute("UPDATE okupansikelas SET udzur_frek=0");
            statement.execute("UPDATE okupansikelas SET ghoib_kapan=''");
            statement.execute("UPDATE okupansikelas SET udzur_kapan=''");
            statement.close(); conn.close();
        } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }
    }


    public String getAlamatTelegramKelas(String namaKelas) {
        String alamatKelas = "";
        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();
            ResultSet qCariAlamatKelas = statement.executeQuery("SELECT * FROM infokelas WHERE nama_kelas='"
                    + namaKelas + "'");
            alamatKelas = qCariAlamatKelas.getString("alamat");
            qCariAlamatKelas.close();
            statement.close(); conn.close();
        } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }

        System.out.println("Alamat Kelas di GetAlamat = " + alamatKelas);
        return alamatKelas;
    }


    public void generateDailyRekap() {
        // -- Lakukan reset tanggal per awal bulan Hijriah

        String report = "";
        StringBuilder sb = new StringBuilder();
        StringBuilder sbUdzurGhoib = new StringBuilder();
        MTQBotRekap telegramBot = new MTQBotRekap();
        OperasiTanggal operasiTanggal = new OperasiTanggal();

        List<String> daftarKelas = new ArrayList<String>();
        List<String> alamatKelas = new ArrayList<String>();
        List<String> santriKelas = new ArrayList<String>();

        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();

            try {
                // Cari dulu mana-mana kelas yang perlu dibikinin rekap
                ResultSet qStatusOn = statement.executeQuery("SELECT nama_kelas, status, id_db_kelas, alamat FROM infokelas WHERE status=1");
                while (qStatusOn.next()) {
                    daftarKelas.add(qStatusOn.getString(1));
                    alamatKelas.add(qStatusOn.getString("alamat"));
                }
                qStatusOn.close();
            } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }

            System.out.println(daftarKelas);
            System.out.println(alamatKelas);
            // get member of list
            // System.out.println(daftarKelas.get(1));
            // == daftarKelas: daftar kelas yang dibuatin rekap
            // == alamatKelas: daftar alamat group telegram utk pengiriman rekap
            // convert arrayList to array
            String[] stringDaftarKelas = daftarKelas.toArray(new String[0]);
            String[] stringAlamatKelas = alamatKelas.toArray(new String[0]);

            int sizeOfArray = stringDaftarKelas.length;
            for (int i = 0; i < sizeOfArray; i++) {
                generateRekapHarianPerKelas(stringDaftarKelas[i]);
            }


            // == iterate through content - ini sudah masuk PER kelas berarti
/*
            // lakukan sejumlah kelas yang masuk kategori aktif tadi
            int sizeOfArray = stringDaftarKelas.length;
            for (int i = 0; i < sizeOfArray; i++) {
                int count_aktif = 0;
                int count_udzur = 0;
                int count_ghoib = 0;
                String simbolSetoran = "";
                System.out.println("\nMemproses untuk [" + i + "] = " + stringDaftarKelas[i]);
                sb.setLength(0);
                sbUdzurGhoib.setLength(0);

                sb.append("\n================================\n");
                sb.append(EmojiParser.parseToUnicode(":mecca: MTQ-Ma'had Tahfizh Qur'an \n"));
                sb.append(EmojiParser.parseToUnicode(":dhikr_beads: Rekap Setoran Hafizh MTQ " + stringDaftarKelas[i])); sb.append("\n");
                sb.append(EmojiParser.parseToUnicode(":spiral_calendar_pad: ") +  operasiTanggal.getTanggalPelaporan());
                sb.append("================================\n");

                // == try catch ini khusus menghitung BERAPA yang aktif, udzur, dan ghoib
                try {
                    // cari slot okupansi yg tertandai sebagai terisi=1 | yakni yg orangnya memang ada dan aktif
                    santriKelas.clear();
                    ResultSet qOkupansi = statement.executeQuery("SELECT * FROM okupansikelas WHERE terisi=1 AND kelas='" + stringDaftarKelas[i] + "'");

                    while (qOkupansi.next()) {
                        santriKelas.add(qOkupansi.getString("id_okupansi"));
                        count_aktif++;
                    }
                    qOkupansi.close();

                    ResultSet qYangUdzur = statement.executeQuery("SELECT * FROM okupansikelas WHERE terisi=1 AND kelas='" + stringDaftarKelas[i] + "' AND today_setoran=10");
                    while (qYangUdzur.next()) {
                        count_udzur++;
                    }
                    qYangUdzur.close();

                    // sekalian lakukan penambahan bila ada yang ghoib dan pasang alarm
                    ResultSet qYangGhoib = statement.executeQuery("SELECT * FROM okupansikelas WHERE terisi=1 AND kelas='" + stringDaftarKelas[i] + "' AND today_setoran=0");
                    while (qYangGhoib.next()) {
                        count_ghoib++;
                    }
                    qYangGhoib.close();

                    System.out.println("Jumlah yg aktif = " + count_aktif);
                    System.out.println("Jumlah yg udzur = " + count_udzur);

                } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }

                System.out.println(santriKelas);

                // iterate per santri dalam kelas ybs
                // Bagian yang ini utk mencatat info setoran tiap santri
                String[] stringDaftarSantri = santriKelas.toArray(new String[0]);
                int sizeOfSantriArray = stringDaftarSantri.length;
                for (int a = 0; a < sizeOfSantriArray; a++) {
                    System.out.println("Santri antara lain [" + a + "] = " + stringDaftarSantri[a]);
                    try {
                        ResultSet qSantri = statement.executeQuery("SELECT * FROM okupansikelas WHERE id_okupansi='" + stringDaftarSantri[a] + "'");
                        if (qSantri.getInt("today_setoran")==0) {
                            simbolSetoran = EmojiParser.parseToUnicode( ":no_entry:");
                        } else if (qSantri.getInt("today_setoran")==10) {
                            simbolSetoran = EmojiParser.parseToUnicode( ":sos:");
                        } else {
                            simbolSetoran = EmojiParser.parseToUnicode( ":heavy_check_mark:");
                        }

                        if (qSantri.getInt("udzur_frek")>0) {
                            sbUdzurGhoib.append(EmojiParser.parseToUnicode(":sos: ")
                                    + qSantri.getString("nama_santri") + " "
                                    + qSantri.getInt("udzur_frek")
                                    + "x pd"
                                    + qSantri.getString("udzur_kapan") + "\n");
                        }

                        if (qSantri.getInt("udzur_frek")>7) {
                            String pesanUdzurLewatBatas = "Santri " + qSantri.getString("nama_santri")
                                    + " (" + qSantri.getString("username")
                                    + ") sudah udzur sebanyak " + qSantri.getInt("udzur_frek")
                                    + " kali. Silahkan ditindaklanjuti.";
                            telegramBot.kirimNotifikasiAdaYgUdzurKelewatan(pesanUdzurLewatBatas);
                        }

                        if (qSantri.getInt("ghoib_frek")>0) {
                            sbUdzurGhoib.append(EmojiParser.parseToUnicode(":no_entry: ")
                                    + qSantri.getString("nama_santri") + " "
                                    + qSantri.getInt("ghoib_frek")
                                    + "pt pd"
                                    + qSantri.getString("ghoib_kapan") + "\n");
                        }
                        System.out.println("keluaran: " + sbUdzurGhoib.toString());

                        sb.append(simbolSetoran + " ");
                        sb.append(qSantri.getString("nama_santri") + " ");
                        sb.append(qSantri.getInt("p_no_suroh") + ":");
                        sb.append(qSantri.getInt("p_ayat_begin") + "-");
                        sb.append(qSantri.getInt("p_ayat_end") + "\n");


                        qSantri.close();
                    } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }
                } // ending iterate tiap santri

                sb.append("================================\n");
                sb.append(EmojiParser.parseToUnicode( ":busts_in_silhouette: = ") + count_aktif);
                sb.append(EmojiParser.parseToUnicode( " :bust_in_silhouette: = ") + (count_aktif - count_udzur - count_ghoib));
                sb.append(EmojiParser.parseToUnicode( " :no_entry: = ")  + count_ghoib);
                sb.append(EmojiParser.parseToUnicode( " :sos: = ")  + count_udzur); sb.append("\n\n");
                sb.append(sbUdzurGhoib.toString());
                sb.append("================================\n");


                report = sb.toString();
                System.out.println(report);

                telegramBot.kirimReport(report, stringAlamatKelas[i]);

            } // ending pengiriman per kelas
            */

            statement.close(); conn.close();
        } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }

    }



    public void generateRekapHarianPerKelas(String namaKelas) {

        String report = "";
        StringBuilder sb = new StringBuilder();
        StringBuilder sbUdzurGhoib = new StringBuilder();
        MTQBotRekap telegramBot = new MTQBotRekap();
        OperasiTanggal operasiTanggal = new OperasiTanggal();
        // untuk tampung id_okupansi yg aktif (terisi)
        List<String> santriKelas = new ArrayList<String>();
        String alamatKelas = "";

        int count_aktif = 0;
        int count_udzur = 0;
        int count_ghoib = 0;

        String simbolSetoran = "";
        System.out.println("\nMemproses untuk = " + namaKelas);
        sb.setLength(0);
        sbUdzurGhoib.setLength(0);
        String userNameSantri = "";


        sb.append("\n================================\n");
        sb.append(EmojiParser.parseToUnicode(":mecca: MTQ-Ma'had Tahfizh Qur'an \n"));
        sb.append(EmojiParser.parseToUnicode(":dhikr_beads: Rekap Setoran Hafizh MTQ " + namaKelas)); sb.append("\n");
        sb.append(EmojiParser.parseToUnicode(":spiral_calendar_pad: ") +  operasiTanggal.getTanggalPelaporan());
        sb.append("================================\n");

        try {

            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();

            ResultSet qCariAlamatKelas = statement.executeQuery("SELECT * FROM infokelas WHERE nama_kelas='"
                    + namaKelas + "'");
            alamatKelas = qCariAlamatKelas.getString("alamat");
            qCariAlamatKelas.close();

            // cari slot okupansi yg tertandai sebagai terisi=1 | yakni yg orangnya memang ada dan aktif
            santriKelas.clear();
            ResultSet qOkupansi = statement.executeQuery("SELECT * FROM okupansikelas WHERE terisi=1 AND kelas='" + namaKelas + "'");

            while (qOkupansi.next()) {
                santriKelas.add(qOkupansi.getString("id_okupansi"));
                count_aktif++;
            }
            qOkupansi.close();

            ResultSet qYangUdzur = statement.executeQuery("SELECT * FROM okupansikelas WHERE terisi=1 AND kelas='" + namaKelas + "' AND today_setoran=10");
            while (qYangUdzur.next()) {
                count_udzur++;
            }
            qYangUdzur.close();

            // sekalian lakukan penambahan bila ada yang ghoib dan pasang alarm
            ResultSet qYangGhoib = statement.executeQuery("SELECT * FROM okupansikelas WHERE terisi=1 AND kelas='" + namaKelas + "' AND today_setoran=0");
            while (qYangGhoib.next()) {
                count_ghoib++;
            }
            qYangGhoib.close();

//            System.out.println("Jumlah yg aktif = " + count_aktif);
//            System.out.println("Jumlah yg udzur = " + count_udzur);

//            System.out.println(santriKelas);

            // iterate per santri dalam kelas ybs berdasarkan data yg diambil di atas (list santriKelas)
            // Bagian yang ini utk mencatat info setoran tiap santri
            String[] stringDaftarSantri = santriKelas.toArray(new String[0]);
            int sizeOfSantriArray = stringDaftarSantri.length;
            for (int a = 0; a < sizeOfSantriArray; a++) {
                int todaySetoran = 0;
                int udzurFrek = 0;
                int ghoibFrek = 0;
                int pNoSuroh = 0;
                int pNoAyatBegin = 0;
                int pNoAyatEnd = 0;

                String kapanGhoib = "";
                String kapanUdzur = "";
                String namaSantri = "";
                System.out.println("Santri antara lain [" + a + "] = " + stringDaftarSantri[a]);

                ResultSet qSantri = statement.executeQuery("SELECT * FROM okupansikelas WHERE id_okupansi='" + stringDaftarSantri[a] + "'");
                while (qSantri.next()) {
                    namaSantri = qSantri.getString("nama_santri");
                    userNameSantri = qSantri.getString("username");
                    todaySetoran = qSantri.getInt("today_setoran");
                    udzurFrek = qSantri.getInt("udzur_frek");
                    ghoibFrek = qSantri.getInt("ghoib_frek");
                    kapanGhoib = qSantri.getString("ghoib_kapan");
                    kapanUdzur = qSantri.getString("udzur_kapan");
                    pNoSuroh = qSantri.getInt("p_no_suroh");
                    pNoAyatBegin = qSantri.getInt("p_ayat_begin");
                    pNoAyatEnd = qSantri.getInt("p_ayat_end");
                }
                qSantri.close();



                if (todaySetoran==0) {
                    simbolSetoran = EmojiParser.parseToUnicode( ":no_entry:");
                } else if (todaySetoran==10) {
                    simbolSetoran = EmojiParser.parseToUnicode( ":sos:");
                } else {
                    simbolSetoran = EmojiParser.parseToUnicode( ":heavy_check_mark:");
                }

                if (udzurFrek>0) {
                    sbUdzurGhoib.append(EmojiParser.parseToUnicode(":sos: ")
                            + namaSantri + " " + udzurFrek + "x pd" + kapanUdzur + "\n");
                }

                if (udzurFrek>7) {
                    String pesanUdzurLewatBatas = "Santri " + namaSantri
                            + " (" + userNameSantri
                            + EmojiParser.parseToUnicode(") sudah UDZUR :sos: sebanyak ")
                            + udzurFrek + " kali. Silahkan ditindaklanjuti.";
                    telegramBot.kirimNotifikasiAdaYgUdzurKelewatan(pesanUdzurLewatBatas);
                }

                if (ghoibFrek>0) {
                    sbUdzurGhoib.append(EmojiParser.parseToUnicode(":no_entry: ")
                            + namaSantri + " " + ghoibFrek + "pt pd" + kapanGhoib + "\n");
                }

                if (ghoibFrek>1) {
                    String pesanUdzurLewatBatas = "Santri " + namaSantri
                            + " (" + userNameSantri
                            + EmojiParser.parseToUnicode(") sudah GHOIB :no_entry_sign: sebanyak ")
                            + ghoibFrek + " kali. Silahkan ditindaklanjuti.";
                    telegramBot.kirimNotifikasiAdaYgUdzurKelewatan(pesanUdzurLewatBatas);
                }

                sb.append(simbolSetoran + " ");
                sb.append(namaSantri + " ");
                sb.append(pNoSuroh + ":");
                sb.append(pNoAyatBegin + "-");
                sb.append(pNoAyatEnd + "\n");


//                statement.execute("UPDATE okupansikelas SET today_setoran=0 WHERE username='" + userNameSantri + "'");

//                System.out.println("____Nilai UserName santri = " + userNameSantri);
//                resetValueSetoran(userNameSantri);

            } // ending iterate tiap santri
            statement.close(); conn.close();

            sb.append("================================\n");
            sb.append(EmojiParser.parseToUnicode( ":busts_in_silhouette: = ") + count_aktif);
            sb.append(EmojiParser.parseToUnicode( " :bust_in_silhouette: = ") + (count_aktif - count_udzur - count_ghoib));
            sb.append(EmojiParser.parseToUnicode( " :no_entry: = ")  + count_ghoib);
            sb.append(EmojiParser.parseToUnicode( " :sos: = ")  + count_udzur); sb.append("\n\n");
            sb.append(sbUdzurGhoib.toString());
            sb.append("================================\n");


            report = sb.toString();
            telegramBot.kirimReport(report, alamatKelas);

        } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }

        /*try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();
            // Reset skor setor today
            statement.execute("UPDATE okupansikelas SET today_setoran=0 WHERE username='" + userNameSantri + "'");
        } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }*/




//        System.out.println(report);
//        System.out.println("---- Alamat Kelas = " + alamatKelas);

    }


    public void generateDailyMRJreport(String namaKelas) {
        MTQBotRekap telegramBot = new MTQBotRekap();
        OperasiTanggal operasiTanggal = new OperasiTanggal();
        String report = "";
        StringBuilder sb = new StringBuilder();
        String simbolSetoran = "";
        String simbolSimak = "";
        System.out.println("\nMemproses untuk = " + namaKelas);
        sb.setLength(0);
        String userNameSantri = "";
        String alamatKelas = "";

        List<String> santriKelas = new ArrayList<String>();

        sb.append("\n================================\n");
        sb.append(EmojiParser.parseToUnicode(":star_crescent: MTQ-Ma'had Tahfizh Qur'an \n"));
        sb.append(EmojiParser.parseToUnicode(":m: Rekap Muroja'ah Hafizh MTQ " + namaKelas)); sb.append("\n");
        sb.append(EmojiParser.parseToUnicode(":spiral_calendar_pad: ") +  operasiTanggal.getTanggalPelaporan());
        sb.append("================================\n");

        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();

            ResultSet qCariAlamatKelas = statement.executeQuery("SELECT * FROM infokelas WHERE nama_kelas='"
                    + namaKelas + "'");
            alamatKelas = qCariAlamatKelas.getString("alamat");
            qCariAlamatKelas.close();

            // cari slot okupansi yg tertandai sebagai terisi=1 | yakni yg orangnya memang ada dan aktif
            santriKelas.clear();
            ResultSet qOkupansi = statement.executeQuery("SELECT * FROM okupansikelas WHERE terisi=1 AND is_wajib_mrjharian=1 AND kelas='" + namaKelas + "'");

            while (qOkupansi.next()) {
                santriKelas.add(qOkupansi.getString("id_okupansi"));
                userNameSantri = qOkupansi.getString("username");
            }
            qOkupansi.close();

            System.out.println(santriKelas);

            // iterate per santri dalam kelas ybs berdasarkan data yg diambil di atas (list santriKelas)
            // Bagian yang ini utk mencatat info setoran tiap santri
            String[] stringDaftarSantri = santriKelas.toArray(new String[0]);
            int sizeOfSantriArray = stringDaftarSantri.length;
            for (int a = 0; a < sizeOfSantriArray; a++) {
                System.out.println("Santri antara lain [" + a + "] = " + stringDaftarSantri[a]);

                ResultSet qSantri = statement.executeQuery("SELECT * FROM okupansikelas WHERE id_okupansi='" + stringDaftarSantri[a] + "'");
                if (qSantri.getInt("today_mrjharian")==0) {
                    simbolSetoran = EmojiParser.parseToUnicode( ":no_entry:");
                    setGhoibPoinSeparuh(userNameSantri);

                } else if (qSantri.getInt("today_mrjharian")==10) {
                    simbolSetoran = EmojiParser.parseToUnicode( ":sos:");
                } else {
                    simbolSetoran = EmojiParser.parseToUnicode( ":ballot_box_with_check:");
                }

                if (qSantri.getInt("today_simak")==0) {
                    simbolSimak = EmojiParser.parseToUnicode( ":no_entry_sign:");
                    setGhoibPoinSeparuh(userNameSantri);
                } else if (qSantri.getInt("today_simak")==10) {
                    simbolSimak = EmojiParser.parseToUnicode( ":sos:");
                } else {
                    simbolSimak = EmojiParser.parseToUnicode( ":ballot_box_with_check:");
                }

                sb.append(simbolSetoran + " ");
                sb.append(simbolSimak + " ");
                sb.append(qSantri.getString("nama_santri") + " \n");


                qSantri.close();

            } // ending iterate tiap santri
            statement.close(); conn.close();

            sb.append("================================\n");
            report = sb.toString();
            System.out.println(report);

            telegramBot.kirimReport(report, alamatKelas);


        } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }
    }

    public void generateListMRJakhirPekan(String namaKelas) {
        MTQBotRekap telegramBot = new MTQBotRekap();
        String userNameSantri = "";
        String report = "";
        String alamatKelas = "";
        List<String> santriKelas = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        sb.append("\n================================\n");
        sb.append(EmojiParser.parseToUnicode(":earth_asia: MTQ-Ma'had Tahfizh Qur'an \n"));
        sb.append(EmojiParser.parseToUnicode(":m: Setoran Pekan Terakhir " + namaKelas)); sb.append("\n");
        sb.append("================================\n");

        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();

            ResultSet qCariAlamatKelas = statement.executeQuery("SELECT * FROM infokelas WHERE nama_kelas='"
                    + namaKelas + "'");
            alamatKelas = qCariAlamatKelas.getString("alamat");


            // cari slot okupansi yg tertandai sebagai terisi=1 | yakni yg orangnya memang ada dan aktif
            santriKelas.clear();
            ResultSet qOkupansi = statement.executeQuery("SELECT * FROM okupansikelas WHERE terisi=1 AND kelas='" + namaKelas + "'");

            while (qOkupansi.next()) {
                santriKelas.add(qOkupansi.getString("id_okupansi"));
                userNameSantri = qOkupansi.getString("username");
            }

            qOkupansi.close(); qCariAlamatKelas.close();
            System.out.println(santriKelas);

            // iterate per santri dalam kelas ybs berdasarkan data yg diambil di atas (list santriKelas)
            // Bagian yang ini utk mencatat info setoran tiap santri
            String[] stringDaftarSantri = santriKelas.toArray(new String[0]);
            int sizeOfSantriArray = stringDaftarSantri.length;
            for (int a = 0; a < sizeOfSantriArray; a++) {
                System.out.println("Santri antara lain [" + a + "] = " + stringDaftarSantri[a]);

                ResultSet qSantri = statement.executeQuery("SELECT * FROM okupansikelas WHERE id_okupansi='" + stringDaftarSantri[a] + "'");
                sb.append(qSantri.getString("nama_santri") + " ");
                sb.append(qSantri.getInt("mrjap_suroh_begin") + ":");
                sb.append(qSantri.getInt("mrjap_ayat_begin") + " - ");
                sb.append(qSantri.getInt("mrjap_suroh_end") + ":");
                sb.append(qSantri.getInt("mrjap_ayat_end") + "\n");

                qSantri.close();

            } // ending iterate tiap santri
            statement.close(); conn.close();

            sb.append("================================\n");
            report = sb.toString();
            System.out.println(report);

            telegramBot.kirimReport(report, alamatKelas);

        } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }

    }

    public void generateReportMRJakhirPekanPerKelas(String namaKelas) {

        MTQBotRekap telegramBot = new MTQBotRekap();
        OperasiTanggal operasiTanggal = new OperasiTanggal();
        String report = "";
        StringBuilder sb = new StringBuilder();
        String simbolSetoran = "";
        String simbolSimak = "";
        System.out.println("\nMemproses untuk = " + namaKelas);
        String userNameSantri = "";
        String alamatKelas = "";


        List<String> santriKelas = new ArrayList<String>();

        sb.append("\n================================\n");
        sb.append(EmojiParser.parseToUnicode(":id: MTQ-Ma'had Tahfizh Qur'an \n"));
        sb.append(EmojiParser.parseToUnicode(":m: Rekap MRJ Pekanan Hafizh MTQ " + namaKelas)); sb.append("\n");
        sb.append("================================\n");

        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();

            ResultSet qCariAlamatKelas = statement.executeQuery("SELECT * FROM infokelas WHERE nama_kelas='"
                    + namaKelas + "'");
            alamatKelas = qCariAlamatKelas.getString("alamat");


            // cari slot okupansi yg tertandai sebagai terisi=1 | yakni yg orangnya memang ada dan aktif
            santriKelas.clear();
            ResultSet qOkupansi = statement.executeQuery("SELECT * FROM okupansikelas WHERE terisi=1 AND kelas='" + namaKelas + "'");

            while (qOkupansi.next()) {
                santriKelas.add(qOkupansi.getString("id_okupansi"));
                userNameSantri = qOkupansi.getString("username");
            }

            qOkupansi.close(); qCariAlamatKelas.close();
            System.out.println(santriKelas);

            // iterate per santri dalam kelas ybs berdasarkan data yg diambil di atas (list santriKelas)
            // Bagian yang ini utk mencatat info setoran tiap santri
            String[] stringDaftarSantri = santriKelas.toArray(new String[0]);
            int sizeOfSantriArray = stringDaftarSantri.length;
            for (int a = 0; a < sizeOfSantriArray; a++) {
                int MRJapSetor = 0;
                int MRJapSimak = 0;
                String namaSantri = "";
                System.out.println("Santri antara lain [" + a + "] = " + stringDaftarSantri[a]);

                ResultSet qSantri = statement.executeQuery("SELECT * FROM okupansikelas WHERE id_okupansi='" + stringDaftarSantri[a] + "'");
                namaSantri = qSantri.getString("nama_santri");
                MRJapSetor = qSantri.getInt("mrjap_setor");
                MRJapSimak = qSantri.getInt("mrjap_simak");
                qSantri.close();

                if (MRJapSetor==0) {
                    simbolSetoran = EmojiParser.parseToUnicode( ":no_entry:");
                    setGhoibPoinSeparuh(userNameSantri);
                } else if (MRJapSetor==10) {
                    simbolSetoran = EmojiParser.parseToUnicode( ":sos:");
                } else {
                    simbolSetoran = EmojiParser.parseToUnicode( ":ballot_box_with_check:");
                }

                if (MRJapSimak==0) {
                    simbolSimak = EmojiParser.parseToUnicode( ":no_entry_sign:");
                    setGhoibPoinSeparuh(userNameSantri);
                } else if (MRJapSimak==10) {
                    simbolSimak = EmojiParser.parseToUnicode( ":sos:");
                } else {
                    simbolSimak = EmojiParser.parseToUnicode( ":ballot_box_with_check:");
                }

                sb.append(simbolSetoran + " ");
                sb.append(simbolSimak + " ");
                sb.append(namaSantri + " \n");

            } // ending iterate tiap santri
            statement.close(); conn.close();

            sb.append("================================\n");
            report = sb.toString();
            System.out.println(report);

            telegramBot.kirimReport(report, alamatKelas);

        } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }

    }

}
