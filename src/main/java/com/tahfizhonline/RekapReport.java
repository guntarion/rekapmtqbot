package com.tahfizhonline;

import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class RekapReport extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        // TODO
        return "MTQ Rekap Bot";
    }

    @Override
    public String getBotToken() {
        // TODO
//        return "412365466:AAGLovUBFxeLjl-yOAWXzp1owkP7pfk18zQ";
        return "170874075:AAE6U-akZk3c1ppdwyJjAC2M477tVWWRitM";

    }

    @Override
    public void onUpdateReceived(Update update) {

    }
}
