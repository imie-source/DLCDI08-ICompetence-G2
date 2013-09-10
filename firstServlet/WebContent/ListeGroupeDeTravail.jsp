<%@page import="org.imie.DTO.GroupeDeTravailDTO"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel=stylesheet type=text/css href=./css/cssReset.css>

<!-- lien vers la biblio jquery  -->
<link rel="stylesheet" href="./jquery/jquery.ui.theme.css" />
<script src="./jquery/jquery-1.9.1.js"></script>
<script src="./jquery/jquery-ui-1.10.3.custom.js"></script>
<script src="./js.js"></script>

<link rel=stylesheet type=text/css href=./css/Style.css>

<title>liste groupe de travail</title>
</head>
<body>
	<div class="conteneur">
	
		<input type="button" value="Ajouter un groupe de travail" class="creerGdt" />
		
		<div id="tableau" class=tableau>
			<%
				List<GroupeDeTravailDTO> listgdt = (List<GroupeDeTravailDTO>) request
						.getAttribute("listgdt");
				Integer i = 1;
				for (GroupeDeTravailDTO gdtDTO : listgdt) {
			%>

			<div id="lignetableau<%=i%>" class="ligneTableau">


				<div class="celluleTableau largeur100">
					<%=i%></div>
				<div class="celluleTableau largeur350"><%=gdtDTO.getNom()%></div>
				<div class="celluleTableau largeur350"><%=gdtDTO.getType_projet()%></div>
				<div class="celluleTableau largeur100"><%=gdtDTO.getNomCP()%></div>


			</div>
			
			<div id="contenu<%=i%>" class="contenu">
				<!-- lien à modifier  -->
				<button id="openermodif<%=i%>">modifier</button>
				<a href="./CursusServletClass?UrlParam=supr">supprimer</a>
			</div>
			<%
				i++;
				}
			%>
		</div>

		<div id="ajouterdialog" title="ajouter">
			<form id="formajout" method="post"
				action="./GroupServletClass?UrlParam=creer">
				<fieldset>
					<legend>Ajouter un groupe de travail</legend>
					Libellé*:<input type="text" name="libelle" maxlength="15"></input>
				</fieldset>
				<br /> <input type="submit" value="ajouter" />
			</form>
		</div>


		<div id="modifierdialog" title="modifier">
			<form id="formmodifgdt" method="post"
				action="./GroupServletClass?UrlParam=modif">
				<input type="hidden" value="" name="gdtid" />
				<fieldset>
					<legend>modifier un groupe de travail</legend>
					Libellé*:<input type="text" name="libelle" value="" width="15"
						maxlength="15"></input>
				</fieldset>
				<br /> <input type="submit" value="modifier" />
			</form>
		</div>
	</div>
</body>
</html>