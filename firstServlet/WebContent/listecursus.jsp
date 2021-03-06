<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="com.sun.mail.imap.protocol.Item"%>
<%@page import="org.imie.factory.BaseConcreteFactory"%>
<%@page import="org.imie.service.interfaces.ICursusService"%>
<%@page import="org.imie.service.CursusService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="org.imie.DTO.CursusDTO"%>
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
<script src="./js/listeCursus.js"></script>
<script src="./jquery/jquery.validate.js"></script>
<script src="./js/validationlistecursus.js"></script>
<link rel="stylesheet" type="text/css" href="./css/Style.css"/>
<title>Liste de cursus</title>
</head>
<body>
<jsp:include page="./header.jsp"></jsp:include>
	<div class="conteneur">
		<button id="openerajout">ajouter un cursus</button><br />
		<br /> <a href="./AccueilServletClass">Accueil </a>
		<div id="tableau" class=tableau>
			<%
				List<CursusDTO> listeCursus = (List<CursusDTO>) session
						.getAttribute("listeCursus");
				Integer i = 1;
				for (CursusDTO cursusDTO : listeCursus) {
			%>
			<div id="lignetableau<%=i%>" class="tableau">
					<div class="celluleTableau largeur100 ">
						<%=i%></div>
					<div id="libellecursuschoisie<%=i%>"
						class="celluleTableau largeur350 "><%=cursusDTO.getLibelle()%></div>
					<input id="idcursuschoisie<%=i%>" type="hidden"
					value="<%=cursusDTO.getId()%>" name="cursusid"/>
			</div>
			<div id="contenu<%=i%>" class="contenu">
				<button id="openermodif<%=i%>">modifier</button>
				<button id="openersuppr<%=i%>">supprimer</button>
			</div>
			<%
				i++;
				}
			%>
		</div>


		<div id="ajouterdialog" title="ajouter">
			<form id="formajout" method="post"
				action="./CursusServletClass?UrlParam=creer">
				<fieldset>
					<legend>Ajouter un cursus</legend>
					Libellé*:<input  type="text" name="libelle" maxlength="15" required="required"></input>
				</fieldset>
				<br /> <input type="submit" value="ajouter" />
			</form>
		</div>


		<div id="modifierdialog" title="modifier">
			<form id="formmodif" method="post"
				action="./CursusServletClass?UrlParam=modif">
				<input  id="idcursusmodif" type="hidden" value="" name="cursusid" />
				<fieldset>
					<legend>modifier un cursus</legend>
					Libellé*:<input id="libellecursusmodif" type="text" name="libelle"
						value="" width="15" maxlength="15"></input>
				</fieldset>
				<br /> <input type="submit" value="modifier" />
			</form>
		</div>

		
		<div id="supprdialog" title="supprimer">
			<form id="formsuppr" method="post"
				action="./CursusServletClass?UrlParam=suppr">
				<input id="idcursussuppr" type="hidden" value="" name="cursusid" />
				<fieldset>
					<!-- <legend>supprimer un cursus</legend> -->
					vous allez supprimer un cursus
				</fieldset>
				<br /> <input type="submit" value="supprimer" />
			</form>
		</div>
	</div>
</body>
</html>