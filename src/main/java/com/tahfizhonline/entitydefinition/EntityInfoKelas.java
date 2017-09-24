package com.tahfizhonline.entitydefinition;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "infokelas")
public class EntityInfoKelas {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_db_kelas;
    @NaturalId
    private String nama_kelas;
    private String alamat_kelas;
    private int status_kelas;
    private String kategori_kelas;
    private String korlas;
    private String musyrif;
    private int udzur_bulanan;
    private int ghoib_bulanan;
    private String udzur_orangnya;
    private String ghoib_orangnya;

    @OneToMany(mappedBy = "infoKelasOkupansi", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EntityOkupansi> okupansiMTQ;

    public EntityInfoKelas() {

    }

    public EntityInfoKelas(String nama_kelas, String alamat_kelas, int status_kelas, String kategori_kelas, String korlas, String musyrif, int udzur_bulanan, int ghoib_bulanan, String udzur_orangnya, String ghoib_orangnya) {
        this.nama_kelas = nama_kelas;
        this.alamat_kelas = alamat_kelas;
        this.status_kelas = status_kelas;
        this.kategori_kelas = kategori_kelas;
        this.korlas = korlas;
        this.musyrif = musyrif;
        this.udzur_bulanan = udzur_bulanan;
        this.ghoib_bulanan = ghoib_bulanan;
        this.udzur_orangnya = udzur_orangnya;
        this.ghoib_orangnya = ghoib_orangnya;
    }

    public void tambahkan(EntityOkupansi tempOkupansi) {
        if (okupansiMTQ == null) {
            okupansiMTQ = new ArrayList<>();
        }
        okupansiMTQ.add(tempOkupansi);
        tempOkupansi.setInfoKelasOkupansi(this);
    }


    public int getId_db_kelas() {
        return id_db_kelas;
    }

    public void setId_db_kelas(int id_db_kelas) {
        this.id_db_kelas = id_db_kelas;
    }

    public String getNama_kelas() {
        return nama_kelas;
    }

    public void setNama_kelas(String nama_kelas) {
        this.nama_kelas = nama_kelas;
    }

    public String getAlamat_kelas() {
        return alamat_kelas;
    }

    public void setAlamat_kelas(String alamat_kelas) {
        this.alamat_kelas = alamat_kelas;
    }

    public int getStatus_kelas() {
        return status_kelas;
    }

    public void setStatus_kelas(int status_kelas) {
        this.status_kelas = status_kelas;
    }

    public String getKategori_kelas() {
        return kategori_kelas;
    }

    public void setKategori_kelas(String kategori_kelas) {
        this.kategori_kelas = kategori_kelas;
    }

    public String getKorlas() {
        return korlas;
    }

    public void setKorlas(String korlas) {
        this.korlas = korlas;
    }

    public String getMusyrif() {
        return musyrif;
    }

    public void setMusyrif(String musyrif) {
        this.musyrif = musyrif;
    }

    public int getUdzur_bulanan() {
        return udzur_bulanan;
    }

    public void setUdzur_bulanan(int udzur_bulanan) {
        this.udzur_bulanan = udzur_bulanan;
    }

    public int getGhoib_bulanan() {
        return ghoib_bulanan;
    }

    public void setGhoib_bulanan(int ghoib_bulanan) {
        this.ghoib_bulanan = ghoib_bulanan;
    }

    public String getUdzur_orangnya() {
        return udzur_orangnya;
    }

    public void setUdzur_orangnya(String udzur_orangnya) {
        this.udzur_orangnya = udzur_orangnya;
    }

    public String getGhoib_orangnya() {
        return ghoib_orangnya;
    }

    public void setGhoib_orangnya(String ghoib_orangnya) {
        this.ghoib_orangnya = ghoib_orangnya;
    }

    public List<EntityOkupansi> getOkupansiMTQ() {
        return okupansiMTQ;
    }

    public void setOkupansiMTQ(List<EntityOkupansi> okupansiMTQ) {
        this.okupansiMTQ = okupansiMTQ;
    }

    @Override
    public String toString() {
        return "EntityInfoKelas{" +
                "id_db_kelas=" + id_db_kelas +
                ", nama_kelas='" + nama_kelas + '\'' +
                ", alamat_kelas='" + alamat_kelas + '\'' +
                ", status_kelas=" + status_kelas +
                ", kategori_kelas=" + kategori_kelas +
                ", korlas='" + korlas + '\'' +
                ", musyrif='" + musyrif + '\'' +
                ", udzur_bulanan=" + udzur_bulanan +
                ", ghoib_bulanan=" + ghoib_bulanan +
                ", udzur_orangnya='" + udzur_orangnya + '\'' +
                ", ghoib_orangnya='" + ghoib_orangnya + '\'' +
//                ", okupansiMTQ=" + okupansiMTQ +
                '}';
    }
}
