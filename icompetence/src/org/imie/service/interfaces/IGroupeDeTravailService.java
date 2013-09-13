package org.imie.service.interfaces;

import java.util.List;

import org.imie.DTO.GroupeDeTravailDTO;
import org.imie.DTO.UserDTO;
import org.imie.transactionalFramework.ITransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

public interface IGroupeDeTravailService extends ITransactional {

	/**
	 * créer un groupe de travail
	 * 
	 * @param groupeDeTravailAInserer
	 * @return le groupe de travail indéré
	 * @throws ClassNotFoundException
	 * @throws TransactionalConnectionException
	 */
	public abstract Boolean creerGroupeDeTravail(
			GroupeDeTravailDTO groupeDeTravailAInserer)
			throws TransactionalConnectionException;

	/**
	 * retourne une list d'utilisateur par groupe de travail
	 * 
	 * @param gdtDTO
	 * @return liste d'utilisateur par groupe de travail
	 * @throws TransactionalConnectionException
	 */
	
	public List<UserDTO> utilisateurParGroupeDeTravail (GroupeDeTravailDTO gdtDTO) throws TransactionalConnectionException;
	
	/**
	 * modifier groupe de travail
	 * 
	 * @param groupeDeTravailAModifier
	 * @return
	 * @throws ClassNotFoundException
	 * @throws TransactionalConnectionException
	 */
	public abstract Boolean modifierGroupeDeTravail(
			GroupeDeTravailDTO groupeDeTravailAModifier)
			throws TransactionalConnectionException;

	/**
	 * supprimer un groupe de travail
	 * 
	 * @param groupeDeTravailASupprimer
	 * @throws ClassNotFoundException
	 * @throws TransactionalConnectionException
	 */
	public abstract void supprimerGroupeDeTravail(
			GroupeDeTravailDTO groupeDeTravailASupprimer)
			throws TransactionalConnectionException;

	/**
	 * modification user dans gdt
	 * 
	 * @param userDTO
	 * @return
	 * @throws ClassNotFoundException
	 * @throws TransactionalConnectionException
	 */
	public abstract Boolean supprimerUserGroupeDeTravail(UserDTO userDTO,
			GroupeDeTravailDTO gdtDTO) throws TransactionalConnectionException;

	public abstract Boolean creerUserGdt(UserDTO userDTO,
			GroupeDeTravailDTO gdtDTO);

	public abstract Boolean modifCP(UserDTO userDTO, GroupeDeTravailDTO gdtDTO) throws TransactionalConnectionException;

	/**
	 * afficher un groupe de travail
	 * 
	 * @param groupeDeTravailAfficher
	 * @return le groupe de travail indéré
	 * @throws ClassNotFoundException
	 * @throws TransactionalConnectionException
	 */
	public abstract List<GroupeDeTravailDTO> afficherGroupeDeTravail()
			throws TransactionalConnectionException;

}
