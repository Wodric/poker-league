package com.plm;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;

import com.plm.component.datagrid.BlindStructureGrid;
import com.plm.tournament.structures.blinds.BlindStructureMainInformationPanel;
import com.plm.tournamentCore.blind.BlindLevel;
import com.plm.tournamentCore.blind.BlindStructure;
import com.plm.tournamentCore.chip.ChipsSet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
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

	@Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);
        BlindStructureMainInformationPanel panel = new BlindStructureMainInformationPanel();
        Button button = new Button("Click Me");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
            	System.out.println(System.getProperty("catalina.base"));
            }
        });
        layout.addComponent(button);
        layout.addComponent(panel);

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
