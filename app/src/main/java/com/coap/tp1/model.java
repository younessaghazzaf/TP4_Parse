package com.coap.tp1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by controlberkani on 28/09/2015.
 */
public class model implements Parcelable {
    String name;
    String prenom;
    String ville;
    String date;

    public model(String name, String date, String ville, String prenom) {
        this.name = name;
        this.date = date;
        this.ville = ville;
        this.prenom = prenom;
    }

    public model(Parcel in) {
        String[] donnee=new String[4];
        in.readStringArray(donnee);
        this.name=donnee[0];
        this.prenom=donnee[1];
        ville=donnee[2];
        date=donnee[3];
    }

    public String getPrenom() {
        return prenom;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getVille() {
        return ville;
    }

    public String getDate() {
        return date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.name,
                this.prenom,
                this.ville,
                this.date});
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public model createFromParcel(Parcel in) {
            return new model(in);
        }

        public model[] newArray(int size) {
            return new model[size];
        }
    };
}
