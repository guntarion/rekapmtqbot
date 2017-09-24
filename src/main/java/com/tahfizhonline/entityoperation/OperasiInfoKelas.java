package com.tahfizhonline.entityoperation;

import com.tahfizhonline.entitydefinition.EntityInfoKelas;
import com.tahfizhonline.entitydefinition.EntityOkupansi;
import com.tahfizhonline.entitydefinition.EntitySetoran;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OperasiInfoKelas {

    private static org.apache.log4j.Logger log = Logger.getLogger(OperasiInfoKelas.class);

    public void registerKelas(String namaKelas, long alamatGrup) {
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
        if (indikasiGender.contains("i") || indikasiGender.contains("I")) {
            kategoriGender = "1";
        }
        if (indikasiGender.contains("a") || indikasiGender.contains("A")) {
            kategoriGender = "2";
        }

        String namaKelasBentukan = indikasiNamaKelas + kategoriKelas + indikasiGender;

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(EntityInfoKelas.class)
                .addAnnotatedClass(EntityOkupansi.class)
                .addAnnotatedClass(EntitySetoran.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            // create kelas object
            EntityInfoKelas infoKelas = new EntityInfoKelas(namaKelasBentukan, String.valueOf(alamatGrup), 0,
                    kategoriKelas, "", "", 0, 0, "", "");

            EntityOkupansi okupansi_1 = new EntityOkupansi
                    (indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-01", 0, 0, "", 0, "", 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            EntityOkupansi okupansi_2 = new EntityOkupansi
                    (indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-02", 0, 0, "", 0, "", 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            EntityOkupansi okupansi_3 = new EntityOkupansi
                    (indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-03", 0, 0, "", 0, "", 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            EntityOkupansi okupansi_4 = new EntityOkupansi
                    (indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-04", 0, 0, "", 0, "", 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            EntityOkupansi okupansi_5 = new EntityOkupansi
                    (indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-05", 0, 0, "", 0, "", 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            EntityOkupansi okupansi_6 = new EntityOkupansi
                    (indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-06", 0, 0, "", 0, "", 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            EntityOkupansi okupansi_7 = new EntityOkupansi
                    (indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-07", 0, 0, "", 0, "", 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            EntityOkupansi okupansi_8 = new EntityOkupansi
                    (indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-08", 0, 0, "", 0, "", 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            EntityOkupansi okupansi_9 = new EntityOkupansi
                    (indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-09", 0, 0, "", 0, "", 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            EntityOkupansi okupansi_10 = new EntityOkupansi
                    (indikasiNamaKelas + kategoriKelas + "-" + kategoriGender + "-10", 0, 0, "", 0, "", 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

            infoKelas.tambahkan(okupansi_1);
            infoKelas.tambahkan(okupansi_2);
            infoKelas.tambahkan(okupansi_3);
            infoKelas.tambahkan(okupansi_4);
            infoKelas.tambahkan(okupansi_5);
            infoKelas.tambahkan(okupansi_6);
            infoKelas.tambahkan(okupansi_7);
            infoKelas.tambahkan(okupansi_8);
            infoKelas.tambahkan(okupansi_9);
            infoKelas.tambahkan(okupansi_10);

            session.beginTransaction();
            session.save(infoKelas);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace(); log.error(e.getMessage());
        } finally {
            session.close(); factory.close();
        }
    }

    public String listSeluruhKelas() {
        String listKelas = "";
        int counter=0;
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(EntityInfoKelas.class).addAnnotatedClass(EntityOkupansi.class).addAnnotatedClass(EntitySetoran.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();

            // query kelas
            List<EntityInfoKelas> listInfoKelas = session.createQuery("from EntityInfoKelas").getResultList();
//            listInfoKelas = session.createQuery("from EntityInfoKelas s where s.status_kelas=1").getResultList();

            StringBuilder sb = new StringBuilder("\nKelas | Status\n-------------\n");
            List<String> listNamaKelas = new ArrayList<String>();
            List<Integer> listStatus = new ArrayList<Integer>();
            String statusKelas = "";

            for (int i = 0; i < listInfoKelas.size(); i++) {
                if (listInfoKelas.get(i).getStatus_kelas()==1) {
                    statusKelas = "[AKTIF-]"; counter++;
                } else statusKelas = "[--no--]";

                listStatus.add(listInfoKelas.get(i).getStatus_kelas());

                sb.append(listInfoKelas.get(i).getNama_kelas()
                        + " " + statusKelas + "\n");
            }

            sb.append("-------------\n");
            sb.append("Total = " + counter + "/" + listInfoKelas.size());
            listKelas = sb.toString();

            System.out.println(listKelas);



//            listInfoKelas = session.createQuery("from EntityInfoKelas s").getResultList();
//            displayKelas(listInfoKelas);


//            System.out.println(listInfoKelas.);

//            Query query = session.createQuery("select id_db_kelas from EntityInfoKelas s where s.nama_kelas='27Bi'", Object[].class);
//            Query query = session.createQuery("select id_db_kelas from EntityInfoKelas s where s.status_kelas=1", Object[].class);

//            List<Object[]> rows = query.getResultList();


//            while (rows.iterator().hasNext()) {
//                System.out.println("data: " + rows.iterator().next());
//            }

//            System.out.println("\n-----");
//            System.out.println("\uD83D\uDD25 Hasil satuan: " + rows.iterator().next());
//            System.out.println("\n-----");

            // List berdasarkan ID
//            EntityInfoKelas infoKelas = session.get(EntityInfoKelas.class, 4);
//            System.out.println("\n");
//            System.out.println("Get complete: " + infoKelas.getNama_kelas());

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace(); log.error(e.getMessage());
        } finally {
            session.close(); factory.close();
        }

        return listKelas;
    }

    private static void displayKelas(List<EntityInfoKelas> infoKelasnya) {

        List<String> listHasil = new ArrayList<String>();

        for (int i = 0; i < infoKelasnya.size(); i++) {
            listHasil.add(infoKelasnya.get(i).getNama_kelas());
            System.out.println(infoKelasnya.get(i).getNama_kelas());
        }

        System.out.println(listHasil.toArray().toString());
        System.out.println("\n");

        String[] hasilArr = new String[listHasil.size()];
        hasilArr = listHasil.toArray(hasilArr);

    }

    public void enableKelas(String namaKelas) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(EntityInfoKelas.class).addAnnotatedClass(EntityOkupansi.class).addAnnotatedClass(EntitySetoran.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            Query query = session.createNativeQuery("UPDATE infokelas SET status_kelas=1 WHERE nama_kelas='" + namaKelas +"'");
            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); log.error(e.getMessage());
        } finally {
            session.close(); factory.close();
        }
    }

    public void disableKelas(String namaKelas) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(EntityInfoKelas.class).addAnnotatedClass(EntityOkupansi.class).addAnnotatedClass(EntitySetoran.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            Query query = session.createNativeQuery("UPDATE infokelas SET status_kelas=0 WHERE nama_kelas='" + namaKelas +"'");
            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); log.error(e.getMessage());
        } finally {
            session.close(); factory.close();
        }
    }

    public void deleteKelasTerpilih(String namaKelas) {
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
        if (indikasiGender.contains("i") || indikasiGender.contains("I")) {
            kategoriGender = "1";
        }
        if (indikasiGender.contains("a") || indikasiGender.contains("A")) {
            kategoriGender = "2";
        }
        String namaKelasBentukan = indikasiNamaKelas + kategoriKelas + indikasiGender;

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(EntityInfoKelas.class).addAnnotatedClass(EntityOkupansi.class).addAnnotatedClass(EntitySetoran.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            Query queryInfoKelas = session.createNativeQuery("DELETE FROM infokelas WHERE nama_kelas='" + namaKelasBentukan +"'");
            queryInfoKelas.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); log.error(e.getMessage());
        } finally {
            session.close(); factory.close();
        }


    }


}
