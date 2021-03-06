package org.imie.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.imie.DTO.CompetenceDTO;
import org.imie.exeptionManager.ExceptionManager;
import org.imie.factory.BaseConcreteFactory;
import org.imie.service.interfaces.ICompetenceService;
import org.imie.transactionalFramework.TransactionalConnectionException;

/**
 * Servlet implementation class CompetenceServlet
 */
@WebServlet("/CompetenceServletClass")
public class CompetenceServletClass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CompetenceServletClass() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(">>>>>>>Servlet name Competence :"+this.getServletContext().getServletContextName());
		ICompetenceService competenceService = BaseConcreteFactory
				.getInstance().createCompetenceService(null);

		List<CompetenceDTO> competenceDTOs = null;
		try {

			competenceDTOs = competenceService.findAll();
			request.setAttribute("listecompetence", competenceDTOs);

		} catch (TransactionalConnectionException e) {
			ExceptionManager.getInstance().manageException(e);
		}

		// forward
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("./ListeCompetenceUtilisateur.jsp");
		dispatcher.forward(request, response);
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