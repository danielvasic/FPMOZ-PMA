package ba.sum.fpmoz.vjebe05;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;


@Dao
public interface Korisnici {
    @Query("SELECT * FROM korisnik")
    List<Korisnik> listAll();

    @Insert
    void insert(Korisnik korisnik);

    @Delete
    void delete(Korisnik korisnik);
}
