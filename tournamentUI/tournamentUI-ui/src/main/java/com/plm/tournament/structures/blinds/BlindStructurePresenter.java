package com.plm.tournament.structures.blinds;

import com.plm.component.datagrid.BlindStructureGrid;
import com.plm.tournamentCore.blind.BlindStructure;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Button.ClickEvent;
/**
 * The presenter in MVP is a middle-man that handles all user interaction logic, but in an implementation-independent way, 
 * so that it doesn't actually know anything about Vaadin. 
 * It shows data in the view and receives user interaction back from it.
 * @author Alexandre Lefèvre "Wodric"
 */
public class BlindStructurePresenter implements 
	BlindStructureView.BlindStructureViewListener{
	BlindStructure structure;
	BlindStructureViewImpl structureView;

	/**
	 * Constructor to link all part of UI and MVP partern
	 * @param pStructure
	 * @param pStructureView
	 */
    public BlindStructurePresenter(BlindStructure pStructure,
    		BlindStructureViewImpl  pStructureView) {
        this.structure = pStructure;
        this.structureView  = pStructureView;
        
        structureView.addListener(this);
        
    }


	@Override
	/**
	 * manage the click event and set action to user click on button
	 * @paramter pEvent the event to manage
	 */
	public void buttonClick(ClickEvent pEvent) {
		if(pEvent.getSource().equals(this.structureView.
				getStructurePreviewPanel().getSaveStructureBtn())){
			// will save the 
			System.out.println("Save action not define");
			
		}
	}

	@Override
	/**
	 * manage the ValueChangeEvent and set action to user click on button
	 * @paramter pEvent the event to manage
	 */
	public void valueChange(ValueChangeEvent pEvent) {
		BlindStructureMainInformationPanel mainInformationPanel = this.structureView
				.getMainInformationPanel();
		BlindStructureChipsInformationPanel chipsInformationPanel = this.structureView
				.getChipsInformationPanel();
		BlindStructurePreviewPanel structurePreviewPanel = this.structureView.getStructurePreviewPanel();
		BlindStructureGrid structureGrid = structurePreviewPanel.getStructureGrid();
		
		// in case we allow or disallow ante
		if(pEvent.getProperty().equals(mainInformationPanel.getAllowAnteField())){
			// in case we allow ante we need to create the ante column and feed it
			if(mainInformationPanel.getAllowAnteFieldValue()){
				structureGrid.enableAnte();
			}
			 //else remove the column
			else{
				structureGrid.removeAnte();
			}
			System.out.println("coucou");
		}
		System.out.println("Nothing on change value");
		
	}
}
