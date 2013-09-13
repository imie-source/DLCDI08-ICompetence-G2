<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="com.sun.mail.imap.protocol.Item"%>

<%@page import="org.imie.factory.BaseConcreteFactory"%>
<%@page import="org.imie.service.interfaces.IMotClefService"%>
<%@page import="org.imie.service.MotClefService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="org.imie.DTO.MotClefDTO"%>

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
<script src="./liste.js"></script>
<link rel=stylesheet type=text/css href=./css/Style.css>
<title>Liste mot clef</title>
</head>
<body>
	<div class="conteneur">

		<button id="openerajout">ajouter un mot clef</button>

		<br /> <br /> <a href="./AccueilServletClass">Accueil </a>
		<div id="tableau" class=tableau>
			<%
				List<MotClefDTO> listeMotClef = (List<MotClefDTO>) session
						.getAttribute("listeMotClef");
				Integer i = 1;
				for (MotClefDTO motClefDTO : listeMotClef) {
			%>

			<div id="lignetableau<%=i%>" class="ligneTableau">

				<div class="celluleTableau largeur100 ">
					<%=i%></div>
				<div class="celluleTableau largeur350 "><%=motClefDTO.getLibelle()%></div>

			</div>
			<div id="contenu<%=i%>" class="contenu">

				<!-- lien à modifier  -->
				<button id="openmodif<%=i%>">modifier</button>

				<div id="modifierdialog" title="formulaire">
					<form id="formodif" method="post" title="Modifier un mot clef"
						action="./ListMotClefServletClass?UrlParam=modif">
						<fieldset>
							<legend>Modifier le mot clef</legend>
							<input type="text" name="motClefid" value=<%=motClefDTO.getId()%>  maxlength="25"></input>
							Libellé*:<input type="text" name="libelle" value=<%=motClefDTO.getLibelle() %> maxlength="25"></input>
						</fieldset>
						<br /> <input type="submit" value="modifier" />
					</form>
				</div>


				<button id="openersupr<%=i%>">supprimer</button>

			</div>
			<%
				i++;
				}
			%>
		</div>

		<div id="ajouterdialog" title="formulaire">
			<form id="formajout" method="post" title="Ajouter un mot clef"
				action="./ListMotClefServletClass?UrlParam=creer">
				<fieldset>
					<legend>Ajouter un mot clef</legend>
					Libellé*:<input type="text" name="libelle" maxlength="25"></input>
				</fieldset>
				<br /> <input type="submit" value="ajouter" />
			</form>
		</div>


	</div>
</body>
</html>