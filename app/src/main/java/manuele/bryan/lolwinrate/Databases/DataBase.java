package manuele.bryan.lolwinrate.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {
    Context context;

    final static int DB_VERSION = 1;
    final static String DB_NAME = "leaguedb.s3db";
    public static final String KEY_ID = "_id";

    public static final String KEY_TABLE_CHAMPIONS = "champions",
            KEY_CHAMPNAME = "name",
            KEY_CHAMPWINS = "wins",
            KEY_CHAMPLOSSES = "losses",
            KEY_CHAMPMATCHES = "matches";


    public DataBase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String championTable = "CREATE TABLE " + KEY_TABLE_CHAMPIONS + " ( " +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_CHAMPNAME + " TEXT NOT NULL," +
                KEY_CHAMPWINS + " TEXT NOT NULL," +
                KEY_CHAMPLOSSES + " TEXT NOT NULL," +
                KEY_CHAMPMATCHES + " TEXT NOT NULL)";
        db.execSQL(championTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //clears out all data like a boss
        db.execSQL("DROP TABLE IF EXISTS " + KEY_TABLE_CHAMPIONS);

        onCreate(db);
    }
}