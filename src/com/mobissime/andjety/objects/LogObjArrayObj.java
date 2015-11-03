package com.mobissime.andjety.objects;

import java.util.ArrayList;

/**
 *
 * @author Paul
 */
public class LogObjArrayObj {

    private ArrayList<LogObj> listOfLogsOb;

    public LogObjArrayObj() {
    }

    public LogObjArrayObj(ArrayList<LogObj> listOfLogsOb) {
        this.listOfLogsOb = listOfLogsOb;
    }

    public ArrayList<LogObj> getListOfLogsOb() {
        return listOfLogsOb;
    }

    public void setListOfLogsOb(ArrayList<LogObj> listOfLogsOb) {
        this.listOfLogsOb = listOfLogsOb;
    }
}
