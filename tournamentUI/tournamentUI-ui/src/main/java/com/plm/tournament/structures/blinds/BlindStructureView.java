package com.plm.tournament.structures.blinds;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button.ClickEvent;

public interface BlindStructureView extends View {
	

    interface BlindStructureViewListener {
    	void buttonClick(ClickEvent event);
    }

    public void addListener(BlindStructureViewListener listener);

}
