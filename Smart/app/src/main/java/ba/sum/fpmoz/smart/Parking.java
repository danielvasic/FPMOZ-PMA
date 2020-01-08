package ba.sum.fpmoz.smart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Parking {
    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("name")
    private String naziv;

    @Expose
    @SerializedName("address")
    private String adresa;

    @Expose
    @SerializedName("normal_occupied")
    private int zauzeto;

    @Expose
    @SerializedName("normal_available")
    private int slobodno;

    @Expose
    @SerializedName("capacity_normal")
    private int ukupno;

    @Expose
    @SerializedName("lat")
    private double latituda;

    @Expose
    @SerializedName("lng")
    private double longituda;

    @Expose
    @SerializedName("parkingSpaces")
    private ArrayList<ParkingMjesto> parkingMjesta;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public int getZauzeto() {
        return zauzeto;
    }

    public void setZauzeto(int zauzeto) {
        this.zauzeto = zauzeto;
    }

    public int getSlobodno() {
        return slobodno;
    }

    public void setSlobodno(int slobodno) {
        this.slobodno = slobodno;
    }

    public int getUkupno() {
        return ukupno;
    }

    public void setUkupno(int ukupno) {
        this.ukupno = ukupno;
    }

    public ArrayList<ParkingMjesto> getParkingMjesta() {
        return parkingMjesta;
    }

    public void setParkingMjesta(ArrayList<ParkingMjesto> parkingMjesta) {
        this.parkingMjesta = parkingMjesta;
    }

    public double getLatituda() {
        return latituda;
    }

    public void setLatituda(double latituda) {
        this.latituda = latituda;
    }

    public double getLongituda() {
        return longituda;
    }

    public void setLongituda(double longituda) {
        this.longituda = longituda;
    }
}
