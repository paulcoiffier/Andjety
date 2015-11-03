/*
 * Andjety 2.0 - Code by Paul Coiffier - 2012/2013
 * 
 */
package com.mobissime.andjety.utils;

import java.io.File;
import java.util.Collections;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Paul Coiffier
 */
public class OptionsParser {

    public static JList list = new JList();
    //public static DefaultListModel model = new DefaultListModel();
    public String user;
    public String password;
    public String serveur;
    public String skinName;
    public String majOnStartup;
    public String majAstuceOnStartup;
    public String languageValue;

    public void parseXml() {
        try {
            
            String path = System.getProperty("user.dir").toString();
            
            /*if(OSValidator.isUnix()){
                path = "/usr/lib/Andjety";
            }*/
            
            File file = new File(path + "/Files/options.xml");

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            //System.out.println("Root element " + doc.getDocumentElement().getNodeName());
            NodeList nodeLst = doc.getElementsByTagName("database");
            //System.out.println("Information of all employees");

            for (int s = 0; s < nodeLst.getLength(); s++) {

                Node fstNode = nodeLst.item(s);

                if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element fstElmnt = (Element) fstNode;
                    NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("serveur");
                    Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
                    NodeList fstNm = fstNmElmnt.getChildNodes();
//                    serveur = ((Node) fstNm.item(0)).getNodeValue();
                    // System.out.println("Options serveur : " + ((Node) fstNm.item(0)).getNodeValue());

                    NodeList lstNmElmntLst = fstElmnt.getElementsByTagName("user");
                    Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
                    NodeList lstNm = lstNmElmnt.getChildNodes();
                    //user = ((Node) lstNm.item(0)).getNodeValue();
                    //System.out.println("Options user : " + ((Node) lstNm.item(0)).getNodeValue());

                    NodeList PassNmElmntLst = fstElmnt.getElementsByTagName("password");
                    Element PassNmElmnt = (Element) PassNmElmntLst.item(0);
                    NodeList PassNm = PassNmElmnt.getChildNodes();
                    // password = ((Node) PassNm.item(0)).getNodeValue();
                    //System.out.println("Options password : " + ((Node) PassNm.item(0)).getNodeValue());

                    NodeList skinNameNmElmntLst = fstElmnt.getElementsByTagName("skinName");
                    Element skinNameNmElmnt = (Element) skinNameNmElmntLst.item(0);
                    NodeList skinNameNm = skinNameNmElmnt.getChildNodes();
                    skinName = ((Node) skinNameNm.item(0)).getNodeValue();

                    NodeList majOnStartupElmntLst = fstElmnt.getElementsByTagName("majOnStartup");
                    Element majOnStartupNmElmnt = (Element) majOnStartupElmntLst.item(0);
                    NodeList majOnStartupb = majOnStartupNmElmnt.getChildNodes();
                    majOnStartup = ((Node) majOnStartupb.item(0)).getNodeValue();

                    NodeList majAstuceOnStartupElmntLst = fstElmnt.getElementsByTagName("majAstucesOnStartup");
                    Element majAstuceOnStartupNmElmnt = (Element) majAstuceOnStartupElmntLst.item(0);
                    NodeList majAstuceOnStartupb = majAstuceOnStartupNmElmnt.getChildNodes();
                    majAstuceOnStartup = ((Node) majAstuceOnStartupb.item(0)).getNodeValue();

                    NodeList languageElmntLst = fstElmnt.getElementsByTagName("language");
                    Element languageNmElmnt = (Element) languageElmntLst.item(0);
                    NodeList languagepb = languageNmElmnt.getChildNodes();
                    languageValue = ((Node) languagepb.item(0)).getNodeValue();
                }
                //sortList(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void sortList(DefaultListModel model) {

        // We need a List (a Vector) for sorting
        int size = model.getSize();
        Vector list = new Vector();
        for (int x = 0; x < size; ++x) {
            Object o = model.get(x);
            list.addElement(o);
        }

        // sort the List
        Collections.sort(list);
        // update the model with a sorted List
        for (int x = 0; x < size; ++x) {
            if (model.getElementAt(x) != list.elementAt(x)) {
                model.set(x, list.elementAt(x));
            }
        }
    }
}
