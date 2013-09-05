package org.imie.DTO;

import java.util.List;

/**
 * Le DTO représente une compétence. Ce DTO est concu en fonction des besoins de
 * l'interface Il ne peut pas être utilisé pour afficher les niveaux des Users
 * à partir d'une compétence par ex.
 * 
 * @author imie
 * 
 */
public class CompetenceDTO {
	
	//attributs de classe
	private String libelle;
	private NiveauDTO niveau;
	private int id;
	private List<MotClefDTO> motClef;
	
	public int getId() {
		return id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public NiveauDTO getNiveau() {
		return niveau;
	}

	public void setNiveau(NiveauDTO niveau) {
		this.niveau = niveau;
	}
	
	
	// remplacement du setMotClef par les methodes de l'API List
	public void addMotClef(MotClefDTO motClef) {
		this.motClef.add(motClef);
	}

	public void removeMotClef(MotClefDTO motClef) {
		this.motClef.remove(motClef);
	}

	public List<MotClefDTO> getMotClef() {
		return motClef;
	}
	
	
}

