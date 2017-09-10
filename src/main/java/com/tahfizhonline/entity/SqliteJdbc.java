package com.tahfizhonline.entity;

import com.tahfizhonline.MTQBotRekap;
import com.tahfizhonline.fungsidukung.OperasiTanggal;
import org.apache.log4j.Logger;
import com.vdurmont.emoji.EmojiParser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteJdbc {

    final static Logger logger = Logger.getLogger(SqliteJdbc.class);

    public static final String DB_DNAME = "mtqdb.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/Users/guntar/Desktop/" + DB_DNAME;

    public static final String TABEL_SANTRI = "santrireguler";
    public static final String TABEL_OKUPANSI = "okupansikelas";

    public static final String KOLOM_ID_OKUPANSI = "id_okupansi";

    public static final String COLUMN_IDSANTRI = "id_santri";
    public static final String COLUMN_NAMA = "nama";
    public static final String COLUMN_USERNAME = "username";


    public void createKelasOkupansi(String kelas, String gender) throws SQLException {
        try {
            String charKelas = "";
            if (kelas.indexOf("a") >=0) {
                kelas = kelas.replace("a", "A"); charKelas = "A";
            }

            if (kelas.indexOf("b") >=0) {
                kelas = kelas.replace("b", "B"); charKelas = "B";
            }

            String genderInner = "";
            if (gender.equals("1")) genderInner="i"; else genderInner="a";

            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();
            statement.execute("INSERT INTO okupansikelas (id_okupansi, kelas, terisi, kategori) VALUES ('" + kelas + "-" + gender + "-01', '" + kelas + genderInner + "', 0, '" + charKelas + "')");
            statement.execute("INSERT INTO okupansikelas (id_okupansi, kelas, terisi, kategori) VALUES ('" + kelas + "-" + gender + "-02', '" + kelas + genderInner + "', 0, '" + charKelas + "')");
            statement.execute("INSERT INTO okupansikelas (id_okupansi, kelas, terisi, kategori) VALUES ('" + kelas + "-" + gender + "-03', '" + kelas + genderInner + "', 0, '" + charKelas + "')");
            statement.execute("INSERT INTO okupansikelas (id_okupansi, kelas, terisi, kategori) VALUES ('" + kelas + "-" + gender + "-04', '" + kelas + genderInner + "', 0, '" + charKelas + "')");
            statement.execute("INSERT INTO okupansikelas (id_okupansi, kelas, terisi, kategori) VALUES ('" + kelas + "-" + gender + "-05', '" + kelas + genderInner + "', 0, '" + charKelas + "')");
            statement.execute("INSERT INTO okupansikelas (id_okupansi, kelas, terisi, kategori) VALUES ('" + kelas + "-" + gender + "-06', '" + kelas + genderInner + "', 0, '" + charKelas + "')");
            statement.execute("INSERT INTO okupansikelas (id_okupansi, kelas, terisi, kategori) VALUES ('" + kelas + "-" + gender + "-07', '" + kelas + genderInner + "', 0, '" + charKelas + "')");
            statement.execute("INSERT INTO okupansikelas (id_okupansi, kelas, terisi, kategori) VALUES ('" + kelas + "-" + gender + "-08', '" + kelas + genderInner + "', 0, '" + charKelas + "')");
            statement.execute("INSERT INTO okupansikelas (id_okupansi, kelas, terisi, kategori) VALUES ('" + kelas + "-" + gender + "-09', '" + kelas + genderInner + "', 0, '" + charKelas + "')");
            statement.execute("INSERT INTO okupansikelas (id_okupansi, kelas, terisi, kategori) VALUES ('" + kelas + "-" + gender + "-10', '" + kelas + genderInner + "', 0, '" + charKelas + "')");

            statement.close();
            conn.close();

        } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }
    }

    public void registerKelas(String namaKelas, long alamatGrup) {
        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();
            statement.execute("UPDATE infokelas SET alamat='" + alamatGrup + "' WHERE nama_kelas='" + namaKelas + "'");
            statement.execute("UPDATE infokelas SET status=1 WHERE nama_kelas='" + namaKelas + "'");
            statement.close();
            conn.close();
        } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }
    }

    public void disableKelas(String namaKelas, long alamatGrup) {
        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();
            statement.execute("UPDATE infokelas SET status=0 WHERE nama_kelas='" + namaKelas + "'");
            statement.close();
            conn.close();
        } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }
    }

    public static void insertSantriToKelas(String id_okupansi, String username)  {
        try {

            String kategoriKelas = "";
            if (id_okupansi.indexOf("a") >=0) {
                id_okupansi = id_okupansi.replace("a", "A"); kategoriKelas = "A";
            }

            if (id_okupansi.indexOf("b") >=0) {
                id_okupansi = id_okupansi.replace("b", "B"); kategoriKelas = "B";
            }

//            System.out.println(id_okupansi);
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();
            statement.execute("UPDATE okupansikelas SET username='" + username + "' WHERE id_okupansi='" + id_okupansi + "'");
            statement.execute("UPDATE okupansikelas SET terisi=1 WHERE id_okupansi='" + id_okupansi + "'");
            statement.execute("UPDATE okupansikelas SET ketegori='" + kategoriKelas + "' WHERE id_okupansi='" + id_okupansi + "'");
            statement.execute("UPDATE okupansikelas SET nama_santri='" + username + "' WHERE id_okupansi='" + id_okupansi + "'");
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
            statement.execute("UPDATE okupansikelas SET mrjap_suroh_begin=0 WHERE id_okupansi='" + id_okupansi + "'");
            statement.execute("UPDATE okupansikelas SET mrjap_suroh_end=0 WHERE id_okupansi='" + id_okupansi + "'");
            statement.execute("UPDATE okupansikelas SET mrjap_ayat_begin=0 WHERE id_okupansi='" + id_okupansi + "'");
            statement.execute("UPDATE okupansikelas SET mrjap_ayat_end=0 WHERE id_okupansi='" + id_okupansi + "'");
            statement.execute("UPDATE okupansikelas SET mrjap_setor=0 WHERE id_okupansi='" + id_okupansi + "'");
            statement.execute("UPDATE okupansikelas SET mrjap_simak=0 WHERE id_okupansi='" + id_okupansi + "'");

            statement.close();
            conn.close();

        } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }
    }

    public void removeSantriFromKelas(String userName) {
        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();
            statement.execute("UPDATE okupansikelas SET terisi=0 WHERE username='" + userName + "'");
            statement.close(); conn.close();
        } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }
    }

    public static void insertSetoranHarian(String username, int noSuroh, int awalAyat, int akhirAyat) {
        int berapaKaliSetorToday = 0;
        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();

            ResultSet qEksistingSetoran = statement.executeQuery("SELECT today_setoran FROM okupansikelas WHERE username='" + username + "'");
//            System.out.println(qEksistingSetoran.getInt("today_setoran"));
            berapaKaliSetorToday = qEksistingSetoran.getInt("today_setoran");
            berapaKaliSetorToday++;
            qEksistingSetoran.close();


            statement.execute("UPDATE okupansikelas SET today_setoran=" + berapaKaliSetorToday + " WHERE username='" + username + "'");
            statement.execute("UPDATE okupansikelas SET p_no_suroh=" + noSuroh + " WHERE username='" + username + "'");
            statement.execute("UPDATE okupansikelas SET p_ayat_begin=" + awalAyat + " WHERE username='" + username + "'");
            statement.execute("UPDATE okupansikelas SET p_ayat_end=" + akhirAyat + " WHERE username='" + username + "'");

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

    public void mrjSetoran(String userName) {
        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();
            statement.execute("UPDATE okupansikelas SET today_mrjharian=1 WHERE username='" + userName + "'");
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



    public void generateDailyRekap() {
        String report = "";
        StringBuilder sb = new StringBuilder();
        MTQBotRekap telegramBot = new MTQBotRekap();
        OperasiTanggal operasiTanggal = new OperasiTanggal();

        List<String> daftarKelas = new ArrayList<String>();
        List<String> alamatKelas = new ArrayList<String>();
        List<String> santriKelas = new ArrayList<String>();

        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();

            try {
                ResultSet qStatusOn = statement.executeQuery("SELECT nama_kelas, status, id_kelas, alamat FROM infokelas WHERE status=1");
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
            // convert arrayList to array
            String[] stringDaftarKelas = daftarKelas.toArray(new String[0]);
            String[] stringAlamatKelas = alamatKelas.toArray(new String[0]);

            // iterate through content - ini sudah masuk PER kelas berarti

            int sizeOfArray = stringDaftarKelas.length;
            for (int i = 0; i < sizeOfArray; i++) {

                int count_aktif = 0;
                int count_udzur = 0;
                int count_ghoib = 0;
                String simbolSetoran = "";
                System.out.println("\nProcessing [" + i + "] = " + stringDaftarKelas[i]);

                sb.append("\n================================\n");
                sb.append(EmojiParser.parseToUnicode(":mecca: MTQ-Ma'had Tahfidzh Qur'an \n"));
                sb.append(EmojiParser.parseToUnicode(":dhikr_beads: Rekap Setoran Hafizh MTQ " + stringDaftarKelas[i])); sb.append("\n");
                sb.append(EmojiParser.parseToUnicode(":spiral_calendar_pad: ") +  operasiTanggal.getTanggalPelaporan());
                sb.append("================================\n");

                try {
                    // cari slot okupansi yg tertandai sebagai terisi=1 | orangnya memang ada
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
                sb.append(EmojiParser.parseToUnicode( " :sos: = ")  + count_udzur); sb.append("\n");

                statement.close(); conn.close();
                report = sb.toString();
                System.out.println(report);

                telegramBot.kirimReport(report, stringAlamatKelas[i]);

            } // ending pengiriman per kelas

        } catch (SQLException e) { System.out.println(e.getMessage()); logger.error(e.getMessage()); }

    }

    public void generateDailyMRJreport() {
        String report = "";
        StringBuilder sb = new StringBuilder();
        OperasiTanggal operasiTanggal = new OperasiTanggal();

        List<String> daftarKelas = new ArrayList<String>();
        List<String> alamatKelas = new ArrayList<String>();
        List<String> santriKelas = new ArrayList<String>();



    }


}
