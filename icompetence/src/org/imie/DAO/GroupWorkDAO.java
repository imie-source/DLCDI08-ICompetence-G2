package org.imie.DAO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.imie.DAO.interfaces.IGroupWorkDAO;
import org.imie.DTO.UserDTO;
import org.imie.exeptionManager.ExceptionManager;
import org.imie.transactionalFramework.ATransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;



public class GroupWorkDAO extends ATransactional implements IGroupWorkDAO {

	public void updateUserGroupWork(UserDTO currentUser) throws TransactionalConnectionException {

		Statement statement = null;
		ResultSet resultSet = null;
		try {
			// execution d'une requête SQL et récupération du result dans le
			// resultset
			String insertInstruction = "UPDATE groupe_de_travail set id_util_chef_de_groupe = NULL WHERE id_util_chef_de_groupe = "
								+ currentUser.getId();
			
			PreparedStatement preparedStatement = getConnection().prepareStatement(insertInstruction);
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

	}

}
