<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="./jquery/jquery.ui.theme.css" />
<link rel="stylesheet" href="./css/cssReset.css" />
<script src="./jquery/jquery-1.9.1.js"></script>
<script src="./jquery/jquery-ui-1.10.3.custom.js"></script>
<script src="./jquery/jquery.leanModal.min.js"></script>
<link rel="stylesheet" type="text/css" href="./css/Login.css" />

<title>Formulaire connexion</title>
</head>



<body>


	<form id="authentification" method="post" action="./Authentification">
		<h1>Connexion</h1>
		<div id="message"><c:out value="${erreurMessage}"></c:out></div>		
		<a></a>
		<fieldset id="inputs">
			<input id="login" name="login" type="text" placeholder="Identifiant" autofocus required>
			 <input id="password" name="password" type="password" placeholder="Mot de passe" required>
		</fieldset>
		<fieldset id="actions">
			<input type="submit" id="submit" value="Valider"> <a href="">Mot de passe oublié...</a><a href="">S'inscrire</a>
		</fieldset>
	</form>
	
</body>
</html>