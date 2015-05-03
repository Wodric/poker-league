package com.plm.component.datagrid;

import java.util.ArrayList;
import java.util.List;

import com.plm.tournamentCore.blind.BlindLevel;
import com.plm.tournamentCore.blind.BlindStructure;
import com.vaadin.ui.Grid;

public class BlindStructureGrid extends Grid {
	/**
	 * seria number for serialisation
	 */
	private static final long serialVersionUID = 2488945157328981727L;
	
	/**
	 * Name and id of the time column in BlindStructureGrid
	 */
	public static String COLUMN_TIME_NAME = "Time (min)";
	
	/**
	 * Name and id of the small blind column in BlindStructureGrid
	 */
	public static String COLUMN_SMALL_BLIND_NAME = "Small blind";
	
	/**
	 * Name and id of the time big blind in BlindStructureGrid
	 */
	public static String COLUMN_BIG_BLIND_NAME = "Big blind";
	
	/**
	 * Name and id of the ante column in BlindStructureGrid
	 */
	public static String COLUM_ANTE_NAME = "Ante";
	
	/**
	 * Default behaviour of column to sortable
	 */
	private static boolean COLUMN_SORTABLE = false;
	
	private List<Column> gridColumn = new ArrayList<Column>();
	
	private BlindStructure structure;

	public BlindStructureGrid(BlindStructure pStructure, boolean isEditable){
		// set column name and type
		this.addBlindStructureGridColumn(pStructure);
		// feed the grid
		feedBlindStructureGrid(pStructure);
		this.setEditorEnabled(isEditable);
	}
	
	public BlindStructureGrid(boolean isEditable) {
		this.structure = BlindStructure.getDefaultBlindStructure();
		// set column name and type
		this.addBlindStructureGridColumn(structure);
		// feed the grid
		feedBlindStructureGrid(structure);
		this.setEditorEnabled(isEditable);
	}

	/**
	 * Add row in grid from the BlindStructureObject
	 * @param pStructure BlindStructure object, use the blind level cof this object to feed the grid
	 */
	private void feedBlindStructureGrid(BlindStructure pStructure){
		for(BlindLevel aLevel : pStructure.getStructure()){
			if(pStructure.isAnte()){
				this.addRow(aLevel.getDuration(),
						aLevel.getSmallBlind(),
						aLevel.getBigBlind(),
						aLevel.getAnte());
			}
			else{
				this.addRow(aLevel.getDuration(),
						aLevel.getSmallBlind(),
						aLevel.getBigBlind());
			}
		}
	}
	
	/**
	 * Add and parameterize the columns
	 * @param pStructure structure of blind, needed to choose if the ante column is needed
	 */
	private void addBlindStructureGridColumn(BlindStructure pStructure){
		this.gridColumn.add(this.addColumn(COLUMN_TIME_NAME, Integer.class));
		this.gridColumn.add(this.addColumn(COLUMN_SMALL_BLIND_NAME, Integer.class));
		this.gridColumn.add(this.addColumn(COLUMN_BIG_BLIND_NAME, Integer.class));
		if(pStructure.isAnte()){
			this.gridColumn.add(this.addColumn(COLUM_ANTE_NAME, Integer.class));
			this.setColumnOrder(COLUMN_TIME_NAME,COLUMN_SMALL_BLIND_NAME,COLUMN_BIG_BLIND_NAME,COLUM_ANTE_NAME);
		}
		else{
			this.setColumnOrder(COLUMN_TIME_NAME,COLUMN_SMALL_BLIND_NAME,COLUMN_BIG_BLIND_NAME);
		}
		
		for(Column aColumn : this.gridColumn){
			aColumn = aColumn.setSortable(COLUMN_SORTABLE);
		}
	}
	
	/**
	 * Get the blind structure object display in grid
	 * @return the blind structure object display in grid of this panel
	 */
	public BlindStructure getStructure() {
		return this.structure;
	}

}
