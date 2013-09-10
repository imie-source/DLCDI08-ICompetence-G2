package org.imie.Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.imie.DTO.CursusDTO;
import org.imie.factory.BaseConcreteFactory;
import org.imie.service.interfaces.ICursusService;
import org.imie.transactionalFramework.TransactionalConnectionException;

/**
 * Servlet implementation class CursusServletClass
 */
@WebServlet("/CursusServletClass")
public class CursusServletClass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ICursusService cursusService = BaseConcreteFactory.getInstance()
				.createCursusService(null);
		response.setContentType("text/html");

		List<CursusDTO> cursusDTOs = new ArrayList<CursusDTO>();
		try {
			session.setAttribute("listeCursus",
					cursusDTOs = cursusService.findAll());
		} catch (TransactionalConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("listeCursus", cursusDTOs);
		request.getRequestDispatcher("./listecursus.jsp").forward(request,
				response);
		System.out.println("liste cursus");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ICursusService cursusService = BaseConcreteFactory.getInstance()
				.createCursusService(null);
		HttpSession session = request.getSession();

		System.out.println("CursusServletClass");
		// recupération du paramétre de l'url
		String urlParamCreer = request.getParameter("UrlParam");
		String urlParamModif = request.getParameter("UrlParam");
		String urlParamSupr = request.getParameter("UrlParam");

		if (urlParamCreer.equals("creer")) {
			CursusDTO cursusDTOcreer = new CursusDTO();
			String libelleParam = request.getParameter("libelle");
			cursusDTOcreer.setLibelle(libelleParam);
			try {
				System.out.println("creation du cursus");
				cursusService.insertCursus(cursusDTOcreer);
			} catch (TransactionalConnectionException e) {
				System.out.println("echec de la creation du cursus");
				e.printStackTrace();
			}
			session.removeAttribute("listeCursus");
			response.sendRedirect("./CursusServletClass");
		}

		if (urlParamModif.equals("modif")) {
			CursusDTO cursusDTOModif = new CursusDTO();

			String libelleParam = request.getParameter("libelle");
			String idCursusParam = request.getParameter("cursusid");
			if (idCursusParam != null) {
				Integer idcursus = Integer.valueOf(idCursusParam);
				cursusDTOModif.setId(idcursus);
				cursusDTOModif.setLibelle(libelleParam);
				try {
					System.out.println("modification du cursus");
					cursusService.updateCursus(cursusDTOModif);
				} catch (TransactionalConnectionException e) {
					System.out.println("echec de la modif du cursus");
					e.printStackTrace();
				}
			}
			session.removeAttribute("listeCursus");
			response.sendRedirect("./CursusServletClass");
		}

		if (urlParamSupr.equals("suppr")) {
			String idCursusParam = request.getParameter("cursusid");
			if (idCursusParam != null) {
				Integer idcursus = Integer.valueOf(idCursusParam);
				CursusDTO cursusDTOSupr = new CursusDTO();
				cursusDTOSupr.setId(idcursus);
				try {
					System.out.println("supression du cursus");
					cursusService.deleteCursus(cursusDTOSupr);
				} catch (TransactionalConnectionException e) {
					System.out.println("echec de la supression du cursus");
					e.printStackTrace();
				}
			}
			session.removeAttribute("listeCursus");
			response.sendRedirect("./CursusServletClass");
		}

	}
}
