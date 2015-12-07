package com.coap.tp1;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by controlberkani on 13/10/2015.
 */
public class PersonDb {
    public static final String Personne_ROWID = "_id";
    public static final String Name = "nom";
    public static final String Prenom = "prenom";
    public static final String age = "age";

    private static final String LOG_TAG = "PersonDb";
    public static final String SQLITE_TABLE = "person";

    private static final String DATABASE_CREATE =
            "CREATE TABLE " + SQLITE_TABLE + " (" + Personne_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT," + Name + " VARCHAR(255)," + Prenom + " VARCHAR(255)," +  age + " VARCHAR(255)" + ");";

    public static void onCreate(SQLiteDatabase db) {
        Log.w(LOG_TAG, DATABASE_CREATE);
        db.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);
        onCreate(db);
    }
}
