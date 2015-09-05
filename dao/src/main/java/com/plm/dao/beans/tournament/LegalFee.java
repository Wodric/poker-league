package com.plm.dao.beans.tournament;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * LegalFee mapping class see mappings/LegalFee.hbm.xml files in ressources for mapping
 * @Author Alexandre "Wodric" Lefèvre
 */

public class LegalFee implements Serializable {

	/**
	 * generated serial uid
	 */
	private static final long serialVersionUID = -2259355205530727294L;

	/**
	 *  id fee, auto generated
	 */
	private long idFee;
	
	/**
	 * fee short name
	 */
	private String shortName;
	
	/**
	 * Description of fee
	 */
	private String description;
	
	/**
	 *  This variable allow to set a taxe as pourcent
	 */
	private Integer feePercent;
	
	/**
	 *  This variable allow to set a taxe as fixes fee
	 */
	private Integer feeFixed;
	
	/**
	 * The tournament which use the fee
	 */
	private Set<Tournament> tournaments = new HashSet<Tournament>(0);

	public LegalFee() {
	}

	public LegalFee(int idFee, String shortName) {
		this.idFee = idFee;
		this.shortName = shortName;
	}

	public LegalFee(int idFee, String shortName, String description,
			Integer feePercent, Integer feeFixed, Set<Tournament> tournaments) {
		this.idFee = idFee;
		this.shortName = shortName;
		this.description = description;
		this.feePercent = feePercent;
		this.feeFixed = feeFixed;
		this.tournaments = tournaments;
	}

	public long getIdFee() {
		return this.idFee;
	}

	public void setIdFee(long idFee) {
		this.idFee = idFee;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getFeePercent() {
		return this.feePercent;
	}

	public void setFeePercent(Integer feePercent) {
		this.feePercent = feePercent;
	}

	public Integer getFeeFixed() {
		return this.feeFixed;
	}

	public void setFeeFixed(Integer feeFixed) {
		this.feeFixed = feeFixed;
	}

	public Set<Tournament> getTournaments() {
		return this.tournaments;
	}

	public void setTournaments(Set<Tournament> tournaments) {
		this.tournaments = tournaments;
	}

}
