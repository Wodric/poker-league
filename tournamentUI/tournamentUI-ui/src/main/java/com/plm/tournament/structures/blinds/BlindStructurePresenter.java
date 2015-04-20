package com.plm.tournament.structures.blinds;

import com.plm.tournamentCore.blind.BlindStructure;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Field.ValueChangeEvent;
/**
 * The presenter in MVP is a middle-man that handles all user interaction logic, but in an implementation-independent way, 
 * so that it doesn't actually know anything about Vaadin. 
 * It shows data in the view and receives user interaction back from it.
 * @author Alexandre Lefèvre "Wodric"
 */
public class BlindStructurePresenter implements 
	BlindStructureView.BlindStructureViewListener{
	BlindStructure structure;
	BlindStructureView structureView;

	/**
	 * Constructor to link all part of UI and MVP partern
	 * @param pStructure
	 * @param pStructureView
	 */
    public BlindStructurePresenter(BlindStructure pStructure,
    		BlindStructureView  pStructureView) {
        this.structure = pStructure;
        this.structureView  = pStructureView;
        
        structureView.addListener(this);
        
    }


	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		System.out.println(event.getSource().toString());
		System.out.println("trololo");
	}


	@Override
	public void changeValue(ValueChangeEvent event) {
		System.out.println("NOthing");
		
	}
}
