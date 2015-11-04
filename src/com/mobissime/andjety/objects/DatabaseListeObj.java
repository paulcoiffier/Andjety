/*
 * Andjety 3.0 - Paul Coiffier - 2012 - 2015
 * 
 */
package com.mobissime.andjety.objects;

import java.util.ArrayList;

/**
 *
 * @author Paul Coiffier
 */
public class DatabaseListeObj {

    private ArrayList<DatabaseObj> dbListe;

    public DatabaseListeObj() {
    }

    public DatabaseListeObj(ArrayList<DatabaseObj> dbListe) {
        this.dbListe = dbListe;
    }

    public ArrayList<DatabaseObj> getDbListe() {
        return dbListe;
    }

    public void setDbListe(ArrayList<DatabaseObj> dbListe) {
        this.dbListe = dbListe;
    }
}
