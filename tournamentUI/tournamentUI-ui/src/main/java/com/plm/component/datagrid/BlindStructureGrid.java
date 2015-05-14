package com.plm.component.datagrid;

import com.plm.tournamentCore.blind.BlindLevel;
import com.plm.tournamentCore.blind.BlindStructure;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Grid;

public class BlindStructureGrid extends Grid {
	/**
	 * seria number for serialisation
	 */
	private static final long serialVersionUID = 2488945157328981727L;
	
	/**
	 * Name and id of the time column in BlindStructureGrid
	 */
	public static String COLUMN_TIME_NAME = "duration";
	
	/**
	 * Name and id of the small blind column in BlindStructureGrid
	 */
	public static String COLUMN_SMALL_BLIND_NAME = "smallBlind";
	
	/**
	 * Name and id of the time big blind in BlindStructureGrid
	 */
	public static String COLUMN_BIG_BLIND_NAME = "bigBlind";
	
	/**
	 * Name and id of the ante column in BlindStructureGrid
	 */
	public static String COLUM_ANTE_NAME = "ante";
	
	/**
	 * blind Structure
	 */
	private BlindStructure structure;

	public BlindStructureGrid(BlindStructure pStructure, boolean isEditable){
		// set column name and type
		this.structure = pStructure;
		BeanItemContainer<BlindLevel> container =
			    new BeanItemContainer<BlindLevel>(BlindLevel.class, this.structure.getStructure());
		this.setContainerDataSource(container);
		
		if(this.structure.isAnte()){
			this.setColumnOrder(COLUMN_TIME_NAME,COLUMN_SMALL_BLIND_NAME,COLUMN_BIG_BLIND_NAME,COLUM_ANTE_NAME);
		}
		else{
			this.removeColumn(COLUM_ANTE_NAME);
			this.setColumnOrder(COLUMN_TIME_NAME,COLUMN_SMALL_BLIND_NAME,COLUMN_BIG_BLIND_NAME);
		}
		
		// feed the grid
		this.setEditorEnabled(isEditable);
	}
	
	public BlindStructureGrid(boolean isEditable) {
		this.structure = BlindStructure.getDefaultBlindStructure();
		BeanItemContainer<BlindLevel> container =
			    new BeanItemContainer<BlindLevel>(BlindLevel.class, this.structure.getStructure());
		this.setContainerDataSource(container);
		

		if(this.structure.isAnte()){
			this.setColumnOrder(COLUMN_TIME_NAME,COLUMN_SMALL_BLIND_NAME,COLUMN_BIG_BLIND_NAME,COLUM_ANTE_NAME);
		}
		else{
			this.removeColumn(COLUM_ANTE_NAME);
			this.setColumnOrder(COLUMN_TIME_NAME,COLUMN_SMALL_BLIND_NAME,COLUMN_BIG_BLIND_NAME);
		}
	
		this.setEditorEnabled(isEditable);
	}

	
	/**
	 * Get the blind structure object display in grid
	 * @return the blind structure object display in grid of this panel
	 */
	public BlindStructure getStructure() {
		return this.structure;
	}
	
	/**
	 * This method enable ante
	 */
	public void enableAnte(){
		this.addColumn(COLUM_ANTE_NAME);
		this.structure.recalculateAnteFromBlinds();
		this.setColumnOrder(COLUMN_TIME_NAME,COLUMN_SMALL_BLIND_NAME,COLUMN_BIG_BLIND_NAME,COLUM_ANTE_NAME);	
	}
	
	/**
	 * This method disable ante
	 */
	public void removeAnte(){
		BeanItemContainer<BlindLevel> container =
			    new BeanItemContainer<BlindLevel>(BlindLevel.class, this.structure.getStructure());
		this.setContainerDataSource(container);
		this.structure.removeAnte();

		this.removeColumn(COLUM_ANTE_NAME);
		this.setColumnOrder(COLUMN_TIME_NAME,COLUMN_SMALL_BLIND_NAME,COLUMN_BIG_BLIND_NAME);
	}

}
