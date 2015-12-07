/*
* Realisé par : AGHAZZAF Youness et Trisna Eko WIYATNI
* */

package com.coap.tp1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;



public class MainActivity extends ActionBarActivity {
    /*
    * Initialise the views
    *
    * */
    Button btn;
    EditText edit1,edit2,edit3,edit4;
    LinearLayout linear;
    /*
    * Spinner
    * */
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        * Model Personne
        * */
        final model info_personne=new model("","","","");
        btn=(Button)findViewById(R.id.valider);

        edit1=(EditText)findViewById(R.id.edit1);
        edit2=(EditText)findViewById(R.id.edit2);
        edit3=(EditText)findViewById(R.id.edit3);
        edit4=(EditText)findViewById(R.id.edit4);
        spinner=(Spinner)findViewById(R.id.departement);
        linear=(LinearLayout)findViewById(R.id.conteneur);

        edit1=(EditText)linear.findViewById(R.id.edit1);
        /*
        * Button listener (Validate button)
        * */
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Hello", Toast.LENGTH_LONG).show();
                info_personne.setName(edit1.getText().toString());
                info_personne.setPrenom(edit2.getText().toString());
                info_personne.setDate(edit3.getText().toString());
                info_personne.setVille(edit4.getText().toString());
                Intent i = new Intent(getApplicationContext(), ListActivity.class);
                i.putExtra("info",info_personne);
                setResult(RESULT_OK,i);
                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id){
            case R.id.zero :
                edit1.setText("");
                edit2.setText("");
                edit3.setText("");
                edit4.setText("");
                break;
            case R.id.phone:
                View child = getLayoutInflater().inflate(R.layout.phone_layout, null);
                linear.addView(child);
                linear.invalidate();
                break;
            case R.id.info_dep:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://fr.wikipedia.org/wiki/"+spinner.getSelectedItem().toString()));
                startActivityForResult(intent, 0);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
