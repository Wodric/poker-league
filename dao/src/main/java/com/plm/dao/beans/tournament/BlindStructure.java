package com.plm.dao.beans.tournament;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * BlindStructure mapping class see maapings/BlindStructure.hbm.xml files in ressources for mapping
 * @Author Alexandre "Wodric" Lefèvre
 */
public class BlindStructure implements Serializable {

	/**
	 * generated serial ID
	 */
	private static final long serialVersionUID = 8312999807662987941L;

	/**
	 *  auto generated ID
	 */
	private long idBlindStructure;
	/**
	 * this string contains the JSON structure representing the bliond structure:
	 * eg:
	 * {
	 *	    levels : 
	 *		[
	 *			{
	 *				"id" : "0",
	 *				"blinds" : "25/50/0",
	 *				"duration" : "30",
	 *			},
	 *			...
	 *			...
	 *			{
	 *				"id" : "5",
	 *				"blinds" : "500/1000/100",
	 *				"duration" : "30",
	 *			}
	 *			...
	 *			...
	 *		...
	 *		]
	 *	}
	 */
    private String structureJson;
    
    /**
     * tournament linked to the blind structure
     */
	private Set<Tournament> tournaments = new HashSet<Tournament>(0);
     
	
    public BlindStructure() {
    	
    } 
    
    public BlindStructure(String structureJson) {
        this.structureJson = structureJson;
    }   
    
    /**
     * 
     * @param structureJson structure as Json 
     * @param tournaments
     */
	public BlindStructure(String structureJson, Set<Tournament> tournaments) {
		this.structureJson = structureJson;
		this.tournaments = tournaments;
	}

    public long getIdBlindStructure() {
        return this.idBlindStructure;
    }
 
    public void setIdBlindStructure(long idBlindStructure) {
        this.idBlindStructure = idBlindStructure;
    }
 
    public String getStructureJson() {
        return this.structureJson;
    }
 
    public void setStructureJson(String structureJson) {
        this.structureJson = structureJson;
    }
    
	public Set<Tournament> getTournaments() {
		return this.tournaments;
	}

	public void setTournaments(Set<Tournament> tournaments) {
		this.tournaments = tournaments;
	}

}
