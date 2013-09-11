package org.imie.DTO;

import java.util.ArrayList;
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
	private List<NiveauDTO> niveauxDTOs;
	private int id;
	private Integer idParent;
	private List<Integer> chemin;
	private List<MotClefDTO> motClefs;
	
	
	// constructeur
		// Ce constructeur par défaut est necessaire pour initialiser la liste des
		// utilisateurs et des mots clés pour être sur que cette liste ne soit pas vide lors de
		// l'instanciation d'un nouvel objet
		public CompetenceDTO() {
			super();
			// initialisation de la liste des utilisateurs d'une competence
			niveauxDTOs = new ArrayList<NiveauDTO>();

			// initialisation de la liste des mots clés d'une competence
			motClefs = new ArrayList<MotClefDTO>();
		}
	
	
	
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	
	
	// remplacement du setMotClef par les methodes de l'API List
	public void addMotClef(MotClefDTO motClef) {
		this.motClefs.add(motClef);
	}

	public void removeMotClef(MotClefDTO motClef) {
		this.motClefs.remove(motClef);
	}

	public List<MotClefDTO> getMotClef() {
		return motClefs;
	}

	//installation de l'arbre de competences
	public Integer getIdParent() {
		return idParent;
	}

	public void setIdParent(Integer idParent) {
		this.idParent = idParent;
	}
	
	public List<Integer> getChemin() {
		return chemin;
	}

	public void setChemin(List<Integer> chemin) {
		this.chemin = chemin;
	}
	
	
	//liste de niveaux de competences
	public void addNiveau(NiveauDTO niveauDTO) {
		this.niveauxDTOs.add(niveauDTO);
	}

	public void removeNiveau(NiveauDTO niveauDTO) {
		this.niveauxDTOs.remove(niveauDTO);
	}

	public List<NiveauDTO> getNiveau() {
		return niveauxDTOs;
	}




	
	
	
}

