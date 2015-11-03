/*
 * Andjety 2.0 - Code by Paul Coiffier - 2012/2013
 * 
 */
package com.mobissime.andjety.objects;

/**
 *
 * @author Paul Coiffier
 */
public class ScheduleTaskObj {

    private String libelleTask;
    private boolean ifalldays;
    private boolean iflundi;
    private boolean ifmardi;
    private boolean ifmercredi;
    private boolean ifjeudi;
    private boolean ifvendredi;
    private boolean ifsamedi;
    private boolean ifdimanche;
    private String minutes;
    private String heures;
    private boolean affichexec;

    public ScheduleTaskObj() {
    }

    public ScheduleTaskObj(String libelleTask, boolean ifalldays, boolean iflundi, boolean ifmardi, boolean ifmercredi, boolean ifjeudi, boolean ifvendredi, boolean ifsamedi, boolean ifdimanche, String minutes, String heures, boolean affichexec) {
        this.libelleTask = libelleTask;
        this.ifalldays = ifalldays;
        this.iflundi = iflundi;
        this.ifmardi = ifmardi;
        this.ifmercredi = ifmercredi;
        this.ifjeudi = ifjeudi;
        this.ifvendredi = ifvendredi;
        this.ifsamedi = ifsamedi;
        this.ifdimanche = ifdimanche;
        this.minutes = minutes;
        this.heures = heures;
        this.affichexec = affichexec;
    }

    public boolean isAffichexec() {
        return affichexec;
    }

    public void setAffichexec(boolean affichexec) {
        this.affichexec = affichexec;
    }

    public String getHeures() {
        return heures;
    }

    public void setHeures(String heures) {
        this.heures = heures;
    }

    public boolean isIfalldays() {
        return ifalldays;
    }

    public void setIfalldays(boolean ifalldays) {
        this.ifalldays = ifalldays;
    }

    public boolean isIfdimanche() {
        return ifdimanche;
    }

    public void setIfdimanche(boolean ifdimanche) {
        this.ifdimanche = ifdimanche;
    }

    public boolean isIfjeudi() {
        return ifjeudi;
    }

    public void setIfjeudi(boolean ifjeudi) {
        this.ifjeudi = ifjeudi;
    }

    public boolean isIflundi() {
        return iflundi;
    }

    public void setIflundi(boolean iflundi) {
        this.iflundi = iflundi;
    }

    public boolean isIfmardi() {
        return ifmardi;
    }

    public void setIfmardi(boolean ifmardi) {
        this.ifmardi = ifmardi;
    }

    public boolean isIfmercredi() {
        return ifmercredi;
    }

    public void setIfmercredi(boolean ifmercredi) {
        this.ifmercredi = ifmercredi;
    }

    public boolean isIfsamedi() {
        return ifsamedi;
    }

    public void setIfsamedi(boolean ifsamedi) {
        this.ifsamedi = ifsamedi;
    }

    public boolean isIfvendredi() {
        return ifvendredi;
    }

    public void setIfvendredi(boolean ifvendredi) {
        this.ifvendredi = ifvendredi;
    }

    public String getLibelleTask() {
        return libelleTask;
    }

    public void setLibelleTask(String libelleTask) {
        this.libelleTask = libelleTask;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }
}
