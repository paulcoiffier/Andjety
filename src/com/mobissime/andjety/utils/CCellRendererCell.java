/*
 * Andjety 2.0 - Code by Paul Coiffier - 2012/2013
 * 
 */
package com.mobissime.andjety.utils;

/*
 * 01/07/2009
 *
 * CCellRenderer.java - A cell renderer for C completions.
 *
 * This library is distributed under a modified BSD license. See the included
 * RSyntaxTextArea.License.txt file for details.
 */
import java.awt.Component;
import java.awt.Graphics;
import java.io.File;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JList;

import org.fife.ui.autocomplete.*;

/**
 * The cell renderer used for the C programming language.
 *
 * @author Robert Futrell
 * @version 1.0
 */
public class CCellRendererCell extends CompletionCellRenderer {

    private Icon variableIcon;
    private Icon functionIcon;
    private Icon dieseIcon;
    private Icon emptyIcon;

    /**
     * Constructor.
     */
    public CCellRendererCell() {
        variableIcon = getIcon("ressources/var.png");
        functionIcon = getIcon("ressources/function.png");
        dieseIcon = getIcon("ressources/macro.png");
        emptyIcon = new EmptyIcon(16);
    }

    /**
     * Returns an icon.
     *
     * @param resource The icon to retrieve. This should either be a file, or a
     * resource loadable by the current ClassLoader.
     * @return The icon.
     */
    private Icon getIcon(String resource) {
        ClassLoader cl = getClass().getClassLoader();
        URL url = cl.getResource(resource);
        if (url == null) {
            File file = new File(resource);
            try {
                url = file.toURI().toURL();
            } catch (MalformedURLException mue) {
                mue.printStackTrace(); // Never happens
            }
        }
        return url != null ? new ImageIcon(url) : null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void prepareForOtherCompletion(JList list,
            Completion c, int index, boolean selected, boolean hasFocus) {
        super.prepareForOtherCompletion(list, c, index, selected, hasFocus);
        setIcon(emptyIcon);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void prepareForVariableCompletion(JList list,
            VariableCompletion vc, int index, boolean selected,
            boolean hasFocus) {
        super.prepareForVariableCompletion(list, vc, index, selected,
                hasFocus);
        setIcon(variableIcon);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void prepareForFunctionCompletion(JList list,
            FunctionCompletion fc, int index, boolean selected,
            boolean hasFocus) {
        super.prepareForFunctionCompletion(list, fc, index, selected,
                hasFocus);
        setIcon(functionIcon);
    }

    @Override
    protected void prepareForMarkupTagCompletion(JList jlist, MarkupTagCompletion mtc, int i, boolean bln, boolean bln1) {
        super.prepareForMarkupTagCompletion(jlist, mtc, i, bln, bln1);
        setIcon(dieseIcon);
    }

    

    /**
     * An standard icon that doesn't paint anything. This can be used to take up
     * an icon's space when no icon is specified.
     *
     * @author Robert Futrell
     * @version 1.0
     */
    private static class EmptyIcon implements Icon, Serializable {

        private int size;

        public EmptyIcon(int size) {
            this.size = size;
        }

        public int getIconHeight() {
            return size;
        }

        public int getIconWidth() {
            return size;
        }

        public void paintIcon(Component c, Graphics g, int x, int y) {
        }
    }
}