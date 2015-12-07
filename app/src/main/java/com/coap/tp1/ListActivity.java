package com.coap.tp1;

import android.app.Activity;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.DataSetObserver;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.parse.Parse;


public class ListActivity extends Activity{
    private MyWebRequestReceiver receiver;
    Button add_user,sync;
    private ArrayList<HashMap<String, String>> listItem;
    ListView listview;
    SimpleAdapter adapter;
    HashMap<String, String> map;
    Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items);
        /* Exercice 1*/
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "nD4uSBWhhqR4PIV1vUSEyUp1m3rNDA77YUPhx2Z8", "pkipVlUSioV4w07CEiYD5XOL214ou4yCL2yZvwDr");

        IntentFilter filter = new IntentFilter(MyWebRequestReceiver.PROCESS_RESPONSE);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        receiver = new MyWebRequestReceiver();
        registerReceiver(receiver, filter);
        listview = (ListView)findViewById(R.id.listView);
        intent = new Intent(this, ParseService.class);

        //Lancer le service qui s'occupe de la synchronisation avec les données sur le serveur (Exercice 3 "Extra")
        startService(intent);

        listItem = new ArrayList<HashMap<String, String>>();
        adapter = new SimpleAdapter(this.getBaseContext(), listItem, R.layout.listitems,
                new String[]{"img", "nom", "prenom"}, new int[]{R.id.img, R.id.itemsname, R.id.itemsprenom});

        recoverData();
        listview.setAdapter(adapter);

        add_user = (Button)findViewById(R.id.addUser);
        add_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(i, 0);
            }
        });
        /*----------*/

        /*Exercice 2*/
        sync = (Button)findViewById(R.id.sync);
        sync.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                recoverData();
            }
        });
        /*----------------*/
    }

    // Récuperer donnée sur le serveur
    public void recoverData(){
        String columns[] = new String[] {"id",PersonDb.Name, PersonDb.Prenom,PersonDb.age };
        Uri mContacts = LibraryContentProvider.CONTENT_URI;
        Cursor cur = getContentResolver().query(mContacts, columns, null, null, null);
        cur.moveToFirst();
        listItem.clear();
        adapter.notifyDataSetChanged();
        boolean found = false;
        if (cur.moveToFirst()) {
            String name = null;
            do {
                map = new HashMap<String, String>();
                map.put("id",cur.getString(cur.getColumnIndex("id")));
                map.put("nom",cur.getString(cur.getColumnIndex(PersonDb.Name)));
                map.put("prenom",cur.getString(cur.getColumnIndex(PersonDb.Prenom)));
                map.put("img", String.valueOf(R.drawable.ic_launcher));
                listItem.add(map);
            } while (cur.moveToNext());
        }
    }

    // Sauvegarder donnée sur le serveur
    public void savePersonne(model personne){
        ContentValues contact = new ContentValues();
        contact.put(PersonDb.Name, personne.getName());
        contact.put(PersonDb.Prenom, personne.getPrenom());
        contact.put(PersonDb.age, Integer.parseInt(personne.getDate()));
        this.getContentResolver().insert(LibraryContentProvider.CONTENT_URI, contact);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        Bundle extras = intent.getExtras();
        if(!(extras==null)){
            model personne = (model)extras.getParcelable("info");
            //Save
            savePersonne(personne);
            map = new HashMap<String, String>();
            map.put("nom",personne.getName());
            map.put("prenom",personne.getPrenom());
            map.put("img", String.valueOf(R.drawable.ic_launcher));
            listItem.add(map);
            adapter.notifyDataSetChanged();
        }

    }
    @Override
    public void onStop (){
        super.onStop();
        stopService(intent);
        unregisterReceiver(receiver);
    }

    public class MyWebRequestReceiver extends BroadcastReceiver{

        public static final String PROCESS_RESPONSE = "com.coap.tp1.intent.action.PROCESS_RESPONSE";

        @Override
        public void onReceive(Context context, Intent intent) {
            recoverData();
            Log.d("info","Update");
        }

        }
}
