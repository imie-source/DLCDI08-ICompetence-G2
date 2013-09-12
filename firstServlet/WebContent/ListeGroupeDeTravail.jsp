<%@page import="org.imie.DTO.GroupeDeTravailDTO"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel=stylesheet type=text/css href=./css/cssReset.css>
<link rel=stylesheet type=text/css href=./css/Style.css>
<!-- lien vers la biblio jquery  -->
<link rel="stylesheet" href="./jquery/jquery.ui.theme.css" />
<script src="./jquery/jquery-1.9.1.js"></script>
<script src="./jquery/jquery-ui-1.10.3.custom.js"></script>
<script src="./groupeTravail.js"></script>

<link rel=stylesheet type=text/css href=./css/Style.css>

<title>liste groupe de travail</title>
</head>
<body>
	<div class="conteneur">
	
		<input type="button" value="Ajouter un groupe de travail" id="openerajout" />
		
		<div id="tableau" class=tableau>
			<%
				List<GroupeDeTravailDTO> listgdt = (List<GroupeDeTravailDTO>) request
						.getAttribute("listgdt");
				Integer i = 1;
				for (GroupeDeTravailDTO gdtDTO : listgdt) {
			%>

			<div id="lignetableau<%=i%>" class="ligneTableau">


				<div class="celluleTableau largeur100"><%=i%></div>
				<%gdtDTO.setNumLigne(0);
				gdtDTO.setNumLigne(i);%>
				<div class="celluleTableau largeur350"><%=gdtDTO.getNom()%></div>
				<div class="celluleTableau largeur350"><%=gdtDTO.getType_projet()%></div>
				<div class="celluleTableau largeur100"><%=gdtDTO.getNomCP()%></div>


			</div>
			
			<div id="contenu<%=i%>" class="contenu">
				<!-- lien à modifier  -->
				<button id="openermodif<%=i%>">modifier</button>
				
				<span id="suppr"><a href="./GroupServletClass?UrlParam=supr&chosengdt=<%=i%>"><img src="http://www.coeur.net/images_communes/croix_rouge3D.png" align="right"></a></span>
			</div>
			<%
				i++;
				}
			%>
		</div>

		<div id="ajouterdialog" title="ajouter">
			<form id="formajout" method="post" title="Ajouter un groupe de travail"
				action="./GroupServletClass?UrlParam=creer">
				
				<fieldset>
					Nom (20 caract max):<input type="text" name="nom" maxlength="20"></input>
					Type (langage, web, client lourd...):<input type="text" name="type" maxlength="40"></input>
				</fieldset>
				<br /> <input type="submit" value="ajouter" />
			</form>
		</div>


		<div id="modifierdialog" title="modifier">
			<form id="formmodif" method="post"
				action="./GroupServletClass?UrlParam=modif" title="modifier un groupe de travail">
				<input type="hidden" value="" name="gdtid" />
				<fieldset>
					Nom (20 caract max):<input type="text" name="nom" maxlength="20"></input>
					Type (langage, web, client lourd...):<input type="text" name="type" maxlength="40"></input>
					Etat <input type="select">
					<option>Manque de volontaire</option>
					<option>Démarrage</option>
				</fieldset>
				<br /> <input type="submit" value="modifier" />
			</form>
		</div>
	</div>
</body>
</html>