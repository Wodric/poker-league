package com.plm.tournament.login;

import com.plm.internationalization.ParametrizedResourceBundle;
import com.plm.messages.constants.MessagesConstants;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

/**
 * Notification in case of login error
 * @author Alexandre Lefèvre "Wodric"
 *
 */
public class ErrorLoginNotification extends Notification {

	/**
	 * Generated serial UID for serialization
	 */
	private static final long serialVersionUID = -3541659573600129809L;
	
	/**
	 * Notification duration
	 */
	public static final int NOTIFICATION_TIMEOUT_MS = 10000;
	
	/**
	 * Notification style
	 */
	public static final String NOTIFICATION_STYLE = "warn closable login-help";
	
	private static final ParametrizedResourceBundle bundle = ParametrizedResourceBundle.
			getParametrizedBundle(MessagesConstants.UI_MESSAGE_FILE_BASE_NAME, UI.getCurrent().getLocale());

	public ErrorLoginNotification() {
		super(
				bundle.getMessage(MessagesConstants.LOGIN_ERROR_TITLE));
        super.setDescription(
        		bundle.getMessage(MessagesConstants.LOGIN_ERROR_DESCRIPTION));
        super.setHtmlContentAllowed(true);
        super.setStyleName(NOTIFICATION_STYLE);
        super.setPosition(Position.TOP_CENTER);
        super.setDelayMsec(NOTIFICATION_TIMEOUT_MS);
        super.show(Page.getCurrent());
	}

}
