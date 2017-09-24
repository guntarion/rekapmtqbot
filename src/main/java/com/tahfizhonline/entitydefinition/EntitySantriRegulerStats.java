package com.tahfizhonline.entitydefinition;

import javax.persistence.*;

@Entity
@Table(name = "santriregulerstats")
public class EntitySantriRegulerStats {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_db_stats")
    private int id_db_stats;
    private int udzur_total;
    private float ghoib_total;
    private int suroh_terakhir;
    private int ayat_terakhir;

    // Bila tidak ingin yg SantriReguler ikut dihapus, maka gunakan:
    // cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    // Association antar dua tabel harus dihapus, lihat Operasi
    @OneToOne(mappedBy = "detailStatsSantri", cascade = CascadeType.ALL)
    private EntitySantriReguler santriReguler;


    public EntitySantriRegulerStats() {
    }

    public EntitySantriRegulerStats(int udzur_total, float ghoib_total, int suroh_terakhir, int ayat_terakhir) {
        this.udzur_total = udzur_total;
        this.ghoib_total = ghoib_total;
        this.suroh_terakhir = suroh_terakhir;
        this.ayat_terakhir = ayat_terakhir;
    }

    @Override
    public String toString() {
        return "EntitySantriRegulerStats{" +
                "id_db_stats=" + id_db_stats +
                ", udzur_total=" + udzur_total +
                ", ghoib_total=" + ghoib_total +
                ", suroh_terakhir=" + suroh_terakhir +
                ", ayat_terakhir=" + ayat_terakhir +
                '}';
    }


    public int getUdzur_total() {
        return udzur_total;
    }

    public void setUdzur_total(int udzur_total) {
        this.udzur_total = udzur_total;
    }

    public float getGhoib_total() {
        return ghoib_total;
    }

    public void setGhoib_total(float ghoib_total) {
        this.ghoib_total = ghoib_total;
    }

    public int getSuroh_terakhir() {
        return suroh_terakhir;
    }

    public void setSuroh_terakhir(int suroh_terakhir) {
        this.suroh_terakhir = suroh_terakhir;
    }

    public int getAyat_terakhir() {
        return ayat_terakhir;
    }

    public void setAyat_terakhir(int ayat_terakhir) {
        this.ayat_terakhir = ayat_terakhir;
    }

    public int getId_db_stats() {
        return id_db_stats;
    }

    public void setId_db_stats(int id_db_stats) {
        this.id_db_stats = id_db_stats;
    }

    public EntitySantriReguler getSantriReguler() {
        return santriReguler;
    }

    public void setSantriReguler(EntitySantriReguler santriReguler) {
        this.santriReguler = santriReguler;
    }
}
