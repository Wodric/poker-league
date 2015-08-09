package com.plm;

import java.util.Locale;

import javax.servlet.annotation.WebServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.plm.internationalization.ParametrizedResourceBundle;
import com.plm.messages.constants.MessagesConstants;
import com.plm.tournament.structures.blinds.BlindStructurePresenter;
import com.plm.tournament.structures.blinds.BlindStructureViewImpl;
import com.plm.tournamentCore.blind.BlindStructure;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

/**
 *
 */
@Theme("valo")
@Widgetset("com.plm.MyAppWidgetset")
public class MyUI extends UI {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = LoggerFactory.getLogger(MyUI.class);
	
    Navigator navigator;
    
    private static Locale userLocale; 
    
	@Override
    protected void init(VaadinRequest vaadinRequest) {
	   
	    logger.debug("Connection from " + vaadinRequest.getLocale().toLanguageTag() + " for user " + vaadinRequest.getRemoteUser());

		userLocale = vaadinRequest.getLocale();
		ParametrizedResourceBundle bundle = ParametrizedResourceBundle.
				getParametrizedBundle(MessagesConstants.UI_MESSAGE_FILE_BASE_NAME, userLocale);
        this.getPage().setTitle(
        		bundle.getMessage(MessagesConstants.PAGE_MAIN_TITLE));
        
        BlindStructureViewImpl createStructureView = new BlindStructureViewImpl();
        BlindStructure structureModel = createStructureView.getStructureToDisplayFromModel();
        
        // Create a navigator to control the views
        navigator = new Navigator(this, this);
        navigator.addView("" /*BlindStructureView.BLIND_STRCUTURE_VIEW*/, createStructureView);
        
        new BlindStructurePresenter(structureModel,createStructureView);
    }
	
	public static Locale getUserLocale(){
		return userLocale;
	}

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
