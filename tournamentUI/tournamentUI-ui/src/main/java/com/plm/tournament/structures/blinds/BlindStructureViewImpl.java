package com.plm.tournament.structures.blinds;

import java.util.ArrayList;
import java.util.List;

import com.plm.framework.ui.mvp.BaseVerticalLayout;
import com.plm.tournamentCore.blind.BlindStructure;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button.ClickEvent;

/**
 * View implementation of the Blind structure creation view. 
 * It class compose the UI and diffuse the Event to presenter
 * @author Alexandre Lefèvre "Wodric"
 *
 */
public class BlindStructureViewImpl extends BaseVerticalLayout 
	implements BlindStructureView,BlindStructureView.BlindStructureViewListener {

    /**
	 * serialization UID
	 */
	private static final long serialVersionUID = -2638576327838174024L;
	
	/**
	 * Main panel that compose the BlindStructure
	 */
	private BlindStructureMainPanel mainPanel;
	

	/* Only the presenter registers one listener... */
    List<BlindStructureViewListener> listeners =
            new ArrayList<BlindStructureViewListener>();
	
	public BlindStructureViewImpl() {
		this.mainPanel = new BlindStructureMainPanel(this);
	}

	@Override
    public void addListener(BlindStructureViewListener listener) {
        this.listeners.add(listener);
    }

	@Override
	/**
	 * Diffuse button click event to presenter
	 * @parameter pEvent Click event to diffuse to presenter
	 */
	public void buttonClick(ClickEvent pEvent) {
		for (BlindStructureViewListener listener: this.listeners){
            listener.buttonClick(pEvent);	
		}
	}
	
	/**
	 * Return the Model (blind structure) will be display in UI
	 * @return the blind structure object to display
	 */
	public BlindStructure getStructureToDisplayFromModel(){
		return this.mainPanel.getStructurePreviewPanel().getStructureGrid().getStructure();
	}

	@Override
	/**
	 * Method execute when application enter in this view
	 */
	public void enter(ViewChangeEvent event) {
		this.addRootComponent(this.mainPanel);		
	}
	
	/**
	 * get main panel that compose the view
	 * @return the main panel
	 */
	public BlindStructureMainPanel getPanel() {
		return this.mainPanel;
	}
	
	/**
	 * get chip information panel that compose the view
	 * @return the chips information panel
	 */
	public BlindStructureChipsInformationPanel getChipsInformationPanel() {
		return this.mainPanel.getChipsInformationPanel();
	}
	
	/**
	 * get main information panel that compose the view
	 * @return the main information panel
	 */
	public BlindStructureMainInformationPanel getMainInformationPanel() {
		return this.mainPanel.getMainInformationPanel();
	}
	
	
	/**
	 * get structure preview panel that compose the view
	 * @return the structure preview panel
	 */
	public BlindStructurePreviewPanel getStructurePreviewPanel() {
		return this.mainPanel.getStructurePreviewPanel();
	}

	@Override
	public void valueChange(ValueChangeEvent pEvent) {
		for (BlindStructureViewListener listener: this.listeners){
            listener.valueChange(pEvent);
		}
	}
}
