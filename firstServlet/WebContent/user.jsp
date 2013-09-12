<?xml version="1.0" encoding="UTF-8" ?>
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
<script src="./user.js"></script>
<script src="./jquery/input_constraint.js"></script>
<link rel=stylesheet type=text/css href=./css/Style.css>
	<title>Détails utilisateur</title>
</head>
<body>
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
				for (CompetenceDTO competenceDTO : currentUserDTO.getCompetences()) {
			%>
			<%=competenceDTO.getLibelle()%>
			<%=competenceDTO.getNiveau()%>
			<%
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
		<div class=bottommenu>
			<button id="openermodif">modifier</button>
		<button id=openersuppr>supprimer</button>
		</div>

	</div>

	<div id="modifdialog" title="modifier">
		<form id="formmodif" method="post" action="./ListeUserServlet?UrlParam=modif">
			<input type="hidden" value="<%=currentUserDTO.getId()%>"
				name="userid" />
			<fieldset>
				<legend>Coordonnées</legend>

				<br /> Nom*:<input class="only_alpha" type="text" value="<%=currentUserDTO.getNom()%>"
					name="nom" /> <br /> Prénom*:<input class="only_alpha" type="text"
					value="<%=currentUserDTO.getPrenom()%>" name="prenom" /> <br />
				Age:<%=currentUserDTO.getAge()%>
				ans <br /> Identifiant*:<input type="text"
					value="<%=currentUserDTO.getIdentifiant()%>" name="identifiant" />
				<br /> date de naissance*: jj/mm/yyyy:<input id="datenaissancemodif" type="text"
					value="<%=dateUser%>" name="datenaissance" /> mail:<input class="only_email"
					type="text" value="<%=currentUserDTO.getAdresse_mail()%>" name=mail />
			</fieldset>

			<%-- 	<%
				for (CompetenceDTO competenceDTO : currentUserDTO.getCompetences()) {
			%>

			<input type="text" value=<%=competenceDTO.getLibelle()%> name=libellé/>
			<input type="text" value=<%=competenceDTO.getNiveau()%> name=niveau/>
			<%
				}
			%>
			 --%>
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
					name="id_adresse" /> Libellé*:<input class="only_alpha" type="text"
					value="<%=adresseDTO.getLibelle()%>" name="libelle" /><br />
				Ville*:<input class="only_alpha" type="text" value="<%=adresseDTO.getVille()%>"
					name="ville" /><br /> Code postal*:<input class="only_integer"type="text"
					value="<%=adresseDTO.getCode_postal()%>" name="code_postal" /><br />
				<%
					}
				%>
			</fieldset>
			<input type="submit" value="modifier" /><br />
		</form>
	</div>

<div id="supprdialog" title="modifier">
			<form id="formsuppr" method="post"
				action="./ListeUserServlet?UrlParam=suppr">
				<input class="only_integer" id="idusersuppr" type="hidden" value="<%=currentUserDTO.getId()%>" name="idusersuppr" />
				<fieldset>
					<!-- <legend>supprimer un cursus</legend> -->
					vous allez supprimer un cursus
				</fieldset>
				<br /> <input type="submit" value="supprimer" />
			</form>
		</div>

</body>
</html>