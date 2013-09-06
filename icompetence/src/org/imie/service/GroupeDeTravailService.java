package org.imie.service;

import java.util.List;

import org.imie.DAO.interfaces.IGroupeDeTravailDAO;
import org.imie.DTO.GroupeDeTravailDTO;
import org.imie.DTO.UserDTO;
import org.imie.factory.BaseConcreteFactory;
import org.imie.service.interfaces.IGroupeDeTravailService;
import org.imie.service.interfaces.IUserService;
import org.imie.transactionalFramework.ATransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

public class GroupeDeTravailService extends ATransactional implements
		IGroupeDeTravailService {

	@Override
	public Boolean creerGroupeDeTravail(
			GroupeDeTravailDTO groupeDeTravailAInserer)
			throws TransactionalConnectionException {
		IGroupeDeTravailDAO gdtDAO = BaseConcreteFactory.getInstance()
				.creerGroupeDeTravailDAO(this);
		return gdtDAO.creerGroupeDeTravail(groupeDeTravailAInserer);
	}

	@Override
	public Boolean modifierGroupeDeTravail(
			GroupeDeTravailDTO groupeDeTravailAModifier)
			throws TransactionalConnectionException {
		IGroupeDeTravailDAO gdtDAO = BaseConcreteFactory.getInstance()
				.creerGroupeDeTravailDAO(this);
		return gdtDAO.modifierGroupeDeTravail(groupeDeTravailAModifier);
	}

	@Override
	public void supprimerGroupeDeTravail(
			GroupeDeTravailDTO groupeDeTravailASupprimer)
			throws TransactionalConnectionException {
		IGroupeDeTravailDAO gdtDAO = BaseConcreteFactory.getInstance()
				.creerGroupeDeTravailDAO(this);
	}

	@Override
	public Boolean supprimerUserGroupeDeTravail(UserDTO userDTO,
			GroupeDeTravailDTO gdtDTO) throws TransactionalConnectionException {
		IGroupeDeTravailDAO gdtDAO = BaseConcreteFactory.getInstance()
				.creerGroupeDeTravailDAO(this);
		return gdtDAO.supprimerUserGroupeDeTravail(userDTO, gdtDTO);
	}

	@Override
	public Boolean creerUserGdt(UserDTO userDTO, GroupeDeTravailDTO gdtDTO) {
		IGroupeDeTravailDAO gdtDAO = BaseConcreteFactory.getInstance()
				.creerGroupeDeTravailDAO(this);
		return gdtDAO.creerUserGdt(userDTO, gdtDTO);
	}

	@Override
	public Boolean modifCP(UserDTO userDTO, GroupeDeTravailDTO gdtDTO) {
		IGroupeDeTravailDAO gdtDAO = BaseConcreteFactory.getInstance()
				.creerGroupeDeTravailDAO(this);

		return gdtDAO.modifCP(userDTO, gdtDTO);
	}

	@Override
	public List<GroupeDeTravailDTO> afficherGroupeDeTravail()
			throws TransactionalConnectionException {
		IGroupeDeTravailDAO gdtDAO = BaseConcreteFactory.getInstance()
				.creerGroupeDeTravailDAO(this);
		return gdtDAO.afficherGroupeDeTravail();
	}

}
