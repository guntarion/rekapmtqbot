package com.tahfizhonline;

import com.tahfizhonline.entity.SantriReguler;
import com.vdurmont.emoji.EmojiParser;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class MTQBotRekap extends TelegramLongPollingBot {




    int noSuroh, awalAyat, akhirAyat;

    @Override
    public String getBotUsername() {
        // TODO
        return "MTQ Rekap Bot";
    }

    @Override
    public String getBotToken() {
        // TODO
        return "412365466:AAGLovUBFxeLjl-yOAWXzp1owkP7pfk18zQ";
    }

    @Override
    public void onUpdateReceived(Update update) {


        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(SantriReguler.class)
                .buildSessionFactory();

        // TODO
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            String isi_pesan = update.getMessage().getText();
            String santri_first_name = update.getMessage().getChat().getFirstName();
            String santri_last_name = update.getMessage().getChat().getLastName();
            String santri_username = update.getMessage().getChat().getUserName();
            long id_chat = update.getMessage().getChatId();

//            String str = "An :grinning:awesome :smiley:string &#128516;with a few :wink:emojis!";
//            String result = EmojiParser.parseToUnicode(str);
//            System.out.println(result);

            if (isi_pesan.equals("/tes")) {
                try {
                    SendMessage message = new SendMessage() // Create a message object object
                            .setChatId(id_chat)
                            .setText(" Tes berhasil :sparkling_heart: ");
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (isi_pesan.length() > 3) {
//                String tigaHurufPertama = isi_pesan.substring(0, Math.min(isi_pesan.length(), 4));
                String[] infoKiriman = isi_pesan.split(" ");
                String perintah_pesan = infoKiriman[0];
                String inti_pesan = infoKiriman[1];
                System.out.println(infoKiriman[0]);
                System.out.println(inti_pesan);

                if (perintah_pesan.equals(".baru") || perintah_pesan.equals("/baru")) {
                    if (inti_pesan.equals("")) {
                        SendMessage pesanBalik = new SendMessage().setChatId(id_chat)
                                .setText(EmojiParser.parseToUnicode(":no_entry: Info setoran kosong"));
                        try { execute(pesanBalik); } catch (TelegramApiException e) { e.printStackTrace();}
                    } else if (inti_pesan.contains("-")) {

                        int[] infoSurohAyat = isiPesanDgAwalAkhirAyat(inti_pesan);
                        noSuroh = infoSurohAyat[0]; awalAyat = infoSurohAyat[1]; akhirAyat = infoSurohAyat[2];

                        String isiRespon = EmojiParser.parseToUnicode(":eight_spoked_asterisk: ") + santri_first_name + " " + santri_last_name + " "
                                + noSuroh + ":" + awalAyat + "-" + akhirAyat;

                        SendMessage pesanBalik = new SendMessage().setChatId(id_chat).setText(isiRespon);
                        try { execute(pesanBalik); } catch (TelegramApiException e) { e.printStackTrace(); }
                    } else { // untuk kelas A

                    }

                } else if (perintah_pesan.equals(".revi") || perintah_pesan.equals("/revi")) {
                    if (inti_pesan.contains("-")) {
                        int[] infoSurohAyat = isiPesanDgAwalAkhirAyat(inti_pesan);
                        noSuroh = infoSurohAyat[0]; awalAyat = infoSurohAyat[1]; akhirAyat = infoSurohAyat[2];

                        String isiRespon = EmojiParser.parseToUnicode(":recycle: ") + santri_first_name + " " + santri_last_name + " "
                                + noSuroh + ":" + awalAyat + "-" + akhirAyat;
                        SendMessage pesanBalik = new SendMessage().setChatId(id_chat).setText(isiRespon);
                        try { execute(pesanBalik); } catch (TelegramApiException e) { e.printStackTrace(); }

                    } else { // untuk kelas A

                    }
                } else if (perintah_pesan.equals(".mrha") || perintah_pesan.equals("/mrha")) {


                } else if (perintah_pesan.equals(".mrpe") || perintah_pesan.equals("/mrpe")) {

                } else if (perintah_pesan.equals(".hamr") || perintah_pesan.equals("/hamr")) {

                } else if (perintah_pesan.equals(".pemr") || perintah_pesan.equals("/pemr")) {

                } else if (perintah_pesan.equals(".reha") || perintah_pesan.equals("/reha")) {

                } else if (perintah_pesan.equals(".repe") || perintah_pesan.equals("/repe")) {

                } else {
                    try {
                        SendMessage message = new SendMessage() // Create a message object object
                                .setChatId(id_chat)
                                .setText("Perintah tak dimengerti");
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }

            } else {
                try {
                    SendMessage message = new SendMessage() // Create a message object object
                            .setChatId(id_chat)
                            .setText("Perintah tak dikenali");
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    private int[] isiPesanDgAwalAkhirAyat(String isiPesan) {
        String[] pisahSurohdanAyatnya = isiPesan.split(":");
        String[] pisahAyatMuladanAkhir = pisahSurohdanAyatnya[1].split("-");
        noSuroh = Integer.parseInt(pisahSurohdanAyatnya[0]);
        awalAyat = Integer.parseInt(pisahAyatMuladanAkhir[0]);
        akhirAyat = Integer.parseInt(pisahAyatMuladanAkhir[1]);

        int[] arrIsiPesan = {noSuroh, awalAyat, akhirAyat};

        return arrIsiPesan;

    }

}
