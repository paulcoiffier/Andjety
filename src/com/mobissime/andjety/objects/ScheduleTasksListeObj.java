/*
 * Andjety 2.0 - Code by Paul Coiffier - 2012/2013
 * 
 */
package com.mobissime.andjety.objects;

import java.util.ArrayList;

/**
 *
 * @author Paul Coiffier
 */
public class ScheduleTasksListeObj {

    private ArrayList<ScheduleTaskObj> listeOfScheduleTasks;

    public ScheduleTasksListeObj() {
    }

    public ScheduleTasksListeObj(ArrayList<ScheduleTaskObj> listeOfScheduleTasks) {
        this.listeOfScheduleTasks = listeOfScheduleTasks;
    }

    public ArrayList<ScheduleTaskObj> getListeOfScheduleTasks() {
        return listeOfScheduleTasks;
    }

    public void setListeOfScheduleTasks(ArrayList<ScheduleTaskObj> listeOfScheduleTasks) {
        this.listeOfScheduleTasks = listeOfScheduleTasks;
    }
}
