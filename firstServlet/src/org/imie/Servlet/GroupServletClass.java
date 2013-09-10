package org.imie.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.imie.DTO.GroupeDeTravailDTO;
import org.imie.factory.BaseConcreteFactory;
import org.imie.service.interfaces.IGroupeDeTravailService;
import org.imie.transactionalFramework.TransactionalConnectionException;

/**
 * Servlet implementation class GroupServletClass
 */
@WebServlet("/GroupServletClass")
public class GroupServletClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupServletClass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		IGroupeDeTravailService gdtService = BaseConcreteFactory.getInstance().creerGroupeDeTravailService(null);
		try {
			request.setAttribute("listgdt", gdtService.afficherGroupeDeTravail());
		} catch (TransactionalConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("./ListeGroupeDeTravail.jsp").forward(request, response);	
		
	}	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IGroupeDeTravailService gdtService = BaseConcreteFactory.getInstance()
				.creerGroupeDeTravailService(null);
		
		String urlParam = null;
		urlParam = request.getParameter("UrlParam");
		

		if (urlParam.equals("creer")) {
			GroupeDeTravailDTO gdtDTOcreer = new GroupeDeTravailDTO();
			String libelleParam = request.getParameter("libelle");
			String typeParam = request.getParameter("type");
			gdtDTOcreer.setType_projet(typeParam);
			gdtDTOcreer.setNom(libelleParam);
			try {
				System.out.println("creation du groupe de travail");
				gdtService.creerGroupeDeTravail(gdtDTOcreer);
			} catch (TransactionalConnectionException e) {
				System.out.println("echec de la creation du groupe de travail");
				e.printStackTrace();
			}
			response.sendRedirect("./listecursus.jsp");
		}

		if (urlParam.equals("modif")) {
			GroupeDeTravailDTO gdtDTOmodif = new GroupeDeTravailDTO();

			
			String idGdtParam = request.getParameter("gdtid");
			if (idGdtParam != null) {
				Integer idGdt = Integer.valueOf(idGdtParam);
				gdtDTOmodif.setId_gdt(idGdt);
				
				try {
					System.out.println("modification du groupe de travail");
					gdtService.modifierGroupeDeTravail(gdtDTOmodif);
				} catch (TransactionalConnectionException e) {
					System.out.println("echec de la modif du groupe de travail");
					e.printStackTrace();
				}
			}
			response.sendRedirect("./listeGroupeDeTravail.jsp");
		}

	}	
	

}
