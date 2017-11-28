package ba.sve_mo.fpmoz.daniel.aplikacija;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteTransactionListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 21.11.2017..
 */

public class KontektiDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME ="kontektiDB";
    private static final String TABLE_NAME = "kontekti";
    private static final int DATABASE_VERSION = 1;

    public KontektiDB(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "ime VARCHAR(20),"
                + "prezime VARCHAR(20),"
                + "email TEXT"
                + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void dodajKontekt (Kontekt k) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ime", k.getIme());
        cv.put("prezime", k.getPrezime());
        cv.put("email", k.getEmail());
        db.insert(TABLE_NAME, null, cv);
        db.close();
    }

    public List<Kontekt> dajSveKontekte () {
        List <Kontekt> kontekti = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String upit = "SELECT * FROM " + TABLE_NAME;

        Cursor c = db.rawQuery(upit, null);

        if (c.moveToFirst()) {
            do {
                kontekti.add(new Kontekt(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3)
                ));
            } while (c.moveToNext());
        }
        return kontekti;
    }
}
