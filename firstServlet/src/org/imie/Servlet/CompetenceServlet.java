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

import org.imie.DAO.interfaces.ICompetenceDAO;
import org.imie.DTO.CompetenceDTO;
import org.imie.DTO.UserDTO;
import org.imie.exeptionManager.ExceptionManager;
import org.imie.factory.BaseConcreteFactory;
import org.imie.service.interfaces.ICompetenceService;
import org.imie.service.interfaces.IUserService;
import org.imie.transactionalFramework.TransactionalConnectionException;

/**
 * Servlet implementation class CompetenceServlet
 */
@WebServlet("/CompetenceServlet")
public class CompetenceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CompetenceServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ICompetenceService competenceService = BaseConcreteFactory
				.getInstance().createCompetenceService(null);
		response.setContentType("text/html");

		// creation de la session

		HttpSession session = request.getSession();
		List<CompetenceDTO> competenceDTOs = null;
		try {
			competenceDTOs = competenceService.findAll();
		} catch (TransactionalConnectionException e) {
			ExceptionManager.getInstance().manageException(e);
		}
		String ligne = request.getParameter("ligne");
		if (ligne != null) {

			// envoie de la liste en session

			session.setAttribute("listecompetence", competenceDTOs);
			Integer competenceRead = Integer.valueOf(ligne);

			// recuperation de la liste
			competenceDTOs = (List<CompetenceDTO>) session
					.getAttribute("listecompetence");

			CompetenceDTO competenceChoisie = competenceDTOs
					.get(competenceRead - 1);
			session.setAttribute("competencechoisie", competenceChoisie);
			session.removeAttribute("listecompetence");

			// forward
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("./competence.jsp");
			dispatcher.forward(request, response);
		} else {

			// sinon envoie de la liste en session
			session.setAttribute("listuser", competenceDTOs);
			// et affichage de la liste

			request.getRequestDispatcher("./listecompetence.jsp").forward(
					request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// recupération du paramétre de l'url

	}
}