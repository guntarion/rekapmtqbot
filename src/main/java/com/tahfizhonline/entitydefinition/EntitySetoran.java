package com.tahfizhonline.entitydefinition;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "setoran")
public class EntitySetoran {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_db_setoran")
    private int id_setoran;

    // TODO: you need to change this
//    @Column(name="id_mtq")
//    private String idMTQ;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_db_okupansi")
    private EntityOkupansi okupansiSetoran;

    private long user_id;
    private String nama_santri;
    private long id_pesan_bot;
    private long id_pesan_user;
    private long id_audio_setoran;
    private long tanggal_pesan;
    private int tanggal_masehi;
    private int bulan_masehi;
    private int tahun_masehi;
    private int nomer_suroh;
    private int ayat_begin;
    private int ayat_end;
    private int status_koreksian;
    private int status_kehadiran;
    @CreationTimestamp
    private LocalDateTime created_date;



//    private static Timestamp getCurrentTimeStamp() {
//        java.util.Date today = new java.util.Date();
//        return new Timestamp(today.getTime());
//    }

    public EntitySetoran() {

    }

    public EntitySetoran(long user_id, String nama_santri, long id_pesan_bot, long id_pesan_user, long id_audio_setoran, long tanggal_pesan, int tanggal_masehi, int bulan_masehi, int tahun_masehi, int nomer_suroh, int ayat_begin, int ayat_end, int status_koreksian, int status_kehadiran) {
        this.user_id = user_id;
        this.nama_santri = nama_santri;
        this.id_pesan_bot = id_pesan_bot;
        this.id_pesan_user = id_pesan_user;
        this.id_audio_setoran = id_audio_setoran;
        this.tanggal_pesan = tanggal_pesan;
        this.tanggal_masehi = tanggal_masehi;
        this.bulan_masehi = bulan_masehi;
        this.tahun_masehi = tahun_masehi;
        this.nomer_suroh = nomer_suroh;
        this.ayat_begin = ayat_begin;
        this.ayat_end = ayat_end;
        this.status_koreksian = status_koreksian;
        this.status_kehadiran = status_kehadiran;
    }

    @Override
    public String toString() {
        return "EntitySetoran{" +
                "id_setoran=" + id_setoran +
                ", okupansiSetoran=" + okupansiSetoran +
                ", user_id=" + user_id +
                ", nama_santri='" + nama_santri + '\'' +
                ", id_pesan_bot=" + id_pesan_bot +
                ", id_pesan_user=" + id_pesan_user +
                ", id_audio_setoran=" + id_audio_setoran +
                ", tanggal_pesan=" + tanggal_pesan +
                ", tanggal_masehi=" + tanggal_masehi +
                ", bulan_masehi=" + bulan_masehi +
                ", tahun_masehi=" + tahun_masehi +
                ", nomer_suroh=" + nomer_suroh +
                ", ayat_begin=" + ayat_begin +
                ", ayat_end=" + ayat_end +
                ", status_koreksian=" + status_koreksian +
                ", status_kehadiran=" + status_kehadiran +
                ", created_date=" + created_date +
                '}';
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public int getId_setoran() {
        return id_setoran;
    }

    public void setId_setoran(int id_setoran) {
        this.id_setoran = id_setoran;
    }

    public EntityOkupansi getOkupansiSetoran() {
        return okupansiSetoran;
    }

    public void setOkupansiSetoran(EntityOkupansi okupansiSetoran) {
        this.okupansiSetoran = okupansiSetoran;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getNama_santri() {
        return nama_santri;
    }

    public void setNama_santri(String nama_santri) {
        this.nama_santri = nama_santri;
    }

    public long getId_pesan_bot() {
        return id_pesan_bot;
    }

    public void setId_pesan_bot(long id_pesan_bot) {
        this.id_pesan_bot = id_pesan_bot;
    }

    public long getId_pesan_user() {
        return id_pesan_user;
    }

    public void setId_pesan_user(long id_pesan_user) {
        this.id_pesan_user = id_pesan_user;
    }

    public long getId_audio_setoran() {
        return id_audio_setoran;
    }

    public void setId_audio_setoran(long id_audio_setoran) {
        this.id_audio_setoran = id_audio_setoran;
    }

    public long getTanggal_pesan() {
        return tanggal_pesan;
    }

    public void setTanggal_pesan(long tanggal_pesan) {
        this.tanggal_pesan = tanggal_pesan;
    }

    public int getTanggal_masehi() {
        return tanggal_masehi;
    }

    public void setTanggal_masehi(int tanggal_masehi) {
        this.tanggal_masehi = tanggal_masehi;
    }

    public int getBulan_masehi() {
        return bulan_masehi;
    }

    public void setBulan_masehi(int bulan_masehi) {
        this.bulan_masehi = bulan_masehi;
    }

    public int getTahun_masehi() {
        return tahun_masehi;
    }

    public void setTahun_masehi(int tahun_masehi) {
        this.tahun_masehi = tahun_masehi;
    }

    public int getNomer_suroh() {
        return nomer_suroh;
    }

    public void setNomer_suroh(int nomer_suroh) {
        this.nomer_suroh = nomer_suroh;
    }

    public int getAyat_begin() {
        return ayat_begin;
    }

    public void setAyat_begin(int ayat_begin) {
        this.ayat_begin = ayat_begin;
    }

    public int getAyat_end() {
        return ayat_end;
    }

    public void setAyat_end(int ayat_end) {
        this.ayat_end = ayat_end;
    }

    public int getStatus_koreksian() {
        return status_koreksian;
    }

    public void setStatus_koreksian(int status_koreksian) {
        this.status_koreksian = status_koreksian;
    }

    public int getStatus_kehadiran() {
        return status_kehadiran;
    }

    public void setStatus_kehadiran(int status_kehadiran) {
        this.status_kehadiran = status_kehadiran;
    }


}
