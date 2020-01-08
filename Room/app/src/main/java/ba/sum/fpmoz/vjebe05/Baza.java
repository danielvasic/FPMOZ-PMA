package ba.sum.fpmoz.vjebe05;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Korisnik.class}, version = 2)
public abstract class Baza extends RoomDatabase {
    public abstract Korisnici korisnici();
}
