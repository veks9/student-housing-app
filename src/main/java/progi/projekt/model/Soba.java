package progi.projekt.model;

import progi.projekt.model.enums.BrojKrevetaEnum;
import progi.projekt.model.enums.OznakeKategorijaEnum;
import progi.projekt.model.enums.TipKupaoniceEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Soba implements Serializable {
    @Id
    private int broj;

    @Id
    private int kat;

    @Id
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "id_paviljon"),
            @JoinColumn(name = "id_dom")
    })
    private Paviljon paviljon;

    @JoinColumn(name = "id_broj_kreveta")
    private BrojKreveta brojKreveta;

    @JoinColumn(name = "id_tip_kupaonice")
    private TipKupaonice tipKupaonice;

    @Column(name = "kategorija")
    @Enumerated(EnumType.STRING)
    private OznakeKategorijaEnum kategorija;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Soba soba = (Soba) o;
        return kat == soba.kat &&
                broj == soba.broj &&
                paviljon.equals(soba.paviljon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(broj, kat, paviljon);
    }

    public Soba() {
    }

    //Ništa ne smije biti null!
    public Soba(int brojSobe, int kat, Paviljon paviljon, BrojKreveta brojKreveta, TipKupaonice tipKupaonice, OznakeKategorijaEnum kategorija) {
        if (paviljon != null && brojKreveta != null && tipKupaonice != null && kategorija != null) {
            this.paviljon = paviljon;
            this.brojKreveta = brojKreveta;
            this.tipKupaonice = tipKupaonice;
            this.broj = brojSobe;
            this.kat = kat;
        } else {
            System.err.println("Ništa u kreaciji sobe ne smije biti null!");
        }
    }

    public int getBroj() {
        return broj;
    }

    public void setBroj(int broj) {
        this.broj = broj;
    }

    public int getKat() {
        return kat;
    }

    public void setKat(int kat) {
        this.kat = kat;
    }

    public Paviljon getPaviljon() {
        return paviljon;
    }

    public void setPaviljon(Paviljon paviljon) {
        this.paviljon = paviljon;
    }

    public BrojKreveta getBrojKreveta() {
        return brojKreveta;
    }

    public void setBrojKreveta(BrojKreveta brojKreveta) {
        this.brojKreveta = brojKreveta;
    }

    public TipKupaonice getTipKupaonice() {
        return tipKupaonice;
    }

    public void setTipKupaonice(TipKupaonice tipKupaonice) {
        this.tipKupaonice = tipKupaonice;
    }

    public OznakeKategorijaEnum getKategorija() {
        return kategorija;
    }

    public void setKategorija(OznakeKategorijaEnum kategorija) {
        this.kategorija = kategorija;
    }
}
