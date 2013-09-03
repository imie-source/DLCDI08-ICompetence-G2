<?xml version="1.0" encoding="UTF-8" ?>
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
				CursusDTO cursusDTO = currentUserDTO.getCursus();
				String cursusUser = null;
				if (cursusDTO != null) {
					cursusUser = cursusDTO.getLibelle();
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
			<a href=./UpdateServletClass>modifier</a> 
			<a href=./DeleteServletClass>supprimer</a> 
				<!-- <a href=./AccueilServletClass>retour à la liste</a> -->
		</div>
	</div>
</body>
</html>