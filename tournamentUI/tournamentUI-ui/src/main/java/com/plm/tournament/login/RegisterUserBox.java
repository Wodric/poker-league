package com.plm.tournament.login;

import com.plm.MyUI;
import com.plm.internationalization.ParametrizedResourceBundle;
import com.plm.messages.constants.MessagesConstants;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * The UI box which manage user registration
 * @author Alexandre Lefèvre "Wodric"
 */
public class RegisterUserBox extends Window {
	
	/**
	 * Generated 
	 */
	private static final long serialVersionUID = 7703450302788661365L;

	/**
	 * message bundle
	 */
	private static final ParametrizedResourceBundle bundle = ParametrizedResourceBundle.
			getParametrizedBundle(MessagesConstants.UI_MESSAGE_FILE_BASE_NAME, MyUI.getUserLocale());
	
    /**
     * Size in pixel of login box width
     */
    private static final String LOGIN_BOX_WIDTH_PX = "500px";
    
    
	/**
	 * Text field where user register it's first name
	 */
	private TextField firstNameField;
	
	/**
	 * Text field where user register it's last name
	 */
	private TextField lastNameField;
    
	/**
	 * Text field where user register it's emial
	 */
	private TextField emailField;
	
	/**
	 * Text field where user register it's password
	 */
	private PasswordField passwordField;
	
	/**
	 * Text field where user confirm it's password
	 */
	private PasswordField passwordConfirmedField;
	
	/**
	 * Combo box where user register it's role
	 */
	private ComboBox roleField;
	
	/**
	 * Text field where user confirm it's company if it's a professional
	 */
	private TextField companyField;
	
	/**
	 * Text field where user confirm it's company if it's a professional
	 */
	private TextField phoneNumberField;

	/**
	 * BUtton to cancel  registration
	 */
	private Button cancelButton;

	/**
	 * BUtton to valid registration
	 */
	private Button registerButton;
	
    /**
     * Constructor - Create the registration user box
     */
    public RegisterUserBox(){
    	// init the layout of element in windows
    	VerticalLayout windowsLayout = new VerticalLayout();
    	windowsLayout.addComponent(this.addSignOnForm());
    	windowsLayout.addComponent(this.addRegisterButtons());
    	windowsLayout.setWidth(LOGIN_BOX_WIDTH_PX);
    	windowsLayout.setSpacing(true);
    	windowsLayout.setMargin(true);
    	
    	
    	// init windows
        this.setContent(windowsLayout);
        this.setCaption(bundle.getMessage(MessagesConstants.REGISTER_TITLE));
        this.setWidth(windowsLayout.getWidth(), windowsLayout.getWidthUnits());
        this.setHeight(windowsLayout.getHeight(),windowsLayout.getHeightUnits());
        this.setClosable(false);
        this.setResizable(false);
        this.center();
        
        MyUI.getCurrent().addWindow(this);
    }
    
	/**
	 * add login form and set layout to login box
	 */
    private Component addSignOnForm() {
        FormLayout signOnForm = new FormLayout();
        
        this.firstNameField = new TextField(
        		bundle.getMessage(MessagesConstants.REGISTER_FIELD_FIRSTNAME));
        signOnForm.addComponents(firstNameField); 
        this.lastNameField = new TextField(
        		bundle.getMessage(MessagesConstants.REGISTER_FIELD_LASTNAME));
        signOnForm.addComponents(lastNameField); 
        this.emailField = new TextField(
        		bundle.getMessage(MessagesConstants.REGISTER_FIELD_EMAIL));
        signOnForm.addComponents(emailField); 
        this.passwordField = new PasswordField(
        		bundle.getMessage(MessagesConstants.REGISTER_FIELD_PASSWORD));
        signOnForm.addComponents(passwordField); 
        this.passwordConfirmedField = new PasswordField(
        		bundle.getMessage(MessagesConstants.REGISTER_FIELD_PASSWORD_CONFIRMED));
        signOnForm.addComponents(passwordConfirmedField); 
        this.roleField = new ComboBox(
        		bundle.getMessage(MessagesConstants.REGISTER_FIELD_ROLE));
        signOnForm.addComponents(roleField); 
        this.companyField = new TextField(
        		bundle.getMessage(MessagesConstants.REGISTER_FIELD_COMPANY));
        signOnForm.addComponents(companyField); 
        this.phoneNumberField = new TextField(
        		bundle.getMessage(MessagesConstants.REGISTER_FIELD_PHONE_NUMBER));
        signOnForm.addComponents(phoneNumberField); 
        
        signOnForm.setSpacing(true);
        signOnForm.forEach(component -> component.setWidth("100%"));

        this.emailField.focus();
        return signOnForm;
    }

    
    /**
     * Method which add and organize login button on layout
     */
    private Component addRegisterButtons() {
        HorizontalLayout buttonsLayout = new HorizontalLayout();
        this.cancelButton = new Button(bundle.getMessage(MessagesConstants.REGISTER_BUTTON_CANCEL), 
        		click -> this.close());

	    this.registerButton = new Button(bundle.getMessage(MessagesConstants.REGISTER_BUTTON_SIGNON), 
	    		click -> Notification.show("Not implemented", Notification.Type.TRAY_NOTIFICATION));

        buttonsLayout.addComponents(this.cancelButton, this.registerButton);

        buttonsLayout.setSpacing(true);
        this.registerButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        return buttonsLayout;
    }
}
