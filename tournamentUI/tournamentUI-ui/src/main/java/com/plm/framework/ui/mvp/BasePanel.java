package com.plm.framework.ui.mvp;

import com.vaadin.ui.Panel;

/**
 * Base panel object for plm projet, permit to make a personal panel.
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
