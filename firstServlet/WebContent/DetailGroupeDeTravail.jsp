<%@page import="org.imie.DTO.UserDTO"%>
<%@page import="org.imie.DTO.GroupeDeTravailDTO"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />


<title></title>
</head>
<body>

	<% GroupeDeTravailDTO gdtDTO = (GroupeDeTravailDTO) request.getAttribute("chosengdt");
List<UserDTO> listUserDTO = gdtDTO.getListUserDTO();
 
for (UserDTO userDTO : listUserDTO) {
 	%><div id="lignetableau" class="ligneTableau">
 	<div class="celluleTableau largeur350"><%=userDTO.getPrenom()%></div>
 	<div class="celluleTableau largeur350"><%=userDTO.getNom()%></div>
 	<div class="celluleTableau largeur100"><%=userDTO.getIdentifiant()%></div><%}%>
 		
 		<button id="openermodif">modifier</button>
				

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