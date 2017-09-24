package com.tahfizhonline.entitydefinition;

import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "okupansi")
public class EntityOkupansi {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_db_okupansi;
//    private int id_db_kelas;
    @NaturalId
    private String id_okupansi;

    @UpdateTimestamp
    private LocalDateTime latest_update;

    private int is_terisi;
    private long user_id;
    private String username_okupan;
    private String nama_santri;
    private float ghoib_frek;
    private String ghoib_kapan;
    private int udzur_frek;
    private String udzur_kapan;
    private int is_imtihan;
    private int today_setoran;
    private int today_mrjharian;
    private int today_simak;
    private int status_setoran;
    private int p_no_suroh;
    private int p_ayat_begin;
    private int p_ayat_end;
    private int mrjap_suroh_begin;
    private int mrjap_suroh_end;
    private int mrj_ap_ayat_begin;
    private int mrj_ap_ayat_end;
    private int mrjap_setor;
    private int mrjap_simak;
    private int is_wajib_mrjharian;
    private int mrjharian_page;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_db_kelas")
    private EntityInfoKelas infoKelasOkupansi;

    @OneToMany(mappedBy = "okupansiSetoran", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EntitySetoran> setorans;

    public  EntityOkupansi() {

    }

    //, EntityInfoKelas infoKelasOkupansi
    public EntityOkupansi(String id_okupansi, int is_terisi, float ghoib_frek, String ghoib_kapan, int udzur_frek, String udzur_kapan, int is_imtihan, int today_setoran, int today_mrjharian, int today_simak, int status_setoran, int p_no_suroh, int p_ayat_begin, int p_ayat_end, int mrjap_suroh_begin, int mrjap_suroh_end, int mrj_ap_ayat_begin, int mrj_ap_ayat_end, int mrjap_setor, int mrjap_simak, int is_wajib_mrjharian, int mrjharian_page) {
        this.id_okupansi = id_okupansi;
        this.is_terisi = is_terisi;
        this.ghoib_frek = ghoib_frek;
        this.ghoib_kapan = ghoib_kapan;
        this.udzur_frek = udzur_frek;
        this.udzur_kapan = udzur_kapan;
        this.is_imtihan = is_imtihan;
        this.today_setoran = today_setoran;
        this.today_mrjharian = today_mrjharian;
        this.today_simak = today_simak;
        this.status_setoran = status_setoran;
        this.p_no_suroh = p_no_suroh;
        this.p_ayat_begin = p_ayat_begin;
        this.p_ayat_end = p_ayat_end;
        this.mrjap_suroh_begin = mrjap_suroh_begin;
        this.mrjap_suroh_end = mrjap_suroh_end;
        this.mrj_ap_ayat_begin = mrj_ap_ayat_begin;
        this.mrj_ap_ayat_end = mrj_ap_ayat_end;
        this.mrjap_setor = mrjap_setor;
        this.mrjap_simak = mrjap_simak;
        this.is_wajib_mrjharian = is_wajib_mrjharian;
        this.mrjharian_page = mrjharian_page;
    }

    public void addSetoranOkupansi(EntitySetoran setoran) {
        if (setorans == null) {
            setorans = new ArrayList<>();
        }

        setorans.add(setoran);
        setoran.setOkupansiSetoran(this);

    }

    public LocalDateTime getLatest_update() {
        return latest_update;
    }

    public String getNama_santri() {
        return nama_santri;
    }

    public void setNama_santri(String nama_santri) {
        this.nama_santri = nama_santri;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public int getId_db_okupansi() {
        return id_db_okupansi;
    }

    public void setId_db_okupansi(int id_db_okupansi) {
        this.id_db_okupansi = id_db_okupansi;
    }

    public String getId_okupansi() {
        return id_okupansi;
    }

    public void setId_okupansi(String id_okupansi) {
        this.id_okupansi = id_okupansi;
    }

    public int getIs_terisi() {
        return is_terisi;
    }

    public void setIs_terisi(int is_terisi) {
        this.is_terisi = is_terisi;
    }

    public String getUsername_okupan() {
        return username_okupan;
    }

    public void setUsername_okupan(String username_okupan) {
        this.username_okupan = username_okupan;
    }

    public float getGhoib_frek() {
        return ghoib_frek;
    }

    public void setGhoib_frek(float ghoib_frek) {
        this.ghoib_frek = ghoib_frek;
    }

    public String getGhoib_kapan() {
        return ghoib_kapan;
    }

    public void setGhoib_kapan(String ghoib_kapan) {
        this.ghoib_kapan = ghoib_kapan;
    }

    public int getUdzur_frek() {
        return udzur_frek;
    }

    public void setUdzur_frek(int udzur_frek) {
        this.udzur_frek = udzur_frek;
    }

    public String getUdzur_kapan() {
        return udzur_kapan;
    }

    public void setUdzur_kapan(String udzur_kapan) {
        this.udzur_kapan = udzur_kapan;
    }

    public int getIs_imtihan() {
        return is_imtihan;
    }

    public void setIs_imtihan(int is_imtihan) {
        this.is_imtihan = is_imtihan;
    }

    public int getToday_setoran() {
        return today_setoran;
    }

    public void setToday_setoran(int today_setoran) {
        this.today_setoran = today_setoran;
    }

    public int getToday_mrjharian() {
        return today_mrjharian;
    }

    public void setToday_mrjharian(int today_mrjharian) {
        this.today_mrjharian = today_mrjharian;
    }

    public int getToday_simak() {
        return today_simak;
    }

    public void setToday_simak(int today_simak) {
        this.today_simak = today_simak;
    }

    public int getStatus_setoran() {
        return status_setoran;
    }

    public void setStatus_setoran(int status_setoran) {
        this.status_setoran = status_setoran;
    }

    public int getP_no_suroh() {
        return p_no_suroh;
    }

    public void setP_no_suroh(int p_no_suroh) {
        this.p_no_suroh = p_no_suroh;
    }

    public int getP_ayat_begin() {
        return p_ayat_begin;
    }

    public void setP_ayat_begin(int p_ayat_begin) {
        this.p_ayat_begin = p_ayat_begin;
    }

    public int getP_ayat_end() {
        return p_ayat_end;
    }

    public void setP_ayat_end(int p_ayat_end) {
        this.p_ayat_end = p_ayat_end;
    }

    public int getMrjap_suroh_begin() {
        return mrjap_suroh_begin;
    }

    public void setMrjap_suroh_begin(int mrjap_suroh_begin) {
        this.mrjap_suroh_begin = mrjap_suroh_begin;
    }

    public int getMrjap_suroh_end() {
        return mrjap_suroh_end;
    }

    public void setMrjap_suroh_end(int mrjap_suroh_end) {
        this.mrjap_suroh_end = mrjap_suroh_end;
    }

    public int getMrj_ap_ayat_begin() {
        return mrj_ap_ayat_begin;
    }

    public void setMrj_ap_ayat_begin(int mrj_ap_ayat_begin) {
        this.mrj_ap_ayat_begin = mrj_ap_ayat_begin;
    }

    public int getMrj_ap_ayat_end() {
        return mrj_ap_ayat_end;
    }

    public void setMrj_ap_ayat_end(int mrj_ap_ayat_end) {
        this.mrj_ap_ayat_end = mrj_ap_ayat_end;
    }

    public int getMrjap_setor() {
        return mrjap_setor;
    }

    public void setMrjap_setor(int mrjap_setor) {
        this.mrjap_setor = mrjap_setor;
    }

    public int getMrjap_simak() {
        return mrjap_simak;
    }

    public void setMrjap_simak(int mrjap_simak) {
        this.mrjap_simak = mrjap_simak;
    }

    public int getIs_wajib_mrjharian() {
        return is_wajib_mrjharian;
    }

    public void setIs_wajib_mrjharian(int is_wajib_mrjharian) {
        this.is_wajib_mrjharian = is_wajib_mrjharian;
    }

    public int getMrjharian_page() {
        return mrjharian_page;
    }

    public void setMrjharian_page(int mrjharian_page) {
        this.mrjharian_page = mrjharian_page;
    }

    public EntityInfoKelas getInfoKelasOkupansi() {
        return infoKelasOkupansi;
    }

    public void setInfoKelasOkupansi(EntityInfoKelas infoKelasOkupansi) {
        this.infoKelasOkupansi = infoKelasOkupansi;
    }

    public List<EntitySetoran> getSetorans() {
        return setorans;
    }

    public void setSetorans(List<EntitySetoran> setorans) {
        this.setorans = setorans;
    }


    @Override
    public String toString() {
        return "EntityOkupansi{" +
                "id_db_okupansi=" + id_db_okupansi +
                ", id_okupansi='" + id_okupansi + '\'' +
                ", latest_update=" + latest_update +
                ", is_terisi=" + is_terisi +
                ", user_id=" + user_id +
                ", username_okupan='" + username_okupan + '\'' +
                ", nama_santri='" + nama_santri + '\'' +
                ", ghoib_frek=" + ghoib_frek +
                ", ghoib_kapan='" + ghoib_kapan + '\'' +
                ", udzur_frek=" + udzur_frek +
                ", udzur_kapan='" + udzur_kapan + '\'' +
                ", is_imtihan=" + is_imtihan +
                ", today_setoran=" + today_setoran +
                ", today_mrjharian=" + today_mrjharian +
                ", today_simak=" + today_simak +
                ", status_setoran=" + status_setoran +
                ", p_no_suroh=" + p_no_suroh +
                ", p_ayat_begin=" + p_ayat_begin +
                ", p_ayat_end=" + p_ayat_end +
                ", mrjap_suroh_begin=" + mrjap_suroh_begin +
                ", mrjap_suroh_end=" + mrjap_suroh_end +
                ", mrj_ap_ayat_begin=" + mrj_ap_ayat_begin +
                ", mrj_ap_ayat_end=" + mrj_ap_ayat_end +
                ", mrjap_setor=" + mrjap_setor +
                ", mrjap_simak=" + mrjap_simak +
                ", is_wajib_mrjharian=" + is_wajib_mrjharian +
                ", mrjharian_page=" + mrjharian_page +
                ", infoKelasOkupansi=" + infoKelasOkupansi +
                ", setorans=" + setorans +
                '}';
    }
}


