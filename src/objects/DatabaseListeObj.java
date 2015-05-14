/*
 * Andjety 2.0 - Code by Paul Coiffier - 2012/2013
 * 
 */
package objects;

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
