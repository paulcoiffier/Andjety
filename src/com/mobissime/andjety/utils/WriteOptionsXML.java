/*
 * Andjety 3.0 - Paul Coiffier - 2012 - 2015
 * 
 */
package com.mobissime.andjety.utils;

/**
 *
 * @author Paul Coiffier
 */
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WriteOptionsXML {

    public static void main(String argv[]) {
    }

    public void WriteOptionsFile(String user, String password, String serveur, String SkinName, String MAJOnStartup, String updateStrAstuce, String language) {
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("options");
            doc.appendChild(rootElement);

            // staff elements
            Element staff = doc.createElement("database");
            rootElement.appendChild(staff);

            // set attribute to staff element
		/*Attr attr = doc.createAttribute("id");
             attr.setValue("1");
             staff.setAttributeNode(attr);*/

            // shorten way
            // staff.setAttribute("id", "1");

            // firstname elements
            Element firstname = doc.createElement("serveur");
            firstname.appendChild(doc.createTextNode(serveur));
            staff.appendChild(firstname);

            // lastname elements
            Element lastname = doc.createElement("user");
            lastname.appendChild(doc.createTextNode(user));
            staff.appendChild(lastname);

            // nickname elements
            Element nickname = doc.createElement("password");
            nickname.appendChild(doc.createTextNode(password));
            staff.appendChild(nickname);

            // nickname elements
            Element skin = doc.createElement("skinName");
            skin.appendChild(doc.createTextNode(SkinName));
            staff.appendChild(skin);

            Element majOnStartup = doc.createElement("majOnStartup");
            majOnStartup.appendChild(doc.createTextNode(MAJOnStartup));
            staff.appendChild(majOnStartup);

            Element majAstucesOnStartup = doc.createElement("majAstucesOnStartup");
            majAstucesOnStartup.appendChild(doc.createTextNode(updateStrAstuce));
            staff.appendChild(majAstucesOnStartup);

            Element languageOption = doc.createElement("language");
            languageOption.appendChild(doc.createTextNode(language));
            staff.appendChild(languageOption);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            String path = "";
            
            if (OSValidator.isUnix()) {
                path = "/usr/lib/Andjety";
            } else {
                path = System.getProperty("user.dir").toString();
            }

            
            File file = new File(path + "/Files/options.xml");

            StreamResult result = new StreamResult(file);

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);


        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}