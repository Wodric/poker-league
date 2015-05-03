package com.plm.tournament.structures.blinds;

import com.plm.framework.ui.mvp.BaseView;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Button.ClickEvent;

/**
 * interface view for blmind structure it list the method to implement to manage the event on this view
 * @author Alexandre Lefèvre "Wodric"
 */
public interface BlindStructureView extends BaseView {
	
    public static final String BLIND_STRCUTURE_VIEW = "BLIND_STRCUTURE_VIEW";
    
    interface BlindStructureViewListener{
		void buttonClick(ClickEvent pEvent);
		void valueChange(ValueChangeEvent pEvent);
    }

	void addListener(BlindStructureViewListener listener);
}
