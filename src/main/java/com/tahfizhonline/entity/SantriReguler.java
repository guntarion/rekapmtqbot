package com.tahfizhonline.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "santrireguler")
public class SantriReguler {

    @Id
    @Column(name="id_santri")
    private int id_sanreg;

    private String nama;

    private String username;


    public SantriReguler(int id_sanreg, String nama, String username) {
        this.id_sanreg = id_sanreg;
        this.nama = nama;
        this.username = username;
    }

    public int getId_sanreg() {
        return id_sanreg;
    }

    public void setId_sanreg(int id_sanreg) {
        this.id_sanreg = id_sanreg;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
