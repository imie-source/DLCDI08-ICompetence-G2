<%@page import="org.imie.DTO.UserDTO"%>
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
		<br /> <a href="./AccueilServletClass"><button>Accueil</button> </a> <input
			type="button" value="Ajouter un groupe de travail" id="openermodif" />

		<div id="tableau" class="tableau">
			<%
				List<GroupeDeTravailDTO> listgdt = (List<GroupeDeTravailDTO>) request
						.getAttribute("listgdt");
				Integer i = 1;
				for (GroupeDeTravailDTO gdtDTO : listgdt) {
			%>

			<div id="lignetableau<%=i%>" class="ligneTableau">
				<a href="GroupServletClass?numLigne=<%=i%>">


					<div class="celluleTableau largeur100"><%=i%></div> <%
 	gdtDTO.setNumLigne(0);
 		gdtDTO.setNumLigne(i);
 %>
					<div class="celluleTableau largeur350"><%=gdtDTO.getNom()%></div>
					<div class="celluleTableau largeur350"><%=gdtDTO.getType_projet()%></div>
					<div class="celluleTableau largeur100"><%=gdtDTO.getNomCP()%></div>
				</a>
			</div>



			<div id="contenu<%=i%>" class="contenu">
				<!-- lien à modifier  -->
								<button id="openermodif<%=i%>">modifier</button>
								<button type="submit" value="liste des utilisateurs">volontaires
									du groupe</button>
				<span id="suppr"> <a
					href="./GroupServletClass?UrlParam=supr&chosengdt=<%=i%>"><img
						src="http://www.coeur.net/images_communes/croix_rouge3D.png"
						align="right"></a></span> <span id="userGdt"
					titre="Volontaire du groupe">
					<form id="listUserGdt" method="post" title="Volontaire du groupe"
						action="./GroupServletClass?UrlParam=listUser&gdt=<%=i%>">
				</span>
			</div>

			<%
				i++;
				}
			%>
		</div>


		<div id="formajout" title="ajouter un groupe de travail"
			class="formulaire">
			<form id="ajouterdialog" method="post"
				title="Ajouter un groupe de travail"
				action="./GroupServletClass?UrlParam=creer">

				<fieldset>
					Nom (20 caract max):<input type="text" name="nom" maxlength="20"></input>
					Type (langage, web, client lourd...):<input type="text" name="type"
						maxlength="40"></input>
				</fieldset>

				<br /> <input type="submit" value="ajouter" />
			</form>
		</div>

		<div id="userGdt" titre="Volontaire du groupe">
			<form id="listUserGdt" method="post" title="Volontaire du groupe"
				action="./GroupServletClass?UrlParam=listUser">
				<fieldset></fieldset>
			</form>
		</div>


		<div id="modifierdialog" title="modifier">
			<form id="formmodif" method="post"
				action="./GroupServletClass?UrlParam=modif"
				title="modifier un groupe de travail">
				<input id="idgdtmodif" type="hidden" value="" name="gdtid" />
				<%
					Integer indice = 0;
					String gdtid = null;
					try {
						gdtid = request.getParameter("gdtid");
						if (!gdtid.equals(0)) {
							indice = Integer.parseInt(gdtid);
				%>
				<fieldset>
					Nom (20 caract max):<input type="text" name="nom" maxlength="20"></input>
					Type (langage, web, client lourd...):<input type="text" name="type"
						maxlength="40"></input>
					<%
						GroupeDeTravailDTO chosengdt = listgdt.get(indice);
								System.out.println(chosengdt.getNom());
					%>
					<select title="Etat d'avancement">
						<option>Manque de volontaire</option>
						<option>Démarrage</option>
						<option>En cours</option>
						<option>Terminé</option>
					</select>

					<%
						}
					%>

				</fieldset>
				<%
					} catch (NullPointerException e) {

					}
				%>
				<br /> <input type="submit" value="modifier" />
			</form>
		</div>

	</div>
</body>
</html>