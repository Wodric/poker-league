package com.plm.tournament.structures.blinds;


import com.plm.framework.ui.mvp.BasePanel;
import com.plm.framework.ui.mvp.BaseView;
import com.plm.tournament.structures.blinds.beans.BlindStructureParameters;
import com.plm.tournamentCore.blind.BlindConstants;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

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
	 * Constaint which define the caption of panel
	 */
	private static final String MAIN_INFORMATION_PANEL_CAPTION = "Main information";
	
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
		
		binder.setItemDataSource(createBeanWithDefaultValue());

		FormLayout content = new FormLayout();	
		content.addComponent(this.binder.buildAndBind
				("Number of player", BlindStructureParameters.PARAMETER_NAME_MAX_PLAYER_NUMBER));
		content.addComponent(this.binder.buildAndBind
				("Time per level", BlindStructureParameters.PARAMETER_NAME_LEVEL_DURATION));
		content.addComponent(this.binder.buildAndBind
				("Duration (min)", BlindStructureParameters.PARAMETER_NAME_TOURNAMENT_DURATION_EXPECTED));
		content.addComponent(this.binder.buildAndBind
				("Allow ante",  BlindStructureParameters.PARAMETER_NAME_WITH_ANTE));
		content.setMargin(true);
		content.setSpacing(true);

		this.setConversionTexteFieldBehavior();
		this.setContent(content);
	}
	/**
	 * Set the conversion error message on fields
	 */
	private void setConversionTexteFieldBehavior(){
		
		AbstractTextField maxPlayer = (AbstractTextField) binder.getField
				(BlindStructureParameters.PARAMETER_NAME_MAX_PLAYER_NUMBER);
		maxPlayer.setNullRepresentation(
				String.valueOf(BlindConstants.DEFAULT_NUMBER_PLAYER));
		maxPlayer.setConversionError("Value must be a number between 2 and 50 000");

		AbstractTextField levelDuration = (AbstractTextField) binder.getField
				(BlindStructureParameters.PARAMETER_NAME_LEVEL_DURATION);
		levelDuration.setNullRepresentation(
				String.valueOf(BlindConstants.DEFAULT_LEVEL_DURATION));
		levelDuration.setConversionError("Value must be a number between 10 and 300");
		
		AbstractTextField tournamentDuration = (AbstractTextField) binder.getField
				(BlindStructureParameters.PARAMETER_NAME_TOURNAMENT_DURATION_EXPECTED);
		tournamentDuration.setNullRepresentation(
				String.valueOf(BlindConstants.DEFAULT_TOURNAMENT_DURATION));
		tournamentDuration.setConversionError("Value must be a number between 30 and 60 000");
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
		return (TextField) binder.getField
				(BlindStructureParameters.PARAMETER_NAME_MAX_PLAYER_NUMBER);
	}
	
	/**
	 * get the Max player field value. return it as Integer
	 * @return return the integer value in max player field
	 */
	public Integer getMaxPlayerFieldValue(){
		return Integer.valueOf(this.getMaxPlayerField().getValue());
	}
	
	/**
	 * get the Level duration field it is a Text field from binder
	 * @return the text field for level duration
	 */
	public TextField getLevelDurationField(){
		return (TextField) binder.getField
				(BlindStructureParameters.PARAMETER_NAME_LEVEL_DURATION);
	}
	
	/**
	 * get the level duration field value. return it as Integer
	 * @return return the integer value in level duration field
	 */
	public Integer getLevelDurationFieldValue(){
		return Integer.valueOf(this.getLevelDurationField().getValue());
	}
	
	/**
	 * get the tournament duration field it is a Text field from binder
	 * @return the text field for tournament duration
	 */
	public TextField getTournamentDurationField(){
		return (TextField) binder.getField
				(BlindStructureParameters.PARAMETER_NAME_TOURNAMENT_DURATION_EXPECTED);
	}
	
	/**
	 * get the tournament duration field value. return it as integer
	 * @return return the integer value in tournament duration field
	 */
	public TextField getTournamentDurationFieldValue(){
		return this.getTournamentDurationField();
	}
	
	/**
	 * get the Allow ante / with ante field it is a checkbox from binder
	 * @return the checkbox for Allow ante / with ante 
	 */
	public CheckBox getAllowAnteField(){
		return (CheckBox) binder.getField
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
