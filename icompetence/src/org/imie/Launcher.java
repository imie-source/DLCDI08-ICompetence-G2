package org.imie;

import org.imie.DTO.UserDTO;
import org.imie.IHM.ConsoleIHM;
import org.imie.factory.BaseConcreteFactory;
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
		//ConsoleIHM.getInstance().start();
		IUserService userService = BaseConcreteFactory.getInstance()
				.createUserService(null);
		
		
	

		UserDTO userDTOToIdentify = new UserDTO();
		UserDTO userDTOToFound = new UserDTO();
		
		userDTOToIdentify.setIdentifiant("fatmar");	
		System.out.println(userDTOToIdentify.getIdentifiant());
		try {
			userDTOToFound =  userService.getUser(userDTOToIdentify);

		} catch (TransactionalConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (userDTOToFound != null) {
			// System.out.println("pwd base " +userDtoFound.getPassword()
			// +" password session: "+ password);
			String pwdBase = userDTOToFound.getMotDePasse().replaceAll("\\s", "");
			System.out.println("mot de passe en base :" + pwdBase);
			
			
			
	}

	
}
	
}
