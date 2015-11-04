/*
 * Andjety 3.0 - Paul Coiffier - 2012 - 2015
 * 
 */
package com.mobissime.andjety.objects;

import java.util.ArrayList;

/**
 *
 * @author Paul
 */
public class TaskExecListe {

    private ArrayList<TasksexecObj> listOfTasksExec;

    public TaskExecListe() {
    }

    public TaskExecListe(ArrayList<TasksexecObj> listOfTasksExec) {
        this.listOfTasksExec = listOfTasksExec;
    }

    public ArrayList<TasksexecObj> getListOfTasksExec() {
        return listOfTasksExec;
    }

    public void setListOfTasksExec(ArrayList<TasksexecObj> listOfTasksExec) {
        this.listOfTasksExec = listOfTasksExec;
    }
}
