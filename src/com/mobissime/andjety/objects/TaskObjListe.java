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
public class TaskObjListe {

    private ArrayList<TaskObj> dbObjListe;

    public TaskObjListe() {
    }

    public TaskObjListe(ArrayList<TaskObj> dbObjListe) {
        this.dbObjListe = dbObjListe;
    }

    public ArrayList<TaskObj> getDbObjListe() {
        return dbObjListe;
    }

    public void setDbObjListe(ArrayList<TaskObj> dbObjListe) {
        this.dbObjListe = dbObjListe;
    }
}
