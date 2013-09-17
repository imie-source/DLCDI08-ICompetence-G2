<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="org.imie.DTO.NiveauDTO"%>
<%@page import="org.imie.service.interfaces.INiveauService"%>
<%@page import="org.imie.service.interfaces.ICompetenceService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="org.imie.factory.BaseConcreteFactory"%>
<%@page import="org.imie.service.interfaces.ICursusService"%>
<%@page import="org.imie.DTO.AdresseDTO"%>
<%@page import="org.imie.DTO.CompetenceDTO"%>
<%@page import="org.imie.DTO.CursusDTO"%>
<%@page import="org.imie.DAO.CompetenceDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.imie.DTO.UserDTO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="./jquery/jquery.ui.theme.css" />
<script src="./jquery/jquery-1.9.1.js"></script>
<script src="./jquery/jquery-ui-1.10.3.custom.js"></script>
<script src="./js/user.js"></script>
<script src="./jquery/jquery.validate.js"></script>
<script src="./js/validationuser.js"></script>
<link rel="stylesheet" href="./css/Style.css" />
<title>Détails utilisateur</title>
</head>
<body>
<jsp:include page="./header.jsp"></jsp:include>
	<%-- recuperation du user par le context--%>

	<%
		UserDTO currentUserDTO = (UserDTO) session
				.getAttribute("userChoose");
	%>

	<div class="infouser">
		<div id="Coordonees">
			Nom:<%=currentUserDTO.getNom()%>
			<br /> Prénom:<%=currentUserDTO.getPrenom()%>
			<br /> Age:<%=currentUserDTO.getAge()%>
			ans <br /> Identifiant:<%=currentUserDTO.getIdentifiant()%>
			<br />
			<%
				String dateUser = "";
				String DATE_FORMAT = "dd/MM/yyyy";
				SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
				if (currentUserDTO.getDateNaiss() != null) {
					dateUser = dateFormat.format(currentUserDTO.getDateNaiss());
				}
			%>
			date de naissance:<%=dateUser%>

			mail:<%=currentUserDTO.getAdresse_mail()%>
		</div>

		<div id="Competences">
			<%
				Integer i = 1;
				for (CompetenceDTO competenceDTO : currentUserDTO.getCompetences()) {
					
			%>

			<%=competenceDTO.getLibelle()%>
			<%=competenceDTO.getNiveau()%>
			<input id="competenceid" type="text" name="competenceid" value="<%=competenceDTO.getId()%>"></input>
			<button id=openersupprcompetence>supprimer</button>
			<br />
			<%
				i++;
				}

				CursusDTO cursusDTOModif = currentUserDTO.getCursus();
				String cursusUser = null;
				if (cursusDTOModif != null) {
					cursusUser = cursusDTOModif.getLibelle();
				}
			%>
		</div>
		<div id="Cursus">
			<%=cursusUser%>
		</div>


		<div id="Adresse">

			<%
				for (AdresseDTO adresseDTO : currentUserDTO.getAdresses()) {
			%>
			<input type="hidden" value="<%=adresseDTO.getId_adresse()%>"
				name="id_adresse" />

			<%=adresseDTO.getLibelle()%>
			<%=adresseDTO.getVille()%>
			<%=adresseDTO.getCode_postal()%>
			<%
				}
			%>
		</div>
		<div id="Disponible">
			<%
				if (currentUserDTO.isDisponible() == true) {
			%>
			OUI
			<%
				} else {
			%>
			NON
			<%
				}
			%>
		</div>
		
		
		
		<div class=bottonmenu>
			<button id="openermodif">modifier</button>
			<button id=openersuppr>supprimer</button>
			<button id=openercompetence>ajouter competence</button>


		</div>

	</div>

	<div id="ajoutcompdialog" title="ajoutcomp">
		<form id="formajoutcomp" method="post"
			action="./ListeUserServlet?UrlParam=ajoutcomp">
			<input type="hidden" value="<%=currentUserDTO.getId()%>"
				name="userid" />

			<fieldset>
				<legend>Competence</legend>
				<%
					ICompetenceService competenceService = BaseConcreteFactory
							.getInstance().createCompetenceService(null);
					List<CompetenceDTO> competenceDTOs = new ArrayList<CompetenceDTO>();
					competenceDTOs = competenceService.findAll();
				%>

				<select name="competenceid">
					<%
						for (CompetenceDTO competenceDTO : competenceDTOs) {
							
					%>
					<option value="<%=competenceDTO.getId()%>">
						<%=competenceDTO.getLibelle()%>
					</option>
					<%
						}
						
						%> 
				</select>

				<legend>Niveau</legend>
				<%
					INiveauService niveauService = BaseConcreteFactory.getInstance()
							.createNiveauService(null);
					List<NiveauDTO> niveauDTOs = new ArrayList<NiveauDTO>();
					niveauDTOs = niveauService.findAll();
				%>
				<select name="niveauid">
					<%
						for (NiveauDTO niveauDTO : niveauDTOs) {
					%>
					<option value="<%=niveauDTO.getId()%>">
						<%=niveauDTO.getLibelle()%>
					</option>
					<%
						}
					%>
				</select>
			</fieldset>
			<input type="submit" value="ajouter" /><br />
		</form>
	</div>

	<div id="modifdialog" title="modifier">
		<form id="formmodif" method="post"
			action="./ListeUserServlet?UrlParam=modif">
			<input type="hidden" value="<%=currentUserDTO.getId()%>"
				name="userid" />
			<fieldset>
				<legend>Coordonnées</legend>

				<br /> Nom*:<input type="text" value="<%=currentUserDTO.getNom()%>"
					name="nom" /> <br /> Prénom*:<input type="text"
					value="<%=currentUserDTO.getPrenom()%>" name="prenom" /> <br />
				Age:<%=currentUserDTO.getAge()%>
				ans <br /> Identifiant*:<input type="text"
					value="<%=currentUserDTO.getIdentifiant()%>" name="identifiant" />
				<br /> date de naissance*: jj/mm/yyyy:<input
					id="datenaissancemodif" type="text" value="<%=dateUser%>"
					name="datenaissance" /> mail:<input type="text"
					value="<%=currentUserDTO.getAdresse_mail()%>" name=mail />
				<div id="email"></div>
			</fieldset>

			<fieldset>
				<legend>Competence</legend>


				<select name="competenceid">
					<%
						for (CompetenceDTO competenceDTO : competenceDTOs) {
					%>
					<option value="<%=competenceDTO.getId()%>">
						<%=competenceDTO.getLibelle()%>
					</option>
					<%
						}
					%>
				</select>

				<legend>Niveau</legend>

				<select name="niveauid">
					<%
						for (NiveauDTO niveauDTO : niveauDTOs) {
					%>
					<option value="<%=niveauDTO.getId()%>">
						<%=niveauDTO.getLibelle()%>
					</option>
					<%
						}
					%>
				</select>
			</fieldset>

			<fieldset>
				<legend>Cursus</legend>
				<%
					ICursusService cursusService = BaseConcreteFactory.getInstance()
							.createCursusService(null);
					List<CursusDTO> cursusDTOs = new ArrayList<CursusDTO>();
					cursusDTOs = cursusService.findAll();
				%>
				<select name="cursusid">
					<%
						for (CursusDTO cursusDTO : cursusDTOs) {
							Boolean isUserCursus = cursusDTO.getId().equals(
									currentUserDTO.getCursus().getId());
							if (isUserCursus) {
					%>
					<option selected="selected" value="<%=cursusDTO.getId()%>">
						<%=cursusDTO.getLibelle()%>
					</option>
					<%
						} else {
					%>
					<option value="<%=cursusDTO.getId()%>">
						<%=cursusDTO.getLibelle()%>
					</option>
					<%
						}
						}
					%>

				</select>
			</fieldset>
			<fieldset>
				<legend>Etes vous disponible?</legend>
				<br />
				<%
					if (currentUserDTO.isDisponible() == true) {
				%>
				<input type="radio" name="disponible" value="true" checked="checked" />
				oui <input type="radio" name="disponible" value="false" /> non
				<%
					} else {
				%>
				<input type="radio" name="disponible" value="true" /> oui <input
					type="radio" name="disponible" value="false" checked="checked" />
				non
				<%
					}
				%>
			</fieldset>
			<fieldset>
				<%
					for (AdresseDTO adresseDTO : currentUserDTO.getAdresses()) {
				%>
				<legend>Adresse</legend>
				<input type="hidden" value="<%=adresseDTO.getId_adresse()%>"
					name="id_adresse" /> Libellé*:<input type="text"
					value="<%=adresseDTO.getLibelle()%>" name="libelle" /><br />
				Ville*:<input type="text" value="<%=adresseDTO.getVille()%>"
					name="ville" /><br /> Code postal*:<input type="text"
					value="<%=adresseDTO.getCode_postal()%>" name="code_postal" /><br />
				<%
					}
				%>
			</fieldset>
			<input type="submit" value="modifier" /><br />
		</form>
	</div>

	<div id="supprdialog" title="suprimerutilisateur">
		<form id="formsuppr" method="post"
			action="./ListeUserServlet?UrlParam=suppr">
			<input id="idusersuppr" type="hidden"
				value="<%=currentUserDTO.getId()%>" name="idusersuppr" />
			<fieldset>
				<!-- <legend>supprimer un utilisateur</legend> -->
				vous allez supprimer un utilisateur
			</fieldset>
			<br /> <input type="submit" value="supprimer" />
		</form>
	</div>

</body>
</html>