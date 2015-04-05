package com.plm.tournament.structures.blinds;

import com.plm.component.datagrid.BlindStructureGrid;
import com.plm.framework.ui.mvp.BasePanel;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

public class BlindStructurePreviewPanel extends BasePanel {
	
	/**
	 * serialisation UID
	 */
	private static final long serialVersionUID = 4561111650513726471L;
	
	/**
	 * constant which define the caption of panel
	 */
	private static final String STRUCTURE_PREVIEW_PANEL_CAPTION = "Structure preview";
	
	
	/**
	 * Button to save the structure for the user
	 */
	private Button saveStructureBtn = new Button(SAVE_STRUCTURE_LABEL);
	
	/**
	 * the label for saveStructureBtn
	 */
	public static String SAVE_STRUCTURE_LABEL = "Save";
	
	/**
	 * Panel to preview the structure
	 */
	public BlindStructurePreviewPanel() {
		super(STRUCTURE_PREVIEW_PANEL_CAPTION);
		BlindStructureGrid structureGrid = new BlindStructureGrid();
		VerticalLayout mainLayout = new VerticalLayout();
		
		mainLayout.addComponent(structureGrid);
		mainLayout.addComponent(saveStructureBtn);
		
		this.setContent(mainLayout);
	}

}
