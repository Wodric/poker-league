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
	private long feeId;
	
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
	private Integer feePourcent;
	
	/**
	 *  This variable allow to set a taxe as fixes fee
	 */
	private Integer feeFixed;
	
	/**
	 * The tournament which use the fee
	 */
	private Set<Tournament> tournaments = new HashSet<Tournament>(0);

	/**
	 * Default constrctor
	 */
	public LegalFee() {
	}

	/**
	 *  constructor with mandatory fields
	 * @param feeId id fee, auto generated
	 * @param shortName fee short name
	 * @param description Description of fee
	 */
	public LegalFee(long feeId, String shortName, String description) {
		this.feeId = feeId;
		this.shortName = shortName;
		this.description = description;
	}

	/**
	 * constructor with all fields
	 * @param feeId id fee, auto generated
	 * @param shortName fee short name
	 * @param description Description of fee
	 * @param feePourcent  This variable allow to set a taxe as pourcent
	 * @param feeFixed This variable allow to set a taxe as fixes fee
	 * @param tournaments The tournament which use the fee
	 */
	public LegalFee(long feeId, String shortName, String description, Integer feePourcent, Integer feeFixed,
			Set<Tournament> tournaments) {
		this.feeId = feeId;
		this.shortName = shortName;
		this.description = description;
		this.feePourcent = feePourcent;
		this.feeFixed = feeFixed;
		this.tournaments = tournaments;
	}

	public long getFeeId() {
		return this.feeId;
	}

	public void setFeeId(long feeId) {
		this.feeId = feeId;
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

	public Integer getFeePourcent() {
		return this.feePourcent;
	}

	public void setFeePourcent(Integer feePourcent) {
		this.feePourcent = feePourcent;
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
