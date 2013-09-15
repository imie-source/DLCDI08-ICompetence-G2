<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="./css/cssReset.css"/>
<link rel="stylesheet" type="text/css" href="./css/contenuaccueil.css"/>



<title>Accueil</title>
</head>
<body>
	

<p>Bonjour </p>
	<c:out value="${user.getNom()}"></c:out>
	<p>Vous êtes connecté avec le profil de niveau : <c:out value="${user.getProfil()}"></c:out></p>
	<div id=contenuaccueil>
	<c:if test="${user.getProfil()=='3'}">
	<div id=menuright>
		<ul>
			<li><a href="./NiveauServletClass">niveau </a></li>
			<li><a href="./ListMotClefServletClass">liste de mot clef </a></li>
		</ul>
	</div>
</c:if>
		<a href="./ListeUserServlet">
			<div id=squaretopleft>
				<div>Liste volontaire</div>
			</div>
		</a> <a href="./CursusServletClass">
			<div id=squarebottomleft>
				<div>cursus</div>
			</div>
		</a> <a href="./GroupServletClass">
			<div id=squaretopright>
				<div>groupe de travail</div>
			</div>
		</a> <a href="./AfficherArborescence">
			<div id=squarebottomright>
				<div>Competence</div>
			</div>
		</a>
	</div>

	
</body>
</html>