package org.imie.DAO.interfaces;

import java.util.List;

import org.imie.DTO.CompetenceDTO;
import org.imie.DTO.MotClefDTO;
import org.imie.DTO.NiveauDTO;
import org.imie.DTO.UserDTO;
import org.imie.transactionalFramework.ITransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

/**
 * interface de DAO de la table compétence
 * @author imie
 *
 */
public interface ICompetenceDAO extends ITransactional {

	/**
	 * @param userDTO
	 * @return liste des compétences par utilisateur
	 * @throws ClassNotFoundException
	 * @throws TransactionalConnectionException 
	 */
	public abstract List<CompetenceDTO> getCompetenceByUser(UserDTO userDTO) throws
			TransactionalConnectionException;

	public abstract List<CompetenceDTO> findAll() throws TransactionalConnectionException;

	public abstract CompetenceDTO findById(Integer competenceid) throws TransactionalConnectionException;
	
	public abstract CompetenceDTO insertCompetence(CompetenceDTO competenceToInsert) throws TransactionalConnectionException;
	
	public abstract CompetenceDTO updateCompetence(CompetenceDTO competenceToUpdate) throws TransactionalConnectionException;
	
	public abstract void deleteCompetence(CompetenceDTO competenceToDelete) throws TransactionalConnectionException;
	
	public abstract Boolean creerCompUserNiveau(UserDTO userDTO, CompetenceDTO competenceDTO, NiveauDTO niveauDTO) throws TransactionalConnectionException;
	
	public abstract void modifierCompUserNiveau(UserDTO userDTO,CompetenceDTO competenceDTO, NiveauDTO niveauDTO) throws TransactionalConnectionException;
	
	public abstract void supprimerCompUserNiveau(UserDTO userDTO,CompetenceDTO competenceDTO, NiveauDTO niveauDTO) throws TransactionalConnectionException;

	public abstract Boolean creerCompMotClef( CompetenceDTO competenceDTO, MotClefDTO mot_clefDTO) throws TransactionalConnectionException;
	
	public abstract void modifierCompMotClef( CompetenceDTO competenceDTO, MotClefDTO mot_clefDTO) throws TransactionalConnectionException;
	
	public abstract void supprimerCompMotClef( CompetenceDTO competenceDTO, MotClefDTO mot_clefDTO)	throws TransactionalConnectionException;
	
	public List<CompetenceDTO> findAllArbo() throws TransactionalConnectionException;
	
}