package org.imie.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.TransactionRequiredException;

import org.imie.DAO.interfaces.IAdresseDAO;
import org.imie.DAO.interfaces.ICompetenceDAO;
import org.imie.DAO.interfaces.ICursusDAO;
import org.imie.DAO.interfaces.IUserDAO;
import org.imie.DTO.AdresseDTO;
import org.imie.DTO.CompetenceDTO;
import org.imie.DTO.CursusDTO;
import org.imie.DTO.UserDTO;
import org.imie.exeptionManager.ExceptionManager;
import org.imie.factory.BaseConcreteFactory;
import org.imie.transactionalFramework.ATransactional;

import org.imie.transactionalFramework.TransactionalConnectionException;

/**
 * DAO de gestion de la table Utilisateur
 * 
 * @author imie
 * 
 */
public class UserDAO extends ATransactional implements IUserDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.DAO.IuserDAO#getUsers()
	 */
	@Override
	public List<UserDTO> getUsers() throws TransactionalConnectionException {

		// initialisation de la liste qui servira au retour
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();

		// déclaration de la variable de statement
		Statement statement = null;
		// déclaration de la variable de resultset
		ResultSet resultSet = null;
		try {
			// création du statement à partir de la connection
			statement = getConnection().createStatement();
			// execution d'une requête SQL et récupération du result dans le
			// resultset
			resultSet = statement.executeQuery("select * " + "FROM utilisateur ");
			// parcours du resultset
			while (resultSet.next()) {
				UserDTO userDTO = buildDTO(resultSet);
				// ajout du DTO dans la liste de retour
				userDTOs.add(userDTO);
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
		return userDTOs;
	}

	/**
	 * construction d'un DTO à partir d'un resultset
	 * 
	 * @param cn
	 *            la connection avec laquel le resulset
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws TransactionalConnectionException
	 * @throws TransactionRequiredException
	 */
	private UserDTO buildDTO(ResultSet resultSet) throws SQLException, TransactionalConnectionException {
		// initialisation du DAO de gestion des compétences
		ICompetenceDAO competenceDAO = BaseConcreteFactory.getInstance().createCompetenceDAO(this);
		IAdresseDAO adresseDAO = BaseConcreteFactory.getInstance().createAdresseDAO(this);
		ICursusDAO cursusDAO = BaseConcreteFactory.getInstance().createCursusDAO(this);
		// création d'un nouveau UserDTO
		UserDTO userDTO = new UserDTO();
		// affectation des attribut du UserDTO à partir des valeurs du
		// resultset sur l'enregistrement courant
		userDTO.setNom(resultSet.getString("nom"));
		userDTO.setPrenom(resultSet.getString("prenom"));
		userDTO.setId(resultSet.getInt("id_utilisateur"));
		userDTO.setDateNaiss(resultSet.getDate("date_naissance"));
		userDTO.setAdresse_mail(resultSet.getString("adresse_mail"));
		userDTO.setIdentifiant(resultSet.getString("identifiant"));
		userDTO.setPwd(resultSet.getString("pwd"));
		userDTO.setDisponible(resultSet.getBoolean("disponible"));
	
		
		

		// récupération des compétences du user grâce au competenceDAO
		// la connection passée en paramètre permet de partager la
		// connection entre cette methode et celle appelée

		List<CompetenceDTO> competenceDTOs = competenceDAO.getCompetenceByUser(userDTO);
		// parcours des compétences du user
		for (CompetenceDTO competenceDTO : competenceDTOs) {
			// ajout des compétences sur le UserDTO à partir de celles
			// fournies par le CompetenceDAO
			userDTO.addCompetence(competenceDTO);
		}

		List<AdresseDTO> adresseDTOs = adresseDAO.getAdresseByUser(userDTO);
		for (AdresseDTO adresseDTO : adresseDTOs) {
			userDTO.addAdresse(adresseDTO);
		}

		CursusDTO cursusDTO = cursusDAO.findByUser(userDTO);

		userDTO.setCursus(cursusDTO);
		return userDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.DAO.IuserDAO#insertUser(org.imie.DTO.UserDTO)
	 */
	@Override
	public UserDTO insertUser(UserDTO userToInsert) throws TransactionalConnectionException {

		// déclaration de la variable de statement
		Statement statement = null;
		// déclaration de la variable de resultset
		ResultSet resultSet = null;
		UserDTO userDTOinserted = null;
		
		try {

			// execution d'une requête SQL et récupération du result dans le
			// resultset
			String insertInstruction = "insert into utilisateur (nom, prenom, adresse_mail, date_naissance, disponible, identifiant, pwd, id_formation,id_adresse) "
					+ "values (?,?,?,?,?,?,?,?) returning nom, prenom, adresse_mail, date_naissance, disponible, identifiant, pwd, id_formation, id_adresse ";
			PreparedStatement preparedStatement = getConnection().prepareStatement(insertInstruction);
			preparedStatement.setString(1, userToInsert.getNom());
			preparedStatement.setString(2, userToInsert.getPrenom());
			preparedStatement.setString(3, userToInsert.getAdresse_mail());
			preparedStatement.setDate(4, new java.sql.Date(userToInsert.getDateNaiss().getTime()));
			preparedStatement.setBoolean(5, userToInsert.isDisponible());
			preparedStatement.setString(6, userToInsert.getIdentifiant());
			preparedStatement.setString(7, userToInsert.getPwd());
			preparedStatement.setInt(8, userToInsert.getCursus().getId());
			preparedStatement.setInt(9, userToInsert.getAdresse().getId_adresse());
			

			preparedStatement.executeQuery();
			/*
			 * if (resultSet.next()) { userDTOinserted = buildDTO(resultSet); }
			 */

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
		return userDTOinserted;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.DAO.IuserDAO#updateUser(org.imie.DTO.UserDTO)
	 */
	
	
	
	@Override
	public UserDTO updateUser(UserDTO userToUpdate) throws TransactionalConnectionException {
		UserDTO userDTORetour = null;
		

		// déclaration de la variable de statement
		Statement statement = null;
		// déclaration de la variable de resultset
		ResultSet resultSet = null;
		try {
			// execution d'une requête SQL et récupération du result dans le
			// resultset
		String insertInstruction = "update utilisateur set nom =?, prenom =?, date_naissance=?, id_formation =?, adresse_mail =?, "
				+ "disponible=?,  pwd=?, identifiant=? " + "where id_utilisateur=?";
			
			
			System.out.println(insertInstruction);
			PreparedStatement preparedStatement = getConnection().prepareStatement(insertInstruction);
			preparedStatement.setString(1, userToUpdate.getNom());
			preparedStatement.setString(2, userToUpdate.getPrenom());
			
			if (userToUpdate.getDateNaiss() != null) {
				preparedStatement.setDate(3, new java.sql.Date(userToUpdate.getDateNaiss().getTime()));
			} else {
				preparedStatement.setNull(3, Types.DATE);
			}

			if (userToUpdate.getCursus() != null) {
				preparedStatement.setInt(4, userToUpdate.getCursus().getId());
			} else {
				preparedStatement.setNull(4, Types.INTEGER);
			}
			preparedStatement.setString(5, userToUpdate.getAdresse_mail());
			preparedStatement.setBoolean(6, userToUpdate.isDisponible());
			preparedStatement.setString(7, userToUpdate.getPwd());
			preparedStatement.setString(8, userToUpdate.getIdentifiant());
			preparedStatement.setInt(9, userToUpdate.getId());
			
			

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
		return userDTORetour;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.DAO.IuserDAO#deleteUser(org.imie.DTO.UserDTO)
	 */
	@Override
	public void deleteUser(UserDTO userToDelete) throws TransactionalConnectionException {

		// déclaration de la variable de statement
		Statement statement = null;
		// déclaration de la variable de resultset
		ResultSet resultSet = null;
		try {

			// execution d'une requête SQL et récupération du result dans le
			// resultset

			String insertInstruction1 = "DELETE FROM utilisateur_fait_partie_profil WHERE id_utilisateur =? ";
			PreparedStatement preparedStatement1 = getConnection().prepareStatement(insertInstruction1);
			preparedStatement1.setInt(1, userToDelete.getId());
			preparedStatement1.executeUpdate();

			String insertInstruction2 = "DELETE FROM utilisateur_appartient_a_gdt WHERE id_utilisateur =? ";
			PreparedStatement preparedStatement2 = getConnection().prepareStatement(insertInstruction2);
			preparedStatement2.setInt(1, userToDelete.getId());
			preparedStatement2.executeUpdate();

			String insertInstruction3 = "DELETE FROM invitation WHERE id_utilisateur =? ";
			PreparedStatement preparedStatement3 = getConnection().prepareStatement(insertInstruction3);
			preparedStatement3.setInt(1, userToDelete.getId());
			preparedStatement3.executeUpdate();

			String insertInstruction4 = "DELETE FROM utilisateur_dispose_comp WHERE id_utilisateur = ? ";
			PreparedStatement preparedStatement4 = getConnection().prepareStatement(insertInstruction4);
			preparedStatement4.setInt(1, userToDelete.getId());
			preparedStatement4.executeUpdate();

			String insertInstruction5 = "DELETE FROM utilisateur WHERE id_utilisateur=? ";
			PreparedStatement preparedStatement5 = getConnection().prepareStatement(insertInstruction5);
			preparedStatement5.setInt(1, userToDelete.getId());
			preparedStatement5.executeUpdate();

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
	}
}
