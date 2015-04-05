package com.plm.tournament.structures.blinds;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;


public class BlindStructureViewImpl extends CustomComponent 
	implements BlindStructureView,ClickListener {

    /**
	 * serialization UID
	 */
	private static final long serialVersionUID = -2638576327838174024L;
	
	public BlindStructureViewImpl() {
		super();
	}


	/* Only the presenter registers one listener... */
    List<BlindStructureViewListener> listeners =
            new ArrayList<BlindStructureViewListener>();

    public void addListener(BlindStructureViewListener listener) {
        listeners.add(listener);
    }

	@Override
	public void buttonClick(ClickEvent event) {
		for (BlindStructureViewListener listener: listeners){
            listener.buttonClick(event);	
		}
	}

	@Override
	public void enter(ViewChangeEvent event) {
		
	}
	





}
