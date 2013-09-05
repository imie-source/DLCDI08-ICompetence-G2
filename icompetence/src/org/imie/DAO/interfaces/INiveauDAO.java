package org.imie.DAO.interfaces;

import java.util.List;

import org.imie.DTO.NiveauDTO;
import org.imie.transactionalFramework.ITransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;


/**
 * interface du niveau de competence d'un utilisateur
 * 
 */
public interface INiveauDAO extends ITransactional{
		
		public abstract List<NiveauDTO> findAll() throws TransactionalConnectionException;

		public abstract NiveauDTO findById(Integer niveauid) throws TransactionalConnectionException;
		
		public abstract NiveauDTO insertNiveau(NiveauDTO niveauToInsert) throws TransactionalConnectionException;
		
		public abstract NiveauDTO updateNiveau(NiveauDTO niveauToUpdate) throws TransactionalConnectionException;
		
		public abstract void deleteNiveau(NiveauDTO niveauToDelete) throws TransactionalConnectionException;
		
		
}
