package com.plm.tournament.structures.blinds;

import com.vaadin.ui.Button.ClickEvent;

public interface BlindStructureView {
	

    interface BlindStructureViewListener {
    	void buttonClick(ClickEvent event);
    }

    public void addListener(BlindStructureViewListener listener);

}
