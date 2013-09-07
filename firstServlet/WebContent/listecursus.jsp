<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="org.imie.factory.BaseConcreteFactory"%>
<%@page import="org.imie.service.interfaces.ICursusService"%>
<%@page import="org.imie.service.CursusService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="org.imie.DTO.AdresseDTO"%>
<%@page import="org.imie.DTO.CompetenceDTO"%>
<%@page import="org.imie.DTO.CursusDTO"%>
<%@page import="org.imie.DAO.CompetenceDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.imie.DTO.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet type=text/css href=./css/cssReset.css>

	<!-- lien vers la biblio jquery  -->
<link rel="stylesheet" href="./jquery/jquery.ui.theme.css" />
<script src="./jquery/jquery-1.9.1.js"></script>
<script src="./jquery/jquery-ui-1.10.3.custom.js"></script>
<script src="./listecursus.js"></script>
<link rel=stylesheet type=text/css href=./css/Style.css>
<title>Liste de cursus</title>
</head>
<body>
	<div class="conteneur">
		<a href="./AccueilServletClass">ajouter un cursus</a><br />
		<div id="tableaucursus" class=tableau>
			<%
				ICursusService cursusService = BaseConcreteFactory.getInstance()
						.createCursusService(null);
				List<CursusDTO> cursusDTOs = new ArrayList<CursusDTO>();
				cursusDTOs = cursusService.findAll();
				Integer i = 1;
				for (CursusDTO cursusDTO : cursusDTOs) {
			%>
			<div id="lignetableaucursus<%=i%>" class="ligneTableauCursus">
				<div class="celluleTableau largeur100 ">
					<%=i%></div>
				<div class="celluleTableau largeur350 "><%=cursusDTO.getLibelle()%></div>
				
			</div>

			<div id="contenu<%=i%>" class="contenu">
			<a href="./AccueilServletClass">modifier</a>
			<a href="./AccueilServletClass">supprimer</a>
			</div>
			<%
				i++;
				}
			%>
		</div>
	</div>
</body>
</html>