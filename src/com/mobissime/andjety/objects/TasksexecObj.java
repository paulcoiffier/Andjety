/*
 * Andjety 2.0 - Code by Paul Coiffier - 2012/2013
 * 
 */
package com.mobissime.andjety.objects;

/**
 *
 * @author Paul Coiffier
 */
public class TasksexecObj {

    private String libelleTask;
    private int idscheduletask;
    private String dateheure;
    private String resultat;
    private String logresult;
    private String logdetails;

    public TasksexecObj() {
    }

    public TasksexecObj(String libelleTask, int idscheduletask, String dateheure, String resultat, String logresult, String logdetails) {
        this.libelleTask = libelleTask;
        this.idscheduletask = idscheduletask;
        this.dateheure = dateheure;
        this.resultat = resultat;
        this.logresult = logresult;
        this.logdetails = logdetails;
    }

    public String getDateheure() {
        return dateheure;
    }

    public void setDateheure(String dateheure) {
        this.dateheure = dateheure;
    }

    public int getIdscheduletask() {
        return idscheduletask;
    }

    public void setIdscheduletask(int idscheduletask) {
        this.idscheduletask = idscheduletask;
    }

    public String getLibelleTask() {
        return libelleTask;
    }

    public void setLibelleTask(String libelleTask) {
        this.libelleTask = libelleTask;
    }

    public String getLogdetails() {
        return logdetails;
    }

    public void setLogdetails(String logdetails) {
        this.logdetails = logdetails;
    }

    public String getLogresult() {
        return logresult;
    }

    public void setLogresult(String logresult) {
        this.logresult = logresult;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }
}
