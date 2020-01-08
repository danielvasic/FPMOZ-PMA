package ba.sum.fpmoz.vjezbe07.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Movie {
    public String naziv;
    public String opis;
    public String slika;

    public Movie() {
    }

    public Movie(String naziv, String opis, String slika) {
        this.naziv = naziv;
        this.opis = opis;
        this.slika = slika;
    }
}
