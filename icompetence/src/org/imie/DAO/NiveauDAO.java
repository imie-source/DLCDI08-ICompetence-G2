package org.imie.DAO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.imie.DAO.interfaces.INiveauDAO;
import org.imie.DTO.CompetenceDTO;
import org.imie.DTO.CursusDTO;
import org.imie.DTO.NiveauDTO;
import org.imie.DTO.UserDTO;
import org.imie.exeptionManager.ExceptionManager;
import org.imie.transactionalFramework.ATransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

public class NiveauDAO extends ATransactional implements INiveauDAO {

	/**
	 * renvoie toute la liste des niveaux (liste de DTO)
	 * 
	 */
	public List<NiveauDTO> findAll() throws TransactionalConnectionException {
				
		List<NiveauDTO> niveauDTOs = new ArrayList<NiveauDTO>();
		
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			
			String selectInstruction = "select id_niveau_comp, libelle from niveau_comp order by id_niveau_comp";
			PreparedStatement preparedStatement = getConnection().prepareStatement(selectInstruction);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				NiveauDTO niveauDTO = new NiveauDTO();
				niveauDTO.setLibelle(resultSet.getString("libelle"));
				niveauDTO.setId(resultSet.getInt("id_niveau_comp"));
				niveauDTOs.add(niveauDTO);
			}

		} catch (SQLException e) {
			ExceptionManager.getInstance().manageException(e);
		} finally {

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
		
		return niveauDTOs;
	}

	/**
	 * renvoie le DTO d'un niveau de competence (libelle et id)
	 */
	public NiveauDTO findById(Integer niveauid)
			throws TransactionalConnectionException {
		
		NiveauDTO niveauDTO = new NiveauDTO();
		
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			if (niveauDTO != null) {
				String selectInstruction = "select id_niveau_comp, libelle from niveau_comp Where id_niveau_comp=? ";
				PreparedStatement preparedStatement = getConnection().prepareStatement(selectInstruction);
				preparedStatement.setInt(1,niveauid);
				resultSet = preparedStatement.executeQuery();
			}

			while (resultSet.next()) {
				niveauDTO.setLibelle(resultSet.getString("libelle"));
				niveauDTO.setId(resultSet.getInt("id_niveau_comp"));
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
		return niveauDTO;		
	}

	/**
	 * créer un niveau de competence
	 * @param niveauToInsert
	 * @return un DTO
	 * @throws TransactionalConnectionException
	 */
	public NiveauDTO insertNiveau(NiveauDTO niveauToInsert)
			throws TransactionalConnectionException {
	
		Statement statement = null;		
		ResultSet resultSet = null;
		NiveauDTO niveauDTOinserted = null;
		
		try {

			String insertInstruction = "insert into niveau_comp (libelle) "
					+ "values (?) returning libelle ";
			PreparedStatement preparedStatement = getConnection().prepareStatement(insertInstruction);
			preparedStatement.setString(1, niveauToInsert.getLibelle());			

			preparedStatement.executeQuery();
			

		} catch (SQLException e) {
			ExceptionManager.getInstance().manageException(e);

		} finally {

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
		
		
		return niveauDTOinserted;
	}

	/**
	 * modifie le niveau de competence
	 * en parametre un DTO
	 */
	public NiveauDTO updateNiveau(NiveauDTO niveauToUpdate)
			throws TransactionalConnectionException {
		
		NiveauDTO niveauDTORetour = null;
		

		Statement statement = null;
		ResultSet resultSet = null;
		try {
			
		String insertInstruction = "update niveau_comp set libelle =? "  + "where id_niveau_comp=?";			
			
			PreparedStatement preparedStatement = getConnection().prepareStatement(insertInstruction);
			preparedStatement.setString(1, niveauToUpdate.getLibelle());
			
			preparedStatement.executeUpdate();

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
		return niveauDTORetour;
	
	}


	/**
	 * supprime un niveau de competence
	 * en parametre un DTO
	 */
	public void deleteNiveau(NiveauDTO niveauToDelete)
			throws TransactionalConnectionException {
		
		Statement statement = null;
		ResultSet resultSet = null;
		try {


			String insertInstruction1 = "DELETE FROM utilisateur_dispose_comp WHERE id_niveau_comp = ? ";
			PreparedStatement preparedStatement1 = getConnection().prepareStatement(insertInstruction1);
			preparedStatement1.setInt(1, niveauToDelete.getId());
			preparedStatement1.executeUpdate();
			
			String insertInstruction2 = "DELETE FROM niveau_comp  WHERE id_niveau_comp =? ";
			PreparedStatement preparedStatement2 = getConnection().prepareStatement(insertInstruction2);
			preparedStatement2.setInt(1, niveauToDelete.getId());
			preparedStatement2.executeUpdate();
			

		} catch (SQLException e) {
			ExceptionManager.getInstance().manageException(e);
		} finally {
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
		
	}
	
	
}
