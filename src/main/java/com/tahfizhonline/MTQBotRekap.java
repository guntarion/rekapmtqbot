package com.tahfizhonline;

import com.tahfizhonline.entity.SqliteJdbc;
import com.tahfizhonline.fungsidukung.InfoDukung;
import com.vdurmont.emoji.EmojiParser;
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
//        return "412365466:AAGLovUBFxeLjl-yOAWXzp1owkP7pfk18zQ";
        return "170874075:AAE6U-akZk3c1ppdwyJjAC2M477tVWWRitM";

    }

    @Override
    public void onUpdateReceived(Update update) {

        InfoDukung infoDukung = new InfoDukung();
        SqliteJdbc database = new SqliteJdbc();
        String namaSuroh;

        // TODO
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            String isi_pesan = update.getMessage().getText();
            String santri_first_name = "";
            String santri_last_name = "";
            String santri_username = "";

            long id_chat = update.getMessage().getChatId();

            // cek apakah ini percakapan group atau pribadi (chat)
            if (id_chat < 0 ) {
                santri_first_name = update.getMessage().getFrom().getFirstName();
                santri_last_name = update.getMessage().getFrom().getLastName();
                santri_username = update.getMessage().getFrom().getUserName();
            } else {
                santri_first_name = update.getMessage().getChat().getFirstName();
                santri_last_name = update.getMessage().getChat().getLastName();
                santri_username = update.getMessage().getChat().getUserName();
            }

//            System.out.println("ChatID = " + id_chat);
//            System.out.println(santri_first_name + " " + santri_last_name);
//            System.out.println(santri_username);
            String santri_last_ringkas = santri_last_name.substring(0,1).toUpperCase() + ".";
//            System.out.println(santri_last_ringkas);

//            String str = "An :grinning:awesome :smiley:string &#128516;with a few :wink:emojis!";
//            String result = EmojiParser.parseToUnicode(str);
//            System.out.println(result);

            if (isi_pesan.equals("/tes")) {
                long chat_id_sasaran = -239673816;

                try {
                    SendMessage message = new SendMessage() // Create a message object object
                            .setChatId(chat_id_sasaran)
                            .setText(EmojiParser.parseToUnicode("Tes berhasil :sparkling_heart: "));
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (isi_pesan.equals(".rep")) {

            // -- SIMAK SETORAN
            } else if (isi_pesan.equals(".sim") || isi_pesan.equals("/sim")) {
                String isiRespon = santri_first_name + " " + santri_last_ringkas + EmojiParser.parseToUnicode(" :speech_balloon: ");
                SendMessage pesanBalik = new SendMessage().setChatId(id_chat).setText(isiRespon);
                database.simakSetoran(santri_username);
                try { execute(pesanBalik); } catch (TelegramApiException e) { e.printStackTrace(); }
            // -- SAKIT
            } else if (isi_pesan.equals(".skt") || isi_pesan.equals("/skt")) {
                String isiRespon = santri_first_name + " " + santri_last_ringkas + EmojiParser.parseToUnicode(" :pill: ");
                SendMessage pesanBalik = new SendMessage().setChatId(id_chat).setText(isiRespon);
                database.setUdzurSakit(santri_username);
                try { execute(pesanBalik); } catch (TelegramApiException e) { e.printStackTrace(); }
            // -- UDZUR
            } else if (isi_pesan.equals(".udz") || isi_pesan.equals("/udz")) {
                String isiRespon = santri_first_name + " " + santri_last_ringkas + EmojiParser.parseToUnicode(" :radioactive: ");
                SendMessage pesanBalik = new SendMessage().setChatId(id_chat).setText(isiRespon);
                database.setUdzurSakit(santri_username);
                try { execute(pesanBalik); } catch (TelegramApiException e) { e.printStackTrace(); }
            } else if (isi_pesan.length() > 3) {
//                String tigaHurufPertama = isi_pesan.substring(0, Math.min(isi_pesan.length(), 4));
                String[] infoKiriman = isi_pesan.split(" ");
                String perintah_pesan = infoKiriman[0];
                String inti_pesan = infoKiriman[1];
                System.out.println(infoKiriman[0]);
                System.out.println(inti_pesan);

                // -- SETORAN BARU
                if (perintah_pesan.equals(".new") || perintah_pesan.equals("/new")) {
                    if (inti_pesan.equals("")) {
                        SendMessage pesanBalik = new SendMessage().setChatId(id_chat)
                                .setText(EmojiParser.parseToUnicode(":no_entry: Info setoran kosong"));
                        try { execute(pesanBalik); } catch (TelegramApiException e) { e.printStackTrace();}
                    } else if (inti_pesan.contains("-")) {
                        int[] infoSurohAyat = isiPesanDgAwalAkhirAyat(inti_pesan);
                        noSuroh = infoSurohAyat[0]; awalAyat = infoSurohAyat[1]; akhirAyat = infoSurohAyat[2];
                        namaSuroh = infoDukung.namaSuroh(noSuroh);

                        String isiRespon = santri_first_name + " " + santri_last_ringkas + EmojiParser.parseToUnicode(" :eight_spoked_asterisk: ")
                                + namaSuroh + " [" + noSuroh + "]:" + awalAyat + "-" + akhirAyat;
                        SendMessage pesanBalik = new SendMessage().setChatId(id_chat).setText(isiRespon);
                        try {
                            execute(pesanBalik);
                            database.insertSetoranHarian(santri_username, noSuroh, awalAyat, akhirAyat);
                        } catch (TelegramApiException e) { e.printStackTrace(); }

                    }
                // -- SETORAN REVISI
                } else if (perintah_pesan.equals(".rev") || perintah_pesan.equals("/rev")) {
                    if (inti_pesan.contains("-")) {
                        int[] infoSurohAyat = isiPesanDgAwalAkhirAyat(inti_pesan);
                        noSuroh = infoSurohAyat[0]; awalAyat = infoSurohAyat[1]; akhirAyat = infoSurohAyat[2];
                        namaSuroh = infoDukung.namaSuroh(noSuroh);
                        String isiRespon = santri_first_name + " " + santri_last_ringkas + EmojiParser.parseToUnicode(" :recycle: ")
                                + namaSuroh + " [" + noSuroh + "]:" + awalAyat + "-" + akhirAyat;
                        SendMessage pesanBalik = new SendMessage().setChatId(id_chat).setText(isiRespon);
                        try {
                            execute(pesanBalik);
                            database.insertSetoranHarian(santri_username, noSuroh, awalAyat, akhirAyat);
                        } catch (TelegramApiException e) { e.printStackTrace(); }
                    }
                // -- SETORAN MRJ
                } else if (perintah_pesan.equals(".mrj") || perintah_pesan.equals("/mrj")) {
                    int intHalMRJ = Integer.parseInt(inti_pesan);
                    String isiRespon = santri_first_name + " " + santri_last_ringkas + EmojiParser.parseToUnicode(" :m: ")
                            + "Hal." + intHalMRJ;
                    SendMessage pesanBalik = new SendMessage().setChatId(id_chat).setText(isiRespon);
                    try {
                        execute(pesanBalik);
                        database.mrjSetoran(santri_username);
                    } catch (TelegramApiException e) { e.printStackTrace(); }

                } else if (perintah_pesan.equals(".reg") || perintah_pesan.equals("/reg")) {
                    String namaKelas = inti_pesan;
                    database.registerKelas(inti_pesan, id_chat);

                    String isiRespon = "Grup ini telah teregistrasi sebagai kelas " + namaKelas;
                    SendMessage pesanBalik = new SendMessage().setChatId(id_chat).setText(isiRespon);
                    try {
                        execute(pesanBalik);
                        database.mrjSetoran(santri_username);
                    } catch (TelegramApiException e) { e.printStackTrace(); }
                } else if (perintah_pesan.equals(".dis") || perintah_pesan.equals("/dis")) {
                    String namaKelas = inti_pesan;
                    database.disableKelas(inti_pesan, id_chat);
                    String isiRespon = "Grup ini telah terdisable sebagai kelas " + namaKelas;
                    SendMessage pesanBalik = new SendMessage().setChatId(id_chat).setText(isiRespon);
                    try {
                        execute(pesanBalik);
                        database.mrjSetoran(santri_username);
                    } catch (TelegramApiException e) { e.printStackTrace(); }
                    // Mendaftarkan kelas baru
                } else if (perintah_pesan.equals(".add") || perintah_pesan.equals("/add")) {
                    String[] masukkanSantri = inti_pesan.split(" ");
                    database.insertSantriToKelas(masukkanSantri[0], masukkanSantri[1]);

                    String isiRespon = "Santri '" + santri_first_name + " " + santri_last_name + "' telah" +
                            " didaftarkan menempati slot kelas " + masukkanSantri[1];
                    SendMessage pesanBalik = new SendMessage().setChatId(id_chat).setText(isiRespon);
                    try { execute(pesanBalik); } catch (TelegramApiException e) { e.printStackTrace(); }
                } else if (perintah_pesan.equals(".del") || perintah_pesan.equals("/del")) {
                    database.removeSantriFromKelas(isi_pesan);

                    String isiRespon = "Santri '" + santri_first_name + " " + santri_last_name + "' telah" +
                            " didaftarkan menempati slot kelasnya ";
                    SendMessage pesanBalik = new SendMessage().setChatId(id_chat).setText(isiRespon);
                    try { execute(pesanBalik); } catch (TelegramApiException e) { e.printStackTrace(); }

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

    public void kirimReport(String isiPesan, String alamatGrup) {
        long chat_id_sasaran = Long.parseLong(alamatGrup);

        try {
            SendMessage message = new SendMessage() // Create a message object object
                    .setChatId(chat_id_sasaran)
                    .setText(EmojiParser.parseToUnicode(isiPesan))  ;
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}
