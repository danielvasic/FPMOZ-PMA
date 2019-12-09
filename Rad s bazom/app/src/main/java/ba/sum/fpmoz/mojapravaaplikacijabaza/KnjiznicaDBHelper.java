package ba.sum.fpmoz.mojapravaaplikacijabaza;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class KnjiznicaDBHelper extends SQLiteOpenHelper {
    private static final int VERZIJA = 1;
    private static final String NAZIV = "knjiznica.db";

    public KnjiznicaDBHelper(Context context) {
        super(context, NAZIV, null, VERZIJA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Knjznica.SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Knjznica.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
