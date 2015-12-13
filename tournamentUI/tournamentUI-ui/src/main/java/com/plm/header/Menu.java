package com.plm.header;

import com.plm.MyUI;
import com.plm.championship.view.ChampionshipView;
import com.plm.internationalization.ParametrizedResourceBundle;
import com.plm.messages.constants.MessagesConstants;
import com.plm.tournament.structures.blinds.BlindStructureView;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;

/**
 * Main manu of application which permit to navigate between differente view / use case
 * @author Alexandre "Wodric" Lefèvre
 *
 */
public class Menu extends MenuBar {
	
	/**
	 * serialization ID
	 */
	private static final long serialVersionUID = 6340196558746178064L;
	
	private static final ParametrizedResourceBundle bundle = ParametrizedResourceBundle.
			getParametrizedBundle(MessagesConstants.UI_MESSAGE_FILE_BASE_NAME, UI.getCurrent().getLocale());
	
	public Menu() {
		
		this.addItem(bundle.getMessage(BlindStructureView.MENU_NAME),
				command -> MyUI.getCurrent().navigateTo(""));
		this.addItem(bundle.getMessage(ChampionshipView.MENU_NAME),
				command -> MyUI.getCurrent().navigateTo(ChampionshipView.NAME));


	}


}
