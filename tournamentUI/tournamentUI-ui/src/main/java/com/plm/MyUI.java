package com.plm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.plm.championship.view.ChampionshipView;
import com.plm.championship.view.ChampionshipViewImpl;
import com.plm.framework.ui.mvp.BaseSystemMessages;
import com.plm.internationalization.ParametrizedResourceBundle;
import com.plm.messages.constants.MessagesConstants;
import com.plm.tournament.structures.blinds.BlindStructurePresenter;
import com.plm.tournament.structures.blinds.BlindStructureViewImpl;
import com.plm.tournamentCore.blind.BlindStructure;
import com.plm.userManagement.UserManagementUtils;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinServletService;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.ui.UI;

/**
 *
 */
@Theme("valo")
@Widgetset("com.plm.MyAppWidgetset")
@Push(PushMode.MANUAL)
public class MyUI extends UI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 35248692657317641L;

	private static Logger logger = LoggerFactory.getLogger(MyUI.class);

	private Navigator navigator;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		logger.debug("Connection from " + vaadinRequest.getLocale().toLanguageTag() + " for user "
				+ vaadinRequest.getRemoteUser());
		UserManagementUtils.initiateShiro();
		UI.getCurrent().setLocale(vaadinRequest.getLocale());

		VaadinServletService.getCurrent().setSystemMessagesProvider(new BaseSystemMessages());
		ParametrizedResourceBundle bundle = ParametrizedResourceBundle
				.getParametrizedBundle(MessagesConstants.UI_MESSAGE_FILE_BASE_NAME, UI.getCurrent().getLocale());
		this.getPage().setTitle(bundle.getMessage(MessagesConstants.PAGE_MAIN_TITLE));

		this.initViewAndNavigator();
	}

	/**
	 * Init view and add navigator to these view
	 */
	private void initViewAndNavigator() {
		navigator = new Navigator(this, this);

		// init blind structure view
		BlindStructureViewImpl createStructureView = new BlindStructureViewImpl();
		BlindStructure structureModel = createStructureView.getStructureToDisplayFromModel();
		new BlindStructurePresenter(structureModel, createStructureView);
		navigator.addView("" /* BlindStructureView.BLIND_STRCUTURE_VIEW */, createStructureView);
		

		// init blind
		ChampionshipViewImpl championshipeView = new ChampionshipViewImpl();
		navigator.addView(ChampionshipView.NAME, championshipeView);
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {

		/**
		 * generated serial
		 */
		private static final long serialVersionUID = 756460008029467703L;
		
		public void servletInitialized() throws ServletException {
			this.getService().setSystemMessagesProvider(new BaseSystemMessages());
		}
	}

	/**
	 * Method to navigate to a view from it's name
	 * @param pViewName the view name which identify the view to navigate to
	 */
	public void navigateTo(String pViewName) {
		getNavigator().navigateTo(pViewName);
	}

	/**
	 * get the navigator object
	 */
	public Navigator getNavigator() {
		return this.navigator;
	}

	/**
	 * Return the current instance of MyUI
	 * @return
	 */
	public static MyUI getCurrent() {
		return (MyUI) UI.getCurrent();
	}
}
