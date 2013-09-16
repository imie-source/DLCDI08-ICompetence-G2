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
<link rel=stylesheet type=text/css href=./css/sources/Style.css>
<script src="./jquery/jquery-1.9.1.js"></script>
<script src="./jquery/jquery-ui-1.10.3.custom.js"></script>
<script src="./js/groupeTravail.js"></script>
<title></title>
</head>
<body>
<div class="celluleTableau largeur350">Prenom :</div>
 	<div class="celluleTableau largeur350">Nom :</div>
 	<div class="celluleTableau largeur100">Identifiant :</div><br /><br />
	<% GroupeDeTravailDTO gdtDTO = (GroupeDeTravailDTO) request.getAttribute("chosengdt");
List<UserDTO> listUserDTO = gdtDTO.getListUserDTO();
 Integer i = 0;
 session.setAttribute("listUserDTO", listUserDTO);
for (UserDTO userDTO : listUserDTO) {%>
	
	
 	<div class="celluleTableau largeur350"><%=i%>. <%= userDTO.getPrenom()%></div>
 	<div class="celluleTableau largeur350"><%=userDTO.getNom()%></div>
 	<div class="celluleTableau largeur100"><%=userDTO.getIdentifiant()%></div>
 	<span id="suppr"> <a
					href="./GroupServletClass?UrlParam=suprUser&chosenUser=<%=i%>"><img
						src="http://www.coeur.net/images_communes/croix_rouge3D.png"
						align="right"></a></span>
 	
 	<% 
 	i++;
 	}%>
 		<br />
 		<input
			type="button" value="modifier groupe de travail" id="openermodif" />
				

	
 
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
					//	GroupeDeTravailDTO chosengdt = listgdt.get(indice);
					//			System.out.println(chosengdt.getNom());
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




</body>
</html>