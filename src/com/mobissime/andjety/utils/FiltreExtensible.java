/*
 * Andjety 3.0 - Paul Coiffier - 2012 - 2015
 * 
 */
package com.mobissime.andjety.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Paul Coiffier
 */
public class FiltreExtensible extends FileFilter {
    //Description et extensions acceptées par le filtre

    private String description;
    private List<String> extensions;
    //Constructeur à partir de la description

    public FiltreExtensible(String description) {
        if (description == null) {
            throw new NullPointerException("La description ne peut être null.");
        }
        this.description = description;
        this.extensions = new ArrayList<String>();
    }
    //Implémentation de FileFilter

    @Override
    public boolean accept(File file) {
        if (file.isDirectory() || extensions.size() == 0) {
            return true;
        }
        String nomFichier = file.getName().toLowerCase();
        for (String extension : extensions) {
            if (nomFichier.endsWith(extension)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getDescription() {
        StringBuffer buffer = new StringBuffer(description);
        buffer.append(" (");
        for (String extension : extensions) {
            buffer.append(extension).append(" ");
        }
        return buffer.append(")").toString();
    }
    //Quelques méthodes utilitaires

    public void setDescription(String description) {
        if (description == null) {
            throw new NullPointerException("La description ne peut être null.");
        }
        this.description = description;
    }

    public void addExtension(String extension) {
        if (extension == null) {
            throw new NullPointerException("Une extension ne peut être null.");
        }
        extensions.add(extension);
    }

    public void removeExtension(String extension) {
        extensions.remove(extension);
    }

    public void clearExtensions() {
        extensions.clear();
    }

    public List<String> getExtensions() {
        return extensions;
    }
}
