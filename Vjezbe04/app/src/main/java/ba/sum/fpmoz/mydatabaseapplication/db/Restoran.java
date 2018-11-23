package ba.sum.fpmoz.mydatabaseapplication.db;

import android.provider.BaseColumns;

public final class Restoran {
    private Restoran () {}

    public static final
        String SQL_CREATE_ENTRIES = "CREATE TABLE " + Korisnik.TABLE_NAME + "(" +
            Korisnik._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            Korisnik.COLUMN_NAME_FIRSTNAME + " VARCHAR(50),"+
            Korisnik.COLUMN_NAME_LASTNAME + " VARCHAR(50),"+
            Korisnik.COLUMN_NAME_EMAIL + " VARCHAR(120),"+
            Korisnik.COLUMN_NAME_PASSWORD + " VARCHAR(256)"
            + ");";

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " +
            Korisnik.TABLE_NAME;

    public static class Korisnik implements BaseColumns {
        public static final String TABLE_NAME = "korisnik";
        public static final String COLUMN_NAME_FIRSTNAME = "firstname";
        public static final String COLUMN_NAME_LASTNAME = "lastname";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PASSWORD = "password";
    }
}
