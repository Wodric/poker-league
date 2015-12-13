package com.plm.tournament.structures.blinds;

import com.plm.framework.ui.mvp.BaseView;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Button.ClickEvent;

/**
 * interface view for blmind structure it list the method to implement to manage the event on this view
 * @author Alexandre Lefèvre "Wodric"
 */
public interface BlindStructureView extends BaseView {
	
	/**
	 * view name to switch on thanks to navigator
	 */
    public static final String NAME = "BLIND_STRUCTURE_VIEW";
	/**
	 * Constant to find the label of blind structure for menu
	 */
    public static final String MENU_NAME = "menu.blindstructure.name";
    
    interface BlindStructureViewListener{
		void buttonClick(ClickEvent pEvent);
		void valueChange(ValueChangeEvent pEvent);
    }

	void addListener(BlindStructureViewListener listener);
}
