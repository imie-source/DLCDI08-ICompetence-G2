package org.imie.Servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.imie.DAO.interfaces.IAdresseDAO;
import org.imie.DTO.AdresseDTO;
import org.imie.DTO.CursusDTO;
import org.imie.DTO.UserDTO;
import org.imie.IHM.PassThroughInputException;
import org.imie.factory.BaseConcreteFactory;
import org.imie.service.interfaces.ICursusService;
import org.imie.service.interfaces.IUserService;
import org.imie.transactionalFramework.TransactionalConnectionException;

/**
 * Servlet implementation class UpdateServletClass
 */
@WebServlet("/UpdateServletClass")
public class UpdateServletClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String DATE_FORMAT = "dd/MM/yyyy";
	private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateServletClass() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");

		// debut de la page html

		request.getRequestDispatcher("./userupdate.jsp").forward(request,

		response);

	}

	private Date getDateInput(String datenaissconvert)
			throws PassThroughInputException {

		String dateNaissString = datenaissconvert;

		Date dateNaissDate = null;
		if (dateNaissString != null) {
			try {
				dateNaissDate = dateFormat.parse(dateNaissString);
			} catch (ParseException e1) {

			}
		}
		return dateNaissDate;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// update de l'adresse
		IAdresseDAO adresseDAO = BaseConcreteFactory.getInstance()
				.createAdresseDAO(null);
		AdresseDTO adresseToUpdate = new AdresseDTO();

		String libelleParam = request.getParameter("libelle");
		String villeParam = request.getParameter("ville");
		String codePostalParam = request.getParameter("code_postal");
		String idAdresseParam = request.getParameter("id_adresse");
		if (codePostalParam != null) {

			Integer codePostal = Integer.valueOf(codePostalParam);

			if (idAdresseParam != null) {

				Integer idAdresse = Integer.valueOf(idAdresseParam);

				adresseToUpdate.setLibelle(libelleParam);
				adresseToUpdate.setVille(villeParam);
				adresseToUpdate.setCode_postal(codePostal);
				adresseToUpdate.setId_adresse(idAdresse);
				try {
					adresseDAO.UpdateAdresse(adresseToUpdate);
				} catch (TransactionalConnectionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		String userIdParam = request.getParameter("userid");
		Integer userId = null;
		if (userIdParam != null) {
			userId = Integer.valueOf(userIdParam);
		}
		if (userId != null) {
			IUserService userService = BaseConcreteFactory.getInstance()
					.createUserService(null);

			List<UserDTO> userDTOs = new ArrayList<UserDTO>();

			try {
				userDTOs = userService.getUsers();
			} catch (TransactionalConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (userDTOs.size() > 0) {
				UserDTO userDTOToUpdate = userDTOs.get(0);
				String userIdentifiantParam = request.getParameter("identifiant");
				String userNomParam = request.getParameter("nom");
				String userPrenomParam = request.getParameter("prenom");
				String userMailParam = request.getParameter("mail");
				String userCursusParam = request.getParameter("cursusid");
				Date userDateNaissParam = null;
				String userDisponibleParam = request.getParameter("disponible");
				if (userDisponibleParam != null) {
					Boolean disponible = Boolean.valueOf(userDisponibleParam);

					try {
						userDateNaissParam = getDateInput(request
								.getParameter("datenaissance"));
					} catch (PassThroughInputException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					if (userCursusParam != null) {
						Integer userCursus = Integer.valueOf(userCursusParam);
						ICursusService cursusService = BaseConcreteFactory
								.getInstance().createCursusService(null);

						CursusDTO cursusDTO = new CursusDTO();
						try {
							cursusDTO = cursusService.findbyid(userCursus);
						} catch (TransactionalConnectionException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						userDTOToUpdate.setId(userId);
						userDTOToUpdate.setNom(userNomParam);
						userDTOToUpdate.setPrenom(userPrenomParam);
						userDTOToUpdate.setDateNaiss(userDateNaissParam);
						userDTOToUpdate.setCursus(cursusDTO);
						userDTOToUpdate.setAdresse_mail(userMailParam);
						userDTOToUpdate.setDisponible(disponible);
						userDTOToUpdate.setIdentifiant(userIdentifiantParam);
						
					

						try {
							userService.updateUser(userDTOToUpdate);
						} catch (TransactionalConnectionException e) {
							// TODO Gerer l'affichage en cas d'erreur.
							e.printStackTrace();

						}
					}

				}

			}

			response.sendRedirect("./AccueilServletClass");
		}
	}

}
