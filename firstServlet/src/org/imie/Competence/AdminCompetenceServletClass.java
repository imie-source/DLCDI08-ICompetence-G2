package org.imie.Competence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class AdminCompetenceServletClass
 */
@WebServlet("/AdminCompetenceServletClass")
public class AdminCompetenceServletClass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminCompetenceServletClass() {
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

		ICompetenceService competenceService = BaseConcreteFactory
				.getInstance().createCompetenceService(null);

		List<CompetenceDTO> competenceDTOs = null;
		List<CompetenceDTO> competenceDTOrechs = new ArrayList<CompetenceDTO>();
		List<CompetenceDTO> competenceDTOtrouves = null;

		String compRecher = null;
		int idrech;
		compRecher = request.getParameter("competencerecherchee").toLowerCase();

		try {
			if (compRecher != null) {
				competenceDTOs = competenceService.findByNom(compRecher);
				for (CompetenceDTO comp : competenceDTOs) {
					idrech = comp.getId();
					competenceDTOtrouves = competenceService
							.findArboFilsPere(idrech);
					competenceDTOrechs.addAll(competenceDTOtrouves);
				}
				request.setAttribute("listecompetence", competenceDTOrechs);
				// forward
				request.getRequestDispatcher("./arborescence.jsp").forward(
						request, response);
			} else {
				request.getRequestDispatcher("./Accueil.jsp").forward(request,
						response);
			}
			compRecher = null;
		} catch (TransactionalConnectionException e) {
			ExceptionManager.getInstance().manageException(e);
		}

	}

}
