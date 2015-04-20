package com.plm;

import javax.servlet.annotation.WebServlet;

import com.plm.tournament.structures.blinds.BlindStructurePresenter;
import com.plm.tournament.structures.blinds.BlindStructureView;
import com.plm.tournament.structures.blinds.BlindStructureViewImpl;
import com.plm.tournamentCore.blind.BlindStructure;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 *
 */
@Theme("mytheme")
@Widgetset("com.plm.MyAppWidgetset")
public class MyUI extends UI {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    Navigator navigator;
    
	@Override
    protected void init(VaadinRequest vaadinRequest) {
        this.getPage().setTitle("Navigation Example");
        
        BlindStructureViewImpl createStructureView = new BlindStructureViewImpl();
        BlindStructure structureModel = createStructureView.getStructureToDisplayFromModel();
        
        // Create a navigator to control the views
        navigator = new Navigator(this, this);
        navigator.addView("" /*BlindStructureView.BLIND_STRCUTURE_VIEW*/, createStructureView);
        
        BlindStructurePresenter structurePresenter = new BlindStructurePresenter(structureModel,createStructureView);

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
