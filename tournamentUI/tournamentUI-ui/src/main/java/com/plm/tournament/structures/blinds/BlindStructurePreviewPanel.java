package com.plm.tournament.structures.blinds;

import com.plm.component.datagrid.BlindStructureGrid;
import com.plm.framework.ui.mvp.BasePanel;
import com.plm.framework.ui.mvp.BaseView;
import com.plm.tournamentCore.blind.BlindStructure;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

/**
 * Panel which show the preview of structure
 * @author Alexandre Lefèvre "Wodric"
 */
public class BlindStructurePreviewPanel extends BasePanel{
	
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
	private Button saveStructureBtn;
	
	/**
	 * the label for saveStructureBtn
	 */
	public static String UPDATE_STRUCTURE_LABEL = "Update";
	
	/**
	 * Panel width
	 */
	public static float PANEL_WIDTH = (float) 44.0;
	
	/**
	 * 
	 */
	private BlindStructureGrid structureGrid;
	
	/**
	 * Layout to display
	 */
	private VerticalLayout mainLayout = new VerticalLayout();
	
	/**
	 * Component parent, used to get listener for exemple
	 */
	private BaseView parentView;

	/**
	 * Panel to preview the structure
	 * @param parentComponent2 the parent component
	 */
	public BlindStructurePreviewPanel(BaseView pParentView) {
		super(STRUCTURE_PREVIEW_PANEL_CAPTION);
		this.parentView = pParentView;
		
		//init classe member
		this.saveStructureBtn = new Button(UPDATE_STRUCTURE_LABEL, this.parentView);
		this.structureGrid = new BlindStructureGrid();

		
		// init layout
		this.mainLayout.addComponent(this.structureGrid);
		this.mainLayout.addComponent(this.saveStructureBtn);
		this.mainLayout.setMargin(true);
		this.mainLayout.setSpacing(true);
		
		this.setContent(mainLayout);
	}
	
	/**
	 * Get the blind structure object display in grid
	 * @return the blind structure object display in grid of this panel
	 */
	public BlindStructure getStructure() {
		return this.structureGrid.getStructure();
	}

}
