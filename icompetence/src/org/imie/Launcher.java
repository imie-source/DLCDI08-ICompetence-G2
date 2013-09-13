package org.imie;

import java.util.List;

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
	 * @throws TransactionalConnectionException 
	 */
	public static void main(String[] args) throws TransactionalConnectionException {
		// ConsoleIHM.getInstance().start();

		IGroupeDeTravailService gdtService = BaseConcreteFactory.getInstance()
				.creerGroupeDeTravailService(null);

		List<GroupeDeTravailDTO> gdtDTO = gdtService.afficherGroupeDeTravail();
		GroupeDeTravailDTO cgdtDTO = gdtDTO.get(1);
		
		List<UserDTO> listDTO = gdtService.utilisateurParGroupeDeTravail(cgdtDTO);
		
		for (UserDTO userDTO : listDTO) {
			System.out.println(userDTO.getNom());
			
		}
		
		
	}
}
