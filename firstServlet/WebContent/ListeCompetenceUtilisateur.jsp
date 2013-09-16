<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel=stylesheet type=text/css href=./css/cssReset.css />

<!-- lien vers la biblio jquery  -->
<link rel="stylesheet" href="./jquery/jquery.ui.theme.css" />
<script src="./jquery/jquery-1.9.1.js"></script>
<script src="./jquery/jquery-ui-1.10.3.custom.js"></script>
<script src="./js/liste.js"></script>

<link rel="stylesheet" type="text/css" href="./css/Style.css" />

<title>Liste des compétences</title>
</head>
<body>

	<h1>Liste des compétences</h1>
	<div class="conteneur">
<br /> <br /> <a href="./AccueilServletClass">Accueil </a>

		<c:forEach var="competence" items="${listecompetence}">
				<c:out value="${competence.id}"></c:out>
				<c:out value="${competence.libelle}"></c:out>
				<br />
		</c:forEach>
	</div>
</body>
</html>