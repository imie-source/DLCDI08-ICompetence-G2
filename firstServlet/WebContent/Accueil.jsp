<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="./css/cssReset.css" />
<!-- lien vers la biblio jquery  -->
<link rel="stylesheet" href="./jquery/jquery.ui.theme.css" />
<script src="./jquery/jquery-1.9.1.js"></script>
<script src="./jquery/jquery-ui-1.10.3.custom.js"></script>
<script src="./js/contenuaccueil.js"></script>
<link rel="stylesheet" type="text/css" href="./css/contenuaccueil.css" />



<title>Accueil</title>
</head>
<body>
	<jsp:include page="./header.jsp"></jsp:include>
	
	<div id=textconnect>
		<p>Bonjour
			<c:out value="${user.getPrenom()}"></c:out>
			<c:out value="${user.getNom()}"></c:out>
		<p>
			Vous êtes connecté avec le profil de niveau :
			<c:out value="${user.getProfil()}"></c:out>
		</p>
		<c:set var="niveau" scope="page" value="${user.getProfil()}" />
	</div>
	
	
	
	<div id=contenuaccueil>

<%-- 		<c:if test="${niveau == 3}"> --%>


		<a id="zonemenurighttop" href="./ListeUserServlet">
		
		<div id=menurighttop>
			 <a id="zone1" class="texte"><span
				class="stylewhite">NIVEAU</span></a><br>  <span><br>
			<br></span> <a id="zone2" class="texte" href="./ListMotClefServletClass"><span
				class="styleblack"> LISTE DE</span> <span class="stylewhite">MOT
					CLEF</span></a><br> <span><br>
			<br></span>
					 <a id="zone3" class="texte" href="./FileUpLoad"><span
				class="stylewhite">IMPORT</span><br> </a> 
		</div>

			<div id="menurighttop">


				<a id="zone1" class="texte"><span
					class="stylewhite">NIVEAU</span><br> </a> <span><br> <br></span>
				<a id="zone2" class="texte" href="./ListMotClefServletClass"><span
					class="styleblack"> LISTE DE</span> <span class="stylewhite">MOT
						CLEF</span><br> </a>

			</div>
		</a>
<%-- 		</c:if> --%>




		<a id="zonesmenurightbottom">
			<div id="menurightbottom1">
				<div class="texte1">
					<span class="stylewhite">LE PROJET</span><br>
					<span class="styleblack">DE L'ECOLE</span><br>
					<span class="styleblack">IMIE</span>
				</div>
				
				<div class="texte2">

					<span class="stylewhite">L'application de gestion</span><br> 
					<span class="stylewhite"> des compétences</span><br> 
					<span class="stylewhite"> permet à l'ensemble</span><br>
					<span class="stylewhite"> des étudiants volontaires,</span><br>
					<span class="styleblack"> dans le projet école en cours d'étude,</span><br>
					<span class="stylewhite"> de se positionner sur les compétences</span><br>
					<span class="stylewhite"> qu'ils possèdent et qui seront </span><br>
					<span class="stylewhite"> nécessaire à la réalisation de</span><br>
					<span class="stylewhite"> ce projet école.</span><br>

				</div>
			</div>
			<div id="menurightbottom2">
				<div class="texte">


					<span class="stylewhite">INFO</span><br> <span
						class="stylewhite"> ET</span><br> <span class="styleblack">
						NEWS</span>


				</div>
			</div>
		</a> 
		
		<a id="zonesquaretopleft" href="./ListeUserServlet">
			<div id="squaretopleft1">
				<div class="texte">
					<span class="stylewhite">AUTRE</span><br> <span
						class="styleblack"> TEXTE</span>
				</div>
			</div>

			<div id="squaretopleft2">
				<div class="texte">
					<span class="stylewhite">VOIR</span><br> <span
						class="stylewhite"> LES</span><br> <span class="styleblack">
						VOLONTAIRES</span>
				</div>
			</div>
		</a> 
		
		<a id="zonesquarebottomleft" href="./CursusServletClass">

			<div id="squarebottomleft1">
				<div class="texte">
					<span class="stylewhite">AUTRE</span><br> <span
						class="styleblack"> TEXTE</span>
				</div>
			</div>

			<div id="squarebottomleft2">
				<div class="texte">
					<span class="stylewhite">VOIR</span><br> <span
						class="stylewhite"> LES</span><br> <span class="styleblack">
						CURSUS</span>
				</div>
			</div>
		</a> <a id="zonesquaretopright" href="./GroupServletClass">
			<div id="squaretopright1">
				<div class="texte">
					<span class="stylewhite">AUTRE</span><br> <span
						class="styleblack"> TEXTE</span>
				</div>
			</div>

			<div id="squaretopright2">
				<div class="texte">
					<span class="stylewhite">VOIR LES</span><br> <span
						class="styleblack"> GROUPE DE </span><br> <span
						class="stylewhite"> TRAVAIL</span>
				</div>
			</div>
		</a> <a id="zonesquarebottomright" href="./AfficherArborescence">
			<div id="squarebottomright1">
				<div class="texte">
					<span class="stylewhite">AUTRE</span><br> <span
						class="styleblack"> TEXTE</span>
				</div>
			</div>

			<div id="squarebottomright2">
				<div class="texte">
					<span class="stylewhite">VOIR</span><br> <span
						class="stylewhite"> LES</span><br> <span class="styleblack">
						COMPETENCES</span>
				</div>
			</div>
		</a>


	</div>
	
</body>
</html>