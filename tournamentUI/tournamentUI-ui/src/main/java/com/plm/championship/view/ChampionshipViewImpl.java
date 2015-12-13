package com.plm.championship.view;

import java.util.ArrayList;
import java.util.List;

import com.plm.framework.ui.mvp.BaseVerticalLayout;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * View implementation of the championship management view. 
 * It class compose the UI and diffuse the Event to presenter
 * @author Alexandre Lefèvre "Wodric"
 *
 */
public class ChampionshipViewImpl extends BaseVerticalLayout 
	implements ChampionshipView,ChampionshipView.ChampionshipViewListener {

    /**
	 * serialization UID
	 */
	private static final long serialVersionUID = -2638576327838174024L;
	

	/* Only the presenter registers one listener... */
    List<ChampionshipViewListener> listeners =
            new ArrayList< ChampionshipViewListener>();
    
    
    private TabSheet mainTabs;
	
	public ChampionshipViewImpl() {
		mainTabs = new TabSheet();
		mainTabs.setHeight(80, Unit.PERCENTAGE);
		mainTabs.addStyleName(ValoTheme.TABSHEET_FRAMED);
		 
		// TO DO code sample to generate tabs, will me change with new use case implementation
        for (int i = 1; i <= 3; i++) {
            final VerticalLayout layout = new VerticalLayout(new Label(
            		"Tab " + i ));
            layout.setMargin(true);
            mainTabs.addTab(layout, "Tab " + i);
        }
	}


	@Override
	/**
	 * Diffuse button click event to presenter
	 * @parameter pEvent Click event to diffuse to presenter
	 */
	public void buttonClick(ClickEvent pEvent) {
		for ( ChampionshipViewListener listener: this.listeners){
            listener.buttonClick(pEvent);	
		}
	}


	@Override
	/**
	 * Method execute when application enter in this view
	 */
	public void enter(ViewChangeEvent event) {
		this.addRootComponent(this.mainTabs);		
	}


	@Override
	public void valueChange(ValueChangeEvent pEvent) {
		for ( ChampionshipViewListener listener: this.listeners){
            listener.valueChange(pEvent);
		}
	}

	@Override
	public void addListener(ChampionshipViewListener listener) {
		this.listeners.add(listener);
	}
}
