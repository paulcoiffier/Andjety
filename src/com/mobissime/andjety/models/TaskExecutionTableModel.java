/*
 * Andjety 3.0 - Paul Coiffier - 2012 - 2015
 * 
 */
package com.mobissime.andjety.models;

/**
 *
 * @author Paul Coiffier
 */
import com.mobissime.andjety.utils.DBUtils;
import com.mobissime.andjety.entities.Scheduletasks;
import com.mobissime.andjety.entities.Tasks;
import com.mobissime.andjety.entities.Tasksexec;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Paul
 */
public class TaskExecutionTableModel extends AbstractTableModel {

    private final EntityManager manager;
    private int startPosition;
    private List<Tasksexec> theList;
    private int counter = 0;
    private final Tasks laTache;
    private final Scheduletasks theTasksToEdit;

    public TaskExecutionTableModel(EntityManager manager, Tasks theTasks,Scheduletasks sct) {
        this.manager = manager;
        this.laTache = theTasks;
        this.theTasksToEdit = sct;
        this.startPosition = 0;
        this.theList = getItems(startPosition, startPosition + 100);
        
        
        System.out.println("In model TASK ID : " + laTache.getId());
    }

    public String getColumnName(int col) {
        String columnName = "vide";

        if (col == 0) {
            columnName = "Tâche";
        } else if (col == 1) {
            columnName = "Date / Heure";
        } else if (col == 2) {
            columnName = "Fichier résultat";
        }

        return columnName;
    }

    @Override
    public int getRowCount() {
        return ((Long) manager.createQuery("SELECT COUNT(t) FROM Tasksexec t where t.idtask=" + laTache.getId() + " AND t.idscheduletask = " + theTasksToEdit.getId()).getSingleResult()).intValue();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        if ((rowIndex >= startPosition) && (rowIndex < (startPosition + 100))) {
        } else {
            this.theList = getItems(rowIndex, rowIndex + 100);
            this.startPosition = rowIndex;
        }
        Tasksexec c = theList.get(rowIndex - startPosition);

        Object toReturn = null;
        switch (columnIndex) {
            case 0:
                // On va cherche le libellé de la tâche
                DBUtils dbu = new DBUtils();
                Tasks theTmp = dbu.getTaskById(c.getIdtask(), manager);
                toReturn = theTmp.getTaskLibelle();
                //toReturn = "test";
                break;
            case 1:
                toReturn = c.getDateheure();
                break;
            case 2:
                toReturn = c.getResultat();
                break;

            default:
                toReturn = c.getId();

        }
        return toReturn;
    }

    private List<Tasksexec> getItems(int from, int to) {
        Query query = manager.createQuery("SELECT t FROM Tasksexec t WHERE t.idtask = " + String.valueOf(laTache.getId()) + " AND t.idscheduletask = " + theTasksToEdit.getId()).setMaxResults(to - from).setFirstResult(from);

        //add the cache
        List<Tasksexec> resultList = query.getResultList();
        return resultList;
    }
}