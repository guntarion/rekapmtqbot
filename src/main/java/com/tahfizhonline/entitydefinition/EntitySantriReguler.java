package com.tahfizhonline.entitydefinition;

import javafx.scene.layout.Pane;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "santrireguler")
public class EntitySantriReguler {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_db_santri")
    private int id_db_santri;

    private String id_mtq;
    private long user_id;
    private String nama_santri;
    private String nama_kunyah;
    private String nomer_hp;
    private String email;
    private String gender;
    private String username;
    private int tahun_lahir;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_santri_detail")
    private EntitySantriRegulerStats detailStatsSantri;

//    @OneToMany(mappedBy = "santriReguler", cascade = CascadeType.ALL)
//    private List<EntitySetoran> setorans;

    public EntitySantriReguler() {

    }

    public EntitySantriReguler(long user_id, String id_mtq, String nama_santri, String nama_kunyah, String nomer_hp, String gender, String username, int tahun_lahir) {
        this.user_id = user_id;
        this.nama_santri = nama_santri;
        this.nama_kunyah = nama_kunyah;
        this.id_mtq = id_mtq;
        this.nomer_hp = nomer_hp;
        this.gender = gender;
        this.username = username;
        this.tahun_lahir = tahun_lahir;
    }

    public int getId_db_santri() {
        return id_db_santri;
    }

    public void setId_db_santri(int id_db_santri) {
        this.id_db_santri = id_db_santri;
    }

    public String getId_mtq() {
        return id_mtq;
    }

    public void setId_mtq(String id_mtq) {
        this.id_mtq = id_mtq;
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

    public String getNama_kunyah() {
        return nama_kunyah;
    }

    public void setNama_kunyah(String nama_kunyah) {
        this.nama_kunyah = nama_kunyah;
    }

    public String getNomer_hp() {
        return nomer_hp;
    }

    public void setNomer_hp(String nomer_hp) {
        this.nomer_hp = nomer_hp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTahun_lahir() {
        return tahun_lahir;
    }

    public void setTahun_lahir(int tahun_lahir) {
        this.tahun_lahir = tahun_lahir;
    }

    public EntitySantriRegulerStats getDetailStatsSantri() {
        return detailStatsSantri;
    }

    public void setDetailStatsSantri(EntitySantriRegulerStats detailStatsSantri) {
        this.detailStatsSantri = detailStatsSantri;
    }
}
