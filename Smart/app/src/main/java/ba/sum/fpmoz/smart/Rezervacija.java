package ba.sum.fpmoz.smart;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Rezervacija {
    public long vrijemeOD;
    public long vrijemeDO;
    public String registracija;

    public Rezervacija(long vrijemeOD, long vrijemeDO, String registracija) {
        this.vrijemeOD = vrijemeOD;
        this.vrijemeDO = vrijemeDO;
        this.registracija = registracija;
    }

    public String getRegistracija() {
        return registracija;
    }

    public void setRegistracija(String registracija) {
        this.registracija = registracija;
    }

    public Rezervacija() {}

    public Rezervacija(long vrijemeOD, long vrijemeDO) {
        this.vrijemeOD = vrijemeOD;
        this.vrijemeDO = vrijemeDO;
    }

    public long getVrijemeOD() {
        return vrijemeOD;
    }

    public void setVrijemeOD(long vrijemeOD) {
        this.vrijemeOD = vrijemeOD;
    }

    public long getVrijemeDO() {
        return vrijemeDO;
    }

    public void setVrijemeDO(long vrijemeDO) {
        this.vrijemeDO = vrijemeDO;
    }

    public String getVrijemeODString(){
        Date date = new Date(this.vrijemeOD);
        Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public String getVrijemeDOString(){
        Date date = new Date(this.vrijemeDO);
        Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    @Override
    public String toString() {
        return "Rezervacija{" +
                "vrijemeOD=" + vrijemeOD +
                ", vrijemeDO=" + vrijemeDO +
                '}';
    }
}
