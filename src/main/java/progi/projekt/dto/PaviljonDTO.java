package progi.projekt.dto;

import progi.projekt.model.Paviljon;
import progi.projekt.model.enums.OznakeKategorijaEnum;

import java.util.UUID;

public class PaviljonDTO {
    private UUID id;
    private String naziv;
    private String nazivDom;
    private OznakeKategorijaEnum kategorija;
    private int brojKatova;

    public PaviljonDTO(Paviljon paviljon) {
        if (paviljon == null) return;
        this.id = paviljon.getId();
        this.naziv = paviljon.getNaziv();
        if (paviljon.getDom() != null) this.nazivDom = paviljon.getDom().getNaziv();
        this.kategorija = paviljon.getKategorija();
        this.brojKatova = paviljon.getBrojKatova();
    }

    public String getNazivDom() {
        return nazivDom;
    }

    public void setNazivDom(String nazivDom) {
        this.nazivDom = nazivDom;
    }

    public int getBrojKatova() {
        return brojKatova;
    }

    public void setBrojKatova(int brojKatova) {
        this.brojKatova = brojKatova;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public OznakeKategorijaEnum getKategorija() {
        return kategorija;
    }

    public void setKategorija(OznakeKategorijaEnum kategorija) {
        this.kategorija = kategorija;
    }
}
