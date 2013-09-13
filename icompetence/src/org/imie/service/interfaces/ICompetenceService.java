package org.imie.service.interfaces;

import java.util.List;

import org.imie.DTO.CompetenceDTO;
import org.imie.DTO.MotClefDTO;
import org.imie.DTO.NiveauDTO;
import org.imie.DTO.UserDTO;
import org.imie.transactionalFramework.ITransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

public interface ICompetenceService extends ITransactional {

	List<CompetenceDTO> getCompetenceByUser(UserDTO userDTO)
			throws TransactionalConnectionException;

	List<CompetenceDTO> findAll() throws TransactionalConnectionException;

	CompetenceDTO findById(Integer competenceid)
			throws TransactionalConnectionException;

	CompetenceDTO insertCompetence(CompetenceDTO competenceToInsert)
			throws TransactionalConnectionException;

	CompetenceDTO updateCompetence(CompetenceDTO competenceToUpdate)
			throws TransactionalConnectionException;

	void deleteCompetence(CompetenceDTO competenceToDelete)
			throws TransactionalConnectionException;

	Boolean creerCompUserNiveau(UserDTO userDTO, CompetenceDTO competenceDTO,
			NiveauDTO niveauDTO) throws TransactionalConnectionException;

	void modifierCompUserNiveau(UserDTO userDTO, CompetenceDTO competenceDTO,
			NiveauDTO niveauDTO) throws TransactionalConnectionException;

	void supprimerCompUserNiveau(UserDTO userDTO, CompetenceDTO competenceDTO,
			NiveauDTO niveauDTO) throws TransactionalConnectionException;

	Boolean creerCompMotClef(CompetenceDTO competenceDTO, MotClefDTO mot_clefDTO)
			throws TransactionalConnectionException;

	void modifierCompMotClef(CompetenceDTO competenceDTO, MotClefDTO mot_clefDTO)
			throws TransactionalConnectionException;

	void supprimerCompMotClef(CompetenceDTO competenceDTO,
			MotClefDTO mot_clefDTO) throws TransactionalConnectionException;

	List<CompetenceDTO> findAllArbo() throws TransactionalConnectionException;
	
	List<CompetenceDTO> findArboFilsPere(Integer id) throws TransactionalConnectionException;
	
	List<CompetenceDTO> findByNom(String competencenom) throws TransactionalConnectionException;

}
