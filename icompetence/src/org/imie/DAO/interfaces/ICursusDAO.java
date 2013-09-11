package org.imie.DAO.interfaces;

import java.util.List;

import org.imie.DTO.CursusDTO;
import org.imie.DTO.UserDTO;
import org.imie.transactionalFramework.ITransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;
/**
 * interface de DAO de la table cursus
 * @author imie
 *
 */
public interface ICursusDAO extends ITransactional{

	/**
	 * rechercher le cursus d'un utilisateur
	 * @param userDTO
	 * @return
	 * @throws ClassNotFoundException
	 * @throws TransactionalConnectionException
	 */
	public abstract CursusDTO findByUser(UserDTO userDTO) throws 
			TransactionalConnectionException;

	/**
	 * recherche de tous les cursus
	 * @return
	 * @throws ClassNotFoundException
	 * @throws TransactionalConnectionException
	 */
	public abstract List<CursusDTO> findAll() throws TransactionalConnectionException;

	public abstract CursusDTO findById(Integer cursusid) throws TransactionalConnectionException;
	
	public abstract CursusDTO insertCursus(CursusDTO cursusToInsert) throws TransactionalConnectionException;
	
	public abstract CursusDTO updateCursus(CursusDTO cursusToUpdate) throws TransactionalConnectionException;
	
	public abstract CursusDTO deleteCursus(CursusDTO cursusToDelete) throws TransactionalConnectionException;
	

}