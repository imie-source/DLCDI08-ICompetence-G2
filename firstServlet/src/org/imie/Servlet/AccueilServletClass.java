package org.imie.Servlet;

import java.io.IOException;

import java.util.List;

import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.imie.DTO.UserDTO;
import org.imie.exeptionManager.ExceptionManager;
import org.imie.factory.BaseConcreteFactory;
import org.imie.service.interfaces.IUserService;
import org.imie.transactionalFramework.TransactionalConnectionException;

/**
 * Servlet implementation class AccueilServletClass
 */
// en 3.0 on utilise les annotations pour le mapping

@WebServlet(description = "la premiere servlet", urlPatterns = { "/AccueilServletClass" })
public class AccueilServletClass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccueilServletClass() {
		super();
		// TODO Auto-generated constructor stub
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
		List<UserDTO> userDTOs = null;
		try {
			userDTOs = userService.getUsers();
		} catch (TransactionalConnectionException e) {
			ExceptionManager.getInstance().manageException(e);
		}
		System.out.println("hello world");

		// recupération du paramétre de l'url
		String ligne = request.getParameter("ligne");
		if (ligne != null) {

			// envoie de la liste en session

			session.setAttribute("listuser", userDTOs);
			Integer userRead = Integer.valueOf(ligne);
			
			//recuperation de la liste 
			List<UserDTO> listuser = (List<UserDTO>) session
					.getAttribute("listuser");

			UserDTO userChoose = listuser.get(userRead - 1);
			session.setAttribute("userChoose", userChoose);
			session.removeAttribute("listuser");

			// forward
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("./user.jsp");
			dispatcher.forward(request, response);
		} else {

			// sinon envoie de la liste en session
			session.setAttribute("listuser", userDTOs);
			// et affichage de la liste

			request.getRequestDispatcher("./liste.jsp").forward(request,response);
		}

	}

}