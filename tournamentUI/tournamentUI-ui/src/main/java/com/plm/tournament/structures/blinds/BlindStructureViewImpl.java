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

/**
 * View implementation of the Blind structure creation view. 
 * It class compose the UI and diffuse the Event to presenter
 * @author Alexandre Lefèvre "Wodric"
 *
 */
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
	/**
	 * Diffuse button click event to presenter
	 * @parameter pEvent Click event to diffuse to presenter
	 */
	public void buttonClick(ClickEvent pEvent) {
		for (BlindStructureViewListener listener: listeners){
            listener.buttonClick(pEvent);	
		}
	}
	
	@Override
	/**
	 * Diffuse value change event to presenter
	 * @parameter pEvent value change t to diffuse to presenter
	 */
	public void valueChange(ValueChangeEvent pEvent) {
		for (BlindStructureViewListener listener: listeners){
            ((ValueChangeListener) listener).valueChange(pEvent);	
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
	/**
	 * Method execute when application enter in this view
	 */
	public void enter(ViewChangeEvent event) {
		this.panel =  new BlindStructureMainPanel(this);
		Layout layout = new HorizontalLayout(this.panel);
		this.setCompositionRoot(layout);		
	}
}
