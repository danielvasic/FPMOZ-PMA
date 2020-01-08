package ba.sum.fpmoz.daniel.java;

public class Radnik extends Osoba {
    private float placa;

    public Radnik(String ime, String prezime, float placa) {
        super(ime, prezime);
        this.placa = placa;
    }

    @Override
    public String toString() {
        return "Ja sam radnik pod imenom "+ this.getIme() + " " + this.getPrezime()
                + ", placa mi je " + this.placa;
    }
}
