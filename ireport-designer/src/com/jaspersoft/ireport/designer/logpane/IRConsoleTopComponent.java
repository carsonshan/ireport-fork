/*
 * iReport - Visual Designer for JasperReports.
 * Copyright (C) 2002 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of iReport.
 *
 * iReport is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * iReport is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with iReport. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.ireport.designer.logpane;

import com.jaspersoft.ireport.locale.I18n;
import java.awt.Component;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
//import org.openide.util.Utilities;

/**
 * Top component which displays something.
 */
final public class IRConsoleTopComponent extends TopComponent implements java.awt.event.ActionListener {

    private LogTextArea mainLogTextArea;
    private LogTextArea pointedLogTextArea = null;
    private List logsComponents = new ArrayList();
    
    private static IRConsoleTopComponent instance;
    /** path to the icon used by the component and its open action */
//    static final String ICON_PATH = "SET/PATH/TO/ICON/HERE";
    private static final String PREFERRED_ID = "IRConsoleTopComponent";

    private IRConsoleTopComponent() {
        initComponents();
//        setName(NbBundle.getMessage(IRConsoleTopComponent.class, "CTL_IRConsoleTopComponent"));
//        setIcon(Utilities.loadImage(ICON_PATH, true));
        
        mainLogTextArea = new LogTextArea(I18n.getString("IRConsoleTopComponent.TextArea.iReportConsole")); //I18n.getString("logPane.mainConsole",
        mainLogTextArea.setLogPane( this );
        mainLogTextArea.addActionListener( this );

        
        jTabbedPaneLogs.add(mainLogTextArea.getTitle(), mainLogTextArea);
        //jTabbedPaneLogs.add(problemsPanel.getName(), problemsPanel);
        
        updateLogTabs();
        
    }

    protected void updateLogTabs()
    {
        
        if (logsComponents.size() > 0)
        {
            if (getComponent(0) != jTabbedPaneLogs)
            {
                while (getComponentCount() > 0)
                {
                    remove(0);
                }
                add(jTabbedPaneLogs, java.awt.BorderLayout.CENTER);
                
            }
            
            jTabbedPaneLogs.removeAll();
            jTabbedPaneLogs.add(mainLogTextArea.getTitle(),mainLogTextArea);
            for (int i=0; i<logsComponents.size(); ++i)
            {
                jTabbedPaneLogs.add(((LogTextArea)logsComponents.get(i)).getTitle(),
                                     (LogTextArea)logsComponents.get(i));
            }
        }
        else
        {
            if (getComponent(0) != mainLogTextArea)
            {
                while (getComponentCount() > 0)
                {
                    remove(0);
                }
                add(mainLogTextArea, java.awt.BorderLayout.CENTER);
            }
        }
        
        this.updateUI();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPaneLogs = new javax.swing.JTabbedPane();

        setLayout(new java.awt.BorderLayout());
        add(jTabbedPaneLogs, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPaneLogs;
    // End of variables declaration//GEN-END:variables

    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link findInstance}.
     */
    public static synchronized IRConsoleTopComponent getDefault() {
        if (instance == null) {
            instance = new IRConsoleTopComponent();
        }
        return instance;
    }

    /**
     * Obtain the IRConsoleTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized IRConsoleTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            Logger.getLogger(IRConsoleTopComponent.class.getName()).warning(
                    I18n.getString("IRConsoleTopComponent.Message.ErrorA") + PREFERRED_ID + I18n.getString("IRConsoleTopComponent.Message.ErrorB"));
            return getDefault();
        }
        if (win instanceof IRConsoleTopComponent) {
            return (IRConsoleTopComponent)win;
        }
        Logger.getLogger(IRConsoleTopComponent.class.getName()).warning(
                I18n.getString("IRConsoleTopComponent.Message.WarningA") + PREFERRED_ID +
                I18n.getString("IRConsoleTopComponent.Message.WarningB"));
        return getDefault();
    }

    public @Override int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }

    public @Override void componentOpened() {
        // TODO add custom code on component opening
    }

    public @Override void componentClosed() {
        // TODO add custom code on component closing
    }

    /** replaces this in object stream */
    public @Override Object writeReplace() {
        return new ResolvableHelper();
    }

    protected @Override String preferredID() {
        return PREFERRED_ID;
    }

    final static class ResolvableHelper implements Serializable {
        private static final long serialVersionUID = 1L;
        public Object readResolve() {
            return IRConsoleTopComponent.getDefault();
        }
    }

    @SuppressWarnings("unchecked")
    public LogTextArea createNewLog()
    {
        LogTextArea lta = new LogTextArea(I18n.getString("IRConsoleTopComponent.TextArea.Log"));
        lta.setLogPane( this );
        lta.addActionListener( this );
        logsComponents.add(lta);
        for (int i=0; i<logsComponents.size(); ++i)
        {
            Object component = logsComponents.get(i);
            if (!(component instanceof LogTextArea)) continue;
            
            if ( ((LogTextArea)component).isRemovable())
            {
                logsComponents.remove(i);
                i--;
            }
        }
        updateLogTabs();
        return lta;
    }

    /*
    public void removeLog(int logIndex)
    {
        logsComponents.removeElementAt(logIndex);
        updateLogTabs();
    }
     */

    public void removeLog(LogTextArea logTextArea)
    {
        if (logTextArea.isRemovable())
        {
            logsComponents.remove(logTextArea);
            updateLogTabs();
        }
    }

    public void actionPerformed(java.awt.event.ActionEvent actionEvent) {

        if (actionEvent.getSource() == null) return;
        for (int i=1; i<jTabbedPaneLogs.getComponentCount(); ++i)
        {
            LogTextArea lta = (LogTextArea)jTabbedPaneLogs.getComponent(i);
            if (lta == actionEvent.getSource())
            {
                jTabbedPaneLogs.setTitleAt(i, lta.getTitle());
                break;
            }
        }
    }

    public LogTextArea getMainLogTextArea() {
        return mainLogTextArea;
    }

    public void setMainLogTextArea(LogTextArea mainLogTextArea) {
        this.mainLogTextArea = mainLogTextArea;
    }

    public void setActiveLog( LogTextArea c) {
        try {
        jTabbedPaneLogs.setSelectedComponent(c);
        } catch (Exception ex) {}
    }
    
    public void setActiveLogComponent( Component c) {
        try {
        jTabbedPaneLogs.setSelectedComponent(c);
        } catch (Exception ex) {}
    }

    @Override
    public String getDisplayName() {
        return I18n.getString("IRConsoleTopComponent.Display.iROutput");
    }
}
