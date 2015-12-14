package com.plm.tournament.login;

import org.apache.shiro.authc.AuthenticationException;

import com.plm.MyUI;
import com.plm.internationalization.ParametrizedResourceBundle;
import com.plm.messages.constants.MessagesConstants;
import com.plm.userManagement.UserManagementUtils;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.UserError;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * The UI box which manage login
 * @author Alexandre Lefèvre "Wodric"
 */
public class LoginBox extends Window {

	/**
	 * Generated 
	 */
	private static final long serialVersionUID = -784268133809934972L;
	
	/**
	 * message bundle
	 */
	private static final ParametrizedResourceBundle bundle = ParametrizedResourceBundle.
			getParametrizedBundle(MessagesConstants.UI_MESSAGE_FILE_BASE_NAME, UI.getCurrent().getLocale());
	
	/**
	 * Text field where user enter it's login
	 */
	private TextField usernameField;
	
	/**
	 * Field where user enter is password
	 */
    private PasswordField passwordField;
    
    /**
     * Button to launch identifier recovery procedure
     */
    private Button forgotButton;
    
    /**
     * Button for login action
     */
    private Button loginButton;
    
    /**
     * Button to launch inscription
     */
    private Button logOnButton;
    
    /**
     * Button for cancel action and back to previous view
     */
    private Button cancelLoginButton;
    
    /**
     * Size in pixel of login box width
     */
    private static final String LOGIN_BOX_WIDTH_PX = "400px";
    
    /**
     * The button which need the login
     */
    private Button originButton;
    
    /**
     * The menu item which nee the login
     */
    private MenuItem originMenuItem;
    
    /**
     * Constructor - Create the box
     * @param pButton the button which need the login 
     */
    public LoginBox(Button pButton){
    	this.originButton = pButton;
    	this.buildWindows();
    }
    
    public LoginBox(MenuItem pOriginMenuItem){
    	this.originMenuItem = pOriginMenuItem;
    	this.buildWindows();
    }
	
    private void buildWindows(){
    	// init the layout of ellement in windows
    	VerticalLayout windowsLayout = this.createAndConfigureMainLayout();
    	
    	// init windows
        this.configureWindows(windowsLayout);
        
        MyUI.getCurrent().addWindow(this);
    }
    
    /**
     * Create the layout, add element to it and configure the layout
     * @return the layout build
     */
    private VerticalLayout createAndConfigureMainLayout(){
    	VerticalLayout windowsLayout = new VerticalLayout();
    	
    	windowsLayout.setWidth(LOGIN_BOX_WIDTH_PX);
    	windowsLayout.setSpacing(true);
    	windowsLayout.setMargin(true);
    	windowsLayout.addComponent(this.addLoginForm());
    	
    	// login button
    	Component loginButton = this.addLoginButtons();
    	windowsLayout.addComponent(loginButton);
    	windowsLayout.setComponentAlignment(loginButton,Alignment.BOTTOM_RIGHT);
    	windowsLayout.addComponent(this.addNoAccountCaption());
    	
    	// no account button
    	Component noAccountButton = this.addNoAccountButtons();
    	windowsLayout.addComponent(noAccountButton);
    	windowsLayout.setComponentAlignment(noAccountButton,Alignment.BOTTOM_RIGHT);
    	return windowsLayout;
    }
    
    /**
     * Configure the windows, add the layoutToSet in windows
     * @param layoutToSet the layout to set at windows
     */
    private void configureWindows(Layout layoutToSet){
        this.setContent(layoutToSet);
        this.setCaption(bundle.getMessage(MessagesConstants.LOGIN_TITLE));
        this.setWidth(layoutToSet.getWidth(), layoutToSet.getWidthUnits());
        this.setHeight(layoutToSet.getHeight(),layoutToSet.getHeightUnits());
        this.setClosable(false);
        this.setResizable(false);
        this.center();
    }
    
    /**
     * set title for no account user part
     */
	private Component addNoAccountCaption() {
        Label noAccountCaption = new Label(
        		bundle.getMessage(MessagesConstants.LOGIN_TITLE_NOACCOUNT));
        return noAccountCaption;
    }

	/**
	 * add login form and set layout to login box
	 */
    private Component addLoginForm() {
        FormLayout loginForm = new FormLayout();
        this.usernameField = new TextField(
        		bundle.getMessage(MessagesConstants.LOGIN_FIELD_LOGIN));
        this.passwordField = new PasswordField(
        		bundle.getMessage(MessagesConstants.LOGIN_FIELD_PASSWORD));
        loginForm.addComponents(usernameField, passwordField);

        loginForm.setSpacing(true);
        loginForm.forEach(component -> component.setWidth("100%"));

        this.usernameField.focus();
        return loginForm;
    }

    /**
     * Method which add and organize login button on layout
     */
    private Component addLoginButtons() {
        HorizontalLayout buttonsLayout = new HorizontalLayout();
        this.forgotButton = new Button(bundle.getMessage(MessagesConstants.LOGIN_BUTTON_FORGOT), 
        		click -> Notification.show(bundle.getMessage(MessagesConstants.GLOBAL_NOT_IMPLEMENTED),
        				Notification.Type.TRAY_NOTIFICATION));

	    this.loginButton = new Button(bundle.getMessage(MessagesConstants.LOGIN_BUTTON_LOGIN), 
	        		click -> this.login(getUsernameField().getValue(),
	        		getPasswordField().getValue()));

		
        buttonsLayout.addComponents(this.forgotButton, this.loginButton);

        buttonsLayout.setSpacing(true);
        this.loginButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        return buttonsLayout;
    }
    
    /**
     * Method which add and organize button which user with no account will user
     */
    private Component addNoAccountButtons() {
        HorizontalLayout buttonsLayout = new HorizontalLayout();
        this.cancelLoginButton = new Button(
        		bundle.getMessage(MessagesConstants.LOGIN_BUTTON_CANCEL),
        		click -> this.close());
        this.logOnButton = new Button(
        		bundle.getMessage(MessagesConstants.LOGIN_BUTTON_SIGNON),
        		click -> this.registerUser());
        buttonsLayout.addComponents(this.cancelLoginButton, this.logOnButton);

        buttonsLayout.setSpacing(true);

        return buttonsLayout;
    }
    
    /**
     * 
     * @param pUserName string representing the username enter by user
     * @param pPassword string representing the password enter by user
     */
    private void login(String pUserName, String pPassword){
    	try{
    		UserManagementUtils.login(
	        		getUsernameField().getValue(),
	        		getPasswordField().getValue());
        	if(this.originButton != null){
            	this.originButton.click();
        	}
        	else if(this.originMenuItem != null){
        		this.originMenuItem.getCommand().menuSelected(this.originMenuItem);
        	}
        	this.close();
    	}
    	catch(AuthenticationException e){
    		new ErrorLoginNotification();
    		this.getUsernameField().setValue("");
    		this.getPasswordField().setValue("");
    		this.getLoginButton().setComponentError(
    				new UserError(
    						bundle.getMessage(MessagesConstants.LOGIN_ERROR_TITLE)));
    	}
    }
    
    /**
     * register User
     */
    private void registerUser(){
    	new RegisterUserBox(this.originButton);
    	this.close();
    }

	/**
     * Get Text field where user enter it's login
     * @return the text field
     */
    public TextField getUsernameField() {
		return this.usernameField;
	}

    /**
     *  Field where user enter is password
     * @return the password field
     */
	public PasswordField getPasswordField() {
		return this.passwordField;
	}

	/**
	 * get the button which launch identifier recovery procedure
	 * @return the button
	 */
	public Button getForgotButton() {
		return this.forgotButton;
	}

	/**
	 * get the button which launch login procedure
	 * @return the button
	 */
	public Button getLoginButton() {
		return this.loginButton;
	}
	
	/**
	 * get the button which launch logon procedure
	 * @return the button
	 */
    public Button getLogOnButton() {
		return this.logOnButton;
	}

	/**
	 * get the button which cancel login procedure
	 * @return the button
	 */
	public Button getCancelLoginButton() {
		return this.cancelLoginButton;
	}

 }