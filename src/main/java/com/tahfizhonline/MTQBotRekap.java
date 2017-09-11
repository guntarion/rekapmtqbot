package com.tahfizhonline;

import com.tahfizhonline.entity.SqliteJdbc;
import com.tahfizhonline.fungsidukung.InfoDukung;
import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.regex.Pattern;


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
//        return "170874075:AAE6U-akZk3c1ppdwyJjAC2M477tVWWRitM";
        return "417443729:AAH0JCiquUWFC4DSTulHvh7Ry1Dl2YEZZVs";

    }

    @Override
    public void onUpdateReceived(Update update) {

        InfoDukung infoDukung = new InfoDukung();
        SqliteJdbc database = new SqliteJdbc();
        String namaSuroh;


        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            String isi_pesan = update.getMessage().getText();
            String santri_first_name = "";
            String santri_last_name = "";
            String santri_username = "";

//            System.out.println(is_OneWord(isi_pesan));


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

            // --- bila perintahnya hanya ada satu huruf
            if (is_OneWord(isi_pesan)) {

                if (Pattern.compile("/tes").matcher(isi_pesan).find()) {
                    long chat_id_sasaran = -239673816;

                    try {
                        SendMessage message = new SendMessage() // Create a message object object
                                .setChatId(chat_id_sasaran)
                                .setText(EmojiParser.parseToUnicode("Tes berhasil :sparkling_heart: "));
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (isi_pesan.equals(".grupadmin")) {
                    System.out.println(id_chat);
                    try {
                        SendMessage message = new SendMessage() // Create a message object object
                                .setChatId(id_chat)
                                .setText(EmojiParser.parseToUnicode("insyaAllah :high_brightness: "));
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                    // -- SIMAK SETORAN
                    // REGEX!
//            } else if (isi_pesan.equals(".sim") || isi_pesan.equals("/sim")) {
//            } else if (Pattern.compile(".sim").matcher(isi_pesan).find() || Pattern.compile("/sim").matcher(isi_pesan).find()) {

                    // ⏹ -- SIMAK SETORAN
                } else if (isi_pesan.equals(".sim") || isi_pesan.equals("/sim")) {

                    String isiRespon = santri_first_name + " " + santri_last_ringkas + EmojiParser.parseToUnicode(" :speech_balloon: ");
                    SendMessage pesanBalik = new SendMessage().setChatId(id_chat).setText(isiRespon);
                    database.simakSetoran(santri_username);
                    try { execute(pesanBalik); } catch (TelegramApiException e) { e.printStackTrace(); }
                    // ⏹ -- SAKIT
                } else if (isi_pesan.equals(".skt") || isi_pesan.equals("/skt")) {
                    String isiRespon = santri_first_name + " " + santri_last_ringkas + EmojiParser.parseToUnicode(" :pill: ");
                    SendMessage pesanBalik = new SendMessage().setChatId(id_chat).setText(isiRespon);
                    database.setUdzurSakit(santri_username);
                    try { execute(pesanBalik); } catch (TelegramApiException e) { e.printStackTrace(); }
                    // ⏹ -- UDZUR
                } else if (isi_pesan.equals(".udz") || isi_pesan.equals("/udz")) {
                    String isiRespon = santri_first_name + " " + santri_last_ringkas + EmojiParser.parseToUnicode(" :radioactive: ");
                    SendMessage pesanBalik = new SendMessage().setChatId(id_chat).setText(isiRespon);
                    database.setUdzurSakit(santri_username);
                    try { execute(pesanBalik); } catch (TelegramApiException e) { e.printStackTrace(); }


                    // ▶️ [ADMIN] -- LIST SELURUH KELAS
                } else if (isi_pesan.equals(".listkelas") || isi_pesan.equals("/listkelas")) {

                    String isiRespon = database.listSeluruhKelas();
                    SendMessage pesanBalik = new SendMessage().setChatId(id_chat).setText(isiRespon);
                    try { execute(pesanBalik); } catch (TelegramApiException e) { e.printStackTrace(); }


                    // ▶️ [ADMIN] -- RESET ANGKA SETORAN HARIAN
                } else if (isi_pesan.equals(".RESET_SET")) {
                    database.resetValueSetoran();
                    String isiRespon = "Seluruh today_setor has been reset.";
                    SendMessage pesanBalik = new SendMessage().setChatId(id_chat).setText(isiRespon);
                    try { execute(pesanBalik); } catch (TelegramApiException e) { e.printStackTrace(); }

                    // ▶️ [ADMIN] -- RESET ANGKA MRJ HARIAN
                } else if (isi_pesan.equals(".RESET_MRJ")) {
                    database.resetValueMRJ();
                    String isiRespon = "Seluruh today_mrjharian has been reset.";
                    SendMessage pesanBalik = new SendMessage().setChatId(id_chat).setText(isiRespon);
                    try { execute(pesanBalik); } catch (TelegramApiException e) { e.printStackTrace(); }
                    // ▶️ [ADMIN] -- RESET STATS PER GANTI BULAN HIJRIAH
                } else if (isi_pesan.equals(".RESET_BULANAN")) {
                    database.resetPresensiBulanan();
                    String isiRespon = "Seluruh stats presensi has been reset.";
                    SendMessage pesanBalik = new SendMessage().setChatId(id_chat).setText(isiRespon);
                    try { execute(pesanBalik); } catch (TelegramApiException e) { e.printStackTrace(); }


                } else if (Pattern.compile(".broadc.all").matcher(isi_pesan).find()) {
                    // TODO: broadcast to all
                    SendMessage pesanBalik = new SendMessage().setChatId(id_chat).setText("");
                    database.setUdzurSakit(santri_username);
                    try { execute(pesanBalik); } catch (TelegramApiException e) { e.printStackTrace(); }
//            } else if (isi_pesan.length() > 3) {
                }

            } else if (!is_OneWord(isi_pesan)) {
                //                String tigaHurufPertama = isi_pesan.substring(0, Math.min(isi_pesan.length(), 4));
                String[] infoKiriman = isi_pesan.split(" ");
                String perintah_pesan = infoKiriman[0];
                String inti_pesan = infoKiriman[1];
                int banyakPesannya = infoKiriman.length;
                System.out.println("isi array = " + banyakPesannya);
                String pesan_tambahan ="";
                if (banyakPesannya==3) pesan_tambahan = infoKiriman[2];
//                System.out.println("pesan tambahan: " + pesan_tambahan);
//                System.out.println("Perintah Pesan = " + perintah_pesan + " - " + inti_pesan);

                // ⏹ -- SETORAN BARU
                if (perintah_pesan.equals(".new") || perintah_pesan.equals("/new")) {
                    if (inti_pesan.equals(null)) {
                        SendMessage pesanBalik = new SendMessage().setChatId(id_chat)
                                .setText(EmojiParser.parseToUnicode(":no_entry: Harap sertakan info setoran."));
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
                            database.insertSetoranHarian(santri_username, santri_first_name + " " + santri_last_ringkas, noSuroh, awalAyat, akhirAyat);
                        } catch (TelegramApiException e) { e.printStackTrace(); }

                    }
                    // ⏹ -- SETORAN REVISI
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
                            database.insertSetoranHarian(santri_username, santri_first_name + " " + santri_last_ringkas, noSuroh, awalAyat, akhirAyat);
                        } catch (TelegramApiException e) { e.printStackTrace(); }
                    }

                    // ⏹️ -- SETORAN MRJ
                } else if (perintah_pesan.equals(".mrj") || perintah_pesan.equals("/mrj")) {
                    int intHalMRJ = Integer.parseInt(inti_pesan);
                    String isiRespon = santri_first_name + " " + santri_last_ringkas + EmojiParser.parseToUnicode(" :m: ")
                            + "Hal." + intHalMRJ;
                    SendMessage pesanBalik = new SendMessage().setChatId(id_chat).setText(isiRespon);
                    try {
                        execute(pesanBalik);
                        database.mrjSetoran(santri_username, intHalMRJ);
                    } catch (TelegramApiException e) { e.printStackTrace(); }


                    // 🔲 ----- FUNGSI ADMIN ----- 🔲

                    // ▶️ [ADMIN] -- MENDAFTARKAN KELAS BARU
                } else if (perintah_pesan.equals(".register") || perintah_pesan.equals("/register")) {
                    String isiRespon ="";
                    if (inti_pesan.length()==4) {
                        database.registerKelas(inti_pesan, id_chat);
//                        isiRespon = "tes";
                        isiRespon = EmojiParser.parseToUnicode(":new: Grup ini telah teregistrasi sebagai kelas ") + indahkanNamaKelas(inti_pesan);
                    } else {
                        isiRespon = EmojiParser.parseToUnicode(":exclamation: Format input salah. Digit input kurang.");
                    }

                    SendMessage pesanBalik = new SendMessage().setChatId(id_chat).setText(isiRespon);
                    try {
                        execute(pesanBalik);
                    } catch (TelegramApiException e) { e.printStackTrace(); }

                    // ▶️ [ADMIN] -- ENABLE KELAS
                } else if (perintah_pesan.equals(".enable") || perintah_pesan.equals("/enable")) {
                    database.enableKelas(inti_pesan, id_chat);
                    String isiRespon = EmojiParser.parseToUnicode(":arrow_forward:")
                            + " Kelas " + indahkanNamaKelas(inti_pesan) + " dinyatakan aktif";
                    SendMessage pesanBalik = new SendMessage().setChatId(id_chat).setText(isiRespon);
                    try {
                        execute(pesanBalik);
                    } catch (TelegramApiException e) { e.printStackTrace(); }

                    // ▶️ [ADMIN] -- DISABLE KELAS
                } else if (perintah_pesan.equals(".disable") || perintah_pesan.equals("/disable")) {
                    database.disableKelas(inti_pesan);
                    String isiRespon = EmojiParser.parseToUnicode(":double_vertical_bar:")
                            + " Kelas " + indahkanNamaKelas(inti_pesan) + " dinyatakan NON aktif";
                    SendMessage pesanBalik = new SendMessage().setChatId(id_chat).setText(isiRespon);
                    try {
                        execute(pesanBalik);
                    } catch (TelegramApiException e) { e.printStackTrace(); }

                    // ▶️ [ADMIN] -- MENGHAPUS KELAS
                } else if (perintah_pesan.equals(".DELETE") || perintah_pesan.equals("/DELETE")) {
                    database.deleteKelasTerpilih(inti_pesan);
                    String isiRespon = EmojiParser.parseToUnicode(":broken_heart:")
                            + " Kelas " + indahkanNamaKelas(inti_pesan) + " telah dihapus dari database";
                    SendMessage pesanBalik = new SendMessage().setChatId(id_chat).setText(isiRespon);
                    try {
                        execute(pesanBalik);
                    } catch (TelegramApiException e) { e.printStackTrace(); }


                }
                // ️ [ADMIN] -- MEMBUAT 10 SLOT OKUPANSI UNTUK SATU KELAS TERTENTU
                /*else if (perintah_pesan.equals(".buatslotkelas") || perintah_pesan.equals("/buatslotkelas")) {
                    String isiRespon = "Slot untuk kelas " + inti_pesan + " telah dibuat";
                    SendMessage pesanBalik = new SendMessage().setChatId(id_chat).setText(isiRespon);
                    try {
                        database.createKelasOkupansi(inti_pesan);
                        execute(pesanBalik);
                    } catch (TelegramApiException e) { e.printStackTrace(); }*/
//                 }

                    // ▶️ [ADMIN] -- MENDAFTARKAN SATU ORANG SANTRI KE KELAS
                else if (perintah_pesan.equals(".addsatuan") || perintah_pesan.equals("/addsatuan")) {
//                    System.out.println(inti_pesan + " dan " + pesan_tambahan);
                    database.insertSantriToKelas(inti_pesan, pesan_tambahan);

                    String isiRespon = "Santri @" + pesan_tambahan + " telah" +
                            " didaftarkan menempati slot kelas di " + inti_pesan.substring(0,3);;
                    SendMessage pesanBalik = new SendMessage().setChatId(id_chat).setText(isiRespon);
                    try { execute(pesanBalik); } catch (TelegramApiException e) { e.printStackTrace(); }

                    // ▶️ [ADMIN] -- MENDAFTARKAN SEPULUH SANTRI KE KELAS
                } else if (perintah_pesan.equals(".addall") || perintah_pesan.equals("/addall")) {
                    System.out.println(inti_pesan);
                    System.out.println(pesan_tambahan);
                    database.insertBulkSantriToKelas(inti_pesan, pesan_tambahan);
                    String isiRespon = EmojiParser.parseToUnicode(":eight_pointed_black_star:") +
                            "Santri-santri telah" +
                            " didaftarkan menempati slot kelas di " + indahkanNamaKelas(inti_pesan);
                    SendMessage pesanBalik = new SendMessage().setChatId(id_chat).setText(isiRespon);
                    try { execute(pesanBalik); } catch (TelegramApiException e) { e.printStackTrace(); }

                    // ▶️ [ADMIN] -- MENGHAPUS SATU SANTRI DARI KELAS
                } else if (perintah_pesan.equals(".remove") || perintah_pesan.equals("/remove")) {
                    database.removeSantriFromKelas(inti_pesan);

                    String isiRespon = EmojiParser.parseToUnicode(":broken_heart:") +
                            "Santri @" + inti_pesan + " telah" +
                            " diremove dari slot kelasnya.";
                    SendMessage pesanBalik = new SendMessage().setChatId(id_chat).setText(isiRespon);
                    try { execute(pesanBalik); } catch (TelegramApiException e) { e.printStackTrace(); }

                    // ▶️ [ADMIN] -- LIST OKUPANSI KELAS TERPILIH
                } else if (perintah_pesan.equals(".okupansi") || perintah_pesan.equals("/okupansi")) {
                    String isiRespon = database.listingSlotKelasOkupansi(inti_pesan);;
                    SendMessage pesanBalik = new SendMessage().setChatId(id_chat).setText(isiRespon);
                    try { execute(pesanBalik); } catch (TelegramApiException e) { e.printStackTrace(); }


                    // ▶️ [ADMIN] -- KIRIM REKAP SETORAN KELAS TERPILIH
                } else if (perintah_pesan.equals(".rekapsetoran") || perintah_pesan.equals("/rekapsetoran")) {
                    database.generateRekapHarianPerKelas(inti_pesan);
                    SendMessage pesanBalik = new SendMessage().setChatId(id_chat).setText("Rekap setoran terkirim");
                    try { execute(pesanBalik); } catch (TelegramApiException e) { e.printStackTrace(); }

                    // ▶️ [ADMIN] -- KIRIM REKAP MUROJA'AH KELAS TERPILIH
                } else if (perintah_pesan.equals(".mrjharian") || perintah_pesan.equals("/mrjharian")) {
                    database.generateDailyMRJreport(inti_pesan);
                    String isiRespon = database.listingSlotKelasOkupansi(inti_pesan);;
                    SendMessage pesanBalik = new SendMessage().setChatId(id_chat).setText("Rekap MRJ terkirim");
                    try { execute(pesanBalik); } catch (TelegramApiException e) { e.printStackTrace(); }

                    // ▶️ [ADMIN] -- SET VALUE GHOIB SANTRI
                } else if (perintah_pesan.equals(".setghoib") || perintah_pesan.equals("/setghoib")) {
                    database.setValueGhoibSantri(inti_pesan, Integer.parseInt(pesan_tambahan));
                    String isiRespon = "Value ghoib untuk santri ybs telah diubah menjadi " + pesan_tambahan;;
                    SendMessage pesanBalik = new SendMessage().setChatId(id_chat).setText(isiRespon);
                    try { execute(pesanBalik); } catch (TelegramApiException e) { e.printStackTrace(); }

                    // ▶️ [ADMIN] -- SET VALUE UDZUR SANTRI
                } else if (perintah_pesan.equals(".setudzur") || perintah_pesan.equals("/setudzur")) {
                    database.setValueUdzurSantri(inti_pesan, Integer.parseInt(pesan_tambahan));
                    String isiRespon = "Value udzur untuk santri ybs telah diubah menjadi " + pesan_tambahan;;
                    SendMessage pesanBalik = new SendMessage().setChatId(id_chat).setText(isiRespon);
                    try { execute(pesanBalik); } catch (TelegramApiException e) { e.printStackTrace(); }



                    // TODO: broadcast ke kelas tertentu
                } else if (perintah_pesan.equals(".rep") || perintah_pesan.equals("/rep")) {

                    String isiPesanReply = update.getMessage().getReplyToMessage().getText();
                    long chat_id_sasaran = -239673816;

                    String isiRespon = isiPesanReply;
                    SendMessage pesanBalik = new SendMessage().setChatId(chat_id_sasaran).setText(isiRespon);
                    try { execute(pesanBalik); } catch (TelegramApiException e) { e.printStackTrace(); }

                }
            }




                /*else {
                    try {
                        SendMessage message = new SendMessage() // Create a message object object
                                .setChatId(id_chat)
                                .setText("Perintah tak dimengerti");
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }*/


            /*else {
                try {
                    SendMessage message = new SendMessage() // Create a message object object
                            .setChatId(id_chat)
                            .setText("Perintah tak dikenali");
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }*/


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

    public static boolean is_OneWord(String s) {
        return (s.length() > 0 && s.split("\\s+").length == 1);
    }

    // TODO: perindah nama kelas
    public String perindahNamaKelas(String namaKelas) {
        String indikasiNamaKelas =  namaKelas.substring(0,2);
        String indikasiKategori = namaKelas.substring(2,3);
        String indikasiGender = namaKelas.substring(3,4);

        System.out.println(indikasiNamaKelas);
        System.out.println(indikasiKategori);
        System.out.println(indikasiGender);

        if (indikasiGender.equals("01")) {

        }

        String kategoriKelas = "";
        if (indikasiKategori.indexOf("a") >=0 || indikasiKategori.indexOf("A") >=0) {
            kategoriKelas = EmojiParser.parseToUnicode(":a:");
        }
        if (indikasiKategori.indexOf("b") >=0 || indikasiKategori.indexOf("B") >=0) {
            kategoriKelas = EmojiParser.parseToUnicode(":b:");
        }

        String kategoriGender = "";
        if (indikasiGender.indexOf("i") >=0 || indikasiGender.indexOf("I") >=0) {
            kategoriGender = "1";
        }
        if (indikasiGender.indexOf("a") >=0 || indikasiGender.indexOf("A") >=0) {
            kategoriGender = "2";
        }


        return null;
    }


    public String indahkanNamaKelas(String namaKelas) {
        String indikasiNamaKelas =  namaKelas.substring(0,2);
        String indikasiKategori = namaKelas.substring(2,3);
        String indikasiGender = namaKelas.substring(3,4);

        String digi1Kelas = indikasiNamaKelas.substring(0,1);
        String digi2Kelas = indikasiNamaKelas.substring(1,2);

        if (digi1Kelas.equals("0")) digi1Kelas = "";
        digi1Kelas = ubahAngkaJadiCakep(digi1Kelas);
        digi2Kelas = ubahAngkaJadiCakep(digi2Kelas);

        String kategoriKelas = "";
        if (indikasiKategori.indexOf("a") >=0 || indikasiKategori.indexOf("A") >=0) {
            kategoriKelas = EmojiParser.parseToUnicode(":a:");
        }
        if (indikasiKategori.indexOf("b") >=0 || indikasiKategori.indexOf("B") >=0) {
            kategoriKelas = EmojiParser.parseToUnicode(":b:");
        }

        if (indikasiGender.equals("i")) {
            indikasiGender = " ikhwan";
        } else indikasiGender = " akhowat";

        String namaKelasBentukan = digi1Kelas + digi2Kelas + kategoriKelas + indikasiGender;

        return namaKelasBentukan;
    }

    public String ubahAngkaJadiCakep(String digit) {
        String digitEmoji = "";
        if (digit.equals("0")) digitEmoji = EmojiParser.parseToUnicode(":zero:");
        else if (digit.equals("1")) digitEmoji = EmojiParser.parseToUnicode(":one:");
        else if (digit.equals("2")) digitEmoji = EmojiParser.parseToUnicode(":two:");
        else if (digit.equals("3")) digitEmoji = EmojiParser.parseToUnicode(":three:");
        else if (digit.equals("4")) digitEmoji = EmojiParser.parseToUnicode(":four:");
        else if (digit.equals("5")) digitEmoji = EmojiParser.parseToUnicode(":five:");
        else if (digit.equals("6")) digitEmoji = EmojiParser.parseToUnicode(":six:");
        else if (digit.equals("7")) digitEmoji = EmojiParser.parseToUnicode(":seven:");
        else if (digit.equals("8")) digitEmoji = EmojiParser.parseToUnicode(":eight:");
        else if (digit.equals("9")) digitEmoji = EmojiParser.parseToUnicode(":nine:");
        return digitEmoji;
    }

    public void kirimReport(String isiPesan, String alamatGrup) {
//        System.out.println(alamatGrup);
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

    public void kirimNotifikasiAdaYgUdzurKelewatan(String isiPesan) {
        long chat_id_sasaran = 1234567;

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
