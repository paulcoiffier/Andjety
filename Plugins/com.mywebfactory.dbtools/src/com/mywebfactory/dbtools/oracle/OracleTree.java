package com.mywebfactory.dbtools.oracle;

import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import com.mywebfactory.objects.connectionObj;

/**
 *
 * @author Polo
 */
public class OracleTree {

    public static DefaultTreeModel OracleTreeModel(connectionObj conObj) {
        
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Objets");
        DefaultMutableTreeNode tablesTreeNode = new DefaultMutableTreeNode("Tables");
        DefaultMutableTreeNode vuesTreeNode = new DefaultMutableTreeNode("Vues");
        DefaultMutableTreeNode indexTreeNode = new DefaultMutableTreeNode("Index");
        DefaultMutableTreeNode packagesTreeNode = new DefaultMutableTreeNode("Packages");
        DefaultMutableTreeNode proceduresTreeNode = new DefaultMutableTreeNode("Procédures");
        DefaultMutableTreeNode fonctionsTreeNode = new DefaultMutableTreeNode("Fonctions");
        DefaultMutableTreeNode triggersTreeNode = new DefaultMutableTreeNode("Triggers");
        DefaultMutableTreeNode sequencesTreeNode = new DefaultMutableTreeNode("Séquences");

        ArrayList<String> listeOfTables = OracleListObjects.listeOracleTables(conObj);
        ArrayList<String> listeOfViews = OracleListObjects.listeOracleVues(conObj);
        ArrayList<String> listeOfIndexes = OracleListObjects.listeOracleIndexes(conObj);
        ArrayList<String> listeOfTriggers = OracleListObjects.listeOracleTriggers(conObj);
        ArrayList<String> listeOfProcedures = OracleListObjects.listeOracleProcedures(conObj);
        ArrayList<String> listeOfFonctions = OracleListObjects.listeOracleFonctions(conObj);
        ArrayList<String> listeOfPackages = OracleListObjects.listeOraclePackages(conObj);
        ArrayList<String> listeOfSequences = OracleListObjects.listeOracleSequences(conObj);
        
        for (String tmp : listeOfTables) {
            DefaultMutableTreeNode child = new DefaultMutableTreeNode(tmp);
            tablesTreeNode.add(child);
        }
        
        for (String tmp : listeOfViews) {
            DefaultMutableTreeNode child = new DefaultMutableTreeNode(tmp);
            vuesTreeNode.add(child);
        }
        
        for (String tmp : listeOfIndexes) {
            DefaultMutableTreeNode child = new DefaultMutableTreeNode(tmp);
            indexTreeNode.add(child);
        }
        
        for (String tmp : listeOfTriggers) {
            DefaultMutableTreeNode child = new DefaultMutableTreeNode(tmp);
            triggersTreeNode.add(child);
        }
        
        for (String tmp : listeOfProcedures) {
            DefaultMutableTreeNode child = new DefaultMutableTreeNode(tmp);
            proceduresTreeNode.add(child);
        }
        
        for (String tmp : listeOfFonctions) {
            DefaultMutableTreeNode child = new DefaultMutableTreeNode(tmp);
            fonctionsTreeNode.add(child);
        }
        
        for (String tmp : listeOfPackages) {
            DefaultMutableTreeNode child = new DefaultMutableTreeNode(tmp);
            packagesTreeNode.add(child);
        }
        
        for (String tmp : listeOfSequences) {
            DefaultMutableTreeNode child = new DefaultMutableTreeNode(tmp);
            sequencesTreeNode.add(child);
        }
        
        root.add(tablesTreeNode);
        root.add(vuesTreeNode);
        root.add(indexTreeNode);
        root.add(packagesTreeNode);
        root.add(proceduresTreeNode);
        root.add(fonctionsTreeNode);
        root.add(triggersTreeNode);
        root.add(sequencesTreeNode);

        DefaultTreeModel rootModel = new DefaultTreeModel(root);
        
        return rootModel;
    }
}
