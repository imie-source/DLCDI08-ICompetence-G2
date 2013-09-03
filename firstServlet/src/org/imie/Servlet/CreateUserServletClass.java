package org.imie.Servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
 * Servlet implementation class CreateServletClass
 */
@WebServlet("/CreateServletClass")
public class CreateUserServletClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String DATE_FORMAT = "dd/MM/yyyy";
	private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
	private AdresseDTO adresseToCreate = new AdresseDTO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateUserServletClass() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		// debut de la page html

		request.getRequestDispatcher("./createuser.jsp").forward(request,

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
		ICursusService cursusService = BaseConcreteFactory.getInstance()
				.createCursusService(null);
		IUserService userService = BaseConcreteFactory.getInstance()
				.createUserService(null);

		// methode de création de l'adresse

		String libelleParam = request.getParameter("libelle");
		String villeParam = request.getParameter("ville");
		String codePostalParam = request.getParameter("code_postal");
		if (codePostalParam != null) {

			Integer codePostal = Integer.valueOf(codePostalParam);

			IAdresseDAO adresseDAO = BaseConcreteFactory.getInstance()
					.createAdresseDAO(null);

			adresseToCreate.setLibelle(libelleParam);
			adresseToCreate.setVille(villeParam);
			adresseToCreate.setCode_postal(codePostal);

			try {
				adresseDAO.createAdresse(adresseToCreate);
			} catch (TransactionalConnectionException e) {
				
				e.printStackTrace();
			}
		}

			String userNomParam = request.getParameter("nom");
			String userPrenomParam = request.getParameter("prenom");
			Date userDateNaissParam = null;
			try {
				userDateNaissParam = getDateInput(request
						.getParameter("date_naissance"));
			} catch (PassThroughInputException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String userCursusParam = request.getParameter("cursusid");
			if (userCursusParam != null) {

				Integer cursusid = Integer.valueOf(userCursusParam);

				String userAdresseMailParam = request
						.getParameter("adresse_mail");
				String userIdentifiantParam = request
						.getParameter("identifiant");
				String userPwdParam = request.getParameter("pwd");

				CursusDTO cursusDTO = new CursusDTO();
				try {
					cursusDTO = cursusService.findbyid(cursusid);
				} catch (TransactionalConnectionException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				UserDTO newUser = new UserDTO();

				newUser.setNom(userNomParam);
				newUser.setPrenom(userPrenomParam);
				newUser.setDateNaiss(userDateNaissParam);
				newUser.setAdresse_mail(userAdresseMailParam);
				newUser.setIdentifiant(userIdentifiantParam);
				newUser.setPwd(userPwdParam);
				newUser.setCursus(cursusDTO);
				//newUser.addAdresse(adresseToCreate);
				try {
					userService.insertUser(newUser);
				} catch (TransactionalConnectionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		response.sendRedirect("./AccueilServletClass");
	}
}
