package com.plm.header;

import com.plm.MyUI;
import com.plm.internationalization.ParametrizedResourceBundle;
import com.plm.messages.constants.MessagesConstants;
import com.plm.tournament.login.LoginBox;
import com.plm.tournament.login.RegisterUserBox;
import com.plm.userManagement.UserManagementUtils;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;

/**
 * This objecti represent the menu bar for user information,
 * offer some acces like user informaiton of disconnection
 * @author Alexandre "Wodric" Lefèvre
 *
 */
public class UserMenu extends MenuBar{
	
	/**
	 * serialization ID
	 */
	private static final long serialVersionUID = 6340196558746178064L;
	
	private static final ParametrizedResourceBundle bundle = ParametrizedResourceBundle.
			getParametrizedBundle(MessagesConstants.UI_MESSAGE_FILE_BASE_NAME, UI.getCurrent().getLocale());
	
	/**
	 * Constant to find the label of login button on user menu
	 */
    public static final String MENU_NAME_UNCONNECT_LOGIN = "menu.user.login";
	/**
	 * Constant to find the label of register button on user menu
	 */
    public static final String MENU_NAME_UNCONNECT_SIGNUP = "menu.user.signup";
	/**
	 * Constant to find the label of profile button on user menu
	 */
    public static final String MENU_NAME_CONNECT_PROFILE= "menu.user.profile";
	/**
	 * Constant to find the label of disconnect button on user menu
	 */
    public static final String MENU_NAME_CONNECT_DISCONNECT= "menu.user.disconnect";

	
    MenuItem mainUserItem;
    MenuItem profileItem;
    MenuItem disconnectItem;
    MenuItem loginItem;
    MenuItem signupItem;
    
	/**
	 * Build user menu, if user is log display the personnal user menu otherwise 
	 * display login and register button
	 */
	public UserMenu() {
		
		this.setImmediate(true);

		if(!UserManagementUtils.isAuthenticated()){
			this.buildDefaultMenu();
		}
		else{
			this.buildLogUserMenu();
		}
	}
	
	/**
	 * Add item to menu bar in case a use is log.
	 * THis add, menu to manage user profile and a disconnection mmenu item
	 */
	private void buildLogUserMenu(){
		this.mainUserItem = this.addItem(UserManagementUtils.getPrincipal(), null);
		this.profileItem = this.mainUserItem.addItem(bundle.getMessage(MENU_NAME_CONNECT_PROFILE),
				null);
		this.signupItem = this.mainUserItem.addItem(bundle.getMessage(MENU_NAME_CONNECT_DISCONNECT),
				command -> this.logoutAndGoToRootView() );
		
	}
	
	/**
	 * Default menu items in menubar when user is not connected.
	 * Display an item to log in and an other item to register
	 * 
	 */
	private void buildDefaultMenu(){
		this.signupItem = this.addItem(bundle.getMessage(MENU_NAME_UNCONNECT_SIGNUP),
				command -> this.register());
		this.loginItem = this.addItem(bundle.getMessage(MENU_NAME_UNCONNECT_LOGIN),
				command -> this.login());
		
	}
	
	/**
	 * Logout user or if user already logout, display the user logged menu
	 */
	private void logoutAndGoToRootView(){
		UserManagementUtils.logout();
		if(!UserManagementUtils.isAuthenticated()){
			this.removeItems();
			this.buildDefaultMenu();
			// make sure vaadin session is close server side and client side
			VaadinSession.getCurrent().close();
			MyUI.getCurrent().push();
		}

	}
	
	
	/**
	 * login user or if user already log display the user logged menu
	 */
	private void login(){
		if(UserManagementUtils.isAuthenticated()){
			this.removeItems();
			this.buildLogUserMenu();
		}
		else{
			new LoginBox(loginItem);
		}
	}
	
	/**
	 * Register user or if user already log display the user logged menu
	 */
	private void register(){
		if(UserManagementUtils.isAuthenticated()){
			this.removeItems();
			this.buildLogUserMenu();
		}
		else{
			new RegisterUserBox(signupItem);
		}

	}
}
