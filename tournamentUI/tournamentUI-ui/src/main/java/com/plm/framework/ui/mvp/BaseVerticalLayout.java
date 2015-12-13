package com.plm.framework.ui.mvp;

import com.plm.header.Header;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

/**
 * This is the high level layout to set header view and footer
 * @author Alexandre "Wodric" Lefèvre
 *
 */
public abstract class BaseVerticalLayout extends VerticalLayout {

	/**
	 * GEnerated serial ID for serialization
	 */
	private static final long serialVersionUID = -7051709573583478996L;

	public BaseVerticalLayout(){
		Header plmHeader = new Header();
		this.addComponent(plmHeader);

	}
	
	/**
	 *  add view component
	 * @param pViewRootComponent the view root component to add
	 */
	public void addRootComponent(Component pViewRootComponent){
		this.addComponent(pViewRootComponent);
	}

}
