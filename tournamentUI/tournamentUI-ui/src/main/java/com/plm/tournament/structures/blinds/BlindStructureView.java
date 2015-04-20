package com.plm.tournament.structures.blinds;

import com.plm.framework.ui.mvp.BaseView;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Field.ValueChangeEvent;

public interface BlindStructureView extends BaseView {
	
    public static final String BLIND_STRCUTURE_VIEW = "BLIND_STRCUTURE_VIEW";
    
    interface BlindStructureViewListener{

		void buttonClick(ClickEvent event);
		void changeValue(ValueChangeEvent event);
    }

	void addListener(BlindStructureViewListener listener);
}
