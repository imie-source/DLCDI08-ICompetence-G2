package org.imie.DTO;

public class AdresseDTO {
	private Integer id_adresse;
	private Integer code_postal;
	private String ville;
	private String libelle;
	
	/**
	 * @return the code_postal
	 */
	public Integer getCode_postal() {
		return code_postal;
	}
	/**
	 * @param code_postal the code_postal to set
	 */
	public void setCode_postal(Integer code_postal) {
		this.code_postal = code_postal;
	}
	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}
	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}
	/**
	 * @return the adresse
	 */
	public String getLibelle() {
		return libelle;
	}
	/**
	 * @param adresse the adresse to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Integer getId_adresse() {
		return id_adresse;
	}
	public void setId_adresse(int id_adresse) {
		this.id_adresse = id_adresse;
	}

	
}
