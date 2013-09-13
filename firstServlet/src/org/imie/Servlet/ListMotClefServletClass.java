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

import org.imie.DTO.MotClefDTO;
import org.imie.factory.BaseConcreteFactory;
import org.imie.service.interfaces.IMotClefService;
import org.imie.transactionalFramework.TransactionalConnectionException;

@WebServlet("/ListMotClefServletClass")
public class ListMotClefServletClass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListMotClefServletClass() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		IMotClefService motClefService = BaseConcreteFactory.getInstance()
				.createMotClefService(null);
		response.setContentType("text/html");

		List<MotClefDTO> motClefDTOs = new ArrayList<MotClefDTO>();

		try {
			session.setAttribute("listeMotClef",motClefDTOs = motClefService.findAll());
		} catch (TransactionalConnectionException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("./listeMotClef.jsp").forward(request,
				response);
		System.out.println("liste mot clef");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		IMotClefService motClefService = BaseConcreteFactory.getInstance()
				.createMotClefService(null);
		HttpSession session = request.getSession();

		System.out.println("ListMotClefServletClass");
		// recupération du paramétre de l'url
		String urlParam = request.getParameter("UrlParam");

		if (urlParam.equals("creer")) {
			MotClefDTO motClefDTOcreer = new MotClefDTO();
			String libelleParam = request.getParameter("libelle");
			motClefDTOcreer.setLibelle(libelleParam);
			try {
				System.out.println("creation mot clef");
				motClefService.insertmotClef(motClefDTOcreer);
			} catch (TransactionalConnectionException e) {
				System.out.println("echec de la creation du mot clef");
				e.printStackTrace();
			}
			session.removeAttribute("listeMotClef");
			response.sendRedirect("./ListMotClefServletClass");
		}

		if (urlParam.equals("modif")) {
			MotClefDTO motClefDTOModif = new MotClefDTO();

			String libelleParam = request.getParameter("libelle");
			System.out.println("libelle param");
			String idMotClefParam = request.getParameter("motClefid");
			if (idMotClefParam != null) {
				Integer idmotClef = Integer.valueOf(idMotClefParam);
				motClefDTOModif.setId(idmotClef);
				motClefDTOModif.setLibelle(libelleParam);
				try {
					System.out.println("modification du mot clef");
					motClefService.updatemotClef(motClefDTOModif);
				} catch (TransactionalConnectionException e) {
					System.out.println("echec de la modif du mot clef");
					e.printStackTrace();
				}
			}
			session.removeAttribute("listeMotClef");
			response.sendRedirect("./ListMotClefServletClass");
		}

		if (urlParam.equals("suppr")) {
			String idMotClefParam = request.getParameter("motClefid");
			if (idMotClefParam != null) {
				Integer idmotClef = Integer.valueOf(idMotClefParam);
				MotClefDTO motClefDTOSupr = new MotClefDTO();
				motClefDTOSupr.setId(idmotClef);
				try {
					System.out.println("supression du mot clef");
					motClefService.deletemotClef(motClefDTOSupr);
				} catch (TransactionalConnectionException e) {
					System.out.println("echec de la supression du mot clef");
					e.printStackTrace();
				}
			}
			session.removeAttribute("listeMotClef");
			response.sendRedirect("./ListMotClefServletClass");
		}

	}
}
