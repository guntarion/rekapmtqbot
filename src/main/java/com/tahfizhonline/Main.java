package com.tahfizhonline;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Main {
    public static void main(String[] args) {

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