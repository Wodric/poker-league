package com.plm.tournament.structures.blinds;


import com.plm.framework.ui.mvp.BasePanel;
import com.plm.framework.ui.mvp.BaseView;
import com.plm.internationalization.ParametrizedResourceBundle;
import com.plm.messages.constants.MessagesConstants;
import com.plm.tournament.structures.blinds.beans.BlindStructureParameters;
import com.plm.tournamentCore.blind.BlindConstants;
import com.plm.vaadin.FieldConvertorUtils;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

/**
 * Panel which contains the main element to define the structure
 * @author Lefèvre Alexandre "Wodric"
 */
public class BlindStructureMainInformationPanel extends BasePanel{
	
	/**
	 * Bean validator for structure parameters and variable
	 */
	private final BeanFieldGroup<BlindStructureParameters> binder = 
			new BeanFieldGroup<BlindStructureParameters>(BlindStructureParameters.class);
	
	/**
	 * serialisation UID
	 */
	private static final long serialVersionUID = 6828111650513726232L;
	
	/**
	 * bundle for message
	 */
	private static final ParametrizedResourceBundle bundle = ParametrizedResourceBundle.
			getParametrizedBundle(MessagesConstants.UI_MESSAGE_FILE_BASE_NAME, UI.getCurrent().getLocale());
	
	/**
	 * Constaint which define the caption of panel
	 */
	private static final String MAIN_INFORMATION_PANEL_CAPTION = bundle.
			getMessage(MessagesConstants.BLINDSTRUCTURE_PANEL_MAIN_TITLE);
	
	/**
	 * Panel width
	 */
	public static float PANEL_WIDTH = (float) 28.0;
	
	/**
	 * Component parent, used to get listener for exemple
	 */
	BaseView parentComponent;

	/**
	 * Return the panel already prepare
	 * @param pParent 
	 */
	public BlindStructureMainInformationPanel(BaseView pParentView) {
		super(MAIN_INFORMATION_PANEL_CAPTION);
		this.parentComponent = pParentView;
		
		this.binder.setItemDataSource(createBeanWithDefaultValue());
		
		FormLayout content = new FormLayout();	
		content.addComponent(this.binder.buildAndBind
				(bundle.getMessage(MessagesConstants.INFORMATION_PANEL_INFORMATIONS_PLAYERS),
						BlindStructureParameters.PARAMETER_NAME_MAX_PLAYER_NUMBER));
		content.addComponent(this.binder.buildAndBind
				(bundle.getMessage(MessagesConstants.INFORMATION_PANEL_INFORMATIONS_DURATION_LEVELS),
						BlindStructureParameters.PARAMETER_NAME_LEVEL_DURATION));
		content.addComponent(this.binder.buildAndBind
				(bundle.getMessage(MessagesConstants.INFORMATION_PANEL_INFORMATIONS_DURATION_TOURNAMENT),
						BlindStructureParameters.PARAMETER_NAME_TOURNAMENT_DURATION_EXPECTED));
		content.addComponent(this.binder.buildAndBind
				(bundle.getMessage(MessagesConstants.INFORMATION_PANEL_INFORMATIONS_ANTE),
						BlindStructureParameters.PARAMETER_NAME_WITH_ANTE));
		content.setMargin(true);
		content.setSpacing(true);

		this.setDefaultFieldBehavior();
		this.setContent(content);
	}
	
	
	
	
	
	public BlindStructureMainInformationPanel() {
		super(MAIN_INFORMATION_PANEL_CAPTION);
		binder.setItemDataSource(createBeanWithDefaultValue());

		FormLayout content = new FormLayout();	
		content.addComponent(this.binder.buildAndBind
				(bundle.getMessage(MessagesConstants.INFORMATION_PANEL_INFORMATIONS_PLAYERS),
						BlindStructureParameters.PARAMETER_NAME_MAX_PLAYER_NUMBER));
		content.addComponent(this.binder.buildAndBind
				(bundle.getMessage(MessagesConstants.INFORMATION_PANEL_INFORMATIONS_DURATION_LEVELS),
						BlindStructureParameters.PARAMETER_NAME_LEVEL_DURATION));
		content.addComponent(this.binder.buildAndBind
				(bundle.getMessage(MessagesConstants.INFORMATION_PANEL_INFORMATIONS_DURATION_TOURNAMENT),
						BlindStructureParameters.PARAMETER_NAME_TOURNAMENT_DURATION_EXPECTED));
		content.addComponent(this.binder.buildAndBind
				(bundle.getMessage(MessagesConstants.INFORMATION_PANEL_INFORMATIONS_ANTE),
						BlindStructureParameters.PARAMETER_NAME_WITH_ANTE));
		content.setMargin(true);
		content.setSpacing(true);

		this.setDefaultFieldBehavior();
		this.setContent(content);
	}

	/**
	 * Set the conversion error message on fields
	 */
	private void setDefaultFieldBehavior(){

		AbstractTextField maxPlayer = (AbstractTextField) this.binder.getField
				(BlindStructureParameters.PARAMETER_NAME_MAX_PLAYER_NUMBER);
		maxPlayer.setNullRepresentation(
				String.valueOf(BlindConstants.DEFAULT_NUMBER_PLAYER));
		maxPlayer.setConversionError(
				bundle.getMessage(MessagesConstants.INFORMATION_PANEL_INFORMATIONS_PLAYERS_TOOLTIP,
				BlindConstants.MIN_NUMBER_PLAYER, BlindConstants.MAX_NUMBER_PLAYER));
		maxPlayer.addValueChangeListener(this.parentComponent);

		AbstractTextField levelDuration = (AbstractTextField) this.binder.getField
				(BlindStructureParameters.PARAMETER_NAME_LEVEL_DURATION);
		levelDuration.setNullRepresentation(
				String.valueOf(BlindConstants.DEFAULT_LEVEL_DURATION));
		levelDuration.setConversionError(
				bundle.getMessage(MessagesConstants.INFORMATION_PANEL_INFORMATIONS_DURATION_LEVELS_TOOLTIP,
				BlindConstants.MIN_LEVEL_DURATION, BlindConstants.MAX_LEVEL_DURATION));
		levelDuration.addValueChangeListener(this.parentComponent);
		
		AbstractTextField tournamentDuration = (AbstractTextField) this.binder.getField
				(BlindStructureParameters.PARAMETER_NAME_TOURNAMENT_DURATION_EXPECTED);
		tournamentDuration.setNullRepresentation(
				String.valueOf(BlindConstants.DEFAULT_TOURNAMENT_DURATION));
		tournamentDuration.setConversionError(
				bundle.getMessage(MessagesConstants.INFORMATION_PANEL_INFORMATIONS_DURATION_TOURNAMENT_TOOLTIP,
				BlindConstants.MIN_TOURNAMENT_DURATION, BlindConstants.MAX_TOURNAMENT_DURATION));
		tournamentDuration.addValueChangeListener(this.parentComponent);
		
		CheckBox withAnte = (CheckBox) this.binder.getField
				(BlindStructureParameters.PARAMETER_NAME_WITH_ANTE);
		withAnte.addValueChangeListener(this.parentComponent);
	}
	
	/**
	 * Initiate the bean object (BlindStructureParameters) for structure parameter
	 */
	private BlindStructureParameters createBeanWithDefaultValue(){
		BlindStructureParameters structureParameters = new BlindStructureParameters();
		structureParameters.setLevelDuration(
				BlindConstants.DEFAULT_LEVEL_DURATION);
		structureParameters.setTournamentDurationExpected(
				BlindConstants.DEFAULT_TOURNAMENT_DURATION);
		structureParameters.setMaxPlayerNumber(
				BlindConstants.DEFAULT_NUMBER_PLAYER);
		structureParameters.setWithAnte(
				BlindConstants.DEFAULT_WITH_ANTE);
		return structureParameters;
	}
	
	/**
	 * get the Max player field it is a Text field from binder
	 * @return the text field for max player
	 */
	public TextField getMaxPlayerField(){
		return (TextField) this.binder.getField
				(BlindStructureParameters.PARAMETER_NAME_MAX_PLAYER_NUMBER);
	}
	
	/**
	 * get the Max player field value. return it as Integer
	 * @return return the integer value in max player field
	 */
	public int getMaxPlayerFieldValue(){
		return FieldConvertorUtils.convertNumericFieldToInt(
					this.getMaxPlayerField().getValue());
	}
	
	/**
	 * get the Level duration field it is a Text field from binder
	 * @return the text field for level duration
	 */
	public TextField getLevelDurationField(){
		return (TextField) this.binder.getField
				(BlindStructureParameters.PARAMETER_NAME_LEVEL_DURATION);
	}
	
	/**
	 * get the level duration field value. return it as Integer
	 * @return return the integer value in level duration field
	 */
	public int getLevelDurationFieldValue(){
		return FieldConvertorUtils.convertNumericFieldToInt(
					this.getLevelDurationField().getValue());
	}
	
	/**
	 * get the tournament duration field it is a Text field from binder
	 * @return the text field for tournament duration
	 */
	public TextField getTournamentDurationField(){
		return (TextField) this.binder.getField
				(BlindStructureParameters.PARAMETER_NAME_TOURNAMENT_DURATION_EXPECTED);
	}
	
	/**
	 * get the tournament duration field value. return it as integer
	 * @return return the integer value in tournament duration field
	 */
	public int getTournamentDurationFieldValue(){
		return FieldConvertorUtils.convertNumericFieldToInt(
					this.getTournamentDurationField().getValue());
	}
	
	/**
	 * get the Allow ante / with ante field it is a checkbox from binder
	 * @return the checkbox for Allow ante / with ante 
	 */
	public CheckBox getAllowAnteField(){
		return (CheckBox) this.binder.getField
				(BlindStructureParameters.PARAMETER_NAME_WITH_ANTE);
	}
	
	/**
	 * get the Allow ante / with ante field value return it as boolean
	 * @return the checkbox for Allow ante / with ante value
	 */
	public Boolean getAllowAnteFieldValue(){
		return this.getAllowAnteField().getValue();
	}
}
