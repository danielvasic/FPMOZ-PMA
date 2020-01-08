package ba.sum.fpmoz.mojapravaaplikacijabaza;

import android.provider.BaseColumns;

public final class Knjznica {
    private Knjznica(){}

    public static class Knjiga implements BaseColumns{
        public static final String TABLE_NAME = "knjiga";
        public static final String COLUMN_NAZIV = "naziv";
        public static final String COLUMN_AUTOR = "autor";
        public static final String COLUMN_ISBN = "ISBN";
    }


    public static final String SQL_CREATE = "CREATE TABLE " + Knjiga.TABLE_NAME + "(" +
            Knjiga._ID + " PRIMARY KEY, " +
            Knjiga.COLUMN_NAZIV + " TEXT, " +
            Knjiga.COLUMN_AUTOR + " TEXT, " +
            Knjiga.COLUMN_ISBN + " TEXT" +
            ");";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Knjiga.TABLE_NAME;
}
