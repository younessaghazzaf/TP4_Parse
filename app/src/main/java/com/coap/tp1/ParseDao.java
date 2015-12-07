package com.coap.tp1;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

/**
 * Created by youness on 21/11/15.
 */
public class ParseDao{

    static Cursor getClients(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        MatrixCursor cursor=new MatrixCursor(projection);
        List<ParseObject> list;
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Client");
        try {
            list = query.find();
            int i=0;
            while(i<list.size()){
                cursor.addRow(new String[]{list.get(i).getObjectId(),list.get(i).get("nom").toString(),list.get(i).get("prenom").toString(),list.get(i).get("age").toString()});
                i++;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cursor;
    }
    static void insert(ContentValues contentValues){
        ParseObject parseObject = new ParseObject("Client");
        parseObject.put("nom", contentValues.get("nom"));
        parseObject.put("prenom",contentValues.get("prenom"));
        parseObject.put("age",contentValues.get("age"));
        parseObject.saveInBackground();
    }
}
