package com.tahfizhonline.entityoperation;


import com.tahfizhonline.MTQBotRekap;
import com.tahfizhonline.entitydefinition.*;
import com.tahfizhonline.fungsidukung.OperasiTanggal;
import com.vdurmont.emoji.EmojiParser;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.joda.time.DateTime;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OperasiOkupansi {

    private static org.apache.log4j.Logger log = Logger.getLogger(OperasiOkupansi.class);



    //update udzur



    public void setSimakSetoran(long userID) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(EntityOkupansi.class)
                .addAnnotatedClass(EntitySetoran.class)
                .addAnnotatedClass(EntityInfoKelas.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            Query query = session.createNativeQuery("UPDATE okupansi SET today_simak=1 WHERE user_id='" + userID +"'");
            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); log.error(e.getMessage());
        } finally {
            session.close(); factory.close();
        }
    }



    public void setUdzurSakit(long user_id) {
        OperasiTanggal operasiTanggal = new OperasiTanggal();
        String kapanSajaUdzur = "";
        int berapaKaliUdzur = 0;


        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(EntityOkupansi.class)
                .addAnnotatedClass(EntitySetoran.class)
                .addAnnotatedClass(EntityInfoKelas.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            String query = "SELECT udzur_kapan, udzur_frek FROM okupansi WHERE user_id="+ user_id;
            List<Object[]> hasilQuery = session.createNativeQuery(query).list();
            for (Object[] hasil : hasilQuery) {
                berapaKaliUdzur = Integer.parseInt(hasil[1].toString());
                kapanSajaUdzur = hasil[0].toString();
            }

            StringBuilder sb = new StringBuilder(kapanSajaUdzur);
            sb.append(" " + operasiTanggal.getTodayDate());
            berapaKaliUdzur = berapaKaliUdzur + 1;
            kapanSajaUdzur = sb.toString();

            session.createNativeQuery("UPDATE okupansi SET today_setoran=10, udzur_frek=" + berapaKaliUdzur + ", udzur_kapan='"+ kapanSajaUdzur + "' WHERE user_id=" + user_id).executeUpdate();

        } catch (Exception e) {
            e.printStackTrace(); log.error(e.getMessage());
        } finally {
            session.close();
            factory.close();
        }
    }

    public void setPoinGhoib(long user_id, int jenisGhoib) {
        OperasiTanggal operasiTanggal = new OperasiTanggal();
        String kapanSajaGhoib = "";
        float berapaKaliGhoib = 0;

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(EntityOkupansi.class)
                .addAnnotatedClass(EntitySetoran.class)
                .addAnnotatedClass(EntityInfoKelas.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            String query = "SELECT ghoib_kapan, ghoib_frek FROM okupansi WHERE user_id="+ user_id;
            List<Object[]> hasilQuery = session.createNativeQuery(query).list();
            for (Object[] hasil : hasilQuery) {
                berapaKaliGhoib = Integer.parseInt(hasil[1].toString());
                kapanSajaGhoib = hasil[0].toString();
            }

            StringBuilder sb = new StringBuilder(kapanSajaGhoib);
            sb.append(" " + operasiTanggal.getTodayDate());
            kapanSajaGhoib = sb.toString();

            if (jenisGhoib==1) {
                berapaKaliGhoib = berapaKaliGhoib + 1;
            } else {
                berapaKaliGhoib = berapaKaliGhoib + 0.5f;
            }
            session.createNativeQuery("UPDATE okupansi SET today_setoran=0, ghoib_frek=" + berapaKaliGhoib
                    + ", ghoib_kapan='"+ kapanSajaGhoib + "' WHERE user_id=" + user_id).executeUpdate();

        } catch (Exception e) {
            e.printStackTrace(); log.error(e.getMessage());
        } finally {
            session.close();
            factory.close();
        }
    }



    public void insertSetoranHarian(long userId, long pesanId, long tglPesan, String username, String firstLastName, int noSuroh, int awalAyat, int akhirAyat) {
        /*OperasiTanggal tanggal = new OperasiTanggal();

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(EntityOkupansi.class)
                .addAnnotatedClass(EntitySetoran.class)
                .addAnnotatedClass(EntityInfoKelas.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();

            // Cek dulu setoran yg sudah ada

//            String sql = "SELECT id_db_okupansi, today_setoran FROM okupansi WHERE user_id=:userId";
            String sql = "SELECT id_db_okupansi, today_setoran FROM okupansi WHERE user_id='" + userId + "'";
            NativeQuery query = session.createNativeQuery(sql);
            *//*query.addEntity(EntityOkupansi.class);
            query.setParameter("userId", userId);
            List results = query.list();
            System.out.println(query.list().toArray().toString());
            String id_db_untukOkupansi = "";
            for (Object object : results) {
                Map row = (Map)object;
                id_db_untukOkupansi = row.get("id_db_okupansi").toString();
                System.out.println("id_db_okupansi: " + row.get("id_db_okupansi"));
                System.out.println("today_setoran: " + row.get("today_setoran"));
            }*//*


            Query qSetoranToday = session.createNativeQuery("SELECT today_setoran, udzur_frek FROM okupansi WHERE user_id='"+ userId + "'");
            qSetoranToday.executeUpdate();
            qSetoranToday.getResultList();
            System.out.println("\n---" + qSetoranToday.getResultList());


            Query query = session.createNativeQuery("UPDATE okupansi SET today_setoran=" + berapaKaliSetorToday + \" WHERE user_id='" + userId +"'");
            query.executeUpdate();

            int theId = Integer.parseInt("123456");
            EntityOkupansi santriOkupansi = session.get(EntityOkupansi.class, theId);
            //create setoran
            EntitySetoran setoranSantri = new EntitySetoran(userId, firstLastName, pesanId, pesanId-2, tglPesan,
                    tanggal.getTodayDate(), tanggal.getPresentMonth(), tanggal.getPresentYear(), null,
            noSuroh, awalAyat, akhirAyat, 0, 0);

            // add setoran to santri
            santriOkupansi.addSetoranOkupansi(setoranSantri);
            // save the session
            session.getTransaction().commit();

        } catch (HibernateException e) {
//            if (session.beginTransaction()!=null) session.beginTransaction().rollback();
            e.printStackTrace(); log.error(e.getMessage());
        } finally {
            session.close(); factory.close();
        }*/

    }


    public void createSetoranReguler(long user_id, long id_pesan_bot, long id_pesan_user, long id_audio_setoran, long tanggalPesan, int tanggalMasehi, int bulanMasehi, int tahunMasehi, int nomerSuroh, int ayatBegin, int ayatEnd) {
        int berapaKaliSetorToday = 0;
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(EntityInfoKelas.class)
                .addAnnotatedClass(EntityOkupansi.class)
                .addAnnotatedClass(EntitySetoran.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();

            String queryUserID = "SELECT today_setoran FROM okupansi WHERE user_id=" + user_id;
            List<Byte> hasilQuserID = session.createNativeQuery(queryUserID).list();
            berapaKaliSetorToday = Integer.parseInt(hasilQuserID.get(0).toString());
            berapaKaliSetorToday++;

            session.createNativeQuery("UPDATE okupansi SET today_setoran=" + berapaKaliSetorToday
                    + ", mrjap_suroh_end=" + nomerSuroh +  ", mrj_ap_ayat_end=" + ayatEnd +
                    "  WHERE user_id='" + user_id + "'").executeUpdate();

            if (nomerSuroh==77 && ayatBegin==1) {
                session.createNativeQuery("UPDATE okupansi SET is_wajib_mrjharian=1 WHERE user_id='" + user_id + "'").executeUpdate();
            }

//            session.createNativeQuery("UPDATE okupansi SET mrjap_suroh_end=" + nomerSuroh + " WHERE user_id='" + user_id + "'").executeUpdate();
//            session.createNativeQuery("UPDATE okupansi SET mrj_ap_ayat_end=" + ayatEnd + " WHERE user_id='" + user_id + "'").executeUpdate();

            EntityOkupansi santriOkupansi = session.get(EntityOkupansi.class, getOkupansiDatabaseID(user_id));
            EntitySetoran setoran = new EntitySetoran (user_id, getKunyahSantriFromID(user_id), id_pesan_bot, id_pesan_user, id_audio_setoran, tanggalPesan,
                    tanggalMasehi, bulanMasehi, tahunMasehi, nomerSuroh, ayatBegin, ayatEnd, 0, 0);

            santriOkupansi.addSetoranOkupansi(setoran);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace(); log.error(e.getMessage());
        } finally {
            session.close();
            factory.close();
        }

    }


    public void resetValueSetoran() {

    }

    public void resetValueMRJ() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(EntityOkupansi.class)
                .addAnnotatedClass(EntitySetoran.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            session.createNativeQuery("UPDATE okupansi SET today_mrjharian=0 WHERE is_terisi=1").executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); log.error(e.getMessage());
        } finally {
            session.close(); factory.close();
        }
    }

    public void resetPresensiBulanan() {
    }


    public void mrjSetoran(long userID, int noHalaman) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(EntityOkupansi.class)
                .addAnnotatedClass(EntitySetoran.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            session.createNativeQuery("UPDATE okupansi SET today_mrjharian=1, mrj_halaman=" + noHalaman + " WHERE user_id=" + userID).executeUpdate();
//            Query queryHalaman = session.createNativeQuery("UPDATE okupansi SET mrj_halaman=" + noHalaman + " WHERE user_id='" + userID + "'");
//            queryHalaman.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace(); log.error(e.getMessage());
        } finally {
            session.close(); factory.close();
        }
    }

    public void insertSantriToKelas(String id_okupansi, String userName) {
        String indikasiGender = id_okupansi.substring(4,5);
        String kategoriGender = "";
        if (indikasiGender.equals("1")) {
            kategoriGender = "i";
        } else {
            kategoriGender = "a";
        }
        String namaKelasKirim = id_okupansi.substring(0,3) + kategoriGender;
        long alamatGrup = getAlamatKelas(namaKelasKirim);
        MTQBotRekap mtqBotRekap = new MTQBotRekap();
        String isiPesan = "";
        int infoTerisi = 1;
        boolean booLanjutProses = true;

//        System.out.println("Apakah terisi = " + apakahSlotTelahTerisi(id_okupansi));

        if (apakahSlotTelahTerisi(id_okupansi)) {

            String[] infoSantriReguler = getInfoUntukRegistrasiOkupansi(userName);

            if (!infoSantriReguler.equals("0")) {
                booLanjutProses = true;
            } else booLanjutProses = false;

            if (userName.equals("?")) booLanjutProses = true;

            if (booLanjutProses) {
                long user_id = 0L;
                String kunyahSantri = "";
                int surohTerakhir = 0;
                int ayatTerakhir = 0;

                if (userName.equals("?")) {
                    user_id = 0L;
                    userName = "(EMPTY)";
                    infoTerisi = 0;
                    isiPesan = ":keycap_asterisk: Slot " + id_okupansi + " sengaja di-set kosong.";
                } else {
                    user_id = Long.parseLong(infoSantriReguler[0]);
                    kunyahSantri = infoSantriReguler[1];
                    surohTerakhir = Integer.parseInt(infoSantriReguler[2]);
                    ayatTerakhir = Integer.parseInt(infoSantriReguler[3]);

                    isiPesan = ":ok: Santri " + kunyahSantri + " @" + userName + " sukses didaftarkan di slot kelas " + id_okupansi.substring(0,3);
                }

                SessionFactory factory = new Configuration()
                        .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(EntityOkupansi.class)
                        .addAnnotatedClass(EntityInfoKelas.class)
                        .addAnnotatedClass(EntitySetoran.class)
                        .buildSessionFactory();
                Session session = factory.getCurrentSession();
                try {
                    session.beginTransaction();
                    session.createNativeQuery("UPDATE okupansi SET username_okupan='" + userName + "', is_terisi=" + infoTerisi + ", nama_santri='" + kunyahSantri + "', user_id=" + user_id + ", ghoib_frek=0, ghoib_kapan='', udzur_frek=0, udzur_kapan='', is_imtihan=0, today_setoran=0, today_mrjharian=0, today_simak=0, status_setoran=0, p_no_suroh=0, p_ayat_begin=0, p_ayat_end=0, mrjap_suroh_begin=" + surohTerakhir + ", mrjap_suroh_end=114, mrj_ap_ayat_begin=1, mrj_ap_ayat_end=" + ayatTerakhir + ", mrjap_setor=0, mrjap_simak=0, is_wajib_mrjharian=0, mrjharian_page=0 WHERE id_okupansi='" + id_okupansi + "'").executeUpdate();

                } catch (Exception e) {
                    e.printStackTrace(); log.error(e.getMessage());
                } finally {
                    session.close(); factory.close();
                }
            } else {
                isiPesan = ":bangbang: Santri @" + userName + " belum terdaftar di database RekapMTQbot. Harap yang bersangkutan lakukan pendaftaran terlebih dahulu.";
            }
        } else { //-- bila slot masih terisi
            isiPesan = ":bangbang: Slot " + id_okupansi + " masih terisi, santri " + userName + " tidak bisa dimasukkan ke slot yang bersangkutan.";
        }
        mtqBotRekap.kirimkanPesan(isiPesan, alamatGrup);
    }

    public void insertBulkSantriToKelas(String namaKelas, String daftarSantri) {
        String[] daftarNama = daftarSantri.split(",");
        String[] daftarIDokupansi = new String[10];

        String indikasiNamaKelas =  namaKelas.substring(0,2);
        String indikasiKategori = namaKelas.substring(2,3);
        String indikasiGender = namaKelas.substring(3,4);

        String kategoriKelas = "";
        if (indikasiKategori.contains("a")|| indikasiKategori.contains("A") ) {
            kategoriKelas = "A";
        }
        if (indikasiKategori.contains("b") || indikasiKategori.contains("B") ) {
            kategoriKelas = "B";
        }

        String kategoriGender = "";
        if (indikasiGender.contains("i") || indikasiGender.contains("I") ) {
            kategoriGender = "1";
        }
        if (indikasiGender.contains("a") || indikasiGender.contains("A") ) {
            kategoriGender = "2";
        }

        // buat id okupansinya
        daftarIDokupansi[0] = indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-" + "01";
        daftarIDokupansi[1] = indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-" + "02";
        daftarIDokupansi[2] = indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-" + "03";
        daftarIDokupansi[3] = indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-" + "04";
        daftarIDokupansi[4] = indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-" + "05";
        daftarIDokupansi[5] = indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-" + "06";
        daftarIDokupansi[6] = indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-" + "07";
        daftarIDokupansi[7] = indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-" + "08";
        daftarIDokupansi[8] = indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-" + "09";
        daftarIDokupansi[9] = indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-" + "10";

        // lakukan pengisian
        for (int i = 0; i < 10; i++) {
            insertSantriToKelas(daftarIDokupansi[i], daftarNama[i]);
            System.out.println(daftarIDokupansi[i] + " - " + daftarNama[i]);
        }
    }

    public void removeSantriFromKelas(String username, long alamatTempatKirim) {
        MTQBotRekap mtqBotRekap = new MTQBotRekap();
        String isiPesan = "";
        long userTelegramID = getTelegramUserID(username);

        if (userTelegramID==0) {
            isiPesan = "Username tidak terdaftar. Mungkin telah berganti username. Silahkan temukan " +
                    "user_id yang bersangkutan dengan command .listuserid";
        } else {
            SessionFactory factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(EntityOkupansi.class)
                    .addAnnotatedClass(EntityInfoKelas.class)
                    .addAnnotatedClass(EntitySetoran.class)
                    .buildSessionFactory();
            Session session = factory.getCurrentSession();
            try {
                session.beginTransaction();
                session.createNativeQuery("UPDATE okupansi SET is_terisi=0, username_okupan='(EMPTY)', nama_santri='' WHERE user_id=" + userTelegramID + "").executeUpdate();
                isiPesan = EmojiParser.parseToUnicode(":broken_heart:") +
                        "Santri @" + username + " dengan id " + userTelegramID + " telah" +
                        " diremove dari slot kelasnya.";
            } catch (Exception e) {
                e.printStackTrace(); log.error(e.getMessage());
            } finally {
                session.close(); factory.close();
            }
        }
        mtqBotRekap.kirimkanPesan(isiPesan, alamatTempatKirim);
    }

    public void removeSantriFromKelasByID(String id_santri, long chat_id) {
        long IDsantri = Long.parseLong(id_santri);

        MTQBotRekap mtqBotRekap = new MTQBotRekap();
        String isiPesan = "";

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(EntityOkupansi.class)
                .addAnnotatedClass(EntityInfoKelas.class)
                .addAnnotatedClass(EntitySetoran.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            session.createNativeQuery("UPDATE okupansi SET is_terisi=0 WHERE user_id=" + IDsantri + "").executeUpdate();
            isiPesan = EmojiParser.parseToUnicode(":broken_heart:") +
                    "Santri dengan id " + IDsantri + " telah" +
                    " diremove dari slot kelasnya.";
        } catch (Exception e) {
            e.printStackTrace(); log.error(e.getMessage());
        } finally {
            session.close(); factory.close();
        }
        mtqBotRekap.kirimkanPesan(isiPesan, chat_id);
    }


    public int getUserDatabaseID(String userName) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(EntitySantriReguler.class)
                .addAnnotatedClass(EntitySantriRegulerStats.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        Integer id_db = 0;
        try {
            session.beginTransaction();
            String queryTeks = "SELECT id_db_santri FROM santrireguler WHERE username = '" + userName + "'";
            List<Integer> hasilnya = session.createNativeQuery(queryTeks).list();
            if (hasilnya.size()!=0) {
                id_db = hasilnya.get(0);
            } else {
                id_db = 0;
            }

        } catch (Exception e) {
            e.printStackTrace(); log.error(e.getMessage());
        } finally {
            session.close(); factory.close();
        }
//        System.out.println(id_db);
        return id_db;
    }

    public int getOkupansiDatabaseID(long user_id) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(EntityInfoKelas.class)
                .addAnnotatedClass(EntityOkupansi.class)
                .addAnnotatedClass(EntitySetoran.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        Integer id_db = 0;
        try {
            session.beginTransaction();
            String queryTeks = "SELECT id_db_okupansi FROM okupansi WHERE user_id=" + user_id;
            List<Integer> hasilnya = session.createNativeQuery(queryTeks).list();
            id_db = hasilnya.get(0);
        } catch (Exception e) {
            e.printStackTrace(); log.error(e.getMessage());
        } finally {
            session.close(); factory.close();
        }
//        System.out.println(id_db);
        return id_db;
    }


    // ☣️
    public String[] getInfoUntukRegistrasiOkupansi(String userName) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(EntitySantriReguler.class)
                .addAnnotatedClass(EntitySantriRegulerStats.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        String stUserIDtelegram = "0";
        String namaKunyah = "0";
        String surohTerakhir = "0";
        String ayatTerakhir = "0";

        try {
            session.beginTransaction();
            String queryUserID = "SELECT user_id, nama_kunyah  FROM santrireguler WHERE username = '" + userName + "'";
            List<Object[]> hasilQuery = session.createNativeQuery(queryUserID).list();
            if (hasilQuery.size()!=0) {
                for (Object[] hasil : hasilQuery) {
                    stUserIDtelegram = hasil[0].toString();
                    namaKunyah = hasil[1].toString();
                }
            }

            String queryUserSuroh = "SELECT user_id, nama_kunyah  FROM santrireguler WHERE user_id=" + stUserIDtelegram;
            List<Object[]> hasilQuerySuroh = session.createNativeQuery(queryUserSuroh).list();
            if (hasilQuerySuroh.size()!=0) {
                for (Object[] hasil : hasilQuerySuroh) {
                    surohTerakhir = hasil[0].toString();
                    ayatTerakhir = hasil[1].toString();
                }
            }

        } catch (Exception e) {
            e.printStackTrace(); log.error(e.getMessage());
        } finally {
            session.close(); factory.close();
        }

        String[] infoRegistrasiOkupansi = {stUserIDtelegram, namaKunyah, surohTerakhir, ayatTerakhir};

        return infoRegistrasiOkupansi;
    }

    public long getTelegramUserID(String userName) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(EntitySantriReguler.class)
                .addAnnotatedClass(EntitySantriRegulerStats.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        long userIDtelegram = 0L;
        try {
            session.beginTransaction();
            String queryUserID = "SELECT user_id FROM santrireguler WHERE username = '" + userName + "'";
            List<Integer> hasilQuserID = session.createNativeQuery(queryUserID).list();
            if (hasilQuserID.size()!=0) {
                userIDtelegram = hasilQuserID.get(0);
            } else {
                userIDtelegram = 0;
            }

        } catch (Exception e) {

            e.printStackTrace(); log.error(e.getMessage());
        } finally {
            session.close(); factory.close();
        }
        return userIDtelegram;
    }


    public String getKunyahSantriFromID(long user_id) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(EntitySantriReguler.class)
                .addAnnotatedClass(EntitySantriRegulerStats.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        String namaKunyah = "";
        try {
            session.beginTransaction();
            String queryUserID = "SELECT nama_kunyah FROM santrireguler WHERE user_id=" + user_id;
            List<String> hasilQuserID = session.createNativeQuery(queryUserID).list();
            namaKunyah = hasilQuserID.get(0).toString();
        } catch (Exception e) {
            e.printStackTrace(); log.error(e.getMessage());
        } finally {
            session.close(); factory.close();
        }
        return namaKunyah;
    }


    public boolean santriTelahTerdaftarByID(long user_id) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(EntitySantriReguler.class)
                .addAnnotatedClass(EntitySantriRegulerStats.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        boolean sudahTerdaftar = true;
        try {
            session.beginTransaction();
            String queryUserID = "SELECT id_db_santri FROM santrireguler WHERE user_id=" + user_id;
            List<Integer> hasilQuserID = session.createNativeQuery(queryUserID).list();
            if (hasilQuserID.size()==0) {
                sudahTerdaftar = false;
            }
        } catch (Exception e) {
            e.printStackTrace(); log.error(e.getMessage());
        } finally {
            session.close(); factory.close();
        }
        return sudahTerdaftar;
    }

    public boolean apakahSlotTelahTerisi(String id_okupansi) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(EntityInfoKelas.class)
                .addAnnotatedClass(EntityOkupansi.class)
                .addAnnotatedClass(EntitySetoran.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        boolean telahTerisi = true;
        try {
            session.beginTransaction();
            String query = "SELECT is_terisi FROM okupansi WHERE id_okupansi='" + id_okupansi + "'";
            List<Byte> hasilQuery = session.createNativeQuery(query).list();
            if (hasilQuery.get(0).toString().equals("1")) telahTerisi = false;
        } catch (Exception e) {
            e.printStackTrace(); log.error(e.getMessage());
        } finally {
            session.close(); factory.close();
        }
        return telahTerisi;
    }


    public boolean kelasnyaBerstatusAktif(String namaKelas) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(EntityInfoKelas.class)
                .addAnnotatedClass(EntityOkupansi.class)
                .addAnnotatedClass(EntitySetoran.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        byte statusKelas = 0;
        boolean booStatusKelas = false;
        try {
            session.beginTransaction();
            String query = "SELECT status_kelas FROM infokelas WHERE nama_kelas = '" + namaKelas + "'";
            List<Byte> hasilQuery = session.createNativeQuery(query).list();
            statusKelas = Byte.valueOf(hasilQuery.get(0).toString());
        } catch (Exception e) {
            e.printStackTrace(); log.error(e.getMessage());
        } finally {
            session.close(); factory.close();
        }

        if (statusKelas== 1) {
            booStatusKelas = true;
        }

        return booStatusKelas;
    }

    public long getAlamatKelas(String namaKelas) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(EntityInfoKelas.class)
                .addAnnotatedClass(EntityOkupansi.class)
                .addAnnotatedClass(EntitySetoran.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        long alamatKelas = 0L;
        try {
            session.beginTransaction();
            String query = "SELECT alamat_kelas FROM infokelas WHERE nama_kelas = '" + namaKelas + "'";
            List<String> hasilQuery = session.createNativeQuery(query).list();
            alamatKelas = Long.parseLong(hasilQuery.get(0).toString());
        } catch (Exception e) {
            e.printStackTrace(); log.error(e.getMessage());
        } finally {
            session.close(); factory.close();
        }
        return alamatKelas;
    }


    public String listingSlotKelasOkupansi(String namaKelas) {
        String indikasiNamaKelas =  namaKelas.substring(0,2);
        String indikasiKategori = namaKelas.substring(2,3);
        String indikasiGender = namaKelas.substring(3,4);

        String kategoriKelas = "";
        if (indikasiKategori.contains("a") || indikasiKategori.contains("A")) {
            kategoriKelas = "A";
        }
        if (indikasiKategori.contains("b") || indikasiKategori.contains("B")) {
            kategoriKelas = "B";
        }

        String kategoriGender = "";
        if (indikasiGender.contains("i") || indikasiGender.contains("I") ) {
            kategoriGender = "1";
        }
        if (indikasiGender.contains("a") || indikasiGender.contains("A") ) {
            kategoriGender = "2";
        }

        String namaKelasBentukan = indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-";
        String listOkupansi = "";
        String statusBooking = "";

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(EntityInfoKelas.class)
                .addAnnotatedClass(EntityOkupansi.class)
                .addAnnotatedClass(EntitySetoran.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        StringBuilder sb = new StringBuilder("Okupansi | Status\n-------------------------\n");

        try {
            session.beginTransaction();
            String query = "SELECT id_okupansi, is_terisi FROM okupansi WHERE id_okupansi LIKE '" + namaKelasBentukan + "%'";
            List<Object[]> hasilQuery = session.createNativeQuery(query).list();
//            EntityOkupansi okupansi = new EntityOkupansi();
            for (Object[] hasil : hasilQuery) {
                int getStatus = Integer.parseInt(hasil[1].toString());
                if (getStatus==1) statusBooking = "[BOOKED]"; else statusBooking = "[----------]";
                sb.append(hasil[0].toString() + " - " + statusBooking + "\n");
            }
            sb.append("-------------------------\n");
            listOkupansi = sb.toString();

        } catch (Exception e) {
            e.printStackTrace(); log.error(e.getMessage());
        } finally {
            session.close(); factory.close();
        }

        return listOkupansi;
    }

    public String listingIDokupansi(String namaKelas) {
        String indikasiNamaKelas =  namaKelas.substring(0,2);
        String indikasiKategori = namaKelas.substring(2,3);
        String indikasiGender = namaKelas.substring(3,4);

        String kategoriKelas = "";
        if (indikasiKategori.contains("a") || indikasiKategori.contains("A")) {
            kategoriKelas = "A";
        }
        if (indikasiKategori.contains("b") || indikasiKategori.contains("B")) {
            kategoriKelas = "B";
        }

        String kategoriGender = "";
        if (indikasiGender.contains("i") || indikasiGender.contains("I") ) {
            kategoriGender = "1";
        }
        if (indikasiGender.contains("a") || indikasiGender.contains("A") ) {
            kategoriGender = "2";
        }

        String namaKelasBentukan = indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-";
        String listOkupansi = "";

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(EntityInfoKelas.class)
                .addAnnotatedClass(EntityOkupansi.class)
                .addAnnotatedClass(EntitySetoran.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        StringBuilder sb = new StringBuilder("Okupansi | Status\n-------------------------\n");

        try {
            session.beginTransaction();
            String query = "SELECT id_okupansi, user_id, nama_santri FROM okupansi WHERE id_okupansi LIKE '" + namaKelasBentukan + "%'";
            List<Object[]> hasilQuery = session.createNativeQuery(query).list();
            for (Object[] hasil : hasilQuery) {
                sb.append(hasil[0].toString() + " - " + hasil[1].toString() + " - " + hasil[2].toString() + "\n");
            }
            sb.append("-------------------------\n");
            listOkupansi = sb.toString();

        } catch (Exception e) {
            e.printStackTrace(); log.error(e.getMessage());
        } finally {
            session.close(); factory.close();
        }

        return listOkupansi;
    }


    public void setValueGhoibSantri(String userName, int bilanganGhoib) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(EntityOkupansi.class)
                .addAnnotatedClass(EntitySetoran.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            session.createNativeQuery("UPDATE okupansi SET ghoib_frek=" + bilanganGhoib + " WHERE username='" + userName + "'").executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); log.error(e.getMessage());
        } finally {
            session.close(); factory.close();
        }
    }

    public void setValueUdzurSantri(String userName, int bilanganUdzur) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(EntityOkupansi.class)
                .addAnnotatedClass(EntitySetoran.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            session.createNativeQuery("UPDATE okupansi SET udzur_frek=" + bilanganUdzur + " WHERE username='" + userName + "'").executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); log.error(e.getMessage());
        } finally {
            session.close(); factory.close();
        }
    }

    public void generateRekapHarianPerKelas(String namaKelas) {

        if (kelasnyaBerstatusAktif(namaKelas)) {

            String indikasiNamaKelas =  namaKelas.substring(0,2);
            String indikasiKategori = namaKelas.substring(2,3);
            String indikasiGender = namaKelas.substring(3,4);

            String kategoriKelas = "";
            if (indikasiKategori.contains("a") || indikasiKategori.contains("A")) {
                kategoriKelas = "A";
            }
            if (indikasiKategori.contains("b") || indikasiKategori.contains("B")) {
                kategoriKelas = "B";
            }

            String kategoriGender = "";
            if (indikasiGender.contains("i") || indikasiGender.contains("I") ) {
                kategoriGender = "1";
            }
            if (indikasiGender.contains("a") || indikasiGender.contains("A") ) {
                kategoriGender = "2";
            }

            String namaKelasBentukan = indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-";


            String report = "";
            StringBuilder sbReport = new StringBuilder();
            StringBuilder sbUdzurGhoib = new StringBuilder();
            MTQBotRekap telegramBot = new MTQBotRekap();
            OperasiTanggal operasiTanggal = new OperasiTanggal();

            int tanggalKemarin = Integer.parseInt(operasiTanggal.getYesterdayDate()[0]);
            int bulanKemarin = Integer.parseInt(operasiTanggal.getYesterdayDate()[1]);
            int tahunKemarin = Integer.parseInt(operasiTanggal.getYesterdayDate()[2]);

            System.out.println(tanggalKemarin);
            System.out.println(bulanKemarin);
            System.out.println(tahunKemarin);

            int count_aktif = 0;
            int count_udzur = 0;
            int count_ghoib = 0;

            String simbolSetoran = "";
            System.out.println("\nMemproses untuk = " + namaKelas);
            sbReport.setLength(0);
            sbUdzurGhoib.setLength(0);

            sbReport.append("\n================================\n");
            sbReport.append(EmojiParser.parseToUnicode(":mecca: MTQ-Ma'had Tahfizh Qur'an \n"));
            sbReport.append(EmojiParser.parseToUnicode(":dhikr_beads: Rekap Setoran Hafizh MTQ " + namaKelas)); sbReport.append("\n");
            sbReport.append(EmojiParser.parseToUnicode(":spiral_calendar_pad: ") +  operasiTanggal.getTanggalPelaporan());
            sbReport.append("================================\n");

            // dari kelas terpilih, ambil ID database untuk okupansi yang aktif
            SessionFactory factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(EntityInfoKelas.class)
                    .addAnnotatedClass(EntityOkupansi.class)
                    .addAnnotatedClass(EntitySetoran.class)
                    .buildSessionFactory();
            Session session = factory.getCurrentSession();

            StringBuilder sb_ghoib = new StringBuilder();
            StringBuilder sb_udzur = new StringBuilder();

            List<Integer> id_db_ygSetoran = new ArrayList<>();
            List<Integer> id_user_ygSetoran = new ArrayList<>();

            try {
                session.beginTransaction();

                String query = "SELECT id_db_okupansi, user_id, is_terisi, today_setoran, nama_santri, ghoib_frek, ghoib_kapan, udzur_frek, udzur_kapan  FROM okupansi WHERE id_okupansi LIKE '" + namaKelasBentukan + "%'";
                List<Object[]> hasilQuery = session.createNativeQuery(query).list();

                for (Object[] hasil : hasilQuery) {
                // bila slotnya terisi, maka
                    if (Integer.parseInt(hasil[2].toString())==1) {
                        count_aktif++;
//                        System.out.println("\n");
                        // bila ghoib
                        if (Integer.parseInt(hasil[3].toString())==0) {
                            count_ghoib++;
                            sb_ghoib.append("\n:x: " + hasil[4].toString() + " " + hasil[5].toString() + " " + hasil[6].toString());
                            sbReport.append("\n:x: " + hasil[4].toString());
                            setPoinGhoib(Long.parseLong(hasil[1].toString()), 1);
                            System.out.println(count_aktif + " - ndak setor = " + hasil[4].toString());
                        // bila udzur
                        } else if (Integer.parseInt(hasil[3].toString())==10) {
                            count_udzur++;
                            sb_udzur.append("\n:sos: " + hasil[4].toString() + " " + hasil[5].toString() + " " + hasil[6].toString() + "\n");
                            sbReport.append("\n:sos: " + hasil[4].toString());
                            System.out.println(count_aktif + " - udzur = " + hasil[4].toString());
                        } else { //aktif
                            id_db_ygSetoran.add(Integer.valueOf(hasil[0].toString()));
                            id_user_ygSetoran.add(Integer.valueOf(hasil[1].toString()));

                            System.out.println(count_aktif + " - setoran = " + hasil[4].toString() + " " + hasil[1].toString() + " " + hasil[2].toString());
                        }

                    }

                }

                Integer[] arr_id_db_ygSetoran = new Integer[id_db_ygSetoran.size()];
                arr_id_db_ygSetoran = id_db_ygSetoran.toArray(arr_id_db_ygSetoran);
//                System.out.println("\n database ID yg setoran antara lain:");
//                for (Integer in : arr_id_db_ygSetoran) System.out.println(in);
                Integer[] arr_id_user_ygSetoran = new Integer[id_user_ygSetoran.size()];
                arr_id_user_ygSetoran = id_user_ygSetoran.toArray(arr_id_user_ygSetoran);
//                System.out.println("\n user ID yg setoran antara lain:");
//                for (Integer in : arr_id_user_ygSetoran) System.out.println(in);

                System.out.println("ID DB = " + Integer.valueOf(arr_id_db_ygSetoran[0]));

                String statusKoreksi = "";

                for (int i = 0; i < id_db_ygSetoran.size();i++) {
                    String querySetoran = "SELECT status_koreksian, nama_santri, nomer_suroh, ayat_begin, ayat_end FROM setoran WHERE id_db_okupansi=" + arr_id_db_ygSetoran[i] + " AND tanggal_masehi=" + tanggalKemarin + " AND bulan_masehi=" + bulanKemarin + " AND tahun_masehi=" + tahunKemarin;
                    List<Object[]> hasilQuerySetoran = session.createNativeQuery(querySetoran).list();
                    for (Object[] hasil : hasilQuerySetoran) {
//                    String namaSantrinya = hasil[0].toString();
                        if (hasil[0].toString().equals("0")) {
                            statusKoreksi = ":ballot_box_with_check:";
                        } else if (hasil[0].toString().equals("1")) {
                            statusKoreksi = ":white_check_mark:";
                        } else if (hasil[0].toString().equals("2")) {
                            statusKoreksi = ":recycle:";
                        }

                        sbReport.append("\n" + statusKoreksi + " " + hasil[1].toString() + " " + hasil[2].toString()+ ":" + hasil[3].toString()+ "-" + hasil[4].toString());
                    }
                }

                sbReport.append("\n\n");
                sbReport.append("================================\n");
                sbReport.append(":busts_in_silhouette: = " + count_aktif);
                sbReport.append(" :bust_in_silhouette: = " + (count_aktif - count_udzur - count_ghoib));
                sbReport.append(" :x: = "  + count_ghoib);
                sbReport.append(" :sos: = "  + count_udzur);


                sbReport = sbReport.append(EmojiParser.parseToUnicode(sb_ghoib.toString()));
                sbReport = sbReport.append(EmojiParser.parseToUnicode(sb_udzur.toString()));
//                sbReport.append("-------------------------\n");
                sbReport.append("================================\n");
                report = EmojiParser.parseToUnicode(sbReport.toString());
//                System.out.println(report);

                telegramBot.kirimkanPesan(report, getAlamatKelas(namaKelas));

            } catch (Exception e) {
                e.printStackTrace(); log.error(e.getMessage());
            } finally {
                session.close(); factory.close();
            }
        } else { // bila berstatus tidak aktif, ndak usah ngapa-ngapain
        }
    }

    public void generateReportSetoranSeluruhKelasAktif() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(EntityInfoKelas.class)
                .addAnnotatedClass(EntityOkupansi.class)
                .addAnnotatedClass(EntitySetoran.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        List<Integer> statusKelas = new ArrayList<>();
        List<String> kelasAktif = new ArrayList<>();

        try {
            session.beginTransaction();

            String query = "SELECT id_db_kelas, nama_kelas FROM infokelas WHERE status_kelas=1";
            List<Object[]> hasilQuery = session.createNativeQuery(query).list();

            for (Object[] hasil : hasilQuery) {
                statusKelas.add(Integer.valueOf(hasil[0].toString()));
                kelasAktif.add(hasil[1].toString());
            }

            String[] arrKelasAktif = new String[kelasAktif.size()];
            arrKelasAktif = kelasAktif.toArray(arrKelasAktif);

            for (int i=0; i < arrKelasAktif.length; i++) {
//                System.out.println("kelas aktif = " + arrKelasAktif[i]);
                generateRekapHarianPerKelas(arrKelasAktif[i]);
            }

        } catch (Exception e) {
            e.printStackTrace(); log.error(e.getMessage());
        } finally {
            session.close(); factory.close();
        }
    }

    public void generateDailyMRJreport(String namaKelas) {
        if (kelasnyaBerstatusAktif(namaKelas)) {

            MTQBotRekap telegramBot = new MTQBotRekap();
            OperasiTanggal operasiTanggal = new OperasiTanggal();

            String indikasiNamaKelas =  namaKelas.substring(0,2);
            String indikasiKategori = namaKelas.substring(2,3);
            String indikasiGender = namaKelas.substring(3,4);

            String kategoriKelas = "";
            if (indikasiKategori.contains("a") || indikasiKategori.contains("A")) {
                kategoriKelas = "A";
            }
            if (indikasiKategori.contains("b") || indikasiKategori.contains("B")) {
                kategoriKelas = "B";
            }

            String kategoriGender = "";
            if (indikasiGender.contains("i") || indikasiGender.contains("I") ) {
                kategoriGender = "1";
            }
            if (indikasiGender.contains("a") || indikasiGender.contains("A") ) {
                kategoriGender = "2";
            }

            String namaKelasBentukan = indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-";
            String report = "";
            StringBuilder sbReport = new StringBuilder();

            sbReport.append("\n================================\n");
            sbReport.append(EmojiParser.parseToUnicode(":star_crescent: MTQ-Ma'had Tahfizh Qur'an \n"));
            sbReport.append(EmojiParser.parseToUnicode(":m: Rekap Muroja'ah Hafizh MTQ " + namaKelas)); sbReport.append("\n");
            sbReport.append(EmojiParser.parseToUnicode(":spiral_calendar_pad: ") +  operasiTanggal.getTanggalPelaporan());
            sbReport.append("================================");

            SessionFactory factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(EntityInfoKelas.class)
                    .addAnnotatedClass(EntityOkupansi.class)
                    .addAnnotatedClass(EntitySetoran.class)
                    .buildSessionFactory();
            Session session = factory.getCurrentSession();
            try {
                session.beginTransaction();
                String statusSetoranMRJ = "";
                String statusSimakMRJ = "";
                String query = "SELECT id_db_okupansi, user_id, is_terisi, today_setoran, nama_santri, is_wajib_mrjharian, today_mrjharian, today_simak, mrjharian_page  FROM okupansi WHERE id_okupansi LIKE '" + namaKelasBentukan + "%'";
                List<Object[]> hasilQuery = session.createNativeQuery(query).list();

                for (Object[] hasil : hasilQuery) {
                    // bila slotnya memang terisi
                    if (Integer.parseInt(hasil[2].toString())==1 && hasil[5].toString().equals("1")) {
                        // Untuk setoran MRJ

                        // jika dia berstatus udzur dan dia gak kirim setoran MRJ padahal wajib MRJ harian
                        if (hasil[3].toString().equals("10") && hasil[6].toString().equals("0")) {
                            statusSetoranMRJ = ":sos:";

                            // jika dia gak kirim setoran MRJ padahal wajib MRJ harian
                        } else if (hasil[6].toString().equals("0"))  {
                            statusSetoranMRJ = ":x:";
                            setPoinGhoib(Long.parseLong(hasil[1].toString()), 2);

                        } else if (hasil[5].toString().equals("1")) {
                            statusSetoranMRJ = ":ballot_box_with_check:";
                        }
                        // Untuk simak MRJ partner
                        // jika dia berstatus udzur dan dia gak kirim setoran MRJ padahal wajib MRJ harian
                        if (hasil[3].toString().equals("10") && hasil[7].toString().equals("0")) {
                            statusSimakMRJ = ":sos:";
                            // jika dia gak kirim setoran MRJ padahal wajib MRJ harian
                        } else if (hasil[7].toString().equals("0") && hasil[5].toString().equals("1"))  {
                            statusSimakMRJ = ":x:";
                            setPoinGhoib(Long.parseLong(hasil[1].toString()), 2);
                        } else if (hasil[7].toString().equals("1")) {
                            statusSimakMRJ = ":ballot_box_with_check:";
                        }
                        sbReport.append("\n" + statusSetoranMRJ + " " + statusSimakMRJ + " " + hasil[4].toString() + " Hal." + hasil[8].toString());
                    }
                }

                sbReport.append("\n================================");
                report = EmojiParser.parseToUnicode(sbReport.toString());
                telegramBot.kirimkanPesan(report, getAlamatKelas(namaKelas));
                System.out.println(report);

            } catch (Exception e) {
                e.printStackTrace(); log.error(e.getMessage());
            } finally {
                session.close(); factory.close();
            }
        }
    }


    public void generateDailyMRJreportSemuaKelasAktif() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(EntityInfoKelas.class)
                .addAnnotatedClass(EntityOkupansi.class)
                .addAnnotatedClass(EntitySetoran.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        List<Integer> statusKelas = new ArrayList<>();
        List<String> kelasAktif = new ArrayList<>();

        try {
            session.beginTransaction();

            String query = "SELECT id_db_kelas, nama_kelas FROM infokelas WHERE status_kelas=1";
            List<Object[]> hasilQuery = session.createNativeQuery(query).list();

            for (Object[] hasil : hasilQuery) {
                statusKelas.add(Integer.valueOf(hasil[0].toString()));
                kelasAktif.add(hasil[1].toString());
            }

            String[] arrKelasAktif = new String[kelasAktif.size()];
            arrKelasAktif = kelasAktif.toArray(arrKelasAktif);

            for (int i=0; i < arrKelasAktif.length; i++) {
//                System.out.println("kelas aktif = " + arrKelasAktif[i]);
                generateDailyMRJreport(arrKelasAktif[i]);
            }

        } catch (Exception e) {
            e.printStackTrace(); log.error(e.getMessage());
        } finally {
            session.close(); factory.close();
        }
    }

    public void generateMRJakhirPekanReport(String namaKelas) {

        if (kelasnyaBerstatusAktif(namaKelas)) {
            MTQBotRekap telegramBot = new MTQBotRekap();
            OperasiTanggal operasiTanggal = new OperasiTanggal();

            String indikasiNamaKelas =  namaKelas.substring(0,2);
            String indikasiKategori = namaKelas.substring(2,3);
            String indikasiGender = namaKelas.substring(3,4);

            String kategoriKelas = "";
            if (indikasiKategori.contains("a") || indikasiKategori.contains("A")) {
                kategoriKelas = "A";
            }
            if (indikasiKategori.contains("b") || indikasiKategori.contains("B")) {
                kategoriKelas = "B";
            }

            String kategoriGender = "";
            if (indikasiGender.contains("i") || indikasiGender.contains("I") ) {
                kategoriGender = "1";
            }
            if (indikasiGender.contains("a") || indikasiGender.contains("A") ) {
                kategoriGender = "2";
            }

            String namaKelasBentukan = indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-";
            String report = "";
            StringBuilder sbReport = new StringBuilder();

            sbReport.append("\n================================\n");
            sbReport.append(EmojiParser.parseToUnicode(":star_crescent: MTQ-Ma'had Tahfizh Qur'an \n"));
            sbReport.append(EmojiParser.parseToUnicode(":m: Rekap MRJ Akhir Pekan Hafizh MTQ " + namaKelas)); sbReport.append("\n");
            sbReport.append(EmojiParser.parseToUnicode(":spiral_calendar_pad: ") +  operasiTanggal.getTanggalPelaporan());
            sbReport.append("================================");

            SessionFactory factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(EntityInfoKelas.class)
                    .addAnnotatedClass(EntityOkupansi.class)
                    .addAnnotatedClass(EntitySetoran.class)
                    .buildSessionFactory();
            Session session = factory.getCurrentSession();
            try {
                session.beginTransaction();
                String statusSetoranMRJakhirPekan = "";
                String statusSimakMRJakhirPekan = "";
                String query = "SELECT id_db_okupansi, user_id, is_terisi, today_setoran, nama_santri, mrjap_setor, mrjap_simak  FROM okupansi WHERE id_okupansi LIKE '" + namaKelasBentukan + "%'";
                List<Object[]> hasilQuery = session.createNativeQuery(query).list();

                for (Object[] hasil : hasilQuery) {
                    // bila slotnya memang terisi
                    if (Integer.parseInt(hasil[2].toString())==1) {
                        // Untuk setoran MRJ
                        // jika dia berstatus udzur dan dia gak kirim setoran MRJ padahal wajib MRJ harian
                        if (hasil[3].toString().equals("10")) {
                            statusSetoranMRJakhirPekan = ":sos:";
                            // jika dia gak kirim setoran MRJ padahal wajib MRJ harian
                        } else if (hasil[5].toString().equals("0"))  {
                            statusSetoranMRJakhirPekan = ":x:";
                            setPoinGhoib(Long.parseLong(hasil[1].toString()), 2);
                        } else if (hasil[5].toString().equals("1")) {
                            statusSetoranMRJakhirPekan = ":ballot_box_with_check:";
                        }
                        // Untuk simak MRJ partner
                        // jika dia berstatus udzur dan dia gak kirim setoran MRJ akhir pekan
                        if (hasil[3].toString().equals("10")) {
                            statusSimakMRJakhirPekan = ":sos:";
                            // jika dia gak kirim setoran MRJ akhir pekan
                        } else if (hasil[6].toString().equals("0"))  {
                            statusSimakMRJakhirPekan = ":x:";
                            setPoinGhoib(Long.parseLong(hasil[1].toString()), 2);
                        } else if (hasil[6].toString().equals("1")) {
                            statusSimakMRJakhirPekan = ":ballot_box_with_check:";
                        }
                        sbReport.append("\n" + statusSetoranMRJakhirPekan + " " + statusSimakMRJakhirPekan + " " + hasil[4].toString());
                    }
                }

                sbReport.append("\n================================");
                report = EmojiParser.parseToUnicode(sbReport.toString());
                telegramBot.kirimkanPesan(report, getAlamatKelas(namaKelas));
//                System.out.println(report);

            } catch (Exception e) {
                e.printStackTrace(); log.error(e.getMessage());
            } finally {
                session.close(); factory.close();
            }
        }
    }

    public void generateMRJAkhirPekanSemuaKelasAktif() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(EntityInfoKelas.class)
                .addAnnotatedClass(EntityOkupansi.class)
                .addAnnotatedClass(EntitySetoran.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        List<Integer> statusKelas = new ArrayList<>();
        List<String> kelasAktif = new ArrayList<>();

        try {
            session.beginTransaction();

            String query = "SELECT id_db_kelas, nama_kelas FROM infokelas WHERE status_kelas=1";
            List<Object[]> hasilQuery = session.createNativeQuery(query).list();

            for (Object[] hasil : hasilQuery) {
                statusKelas.add(Integer.valueOf(hasil[0].toString()));
                kelasAktif.add(hasil[1].toString());
            }

            String[] arrKelasAktif = new String[kelasAktif.size()];
            arrKelasAktif = kelasAktif.toArray(arrKelasAktif);

            for (int i=0; i < arrKelasAktif.length; i++) {
//                System.out.println("kelas aktif = " + arrKelasAktif[i]);
                generateMRJakhirPekanReport(arrKelasAktif[i]);
            }

        } catch (Exception e) {
            e.printStackTrace(); log.error(e.getMessage());
        } finally {
            session.close(); factory.close();
        }
    }


    public void koreksiLulus(long idPesanYgDireply) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(EntityInfoKelas.class)
                .addAnnotatedClass(EntityOkupansi.class)
                .addAnnotatedClass(EntitySetoran.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            session.createNativeQuery("UPDATE setoran SET status_koreksian=1 WHERE id_pesan_user=" + idPesanYgDireply + " OR id_audio_setoran=" + idPesanYgDireply).executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); log.error(e.getMessage());
        } finally {
            session.close(); factory.close();
        }
    }

    public void koreksiUlang(long idPesanYgDireply) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(EntityInfoKelas.class)
                .addAnnotatedClass(EntityOkupansi.class)
                .addAnnotatedClass(EntitySetoran.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            session.createNativeQuery("UPDATE setoran SET status_koreksian=2 WHERE id_pesan_user=" + idPesanYgDireply + " OR id_audio_setoran=" + idPesanYgDireply).executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); log.error(e.getMessage());
        } finally {
            session.close(); factory.close();
        }
    }


}
