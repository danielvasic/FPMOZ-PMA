package ba.sum.fpmoz.vjebe05;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Korisnik {
    @PrimaryKey(autoGenerate = true)
    public int kid;

    @ColumnInfo(name="ime")
    public String ime;

    @ColumnInfo(name="prezime")
    public String prezime;
}

