package com.mywebfactory.dbtools.gui;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;
import com.mywebfactory.dbtools.mysql.MysqlTree;
import com.mywebfactory.dbtools.oracle.OracleTree;
import com.mywebfactory.dbtools.renderers.ButtonTabComponent;
import com.mywebfactory.dbtools.renderers.DatabaseObjectsTableCellRenderer;
import objects.connectionObj;


/**
 *
 * @author Polo
 */
public class DBVisualizerPanel extends javax.swing.JPanel {

    private ArrayList<connectionObj> connectionObjListe;
    private static JTabbedPane theJTabbedPane;

    public DBVisualizerPanel(final ArrayList<connectionObj> connectionObjListe) {
        initComponents();
        this.connectionObjListe = connectionObjListe;
        DBVisualizerPanel.theJTabbedPane = jTabbedPaneGeneral;

        if (connectionObjListe != null) {
            for (connectionObj newCon : connectionObjListe) {
                jComboBoxConnections.addItem(newCon.getDbName());
            }
        }

        jComboBoxConnections.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                java.awt.EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        if (connectionObjListe.get(jComboBoxConnections.getSelectedIndex()).getDbType().toUpperCase().equals("MYSQL")) {
                            jTreeObjects.setModel(MysqlTree.MysqlTreeModel(connectionObjListe.get(jComboBoxConnections.getSelectedIndex())));
                            jTreeObjects.setCellRenderer(new DatabaseObjectsTableCellRenderer());
                        } else if (connectionObjListe.get(jComboBoxConnections.getSelectedIndex()).getDbType().toUpperCase().equals("ORACLE")) {
                            jTreeObjects.setModel(OracleTree.OracleTreeModel(connectionObjListe.get(jComboBoxConnections.getSelectedIndex())));
                            jTreeObjects.setCellRenderer(new DatabaseObjectsTableCellRenderer());
                        }
                        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    }
                });
            }
        });


        jTreeObjects.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    TreePath selPath = jTreeObjects.getPathForLocation(e.getX(), e.getY());
                    if (selPath != null) {

                        String value = jTreeObjects.getSelectionPath().getParentPath().toString();

                        boolean foundTab = false;

                        // Test si la panel n'est pas déjà ouvert dans un onglet. Si oui , on le selectionne boolean foundTab = false;
                        for (int i = 0; jTabbedPaneGeneral.getTabCount() > i; i++) {
                            if (jTabbedPaneGeneral.getTitleAt(i).toString().toUpperCase().equals(jTreeObjects.getLastSelectedPathComponent().toString().toUpperCase().trim())) {
                                jTabbedPaneGeneral.setSelectedIndex(i);
                                foundTab = true;
                            }
                        }

                        if (foundTab == false) {
                            if (value.toUpperCase().contains("TABLE")) {
                                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                                DBTablePanelData dbPanel = new DBTablePanelData(jTreeObjects.getLastSelectedPathComponent().toString(), connectionObjListe.get(jComboBoxConnections.getSelectedIndex()), DBVisualizerPanel.this);
                                dbPanel.showData();
                                jTabbedPaneGeneral.add(dbPanel, jTreeObjects.getLastSelectedPathComponent().toString());
                                jTabbedPaneGeneral.setSelectedComponent(dbPanel);
                                initTabComponent(jTabbedPaneGeneral.getSelectedIndex());
                                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                            } else if (value.toUpperCase().contains("TRIGGERS")) {
                                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                                DBScriptPanel dbpane = new DBScriptPanel(jTreeObjects.getLastSelectedPathComponent().toString(), connectionObjListe.get(jComboBoxConnections.getSelectedIndex()), DBVisualizerPanel.this);
                                dbpane.showData();
                                jTabbedPaneGeneral.add(dbpane, jTreeObjects.getLastSelectedPathComponent().toString());
                                jTabbedPaneGeneral.setSelectedComponent(dbpane);
                                initTabComponent(jTabbedPaneGeneral.getSelectedIndex());
                                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                            }
                        }
                    }
                }
            }
        });
    }

    public static void initTabComponent(int i) {
        theJTabbedPane.setTabComponentAt(i,
                new ButtonTabComponent(theJTabbedPane));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane10 = new javax.swing.JSplitPane();
        jTabbedPaneGeneral = new javax.swing.JTabbedPane();
        jSplitPane1 = new javax.swing.JSplitPane();
        jComboBoxConnections = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTreeObjects = new javax.swing.JTree();

        jSplitPane10.setRightComponent(jTabbedPaneGeneral);

        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jSplitPane1.setLeftComponent(jComboBoxConnections);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(250, 322));

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jTreeObjects.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane1.setViewportView(jTreeObjects);

        jSplitPane1.setRightComponent(jScrollPane1);

        jSplitPane10.setLeftComponent(jSplitPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBoxConnections;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane10;
    private javax.swing.JTabbedPane jTabbedPaneGeneral;
    private javax.swing.JTree jTreeObjects;
    // End of variables declaration//GEN-END:variables
}
