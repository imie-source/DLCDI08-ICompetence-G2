package org.imie.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ICursusService cursusService = BaseConcreteFactory.getInstance()
				.createCursusService(null);

		System.out.println("CursusServletClass");
		// recupération du paramétre de l'url
		String urlParamCreer = request.getParameter("UrlParam");
		String urlParamModif = request.getParameter("UrlParam");

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
			response.sendRedirect("./listecursus.jsp");
		}

		if (urlParamModif.equals("modif")) {
			CursusDTO cursusDTOModif = new CursusDTO();

			String libelleParam = request.getParameter("libelle");
			String idCursusParam = request.getParameter("idCursus");
			if (idCursusParam != null) {
				Integer idcursus = Integer.valueOf(idCursusParam);
				cursusDTOModif.setId(idcursus);
				cursusDTOModif.setLibelle(libelleParam);
			}

		}
	}
}
