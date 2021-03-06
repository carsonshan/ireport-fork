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
package com.jaspersoft.ireport.designer.jrtx;

import com.jaspersoft.ireport.designer.GenericInspectorTopComponent;
import com.jaspersoft.ireport.designer.IReportManager;
import com.jaspersoft.ireport.designer.ReportClassLoader;
import com.jaspersoft.ireport.designer.undo.UndoRedoManager;
import com.jaspersoft.ireport.locale.I18n;
import java.awt.BorderLayout;
import java.awt.Image;
import java.beans.BeanInfo;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import javax.swing.ActionMap;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultEditorKit;
import net.sf.jasperreports.engine.JRSimpleTemplate;
import net.sf.jasperreports.engine.xml.JRXmlTemplateLoader;
import org.netbeans.api.visual.model.ObjectSceneEvent;
import org.netbeans.core.api.multiview.MultiViewHandler;
import org.netbeans.core.api.multiview.MultiViewPerspective;
import org.netbeans.core.api.multiview.MultiViews;
import org.netbeans.core.spi.multiview.CloseOperationState;
import org.netbeans.core.spi.multiview.MultiViewDescription;
import org.netbeans.core.spi.multiview.MultiViewElement;
import org.netbeans.core.spi.multiview.MultiViewElementCallback;
import org.netbeans.core.spi.multiview.MultiViewFactory;
import org.openide.awt.UndoRedo;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.explorer.propertysheet.PropertySheetView;
import org.openide.explorer.view.BeanTreeView;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.HelpCtx;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.Mutex;
import org.openide.util.NbBundle;
import org.openide.util.RequestProcessor;
import org.openide.windows.TopComponent;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.ProxyLookup;
import org.openide.windows.Mode;
import org.openide.windows.TopComponentGroup;
import org.openide.windows.WindowManager;

/**
 * Top component which displays something.
 */
final class JRTXVisualView extends TopComponent 
        implements DocumentListener,
                   MultiViewDescription,
                   MultiViewElement,
                   Runnable,
                   ExplorerManager.Provider {


    /**
     * This is the model. It could be null if the underline jrxml is not valid of a parsing
     * error has occurred.
     */
    private JRSimpleTemplate template = null;

    private AbstractNode noTemplateNode = null;

    private JComponent toolbar = null;

    /**
     * This control the global view when editing a JRCTX file...
     */
    private static Boolean groupVisible = null;

    /**
     * The explorer manager selection (and content)
     */
    private transient ExplorerManager explorerManager;

    private JRTXEditorSupport support;
    private JRTXPreviewPanel previewPanel = null;
    private boolean elementInitialized = false;

    private boolean needModelRefresh = true;
    MultiViewElementCallback callback = null;

    InstanceContent ic;
    AbstractLookup abstractLookup;
    private ProxyLookup lookup = null;


    private static JRTXVisualView instance;
    /** path to the icon used by the component and its open action */
    static final String ICON_PATH = "com/jaspersoft/ireport/designer/resources/jasperreports_jrtx.png";

    private static final String PREFERRED_ID = "JRTXVisualEditorTopComponent";

    private BeanTreeView treeView;
    private PropertySheetView propertySheetView;

    public JRTXVisualView(JRTXEditorSupport ed) {
        super();
        support = ed;
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_NEVER;
    }

    @Override
    public String getDisplayName() {
        return I18n.getString("view.designer");  // NOI18N
    }

    private TemplateNode model = null;
    public TemplateNode getModel() {
        return model;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
 

    @Override
    public Image getIcon() {
        Node nd = ((JRTXDataObject)support.getDataObject()).getNodeDelegate();
        return nd.getIcon( BeanInfo.ICON_COLOR_16x16);
    }

    @Override
    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    public String preferredID() {
        return PREFERRED_ID;
    }

    public MultiViewElement createElement() {

        if (!elementInitialized)
        {

             noTemplateNode = new AbstractNode(Children.LEAF);
             noTemplateNode.setDisplayName("Template not available");


            try {
                assert java.awt.EventQueue.isDispatchThread();


                    elementInitialized = true;

                    ic = new InstanceContent();
                    explorerManager = new ExplorerManager();

                    explorerManager.addPropertyChangeListener(new PropertyChangeListener() {
                        public void propertyChange(PropertyChangeEvent evt) {
                            if (ExplorerManager.PROP_SELECTED_NODES.equals(evt.getPropertyName()))
                            {
                                modelChanged();
                            }
                        }
                    });

                    ActionMap map = getActionMap();
                    //setActionMap(map);
                    map.put(DefaultEditorKit.copyAction, ExplorerUtils.actionCopy(explorerManager));
                    map.put(DefaultEditorKit.cutAction, ExplorerUtils.actionCut(explorerManager));
                    map.put(DefaultEditorKit.pasteAction, ExplorerUtils.actionPaste(explorerManager));
                    map.put("delete", ExplorerUtils.actionDelete(explorerManager, true)); // NOI18N

                    abstractLookup = new AbstractLookup(ic);
                    //if (pc != null)



                    lookup = new ProxyLookup(new Lookup[]{
                        abstractLookup,
                        ExplorerUtils.createLookup(explorerManager, map),
                        support.getDataObject().getLookup()
                    });

                    associateLookup(lookup);
                    //setActivatedNodes( new Node[]{support.getDataObject().getNodeDelegate()});


                    removeAll();
                    support.openDocument().addDocumentListener(this);

                    initComponents();
                    setName(NbBundle.getMessage(JRTXVisualView.class, "CTL_JRTXVisualEditorTopComponent"));
                    setToolTipText(NbBundle.getMessage(JRTXVisualView.class, "HINT_JRTXVisualEditorTopComponent"));
                    setIcon(ImageUtilities.loadImage(ICON_PATH, true));

                    //treeView = new BeanTreeView();
                    //propertySheetView = new PropertySheetView();
                    //jPanelStyles.add(treeView,BorderLayout.CENTER);
                    //jPanelProperties.add(propertySheetView,BorderLayout.CENTER);

                    setPreviewPanel(new JRTXPreviewPanel(this));

                    add(getPreviewPanel(),BorderLayout.CENTER);

                    getExplorerManager().setRootContext(noTemplateNode);

            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        return this;
    }

    public JComponent getVisualRepresentation() {
        return this;
    }

    public void insertUpdate(DocumentEvent e) {
        setNeedModelRefresh(true);
    }

    public void removeUpdate(DocumentEvent e) {
        setNeedModelRefresh(true);
    }

    public void changedUpdate(DocumentEvent e) {
        setNeedModelRefresh(true);
    }

    public synchronized void setNeedModelRefresh(boolean b) {
        needModelRefresh = b;
    }

    public boolean isNeedModelRefresh() {
        return needModelRefresh;
    }

    boolean loading = false;
    public boolean isLoading()
    {
        return loading;
    }


    public JComponent getToolbarRepresentation() {
        if (toolbar == null)
        {
            toolbar = new javax.swing.JPanel();
        }
        return toolbar;
    }




    @Override
    public void componentOpened() {
        setNeedModelRefresh(false);
        refreshModel();
    }

    @Override
    public void componentClosed() {
        syncSatelliteViews(false);
    }

    @Override
    public void componentShowing() {

        if (isNeedModelRefresh())
        {
            refreshModel();
        }
        else
        {
            support.setCurrentModel( template  );
            syncSatelliteViews();
        }

    }

     public JRTXEditorSupport getEditorSupport()
    {
        return support;
    }


     public void refreshModel()
    {
        // This method should run in a separate thread...
        try {
            model = null;
            RequestProcessor.getDefault().post(this);
        } finally {

            // set the model as refreshed even if the model loading is failed
            // this to avoid to reload a errouneus file again.
            setNeedModelRefresh(false);
        }
    }


    public void modelChanged()
    {
        if (getPreviewPanel() != null)
        {
            getPreviewPanel().modelChanged();
        }
    }


     public void updateName() {

        Runnable run = new Runnable() {
            public void run() {
                MultiViewElementCallback c = callback;

                if (c == null) {
                    return;
                }
                TopComponent tc = c.getTopComponent();
                if (tc == null) {
                    return;
                }
                Node nd = ((JRTXDataObject)support.getDataObject()).getNodeDelegate();
                tc.setName(nd.getName() );
                tc.setDisplayName(nd.getDisplayName());
                tc.setHtmlDisplayName(nd.getHtmlDisplayName());
                tc.setToolTipText( nd.getShortDescription() );
            }
        };

        if (SwingUtilities.isEventDispatchThread ())
            run.run();
        else
            SwingUtilities.invokeLater (run);
    }


    public void selectionChanged(ObjectSceneEvent event, Set<Object> previousSelection, Set<Object> newSelection) {

    }


    public ExplorerManager getExplorerManager() {
        return explorerManager;
    }


    private UndoRedoManager undoRedoManager = null;

    @Override
    public UndoRedo getUndoRedo(){
        return getUndoRedoManager();
    }

    @Override
    public Lookup getLookup()
    {
        return super.getLookup();
    }

    public UndoRedo.Manager getUndoRedoManager()
    {
        if (undoRedoManager == null)
        {
            undoRedoManager = new UndoRedoManager();
            undoRedoManager.setLimit(300);
        }
        return undoRedoManager;
    }


    public void run()
    {
        loading = true;
        try {
            //
            support.setCurrentModel( null );


            Thread.currentThread().setContextClassLoader(new ReportClassLoader(IReportManager.getReportClassLoader()));

            //System.out.println("Loading file with encoding: " + FileEncodingQuery.getEncoding(getEditorSupport().getDataObject().getPrimaryFile()));
            //System.out.flush();

            template = (JRSimpleTemplate) JRXmlTemplateLoader.load(support.getInputStream());

            if (template != null)
            {
                javax.swing.SwingUtilities.invokeAndWait(new java.lang.Runnable() {
                    public void run() {

                            // Update UI with the new template...

                            model = new TemplateNode(template, support.getSpecialNodeLookup());
                            getUndoRedoManager().discardAllEdits();
                            explorerManager.setRootContext(model);

                            support.setCurrentModel( template  );



                            try {
                                explorerManager.setSelectedNodes(new Node[]{model});
                            } catch (Exception ex) {
                            }
                    }
                });
            }
        } catch (Exception ex) {
            //Exceptions.printStackTrace(ex);
            //Misc.showErrorMessage( I18n.getString("view.designer.errorloading", ex.getMessage()), // NOI18N
            //                       I18n.getString("view.designer.errorloading.title"), ex); // NOI18N


            noTemplateNode.setDisplayName("Loading error: " + ex.getMessage());
            getExplorerManager().setRootContext(noTemplateNode);
            model = null;
            ex.printStackTrace();
        }
        finally{

            if (getPreviewPanel() != null)
            {
                getPreviewPanel().modelChanged();
            }
            syncSatelliteViews();
            loading = false;
        }
    }

    public void setMultiViewCallback(MultiViewElementCallback callback) {
        this.callback = callback;
        updateName();
    }

    @Override
    public void requestActive() {
        if (callback != null) {
            callback.requestActive();
        } else {
            super.requestActive();
        }
    }

    @Override
    public void componentActivated() {
        syncSatelliteViews();
        updateGroupVisibility();
    }

    @Override
    public void componentDeactivated() {
        updateGroupVisibility();
    }

    @Override
    public void componentHidden() {
        syncSatelliteViews(false);
    }

    public CloseOperationState canCloseElement() {
        if(getEditorSupport().isModified()){
            return MultiViewFactory.createUnsafeCloseState(PREFERRED_ID,
                    null,null);
        }
        return CloseOperationState.STATE_OK;
    }



    private void updateGroupVisibility() {
        WindowManager wm = WindowManager.getDefault();
        final TopComponentGroup group = wm.findTopComponentGroup("ireport_jrctx"); // NOI18N
        if (group == null) {
            return; // group not found (should not happen)
        }
        //
        boolean isVisualViewSelected = false;
        Iterator it = wm.getModes().iterator();
        while (it.hasNext()) {
            Mode mode = (Mode) it.next();
            TopComponent selected = mode.getSelectedTopComponent();
            if (selected != null) {
            MultiViewHandler mvh = MultiViews.findMultiViewHandler(selected);
                if (mvh != null) {
                    MultiViewPerspective mvp = mvh.getSelectedPerspective();
                    if (mvp != null) {
                        String id = mvp.preferredID();
                        if (PREFERRED_ID.equals(id)) {
                            isVisualViewSelected = true;
                            break;
                        }
                    }
                }
            }
        }
        //
        if (isVisualViewSelected && !Boolean.TRUE.equals(groupVisible)) {
            group.open();
        } else if (!isVisualViewSelected && !Boolean.FALSE.equals(groupVisible)) {
            group.close();
        }
        //
        groupVisible = isVisualViewSelected ? Boolean.TRUE : Boolean.FALSE;

    }


    protected void syncSatelliteViews() {
         syncSatelliteViews(true);
    }

    protected void syncSatelliteViews(final boolean b)
    {
        Mutex.EVENT.readAccess(new Runnable() {

                public void run() {
                    if (b)
                    {
                        GenericInspectorTopComponent.getDefault().setCurrentVisualView(JRTXVisualView.this);
                    }
                    else
                    {
                        GenericInspectorTopComponent.getDefault().closingVisualView(JRTXVisualView.this);
                    }
                }
            });
    }

    /**
     * @return the previewPanel
     */
    public JRTXPreviewPanel getPreviewPanel() {
        return previewPanel;
    }

    /**
     * @param previewPanel the previewPanel to set
     */
    public void setPreviewPanel(JRTXPreviewPanel previewPanel) {
        this.previewPanel = previewPanel;
    }
}
