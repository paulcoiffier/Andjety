
/*
 * Andjety 3.0 - Paul Coiffier - 2012 - 2015
 * 
 */
package com.mobissime.andjety.objects;

import java.util.Date;

/**
 *
 * @author Paul
 */
public class LogObjDetails {

    private String logobjd_database;
    private String logobj_resultat;
    private Date logobj_date;
    private String logobj_heuredeb;
    private String logobj_heurefin;

    public LogObjDetails() {
    }

    public LogObjDetails(String logobjd_database, String logobj_resultat, Date logobj_date, String logobj_heuredeb, String logobj_heurefin) {
        this.logobjd_database = logobjd_database;
        this.logobj_resultat = logobj_resultat;
        this.logobj_date = logobj_date;
        this.logobj_heuredeb = logobj_heuredeb;
        this.logobj_heurefin = logobj_heurefin;
    }

    public Date getLogobj_date() {
        return logobj_date;
    }

    public void setLogobj_date(Date logobj_date) {
        this.logobj_date = logobj_date;
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

    public String getLogobj_resultat() {
        return logobj_resultat;
    }

    public void setLogobj_resultat(String logobj_resultat) {
        this.logobj_resultat = logobj_resultat;
    }

    public String getLogobjd_database() {
        return logobjd_database;
    }

    public void setLogobjd_database(String logobjd_database) {
        this.logobjd_database = logobjd_database;
    }
}
