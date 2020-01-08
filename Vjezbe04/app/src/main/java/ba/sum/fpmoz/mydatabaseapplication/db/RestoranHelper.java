package ba.sum.fpmoz.mydatabaseapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RestoranHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "restoran.db";
    public static final int DB_VERSION = 1;

    public RestoranHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Restoran.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Restoran.SQL_DELETE_ENTRIES);
        db.execSQL(Restoran.SQL_CREATE_ENTRIES);
    }
}
