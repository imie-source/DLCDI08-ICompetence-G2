package org.imie.DAO.interfaces;

import java.util.List;

import org.imie.DTO.CompetenceDTO;
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

}