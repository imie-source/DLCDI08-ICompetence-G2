package org.imie.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.imie.DAO.interfaces.IAdresseDAO;
import org.imie.DTO.AdresseDTO;
import org.imie.DTO.UserDTO;
import org.imie.exeptionManager.ExceptionManager;
import org.imie.transactionalFramework.ATransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

public class AdresseDAO extends ATransactional implements IAdresseDAO {
	
	public List<AdresseDTO> getAdresseByUser(UserDTO userDTO) throws TransactionalConnectionException {

		List<AdresseDTO> adresseDTOs = new ArrayList<AdresseDTO>();
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {

			String query = "SELECT ad.code_postal as code_postal, ad.ville as ville, ad.adresse as libelle, ad.id_adresse as id_adresse "
					+ "FROM utilisateur as util "
					+ "INNER JOIN adresse as ad ON util.id_adresse = ad.id_adresse "
					+ "Where util.id_utilisateur =? ";
		
			PreparedStatement preparedStatement = getConnection().prepareStatement(query);
			preparedStatement.setInt(1, userDTO.getId());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				AdresseDTO adresseDTO = new AdresseDTO();
				adresseDTO.setCode_postal(resultSet.getInt("code_postal"));
				adresseDTO.setVille(resultSet.getString("ville"));
				adresseDTO.setLibelle(resultSet.getString("libelle"));
				adresseDTO.setId_adresse(resultSet.getInt("id_adresse"));
				
				adresseDTOs.add(adresseDTO);
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
		return adresseDTOs;
	}
	

	@Override
	public AdresseDTO UpdateAdresse(AdresseDTO adresseToUpdate) throws TransactionalConnectionException {
		AdresseDTO adresseRetour = null;
		// déclaration de la variable de statement
		Statement statement = null;
		// déclaration de la variable de resultset
		ResultSet resultSet = null;
		
		
		try {
			// execution d'une requête SQL et récupération du result dans le
			// resultset
		String insertInstruction = "update adresse set adresse =?, ville =?, code_postal =?" +
				" where id_adresse =?";
			
			
			System.out.println(insertInstruction);
			PreparedStatement preparedStatement = getConnection().prepareStatement(insertInstruction);
			preparedStatement.setString(1, adresseToUpdate.getLibelle());
			preparedStatement.setString(2, adresseToUpdate.getVille());
			preparedStatement.setInt(3, adresseToUpdate.getCode_postal());
			preparedStatement.setInt(4, adresseToUpdate.getId_adresse());
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
		return adresseRetour;
	}
			
		
	
	
	@Override
	public AdresseDTO createAdresse(AdresseDTO adresseToCreate) throws TransactionalConnectionException {
		// déclaration de la variable de statement
		Statement statement = null;
		// déclaration de la variable de resultset
		ResultSet resultSet = null;
		AdresseDTO adresseDTOCreated = null;
		try {

			// execution d'une requête SQL et récupération du result dans le
			// resultset
			String insertInstruction = "insert into adresse (code_postal, ville, adresse) "
					+ "values (?,?,?) returning code_postal, ville, adresse ";
			PreparedStatement preparedStatement = getConnection().prepareStatement(insertInstruction);
			preparedStatement.setInt(1, adresseToCreate.getCode_postal());
			preparedStatement.setString(2, adresseToCreate.getVille());
			preparedStatement.setString(3, adresseToCreate.getLibelle());
		
			preparedStatement.executeQuery();
		

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
		return adresseDTOCreated;
		
	}


	


	
	
}
