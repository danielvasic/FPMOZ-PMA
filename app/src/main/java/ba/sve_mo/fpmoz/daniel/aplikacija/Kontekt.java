package ba.sve_mo.fpmoz.daniel.aplikacija;

/**
 * Created by Daniel on 21.11.2017..
 */

public class Kontekt {
    private int id;
    private String ime;
    private String prezime;
    private String email;

    public Kontekt() {
        this.id = 0;
        this.ime = "";
        this.prezime = "";
        this.email = "";
    }

    public Kontekt(int id, String ime, String prezime, String email) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
