package com.plm.championship.view;

import com.plm.framework.ui.mvp.BaseView;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Button.ClickEvent;

/**
 * interface view for championship management
 * it list the method to implement to manage the event on this view
 * @author Alexandre Lefèvre "Wodric"
 */
public interface ChampionshipView extends BaseView {
	
	/**
	 * view name to switch on thanks to navigator
	 */
    public static final String NAME = "CHAMPIONSHIP_VIEW";
	/**
	 * Constant to find the label of championship for menu
	 */
    public static final String MENU_NAME = "menu.championship.name";
    
    interface ChampionshipViewListener{
		void buttonClick(ClickEvent pEvent);
		void valueChange(ValueChangeEvent pEvent);
    }

	void addListener(ChampionshipViewListener listener);
}