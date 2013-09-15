<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<aside id=menurighttop2> </aside>
		
		
		<div id=menurighttop class="effet1">
			 <a class="texte" href="./NiveauServletClass"><span
				class="stylewhite">NIVEAU</span><br> </a> <span><br>
			<br></span> <a class="texte" href="./ListMotClefServletClass"><span
				class="styleblack"> LISTE DE</span> <span class="stylewhite">MOT
					CLEF</span><br> </a>
		</div>





		<aside class="effet1" id=menurightbottom>
		<div class="effet1">
			<span class="stylewhite">INFO</span><br> <span
				class="stylewhite"> ET</span><br> <span class="styleblack">
				NEWS</span>
		</div>
		</aside>

		<a  href="./ListeUserServlet">
			<div class="effet1" id=squaretopleft>
				<div class="texte">
					<span class="stylewhite">VOIR</span><br> <span
						class="stylewhite"> LES</span><br> <span class="styleblack">
						VOLONTAIRES</span>
				</div>
			</div>
		</a> <a  href="./CursusServletClass">
			<div id=squarebottomleft>
				<div class="texte">
					<span class="stylewhite">VOIR</span><br> <span
						class="stylewhite"> LES</span><br> <span class="styleblack">
						CURSUS</span>
				</div>
			</div>
		</a> <a class=effet1 href="./GroupServletClass">
			<div id=squaretopright>
				<div class="texte">
					<span class="stylewhite">VOIR LES</span><br> <span
						class="styleblack"> GROUPE DE </span><br> <span
						class="stylewhite"> TRAVAIL</span>
				</div>
			</div>
		</a> <a  href="./AfficherArborescence">
			<div id=squarebottomright>
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