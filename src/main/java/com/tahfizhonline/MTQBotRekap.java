package com.tahfizhonline;

import com.tahfizhonline.entitydefinition.SqliteJdbc;
import com.tahfizhonline.entityoperation.MySQLhibernate;
import com.tahfizhonline.entityoperation.OperasiInfoKelas;
import com.tahfizhonline.entityoperation.OperasiOkupansi;
import com.tahfizhonline.entityoperation.OperasiSantriReguler;
import com.tahfizhonline.fungsidukung.InfoDukung;
import com.tahfizhonline.fungsidukung.OperasiTanggal;
import com.vdurmont.emoji.EmojiParser;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.regex.Pattern;


public class MTQBotRekap extends TelegramLongPollingBot {

    int noSuroh, awalAyat, akhirAyat;
    private static org.apache.log4j.Logger log = Logger.getLogger(MTQBotRekap.class);

    @Override
    public String getBotUsername() {
        return "MTQ Rekap Bot";
    }

    @Override
    public String getBotToken() {
//        return "412365466:AAGLovUBFxeLjl-yOAWXzp1owkP7pfk18zQ";
//        return "170874075:AAE6U-akZk3c1ppdwyJjAC2M477tVWWRitM"; // guntarionbot
        return "417443729:AAH0JCiquUWFC4DSTulHvh7Ry1Dl2YEZZVs"; // mtqbot
    }

    @Override
    public void onUpdateReceived(Update update) {

        log.setLevel(Level.INFO);
        InfoDukung infoDukung = new InfoDukung();
        SqliteJdbc database = new SqliteJdbc();
        OperasiOkupansi db_okupansi = new OperasiOkupansi();
        OperasiSantriReguler db_santri_reguler = new OperasiSantriReguler();
        OperasiInfoKelas db_info_kelas = new OperasiInfoKelas();

        String namaSuroh;
        String isiRespon = "";


        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {

            String isi_pesan = update.getMessage().getText();

            String first_name = "";
            String last_name = "";
            String username = "";
            long user_id = 0L;
            long message_id = 0L;
            long message_date = 0L;

//            System.out.println(EmojiParser.parseToAliases(isi_pesan));

            long chat_id = update.getMessage().getChatId();

//            System.out.println("isi_pesan = " + isi_pesan);
//            System.out.println(user_id);
//            System.out.println(chat_id);
//            System.out.println("---Jalankan perintah di sini");
//            System.out.println("");
//            System.out.println("from = "  + update.getMessage().getReplyToMessage().getFrom());
//            System.out.println("date = "  + update.getMessage().getReplyToMessage().getDate());
//            System.out.println("message text = "  + update.getMessage().getReplyToMessage().getText());
//            System.out.println("message ID = "  + update.getMessage().getReplyToMessage().getMessageId());
//            System.out.println("is command? = " + update.getMessage().getReplyToMessage().isCommand());
//            System.out.println("is reply? = " + update.getMessage().getReplyToMessage().isReply());
//            System.out.println("is user message? = " + update.getMessage().getReplyToMessage().isUserMessage());
//            System.out.println("is grup message? = " + update.getMessage().getReplyToMessage().isGroupMessage());




            // ---------[ C H E C K I N G ]-----------------------------------------------------------------------
            // cek apakah ini percakapan group atau pribadi (chat). Nantinya akan jadi restriction
            boolean isOnGrup = true;
            if (chat_id < 0 ) {
                first_name = update.getMessage().getFrom().getFirstName();
                last_name = update.getMessage().getFrom().getLastName();
                username = update.getMessage().getFrom().getUserName();
                user_id = update.getMessage().getFrom().getId();
                message_id = update.getMessage().getMessageId();
                message_date = update.getMessage().getDate();
            } else {
                isOnGrup = false;
                first_name = update.getMessage().getChat().getFirstName();
                last_name = update.getMessage().getChat().getLastName();
                username = update.getMessage().getChat().getUserName();
                user_id = update.getMessage().getChat().getId();
            }


            String last_ringkas = "";
            // Antisipasi user tidak mengisi last_name
//            if (last_name.length()>0) {
            if (last_name!=null) {
                // Ambil satu huruf dari nama belakang, bubuhi dg titik
                last_ringkas = last_name.substring(0,1).toUpperCase() + ".";
            }


            // ---------[ KOREKSIAN MUSYRIF ]-----------------------------------------------------------------------
            // cek untuk pesan berbentuk koreksian dari musyrif - gak perlu lewat pengecekan
            long idPesanYgDireply = 0L;
            long idPengirimYgDireply = 0L;
            // ⏹✅ MUSYRIF KIRIM KOREKSIAN BOLEH LANJUT
            if (EmojiParser.parseToAliases(isi_pesan).equals(":white_check_mark:")) {
                // Cek apakah pesannya merupakan pesan reply
                if (update.getMessage().getReplyToMessage()!=null) {
                    // TODO: musyrif kirim koreksian lolos
//                    first_name = update.getMessage().getReplyToMessage().getFrom().getFirstName();
//                    last_name = update.getMessage().getReplyToMessage().getFrom().getLastName();
//                    username = update.getMessage().getReplyToMessage().getFrom().getUserName();
                    idPesanYgDireply = update.getMessage().getReplyToMessage().getMessageId();
                    idPengirimYgDireply = user_id = update.getMessage().getReplyToMessage().getFrom().getId();
                    db_okupansi.koreksiLulus(idPesanYgDireply);
//                    System.out.println(idPesanYgDireply + " oleh " + idPengirimYgDireply);
//                    long info_setoran = update.getMessage().getMessageId();
//
//                    isiRespon = "first_name: " + first_name +
//                            "\nlast_name: " + last_name + "\nusername: " + username + "\nuser_id: " + user_id;
////                    kirimkanPesan("It works lolos", chat_id);
//                    kirimkanPesan(isiRespon, chat_id);
                }
            }

            // use reply_to_message_id
            // MUSYRIF KIRIM KOREKSIAN LOLOS DG CATATAN
            if (EmojiParser.parseToAliases(isi_pesan).equals(":white_check_mark: :memo:")) {
                // Cek apakah pesannya merupakan pesan reply
                if (update.getMessage().getReplyToMessage()!=null) {

                    // TODO: musyrif kirim koreksian lolos dg catatan
                    kirimkanPesan("It works", chat_id);
                }
            }
            // ⏹✅ MUSYRIF KIRIM KOREKSIAN MENGULANG
            if (EmojiParser.parseToAliases(isi_pesan).equals(":recycle:")) {
                // Cek apakah pesannya merupakan pesan reply
                if (update.getMessage().getReplyToMessage()!=null) {
                    idPesanYgDireply = update.getMessage().getReplyToMessage().getMessageId();
                    db_okupansi.koreksiUlang(idPesanYgDireply);
                }
            }


            // ---------[ PESAN BERISI SATU HURUF ]-----------------------------------------------------------------

            if (is_OneWord(isi_pesan)) {

                if (Pattern.compile(".test").matcher(isi_pesan).find()) {
                    long chat_id_sasaran = -239673816;
                    kirimkanPesan(EmojiParser.parseToUnicode("Tes berhasil :sparkling_heart: "), chat_id_sasaran);

                } else if (isi_pesan.equals(".id")) {
                    long chat_id_sasaran = -239673816;
                    log.info(first_name + " (" + username + ") " + " is on " + isi_pesan);
                    isiRespon = "first_name: " + first_name +
                            "\nlast_name: " + last_name + "\nusername: " + username + "\nuser_id: " + user_id;

                    kirimkanPesan(isiRespon, chat_id_sasaran);




                } else if (isi_pesan.equals(".tesbot")) {
                    log.info(first_name + " (" + username + ") " + " is on " + isi_pesan);
                    if (adalahAdmin(user_id)) {
                        System.out.println(chat_id + " - " + user_id);
                        isiRespon = EmojiParser.parseToUnicode("insyaAllah :high_brightness: ");
                    } else {
                        isiRespon = EmojiParser.parseToUnicode(":information_source:")
                                + " Perintah hanya bisa dijalankan oleh Admin MTQ.";
                    }
                    kirimkanPesan(isiRespon, chat_id);


                    // ⏹✅ -- SIMAK SETORAN
                } else if (isi_pesan.equals(".sim") && isOnGrup) {
                    log.info(first_name + " (" + username + ") " + " is on " + isi_pesan);
                    if (db_okupansi.santriTelahTerdaftarByID(user_id)) {
                        isiRespon = first_name + " " + last_ringkas + EmojiParser.parseToUnicode(" :speech_balloon: ");
                        db_okupansi.setSimakSetoran(user_id);
                    } else {
                        isiRespon = ":warning: Santri belum/tidak lagi terdaftar di database RekapMTQbot. Setoran tidak direkap dan aktivitas santri tidak masuk monitoring sistem.";
                    }
                    kirimkanPesan(isiRespon, chat_id);


                    // ⏹✅ -- SAKIT
                } else if (isi_pesan.equals(".skt") && isOnGrup) {
                    log.info(first_name + " (" + username + ") " + " is on " + isi_pesan);
                    if (db_okupansi.santriTelahTerdaftarByID(user_id)) {
                        isiRespon = first_name + " " + last_ringkas + EmojiParser.parseToUnicode(" :pill: ");
                        db_okupansi.setUdzurSakit(user_id);
                    } else {
                        isiRespon = ":warning: Santri belum/tidak lagi terdaftar di database RekapMTQbot. Setoran tidak direkap dan aktivitas santri tidak masuk monitoring sistem.";
                    }
                    kirimkanPesan(isiRespon, chat_id);


                    // ⏹✅ -- UDZUR
                } else if (isi_pesan.equals(".udz") && isOnGrup) {
                    if (db_okupansi.santriTelahTerdaftarByID(user_id)) {
                        isiRespon = first_name + " " + last_ringkas + EmojiParser.parseToUnicode(" :pill: ");
                        db_okupansi.setUdzurSakit(user_id);
                    } else {
                        isiRespon = ":warning: Santri belum/tidak lagi terdaftar di database RekapMTQbot. Setoran tidak direkap dan aktivitas santri tidak masuk monitoring sistem.";
                    }
                    kirimkanPesan(isiRespon, chat_id);


                    // ▶️✅ [ADMIN] -- LIST SELURUH KELAS
                } else if (isi_pesan.equals(".listkelas") || isi_pesan.equals("/listkelas")) {
                    log.info(first_name + " " + last_name + " (" + username + ") "
                            + " is on " + isi_pesan);
                    if (adalahAdmin(user_id)) {
                        isiRespon = db_info_kelas.listSeluruhKelas();
                    } else {
                        isiRespon = EmojiParser.parseToUnicode(":information_source:")
                                + " Perintah hanya bisa dijalankan oleh Admin MTQ.";
                    }
                    kirimkanPesan(isiRespon, chat_id);

                    // ▶️🔴✅ [ADMIN] -- RESET ANGKA SETORAN HARIAN
                } else if (isi_pesan.equals(".RESET_SET")) {
                    log.info(first_name + " " + last_name + " (" + username + ") "
                            + " is on " + isi_pesan);
                    if (adalahAdmin(user_id)) {
                        db_okupansi.resetValueSetoran();
                        isiRespon = "Seluruh today_setor has been reset.";
                    } else {
                        isiRespon = EmojiParser.parseToUnicode(":information_source:")
                                + " Perintah hanya bisa dijalankan oleh Admin MTQ.";
                    }
                    kirimkanPesan(isiRespon, chat_id);

                    // ▶🔴✅️ [ADMIN] -- RESET ANGKA MRJ HARIAN
                } else if (isi_pesan.equals(".RESET_MRJ")) {
                    log.info(first_name + " " + last_name + " (" + username + ") "
                            + " is on " + isi_pesan);
                    if (adalahAdmin(user_id)) {
                        db_okupansi.resetValueMRJ();
                        isiRespon = "Seluruh today_mrjharian has been reset.";
                    } else {
                        isiRespon = EmojiParser.parseToUnicode(":information_source:")
                                + " Perintah hanya bisa dijalankan oleh Admin MTQ.";
                    }
                    kirimkanPesan(isiRespon, chat_id);
                    // ▶️🔴✅ [ADMIN] -- RESET STATS PER GANTI BULAN HIJRIAH
                } else if (isi_pesan.equals(".RESET_BULANAN")) {
                    log.info(first_name + " " + last_name + " (" + username + ") "
                            + " is on " + isi_pesan);
                    if (adalahAdmin(user_id)) {
                        db_okupansi.resetPresensiBulanan();
                        isiRespon = "Seluruh stats presensi has been reset.";
                    } else {
                        isiRespon = EmojiParser.parseToUnicode(":information_source:")
                                + " Perintah hanya bisa dijalankan oleh Admin MTQ.";
                    }
                    kirimkanPesan(isiRespon, chat_id);


                } else if (Pattern.compile(".broadc.all").matcher(isi_pesan).find()) {
                    // TODO: broadcast to all

                    SendMessage pesanBalik = new SendMessage().setChatId(chat_id).setText("");
                    try { execute(pesanBalik); } catch (TelegramApiException e) { e.printStackTrace(); log.error(e.getMessage()); }
//            } else if (isi_pesan.length() > 3) {
                }

            } else if (!is_OneWord(isi_pesan)) {
                //                String tigaHurufPertama = isi_pesan.substring(0, Math.min(isi_pesan.length(), 4));
                String[] infoKiriman = isi_pesan.split(" ");
                String perintah_pesan = infoKiriman[0];
                String inti_pesan = infoKiriman[1];
                int banyakPesannya = infoKiriman.length;
//                System.out.println("isi array = " + banyakPesannya);
                String pesan_tambahan ="";
                if (banyakPesannya==3) pesan_tambahan = infoKiriman[2];
//                System.out.println("pesan tambahan: " + pesan_tambahan);
//                System.out.println("Perintah Pesan = " + perintah_pesan + " - " + inti_pesan);

                // ⏹✅ ️ -- SETORAN BARU
                if (perintah_pesan.equals(".new")  && isOnGrup) {
                    log.info(first_name + " " + last_name + " (" + username + ") "
                            + " is on " + isi_pesan);
                    if (inti_pesan.equals(null)) {
                        SendMessage pesanBalik = new SendMessage().setChatId(chat_id)
                                .setText(EmojiParser.parseToUnicode(":no_entry: Harap sertakan info setoran."));
                        try { execute(pesanBalik); } catch (TelegramApiException e) { e.printStackTrace(); log.error(e.getMessage());}

                    } else if (inti_pesan.contains("-") && update.getMessage().getReplyToMessage()!=null) {

                        if (db_okupansi.santriTelahTerdaftarByID(user_id)) {
                            long id_audio_setoran = update.getMessage().getReplyToMessage().getMessageId();
                            System.out.println(id_audio_setoran);

                            message_date = update.getMessage().getDate();
                            OperasiTanggal date = new OperasiTanggal();

                            int[] infoSurohAyat = isiPesanDgAwalAkhirAyat(inti_pesan);
                            noSuroh = infoSurohAyat[0]; awalAyat = infoSurohAyat[1]; akhirAyat = infoSurohAyat[2];
                            namaSuroh = infoDukung.namaSuroh(noSuroh);

                            isiRespon = first_name + " " + last_ringkas + EmojiParser.parseToUnicode(" :eight_spoked_asterisk: ")
                                    + namaSuroh + " [" + noSuroh + "]:" + awalAyat + "-" + akhirAyat;

                            db_okupansi.createSetoranReguler(user_id, message_id, message_id, id_audio_setoran, message_date,
                                    Integer.parseInt(date.getTodayDate()), Integer.parseInt(date.getPresentMonth()), Integer.parseInt(date.getPresentYear()), noSuroh, awalAyat, akhirAyat);

                        } else {
                            isiRespon = ":warning: Santri belum/tidak lagi terdaftar di database RekapMTQbot. Setoran tidak direkap dan aktivitas santri tidak masuk monitoring sistem.";
                        }
                        kirimkanPesan(isiRespon, chat_id);
                    }
                    // ⏹✅ -- SETORAN REVISI
                } else if (perintah_pesan.equals(".rev")  && isOnGrup) {
                    log.info(first_name + " " + last_name + " (" + username + ") "
                            + " is on " + isi_pesan);

                    if (inti_pesan.equals(null)) {
                        SendMessage pesanBalik = new SendMessage().setChatId(chat_id)
                                .setText(EmojiParser.parseToUnicode(":no_entry: Harap sertakan info setoran."));
                        try { execute(pesanBalik); } catch (TelegramApiException e) { e.printStackTrace(); log.error(e.getMessage());}

                    } else if (inti_pesan.contains("-") && update.getMessage().getReplyToMessage()!=null) {

                        if (db_okupansi.santriTelahTerdaftarByID(user_id)) {
                            long id_audio_setoran = update.getMessage().getReplyToMessage().getMessageId();
                            System.out.println(id_audio_setoran);

                            message_date = update.getMessage().getDate();
                            OperasiTanggal date = new OperasiTanggal();

                            int[] infoSurohAyat = isiPesanDgAwalAkhirAyat(inti_pesan);
                            noSuroh = infoSurohAyat[0]; awalAyat = infoSurohAyat[1]; akhirAyat = infoSurohAyat[2];
                            namaSuroh = infoDukung.namaSuroh(noSuroh);

                            isiRespon = first_name + " " + last_ringkas + EmojiParser.parseToUnicode(" :recycle: ")
                                    + namaSuroh + " [" + noSuroh + "]:" + awalAyat + "-" + akhirAyat;

                            db_okupansi.createSetoranReguler(user_id, message_id, message_id, id_audio_setoran, message_date,
                                    Integer.parseInt(date.getTodayDate()), Integer.parseInt(date.getPresentMonth()), Integer.parseInt(date.getPresentYear()), noSuroh, awalAyat, akhirAyat);

                        } else {
                            isiRespon = ":warning: Santri belum/tidak lagi terdaftar di database RekapMTQbot. Setoran tidak direkap dan aktivitas santri tidak masuk monitoring sistem.";
                        }
                        kirimkanPesan(isiRespon, chat_id);
                    }



                    // ⏹️✅ -- SETORAN MRJ
                } else if (perintah_pesan.equals(".mrj")  && isOnGrup) {
                    log.info(first_name + " " + last_name + " (" + username + ") "
                            + " is on " + isi_pesan);
                    int intHalMRJ = Integer.parseInt(inti_pesan);
                    db_okupansi.mrjSetoran(user_id, intHalMRJ);
                    isiRespon = first_name + " " + last_ringkas + EmojiParser.parseToUnicode(" :m: ")
                            + "Hal." + intHalMRJ;
                    kirimkanPesan(isiRespon, chat_id);


                    // 🔲 ----- FUNGSI ADMIN ----- 🔲

                    // ▶️✅ [ADMIN] -- MENDAFTARKAN KELAS BARU
                } else if (perintah_pesan.equals(".register") || perintah_pesan.equals("/register")) {
                    log.info(first_name + " " + last_name + " (" + username + ") "
                            + " is on " + isi_pesan);
                    if (adalahAdmin(user_id)) {
                        if (inti_pesan.length()==4) {
                            db_info_kelas.registerKelas(inti_pesan, chat_id);
                            isiRespon = EmojiParser.parseToUnicode(":new: Grup ini berhasil teregistrasi sebagai kelas ") + indahkanNamaKelas(inti_pesan);
                        } else {
                            isiRespon = EmojiParser.parseToUnicode(":exclamation: Format input salah. Digit input kurang.");
                        }
                    } else {
                        isiRespon = EmojiParser.parseToUnicode(":information_source:")
                                + " Perintah hanya bisa dijalankan oleh Admin MTQ.";
                    }
                    kirimkanPesan(isiRespon, chat_id);

                    // ▶️✅ [ADMIN] -- ENABLE KELAS
                } else if (perintah_pesan.equals(".enable") || perintah_pesan.equals("/enable")) {
                    log.info(first_name + " " + last_name + " (" + username + ") "
                            + " is on " + isi_pesan);
                    if (adalahAdmin(user_id)) {
                        db_info_kelas.enableKelas(inti_pesan);
                        isiRespon = EmojiParser.parseToUnicode(":arrow_forward:")
                                + " Kelas " + indahkanNamaKelas(inti_pesan) + " dinyatakan aktif";
                    } else {
                        isiRespon = EmojiParser.parseToUnicode(":information_source:")
                                + " Perintah hanya bisa dijalankan oleh Admin MTQ.";
                    }
                    kirimkanPesan(isiRespon, chat_id);

                    // ▶️✅ [ADMIN] -- DISABLE KELAS
                } else if (perintah_pesan.equals(".disable") || perintah_pesan.equals("/disable")) {
                    log.info(first_name + " " + last_name + " (" + username + ") "
                            + " is on " + isi_pesan);
                    if (adalahAdmin(user_id)) {
                        db_info_kelas.disableKelas(inti_pesan);
                        isiRespon = EmojiParser.parseToUnicode(":double_vertical_bar:")
                                + " Kelas " + indahkanNamaKelas(inti_pesan) + " dinyatakan NON aktif";
                    } else {
                        isiRespon = EmojiParser.parseToUnicode(":information_source:")
                                + " Perintah hanya bisa dijalankan oleh Admin MTQ.";
                    }
                    kirimkanPesan(isiRespon, chat_id);

                    // ▶️✅ [ADMIN] -- MENGHAPUS KELAS
                } else if (perintah_pesan.equals(".DELETE") || perintah_pesan.equals("/DELETE")) {
                    log.info(first_name + " " + last_name + " (" + username + ") "
                            + " is on " + isi_pesan);
                    if (adalahAdmin(user_id)) {
                        db_info_kelas.deleteKelasTerpilih(inti_pesan);
                        isiRespon = EmojiParser.parseToUnicode(":broken_heart:")
                                + " Kelas " + indahkanNamaKelas(inti_pesan) + " telah dihapus dari database";
                    } else {
                        isiRespon = EmojiParser.parseToUnicode(":information_source:")
                                + " Perintah hanya bisa dijalankan oleh Admin MTQ.";
                    }
                    kirimkanPesan(isiRespon, chat_id);

                }

                // ▶️✅ [SANTRI] -- MENGIRIM INFO DIRI DI KELAS
                else if (perintah_pesan.equals(".ana") || perintah_pesan.equals("/ana")) {
                    log.info(first_name + " " + last_name + " (" + username + ") "
                            + " is on " + isi_pesan);

                    if (username.equals(null)) {
                        isiRespon = ":warning: Registrasi tidak dapat diproses. Harap info username telegram diisi terlebih dahulu.";
                    } else {
                        String[] pecahan = isi_pesan.split(",");
                        String kunyah = pecahan[0];
                        kunyah = kunyah.replace(".ana ", "");
                        String setoranTerakhir = pecahan[1].trim();
                        String noHP = pecahan[2].trim();
                        String gender = pecahan[3].trim().toLowerCase();
                        String tahunLahir = pecahan[4].trim();

                        System.out.println("kunyah = " + kunyah);
                        System.out.println("setoranTerakhir" + setoranTerakhir);
                        System.out.println("noHP = " + noHP);
                        System.out.println("gender = " + gender);
                        System.out.println("tahunLahir = " + tahunLahir);

//                    char genderChar = gender.charAt(0);
                        int intTahunLahir = Integer.parseInt(tahunLahir);
//                    if (kunyah.contains("_")) {
//                        String[] namaKunyah = kunyah.split("_");
//                        kunyah = namaKunyah[0] + " " + namaKunyah[1];
//                    }

                        db_santri_reguler.regSbgSantri(user_id, username, first_name + " " + last_ringkas, kunyah, setoranTerakhir, noHP, gender, intTahunLahir, 0, 0, 0, 0, chat_id);
                        isiRespon = "Info peserta `" + kunyah + "` berhasil didaftarkan di bot rekap MTQ.";

                    }
                    kirimkanPesanMarkdown(isiRespon, chat_id);

                }


                    // ▶️✅️ [ADMIN] -- MENDAFTARKAN SATU ORANG SANTRI KE KELAS
                else if (perintah_pesan.equals(".addsatuan") || perintah_pesan.equals("/addsatuan")) {
                    log.info(first_name + " " + last_name + " (" + username + ") "
                            + " is on " + isi_pesan);

                    if (adalahAdmin(user_id)) {


                        db_okupansi.insertSantriToKelas(inti_pesan, pesan_tambahan);
//                        isiRespon = "Santri @" + pesan_tambahan + " telah didaftarkan menempati slot kelas di " + inti_pesan.substring(0,3);;
                    } else {
                        isiRespon = EmojiParser.parseToUnicode(":information_source:")
                                + " Perintah hanya bisa dijalankan oleh Admin MTQ.";
                        kirimkanPesan(isiRespon, chat_id);
                    }


                    // ▶️✅ [ADMIN] -- MENDAFTARKAN SEPULUH SANTRI KE KELAS
                } else if (perintah_pesan.equals(".addall") || perintah_pesan.equals("/addall")) {
                    log.info(first_name + " " + last_name + " (" + username + ") "
                            + " is on " + isi_pesan);
                    if (adalahAdmin(user_id)) {
                        db_okupansi.insertBulkSantriToKelas(inti_pesan, pesan_tambahan);
                        isiRespon = EmojiParser.parseToUnicode(":eight_pointed_black_star:") +
                                "Santri-santri telah" +
                                " didaftarkan menempati slot kelas di " + indahkanNamaKelas(inti_pesan);
                    } else {
                        isiRespon = EmojiParser.parseToUnicode(":information_source:")
                                + " Perintah hanya bisa dijalankan oleh Admin MTQ.";
                    }
                    kirimkanPesan(isiRespon, chat_id);

                    // ▶️✅ [ADMIN] -- MENGHAPUS SATU SANTRI DARI KELAS
                } else if (perintah_pesan.equals(".remove") || perintah_pesan.equals("/remove")) {
                    log.info(first_name + " " + last_name + " (" + username + ") "
                            + " is on " + isi_pesan);
                    if (adalahAdmin(user_id)) {
                        db_okupansi.removeSantriFromKelas(inti_pesan, chat_id);
                    } else {
                        isiRespon = EmojiParser.parseToUnicode(":information_source:")
                                + " Perintah hanya bisa dijalankan oleh Admin MTQ.";
                        kirimkanPesan(isiRespon, chat_id);
                    }

                } else if (perintah_pesan.equals(".removebyid") || perintah_pesan.equals("/removebyid")) {
                    log.info(first_name + " " + last_name + " (" + username + ") "
                            + " is on " + isi_pesan);
                    if (adalahAdmin(user_id)) {
                        db_okupansi.removeSantriFromKelasByID(inti_pesan, chat_id);
                    } else {
                        isiRespon = EmojiParser.parseToUnicode(":information_source:")
                                + " Perintah hanya bisa dijalankan oleh Admin MTQ.";
                        kirimkanPesan(isiRespon, chat_id);
                    }


                    // ▶️✅ [ADMIN] -- LIST OKUPANSI KELAS TERPILIH
                } else if (perintah_pesan.equals(".okupansi") || perintah_pesan.equals("/okupansi")) {
                    log.info(first_name + " " + last_name + " (" + username + ") "
                            + " is on " + isi_pesan);
                    if (adalahAdmin(user_id)) {
                        isiRespon = db_okupansi.listingSlotKelasOkupansi(inti_pesan);;
                    } else {
                        isiRespon = EmojiParser.parseToUnicode(":information_source:")
                                + " Perintah hanya bisa dijalankan oleh Admin MTQ.";
                    }
                    kirimkanPesan(isiRespon, chat_id);

                    // ▶️✅ [ADMIN] -- LIST OKUPANSI KELAS TERPILIH
                } else if (perintah_pesan.equals(".listid") || perintah_pesan.equals("/listid")) {
                    log.info(first_name + " " + last_name + " (" + username + ") "
                            + " is on " + isi_pesan);
                    if (adalahAdmin(user_id)) {
                        isiRespon = db_okupansi.listingIDokupansi(inti_pesan);;
                    } else {
                        isiRespon = EmojiParser.parseToUnicode(":information_source:")
                                + " Perintah hanya bisa dijalankan oleh Admin MTQ.";
                    }
                    kirimkanPesan(isiRespon, chat_id);


                    // ▶️✅ [ADMIN] -- KIRIM REKAP SETORAN KELAS TERPILIH
                } else if (perintah_pesan.equals(".rekapsetoran") || perintah_pesan.equals("/rekapsetoran")) {
                    log.info(first_name + " " + last_name + " (" + username + ") "
                            + " is on " + isi_pesan);
                    if (adalahAdmin(user_id)) {
                        db_okupansi.generateRekapHarianPerKelas(inti_pesan);
                    } else {
                        isiRespon = EmojiParser.parseToUnicode(":information_source:")
                                + " Perintah hanya bisa dijalankan oleh Admin MTQ.";
                        kirimkanPesan(isiRespon, chat_id);
                    }


                    // ▶️✅ [ADMIN] -- KIRIM REKAP MUROJA'AH KELAS TERPILIH
                } else if (perintah_pesan.equals(".mrjharian") || perintah_pesan.equals("/mrjharian")) {
                    log.info(first_name + " " + last_name + " (" + username + ") "
                            + " is on " + isi_pesan);
                    if (adalahAdmin(user_id)) {
                        db_okupansi.generateDailyMRJreport(inti_pesan);
                    } else {
                        isiRespon = EmojiParser.parseToUnicode(":information_source:")
                                + " Perintah hanya bisa dijalankan oleh Admin MTQ.";
                        kirimkanPesan(isiRespon, chat_id);
                    }

                    // ▶️✅ [ADMIN] -- KIRIM REKAP MUROJA'AH AKHIR PEKAN DARI KELAS TERPILIH
                } else if (perintah_pesan.equals(".mrjap")) {
                    log.info(first_name + " " + last_name + " (" + username + ") "
                            + " is on " + isi_pesan);
                    if (adalahAdmin(user_id)) {
                        db_okupansi.generateMRJakhirPekanReport(inti_pesan);
                    } else {
                        isiRespon = EmojiParser.parseToUnicode(":information_source:")
                                + " Perintah hanya bisa dijalankan oleh Admin MTQ.";
                        kirimkanPesan(isiRespon, chat_id);
                    }

                    // ▶️✅ [ADMIN] -- SET VALUE GHOIB SANTRI
                } else if (perintah_pesan.equals(".setghoib") || perintah_pesan.equals("/setghoib")) {
                    log.info(first_name + " " + last_name + " (" + username + ") "
                            + " is on " + isi_pesan);
                    if (adalahAdmin(user_id)) {
                        db_okupansi.setValueGhoibSantri(inti_pesan, Integer.parseInt(pesan_tambahan));
                        isiRespon = "Value ghoib untuk santri ybs telah diubah menjadi " + pesan_tambahan;;
                    } else {
                        isiRespon = EmojiParser.parseToUnicode(":information_source:")
                                + " Perintah hanya bisa dijalankan oleh Admin MTQ.";
                    }
                    kirimkanPesan(isiRespon, chat_id);

                    // ▶️✅ [ADMIN] -- SET VALUE UDZUR SANTRI
                } else if (perintah_pesan.equals(".setudzur") || perintah_pesan.equals("/setudzur")) {
                    log.info(first_name + " " + last_name + " (" + username + ") "
                            + " is on " + isi_pesan);
                    if (adalahAdmin(user_id)) {
                        db_okupansi.setValueUdzurSantri(inti_pesan, Integer.parseInt(pesan_tambahan));
                        isiRespon = "Value udzur untuk santri ybs telah diubah menjadi " + pesan_tambahan;;
                    } else {
                        isiRespon = EmojiParser.parseToUnicode(":information_source:")
                                + " Perintah hanya bisa dijalankan oleh Admin MTQ.";
                    }
                    kirimkanPesan(isiRespon, chat_id);

                    // TODO: broadcast ke kelas tertentu
                } else if (perintah_pesan.equals(".rep") || perintah_pesan.equals("/rep")) {

                    String isiPesanReply = update.getMessage().getReplyToMessage().getText();
                    long chat_id_sasaran = -239673816;

                    isiRespon = isiPesanReply;
                    SendMessage pesanBalik = new SendMessage().setChatId(chat_id_sasaran).setText(isiRespon);
                    try { execute(pesanBalik); } catch (TelegramApiException e) { e.printStackTrace(); log.error(e.getMessage()); }

                }
            }




                /*else {
                    try {
                        SendMessage message = new SendMessage() // Create a message object object
                                .setChatId(id_chat)
                                .setText("Perintah tak dimengerti");
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace(); log.error(e.getMessage());
                    }
                }*/


            /*else {
                try {
                    SendMessage message = new SendMessage() // Create a message object object
                            .setChatId(id_chat)
                            .setText("Perintah tak dikenali");
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace(); log.error(e.getMessage());
                }
            }*/


        }
    }

    public void kirimkanPesan(String isiPesan, long chat_id) {
        SendMessage pesanBalik = new SendMessage()
//                .enableMarkdown(true)
                .setChatId(chat_id)
                .setText(EmojiParser.parseToUnicode(isiPesan));

        try {
            execute(pesanBalik);
        } catch (TelegramApiException e) {
            e.printStackTrace(); log.error(e.getMessage());
        }
    }

    public void kirimkanPesanMarkdown(String isiPesan, long chat_id) {
        SendMessage pesanBalik = new SendMessage()
                .enableMarkdown(true)
                .setChatId(chat_id)
                .setText(isiPesan);
        try {
            execute(pesanBalik);
        } catch (TelegramApiException e) {
            e.printStackTrace(); log.error(e.getMessage());
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

    public void kirimTakTerdaftar(String isiPesan) {
//        SendMessage pesanBalik = new SendMessage().setChatId(id_chat).setText("Rekap MRJ terkirim");
//        try { execute(pesanBalik); } catch (TelegramApiException e) { e.printStackTrace(); log.error(e.getMessage()); }
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
            e.printStackTrace(); log.error(e.getMessage());
        }
    }

    public boolean merupakanAdmin(String username) {
        boolean apaAdmin = false;
        String[] namaPengurus = {"zakkiy","ArdynataMTQ", "abuazamMTQ", "GunturPerwiraNegara", "Abu_Asadulloh", "guntar"};

        for (int i = 0; i < namaPengurus.length; i++ ) {
            if (username.equals(namaPengurus[i])) apaAdmin = true;
        }
        return apaAdmin;
    }

    public boolean adalahAdmin(long user_id) {
        boolean apaAdmin = false;
        // 45944551 guntur
        // 405577050 zakkiy
        // 51644018 85076067 guntar
        long[] idPengurus = {85076067, 405577050, 45944551, 51644018};

        for (int i = 0; i < idPengurus.length; i++ ) {
            if (user_id==idPengurus[i]) apaAdmin = true;
        }
        return apaAdmin;
    }



    public void kirimNotifikasiAdaYgUdzurKelewatan(String isiPesan) {

//        long chat_id_target = -1001040612991L; // alamat sekretariat
        long chat_id_target = -239673816; // TesBot_01

        try {
            SendMessage message = new SendMessage() // Create a message object object
                    .setChatId(chat_id_target)
                    .setText(EmojiParser.parseToUnicode(isiPesan))  ;
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace(); log.error(e.getMessage());
        }

    }


}
