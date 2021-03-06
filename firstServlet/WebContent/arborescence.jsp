<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel=stylesheet type=text/css href=./css/cssReset.css />
<link rel="stylesheet" href="./jquery/jquery.ui.theme.css" />
<script src="./jquery/jquery-1.9.1.js"></script>
<script src="./jquery/jquery-ui-1.10.3.custom.js"></script>
<script src="./js/liste.js"></script>
<link rel="stylesheet" type="text/css" href="./css/Style.css" />
<title>Insert title here</title>
</head>
<body>

	<div class="conteneurcomp">
		<br /> <br /> <a href="./AccueilServletClass">Accueil </a>
		<div class="titre1">Liste des competences</div>
		<br /> <br />

		<div class="tableaucentre">
			Chercher une compétence :
			<form method="post" action="./AdminCompetenceServletClass">
				<input type="text" name="competencerecherchee" /> <input
					type="submit" value="Chercher" />
			</form>
			<br /> <br />
			<div>
				<c:forEach var="competence" items="${listecompetence}">
					<div class="ligneTableau"
						style="padding-left:<c:out value="${competence.niveauParent * 100}"></c:out>px">
						<c:out value="${competence.id}"></c:out>
						<c:out value="${competence.libelle}"></c:out>

						<form id="formscomp" method="post" action="./AfficherArborescence">

							<input id="idcomp" type="hidden"
								value="<c:out value="${competence.id}"></c:out>" name="idcomp" />
								
							<input id="chemincomp" type="hidden"
								value="<c:out value="${competence.chemin}"></c:out>"
								name="chemincomp" /> 
								
								<input id="niveauParentcomp" type="hidden"
								value="<c:out value="${competence.niveauParent}"></c:out>"
								name="niveauParentcomp" /> 
								
								
								
								<input type="submit" name= "supprimer" value="supprimer" /> 
								<input type="submit" name= "ajouter" value="ajouter une compétence" /> 
								<input type="submit" name= "modifier" value="modifier" />
								<input id="libelle" type="text"
								value="<c:out value="${competence.libelle}"></c:out>"
								name="libelle" /> 

						</form>
					</div>
					<br />
				</c:forEach>
			</div>
		</div>
</body>
</html>