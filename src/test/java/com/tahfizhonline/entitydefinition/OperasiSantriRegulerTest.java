package com.tahfizhonline.entitydefinition;

import com.tahfizhonline.entityoperation.OperasiInfoKelas;
import com.tahfizhonline.entityoperation.OperasiOkupansi;
import com.tahfizhonline.entityoperation.OperasiSantriReguler;
import com.tahfizhonline.fungsidukung.OperasiTanggal;
import org.hibernate.annotations.NaturalId;
import org.junit.Test;

public class OperasiSantriRegulerTest {

    private static String namanya = "Abdullah";
    private static String usernamenya = "abdul198";
    private static String kunyahnya = "Abu Rezeki";
    private static int gender = 1;
    private static int tahunlahir = 1983;
    private static String noHPnya = "08123146673";
    private static String emailnya = "abu@gmail.com";
    public static String isiPesan = "isi pesannya";

    @Test
    public void cobaSantriReguler() throws Exception {
        OperasiSantriReguler operasiSantriReguler = new OperasiSantriReguler();
//        operasiSantriReguler.createUserMTQ("id_mtq", "nama_depan", "nama_belakang",
//                "nama_kunyah", "nomer_hp", "email", 1, "username",
//                1, 2, 3, 103);
//        operasiSantriReguler.deleteUserMTQ(5);
//        operasiSantriReguler.getSantriDetail(5);
// long user_id, String id_mtq, String nama_santri, String nama_kunyah, String nomer_hp, String email, String gender, String username, int tahun_lahir) {
//        operasiSantriReguler.regSbgSantri(85076067, usernamenya, namanya, kunyahnya, noHPnya, emailnya, "i", tahunlahir,
//                0, 0, 0, 0, -239673816);
    }

    @Test
    public void OperasiInfoKelas() throws Exception {
        OperasiInfoKelas operasiInfoKelas = new OperasiInfoKelas();
//        operasiInfoKelas.registerKelas("32bi", 1235678L);
//        operasiInfoKelas.listSeluruhKelas();
//        operasiInfoKelas.enableKelas("24Bi");
//        operasiInfoKelas.deleteKelasTerpilih("31bi");
    }

    @Test
    public void OperasiOkupansi() throws Exception {
        OperasiOkupansi operasiOkupansi = new OperasiOkupansi();
//        operasiOkupansi.insertSetoranHarian(123456, 3455635, 12345312, "username",
//                "FirstLastName", 110, 1, 3);
//        System.out.println(operasiOkupansi.getKunyahSantri("Masta198"));
//        operasiOkupansi.insertSantriToKelas("32B-1-04", "Masta198212");
//        System.out.println(operasiOkupansi.getAlamatKelas("30Bi"));
//        operasiOkupansi.listingSlotKelasOkupansi("32bi");
//        operasiOkupansi.getUserDatabaseID("aburamsi2");
//        operasiOkupansi.getOkupansiDatabaseID(831);
// (long user_id, long id_pesan_bot, long id_pesan_user, long id_audio_setoran, long tanggalPesan, int tanggalMasehi, int bulanMasehi, int tahunMasehi, int nomerSuroh, int ayatBegin, int ayatEnd) {
//        operasiOkupansi.createSetoranReguler(123456, 13131, 23232, 43434, 31232, 23, 9, 2017, 110, 4, 5);
//        System.out.println(operasiOkupansi.santriTelahTerdaftarByID(85076067));
//        System.out.println("Status = " + operasiOkupansi.kelasnyaBerstatusAktif("29Bi"));
//        operasiOkupansi.generateRekapHarianPerKelas("32Bi");
//        operasiOkupansi.generateReportSetoranSeluruhKelasAktif();
//        operasiOkupansi.generateDailyMRJreport("32Bi");
//        operasiOkupansi.generateDailyMRJreportSemuaKelasAktif();
//        operasiOkupansi.generateMRJakhirPekanReport("32Bi");
//        operasiOkupansi.setUdzurSakit(85076067);
        operasiOkupansi.setPoinGhoib(85076067,1);


    }

    @Test
    public void OperasiTanggal() throws Exception {
        OperasiTanggal operasiTanggal = new OperasiTanggal();
//        System.out.println(operasiTanggal.getAweekAgoDate()[0]);
    }


    /*
    MTQBotRekap mtqBotRekap;
    @Before
    public void setupMoc() {
        mtqBotRekap = new MTQBotRekap();
        Update update = new Update();
        update.hasMessage();
        update.getMessage().hasText();
        System.out.println(update.hasMessage());
        System.out.println(update.getMessage().hasText());
//        when(update.hasMessage()).thenReturn(true);
//        when(update.getMessage().hasText()).thenReturn(true);
        when(update.getMessage().getText()).thenReturn("This is your message");
    }

    @Test
    public void testMockCreation() {
        assertNotNull(update);

    }

    @Test
    public void testMessage() {
        String expected="This is your message";
        mtqBotRekap.onUpdateReceived(update);

        assertThat(update.getMessage().getText(), equalToIgnoringCase("This is your message"));

    }*/



}