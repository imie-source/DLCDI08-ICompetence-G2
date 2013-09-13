package org.imie;

import java.util.List;

import org.imie.DTO.CompetenceDTO;
import org.imie.DTO.GroupeDeTravailDTO;
import org.imie.DTO.UserDTO;
import org.imie.IHM.ConsoleIHM;
import org.imie.exeptionManager.ExceptionManager;
import org.imie.factory.BaseConcreteFactory;
import org.imie.service.interfaces.ICompetenceService;
import org.imie.service.interfaces.IGroupeDeTravailService;
import org.imie.service.interfaces.IUserService;
import org.imie.transactionalFramework.TransactionalConnectionException;

/**
 * classe principale de l'application contenant le main
 * 
 * @author imie
 * 
 */
public class Launcher {

	/**
	 * point d'entrée de l'application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// ConsoleIHM.getInstance().start();

		IUserService usrSvc = BaseConcreteFactory
				.getInstance().createUserService(null);
		

		UserDTO userToFind = new UserDTO();
		userToFind.setIdentifiant("youmet");
		
		
		try {

			UserDTO userFound = usrSvc.getUser(userToFind);
			userToFind.setProfil(userFound.getProfil());

		} catch (TransactionalConnectionException e) {
			ExceptionManager.getInstance().manageException(e);
		}
		System.out.println(userToFind.getProfil());
	}
	
}
