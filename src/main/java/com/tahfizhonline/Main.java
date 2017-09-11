package com.tahfizhonline;

import com.tahfizhonline.entity.SQLiteJDBCDriverConnection;
import com.tahfizhonline.entity.SqliteJdbc;
import com.tahfizhonline.entity.TesDB;
import com.tahfizhonline.fungsidukung.CronTriggerExample;
import com.tahfizhonline.fungsidukung.JobScheduler;
import com.tahfizhonline.fungsidukung.OperasiTanggal;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

//        try {
//            SqliteJdbc koneksiDB = new SqliteJdbc();
//            koneksiDB.createKelasOkupansi("03b", "1");
//            koneksiDB.insertSantriToKelas("05b-1-02", "@Teguhaburumaishaa", "Teguh");
//            koneksiDB.insertSetoranHarian("@Teguhaburumaishaa", 47, 11, 20);
//            koneksiDB.generateDailyRekap();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        SqliteJdbc koneksiDB = new SqliteJdbc();
//            koneksiDB.createKelasOkupansi("03b", "1");
//            koneksiDB.insertSantriToKelas("05b-1-02", "@Teguhaburumaishaa", "Teguh");
//            koneksiDB.insertSetoranHarian("@Teguhaburumaishaa", 47, 11, 20);
//        koneksiDB.generateDailyRekap();
//        koneksiDB.generateRekapHarianPerKelas("05Bi");
//        koneksiDB.generateDailyMRJreport("05Bi", "-239673816");
//        koneksiDB.resetValueSetoran("guntar");

//        JobScheduler jobScheduler = new JobScheduler();

//        CronTriggerExample cronTriggerExample = new CronTriggerExample();


//        OperasiTanggal operasiTanggal = new OperasiTanggal();

//        String stringToSearch = "Four score and seven years ago our fathers ...";

//        Pattern p = Pattern.compile("score");   // the pattern to search for
//        Matcher m = Pattern.compile("score").matcher(stringToSearch);

        // now try to find at least one match
//        if (Pattern.compile("score").matcher(stringToSearch).find())
//            System.out.println("Found a match!");
//        else
//            System.out.println("Did not find a match");


//        String tes = ".tes";
//        String[] coba = new String[2];
//        coba[0] = tes.split(" ")[0];
//        System.out.println(coba[0]);
//        System.out.println(coba[1]);

//        String namaKelas = "01ba";
//        String indikasiNamaKelas =  namaKelas.substring(0,2);
//        String indikasiKategori = namaKelas.substring(2,3);
//        String indikasiGender = namaKelas.substring(3,4);
//        System.out.println(indikasiNamaKelas + " " + indikasiKategori + " " + indikasiGender);

//        String indikasiNamaKelas =  "05B-1-07".substring(2,3);
//        System.out.println(indikasiNamaKelas);

        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new MTQBotRekap());
            System.out.println("MTQ bot successfully started!");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}