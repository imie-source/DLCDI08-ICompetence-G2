package org.imie.Competence;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
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
 * Servlet implementation class AfficherArborescence
 */
@WebServlet(urlPatterns = "/AfficherArborescence",
		initParams ={@WebInitParam(name="profil",value="1")})
public class AfficherArborescence extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AfficherArborescence() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		/***
		 * @author ym
		 * charger les parametres
		 * 
		 */
		
		ServletConfig sc = getServletConfig();
		String leProfil = sc.getInitParameter("profil");
		
		
		
		ICompetenceService competenceService = BaseConcreteFactory.getInstance().createCompetenceService(null);
		
		
		
		System.out.println(">>>>>>>Servlet profil AfficherArborescence : "+leProfil);
		List<CompetenceDTO> competenceDTOs = null;
		try {			
				
				competenceDTOs = competenceService.findAllArbo();
				request.setAttribute("listecompetence", competenceDTOs);
			
		} catch (TransactionalConnectionException e) {
			ExceptionManager.getInstance().manageException(e);
		}
		
			// forward
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("./arborescence.jsp");
			dispatcher.forward(request, response);	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Integer parent;
		String libelle = request.getParameter("libelle");
		Integer idcomp = Integer.valueOf(request.getParameter("idcomp"));
		String chemin = (request.getParameter("chemincomp"));
		
		
		ICompetenceService competenceService = BaseConcreteFactory.getInstance().createCompetenceService(null);
		CompetenceDTO competenceDTO = new CompetenceDTO();
		
			
		if (request.getParameter("modifier") != null  ) {
			
			try {	
				
				competenceDTO.setId(idcomp);
				competenceDTO.setLibelle(libelle);
				
				competenceService.updateCompetence(competenceDTO);
				
			} catch (TransactionalConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if (request.getParameter("supprimer") != null ) {
			
			try {
				
				competenceDTO.setId(idcomp);
				competenceDTO.setLibelle(libelle);					
				
				competenceService.deleteCompetence(competenceDTO);
			} catch (TransactionalConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if (request.getParameter("ajouter") != null ) {
			
			try {
				
				competenceDTO.setId(idcomp);
				competenceDTO.setLibelle(libelle);	
				
				String[] parentch = chemin.split(",");
				int nb = parentch.length-1;
				
				String parentstr = parentch[nb];
				parent = Integer.valueOf(parentstr.replace("}",""));
				
				competenceDTO.setNiveauParent(parent);
				System.out.println(parent);
				
				
				
				competenceService.insertCompetence(competenceDTO);
			} catch (TransactionalConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} 
		
		response.sendRedirect("./AfficherArborescence");
		
		
		
	}

}
