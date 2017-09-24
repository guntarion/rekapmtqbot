package com.tahfizhonline.entityoperation;

import com.tahfizhonline.MTQBotRekap;
import com.tahfizhonline.entitydefinition.EntityPendaftar;
import com.tahfizhonline.entitydefinition.EntitySantriReguler;
import com.tahfizhonline.entitydefinition.EntitySantriRegulerStats;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class OperasiSantriReguler {

    private static org.apache.log4j.Logger log = Logger.getLogger(OperasiSantriReguler.class);

    public void createCalonSantri(String nama, String username, int gender, int tahunLahir) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml").addAnnotatedClass(EntityPendaftar.class).buildSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            EntityPendaftar entityPendaftar = new EntityPendaftar(nama, username, gender, tahunLahir);
            session.beginTransaction(); session.save(entityPendaftar); session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }


    public void createUserMTQ(String id_mtq, long user_id, String username, String nama_depan, String nama_belakang, String nama_kunyah,
                              String nomer_hp, String email, int gender, int udzur_total, float ghoib_total, int suroh_terakhir, int ayat_terakhir) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(EntitySantriReguler.class)
                .addAnnotatedClass(EntitySantriRegulerStats.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        try {
//            EntitySantriReguler entitySantri =
//                    new EntitySantriReguler(id_mtq, user_id, username, nama_depan, nama_belakang, nama_kunyah,
//                    nomer_hp, email, gender);

//            EntitySantriRegulerStats entitySantriRegulerStats =
//                    new EntitySantriRegulerStats(udzur_total, ghoib_total, suroh_terakhir, ayat_terakhir);
//            // associate the objects
//            entitySantri.setDetailStatsSantri(entitySantriRegulerStats);
//
//            session.beginTransaction();
//            session.save(entitySantri);
//            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace(); log.error(e.getMessage());
        } finally {
            factory.close();
        }
    }

    public void regSbgSantri(long user_id, String username, String namaSantri, String kunyah, String SetoranTerakhir, String noHP, String gender, int tahunLahir,
                             int udzur_total, float ghoib_total, int suroh_terakhir, int ayat_terakhir, long chat_id) {


        if (!idSantriSudahTerdaftar(user_id)) {
            SessionFactory factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(EntitySantriReguler.class)
                    .addAnnotatedClass(EntitySantriRegulerStats.class)
                    .buildSessionFactory();
            Session session = factory.getCurrentSession();

            String[] setorTerakhir = SetoranTerakhir.split(":");

            try {
                EntitySantriReguler entitySantri =
                        new EntitySantriReguler(user_id, "", namaSantri, kunyah, noHP, gender, username, tahunLahir);
                EntitySantriRegulerStats entitySantriRegulerStats =
                        new EntitySantriRegulerStats(udzur_total, ghoib_total, Integer.parseInt(setorTerakhir[0]), Integer.parseInt(setorTerakhir[1]));
                // associate the objects
                entitySantri.setDetailStatsSantri(entitySantriRegulerStats);

                session.beginTransaction();
                session.save(entitySantri);
                session.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace(); log.error(e.getMessage());
            } finally {
                factory.close();
            }
        } else {
            String isiPesan = ":memo: :blush: Data santri @" + username + " sudah terdaftar di database RekapMTQbot. Tidak perlu lakukan input ulang.";
            MTQBotRekap mtqBotRekap = new MTQBotRekap();
            mtqBotRekap.kirimkanPesan(isiPesan, chat_id);
        }



    }

    public void deleteUserMTQ(int theId) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(EntitySantriReguler.class)
                .addAnnotatedClass(EntitySantriRegulerStats.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
//            EntitySantriReguler entitySantri = session.get(EntitySantriReguler.class, theId);
            EntitySantriRegulerStats entitySantri = session.get(EntitySantriRegulerStats.class, theId);

            // !! Jika ingin hanya menghapus di tabel Stats saja, maka hapus dulu asosiasi antar dua tabel
//            entitySantri.getSantriReguler().setDetailStatsSantri(null);

            if (entitySantri != null) {
                session.delete(entitySantri);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace(); log.error(e.getMessage());
        } finally {
            factory.close();
        }
    }

    public void getSantriDetail(int theId) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(EntitySantriReguler.class)
                .addAnnotatedClass(EntitySantriRegulerStats.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            EntitySantriRegulerStats entitySantriStats =
                    session.get(EntitySantriRegulerStats.class, theId);

            System.out.println("detail = " + entitySantriStats);
            System.out.println("the santri = " + entitySantriStats.getSantriReguler());

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace(); log.error(e.getMessage());
        } finally {
            factory.close();
        }
    }

    public boolean idSantriSudahTerdaftar(long user_id) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(EntitySantriReguler.class)
                .addAnnotatedClass(EntitySantriRegulerStats.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        boolean sudahTerdaftar = true;
        try {
            session.beginTransaction();
            String queryUserID = "SELECT user_id FROM santrireguler WHERE user_id=" + user_id;
            List<Boolean> hasilQuserID = session.createNativeQuery(queryUserID).list();
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


}
