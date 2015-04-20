package com.plm.tournament.structures.blinds;


import com.plm.framework.ui.mvp.BasePanel;
import com.plm.framework.ui.mvp.BaseView;
import com.plm.tournament.structures.blinds.beans.BlindStructureParameters;
import com.plm.tournamentCore.blind.BlindStructure;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.FormLayout;

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
				String.valueOf(BlindStructure.DEFAULT_NUMBER_PLAYER));
		maxPlayer.setConversionError("Value must be a number between 2 and 50 000");

		AbstractTextField levelDuration = (AbstractTextField) binder.getField
				(BlindStructureParameters.PARAMETER_NAME_LEVEL_DURATION);
		levelDuration.setNullRepresentation(
				String.valueOf(BlindStructure.DEFAULT_LEVEL_DURATION));
		levelDuration.setConversionError("Value must be a number between 10 and 300");
		
		AbstractTextField tournamentDuration = (AbstractTextField) binder.getField
				(BlindStructureParameters.PARAMETER_NAME_TOURNAMENT_DURATION_EXPECTED);
		tournamentDuration.setNullRepresentation(
				String.valueOf(BlindStructure.DEFAULT_TOURNAMENT_DURATION));
		tournamentDuration.setConversionError("Value must be a number between 30 and 60 000");
	}
	
	/**
	 * Initiate the bean object (BlindStructureParameters) for structure parameter
	 */
	private BlindStructureParameters createBeanWithDefaultValue(){
		BlindStructureParameters structureParameters = new BlindStructureParameters();
		structureParameters.setLevelDuration(
				BlindStructure.DEFAULT_LEVEL_DURATION);
		structureParameters.setTournamentDurationExpected(
				BlindStructure.DEFAULT_TOURNAMENT_DURATION);
		structureParameters.setMaxPlayerNumber(
				BlindStructure.DEFAULT_NUMBER_PLAYER);
		structureParameters.setWithAnte(
				BlindStructure.DEFAULT_WITH_ANTE);
		return structureParameters;
	}
}
