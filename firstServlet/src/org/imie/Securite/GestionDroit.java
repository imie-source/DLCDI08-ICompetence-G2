package org.imie.Securite;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;

import org.imie.DTO.UserDTO;

public class GestionDroit extends HttpServlet {

	private UserDTO userConnecte;
	private List<UserDTO> utilisateursAutorises = new ArrayList<UserDTO>();

	public UserDTO getUserConnecte() {
		return userConnecte;
	}

	public void setUserConnecte(UserDTO userConnecte) {
		this.userConnecte = userConnecte;
	}

	public void addUtilisateurAutorises(UserDTO u) {
		utilisateursAutorises.add(u);

	}

	public void removeUtilisateurAutorises(UserDTO u) {
		utilisateursAutorises.remove(utilisateursAutorises.indexOf(u));

	}

	public boolean estAutorise(int niveauDeProfil, List<UserDTO> users) {

		boolean validation;
		validation = false;
		// admin tous les droits
		if (userConnecte.getProfil() == 3) {
			validation = true;
		}
		// sinon niveau de profil = celui requis
		else if (userConnecte.getProfil() == niveauDeProfil) {
			validation = true;

		}

		else if (userConnecte.getProfil() != niveauDeProfil) {
			for (UserDTO current : users) {
				if (estProprietaire(current)) {
					validation = true;
				} else {
					validation = false;
				}

			}

		}
		return validation;
	}

	public boolean estProprietaire(UserDTO u) {

		return userConnecte.getIdentifiant().replaceAll("\\s", "")
				.equalsIgnoreCase(u.getIdentifiant());
	}

}
