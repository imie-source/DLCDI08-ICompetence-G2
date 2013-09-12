<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.ArrayList"%>
<%@page import="org.imie.factory.BaseConcreteFactory"%>
<%@page import="org.imie.service.interfaces.ICursusService"%>
<%@page import="org.imie.DTO.CursusDTO"%>
<%@page import="java.util.List"%>
<%@page import="org.imie.DTO.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel=stylesheet type=text/css href=./css/cssReset.css>

	<!-- lien vers la biblio jquery  -->
	<link rel="stylesheet" href="./jquery/jquery.ui.theme.css" />
	<script src="./jquery/jquery-1.9.1.js"></script>
	<script src="./jquery/jquery-ui-1.10.3.custom.js"></script>
	<script src="./liste.js"></script>



	<link rel=stylesheet type=text/css href=./css/Style.css>

		<title>liste utilisateur</title>
</head>
<body>
	<div class="conteneur">
		<button id="openerajout">ajouter un utilisateur</button>
		<br /> <a href="./AccueilServletClass">Accueil </a>

		<div id="tableau" class=tableau>
			<%
				List<UserDTO> listUser = (List<UserDTO>) session
						.getAttribute("listUser");
				Integer i = 1;
				for (UserDTO userDTO : listUser) {
			%>

			<div id="lignetableau<%=i%>" class="ligneTableau">

				<a href="./ListeUserServlet?ligne=<%=i%>" title="plus d'info">
					<div class="celluleTableau largeur100">
						<%=i%></div>
					<div class="celluleTableau largeur350"><%=userDTO.getNom()%></div>
					<div class="celluleTableau largeur350"><%=userDTO.getPrenom()%></div>
					<div class="celluleTableau largeur100"><%=userDTO.getAge()%>ans
					</div>
				</a>
			</div>
			<div id="contenu<%=i%>" class="contenu"></div>
			<%
				i++;
				}
			%>
		</div>

		<div id="ajouterdialog" class="formulaire">
			<form id="formajout" method="post"
				action="./ListeUserServlet?UrlParam=creer">
				<fieldset>
					<legend>Coordonnées</legend>
					Nom*:<input id="nom" type="text" name="nom" maxlength="25" /> <br />
					Prénom*:<input id="prenom" type="text" name="prenom" maxlength="20" />
					<br /> date de naissance* jj/mm/yyyy:<input id="date_naissance"
						type="text" name="date_naissance" /> <br /> mail:<input
						id="mail" type="text" name="adresse_mail" maxlength="40" /> <br />
					Identifiant*:<input id="identifiant" type="text" name="identifiant"
						maxlength="20" /> <br /> Mot de passe*:<input id="mot_de_passe"
						type="text" name="pwd" maxlength="8" /> <br />
				</fieldset>
				<fieldset>
					<legend>Adresse</legend>

					Libellé*:<input type="text" name="libelle" maxlength="20"></input><br />
					Ville*:<input type="text" name="ville" maxlength="20"></input><br />
					Code postal*:<input class="only_zip_code" type="text" name="code_postal" maxlength="5"></input><br />


					<!--<fieldset> 	
			<legend>Competence</legend>
			Libellé : <input type="text" name="libellé"/><br /> Niveau :
			<input type="text" name="niveau"/><br /> 
			</fieldset>-->
				</fieldset>
				<fieldset>
					<legend>Cursus*</legend>
					<%
						ICursusService cursusService = BaseConcreteFactory.getInstance()
								.createCursusService(null);
						List<CursusDTO> cursusDTOs = new ArrayList<CursusDTO>();
						cursusDTOs = cursusService.findAll();
					%>

					<select name="cursusid">
						<%
							for (CursusDTO cursusDTO : cursusDTOs) {
						%>
						<option value="<%=cursusDTO.getId()%>">
							<%=cursusDTO.getLibelle()%>
						</option>
						<%
							}
						%>
					</select>
				</fieldset>
				<fieldset>
					<legend>Etes vous disponible?</legend>
					<br /> <input type="radio" checked="checked" name="disponible"
						value="true" /> oui <input type="radio" name="disponible"
						value="false" />non<br />
				</fieldset>
				<br /> <input id="validationformajout" type="submit"
					value="ajouter" />
			</form>
		</div>
		
	</div>
</body>
</html>