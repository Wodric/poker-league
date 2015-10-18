package com.plm.tournament.structures.blinds;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.plm.component.datagrid.BlindStructureGrid;
import com.plm.tournamentCore.blind.BlindLevel;
import com.plm.tournamentCore.blind.BlindStructure;
import com.plm.tournamentCore.chip.ChipsSet;
import com.plm.userManagement.UserManagementUtils;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
/**
 * The presenter in MVP is a middle-man that handles all user interaction logic, but in an implementation-independent way, 
 * so that it doesn't actually know anything about Vaadin. 
 * It shows data in the view and receives user interaction back from it.
 * @author Alexandre Lefèvre "Wodric"
 */
public class BlindStructurePresenter implements 
	BlindStructureView.BlindStructureViewListener{
	BlindStructure structure;
	BlindStructureViewImpl structureView;

	private static Logger logger = LoggerFactory.getLogger(BlindStructurePresenter.class);

	/**
	 * Constructor to link all part of UI and MVP partern
	 * @param pStructure
	 * @param pStructureView
	 */
    public BlindStructurePresenter(BlindStructure pStructure,
    		BlindStructureViewImpl  pStructureView) {
        this.structure = pStructure;
        this.structureView  = pStructureView;
        
        structureView.addListener(this);
        
    }


	@Override
	/**
	 * manage the click event and set action to user click on button
	 * @parameter pEvent the event to manage
	 */
	public void buttonClick(ClickEvent pEvent) {
		if(pEvent.getSource().equals(this.structureView.
				getStructurePreviewPanel().getSaveStructureBtn())){
			// will save the structure
			Subject user = SecurityUtils.getSubject();
			if(!user.isAuthenticated()){
				logger.warn("user not authenticate");
				Notification.show("Pas authentifié (testing string to remove!!!)");
				UserManagementUtils.login("admin","admin");
			    Session session = user.getSession();   
			    session.setAttribute("login", "admin"); 
			}
			else{
				logger.warn("user log : " + user.getSession().getAttribute("login"));
				this.structure.saveBlindStructure();
			}
		}
	}

	@Override
	/**
	 * manage the ValueChangeEvent and set action to user click on button
	 * @parameter pEvent the event to manage
	 */
	public void valueChange(ValueChangeEvent pEvent) {
		BlindStructureMainInformationPanel mainInformationPanel = this.structureView
				.getMainInformationPanel();
		BlindStructureChipsInformationPanel chipsInformationPanel = this.structureView
				.getChipsInformationPanel();
		BlindStructurePreviewPanel structurePreviewPanel = this.structureView.getStructurePreviewPanel();
		BlindStructureGrid structureGrid = structurePreviewPanel.getStructureGrid();
		
		// in case we allow or disallow ante
		if(pEvent.getProperty().equals(mainInformationPanel.getAllowAnteField())){
			// in case we allow ante we need to create the ante column and feed it
			if(mainInformationPanel.getAllowAnteFieldValue()){
				structureGrid.enableAnte();
			}
			 //else remove the column
			else{
				structureGrid.removeAnte();
			}
		}
		else if(pEvent.getProperty().equals(mainInformationPanel.getLevelDurationField()) ||
				pEvent.getProperty().equals(mainInformationPanel.getMaxPlayerField()) ||
				pEvent.getProperty().equals(mainInformationPanel.getTournamentDurationField()) ||
				pEvent.getProperty().equals(chipsInformationPanel.getInitialStackField())||
				pEvent.getProperty().equals(chipsInformationPanel.getMinimumSmallBlindField())||
				pEvent.getProperty().equals(chipsInformationPanel.getChipSetComboBox())){
			
			BlindLevel firstLevel = new BlindLevel(mainInformationPanel.getLevelDurationFieldValue(), 
					chipsInformationPanel.getMinimumSmallBlindFieldValue(), 
					chipsInformationPanel.getMinimumSmallBlindFieldValue()*2);
			
			structureGrid.updateGrid(
					mainInformationPanel.getMaxPlayerFieldValue(),
					chipsInformationPanel.getInitialStackFieldValue(),
					mainInformationPanel.getTournamentDurationFieldValue(),
					mainInformationPanel.getLevelDurationFieldValue(),
					firstLevel,
					mainInformationPanel.getAllowAnteFieldValue(),
					new ChipsSet(chipsInformationPanel.getChipSetComboBoxValue()));
			
		}		
	}
}
