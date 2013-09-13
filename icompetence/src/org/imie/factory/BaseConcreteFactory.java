package org.imie.factory;

import org.imie.DAO.AdresseDAO;
import org.imie.DAO.CompetenceDAO;
import org.imie.DAO.CursusDAO;
import org.imie.DAO.GroupeDeTravailDAO;
import org.imie.DAO.MotClefDAO;
import org.imie.DAO.NiveauDAO;
import org.imie.DAO.UserDAO;
import org.imie.DAO.interfaces.IAdresseDAO;
import org.imie.DAO.interfaces.ICompetenceDAO;
import org.imie.DAO.interfaces.ICursusDAO;
import org.imie.DAO.interfaces.IGroupeDeTravailDAO;
import org.imie.DAO.interfaces.IMotClefDAO;
import org.imie.DAO.interfaces.INiveauDAO;
import org.imie.DAO.interfaces.IUserDAO;
import org.imie.service.CompetenceService;
import org.imie.service.CursusService;
import org.imie.service.MotClefService;
import org.imie.service.NiveauService;
import org.imie.service.GroupeDeTravailService;
import org.imie.service.UserService;
import org.imie.service.interfaces.ICompetenceService;
import org.imie.service.interfaces.ICursusService;
import org.imie.service.interfaces.IMotClefService;
import org.imie.service.interfaces.INiveauService;
import org.imie.service.interfaces.IGroupeDeTravailService;
import org.imie.service.interfaces.IUserService;
import org.imie.transactionalFramework.ITransactional;
import org.imie.transactionalFramework.TransactionalFactory;

/**
 * factory de base de l'application
 * 
 * @author imie
 * 
 */
public class BaseConcreteFactory implements IFactory {

	/**
	 * attribut stockant l'instance du singleton
	 */
	private static BaseConcreteFactory instance;

	/**
	 * modification de la portée du constructeur pour ne pas pouvoir faire new
	 * BaseFActory hors de la classe
	 */
	private BaseConcreteFactory() {
		super();
	};

	/**
	 * methode qui retourne toujours la même instance de BaseConcreteFactory
	 * 
	 * @return le singleton instancié
	 */
	public static synchronized BaseConcreteFactory getInstance() {
		if (instance == null) {
			instance = new BaseConcreteFactory();
		}
		return instance;
	}

	public IGroupeDeTravailService creerGroupeDeTravailService(
			ITransactional caller) {
		TransactionalFactory<GroupeDeTravailService> fact = (TransactionalFactory<GroupeDeTravailService>) TransactionalFactory
				.getInstance();
		return fact.createTransactionalService(new GroupeDeTravailService(),
				caller);

	}

	public IUserService createUserService(ITransactional caller) {
		TransactionalFactory<UserService> fact = (TransactionalFactory<UserService>) TransactionalFactory
				.getInstance();
		return fact.createTransactionalService(new UserService(), caller);
	}

	public ICursusService createCursusService(ITransactional caller) {
		TransactionalFactory<CursusService> fact = (TransactionalFactory<CursusService>) TransactionalFactory
				.getInstance();
		return fact.createTransactionalService(new CursusService(), caller);
	}

	public IUserDAO createUserDAO(ITransactional caller) {
		TransactionalFactory<UserDAO> fact = (TransactionalFactory<UserDAO>) TransactionalFactory
				.getInstance();
		return fact.createTransactionalService(new UserDAO(), caller);
	}

	public ICursusDAO createCursusDAO(ITransactional caller) {
		TransactionalFactory<CursusDAO> fact = (TransactionalFactory<CursusDAO>) TransactionalFactory
				.getInstance();
		return fact.createTransactionalService(new CursusDAO(), caller);
	}

	public ICompetenceDAO createCompetenceDAO(ITransactional caller) {
		TransactionalFactory<CompetenceDAO> fact = (TransactionalFactory<CompetenceDAO>) TransactionalFactory
				.getInstance();
		return fact.createTransactionalService(new CompetenceDAO(), caller);
	}

	public IGroupeDeTravailDAO creerGroupeDeTravailDAO(ITransactional caller) {
		TransactionalFactory<GroupeDeTravailDAO> fact = (TransactionalFactory<GroupeDeTravailDAO>) TransactionalFactory
				.getInstance();
		return fact
				.createTransactionalService(new GroupeDeTravailDAO(), caller);
	}

	public IAdresseDAO createAdresseDAO(ITransactional caller) {
		TransactionalFactory<AdresseDAO> fact = (TransactionalFactory<AdresseDAO>) TransactionalFactory
				.getInstance();
		return fact.createTransactionalService(new AdresseDAO(), caller);

	}

	public INiveauDAO createNiveauDAO(ITransactional caller) {
		TransactionalFactory<NiveauDAO> fact = (TransactionalFactory<NiveauDAO>) TransactionalFactory
				.getInstance();
		return fact.createTransactionalService(new NiveauDAO(), caller);
	}

	public ICompetenceService createCompetenceService(ITransactional caller) {
		TransactionalFactory<CompetenceService> fact = (TransactionalFactory<CompetenceService>) TransactionalFactory
				.getInstance();
		return fact.createTransactionalService(new CompetenceService(), caller);
	}

	public INiveauService createNiveauService(ITransactional caller) {
		TransactionalFactory<NiveauService> fact = (TransactionalFactory<NiveauService>) TransactionalFactory
				.getInstance();
		return fact.createTransactionalService(new NiveauService(), caller);
	}

	public IMotClefService createMotClefService(ITransactional caller) {
		TransactionalFactory<MotClefService> fact = (TransactionalFactory<MotClefService>) TransactionalFactory
				.getInstance();
		return fact.createTransactionalService(new MotClefService(), caller);
	}


	public IMotClefDAO createMotClefDAO(ITransactional caller) {
		TransactionalFactory<MotClefDAO> fact = (TransactionalFactory<MotClefDAO>) TransactionalFactory
				.getInstance();
		return fact.createTransactionalService(new MotClefDAO(), caller);
	}
}
