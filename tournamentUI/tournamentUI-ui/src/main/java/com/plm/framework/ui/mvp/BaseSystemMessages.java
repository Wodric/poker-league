package com.plm.framework.ui.mvp;

import java.util.Locale;

import com.plm.internationalization.ParametrizedResourceBundle;
import com.plm.messages.constants.MessagesConstants;
import com.vaadin.server.CustomizedSystemMessages;
import com.vaadin.server.SystemMessages;
import com.vaadin.server.SystemMessagesInfo;
import com.vaadin.server.SystemMessagesProvider;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;

/**
 * Class to edit system message. Need to initiate yourm essage with this class 
 * in vaadin servlet init
 * @author Alexandre "Wodric" Lefèvre
 *
 */
public class BaseSystemMessages implements SystemMessagesProvider {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8134010855438674198L;
	
	private Locale localeMessage =  (UI.getCurrent() == null || UI.getCurrent().getLocale() == null)? 
			Locale.ENGLISH : UI.getCurrent().getLocale();
	/**
	 * message bundle
	 */
	private ParametrizedResourceBundle bundle = ParametrizedResourceBundle
			.getParametrizedBundle(MessagesConstants.UI_MESSAGE_FILE_BASE_NAME, localeMessage);
	
    /**
     * Gets the system messages to use in the given context. The
     * {@link SystemMessagesInfo} object contains available information but in
     * most cases some or both of {@link VaadinSession#getCurrent()} and
     * {@link UI#getCurrent()} can also be used to find more information to help
     * the decision.
     * 
     * @param systemMessagesInfo
     *            Locale, current request and other information available.
     * @return a system messages object
     */
    @Override
    public SystemMessages getSystemMessages(SystemMessagesInfo systemMessagesInfo) {

		CustomizedSystemMessages messages = new CustomizedSystemMessages();
		
		messages.setSessionExpiredMessage(this.bundle.getMessage(
				MessagesConstants.SYSTEM_MESSAGES_SESSION_EXPIRED_MESSAGE));
		messages.setSessionExpiredCaption(this.bundle.getMessage(
				MessagesConstants.SYSTEM_MESSAGES_SESSION_EXPIRED_CAPTION));
		messages.setSessionExpiredNotificationEnabled(true);
		
		messages.setCommunicationErrorCaption(this.bundle.getMessage(
				MessagesConstants.SYSTEM_MESSAGES_COMMUNICATION_ERROR_CAPTION));
		messages.setCommunicationErrorMessage(this.bundle.getMessage(
				MessagesConstants.SYSTEM_MESSAGES_COMMUNICATION_ERROR_MESSAGE));
		messages.setCommunicationErrorNotificationEnabled(true);
		
        return messages;
    }
}
