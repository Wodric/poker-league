package com.plm.tournament.structures.blinds;

import com.plm.framework.ui.mvp.BasePanel;
import com.plm.framework.ui.mvp.BaseView;
import com.plm.internationalization.ParametrizedResourceBundle;
import com.plm.messages.constants.MessagesConstants;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

/**
 * The main panel for creation of a structure
 * @author Alexandre Lefèvre "Wodric"
 */
public class BlindStructureMainPanel extends BasePanel {
	
	/**
	 * Generated serial version UID
	 */
	private static final long serialVersionUID = -3501751795892817295L;
	
	/**
	 * bundle for message
	 */
	private static final ParametrizedResourceBundle bundle = ParametrizedResourceBundle.
			getParametrizedBundle(MessagesConstants.UI_MESSAGE_FILE_BASE_NAME, UI.getCurrent().getLocale());
	
	/**
	 * constant which define the caption of panel
	 */
	private static final String STRUCTURE_BUILDER_PANEL_CAPTION = bundle.
			getMessage(MessagesConstants.INFORMATION_PANEL_INFORMATIONS_TITLE);
	
	/**
	 * Panel width
	 */
	public static final boolean IS_STRUCTURE_EDITABLE = true;
	
	/**
	 * Panel width
	 */
	public static final float PANEL_WIDTH = (float) 90.0;
	
	/**
	 * Panel with main information to calculate the structure to create
	 */
	private BlindStructureMainInformationPanel mainInformationPanel;
	/**
	 * Panel with chips information to calculate the structure to create
	 */
    private BlindStructureChipsInformationPanel chipsInformationPanel;
    
	/**
	 * Panel with Structutre preview
	 */
    private BlindStructurePreviewPanel structurePreviewPanel;
    
    /**
     * Horizontal layout use to organize the panels
     */
    private HorizontalLayout horizontalLayout = new HorizontalLayout();
    
	/**
	 * Component parent, used to get listener for exemple
	 */
    BaseView parentComponent;
	
	/**
	 * Panel to preview the structure
	 * @param blindStructureViewImpl 
	 */
	public BlindStructureMainPanel(BaseView pParentView) {
		super(STRUCTURE_BUILDER_PANEL_CAPTION);
		this.parentComponent = pParentView;
		
		this.mainInformationPanel = new BlindStructureMainInformationPanel(parentComponent);
	    this.chipsInformationPanel = new BlindStructureChipsInformationPanel(parentComponent);
	    this.structurePreviewPanel = new BlindStructurePreviewPanel(parentComponent,IS_STRUCTURE_EDITABLE);
		
        this.horizontalLayout = new HorizontalLayout();
        this.horizontalLayout.addComponent(mainInformationPanel);
        this.horizontalLayout.addComponent(chipsInformationPanel);
        this.horizontalLayout.addComponent(structurePreviewPanel);
        this.horizontalLayout.setMargin(true);
        this.horizontalLayout.setSpacing(true); 
        this.horizontalLayout.setSizeFull();
        this.horizontalLayout.setExpandRatio(mainInformationPanel, BlindStructureMainInformationPanel.PANEL_WIDTH);
        this.horizontalLayout.setExpandRatio(chipsInformationPanel, BlindStructureChipsInformationPanel.PANEL_WIDTH);
        this.horizontalLayout.setExpandRatio(structurePreviewPanel, BlindStructurePreviewPanel.PANEL_WIDTH);
        this.horizontalLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        this.setWidth(PANEL_WIDTH, Unit.PERCENTAGE);
		this.setContent(this.horizontalLayout);
	}
	
	/**
	 * get the main panel information. It permit to get the elment of UI
	 * @return mainInformationPanel panel
	 */
	public BlindStructureMainInformationPanel getMainInformationPanel() {
		return this.mainInformationPanel;
	}

	/**
	 * get the chips panel information. It permit to get the elment of UI
	 * @return chipsInformationPanel panel
	 */
	public BlindStructureChipsInformationPanel getChipsInformationPanel() {
		return this.chipsInformationPanel;
	}

	/**
	 * get the structure preview panel. It permit to get the elment of UI
	 * @return structurePreviewPanel panel
	 */
	public BlindStructurePreviewPanel getStructurePreviewPanel() {
		return this.structurePreviewPanel;
	}
}
