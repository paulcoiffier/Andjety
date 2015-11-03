/*
 * Andjety 2.0 - Code by Paul Coiffier - 2012/2013
 * 
 */
package com.mobissime.andjety.renderers;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Paul Coiffier
 */
public class ProgressRenderer extends JProgressBar implements TableCellRenderer {

    public ProgressRenderer() {

        setMaximum(100);
        setBorderPainted(true);
        setStringPainted(true);

    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int col) {

        setValue((Integer) value);
        if (isSelected) {
            setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        } else {
            setBorder(null);
        }
        setString(String.valueOf((Integer) value) + ".00%");
        return this;
    }
}