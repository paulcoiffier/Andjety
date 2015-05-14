/*
 * Andjety 2.0 - Code by Paul Coiffier - 2012/2013
 * 
 */
package models;

import entities.Tasks;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Paul Coiffier
 */
public class TasksTableModelWithFilter extends AbstractTableModel {

    private final EntityManager manager;
    private int startPosition;
    private List<Tasks> theList;
    private String taskLibelleStr;

    public TasksTableModelWithFilter(EntityManager manager, String taskLibelle) {
        this.manager = manager;
        this.startPosition = 0;
        this.theList = getItems(startPosition, startPosition + 100);
        this.taskLibelleStr = taskLibelle;
    }

    public String getColumnName(int col) {
        String columnName = "vide";
        if (col == 0) {
            columnName = "Libellé";
        } else if (col == 1) {
            columnName = "Requête";
        }

        return columnName;
    }

    @Override
    public int getRowCount() {
        return ((Long) manager.createQuery("SELECT COUNT(t) FROM Tasks t where t.taskLibelle  = '" + taskLibelleStr + "'").getSingleResult()).intValue();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        if ((rowIndex >= startPosition) && (rowIndex < (startPosition + 100))) {
        } else {
            this.theList = getItems(rowIndex, rowIndex + 100);
            this.startPosition = rowIndex;
        }
        Tasks c = theList.get(rowIndex - startPosition);

        Object toReturn = null;
        switch (columnIndex) {
            case 0:
                toReturn = c.getTaskLibelle();
                break;
            case 1:
                toReturn = c.getTaskRequete();
                break;
            default:
                toReturn = c.getId();

        }
        return toReturn;
    }

    private List<Tasks> getItems(int from, int to) {

        Query query = manager.createQuery("SELECT t FROM Tasks t where t.taskLibelle  = '" + taskLibelleStr + "'").setMaxResults(to - from).setFirstResult(from);

        //add the cache
        List<Tasks> resultList = query.getResultList();
        return resultList;
    }
}