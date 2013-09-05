package org.imie.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DTO représentant un groupe de travail
 * 
 * @author Arnvis
 * 
 */

public class GroupeDeTravailDTO {

	private String nom;
	private String type_projet;
	private String bilan;
	private int id_util;
	private int id_etat;
	private String libelleEtat;
	private Integer id_gdt;

	private List<UserDTO> utilisateurs;

	public GroupeDeTravailDTO() {
		utilisateurs = new ArrayList<UserDTO>();

	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getType_projet() {
		return type_projet;
	}

	public void setType_projet(String type_projet) {
		this.type_projet = type_projet;
	}

	public String getBilan() {
		return bilan;
	}

	public void setBilan(String bilan) {
		this.bilan = bilan;
	}

	public int getId_util() {
		return id_util;
	}

	public void setId_util(int i) {
		this.id_util = i;
	}

	public Integer getId_etat() {
		return id_etat;
	}

	public void setId_etat(int id_etat) {
		this.id_etat = id_etat;
	}

	public Integer getId_gdt() {
		return id_gdt;
	}

	public void setId_gdt(Integer id_gdt) {
		this.id_gdt = id_gdt;
	}

	public String getLibelleEtat() {
		return libelleEtat;
	}

	public void setLibelleEtat(String libelleEtat) {
		this.libelleEtat = libelleEtat;
	}

}
