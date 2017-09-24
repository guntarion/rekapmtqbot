package com.tahfizhonline.entitydefinition;

import javax.persistence.*;

@Entity
@Table(name = "santripendaftar")
public class EntityPendaftar {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_pendaftar")
    private int idPendaftar;
    @Column(name="nama_pendaftar")
    private String namaPendaftar;
    @Column(name="username")
    private String username;
    @Column(name="gender")
    private int gender;
    @Column(name="tahun_lahir")
    private int tahunLahir;
    @Column(name="kelulusan")
    private int kelulusan;

    public EntityPendaftar() {

    }

    public EntityPendaftar(String namaPendaftar, String username, int gender, int tahunLahir) {
        this.namaPendaftar = namaPendaftar;
        this.username = username;
        this.gender = gender;
        this.tahunLahir = tahunLahir;
        this.kelulusan = 0;
    }

    public int getIdPendaftar() {
        return idPendaftar;
    }

    public void setIdPendaftar(int idPendaftar) {
        this.idPendaftar = idPendaftar;
    }

    public String getNamaPendaftar() {
        return namaPendaftar;
    }

    public void setNamaPendaftar(String namaPendaftar) {
        this.namaPendaftar = namaPendaftar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getTanggal_lahir() {
        return tahunLahir;
    }

    public void setTanggal_lahir(int tahun_lahir) {
        this.tahunLahir = tahun_lahir;
    }

    public int getKelulusan() {
        return kelulusan;
    }

    public void setKelulusan(int kelulusan) {
        this.kelulusan = kelulusan;
    }

    @Override
    public String toString() {
        return "EntityPendaftar{" +
                "idPendaftar=" + idPendaftar +
                ", namaPendaftar='" + namaPendaftar + '\'' +
                ", username='" + username + '\'' +
                ", gender=" + gender +
                ", tahun_lahir=" + tahunLahir +
                ", kelulusan=" + kelulusan +
                '}';
    }
}
