package com.plm.tournament.structures.blinds;

import com.plm.tournamentCore.blind.BlindStructure;
import com.vaadin.ui.Button.ClickEvent;

public class BlindStructurePresenter implements 
	BlindStructureView.BlindStructureViewListener{
	BlindStructure structure;
	BlindStructureView structureView;

	  
    public BlindStructurePresenter(BlindStructure pStructure,
    		BlindStructureView  pStructureView) {
        this.structure = pStructure;
        this.structureView  = pStructureView;
        
        structureView.addListener(this);
    }


	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		
	}


}
