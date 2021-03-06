package org.imie.factory;

import org.imie.DAO.interfaces.IAdresseDAO;
import org.imie.DAO.interfaces.ICompetenceDAO;
import org.imie.DAO.interfaces.ICursusDAO;
import org.imie.DAO.interfaces.IGroupeDeTravailDAO;
import org.imie.DAO.interfaces.IMotClefDAO;
import org.imie.DAO.interfaces.INiveauDAO;
import org.imie.DAO.interfaces.IUserDAO;
import org.imie.service.interfaces.ICompetenceService;
import org.imie.service.interfaces.ICursusService;
import org.imie.service.interfaces.IMotClefService;
import org.imie.service.interfaces.INiveauService;
import org.imie.service.interfaces.IUserService;
import org.imie.transactionalFramework.ITransactional;

/**
 * Interface de la factory de l'application
 * 
 * @author imie
 * 
 */
public interface IFactory {
	/**
	 * créer un service transactionel (un proxy de service) gérant les Users
	 * 
	 * @param caller
	 *            objet transactionel appelant ce service. null si début de
	 *            transaction
	 * @return
	 */
	public abstract IUserService createUserService(ITransactional caller);

	/**
	 * créer un service transactionel (un proxy de service) gérant les cursus
	 * 
	 * @param caller
	 *            objet transactionel appelant ce service. null si début de
	 *            transaction
	 * @return
	 */
	public abstract ICursusService createCursusService(ITransactional caller);

	/**
	 * créer un dao transactionel dédié à la table utilisateur (un proxy de dao)
	 * 
	 * @param caller
	 *            objet transactionel appelant ce service. null si début de
	 *            transaction
	 * @return
	 */

	public abstract ICompetenceService createCompetenceService(
			ITransactional caller);

	public abstract INiveauService createNiveauService(ITransactional caller);

	public abstract IMotClefService createMotClefService(ITransactional caller);

	public abstract IUserDAO createUserDAO(ITransactional caller);

	/**
	 * créer un dao transactionel dédié à la table cursus (un proxy de dao)
	 * 
	 * @param caller
	 *            objet transactionel appelant ce service. null si début de
	 *            transaction
	 * @return
	 */
	public abstract ICursusDAO createCursusDAO(ITransactional caller);

	/**
	 * créer un dao transactionel dédié à la table niveau (un proxy de dao)
	 * 
	 * @param caller
	 *            objet transactionel appelant ce service. null si début de
	 *            transaction
	 * @return
	 */
	public abstract INiveauDAO createNiveauDAO(ITransactional caller);

	public abstract IMotClefDAO createMotClefDAO(ITransactional caller);

	public abstract ICompetenceDAO createCompetenceDAO(ITransactional caller);

	public abstract IGroupeDeTravailDAO creerGroupeDeTravailDAO(
			ITransactional caller);

	/**
	 * créer un dao transactionel dédié à la table groupework (groupe de
	 * travail) (un proxy de dao)
	 * 
	 * @param caller
	 *            objet transactionel appelant ce service. null si début de
	 *            transaction
	 * @return
	 */

	/**
	 * créer un dao transactionel dédié à la table adresse (un proxy de dao)
	 * 
	 * @param caller
	 *            objet transactionel appelant ce service. null si début de
	 *            transaction
	 * @return
	 */
	public abstract IAdresseDAO createAdresseDAO(ITransactional caller);

}