package com.plm.tournament.login;

import com.plm.MyUI;
import com.plm.internationalization.ParametrizedResourceBundle;
import com.plm.messages.constants.MessagesConstants;
import com.plm.strings.validator.EmailValidator;
import com.plm.strings.validator.NameValidator;
import com.plm.strings.validator.PasswordValidator;
import com.plm.strings.validator.PhoneValidator;
import com.plm.user.UserProfile;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.UserError;
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
	
	private static final boolean REQUIRED = true;
	
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
        
        // first name field setting
        this.firstNameField = new TextField(
        		bundle.getMessage(MessagesConstants.REGISTER_FIELD_FIRSTNAME));
        this.firstNameField.setRequired(REQUIRED);
        this.firstNameField.addValueChangeListener(
        		e -> this.validateName(this.firstNameField));
        signOnForm.addComponents(firstNameField); 
        
        // last name field setting
        this.lastNameField = new TextField(
        		bundle.getMessage(MessagesConstants.REGISTER_FIELD_LASTNAME));
        this.lastNameField.setRequired(REQUIRED);
        this.lastNameField.addValueChangeListener(
        		e -> this.validateName(this.lastNameField));
        signOnForm.addComponents(lastNameField); 
        
        // email field setting
        this.emailField = new TextField(
        		bundle.getMessage(MessagesConstants.REGISTER_FIELD_EMAIL));
        this.emailField.setRequired(REQUIRED);
        this.emailField.addValueChangeListener(
        		e -> this.validateEmail());
        signOnForm.addComponents(emailField);

        // password field setting
        this.passwordField = new PasswordField(
        		bundle.getMessage(MessagesConstants.REGISTER_FIELD_PASSWORD));
        this.passwordField.setRequired(REQUIRED);
        this.passwordField.setDescription(
        		bundle.getMessage(MessagesConstants.REGISTER_FIELD_PASSWORD_ERROR_NOTVALID));
        this.passwordField.addValueChangeListener(
        		e -> this.validatePassword(this.passwordField));
        signOnForm.addComponents(passwordField);
        
        // password confirmation field setting
        this.passwordConfirmedField = new PasswordField(
        		bundle.getMessage(MessagesConstants.REGISTER_FIELD_PASSWORD_CONFIRMED));
        this.passwordConfirmedField.setRequired(REQUIRED);
        this.passwordConfirmedField.addValueChangeListener(
        		e -> this.validatePassword(this.passwordConfirmedField));
        signOnForm.addComponents(passwordConfirmedField); 
        
        // role field setting
        this.roleField = new ComboBox(
        		bundle.getMessage(MessagesConstants.REGISTER_FIELD_ROLE));
        this.roleField.setRequired(REQUIRED);
        signOnForm.addComponents(roleField); 
        this.fillRoleComboBox();
        this.roleField.addValueChangeListener(
        		e -> this.validateProfile(e));

        
        // company field setting
        this.companyField = new TextField(
        		bundle.getMessage(MessagesConstants.REGISTER_FIELD_COMPANY));
        signOnForm.addComponents(companyField); 
        
        // phone number field setting
        this.phoneNumberField = new TextField(
        		bundle.getMessage(MessagesConstants.REGISTER_FIELD_PHONE_NUMBER));
        this.phoneNumberField.addValueChangeListener(
        		e -> this.validatePhone());
        signOnForm.addComponents(phoneNumberField); 
        
        signOnForm.setSpacing(true);
        signOnForm.forEach(component -> component.setWidth("100%"));

        this.firstNameField.focus();
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
	    		click -> Notification.show(bundle.getMessage(MessagesConstants.GLOBAL_NOT_IMPLEMENTED),
	    				Notification.Type.TRAY_NOTIFICATION));

        buttonsLayout.addComponents(this.cancelButton, this.registerButton);

        buttonsLayout.setSpacing(true);
        this.registerButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        return buttonsLayout;
    }
    
    private void validateName(TextField pNameField){
    	if(!pNameField.getValue().equals("") && !NameValidator.validate(pNameField.getValue())){
    		pNameField.setComponentError(
    				new UserError(
    						bundle.getMessage(MessagesConstants.REGISTER_FIELD_NAME_ERROR_NOTVALID)));
    	}
    	else{
    		pNameField.setComponentError(null);
    	}
    }
    
    /**
     * Validate password and  if both password field are not empty verify their are identical.
     * Send UI error to user in case of error
     * @param pPasswordField password to validate
     */
    private void validatePassword(PasswordField pPasswordField){
    	// verify password respect the password policy
    	if(! pPasswordField.getValue().equals("") && !PasswordValidator.validate(pPasswordField.getValue())){
    		pPasswordField.setComponentError(
    				new UserError("")); 
    		// message in description focus user attention to field to make them read the description
    		return;
    	}
    	
    	//verify both are identical
    	String password = this.passwordField.getValue();
    	String passwordConfirmed = this.passwordConfirmedField.getValue();
    	if(passwordConfirmed != null && passwordConfirmed != null 
    			&& !passwordConfirmed.equals("") && !password.equals("")
    			&& !passwordConfirmed.equals(password)){
    		this.passwordField.setComponentError(
    				new UserError(bundle.getMessage(MessagesConstants.REGISTER_FIELD_PASSWORD_ERROR_NOTEQUALS)));
    		this.passwordConfirmedField.setComponentError(
    				new UserError(bundle.getMessage(MessagesConstants.REGISTER_FIELD_PASSWORD_ERROR_NOTEQUALS)));
    	}
    	else{
    		this.passwordField.setComponentError(null);
    		this.passwordConfirmedField.setComponentError(null);
    	}
    }
    
    /**
     * verify the mail format and send UI error if email is malformed
     */
    private void validateEmail(){
    	if(!this.emailField.getValue().equals("") 
    			&& !EmailValidator.validate(this.emailField.getValue())){
    		this.emailField.setComponentError(
    				new UserError(bundle.getMessage(MessagesConstants.REGISTER_FIELD_EMAIL_ERROR_NOTVALID)));
    	}
    	else{
    		this.emailField.setComponentError(null);
    	}
    }
    
    /**
     * verify the phone number format and send UI error if email is malformed
     */
    private void validatePhone(){
    	if(!this.phoneNumberField.getValue().equals("") 
    			&& !PhoneValidator.validate(this.phoneNumberField.getValue())){
    		this.phoneNumberField.setComponentError(
    				new UserError(bundle.getMessage(MessagesConstants.REGISTER_FIELD_PHONENUMBER_ERROR_NOTVALID)));
    	}
    	else{
    		this.phoneNumberField.setComponentError(null);
    	}
    }
    
    /**
     * Fill the combo box role.
     */
    private void fillRoleComboBox(){
    	for(UserProfile aProfile:UserProfile.getAllowedUserChoise()){
            this.roleField.addItem(aProfile);
            this.roleField.setItemCaption(aProfile, bundle.getMessage(aProfile.name()));
    	}
    }
    
    /**
     * Fill the combo box role.
     * @param e the event
     */
    private void validateProfile(ValueChangeEvent e){
    	if (e.getProperty().getValue() == null){
    		this.roleField.setComponentError(
    				new UserError(
    						bundle.getMessage(
    								MessagesConstants.REGISTER_FIELD_ROLE_ERROR_NOTVALID)));
    	}
    	else{
    		this.roleField.setComponentError(null);
    	}
    }
    
    private void registerUser(){
    	// save user & user information
    	
    	// depending of role,  user role
    }
    
}
