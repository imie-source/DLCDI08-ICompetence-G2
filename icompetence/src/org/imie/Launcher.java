package org.imie;

import java.util.List;

import org.imie.DTO.CompetenceDTO;
import org.imie.DTO.GroupeDeTravailDTO;
import org.imie.DTO.MotClefDTO;
import org.imie.DTO.UserDTO;
import org.imie.IHM.ConsoleIHM;
import org.imie.exeptionManager.ExceptionManager;
import org.imie.factory.BaseConcreteFactory;
import org.imie.service.interfaces.ICompetenceService;
import org.imie.service.interfaces.IGroupeDeTravailService;
import org.imie.service.interfaces.IMotClefService;
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
	 * @throws TransactionalConnectionException
	 */
	public static void main(String[] args) throws TransactionalConnectionException {
		//ConsoleIHM.getInstance().start();
		IMotClefService mcService = BaseConcreteFactory.getInstance().createMotClefService(null);


		IGroupeDeTravailService gdtServ = BaseConcreteFactory
				.getInstance().creerGroupeDeTravailService(null);

		
		GroupeDeTravailDTO gdtdto = new GroupeDeTravailDTO();
		gdtdto.setId_gdt(1);
		UserDTO userDTO = new UserDTO();
		userDTO.setId(3);
		gdtServ.supprimerUserGroupeDeTravail(userDTO, gdtdto);
		
}
}
