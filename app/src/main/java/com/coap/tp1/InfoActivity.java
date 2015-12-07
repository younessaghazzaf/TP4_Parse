package com.coap.tp1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by controlberkani on 28/09/2015.
 */
public class InfoActivity extends Activity {
       TextView nom;
       TextView prenom;
       TextView date;
       TextView ville;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Bundle b=getIntent().getExtras();
        model personne = (model)b.getParcelable("info");
        nom=(TextView)findViewById(R.id.name);
        prenom=(TextView)findViewById(R.id.prenom);
        date=(TextView)findViewById(R.id.date);
        ville=(TextView)findViewById(R.id.ville);
        nom.setText(personne.getName());
        prenom.setText(personne.getPrenom());
        date.setText(personne.getDate());
        ville.setText(personne.getVille());

    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state

        super.onSaveInstanceState(savedInstanceState);
    }
}
