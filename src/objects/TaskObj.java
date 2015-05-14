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
public class TaskObj {

    private String TASK_AFFICHEXEC;
    private String TASK_LIBELLE;
    private String TASK_REQUETE;
    private String TASK_RESULTAT;
    private String TASK_TYPE;
    private ArrayList<DatabaseObj> TASK_SERVERSLIST;

    public TaskObj() {
    }

    public TaskObj(String TASK_AFFICHEXEC, String TASK_LIBELLE, String TASK_REQUETE, String TASK_RESULTAT, String TASK_TYPE, ArrayList<DatabaseObj> TASK_SERVERSLIST) {
        this.TASK_AFFICHEXEC = TASK_AFFICHEXEC;
        this.TASK_LIBELLE = TASK_LIBELLE;
        this.TASK_REQUETE = TASK_REQUETE;
        this.TASK_RESULTAT = TASK_RESULTAT;
        this.TASK_TYPE = TASK_TYPE;
        this.TASK_SERVERSLIST = TASK_SERVERSLIST;
    }

    public ArrayList<DatabaseObj> getTASK_SERVERSLIST() {
        return TASK_SERVERSLIST;
    }

    public void setTASK_SERVERSLIST(ArrayList<DatabaseObj> TASK_SERVERSLIST) {
        this.TASK_SERVERSLIST = TASK_SERVERSLIST;
    }

    public String getTASK_AFFICHEXEC() {
        return TASK_AFFICHEXEC;
    }

    public void setTASK_AFFICHEXEC(String TASK_AFFICHEXEC) {
        this.TASK_AFFICHEXEC = TASK_AFFICHEXEC;
    }

    public String getTASK_LIBELLE() {
        return TASK_LIBELLE;
    }

    public void setTASK_LIBELLE(String TASK_LIBELLE) {
        this.TASK_LIBELLE = TASK_LIBELLE;
    }

    public String getTASK_REQUETE() {
        return TASK_REQUETE;
    }

    public void setTASK_REQUETE(String TASK_REQUETE) {
        this.TASK_REQUETE = TASK_REQUETE;
    }

    public String getTASK_RESULTAT() {
        return TASK_RESULTAT;
    }

    public void setTASK_RESULTAT(String TASK_RESULTAT) {
        this.TASK_RESULTAT = TASK_RESULTAT;
    }

    public String getTASK_TYPE() {
        return TASK_TYPE;
    }

    public void setTASK_TYPE(String TASK_TYPE) {
        this.TASK_TYPE = TASK_TYPE;
    }
}
