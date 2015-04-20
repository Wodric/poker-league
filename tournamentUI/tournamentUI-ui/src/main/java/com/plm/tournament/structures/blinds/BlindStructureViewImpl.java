package com.plm.tournament.structures.blinds;

import java.util.ArrayList;
import java.util.List;

import com.plm.tournamentCore.blind.BlindStructure;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;


public class BlindStructureViewImpl extends CustomComponent 
	implements BlindStructureView,ClickListener {

    /**
	 * serialization UID
	 */
	private static final long serialVersionUID = -2638576327838174024L;
	
	/**
	 * Main panel that compose the BlindStructure
	 */
	private BlindStructureMainPanel panel;
	

	/* Only the presenter registers one listener... */
    List<BlindStructureViewListener> listeners =
            new ArrayList<BlindStructureViewListener>();
	
	public BlindStructureViewImpl() {
		this.panel = new BlindStructureMainPanel(this);
		this.panel.setSizeFull();
	}

	@Override
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
	public void valueChange(ValueChangeEvent event) {
		for (BlindStructureViewListener listener: listeners){
            ((ValueChangeListener) listener).valueChange(event);	
		}
	}
	
	/**
	 * Return the Model (blind structure) will be display in UI
	 * @return the blind structure object to display
	 */
	public BlindStructure getStructureToDisplayFromModel(){
		return this.panel.getStructureToDisplay();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		this.panel =  new BlindStructureMainPanel(this);
		Layout layout = new HorizontalLayout(this.panel);
		this.setCompositionRoot(layout);		
	}
}
