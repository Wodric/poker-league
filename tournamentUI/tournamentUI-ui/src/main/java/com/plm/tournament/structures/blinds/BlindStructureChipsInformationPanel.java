package com.plm.tournament.structures.blinds;

import java.util.ArrayList;
import java.util.List;

import com.plm.framework.ui.mvp.BasePanel;
import com.plm.tournamentCore.blind.BlindStructureParameters;
import com.plm.tournamentCore.chip.ChipsSet;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;

public class BlindStructureChipsInformationPanel extends BasePanel{
	
	/**
	 * Bean validator for structure parameters and variable
	 */
	private final BeanFieldGroup<BlindStructureParameters> binder = 
			new BeanFieldGroup<BlindStructureParameters>(BlindStructureParameters.class);
	
	/**
	 * serialisation UID
	 */
	private static final long serialVersionUID = 6828111650513726471L;
	
	/**
	 * Constaint which defin the caption of panel
	 */
	private static final String CHIPS_INFORMATION_PANEL_CAPTION = "Chips information";
	
	/**
	 * Default value of level time to set on UI
	 */
	public static final Integer UI_DEFAULT_MINIMUM_SMALL_BLIND = 
			BlindStructureParameters.DEFAULT_SMALL_BLIND_VALUE;

	
	/**
	 * Default value of tournamenet duration to set on UI
	 */
	public static final Integer UI_DEFAULT_TOURNAMENT_DURATION =
			BlindStructureParameters.DEFAULT_TOURNAMENT_DURATION;

	
	/**
	 * Default value of number of tournament player to set on UI
	 */
	public static final Integer UI_DEFAULT_PLAYER_NUMBER = 
			BlindStructureParameters.DEFAULT_NUMBER_PLAYER;

	/**
	 * Default value ante to set on UI
	 */
	public static final Boolean UI_DEFAULT_WITH_ANTE = 
			BlindStructureParameters.DEFAULT_WITH_ANTE;
	
	/**
	 * Default initial stack value to set on UI
	 */
	public static final int UI_DEFAULT_STARTING_STACK= 
			BlindStructureParameters.DEFAULT_INITIAL_STACK_SIZE;
	
	
	/**
	 * Default chip set to set on UI
	 */
	public static final ChipsSet UI_DEFAULT_CHIP_SET = 
			BlindStructureParameters.DEFAULT_CHIPSET;
	
	private ComboBox chipsSetsComboBox;
	
	

	public BlindStructureChipsInformationPanel() {
		super(CHIPS_INFORMATION_PANEL_CAPTION);
		binder.setItemDataSource(createBeanWithDefaultValue());
		

		FormLayout content = new FormLayout();
		content.addComponent(this.binder.buildAndBind
				("Initial small blind", BlindStructureParameters.PARAMETER_NAME_MINIMUM_SMALL_BLIND_VALUE));
		content.addComponent(this.binder.buildAndBind
				("Initial stack size", BlindStructureParameters.PARAMETER_NAME_INITIAL_STACK_SIZE));
		this.initChipSetSelector();
		content.addComponent(this.chipsSetsComboBox);

		//this.setConversionTexteFieldBehavior();
		this.setWidth((float) 40.0, Unit.PERCENTAGE);
		this.setContent(content);
	}
	
	/**
	 * Initiate the bean object (BlindStructureParameters) for structure parameter
	 */
	private BlindStructureParameters createBeanWithDefaultValue(){
		BlindStructureParameters structureParameters = new BlindStructureParameters();
		structureParameters.setMinimumSmallBlindValue(UI_DEFAULT_MINIMUM_SMALL_BLIND);
		structureParameters.setInitialStackSize(UI_DEFAULT_STARTING_STACK);
		return structureParameters;
	}
	
	/**
	 * initialize the chipset selector with default value in database
	 */
	private void initChipSetSelector(){
		List<ChipsSet> defaultChipsSets = ChipsSet.getDefaultChipsSets();
		List<String> defaultChipsSetToString = new ArrayList<String>();
		for(ChipsSet aSet : defaultChipsSets){
			defaultChipsSetToString.add(aSet.toString());
		}
		this.chipsSetsComboBox = new ComboBox("Chips set", defaultChipsSetToString);
		// set default value to the first item of default
		this.chipsSetsComboBox.setNullSelectionAllowed(false);
		this.chipsSetsComboBox.setNullSelectionItemId(defaultChipsSetToString.get(0));
		//binding
		binder.bind(this.chipsSetsComboBox, BlindStructureParameters.PARAMETER_NAME_CHIP_SET);
		
	}
	

}
