package com.plm.tournament.structures.blinds;

import com.plm.framework.ui.components.ImmediateTextfield;
import com.plm.framework.ui.mvp.BasePanel;
import com.plm.tournamentCore.blind.BlindStructureParameters;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

/**
 * Panel which contains the main element to define the structure
 * @author Lefèvre Alexandre "Wodric"
 */
public class BlindStructureMainInformationPanel extends BasePanel{
	
	/**
	 * Bean validator for structure parameters 
	 */
	private final BeanFieldGroup<BlindStructureParameters> binder = 
			new BeanFieldGroup<BlindStructureParameters>(BlindStructureParameters.class);
	
	/**
	 * serialisation UID
	 */
	private static final long serialVersionUID = 6828111650513726232L;
	
	/**
	 * Constaint which defin the caption of panel
	 */
	private static final String MAIN_INFORMATION_PANEL_CAPTION = "Main information";
	
	/**
	 * Default value of level time to set on UI
	 */
	public static final Integer UI_DEFAULT_LEVEL_TIME = 
			BlindStructureParameters.DEFAULT_LEVEL_DURATION;
	
	/**
	 * The object properties force the level time type for interface object
	 */
	private final ObjectProperty<Integer> levelTimeValue =
		    new ObjectProperty<Integer>(UI_DEFAULT_LEVEL_TIME);
	
	/**
	 * Default value of tournamenet duration to set on UI
	 */
	public static final Integer UI_DEFAULT_TOURNAMENT_DURATION =
			BlindStructureParameters.DEFAULT_TOURNAMENT_DURATION;
	
	/**
	 * The object properties force the tournament duration type for interface object
	 */
	private final ObjectProperty<Integer> tournamentDuration =
		    new ObjectProperty<Integer>(UI_DEFAULT_TOURNAMENT_DURATION);
	
	/**
	 * Default value of number of tournament player to set on UI
	 */
	public static final Integer UI_DEFAULT_PLAYER_NUMBER = 
			BlindStructureParameters.DEFAULT_NUMBER_PLAYER;
		
	/**
	 * The object properties force the number of tournament player type for interface object
	 */
	private final ObjectProperty<Integer> playerNumber =
		    new ObjectProperty<Integer>(UI_DEFAULT_PLAYER_NUMBER);
	
	/**
	 * Default value ante to set on UI
	 */
	public static final Boolean UI_DEFAULT_WITH_ANTE = 
			BlindStructureParameters.DEFAULT_WITH_DURATION;
	
	/**
	 * Panel objects 		
	 */
	
	/**
	 * permit to get user value for the tournament duration in blind structure creation
	 */
	private ImmediateTextfield playerNumberText   = new ImmediateTextfield("Number of player",playerNumber);
	
	/**
	 * permit to get user value for the tournament duration in blind structure creation
	 */
	private ImmediateTextfield levelTimeText   = new ImmediateTextfield("Time per level", levelTimeValue);
	
	/**
	 * permit to get user value for the tournament duration in blind structure creation
	 */
	private ImmediateTextfield tournamentDurationText   = new ImmediateTextfield("Duration (min)",tournamentDuration);	
	
	/**
	 * The check box to define if ante must be integrate in structure
	 */
	CheckBox anteCheckBox = new CheckBox("Ante");
	


	/**
	 * Return the panel already prepare
	 */
	public BlindStructureMainInformationPanel() {
		super(MAIN_INFORMATION_PANEL_CAPTION);
		binder.setItemDataSource(createBeanWithDefaultValue());
		
		FormLayout content = new FormLayout();
		content.addComponent(playerNumberText);
		content.addComponent(levelTimeText);
		content.addComponent(tournamentDurationText);
 		content.addComponent(anteCheckBox);		
		setConversionErrorMessage();
		
		this.setWidth((float) 40.0, Unit.PERCENTAGE);
		this.setContent(content);
	}

	/**
	 * Set the conversion error message on fields
	 */
	private void setConversionErrorMessage(){
		
		
		this.playerNumberText.setConversionError("Value must be a number between 2 and 50000");
		this.levelTimeText.setConversionError("Value must be a number between 10 and 300");
		this.tournamentDurationText.setConversionError("Value must be a number between 30 and 9000");
	}
	
	/**
	 * Initiate the bean object (BlindStructureParameters) for structure parameter
	 */
	private BlindStructureParameters createBeanWithDefaultValue(){
		BlindStructureParameters structureParameters = new BlindStructureParameters();
		structureParameters.setLevelDurations(UI_DEFAULT_LEVEL_TIME);
		structureParameters.setTournamentDurationExpected(UI_DEFAULT_TOURNAMENT_DURATION);
		structureParameters.setMaxPlayerNumber(UI_DEFAULT_PLAYER_NUMBER);
		structureParameters.setWithAnte(UI_DEFAULT_WITH_ANTE);
		return structureParameters;
	}
	/**
	 * getter on Player Number text filed
	 * @return the text field
	 */
	public TextField getPlayerNumberText() {
		return this.playerNumberText;
	}

	/**
	 * getter on level time text filed
	 * @return the text field
	 */
	public TextField getLevelTimeText() {
		return this.levelTimeText;
	}

	/**
	 * getter on tournament duration text filed
	 * @return the text field
	 */
	public TextField getTournamentDurationText() {
		return this.tournamentDurationText;
	}
	
	/**
	 * getter on ante checkbox
	 * @return the checkbox
	 */
	public CheckBox getAnteCheckBox() {
		return this.anteCheckBox;
	}

}
