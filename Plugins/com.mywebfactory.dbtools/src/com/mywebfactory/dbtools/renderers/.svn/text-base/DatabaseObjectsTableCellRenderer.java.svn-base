/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mywebfactory.dbtools.renderers;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author Paul
 */
public class DatabaseObjectsTableCellRenderer extends DefaultTreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
            boolean sel, boolean expanded, boolean leaf, int row,
            boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
                row, hasFocus);

        DefaultMutableTreeNode selectedNode2 = (DefaultMutableTreeNode) (value);

        try {
            if (selectedNode2.toString().toUpperCase().contains("TABLE")) {
                setIcon(new ImageIcon(getClass().getResource("/com/mywebfactory/dbtools/icons/table.png")));
            } else if (selectedNode2.toString().toUpperCase().contains("INDEX")) {
                setIcon(new ImageIcon(getClass().getResource("/com/mywebfactory/dbtools/icons/key.png")));
            } else if (selectedNode2.toString().toUpperCase().contains("VUE")) {
                setIcon(new ImageIcon(getClass().getResource("/com/mywebfactory/dbtools/icons/find.png")));
            } else if (selectedNode2.toString().toUpperCase().contains("TRIGGER")) {
                setIcon(new ImageIcon(getClass().getResource("/com/mywebfactory/dbtools/icons/table_gear.png")));
            } else if (selectedNode2.toString().toUpperCase().contains("FONCTIONS")) {
                setIcon(new ImageIcon(getClass().getResource("/com/mywebfactory/dbtools/icons/page_white_code.png")));
            } else if (selectedNode2.toString().equals("Procédures")) {
                setIcon(new ImageIcon(getClass().getResource("/com/mywebfactory/dbtools/icons/page_white_code_red.png")));
            } else if (selectedNode2.toString().toUpperCase().contains("PACKAGE")) {
                setIcon(new ImageIcon(getClass().getResource("/com/mywebfactory/dbtools/icons/compress.png")));
            } else if (selectedNode2.toString().equals("Séquences")) {
                setIcon(new ImageIcon(getClass().getResource("/com/mywebfactory/dbtools/icons/calculator.png")));
            }

            try {
                if (selectedNode2.getParent().toString().toUpperCase().contains("TABLE")) {
                    setIcon(new ImageIcon(getClass().getResource("/com/mywebfactory/dbtools/icons/table.png")));
                } else if (selectedNode2.getParent().toString().toUpperCase().contains("INDEX")) {
                    setIcon(new ImageIcon(getClass().getResource("/com/mywebfactory/dbtools/icons/key.png")));
                } else if (selectedNode2.getParent().toString().toUpperCase().contains("VUE")) {
                    setIcon(new ImageIcon(getClass().getResource("/com/mywebfactory/dbtools/icons/find.png")));
                } else if (selectedNode2.getParent().toString().toUpperCase().contains("TRIGGER")) {
                    setIcon(new ImageIcon(getClass().getResource("/com/mywebfactory/dbtools/icons/table_gear.png")));
                } else if (selectedNode2.getParent().toString().toUpperCase().contains("FONCTIONS")) {
                    setIcon(new ImageIcon(getClass().getResource("/com/mywebfactory/dbtools/icons/page_white_code.png")));
                } else if (selectedNode2.getParent().toString().equals("Procédures")) {
                    setIcon(new ImageIcon(getClass().getResource("/com/mywebfactory/dbtools/icons/page_white_code_red.png")));
                } else if (selectedNode2.getParent().toString().toUpperCase().contains("PACKAGE")) {
                    setIcon(new ImageIcon(getClass().getResource("/com/mywebfactory/dbtools/icons/compress.png")));
                } else if (selectedNode2.getParent().toString().equals("Séquences")) {
                    setIcon(new ImageIcon(getClass().getResource("/com/mywebfactory/dbtools/icons/calculator.png")));
                }
            } catch (Exception e) {
            }


        } catch (Exception e) {
        }



        return this;
    }
}
