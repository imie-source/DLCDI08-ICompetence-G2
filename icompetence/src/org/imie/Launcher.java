package org.imie;

import org.imie.DTO.GroupeDeTravailDTO;
import org.imie.DTO.UserDTO;
import org.imie.IHM.ConsoleIHM;
import org.imie.factory.BaseConcreteFactory;
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

		IGroupeDeTravailService gdtService = BaseConcreteFactory.getInstance()
				.creerGroupeDeTravailService(null);

		GroupeDeTravailDTO gdtDTOcreer = new GroupeDeTravailDTO();
		String nomParam = "toto";
		String typeParam = "type";
		gdtDTOcreer.setType_projet(typeParam);
		gdtDTOcreer.setNom(nomParam);
		try {
			System.out.println("creation du groupe de travail");
			gdtService.creerGroupeDeTravail(gdtDTOcreer);
		} catch (TransactionalConnectionException e) {
			System.out.println("echec de la creation du groupe de travail");
			e.printStackTrace();
		}

	}
}
