package ba.sum.fpmoz.smart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParkingMjesto {
    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("parking_space_name")
    private String naziv;

    @Expose
    @SerializedName("occupied")
    private int zauzeto;

    @Expose
    @SerializedName("lat")
    private String latituda;

    @Expose
    @SerializedName("lng")
    private String longituda;

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

    public int isZauzeto() {
        return zauzeto;
    }

    public void setZauzeto(int zauzeto) {
        this.zauzeto = zauzeto;
    }

    public String getLatituda() {
        return latituda;
    }

    public void setLatituda(String latituda) {
        this.latituda = latituda;
    }

    public String getLongituda() {
        return longituda;
    }

    public void setLongituda(String longituda) {
        this.longituda = longituda;
    }
}
