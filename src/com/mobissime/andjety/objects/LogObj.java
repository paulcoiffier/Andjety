/*
 * Andjety 3.0 - Paul Coiffier - 2012 - 2015
 * 
 */
package com.mobissime.andjety.objects;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Paul
 */
public class LogObj {

    private String logobj_taskLibelle;
    private Date logobj_dateexec;
    private String logobj_heuredeb;
    private String logobj_heurefin;
    private int logobj_nbServeurs;
    private int logobj_nbServeursErreur;
    private int logobj_nbServeursOk;
    private String logObj_dureeDesTraitements;
    private ArrayList<LogObjDetails> arrayOfLogsDetails;

    public LogObj() {
    }

    public LogObj(String logobj_taskLibelle, Date logobj_dateexec, String logobj_heuredeb, String logobj_heurefin, int logobj_nbServeurs, int logobj_nbServeursErreur, int logobj_nbServeursOk, String logObj_dureeDesTraitements, ArrayList<LogObjDetails> arrayOfLogsDetails) {
        this.logobj_taskLibelle = logobj_taskLibelle;
        this.logobj_dateexec = logobj_dateexec;
        this.logobj_heuredeb = logobj_heuredeb;
        this.logobj_heurefin = logobj_heurefin;
        this.logobj_nbServeurs = logobj_nbServeurs;
        this.logobj_nbServeursErreur = logobj_nbServeursErreur;
        this.logobj_nbServeursOk = logobj_nbServeursOk;
        this.logObj_dureeDesTraitements = logObj_dureeDesTraitements;
        this.arrayOfLogsDetails = arrayOfLogsDetails;
    }

    public String getLogObj_dureeDesTraitements() {
        return logObj_dureeDesTraitements;
    }

    public void setLogObj_dureeDesTraitements(String logObj_dureeDesTraitements) {
        this.logObj_dureeDesTraitements = logObj_dureeDesTraitements;
    }

    public int getLogobj_nbServeurs() {
        return logobj_nbServeurs;
    }

    public void setLogobj_nbServeurs(int logobj_nbServeurs) {
        this.logobj_nbServeurs = logobj_nbServeurs;
    }

    public int getLogobj_nbServeursErreur() {
        return logobj_nbServeursErreur;
    }

    public void setLogobj_nbServeursErreur(int logobj_nbServeursErreur) {
        this.logobj_nbServeursErreur = logobj_nbServeursErreur;
    }

    public int getLogobj_nbServeursOk() {
        return logobj_nbServeursOk;
    }

    public void setLogobj_nbServeursOk(int logobj_nbServeursOk) {
        this.logobj_nbServeursOk = logobj_nbServeursOk;
    }

    public ArrayList<LogObjDetails> getArrayOfLogsDetails() {
        return arrayOfLogsDetails;
    }

    public void setArrayOfLogsDetails(ArrayList<LogObjDetails> arrayOfLogsDetails) {
        this.arrayOfLogsDetails = arrayOfLogsDetails;
    }

    public Date getLogobj_dateexec() {
        return logobj_dateexec;
    }

    public void setLogobj_dateexec(Date logobj_dateexec) {
        this.logobj_dateexec = logobj_dateexec;
    }

    public String getLogobj_heuredeb() {
        return logobj_heuredeb;
    }

    public void setLogobj_heuredeb(String logobj_heuredeb) {
        this.logobj_heuredeb = logobj_heuredeb;
    }

    public String getLogobj_heurefin() {
        return logobj_heurefin;
    }

    public void setLogobj_heurefin(String logobj_heurefin) {
        this.logobj_heurefin = logobj_heurefin;
    }

    public String getLogobj_taskLibelle() {
        return logobj_taskLibelle;
    }

    public void setLogobj_taskLibelle(String logobj_taskLibelle) {
        this.logobj_taskLibelle = logobj_taskLibelle;
    }
}
