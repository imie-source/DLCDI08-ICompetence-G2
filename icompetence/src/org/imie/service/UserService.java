package org.imie.service;

import java.util.List;


import org.imie.DAO.interfaces.IGroupeDeTravailDAO;
import org.imie.DAO.interfaces.IUserDAO;
import org.imie.DTO.UserDTO;
import org.imie.factory.BaseConcreteFactory;
import org.imie.service.interfaces.IUserService;
import org.imie.transactionalFramework.ATransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

public class UserService extends ATransactional implements IUserService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.service.IUserService#getUsers()
	 */
	@Override
	public List<UserDTO> getUsers() throws TransactionalConnectionException {
		IUserDAO userDAO = BaseConcreteFactory.getInstance().createUserDAO(this);
		return userDAO.getUsers();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.service.IUserService#insertUser(org.imie.DTO.UserDTO)
	 */
	@Override
	public UserDTO insertUser(UserDTO userToInsert) throws TransactionalConnectionException {
		IUserDAO userDAO = BaseConcreteFactory.getInstance().createUserDAO(this);
		return userDAO.insertUser(userToInsert);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.service.IUserService#updateUser(org.imie.DTO.UserDTO)
	 */
	@Override
	public UserDTO updateUser(UserDTO userToUpdate) throws TransactionalConnectionException {
		IUserDAO userDAO = BaseConcreteFactory.getInstance().createUserDAO(this);
		return userDAO.updateUser(userToUpdate);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.service.IUserService#deleteUser(org.imie.DTO.UserDTO)
	 */
	@Override
	public void deleteUser(UserDTO userToDelete) throws TransactionalConnectionException {
		IUserDAO userDAO = BaseConcreteFactory.getInstance().createUserDAO(this);
		IGroupeDeTravailDAO groupeDeTravailDAO = BaseConcreteFactory.getInstance().creerGroupeDeTravailDAO(this);
//		groupeDeTravailDAO.modifCP(userToDelete);
		
		userDAO.deleteUser(userToDelete);
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.service.IUserService#getUser(org.imie.DTO.UserDTO)
	 */
	@Override
	public UserDTO getUser(UserDTO userToFind)	throws TransactionalConnectionException {
		IUserDAO userDAO = BaseConcreteFactory.getInstance().createUserDAO(this);	
		
		return userDAO.getUser(userToFind);
		
	}
@Override
	public void attachementCompetence(int userid,int competenceid,int niveauid)
			throws TransactionalConnectionException {
		IUserDAO userDAO = BaseConcreteFactory.getInstance().createUserDAO(this);
		userDAO.attachementCompetence(userid, competenceid, niveauid);
	}
@Override
public void modifattachementCompetence(int userid, int competenceid,
		int niveauid) throws TransactionalConnectionException {
	IUserDAO userDAO = BaseConcreteFactory.getInstance().createUserDAO(this);
	userDAO.modifattachementCompetence(userid, competenceid, niveauid);
}

}
