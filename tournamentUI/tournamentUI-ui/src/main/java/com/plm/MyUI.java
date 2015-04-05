package com.plm;

import javax.servlet.annotation.WebServlet;

import com.plm.tournament.structures.blinds.BlindStructureChipsInformationPanel;
import com.plm.tournament.structures.blinds.BlindStructureMainInformationPanel;
import com.plm.tournament.structures.blinds.BlindStructurePreviewPanel;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
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
        BlindStructureChipsInformationPanel panel2 = new BlindStructureChipsInformationPanel();
        BlindStructurePreviewPanel panel3 = new BlindStructurePreviewPanel();
        Button button = new Button("Click Me");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
            	System.out.println(System.getProperty("catalina.base"));
            }
        });
        layout.addComponent(button);
        layout.addComponent(panel);
        layout.addComponent(panel2);
        layout.addComponent(panel3);

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
