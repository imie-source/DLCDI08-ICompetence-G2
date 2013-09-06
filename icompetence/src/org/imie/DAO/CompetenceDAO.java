package org.imie.DAO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.imie.DAO.interfaces.ICompetenceDAO;
import org.imie.DTO.CompetenceDTO;
import org.imie.DTO.NiveauDTO;
import org.imie.DTO.UserDTO;
import org.imie.exeptionManager.ExceptionManager;
import org.imie.transactionalFramework.ATransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

/**
 * DAO de la table compétences
 * 
 * @author imie
 * 
 */
public class CompetenceDAO extends ATransactional implements ICompetenceDAO {


	public List<CompetenceDTO> getCompetenceByUser(UserDTO userDTO) throws TransactionalConnectionException {
		
		
		// initialisation de la liste qui servira au retour
		List<CompetenceDTO> competenceDTOs = new ArrayList<CompetenceDTO>();
		// déclaration de la variable de statement
		Statement statement = null;
		// déclaration de la variable de resultset
		ResultSet resultSet = null;

		try {
			// création de la variable contenant la query SQL
			String query = "SELECT comp.libelle_comp as libelle,nc.id_niveau_comp as niveau "
					+ "FROM competence as comp "
					+ "INNER JOIN utilisateur_dispose_comp as udc on comp.id_comp= udc.id_comp "
					+ "INNER JOIN utilisateur as util on udc.id_utilisateur = util.id_utilisateur "
					+ "INNER JOIN niveau_comp as nc on nc.id_niveau_comp = udc.id_niveau_comp "
					+ "Where util.id_utilisateur = ?";
			// création du PreparedStatement à partir de la query
			PreparedStatement preparedStatement = getConnection().prepareStatement(query);
			// affectation de la valeur du premier ? de la query
			preparedStatement.setInt(1, userDTO.getId());
			// execution d'une requête SQL et récupération du result dans le
			// resultset
			resultSet = preparedStatement.executeQuery();
			// parcours du resultset
			while (resultSet.next()) {
				// création d'un nouveau CompetenceDTO
				CompetenceDTO competenceDTO = new CompetenceDTO();
				// // affectation des attribut du CompetenceDTO à partir des
				// valeurs du
				// resultset sur l'enregistrement courant
				competenceDTO.setLibelle(resultSet.getString("libelle"));
				
				NiveauDTO niveauDTO = new NiveauDTO();
				
				niveauDTO.setLibelle(resultSet.getString("libelle"));				
				niveauDTO.setId(resultSet.getInt("niveau"));				
				
				competenceDTO.setNiveau(niveauDTO);
				
				
				competenceDTOs.add(competenceDTO);
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
		return competenceDTOs;
	}

	
	public List<CompetenceDTO> findAll() {
	
	
	
	return null;
	}

	@Override
	public CompetenceDTO findById(Integer competenceid)
			throws TransactionalConnectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompetenceDTO insertCompetence(CompetenceDTO competenceToInsert)
			throws TransactionalConnectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompetenceDTO updateCompetence(CompetenceDTO competenceToUpdate)
			throws TransactionalConnectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCompetence(CompetenceDTO competenceToDelete)
			throws TransactionalConnectionException {
		// TODO Auto-generated method stub
		
	}
}
