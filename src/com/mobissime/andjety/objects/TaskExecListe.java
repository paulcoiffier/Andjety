/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
