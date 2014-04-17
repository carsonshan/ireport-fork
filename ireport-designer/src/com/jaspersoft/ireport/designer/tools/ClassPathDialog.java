/*
 * iReport - Visual Designer for JasperReports.
 * Copyright (C) 2002 - 2013 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.ireport.designer.tools;

import com.jaspersoft.ireport.locale.I18n;
import com.jaspersoft.ireport.designer.*;
import com.jaspersoft.ireport.designer.IReportManager;
import java.awt.Dialog;
import java.awt.Frame;
import javax.swing.DefaultListModel;
import java.util.*;
import javax.swing.JFileChooser;
/**
 *
 * @author  Administrator
 */
public class ClassPathDialog extends javax.swing.JDialog {

    private int dialogResult = javax.swing.JOptionPane.CANCEL_OPTION;
    private boolean modifiedPath = false;
    /** Creates new form ClassPathDialog */
    
    public ClassPathDialog(Dialog parent, boolean modal) 
    {
         super(parent,modal);
         initAll();
    }

    /** Creates new form ReportQueryFrame */
    public ClassPathDialog(Frame parent, boolean modal) 
    {
         super(parent,modal);
         initAll();
    }

    
    public void initAll(){ 
        
        initComponents();

        jList1.setModel(new DefaultListModel());
        setLocationRelativeTo(null);
        
        javax.swing.KeyStroke escape =  javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0, false);
        javax.swing.Action escapeAction = new javax.swing.AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                jButtonCancelActionPerformed(e);
            }
        };
       
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(escape, I18n.getString("Global.Pane.Escape"));
        getRootPane().getActionMap().put(I18n.getString("Global.Pane.Escape"), escapeAction);


        //applyI18n();
        
        //to make the default button ...
        this.getRootPane().setDefaultButton(this.jButtonSave);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabelClasspath = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        jButtonAdd = new javax.swing.JButton();
        jButtonAdd1 = new javax.swing.JButton();
        jButtonRemove = new javax.swing.JButton();
        jButtonMoveUp = new javax.swing.JButton();
        jButtonMoveDown = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButtonSave = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle(I18n.getString("ClassPathDialog.Title.IRClasspath")); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabelClasspath.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelClasspath.setText(I18n.getString("ClassPathDialog.Label.Classpath")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        getContentPane().add(jLabelClasspath, gridBagConstraints);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(359, 260));
        jScrollPane1.setViewportView(jList1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 4);
        getContentPane().add(jScrollPane1, gridBagConstraints);

        jPanel1.setMinimumSize(new java.awt.Dimension(120, 10));
        jPanel1.setPreferredSize(new java.awt.Dimension(120, 10));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jButtonAdd.setText(I18n.getString("ClassPathDialog.Button.AddJAR")); // NOI18N
        jButtonAdd.setMaximumSize(new java.awt.Dimension(200, 26));
        jButtonAdd.setMinimumSize(new java.awt.Dimension(90, 26));
        jButtonAdd.setPreferredSize(new java.awt.Dimension(120, 26));
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);
        jPanel1.add(jButtonAdd, gridBagConstraints);

        jButtonAdd1.setText(I18n.getString("ClassPathDialog.Button.AddFolder")); // NOI18N
        jButtonAdd1.setMaximumSize(new java.awt.Dimension(200, 26));
        jButtonAdd1.setMinimumSize(new java.awt.Dimension(90, 26));
        jButtonAdd1.setPreferredSize(new java.awt.Dimension(120, 26));
        jButtonAdd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed1(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);
        jPanel1.add(jButtonAdd1, gridBagConstraints);

        jButtonRemove.setText(I18n.getString("Global.Button.Remove")); // NOI18N
        jButtonRemove.setMaximumSize(new java.awt.Dimension(200, 26));
        jButtonRemove.setMinimumSize(new java.awt.Dimension(90, 26));
        jButtonRemove.setPreferredSize(new java.awt.Dimension(120, 26));
        jButtonRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);
        jPanel1.add(jButtonRemove, gridBagConstraints);

        jButtonMoveUp.setText(I18n.getString("Global.Button.MoveUp")); // NOI18N
        jButtonMoveUp.setMaximumSize(new java.awt.Dimension(200, 26));
        jButtonMoveUp.setMinimumSize(new java.awt.Dimension(90, 26));
        jButtonMoveUp.setPreferredSize(new java.awt.Dimension(120, 26));
        jButtonMoveUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMoveUpActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);
        jPanel1.add(jButtonMoveUp, gridBagConstraints);

        jButtonMoveDown.setText(I18n.getString("Global.Button.MoveDown")); // NOI18N
        jButtonMoveDown.setMaximumSize(new java.awt.Dimension(200, 26));
        jButtonMoveDown.setMinimumSize(new java.awt.Dimension(90, 26));
        jButtonMoveDown.setPreferredSize(new java.awt.Dimension(120, 26));
        jButtonMoveDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMoveDownActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);
        jPanel1.add(jButtonMoveDown, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel2, gridBagConstraints);

        jButtonSave.setText(I18n.getString("ClassPathDialog.Button.SaveClasspath")); // NOI18N
        jButtonSave.setMaximumSize(new java.awt.Dimension(200, 26));
        jButtonSave.setMinimumSize(new java.awt.Dimension(90, 26));
        jButtonSave.setPreferredSize(new java.awt.Dimension(120, 26));
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);
        jPanel1.add(jButtonSave, gridBagConstraints);

        jButtonCancel.setText(I18n.getString("Global.Button.Cancel")); // NOI18N
        jButtonCancel.setMaximumSize(new java.awt.Dimension(200, 26));
        jButtonCancel.setMinimumSize(new java.awt.Dimension(90, 26));
        jButtonCancel.setPreferredSize(new java.awt.Dimension(120, 26));
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);
        jPanel1.add(jButtonCancel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 4);
        getContentPane().add(jPanel1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed1
        javax.swing.JFileChooser jfc = new javax.swing.JFileChooser( IReportManager.getInstance().getCurrentDirectory());

        jfc.setDialogTitle(I18n.getString("ClassPathDialog.DialogTitle.AddJAR"));
        jfc.setDialogTitle(I18n.getString("ClassPathDialog.DialogTitle.AddJAR")); //"addToClassPath"

        jfc.setAcceptAllFileFilterUsed(true);
        jfc.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY  );
        
        jfc.setMultiSelectionEnabled(true);

        jfc.setDialogType( javax.swing.JFileChooser.OPEN_DIALOG);
        if  (jfc.showOpenDialog( this) == javax.swing.JOptionPane.OK_OPTION) {
            java.io.File[] files = jfc.getSelectedFiles();

            for (int i=0; i<files.length; ++i) {
                      ((DefaultListModel)jList1.getModel()).addElement( files[i] );
            }
             IReportManager.getInstance().setCurrentDirectory( jfc.getSelectedFile(), true);
             modifiedPath = true;
        }
    }//GEN-LAST:event_jButtonAddActionPerformed1

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        if (modifiedPath)
        {
            int ret = javax.swing.JOptionPane.showConfirmDialog(this, 
                    I18n.getString("ClassPathDialog.Dialog.Confirm")); //"message.classpath.save"

            if (ret == javax.swing.JOptionPane.CANCEL_OPTION)
            {
                return;
            }

            if (ret == javax.swing.JOptionPane.OK_OPTION)
            {
                setDialogResult(javax.swing.JOptionPane.OK_OPTION);
            }
        }
        setVisible(false);
    }//GEN-LAST:event_formWindowClosing

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        setDialogResult(javax.swing.JOptionPane.OK_OPTION);
        setVisible(false);
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonMoveUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMoveUpActionPerformed

        if (jList1.getSelectedValues() != null)
        {
            int[] indices = jList1.getSelectedIndices();
            for (int i=0; i<indices.length; ++i)
            {
                if (indices[i] == 0) continue;
                Object val = ((DefaultListModel)jList1.getModel()).remove( indices[i] );
                ((DefaultListModel)jList1.getModel()).insertElementAt(val, indices[i]-1);
                indices[i]--;
            }
            jList1.setSelectedIndices(indices);
            modifiedPath = true;
        }

    }//GEN-LAST:event_jButtonMoveUpActionPerformed

    private void jButtonMoveDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMoveDownActionPerformed
        if (jList1.getSelectedValues() != null)
        {
            int[] indices = jList1.getSelectedIndices();
            for (int i=indices.length-1; i>=0; --i)
            {
                if (indices[i] >= ((DefaultListModel)jList1.getModel()).size() -1 ) continue;

                Object val = ((DefaultListModel)jList1.getModel()).remove( indices[i] );
                ((DefaultListModel)jList1.getModel()).insertElementAt(val, indices[i]+1);
                indices[i]++;
            }
            jList1.setSelectedIndices(indices);
            modifiedPath = true;
        }
    }//GEN-LAST:event_jButtonMoveDownActionPerformed

    private void jButtonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveActionPerformed

        if (jList1.getSelectedValues() != null)
        {
            Object[] values = jList1.getSelectedValues();
            for (int i=0; i<values.length; ++i)
            {
                ((DefaultListModel)jList1.getModel()).removeElement(values[i]);
            }
            modifiedPath = true;
        }


    }//GEN-LAST:event_jButtonRemoveActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed

        String fileName = "";
        javax.swing.JFileChooser jfc = new javax.swing.JFileChooser( IReportManager.getInstance().getCurrentDirectory());

        jfc.setDialogTitle(I18n.getString("ClassPathDialog.DialogTitle.AddJAR"));
        jfc.setDialogTitle(I18n.getString("ClassPathDialog.DialogTitle.AddJAR"));//"addToClassPath"

        jfc.setAcceptAllFileFilterUsed(true);
        jfc.setFileSelectionMode( JFileChooser.FILES_ONLY  );
        jfc.addChoosableFileFilter( new javax.swing.filechooser.FileFilter() {
            public boolean accept(java.io.File file) {
                String filename = file.getName();
                return (filename.toLowerCase().endsWith(".jar") || file.isDirectory() ||
                        filename.toLowerCase().endsWith(".zip")
                        ) ;
            }
            public String getDescription() {
                return "*.jar, *.zip";
            }
        });

        jfc.setMultiSelectionEnabled(true);

        jfc.setDialogType( javax.swing.JFileChooser.OPEN_DIALOG);
        if  (jfc.showOpenDialog( this) == javax.swing.JOptionPane.OK_OPTION) {
            java.io.File[] files = jfc.getSelectedFiles();

            for (int i=0; i<files.length; ++i) {
                      ((DefaultListModel)jList1.getModel()).addElement( files[i] );
            }
             IReportManager.getInstance().setCurrentDirectory( jfc.getSelectedFile(), true);
             modifiedPath = true;
        }



    }//GEN-LAST:event_jButtonAddActionPerformed

    
    public void setClasspath(List<String> cp)
    {
        for (String path : cp)
        {
            ((DefaultListModel)jList1.getModel()).addElement( path );
        }
    }

    public List<String> getClasspath()
    {
         List<String> cp = new ArrayList<String>();
         for (int i=0; i<((DefaultListModel)jList1.getModel()).size(); ++i )
         {
             cp.add( "" + ((DefaultListModel)jList1.getModel()).elementAt( i ) );
         }

         return cp;
    }

    public int getDialogResult() {
        return dialogResult;
    }

    public void setDialogResult(int dialogResult) {
        this.dialogResult = dialogResult;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonAdd1;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonMoveDown;
    private javax.swing.JButton jButtonMoveUp;
    private javax.swing.JButton jButtonRemove;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JLabel jLabelClasspath;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    /*
    public void applyI18n(){
                
		this.setTitle(I18n.getString("classPathDialog.title","Classpath"));
                jButtonCancel.setMnemonic(I18n.getString("classPathDialog.buttonCancelMnemonic","c").charAt(0));
                jButtonSave.setMnemonic(I18n.getString("classPathDialog.buttonSaveMnemonic","s").charAt(0));
    }
    */
}