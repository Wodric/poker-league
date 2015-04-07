package com.plm.tournament.structures.blinds;

import java.util.ArrayList;
import java.util.List;

import com.plm.framework.ui.mvp.BasePanel;
import com.plm.tournament.structures.blinds.beans.BlindStructureParameters;
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
	 * the combo box to select the chip set
	 */
	private ComboBox chipsSetsComboBox;
	
	/**
	 * Panel width
	 */
	public static float PANEL_WIDTH = (float) 28.0;
	
	

	/**
	 * 
	 */
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
		content.setMargin(true);
		content.setSpacing(true);

		this.setContent(content);
	}
	
	/**
	 * Initiate the bean object (BlindStructureParameters) for structure parameter
	 */
	private BlindStructureParameters createBeanWithDefaultValue(){
		BlindStructureParameters structureParameters = new BlindStructureParameters();
		structureParameters.setMinimumSmallBlindValue(
				BlindStructureParameters.DEFAULT_SMALL_BLIND_VALUE);
		structureParameters.setInitialStackSize(
				BlindStructureParameters.DEFAULT_INITIAL_STACK_SIZE);
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
		this.chipsSetsComboBox.setNullSelectionItemId(
				defaultChipsSetToString.get(ChipsSet.DEFAULT_CHIPS_SET_INDEX));
		//binding
		binder.bind(this.chipsSetsComboBox, BlindStructureParameters.PARAMETER_NAME_CHIP_SET);
		
	}
	

}
