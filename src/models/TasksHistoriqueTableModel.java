/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import Utils.OptionsParser;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.swing.table.AbstractTableModel;
import objects.TaskExecListe;
import objects.TasksexecObj;
import org.openide.util.Exceptions;

/**
 *
 * @author Paul
 */
public class TasksHistoriqueTableModel extends AbstractTableModel {

    private TaskExecListe taskListObj;
    private int startPosition;
    private List<TasksexecObj> theList;
    private Properties properties = new Properties();

    public TasksHistoriqueTableModel(TaskExecListe taskList) {
        this.taskListObj = taskList;
        this.startPosition = 0;
        this.theList = getItems();

        // Définition de la langue
        OptionsParser optionsParse = new OptionsParser();
        optionsParse.parseXml();
        String languageValue = optionsParse.languageValue;
        InputStream stream = null;

        if (languageValue.toUpperCase().equals("ENGLISH")) {
            stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("languages/english.properties");
        } else if (languageValue.toUpperCase().contains("FRAN")) {
            stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("languages/french.properties");
        } else {
            stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("languages/english.properties");
        }

        try {
            properties.load(stream);
        } catch (IOException e) {
            System.out.println(properties.getProperty("errorOpenFile"));
        }

    }

    public String getColumnName(int col) {
        String columnName = "vide";
        if (col == 0) {
            columnName = properties.getProperty("AccueilPanelTaskDate");
        } else if (col == 1) {
            columnName = properties.getProperty("AccueilPanelTaskLibelle");
        } else if (col == 2) {
            columnName = properties.getProperty("AccueilPanelTaskResultat");
        }

        return columnName;
    }

    @Override
    public int getRowCount() {
        return theList.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        if ((rowIndex >= startPosition) && (rowIndex < (startPosition + 100))) {
        } else {
            this.theList = getItems();
            this.startPosition = rowIndex;
        }
        TasksexecObj c = theList.get(rowIndex - startPosition);

        Object toReturn = null;
        switch (columnIndex) {
            case 0:
                DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy-hh-mm-ss");
                DateFormat dateToFormat = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
                java.util.Date d = null;

                try {
                    d = dateFormat.parse(c.getDateheure());
                } catch (ParseException ex) {
                    Exceptions.printStackTrace(ex);
                }

                toReturn = dateToFormat.format(d);

                break;
            case 1:
                toReturn = c.getLibelleTask();
                break;
            default:
                toReturn = c.getResultat();

        }
        return toReturn;
    }

    private ArrayList<TasksexecObj> getItems() {
        ArrayList<TasksexecObj> resultList = taskListObj.getListOfTasksExec();
        return resultList;
    }
}
