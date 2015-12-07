package com.coap.tp1;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

/**
 * Created by controlberkani on 13/10/2015.
 */
public class LibraryContentProvider extends ContentProvider {
    Activity activity;
    private static final String AUTHORITY = "com.coap.tp1.personne";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String CONTENT_PROVIDER_MIME = "vnd.android.cursor.item/vnd.tutos.android.content.provider.personne";
    @Override
    public boolean onCreate() {

        return false;
    }
    public void setActivity(Activity ac){
        this.activity=ac;
    }
    private long getId(Uri uri) {
        String lastPathSegment = uri.getLastPathSegment();
        if (lastPathSegment != null) {
            try {
                return Long.parseLong(lastPathSegment);
            } catch (NumberFormatException e) {
                Log.e("TutosAndroidProvider", "Number Format Exception : " + e);
            }
        }
        return -1;
    }
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
            long id = getId(uri);
            if (id < 0) {
                return ParseDao.getClients(uri,projection,selection,selectionArgs,sortOrder);
            } else {
                return ParseDao.getClients(uri,projection,selection,selectionArgs,sortOrder);
            }
    }

    @Override
    public String getType(Uri uri) {
        return CONTENT_PROVIDER_MIME;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
       // SQLiteDatabase db = dbHelper.getWritableDatabase();
        ParseDao.insert(values);
        return ContentUris.withAppendedId(uri, 1);

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
