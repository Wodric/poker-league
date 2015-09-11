package com.plm.dao.beans.tournament;

import java.io.Serializable;

/**
 * Tournament mapping class see maapings/Tournament.hbm.xml files in ressources for mapping
 * @Author Alexandre "Wodric" Lefèvre
 */
public class Tournament implements Serializable {

	/**
	 * generated 
	 */
	private static final long serialVersionUID = -4938149907017554992L;
	
	/**
	 * Tournament id auto generated
	 */
	private long idTournament;
	
	/**
	 * Blind structure for this tournament
	 */
	private BlindStructure blindStructure;
	
	/**
	 * LegalFee to apply to this tournament
	 */
	private LegalFee legalFee;
	
	/**
	 * Tournament name to display
	 */
	private String name;
	
	/**
	 * Tournament description to display which explain the tournament
	 */
	private String description;
	
	/**
	 * Poker variante for this tournament
	 */
	private String variante;
	
	/**
	 * max number of player for tournament
	 */
	private Integer maxPlayer;
	
	/**
	 * The tournament allow rebuy
	 */
	private Byte isRebuy;
	
	/**
	 * The tournament allow double rebuy
	 */
	private Byte isDoubleRebuyAuthorized;
	
	/**
	 * Tournament format is shootout
	 */
	private Byte isShootout;
	
	/**
	 * Tournament is qualifier for another tournament
	 */
	private Byte isQualifier;
	
	/**
	 * Tournament is reentry one
	 */
	private Byte isReentry;
	
	/**
	 * Tournament Buy In price involve in price pool, fee are apply to ths part
	 */
	private Integer buyIn;
	
	/**
	 * Tournament rake for tournament manager team
	 */
	private Integer rake;
	
	/**
	 * Number of chips at tournmanet start
	 */
	private Integer startingChips;
	
	/**
	 * number of chip in add on
	 */
	private Integer addOnChips;
	
	/**
	 * number of chip in rebuy 
	 */
	private Integer rebuyChips;
	
	/**
	 * default constructor
	 */
	public Tournament() {
	}

	/**
	 * constructor with mentdatory element
	 */
	public Tournament(BlindStructure blindStructure) {
		this.blindStructure = blindStructure;
	}

	public Tournament(BlindStructure blindStructure, LegalFee legalFee, String name, String description,
			String variante, Integer maxPlayer, Byte isRebuy, Byte isDoubleRebuyAuthorized, Byte isShootout,
			Byte isQualifier, Byte isReentry, Integer buyIn, Integer rake, Integer startingChips, Integer addOnChips,
			Integer rebuyChips) {
		this.blindStructure = blindStructure;
		this.legalFee = legalFee;
		this.name = name;
		this.description = description;
		this.variante = variante;
		this.maxPlayer = maxPlayer;
		this.isRebuy = isRebuy;
		this.isDoubleRebuyAuthorized = isDoubleRebuyAuthorized;
		this.isShootout = isShootout;
		this.isQualifier = isQualifier;
		this.isReentry = isReentry;
		this.buyIn = buyIn;
		this.rake = rake;
		this.startingChips = startingChips;
		this.addOnChips = addOnChips;
		this.rebuyChips = rebuyChips;
	}

	public Long getIdTournament() {
		return this.idTournament;
	}

	public void setIdTournament(Long idTournament) {
		this.idTournament = idTournament;
	}

	public BlindStructure getBlindStructure() {
		return this.blindStructure;
	}

	public void setBlindStructure(BlindStructure blindStructure) {
		this.blindStructure = blindStructure;
	}

	public LegalFee getLegalFee() {
		return this.legalFee;
	}

	public void setLegalFee(LegalFee legalFee) {
		this.legalFee = legalFee;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVariante() {
		return this.variante;
	}

	public void setVariante(String variante) {
		this.variante = variante;
	}

	public Integer getMaxPlayer() {
		return this.maxPlayer;
	}

	public void setMaxPlayer(Integer maxPlayer) {
		this.maxPlayer = maxPlayer;
	}

	public Byte getIsRebuy() {
		return this.isRebuy;
	}

	public void setIsRebuy(Byte isRebuy) {
		this.isRebuy = isRebuy;
	}

	public Byte getIsDoubleRebuyAuthorized() {
		return this.isDoubleRebuyAuthorized;
	}

	public void setIsDoubleRebuyAuthorized(Byte isDoubleRebuyAuthorized) {
		this.isDoubleRebuyAuthorized = isDoubleRebuyAuthorized;
	}

	public Byte getIsShootout() {
		return this.isShootout;
	}

	public void setIsShootout(Byte isShootout) {
		this.isShootout = isShootout;
	}

	public Byte getIsQualifier() {
		return this.isQualifier;
	}

	public void setIsQualifier(Byte isQualifier) {
		this.isQualifier = isQualifier;
	}

	public Byte getIsReentry() {
		return this.isReentry;
	}

	public void setIsReentry(Byte isReentry) {
		this.isReentry = isReentry;
	}

	public Integer getBuyIn() {
		return this.buyIn;
	}

	public void setBuyIn(Integer buyIn) {
		this.buyIn = buyIn;
	}

	public Integer getRake() {
		return this.rake;
	}

	public void setRake(Integer rake) {
		this.rake = rake;
	}

	public Integer getStartingChips() {
		return this.startingChips;
	}

	public void setStartingChips(Integer startingChips) {
		this.startingChips = startingChips;
	}

	public Integer getAddOnChips() {
		return this.addOnChips;
	}

	public void setAddOnChips(Integer addOnChips) {
		this.addOnChips = addOnChips;
	}

	public Integer getRebuyChips() {
		return this.rebuyChips;
	}

	public void setRebuyChips(Integer rebuyChips) {
		this.rebuyChips = rebuyChips;
	}

}
