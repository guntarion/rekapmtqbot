package com.tahfizhonline;

import com.tahfizhonline.entity.SQLiteJDBCDriverConnection;
import com.tahfizhonline.entity.TesDB;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Main {
    public static void main(String[] args) {

//        TesJDBC tesJDBC = new TesJDBC();

//        TesDB tesDB = new TesDB();
//        tesDB.tesDatabase();

        SQLiteJDBCDriverConnection databasemtq = new SQLiteJDBCDriverConnection();
        databasemtq.connect();

        /*ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new MTQBotRekap());
            System.out.println("MTQ bot successfully started!");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }*/

    }
}