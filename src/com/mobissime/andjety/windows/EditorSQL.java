/*
 * Andjety 3.0 - Paul Coiffier - 2012 - 2015
 * 
 */
package com.mobissime.andjety.windows;

import com.mobissime.andjety.utils.CCellRendererCell;
import com.mobissime.andjety.utils.DBUtils;
import com.mobissime.andjety.dialogs.WaitDialog;
import com.mobissime.andjety.entities.Databaselist;
import java.awt.BorderLayout;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.mobissime.andjety.models.ResultSetTableModel;
import com.mobissime.andjety.objects.DatabaseListeObj;
import com.mobissime.andjety.objects.DatabaseObj;
import org.fife.ui.autocomplete.*;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

/**
 *
 * @author Paul Coiffier
 */
public class EditorSQL extends javax.swing.JPanel {
    
    private DatabaseListeObj dbo;

    /**
     * Creates new form EditorSQL
     */
    public EditorSQL(DatabaseListeObj dbo) {
        initComponents();
        this.dbo = dbo;
        CompletionProvider provider = createCompletionProvider();
        AutoCompletion ac = new AutoCompletion(provider);
        ac.setShowDescWindow(true);
        ac.install(rSyntaxTextArea1);
        ac.setListCellRenderer(new CCellRendererCell());
        rSyntaxTextArea1.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SQL);
        rSyntaxTextArea1.setCodeFoldingEnabled(true);
        rSyntaxTextArea1.setAntiAliasingEnabled(true);

        jComboBoxDatabases.removeAllItems();
        
        List<DatabaseObj> resultList = dbo.getDbListe();
        
        for (int i = 0; i < resultList.size(); i++) {
            jComboBoxDatabases.addItem(resultList.get(i).getDblist_libelle().toString());
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jXTable2 = new org.jdesktop.swingx.JXTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        rTextScrollPane1 = new org.fife.ui.rtextarea.RTextScrollPane();
        rSyntaxTextArea1 = new org.fife.ui.rsyntaxtextarea.RSyntaxTextArea();
        jToolBar1 = new javax.swing.JToolBar();
        jComboBoxDatabases = new javax.swing.JComboBox();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButtonExecRequete = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();

        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jXTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jXTable2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Résultat", jPanel2);

        jScrollPane4.setViewportView(jTextPane1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Erreur", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jSplitPane1.setRightComponent(jPanel1);

        rTextScrollPane1.setPreferredSize(new java.awt.Dimension(24, 300));

        rSyntaxTextArea1.setColumns(20);
        rSyntaxTextArea1.setRows(5);
        rTextScrollPane1.setViewportView(rSyntaxTextArea1);

        jSplitPane1.setLeftComponent(rTextScrollPane1);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jComboBoxDatabases.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxDatabases.setMaximumSize(new java.awt.Dimension(200, 32767));
        jToolBar1.add(jComboBoxDatabases);
        jToolBar1.add(jSeparator2);

        jButtonExecRequete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gnome_media_playback_start.png"))); // NOI18N
        jButtonExecRequete.setToolTipText("Executer la requête");
        jButtonExecRequete.setFocusable(false);
        jButtonExecRequete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonExecRequete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonExecRequete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExecRequeteActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonExecRequete);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/gnome_media_seek_forward.png"))); // NOI18N
        jButton2.setToolTipText("Executer le script");
        jButton2.setEnabled(false);
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);
        jToolBar1.add(jSeparator1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void displayData(ResultSet rs) {
        
        int i;
        int count;
        String a[];
        String header[] = {"1", "2", "3", "4", "5"};   //Table Header Values, change, as your wish
        count = header.length;
        
        DefaultTableModel model = new DefaultTableModel();
        
        for (i = 0; i < count; i++) {
            model.addColumn(header[i]);
        }
        jXTable2.setModel(model);                             //Represents table Model
        jXTable2.add(jXTable2.getTableHeader(), BorderLayout.NORTH);
        
        a = new String[count];
        
        try {
            while (rs.next()) {
                for (i = 0; i < count; i++) {
                    a[i] = rs.getString(i + 1);
                }
                model.addRow(a);
                jXTable2.setModel(model);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Exception : " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private CompletionProvider createCompletionProvider() {
        
        DefaultCompletionProvider provider = new DefaultCompletionProvider();
        
        FunctionCompletion fct2 = new FunctionCompletion(provider, "DELETE", "Statement ");
        fct2.setShortDescription("The DELETE statement follows the syntax: <br /><br />"
                + "&nbsp;&nbsp;&nbsp;&nbsp;<strong>DELETE</strong> FROM <i>table_name</i> [<strong>WHERE</strong> condition]; <br /><br />"
                + "Any rows that match the WHERE condition will be removed from the table. "
                + "If the WHERE clause is omitted, all rows in the table are removed. The DELETE statement should thus be used with caution.");
        provider.addCompletion(fct2);
        
        FunctionCompletion fctInsert = new FunctionCompletion(provider, "INSERT", "Statement ");
        fctInsert.setShortDescription("An SQL <strong>INSERT</strong> statement adds one or more records to any single table in a relational database."
                + "Insert statements have the following form: <br /><br />"
                + "<strong>INSERT</strong> INTO <i>table</i> (<i>column1 [, column2, column3 ... ]</i>) VALUES (<i>value1 [, value2, value3 ... ]</i>)<br />"
                + "<strong>INSERT</strong> INTO table VALUES (<i>value1, [value2, ... ]</i>)");
        provider.addCompletion(fctInsert);
        
        FunctionCompletion fct = new FunctionCompletion(provider, "SELECT", "Statement ");
        fct.setShortDescription("The <strong>SELECT</strong> statement is used to select data from a database."
                + " The result is stored in a result table, called the result-set.");
        provider.addCompletion(fct);
        
        VariableCompletion fctFrom = new VariableCompletion(provider, "FROM", "Clause ");
        fctFrom.setShortDescription("The SQL <strong>FROM</strong> clause is the source of a rowset to be operated upon in a "
                + "Data Manipulation Language (DML) statement. From clauses are very common, and will provide"
                + "the rowset to be exposed through a Select statement, the source of values in an Update statement,"
                + " and the target rows to be deleted in a Delete statement.<br /><br />"
                + "<strong>FROM</strong> is an SQL reserved word in the SQL standard");
        provider.addCompletion(fctFrom);
        
        VariableCompletion fctHaving = new VariableCompletion(provider, "HAVING", "Clause ");
        fctHaving.setShortDescription("A <strong>HAVING</strong> clause in SQL specifies that an SQL SELECT statement "
                + "should only return rows where aggregate values meet the specified conditions. It was"
                + "added[when?] to the SQL language because the WHERE keyword could not be used with aggregate functions");
        provider.addCompletion(fctHaving);
        
        VariableCompletion fctJoin = new VariableCompletion(provider, "JOIN", "Clause ");
        fctJoin.setShortDescription("An SQL <strong>JOIN</strong> clause combines records from two or more tables in a database. It creates a set that can be saved as a table or used as is. A JOIN is a means for combining fields from two tables by using values common to each. ANSI standard SQL specifies four types of JOIN: INNER, OUTER, LEFT, and RIGHT. As a special case, a table (base table, view, or joined table) can JOIN to itself in a self-join.");
        provider.addCompletion(fctJoin);
        
        MarkupTagCompletion mkp = new MarkupTagCompletion(provider, "NULL");
        mkp.setDescription("Null is a special marker used in Structured Query Language (SQL) to indicate that a data value does not exist in the database. Introduced by the creator of the relational database model, E. F. Codd, SQL Null serves to fulfill the requirement that all true relational database management systems (RDBMS) support a representation of 'missing information and inapplicable information'. Codd also introduced the use of the lowercase Greek omega (ω) symbol to represent Null in database theory. NULL is also an SQL reserved keyword used to identify the Null special marker.");
        provider.addCompletion(mkp);
        
        provider.addCompletion(new BasicCompletion(provider, "while"));

        /*
         * provider.addCompletion(new ShorthandCompletion(provider, "sysout",
         * "System.out.println(", "System.out.println("));
         * provider.addCompletion(new ShorthandCompletion(provider, "syserr",
         * "System.err.println(", "System.err.println("));
         */
        return provider;
        
    }

    private void jButtonExecRequeteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExecRequeteActionPerformed
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                
                final WaitDialog assitantFen = new WaitDialog(null, false);
                java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                
                assitantFen.setLocation(
                        (screenSize.width - assitantFen.getWidth()) / 2,
                        (screenSize.height - assitantFen.getHeight()) / 2);
                
                assitantFen.setTitle("Andjety");
                assitantFen.show();
                
                try {
                    
                    DatabaseObj newDeb = null;
                    for (DatabaseObj dboo : dbo.getDbListe()) {
                        if (dboo.getDblist_libelle().equals(jComboBoxDatabases.getSelectedItem().toString())) {
                            newDeb = dboo;
                        }
                    }
                    
                    String url = null;
                    Connection conn = null;
                    
                    if (newDeb.getDblist_sgbd().toUpperCase().equals("ORACLE")) {
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        url = "jdbc:oracle:thin:@" + newDeb.getDblist_libelle() + ":1521:" + newDeb.getDblist_instance();
                        conn = DriverManager.getConnection(url, newDeb.getDblist_user(), newDeb.getDblist_password());
                    } else if (newDeb.getDblist_sgbd().toUpperCase().equals("MYSQL")) {
                        Class.forName("com.mysql.jdbc.Driver").newInstance();
                        conn = DriverManager.getConnection("jdbc:mysql://" + newDeb.getDblist_ip() + "/"
                                + newDeb.getDblist_instance() + "?" + "user=" + newDeb.getDblist_user() + "&password=" + newDeb.getDblist_password());
                    }
                    
                    conn.setAutoCommit(false);
                    final Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                    // On supprime les point-virgules
                    java.awt.EventQueue.invokeLater(new Runnable() {
                        
                        @Override
                        public void run() {
                            try {
                                
                                String text = rSyntaxTextArea1.getText().toString();
                                ResultSet rset = stmt.executeQuery(text);
                                
                                jTabbedPane1.setSelectedIndex(0);
                                jXTable2.setModel(new ResultSetTableModel(rset));

                                //Document document = rSyntaxTextArea1.getDocument();
                                //Element rootElem = document.getDefaultRootElement();
                                //Element lineElem = rootElem.getElement(rSyntaxTextArea1.getCaretLineNumber());
                                //int lineStart = lineElem.getStartOffset();
                                //int lineEnd = lineElem.getEndOffset();
                                // On ne récupère plus seulement la ligne, mais le texte complet
                                //String text = rSyntaxTextArea1.getText(lineElem.getStartOffset(), lineEnd - lineStart).trim();
                                // Execution d'un traitement PL/SQL
                                // Suppresion remplacement ";" et ResultSet car on traite désormais
                                // le code en entier
                                //text = text.replaceAll(";", " ");
                                // Execution du bloc de code PL/SQL
                                //boolean rset = stmt.execute(text);
                                // Callable Statement + bibliothèque fonctions ORACLE
                                //String sql = "{call TESTPCL(?)}";
                                //CallableStatement call = conn.prepareCall(sql);
                                //call.setString(1, "ioio");
                                //call.execute();
                                // Pour récupérer la sortie console
                                //DBManagerOracle.print_dbms_output(conn);
                                assitantFen.hide();
                            } catch (SQLException ex) {
                                assitantFen.hide();
                                jTabbedPane1.setSelectedIndex(1);
                                java.sql.Date sDate = new java.sql.Date(System.currentTimeMillis());
                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh-mm-ss");
                                jTextPane1.setText("");
                                
                                jTextPane1.setText(jTextPane1.getText() + "\n" + sdf.format(sDate) + " : " + ex.getMessage().toString());
                                
                            }
                        }
                    });
                    
                } catch (InstantiationException ex) {
                    //Exceptions.printStackTrace(ex);
                } catch (IllegalAccessException ex) {
                    //Exceptions.printStackTrace(ex);
                } catch (SQLException ex) {
                    jTabbedPane1.setSelectedIndex(1);
                    java.sql.Date sDate = new java.sql.Date(System.currentTimeMillis());
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh-mm-ss");
                    jTextPane1.setText("");
                    
                    jTextPane1.setText(jTextPane1.getText() + "\n" + sdf.format(sDate) + " : " + ex.getMessage().toString());
                    //JOptionPane.showMessageDialog(null, "Exception : " + ex.getMessage().toString(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Exception : " + ex.getMessage().toString(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });

    }//GEN-LAST:event_jButtonExecRequeteActionPerformed
    
    class BasicThread2 implements Runnable {
        
        @Override
        public void run() {
            final String[] splitted = rSyntaxTextArea1.getText().toString().split(";");
            
            int nblignes = splitted.length;
            //System.out.println("Nombre de lignes : " + nblignes);

            for (int i = 0; i < splitted.length; i++) {
                
                try {
                    Thread.sleep(400);
                } catch (Exception e) {
                }
                
                final String toto = splitted[i].trim();
                
                java.awt.EventQueue.invokeLater(new Runnable() {
                    
                    @Override
                    public void run() {
                        try {
                            System.out.println("Requete : " + toto.trim());
                            
                            DBUtils dbu = new DBUtils();
                            Databaselist newDeb = dbu.getDatabase(jComboBoxDatabases.getSelectedItem().toString());
                            
                            String url = "jdbc:oracle:thin:@" + newDeb.getDblistIp() + ":1521:" + newDeb.getDblistInstance();
                            
                            Connection conn
                                    = DriverManager.getConnection(url, newDeb.getDblistUser(), newDeb.getDblistPassword());
                            
                            conn.setAutoCommit(false);
                            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                            // On supprime les point-virgules
                            ResultSet rset = stmt.executeQuery(toto.trim());
                            
                            jTabbedPane1.setSelectedIndex(0);
                            jXTable2.setModel(new ResultSetTableModel(rset));
                            
                            if (rSyntaxTextArea1.getCaretPosition() == 0) {
                                rSyntaxTextArea1.setCaretPosition(toto.length() + 2);
                            } else {
                                rSyntaxTextArea1.setCaretPosition(rSyntaxTextArea1.getCaretPosition() + toto.length() + 2);
                            }
                            
                        } catch (SQLException ex) {
                            Logger.getLogger(EditorSQL.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                
            }
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        Thread timeThread = new Thread(new EditorSQL.BasicThread2());
        timeThread.start();
        

    }//GEN-LAST:event_jButton2ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonExecRequete;
    private javax.swing.JComboBox jComboBoxDatabases;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JToolBar jToolBar1;
    private org.jdesktop.swingx.JXTable jXTable2;
    private org.fife.ui.rsyntaxtextarea.RSyntaxTextArea rSyntaxTextArea1;
    private org.fife.ui.rtextarea.RTextScrollPane rTextScrollPane1;
    // End of variables declaration//GEN-END:variables
}
