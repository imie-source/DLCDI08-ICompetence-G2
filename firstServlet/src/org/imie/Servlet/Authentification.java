package org.imie.Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.imie.DAO.UserDAO;
import org.imie.DTO.UserDTO;
import org.imie.factory.BaseConcreteFactory;
import org.imie.service.interfaces.IUserService;
import org.imie.transactionalFramework.TransactionalConnectionException;

@WebServlet(description = "Authentification du user", urlPatterns = { "/Authentification" })
public class Authentification extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Authentification() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO forwarder sur la page demandée avant authentification
		// TODO gérer les hash de mots de passe
		// TODO gérer la déconnexion : quel choix entre la faire ici et avoir
		// une servlet qui la gère?
		// récupération et parsing de la requete
		String path = ((HttpServletRequest) request).getRequestURI();
		System.out.println("url demandée dans Authentification" +path);
		String contextPath = request.getContextPath();
		// la page demandée
		
		String resource = request.getRequestURI();
		System.out.println(resource);
		
		Boolean connected = false;

		// récupération de la valeur du parametre de session du loginButton qui
		// est fixé sur la page du formulaire
		String formSubmitName = request.getParameter("valider");
		IUserService userService = BaseConcreteFactory.getInstance()
				.createUserService(null);
		
		
	

		UserDTO userDTOToIdentify = new UserDTO();
		UserDTO userDTOToFound = new UserDTO();
		HttpSession session = request.getSession();
		String login = "";
		String password = "";
		// création d'un DTO auquel on passe le nom
		if (request.getParameter("login") != null
				&& request.getParameter("password") != null) {
			login = (String) request.getParameter("login");
			password = (String) request.getParameter("password");
			
		}
		userDTOToIdentify.setIdentifiant(login);	
		System.out.println(userDTOToIdentify.getIdentifiant());
		try {
			userDTOToFound =  userService.getUser(userDTOToIdentify);

		} catch (TransactionalConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (userDTOToFound != null) {
			// System.out.println("pwd base " +userDtoFound.getPassword()
			// +" password session: "+ password);
			String pwdBase = userDTOToFound.getMotDePasse().replaceAll("\\s", "");
			System.out.println("bdd :" + pwdBase);

			System.out.println("pwd :" + password);
			if (pwdBase.equalsIgnoreCase(password.replaceAll("\\s", ""))) {
				session.setAttribute("connected", 1);
				session.setAttribute("user", userDTOToFound);
				
				
				//System.out.println("Authentification connected");
				
				RequestDispatcher requestDispatcher = request
						.getRequestDispatcher("./Accueil.jsp");
				requestDispatcher.forward(request, response);
				
			} else {
				// TODO redirect vers la page de login avec message d'erreur
				//System.out.println("erreur password");
				RequestDispatcher requestDispatcher = request
						.getRequestDispatcher("./Connexion.jsp");
				requestDispatcher.forward(request, response);
			}

		} else {
			session.setAttribute("connected", 0);
			//System.out.println(" dans authentification not connected");
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("./Connexion.jsp");
			requestDispatcher.forward(request, response);
		}
		
	}
	
}
