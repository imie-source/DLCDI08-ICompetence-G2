package org.imie.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.imie.DAO.interfaces.IMotClefDAO;
import org.imie.DTO.MotClefDTO;
import org.imie.exeptionManager.ExceptionManager;
import org.imie.transactionalFramework.ATransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

public class MotClefDAO extends ATransactional implements IMotClefDAO {

	/**
	 * renvoie toute la liste des mots clef (liste de DTO)
	 * 
	 */
	
	public List<MotClefDTO> findAll() throws TransactionalConnectionException {
		List<MotClefDTO> motClefDTOs = new ArrayList<MotClefDTO>();

		Statement statement = null;
		ResultSet resultSet = null;
		try {
			String selectInstruction = "select id_mot_clef, libelle from mot_clef order by id_mot_clef";
			PreparedStatement preparedStatement = getConnection()
					.prepareStatement(selectInstruction);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				MotClefDTO motClefDTO = new MotClefDTO();
				motClefDTO.setLibelle(resultSet.getString("libelle"));
				motClefDTO.setId(resultSet.getInt("id_mot_clef"));
				motClefDTOs.add(motClefDTO);
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
		return motClefDTOs;
	}

	/**
	 * renvoie le DTO d'un mot clef (libelle et id)
	 */
	public MotClefDTO findById(Integer motClefid)
			throws TransactionalConnectionException {
		MotClefDTO motClefDTO = new MotClefDTO();

		Statement statement = null;
		ResultSet resultSet = null;

		try {
			if (motClefDTO != null) {
				String selectInstruction = "select id_mot_clef, libelle from mot_clef Where id_mot_clef=? ";
				PreparedStatement preparedStatement = getConnection()
						.prepareStatement(selectInstruction);
				preparedStatement.setInt(1, motClefid);
				resultSet = preparedStatement.executeQuery();
			}

			while (resultSet.next()) {
				motClefDTO.setLibelle(resultSet.getString("libelle"));
				motClefDTO.setId(resultSet.getInt("id_mot_clef"));
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
		return motClefDTO;
	}

	/**
	 * créer un mot clef
	 * 
	 * @param motClefToInsert
	 * @return un DTO
	 * @throws TransactionalConnectionException
	 */
	public MotClefDTO insertmotClef(MotClefDTO motClefToInsert)
			throws TransactionalConnectionException {
		Statement statement = null;
		ResultSet resultSet = null;
		MotClefDTO motClefDTOinserted = null;

		try {

			String insertInstruction = "insert into mot_clef (libelle) "
					+ "values (?) returning libelle ";
			PreparedStatement preparedStatement = getConnection()
					.prepareStatement(insertInstruction);
			preparedStatement.setString(1, motClefToInsert.getLibelle());

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

		return motClefDTOinserted;
	}

	/**
	 * modifie le mot_clef en parametre un DTO
	 */
	public MotClefDTO updateMotClef(MotClefDTO motClefToUpdate)
			throws TransactionalConnectionException {
		MotClefDTO motClefDTORetour = null;

		Statement statement = null;
		ResultSet resultSet = null;
		try {

			String insertInstruction = "update mot_clef set libelle =? "
					+ "where id_mot_clef=?";

			PreparedStatement preparedStatement = getConnection()
					.prepareStatement(insertInstruction);
			preparedStatement.setString(1, motClefToUpdate.getLibelle());

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
		return motClefDTORetour;

	}

	/**
	 * supprime un mot_clef en parametre un DTO
	 */
	public void deletemotClef(MotClefDTO motClefToDelete)
			throws TransactionalConnectionException {
		Statement statement = null;
		ResultSet resultSet = null;
		try {

			String insertInstruction1 = "DELETE FROM comp_correspond_mot_clef WHERE id_mot_clef = ? ";
			PreparedStatement preparedStatement1 = getConnection()
					.prepareStatement(insertInstruction1);
			preparedStatement1.setInt(1, motClefToDelete.getId());
			preparedStatement1.executeUpdate();

			String insertInstruction2 = "DELETE FROM mot_clef  WHERE id_mot_clef =? ";
			PreparedStatement preparedStatement2 = getConnection()
					.prepareStatement(insertInstruction2);
			preparedStatement2.setInt(1, motClefToDelete.getId());
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

	@Override
	public MotClefDTO updatemotClef(MotClefDTO motClefToUpdate)
			throws TransactionalConnectionException {
		// TODO Auto-generated method stub
		return null;
	}

}
