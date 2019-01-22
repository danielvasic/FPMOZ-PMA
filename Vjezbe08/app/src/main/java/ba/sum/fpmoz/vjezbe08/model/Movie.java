package ba.sum.fpmoz.vjezbe08.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Movie {
    public String naziv;
    public String opis;
    public String slika;
    public Float prosjecnaOcjena;

    public Movie() {
    }

    public Movie(String naziv, String opis, String slika, Float prosjecnaOcjena) {
        this.naziv = naziv;
        this.opis = opis;
        this.slika = slika;
        this.prosjecnaOcjena = prosjecnaOcjena;
    }
}
