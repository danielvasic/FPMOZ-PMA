package ba.sum.fpmoz.vj06.model;

import com.orm.SugarRecord;

public class Napomena extends SugarRecord {
    private String naslov;
    private String napomena;

    public Napomena() {
    }

    public Napomena(String naslov, String napomena) {
        this.naslov = naslov;
        this.napomena = napomena;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }
}
