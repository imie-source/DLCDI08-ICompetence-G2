package org.imie.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.imie.DAO.interfaces.IGroupeDeTravailDAO;

import org.imie.DTO.CompetenceDTO;
import org.imie.DTO.GroupeDeTravailDTO;
import org.imie.DTO.UserDTO;
import org.imie.exeptionManager.ExceptionManager;

import org.imie.transactionalFramework.ATransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

public class GroupeDeTravailDAO extends ATransactional implements
		IGroupeDeTravailDAO {

	public Boolean creerGroupeDeTravail(
			GroupeDeTravailDTO groupeDeTravailAInserer)
			throws TransactionalConnectionException {

		// déclaration de la variable de statement
		Statement statement = null;
		// déclaration de la variable de resultset
		System.out.println("entrée -> creerGroupeDeTravail");
		ResultSet resultSet = null;
		boolean cree = true;

		try {

			// execution d'une requête SQL et récupération du result dans le
			// resultset
			
			System.out.println(groupeDeTravailAInserer.getNom());
			System.out.println(groupeDeTravailAInserer.getType_projet());
			
			String insertInstruction = "insert into groupe_de_travail (nom, type_projet, id_etat) "
					+ "values (?,?,1) ";
			PreparedStatement preparedStatement = getConnection()
					.prepareStatement(insertInstruction);
			preparedStatement.setString(1, groupeDeTravailAInserer.getNom());
			preparedStatement.setString(2,
					groupeDeTravailAInserer.getType_projet());
			

			preparedStatement.executeQuery();
			

		} catch (SQLException e) {
			ExceptionManager.getInstance().manageException(e);
			cree = false;

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
		return cree;
	}

	@Override
	public Boolean modifierGroupeDeTravail(
			GroupeDeTravailDTO groupeDeTravailAModifier)
			throws TransactionalConnectionException {

		Boolean modif = true;
		Statement statement = null;
		ResultSet resultSet = null;
		System.out.println("entrée -> modifierGroupeDeTravail");

		try {
			String modification = " UPDATE groupe_de_travail set nom=?, bilan=?, type_projet=?, id_etat=? WHERE id_gdt=?";

			PreparedStatement preparedStatement = getConnection()
					.prepareStatement(modification);
			preparedStatement.setString(1, groupeDeTravailAModifier.getNom());
			preparedStatement.setString(2, groupeDeTravailAModifier.getBilan());
			preparedStatement.setString(3,
					groupeDeTravailAModifier.getType_projet());

			Integer id_etat = null;

			if (groupeDeTravailAModifier.getLibelleEtat().equals(
					"Manque volontaire")) {
				id_etat = 1;
			}
			if (groupeDeTravailAModifier.getLibelleEtat().equals("Démarrage")) {
				id_etat = 2;
			}
			if (groupeDeTravailAModifier.getLibelleEtat().equals("En cours")) {
				id_etat = 3;
			}
			if (groupeDeTravailAModifier.getLibelleEtat().equals("Terminé")) {
				id_etat = 4;
			}
			preparedStatement.setInt(4, id_etat);

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
				modif = false;
			}
		}
		return modif;
	}

	public void supprimerGroupeDeTravail(
			GroupeDeTravailDTO groupeDeTravailASupprimer)
			throws TransactionalConnectionException {

		
		// déclaration de la variable de statement
		Statement statement = null;
		// déclaration de la variable de resultset
		ResultSet resultSet = null;
		try {

			// execution d'une requête SQL et récupération du result dans le
			// resultset

			String insertInstruction1 = "DELETE FROM gdt_utilise_comp WHERE id_gdt =? ";
			PreparedStatement preparedStatement1 = getConnection()
					.prepareStatement(insertInstruction1);
			preparedStatement1.setInt(1, groupeDeTravailASupprimer.getId_gdt());
			preparedStatement1.executeUpdate();
			System.out.println("delete gdt utilise comp");

			String insertInstruction2 = "DELETE FROM utilisateur_appartient_a_gdt WHERE id_gdt =? ";
			PreparedStatement preparedStatement2 = getConnection()
					.prepareStatement(insertInstruction2);
			preparedStatement2.setInt(1, groupeDeTravailASupprimer.getId_gdt());
			preparedStatement2.executeUpdate();
			System.out.println("delete util appartient a gdt");
			
			String insertInstruction3 = "DELETE FROM invitation WHERE id_gdt =? ";
			PreparedStatement preparedStatement3 = getConnection()
					.prepareStatement(insertInstruction3);
			preparedStatement3.setInt(1, groupeDeTravailASupprimer.getId_gdt());
			preparedStatement3.executeUpdate();
			System.out.println("delete from invitation");
			
			String insertInstruction4 = "DELETE FROM groupe_de_travail WHERE id_gdt =? ";
			PreparedStatement preparedStatement4 = getConnection()
					.prepareStatement(insertInstruction4);
			preparedStatement4.setInt(1, groupeDeTravailASupprimer.getId_gdt());
			preparedStatement4.executeUpdate();
			System.out.println("delete from gdt");
			
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

	public Boolean supprimerUserGroupeDeTravail(UserDTO userDTO,
			GroupeDeTravailDTO gdtDTO) throws TransactionalConnectionException {

		Statement statement = null;
		ResultSet resulset = null;
		Boolean supp = true;

		try {
			String modifierUserGdT = "DELETE FROM util_appartient_a_gdt WHERE id_utilisateur = ? AND id_gdt = ?";
			PreparedStatement preparedStatement = getConnection()
					.prepareStatement(modifierUserGdT);
			preparedStatement.setInt(1, userDTO.getId());
			preparedStatement.setInt(2, gdtDTO.getId_gdt());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			ExceptionManager.getInstance().manageException(e);
			supp = false;
		}
		return supp;
	}

	public Boolean creerUserGdt(UserDTO userDTO, GroupeDeTravailDTO gdtDTO) {

		Boolean creer = true;

		try {

			String creerUserGdt = " INSERT INTO util_appartient_a_gdt ( id_utilisateur, id_gdt) VALUES (?,?)";
			PreparedStatement preparedStatement = getConnection()
					.prepareStatement(creerUserGdt);
			preparedStatement.setInt(1, userDTO.getId());
			preparedStatement.setInt(2, gdtDTO.getId_gdt());
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			ExceptionManager.getInstance().manageException(e);
			creer = false;

		}

		return creer;
	}

	public Boolean modifCP(UserDTO userDTO, GroupeDeTravailDTO gdtDTO) {

		Boolean creer = true;
		try {

			String creerUserGdt = " UPDATE groupe_de_travail SET id_utilisateur=? WHERE id_gdt=?";
			PreparedStatement preparedStatement = getConnection()
					.prepareStatement(creerUserGdt);
			preparedStatement.setInt(1, userDTO.getId());
			preparedStatement.setInt(2, gdtDTO.getId_gdt());
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			ExceptionManager.getInstance().manageException(e);
			creer = false;

		}

		return creer;
	}

	@Override
	public List<GroupeDeTravailDTO> afficherGroupeDeTravail()
			throws TransactionalConnectionException {
		List<GroupeDeTravailDTO> groupeDeTravailDTO = new ArrayList<GroupeDeTravailDTO>();
		Statement statement = null;
		ResultSet resultSet = null;
		System.out.println("entrée -> afficherGroupeDeTravail");
		try {
			statement = getConnection().createStatement();
			resultSet = statement
					.executeQuery("select  id_gdt,nom, bilan,type_projet,id_util_chef_de_groupe,id_etat FROM groupe_de_travail");

			while (resultSet.next()) {
				
				GroupeDeTravailDTO groupeDeTravail = buildDTO(resultSet);
				groupeDeTravailDTO.add(groupeDeTravail);
				CPparGdt(groupeDeTravail);
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

		return groupeDeTravailDTO;
		// TODO Auto-generated method stub

	}

	private GroupeDeTravailDTO buildDTO(ResultSet resultSet)
			throws SQLException, TransactionalConnectionException {
		// création d'un nouveau UserDTO
		GroupeDeTravailDTO groupeDeTravailDTO = new GroupeDeTravailDTO();
		// affectation des attribut du UserDTO à partir des valeurs du
		// resultset sur l'enregistrement courant
		System.out.println("entrée -> buildDTO");
		groupeDeTravailDTO.setNom(resultSet.getString("nom"));
		groupeDeTravailDTO.setId_gdt(resultSet.getInt("id_gdt"));
		groupeDeTravailDTO.setBilan(resultSet.getString("bilan"));
		groupeDeTravailDTO.setType_projet(resultSet.getString("type_projet"));
		groupeDeTravailDTO.setId_util(resultSet.getInt("id_util_chef_de_groupe"));
		System.out.println(groupeDeTravailDTO.getId_util());
		groupeDeTravailDTO.setId_etat(resultSet.getInt("id_etat"));
		
		groupeDeTravailDTO.setLibelleEtat(afficherLibelle(groupeDeTravailDTO
				.getId_etat()));
		return groupeDeTravailDTO;

	}
	
	private void CPparGdt(GroupeDeTravailDTO gdtDTO){
		
		
		Statement statement = null;
		ResultSet resultSet = null;
		System.out.println("entrée -> CPparGdt");
		String nomPrenomCP = null;
		Integer id_util = gdtDTO.getId_util();
		
		
		if (id_util != 0){
		try {
			statement = getConnection().createStatement();
			String query = "select utilisateur.nom, prenom "
					+ "FROM utilisateur LEFT JOIN groupe_de_travail as gdt ON gdt.id_util_chef_de_groupe = utilisateur.id_utilisateur" +
					" WHERE utilisateur.id_utilisateur="+id_util;
			System.out.println(query);
			resultSet = statement.executeQuery(query);

			 while(resultSet.next()) {
				System.out.println(resultSet.getString("nom"));
				nomPrenomCP = resultSet.getString("nom")+ " " + resultSet.getString("prenom");
				gdtDTO.setNomCP(nomPrenomCP);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} else {
			gdtDTO.setNomCP("non défini");
			
		}

		
		
	}

	private String afficherLibelle(Integer id_etat) {

		Statement statement = null;
		ResultSet resultSet = null;
		System.out.println("entrée -> afficherLibelle");
		String libelleEtat = null;

		try {
			statement = getConnection().createStatement();
			resultSet = statement.executeQuery("select libelle "
					+ "FROM etat WHERE id_etat =" + id_etat);

			while (resultSet.next()) {
				libelleEtat = resultSet.getString("libelle");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return libelleEtat;

	}

	@Override
	public Boolean modifCP(UserDTO userDTO) {
		Boolean creer = true;
		try {

			String creerUserGdt = " UPDATE groupe_de_travail SET id_utilisateur=? WHERE id_gdt=?";
			PreparedStatement preparedStatement = getConnection()
					.prepareStatement(creerUserGdt);
			preparedStatement.setString(1, null);
			preparedStatement.setInt(2, userDTO.getId());
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			ExceptionManager.getInstance().manageException(e);
			creer = false;

		}

		return creer;

	}

	public List<GroupeDeTravailDTO> getGroupeDeTravailParUtilisateur(
			UserDTO userDTO) throws TransactionalConnectionException {

		List<GroupeDeTravailDTO> groupeDeTravailDTOs = new ArrayList<GroupeDeTravailDTO>();
		Statement statement = null;
		ResultSet resultSet = null;

		try {

			String query = " SELECT nom, bilan, type_projet, id_gdt"
					+ "FROM groupe_de_travail"
					+ "INNER JOIN utilisateur_appartient_a_gdt as uaag"
					+ "WHERE id_utilisateur =?";
			PreparedStatement preparedStatement = getConnection()
					.prepareStatement(query);
			preparedStatement.setInt(1, userDTO.getId());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				GroupeDeTravailDTO gdtDTO = new GroupeDeTravailDTO();
				gdtDTO.setNom(resultSet.getString("nom"));
				gdtDTO.setBilan(resultSet.getString("bilan"));
				gdtDTO.setType_projet(resultSet.getString("type_projet"));
				gdtDTO.setId_gdt(resultSet.getInt("id_gdt"));
				groupeDeTravailDTOs.add(gdtDTO);

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
		return groupeDeTravailDTOs;

	}

	// TODO méthode de recherche par user ou autre à implémenter (voir youyou)
}
