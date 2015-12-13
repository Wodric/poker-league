package com.plm.header;

import com.vaadin.ui.HorizontalLayout;

/**
 * The header containning menus
 * @author Alexandre "Wodric" Lefèvre
 *
 */
public class Header extends HorizontalLayout {
	
	/**
	 * GEnerated Id to serialization
	 */
	private static final long serialVersionUID = 4365706348002937743L;

	public Header(){
		Menu mainMenu = new Menu();
		UserMenu userMenu = new UserMenu();
		this.addComponents(mainMenu, userMenu);
		// main menu takes 4/5 and user menu 1/5
		/**
		 *  This is just to have a first version of menu and will change
		 */
		this.setExpandRatio(mainMenu, 4);
		this.setExpandRatio(userMenu, 1);
		
		this.setWidth(100, Unit.PERCENTAGE);
		this.setHeight(45, Unit.PIXELS);

	}

}
