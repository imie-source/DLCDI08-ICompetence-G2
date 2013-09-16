package org.imie.DTO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * DTO représantant un utilisateur
 * 
 * @author imie
 * 
 */
public class UserDTO {

	// attributs de classe

	private String nom;
	private String prenom;
	private String adresse_mail;
	private Date dateNaiss;
	private boolean disponible;
	private String identifiant;
	private String pwd;
	private Integer id;
	private Integer age;

	private List<CompetenceDTO> competences;
	private CursusDTO cursus;
	/**
	 * @author ym TODO un Utilisateur n'a qu'une adresse : à supprimer
	 * 
	 */
	private List<AdresseDTO> adresses;
	private AdresseDTO adresse;
	private String motDePasse;
	private Integer profil;
	public void setAdresse(AdresseDTO adresse) {
		this.adresse = adresse;
	}

	// constructeur
	// Ce constructeur par défaut est necessaire pour initialiser la liste des
	// compétence pour être sur que cette liste ne soit pas vide lors de
	// l'instanciation d'un nouvel objet
	public UserDTO() {
		super();
		// initialisation de la liste des compétences
		competences = new ArrayList<CompetenceDTO>();

		// initialisation de la liste des adresses
		adresses = new ArrayList<AdresseDTO>();
	}

	// accesseurs
	public Integer getAge() {
		Integer age = null;
		if (getDateNaiss() != null) {
			Calendar dob = Calendar.getInstance();
			dob.setTime(getDateNaiss());
			Calendar today = Calendar.getInstance();
			age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
			if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
				age--;
			} else if (today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)
					&& today.get(Calendar.DAY_OF_MONTH) < dob
							.get(Calendar.DAY_OF_MONTH)) {
				age--;
			}
		}
		return age;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	// remplacement du setCompetence par les methodes de l'API List
	public void addCompetence(CompetenceDTO competenceDTO) {
		this.competences.add(competenceDTO);
	}

	// remplacement du setCompetence par les methodes de l'API List
	public void removeCompetence(CompetenceDTO competenceDTO) {
		this.competences.remove(competenceDTO);
	}

	public List<CompetenceDTO> getCompetences() {
		return competences;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateNaiss() {
		return dateNaiss;
	}

	public void setDateNaiss(Date dateNaiss) {
		this.dateNaiss = dateNaiss;
	}

	public CursusDTO getCursus() {
		return cursus;
	}

	public void setCursus(CursusDTO cursus) {
		this.cursus = cursus;
	}

	public String getAdresse_mail() {
		return adresse_mail;
	}

	public void setAdresse_mail(String adresse_mail) {
		this.adresse_mail = adresse_mail;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public List<AdresseDTO> getAdresses() {
		return adresses;
	}

	// remplacement du setAdresse par les methodes de l'API List
	public void addAdresse(AdresseDTO adresseDTO) {
		this.adresses.add(adresseDTO);
	}

	public void removeAdresses(AdresseDTO adresseDTO) {
		this.adresses.remove(adresseDTO);
	}

	public AdresseDTO getAdresse() {
		return adresse;
	}
	

	public String getMotDePasse() {
		return motDePasse;
	}
	
	public void setMotDePasse(String mdp) {
		motDePasse = mdp;
	}

	public Integer getProfil() {
		return profil;
	}

	public void setProfil(Integer profil) {
		this.profil = profil;
	}
}
