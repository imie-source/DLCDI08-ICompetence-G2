package org.imie.Servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.imie.DAO.interfaces.IAdresseDAO;
import org.imie.DTO.AdresseDTO;
import org.imie.DTO.CursusDTO;
import org.imie.DTO.UserDTO;
import org.imie.IHM.PassThroughInputException;
import org.imie.exeptionManager.ExceptionManager;
import org.imie.factory.BaseConcreteFactory;
import org.imie.service.interfaces.ICursusService;
import org.imie.service.interfaces.IUserService;
import org.imie.transactionalFramework.TransactionalConnectionException;

/**
 * Servlet implementation class ListeUserServlet
 */
@WebServlet("/ListeUserServlet")
public class ListeUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String DATE_FORMAT = "dd/MM/yyyy";
	private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
	private AdresseDTO adresseToCreate = new AdresseDTO();
	AdresseDTO nouvelleAdresse = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListeUserServlet() {
		super();
		// TODO Auto-generated constructor stub
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		IUserService userService = BaseConcreteFactory.getInstance()
				.createUserService(null);
		response.setContentType("text/html");
		// creation de la session
		HttpSession session = request.getSession();
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		try {
			session.setAttribute("listUser", userDTOs = userService.getUsers());
		} catch (TransactionalConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("listeuser");
		// recupération du paramétre de l'url
		String ligne = request.getParameter("ligne");
		if (ligne != null) {
			
			Integer userRead = Integer.valueOf(ligne);
			// recuperation de la liste
			List<UserDTO> listUser = (List<UserDTO>) session
					.getAttribute("listUser");
			UserDTO userChoose = listUser.get(userRead - 1);
			session.setAttribute("userChoose", userChoose);
			session.removeAttribute("listUser");
			// forward
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("./user.jsp");
			dispatcher.forward(request, response);
		} else {
			request.getRequestDispatcher("./liste.jsp").forward(request,
					response);
		}
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ICursusService cursusService = BaseConcreteFactory.getInstance()
				.createCursusService(null);
		IUserService userService = BaseConcreteFactory.getInstance()
				.createUserService(null);
		IAdresseDAO adresseDAO = BaseConcreteFactory.getInstance()
				.createAdresseDAO(null);
		HttpSession session = request.getSession();
		// recupération du paramétre de l'url
		String urlParam = request.getParameter("UrlParam");
		System.out.println("ListeUserClass");

		
		if (urlParam.equals("creer")) {
			// methode de création de l'adresse
			String libelleParam = request.getParameter("libelle");
			String villeParam = request.getParameter("ville");
			String codePostalParam = request.getParameter("code_postal");
			if (codePostalParam != null) {
				Integer codePostal = Integer.valueOf(codePostalParam);
				adresseToCreate.setLibelle(libelleParam);
				adresseToCreate.setVille(villeParam);
				adresseToCreate.setCode_postal(codePostal);
				try {
					nouvelleAdresse = adresseDAO.createAdresse(adresseToCreate);
				} catch (TransactionalConnectionException e) {
					e.printStackTrace();
				}
			}
			System.out.println(nouvelleAdresse.getId_adresse());
			String userNomParam = request.getParameter("nom");
			String userPrenomParam = request.getParameter("prenom");
			String userDisponibleParam = request.getParameter("disponible");
			if (userDisponibleParam != null) {
				Boolean disponible = Boolean.valueOf(userDisponibleParam);
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
					newUser.setDisponible(disponible);
					newUser.setAdresse(nouvelleAdresse);
					try {
						System.out.println("CreationUser");
						userService.insertUser(newUser);
					} catch (TransactionalConnectionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					session.removeAttribute("listuser");
					response.sendRedirect("./ListeUserServlet");
				}
			}
		}
		
		if (urlParam.equals("modif")) {
			System.out.println("modif utilisateur");
			// update de l'adresse
			IAdresseDAO adresseDaomodif = BaseConcreteFactory.getInstance()
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
						adresseDaomodif.UpdateAdresse(adresseToUpdate);
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
				IUserService userServicemodif = BaseConcreteFactory.getInstance()
						.createUserService(null);
				List<UserDTO> userDTOs = new ArrayList<UserDTO>();
				try {
					userDTOs = userServicemodif.getUsers();
				} catch (TransactionalConnectionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (userDTOs.size() > 0) {
					UserDTO userDTOToUpdate = userDTOs.get(0);
					String userIdentifiantParam = request
							.getParameter("identifiant");
					String userNomParam = request.getParameter("nom");
					String userPrenomParam = request.getParameter("prenom");
					String userMailParam = request.getParameter("mail");
					String userCursusParam = request.getParameter("cursusid");
					Date userDateNaissParam = null;
					String userDisponibleParam = request
							.getParameter("disponible");
					if (userDisponibleParam != null) {
						Boolean disponible = Boolean
								.valueOf(userDisponibleParam);
						try {
							userDateNaissParam = getDateInput(request
									.getParameter("datenaissance"));
						} catch (PassThroughInputException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if (userCursusParam != null) {
							Integer userCursus = Integer
									.valueOf(userCursusParam);
							ICursusService cursusServicemodif = BaseConcreteFactory
									.getInstance().createCursusService(null);

							CursusDTO cursusDTO = new CursusDTO();
							try {
								cursusDTO = cursusServicemodif.findbyid(userCursus);
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
							userDTOToUpdate
									.setIdentifiant(userIdentifiantParam);
							try {
								userServicemodif.updateUser(userDTOToUpdate);
							} catch (TransactionalConnectionException e) {
								// TODO Gerer l'affichage en cas d'erreur.
								e.printStackTrace();

							}
							session.removeAttribute("listuser");
							response.sendRedirect("./ListeUserServlet");
						}

					}

				}
				
			}
		}
		
		if (urlParam.equals("suppr")) {
			String idUserParam = request.getParameter("idusersuppr");
			if (idUserParam != null) {
				Integer iduser = Integer.valueOf(idUserParam);
				UserDTO userDtosupr = new UserDTO();
				userDtosupr.setId(iduser);
				try {
					System.out.println("supression du user");
					userService.deleteUser(userDtosupr);
				} catch (TransactionalConnectionException e) {
					System.out.println("echec de la supression du user");
					e.printStackTrace();
				}
				session.removeAttribute("listuser");
				response.sendRedirect("./ListeUserServlet");
			}
				
			}
		
	}

}
