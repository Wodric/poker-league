package com.plm.tournament.structures.blinds;

import com.plm.framework.ui.mvp.BasePanel;
import com.vaadin.ui.HorizontalLayout;

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
	 * constant which define the caption of panel
	 */
	private static final String STRUCTURE_BUILDER_PANEL_CAPTION = "Structure builder";
	
	/**
	 * Panel width
	 */
	public static float PANEL_WIDTH = (float) 90.0;
	
	/**
	 * Panel to preview the structure
	 */
	public BlindStructureMainPanel() {
		super(STRUCTURE_BUILDER_PANEL_CAPTION);
		
        BlindStructureMainInformationPanel mainInformationPanel = new BlindStructureMainInformationPanel();
        BlindStructureChipsInformationPanel chipsInformationPanel = new BlindStructureChipsInformationPanel();
        BlindStructurePreviewPanel structurePreviewPanel = new BlindStructurePreviewPanel();
		
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(mainInformationPanel);
        horizontalLayout.addComponent(chipsInformationPanel);
        horizontalLayout.addComponent(structurePreviewPanel);
        horizontalLayout.setMargin(true);
        horizontalLayout.setSpacing(true); 
        horizontalLayout.setSizeFull();
        horizontalLayout.setExpandRatio(mainInformationPanel, BlindStructureMainInformationPanel.PANEL_WIDTH);
        horizontalLayout.setExpandRatio(chipsInformationPanel, BlindStructureChipsInformationPanel.PANEL_WIDTH);
        horizontalLayout.setExpandRatio(structurePreviewPanel, BlindStructurePreviewPanel.PANEL_WIDTH);
		
        this.setWidth(PANEL_WIDTH, Unit.PERCENTAGE);
		this.setContent(horizontalLayout);
	}

}
