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

public class Main {
    public static void main(String[] args) {

/*        try {
            SqliteJdbc koneksiDB = new SqliteJdbc();
            koneksiDB.createKelasOkupansi("03b", "1");
            koneksiDB.insertSantriToKelas("05b-1-02", "@Teguhaburumaishaa", "Teguh");
            koneksiDB.insertSetoranHarian("@Teguhaburumaishaa", 47, 11, 20);
            koneksiDB.generateDailyRekap();

        } catch (SQLException e) {
            e.printStackTrace();
        }*/

//        JobScheduler jobScheduler = new JobScheduler();

        CronTriggerExample cronTriggerExample = new CronTriggerExample();


//        OperasiTanggal operasiTanggal = new OperasiTanggal();





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