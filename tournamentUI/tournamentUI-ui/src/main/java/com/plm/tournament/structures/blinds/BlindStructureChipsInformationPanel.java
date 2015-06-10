package com.plm.tournament.structures.blinds;

import java.util.ArrayList;
import java.util.List;

import com.plm.MyUI;
import com.plm.framework.ui.mvp.BasePanel;
import com.plm.framework.ui.mvp.BaseView;
import com.plm.internationalization.ParametrizedResourceBundle;
import com.plm.messages.constants.MessagesConstants;
import com.plm.tournament.structures.blinds.beans.BlindStructureParameters;
import com.plm.tournamentCore.blind.BlindConstants;
import com.plm.tournamentCore.chip.ChipsSet;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

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
	 * bundle for message
	 */
	private static final ParametrizedResourceBundle bundle = ParametrizedResourceBundle.
			getParametrizedBundle(MessagesConstants.UI_MESSAGE_FILE_BASE_NAME, MyUI.getUserLocale());
	
	/**
	 * Constaint which defin the caption of panel
	 */
	private static final String CHIPS_INFORMATION_PANEL_CAPTION = bundle.
			getMessage(MessagesConstants.BLINDSTRUCTURE_PANEL_CHIPS_TITLE);
	
	/**
	 * the combo box to select the chip set
	 */
	private ComboBox chipsSetsComboBox;
	
	/**
	 * Panel width
	 */
	public static final float PANEL_WIDTH = (float) 28.0;
	
	/**
	 * Component parent, used to get listener for exemple
	 */
	BaseView parentComponent;

	/**
	 * constructor for panel
	 * @param pParent 
	 */
	public BlindStructureChipsInformationPanel(BaseView pParent) {
		super(CHIPS_INFORMATION_PANEL_CAPTION);
		this.parentComponent = pParent;
		
		binder.setItemDataSource(this.createBeanWithDefaultValue());

		FormLayout content = new FormLayout();
		content.addComponent(this.binder.buildAndBind
				(bundle.getMessage(MessagesConstants.BLINDSTRUCTURE_PANEL_CHIPS_SMALLBLIND),
						BlindStructureParameters.PARAMETER_NAME_MINIMUM_SMALL_BLIND_VALUE));
		content.addComponent(this.binder.buildAndBind
				(bundle.getMessage(MessagesConstants.BLINDSTRUCTURE_PANEL_CHIPS_STACKSIZE),
						BlindStructureParameters.PARAMETER_NAME_INITIAL_STACK_SIZE));
		
		this.initChipSetSelector();
		this.setDefaultFieldBehavior();
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
				BlindConstants.DEFAULT_SMALL_BLIND_VALUE);
		structureParameters.setInitialStackSize(
				BlindConstants.DEFAULT_INITIAL_STACK_SIZE);
		return structureParameters;
	}
	
	/**
	 * Set the conversion error message on fields
	 */
	private void setDefaultFieldBehavior(){

		AbstractTextField smallBlind = (AbstractTextField) binder.getField
				(BlindStructureParameters.PARAMETER_NAME_MINIMUM_SMALL_BLIND_VALUE);
		smallBlind.setNullRepresentation(
				String.valueOf(BlindConstants.DEFAULT_SMALL_BLIND_VALUE));
		smallBlind.setConversionError(
				bundle.getMessage(MessagesConstants.BLINDSTRUCTURE_PANEL_CHIPS_SMALLBLIND_TOOLTIP,
						BlindConstants.MIN_SMALL_BLIND_VALUE,BlindConstants.MAX_SMALL_BLIND_VALUE));
		smallBlind.addValueChangeListener(this.parentComponent);

		AbstractTextField initialStack = (AbstractTextField) binder.getField
				(BlindStructureParameters.PARAMETER_NAME_INITIAL_STACK_SIZE);
		initialStack.setNullRepresentation(
				String.valueOf(BlindConstants.DEFAULT_INITIAL_STACK_SIZE));
		initialStack.setConversionError(
				bundle.getMessage(MessagesConstants.BLINDSTRUCTURE_PANEL_CHIPS_SMALLBLIND_TOOLTIP,
				BlindConstants.MIN_INITIAL_STACK_SIZE,BlindConstants.MAX_INITIAL_STACK_SIZE));
		initialStack.addValueChangeListener(this.parentComponent);

		this.chipsSetsComboBox.addValueChangeListener(this.parentComponent);
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

		this.chipsSetsComboBox = new ComboBox(
				bundle.getMessage(MessagesConstants.BLINDSTRUCTURE_PANEL_CHIPS_SMALLBLIND_SET));
		
		for(String chipSet : defaultChipsSetToString){
			this.chipsSetsComboBox.addItem(chipSet);
		}
		// set default value to the first item of default
		this.chipsSetsComboBox.setNullSelectionAllowed(false);
		this.chipsSetsComboBox.select(
						defaultChipsSetToString.get(ChipsSet.DEFAULT_CHIPS_SET_INDEX));	
	}
	
	/**
	 * get the small blind field it is a Text field from binder
	 * @return the text field for small blind
	 */
	public TextField getMinimumSmallBlindField(){
		return (TextField) binder.getField
				(BlindStructureParameters.PARAMETER_NAME_MINIMUM_SMALL_BLIND_VALUE);
	}
	
	/**
	 * get the small blind field value
	 * @return value of small blind on field
	 */
	public int getMinimumSmallBlindFieldValue(){
		String valueWithoutSpace =  this.getMinimumSmallBlindField().getValue().replaceAll("\\p{javaSpaceChar}","");
		return Integer.valueOf(valueWithoutSpace).intValue();
	}
	
	/**
	 * get the Initial stack field it is a Text field from binder
	 * @return the text field for initial stack 
	 */
	public TextField getInitialStackField(){
		return (TextField) binder.getField
				(BlindStructureParameters.PARAMETER_NAME_INITIAL_STACK_SIZE);
	}
	
	/**
	 * get the Initial stack field value. Return it as Integer
	 * @return the value of the Initial stack field as interger 
	 */
	public int getInitialStackFieldValue(){
		String valueWithoutSpace =  this.getInitialStackField().getValue().replaceAll("\\p{javaSpaceChar}","");
		return  Integer.valueOf(valueWithoutSpace).intValue();
	}
	
	
	/**
	 * get ChipSet combo box it is a Text field
	 * @return the combobox for ChipSet
	 */
	public ComboBox getChipSetComboBox(){
		return this.chipsSetsComboBox;
	}
	
	/**
	 * get ChipSet combo box value return as string
	 * @return the value of the combo box as string
	 */
	public String getChipSetComboBoxValue(){
		return String.valueOf(this.getChipSetComboBox().getValue());
	}
}
