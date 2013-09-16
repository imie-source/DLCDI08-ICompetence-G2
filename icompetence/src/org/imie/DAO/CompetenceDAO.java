package org.imie.DAO;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.imie.DAO.interfaces.ICompetenceDAO;
import org.imie.DTO.CompetenceDTO;
import org.imie.DTO.MotClefDTO;
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

	/**
	 * renvoie la liste des competences d'un utilisateur
	 * 
	 * @return liste de competences DTO
	 * @param un
	 *            utilisateur DTO
	 */
	public List<CompetenceDTO> getCompetenceByUser(UserDTO userDTO)
			throws TransactionalConnectionException {

		// initialisation de la liste qui servira au retour
		List<CompetenceDTO> competenceDTOs = new ArrayList<CompetenceDTO>();
		// déclaration de la variable de statement
		Statement statement = null;
		// déclaration de la variable de resultset
		ResultSet resultSet = null;

		try {
			// création de la variable contenant la query SQL
			String query = "SELECT comp.libelle_comp as libelle, nc.id_niveau_comp as niveau "
					+ "FROM competence as comp "
					+ "INNER JOIN utilisateur_dispose_comp as udc on comp.id_comp= udc.id_comp "
					+ "INNER JOIN utilisateur as util on udc.id_utilisateur = util.id_utilisateur "
					+ "INNER JOIN niveau_comp as nc on nc.id_niveau_comp = udc.id_niveau_comp "
					+ "Where util.id_utilisateur = ?";
			// création du PreparedStatement à partir de la query
			PreparedStatement preparedStatement = getConnection()
					.prepareStatement(query);
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

				competenceDTO.addNiveau(niveauDTO);

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

	/**
	 * renvoie la liste de toutes les compétences
	 * 
	 * @return la liste des competences DTO
	 */
	public List<CompetenceDTO> findAll() {

		List<CompetenceDTO> competenceDTOs = new ArrayList<CompetenceDTO>();

		Statement statement = null;
		ResultSet resultSet = null;

		try {

			String selectInstruction = "select id_comp, libelle_comp from competence order by id_comp";
			PreparedStatement preparedStatement = getConnection()
					.prepareStatement(selectInstruction);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				CompetenceDTO competenceDTO = new CompetenceDTO();
				competenceDTO.setLibelle(resultSet.getString("libelle_comp"));
				competenceDTO.setId(resultSet.getInt("id_comp"));
				competenceDTOs.add(competenceDTO);
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

		return competenceDTOs;

	}

	/**
	 * renvoie une competence
	 * 
	 * @param un
	 *            id de competence
	 * @return un DTO competence
	 */
	public CompetenceDTO findById(Integer competenceid)
			throws TransactionalConnectionException {

		CompetenceDTO competenceDTO = new CompetenceDTO();

		Statement statement = null;
		ResultSet resultSet = null;

		try {
			if (competenceDTO != null) {
				String selectInstruction = "select id_comp, libelle_comp from competence Where id_comp=? ";
				PreparedStatement preparedStatement = getConnection()
						.prepareStatement(selectInstruction);
				preparedStatement.setInt(1, competenceid);
				resultSet = preparedStatement.executeQuery();
			}

			while (resultSet.next()) {
				competenceDTO.setLibelle(resultSet.getString("libelle_comp"));
				competenceDTO.setId(resultSet.getInt("id_comp"));
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
		return competenceDTO;
	}

	public List<CompetenceDTO> findByNom(String competencenom)
			throws TransactionalConnectionException {

		List<CompetenceDTO> competenceDTOs = new ArrayList<CompetenceDTO>();

		Statement statement = null;
		ResultSet resultSet = null;

		try {

			String enteredByUser = competencenom;
			String forSql = "%" + enteredByUser + "%";

			String selectInstruction = "select * from competence Where upper(libelle_comp) like ?";

			PreparedStatement preparedStatement = getConnection()
					.prepareStatement(selectInstruction);
			preparedStatement.setString(1, forSql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				CompetenceDTO competenceDTO = new CompetenceDTO();
				competenceDTO.setLibelle(resultSet.getString("libelle_comp"));
				competenceDTO.setId(resultSet.getInt("id_comp"));
				competenceDTOs.add(competenceDTO);
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
		return competenceDTOs;
	}

	public CompetenceDTO insertCompetence(CompetenceDTO competenceToInsert)
			throws TransactionalConnectionException {

		Statement statement = null;
		ResultSet resultSet = null;
		CompetenceDTO competenceDTOinserted = null;

		try {

			String insertInstruction = "insert into competence (libelle_comp, id_comp_fait_partie) "
					+ "values (?,?) returning libelle_comp, id_comp_fait_partie ";
			PreparedStatement preparedStatement = getConnection()
					.prepareStatement(insertInstruction);
			preparedStatement.setString(1, competenceToInsert.getLibelle());
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

		return competenceDTOinserted;
	}

	/**
	 * modifie une competence
	 */
	public CompetenceDTO updateCompetence(CompetenceDTO competenceToUpdate)
			throws TransactionalConnectionException {

		CompetenceDTO competenceDTORetour = null;

		Statement statement = null;
		ResultSet resultSet = null;
		try {

			String insertInstruction = "update competence set libelle_comp =? "
					+ "where id_comp=?";

			PreparedStatement preparedStatement = getConnection()
					.prepareStatement(insertInstruction);
			preparedStatement.setString(1, competenceToUpdate.getLibelle());
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
		return competenceDTORetour;

	}

	/**
	 * supprimer une competence
	 */
	public void deleteCompetence(CompetenceDTO competenceToDelete)
			throws TransactionalConnectionException {

		Statement statement = null;
		ResultSet resultSet = null;
		try {

			String insertInstruction1 = "DELETE FROM utilisateur_dispose_comp WHERE id_comp = ? ";
			PreparedStatement preparedStatement1 = getConnection()
					.prepareStatement(insertInstruction1);
			preparedStatement1.setInt(1, competenceToDelete.getId());
			preparedStatement1.executeUpdate();

			String insertInstruction2 = "DELETE FROM gdt_utilise_comp WHERE id_comp = ? ";
			PreparedStatement preparedStatement2 = getConnection()
					.prepareStatement(insertInstruction2);
			preparedStatement2.setInt(2, competenceToDelete.getId());
			preparedStatement2.executeUpdate();

			String insertInstruction3 = "DELETE FROM comp_correspond_mot_clef WHERE id_comp = ? ";
			PreparedStatement preparedStatement3 = getConnection()
					.prepareStatement(insertInstruction3);
			preparedStatement3.setInt(3, competenceToDelete.getId());
			preparedStatement3.executeUpdate();

			String insertInstruction4 = "DELETE FROM competence WHERE id_comp =? ";
			PreparedStatement preparedStatement4 = getConnection()
					.prepareStatement(insertInstruction4);
			preparedStatement4.setInt(4, competenceToDelete.getId());
			preparedStatement4.executeUpdate();

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

	/**
	 * creer niveau d'une competence d'un utilisateur
	 */
	public Boolean creerCompUserNiveau(UserDTO userDTO,
			CompetenceDTO competenceDTO, NiveauDTO niveauDTO) {

		Boolean creerCUN = true;

		try {

			String creerCompUserNiveau = " INSERT INTO utilisateur_dispose_comp ( id_utilisateur, id_comp, id_niveau_comp ) VALUES (?,?,?)";
			PreparedStatement preparedStatement = getConnection()
					.prepareStatement(creerCompUserNiveau);
			preparedStatement.setInt(1, userDTO.getId());
			preparedStatement.setInt(2, competenceDTO.getId());
			preparedStatement.setInt(3, niveauDTO.getId());
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			ExceptionManager.getInstance().manageException(e);
			creerCUN = false;

		}

		return creerCUN;
	}

	/**
	 * modifier niveau de comp de l'utilisateur
	 */
	public void modifierCompUserNiveau(UserDTO userDTO,
			CompetenceDTO competenceDTO, NiveauDTO niveauDTO)
			throws TransactionalConnectionException {

		Statement statement = null;
		ResultSet resultSet = null;
		try {

			String modifCompUserNiveau = " UPDATE utilisateur_dispose_comp SET id_utilisateur=? , id_comp = ?, id_niveau_comp = ?";
			PreparedStatement preparedStatement1 = getConnection()
					.prepareStatement(modifCompUserNiveau);
			preparedStatement1.setInt(1, userDTO.getId());
			preparedStatement1.setInt(2, competenceDTO.getId());
			preparedStatement1.setInt(3, niveauDTO.getId());
			preparedStatement1.executeUpdate();

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

	/**
	 * supprimer niveau de comp d'un utilisateur
	 */
	public void supprimerCompUserNiveau(UserDTO userDTO,
			CompetenceDTO competenceDTO, NiveauDTO niveauDTO)
			throws TransactionalConnectionException {

		Statement statement = null;
		ResultSet resultSet = null;
		try {

			String suppCompUserNiveau = "DELETE FROM utilisateur_dispose_comp WHERE id_utilisateur = ? AND id_comp = ? AND id_niveau_comp = ? ";
			PreparedStatement preparedStatement1 = getConnection()
					.prepareStatement(suppCompUserNiveau);
			preparedStatement1.setInt(1, userDTO.getId());
			preparedStatement1.setInt(2, competenceDTO.getId());
			preparedStatement1.setInt(3, niveauDTO.getId());
			preparedStatement1.executeUpdate();

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

	/**
	 * creer niveau d'une competence d'un utilisateur
	 */
	public Boolean creerCompMotClef(CompetenceDTO competenceDTO,
			MotClefDTO mot_clefDTO) throws TransactionalConnectionException {

		Boolean creerCMC = true;

		try {

			String creerCompMotClef = " INSERT INTO comp_correspond_mot_clef ( id_comp, id_mot_clef ) VALUES (?,?,?)";
			PreparedStatement preparedStatement = getConnection()
					.prepareStatement(creerCompMotClef);
			preparedStatement.setInt(1, competenceDTO.getId());
			preparedStatement.setInt(2, mot_clefDTO.getId());
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			ExceptionManager.getInstance().manageException(e);
			creerCMC = false;

		}

		return creerCMC;
	}

	/**
	 * modifier niveau de comp de l'utilisateur
	 */
	public void modifierCompMotClef(CompetenceDTO competenceDTO,
			MotClefDTO mot_clefDTO) throws TransactionalConnectionException {

		Statement statement = null;
		ResultSet resultSet = null;
		try {

			String modifierCompMotClef = " UPDATE comp_correspond_mot_clef SET id_comp = ?, id_mot_clef = ?";
			PreparedStatement preparedStatement1 = getConnection()
					.prepareStatement(modifierCompMotClef);
			preparedStatement1.setInt(1, competenceDTO.getId());
			preparedStatement1.setInt(2, mot_clefDTO.getId());
			preparedStatement1.executeUpdate();

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

	/**
	 * supprimer niveau de comp d'un utilisateur
	 */
	public void supprimerCompMotClef(CompetenceDTO competenceDTO,
			MotClefDTO mot_clefDTO) throws TransactionalConnectionException {

		Statement statement = null;
		ResultSet resultSet = null;
		try {

			String supprimerCompMotClef = "DELETE FROM comp_correspond_mot_clef WHERE  id_comp = ? AND id_mot_clef = ? ";
			PreparedStatement preparedStatement1 = getConnection()
					.prepareStatement(supprimerCompMotClef);
			preparedStatement1.setInt(1, competenceDTO.getId());
			preparedStatement1.setInt(2, mot_clefDTO.getId());
			preparedStatement1.executeUpdate();

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

	public List<CompetenceDTO> findAllArbo()
			throws TransactionalConnectionException {

		List<CompetenceDTO> competenceDTOs = new ArrayList<CompetenceDTO>();

		Statement statement = null;
		ResultSet resultSet = null;

		try {

			String selectInstruction = "select id_competence, libelle, chemin, niveau FROM vue_arbo";
			PreparedStatement preparedStatement = getConnection()
					.prepareStatement(selectInstruction);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				CompetenceDTO competenceDTO = new CompetenceDTO();
				competenceDTO.setLibelle(resultSet.getString("libelle"));
				competenceDTO.setNiveauParent(resultSet.getInt("niveau"));
				competenceDTO.setId(resultSet.getInt("id_competence"));
				competenceDTO.setChemin(resultSet.getArray("chemin"));
				competenceDTOs.add(competenceDTO);
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

		return competenceDTOs;
	}

	public List<CompetenceDTO> findArboFilsPere(Integer id)
			throws TransactionalConnectionException {

		List<CompetenceDTO> competenceDTOPeres = new ArrayList<CompetenceDTO>();
		List<CompetenceDTO> competenceDTOEnfants = new ArrayList<CompetenceDTO>();
		List<CompetenceDTO> competenceDTOs = new ArrayList<CompetenceDTO>();

		Statement statement = null;
		ResultSet resultSet = null;
		ResultSet resultSetParent = null;

		try {
			// recherche des fils
			String selectInstruction = "SELECT * FROM vue_arbo WHERE " + id
					+ "= ANY (chemin);";
			PreparedStatement preparedStatement = getConnection()
					.prepareStatement(selectInstruction);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				CompetenceDTO competenceDTO = new CompetenceDTO();
				competenceDTO.setLibelle(resultSet.getString("libelle"));
				competenceDTO.setNiveauParent(resultSet.getInt("niveau"));
				competenceDTO.setChemin(resultSet.getArray("chemin"));
				competenceDTO.setId(resultSet.getInt("id_competence"));
				competenceDTOEnfants.add(competenceDTO);

			}

			CompetenceDTO compDTO = competenceDTOEnfants.get(0);
			Array tabChemin = compDTO.getChemin();
			Object tableau = tabChemin.getArray();
			// tableau est tableau Java
			Integer[] donneesTableau = (Integer[]) tableau;
			// vous devez le caster dans le type de données contenues
			String chemin = "[";
			int size = donneesTableau.length - 1;

			for (int i = 0; i < size; i++) {
				if (i == size - 1) {
					chemin = chemin + donneesTableau[i];
				} else {
					chemin = chemin + donneesTableau[i] + ",";
				}
			}
			if (size == 0) {
				chemin = chemin + donneesTableau[0];
			}
			chemin += "]";

			String selectInstructionParent = "SELECT * FROM vue_arbo WHERE  id_competence= ANY (ARRAY"
					+ chemin + ");";
			PreparedStatement preparedStatementParent = getConnection()
					.prepareStatement(selectInstructionParent);
			resultSetParent = preparedStatementParent.executeQuery();

			while (resultSetParent.next()) {
				CompetenceDTO competenceDTO = new CompetenceDTO();
				competenceDTO.setLibelle(resultSetParent.getString("libelle"));
				competenceDTO.setNiveauParent(resultSetParent.getInt("niveau"));
				competenceDTO.setChemin(resultSetParent.getArray("chemin"));
				competenceDTO.setId(resultSetParent.getInt("id_competence"));
				competenceDTOPeres.add(competenceDTO);
			}

			competenceDTOs.addAll(competenceDTOPeres);
			competenceDTOs.addAll(competenceDTOEnfants);

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

		return competenceDTOs;
	}

}
