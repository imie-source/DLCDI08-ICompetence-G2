package org.imie.DAO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.imie.DAO.interfaces.ICursusDAO;

import org.imie.DTO.CursusDTO;
import org.imie.DTO.UserDTO;
import org.imie.exeptionManager.ExceptionManager;
import org.imie.transactionalFramework.ATransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

/**
 * DAO de la table cursus(formation de l'utilisateur)
 * 
 * @author imie
 * 
 */
public class CursusDAO extends ATransactional implements ICursusDAO {
	
	/**
	 * 
	 * renvoie un dto le cursus (la formation) d'un utilisateur
	 *
	 */
	public CursusDTO findByUser(UserDTO userDTO) throws TransactionalConnectionException {

		// initialisation de la liste qui servira au retour
		CursusDTO cursusDTO = null;
		// déclaration de la variable de statement
		Statement statement = null;
		// déclaration de la variable de resultset
		ResultSet resultSet = null;
		try {
			// création du statement à partir de la connection
			String selectInstruction = "select f.id_formation, libelle from formation as f  join utilisateur as u on f.id_formation = u.id_formation "
					+ "where id_utilisateur=?";
			PreparedStatement preparedStatement = getConnection().prepareStatement(selectInstruction);
			preparedStatement.setInt(1, userDTO.getId());
			resultSet = preparedStatement.executeQuery();
			// parcours du resultset

			if (resultSet.next()) {
				cursusDTO = new CursusDTO();
				// affectation des attribut du UserDTO à partir des valeurs du
				// resultset sur l'enregistrement courant
				cursusDTO.setLibelle(resultSet.getString("libelle"));
				cursusDTO.setId(resultSet.getInt("id_formation"));
				// ajout du DTO dans la liste de retour

			}

		} catch (SQLException e) {
			ExceptionManager.getInstance().manageException(e);
		} finally {
			// libération des ressources

			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (statement != null) {
					statement.close();
				}

			} catch (SQLException e) {
				ExceptionManager.getInstance().manageException(e);
			}
		}
		return cursusDTO;
	}

	/**
	 * renvoie la liste des formations (liste de DTO)	 
	 * 
	 */
	
	public List<CursusDTO> findAll() throws TransactionalConnectionException {

		// initialisation de la liste qui servira au retour
		List<CursusDTO> cursusDTOs = new ArrayList<CursusDTO>();
		// déclaration de la variable de statement
		Statement statement = null;
		// déclaration de la variable de resultset
		ResultSet resultSet = null;
		try {
			// création du statement à partir de la connection
			String selectInstruction = "select id_formation,libelle from formation";
			PreparedStatement preparedStatement = getConnection().prepareStatement(selectInstruction);
			resultSet = preparedStatement.executeQuery();
			// parcours du resultset
			while (resultSet.next()) {
				CursusDTO cursusDTO = new CursusDTO();
				// affectation des attribut du UserDTO à partir des valeurs du
				// resultset sur l'enregistrement courant
				cursusDTO.setLibelle(resultSet.getString("libelle"));
				cursusDTO.setId(resultSet.getInt("id_formation"));
				// ajout du DTO dans la liste de retour
				cursusDTOs.add(cursusDTO);
			}

		} catch (SQLException e) {
			ExceptionManager.getInstance().manageException(e);
		} finally {

			// libération des ressources
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (statement != null) {
					statement.close();
				}

			} catch (SQLException e) {
				ExceptionManager.getInstance().manageException(e);
			}
		}
		return cursusDTOs;
	}
	

	/**
	 * renvoie un cursusDTO quand on envoie un id (de formation/cursus), libelle et id
	 */
	public CursusDTO findById(Integer cursusid) throws TransactionalConnectionException {
		// initialisation du dto qui servira au retour
		CursusDTO cursusDTO = new CursusDTO();

		// déclaration de la variable de statement
		Statement statement = null;
		// déclaration de la variable de resultset
		ResultSet resultSet = null;

		try {
			if (cursusDTO != null) {
				// création du statement à partir de la connection
				String selectInstruction = "select id_formation,libelle from formation Where id_formation=? ";
				PreparedStatement preparedStatement = getConnection().prepareStatement(selectInstruction);
				preparedStatement.setInt(1, cursusid);
				resultSet = preparedStatement.executeQuery();
				// parcours du resultset
			}

			while (resultSet.next()) {

				// affectation des attribut du UserDTO à partir des valeurs du
				// resultset sur l'enregistrement courant
				cursusDTO.setLibelle(resultSet.getString("libelle"));
				cursusDTO.setId(resultSet.getInt("id_formation"));
			}

		} catch (SQLException e) {
			ExceptionManager.getInstance().manageException(e);
		} finally {

			// libération des ressources
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (statement != null) {
					statement.close();
				}

			} catch (SQLException e) {
				ExceptionManager.getInstance().manageException(e);
			}
		}
		return cursusDTO;
	}

	@Override
	public CursusDTO insertCursus(CursusDTO cursusToInsert)
			throws TransactionalConnectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CursusDTO updateCursus(CursusDTO cursusToUpdate)
			throws TransactionalConnectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCursus(CursusDTO cursusToDelete)
			throws TransactionalConnectionException {
		// TODO Auto-generated method stub
		
	}
}
