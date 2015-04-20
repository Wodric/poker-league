package com.plm.framework.ui.mvp;

import com.vaadin.ui.Panel;

/**
 * Base panel object for plm projet, permit to make a personal panel.
 * This abstraction permit to manage the Listeners as I want and 
 * @author Lefevre Alexandre "Wodric"
 */
public abstract class BasePanel extends Panel{

	/**
	 * serialization UID
	 */
	private static final long serialVersionUID = 2285067213954976016L;

	public BasePanel(String pCaption) {
		super(pCaption);
	}
}
