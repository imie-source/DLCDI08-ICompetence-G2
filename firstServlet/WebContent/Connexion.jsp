<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="./jquery/jquery.ui.theme.css" />
<link rel="stylesheet" href="./css/cssReset.css" />
<script src="./jquery/jquery-1.9.1.js"></script>
<script src="./jquery/jquery-ui-1.10.3.custom.js"></script>
<script src="./jquery/jquery.leanModal.min.js"></script>
<link rel="stylesheet" type="text/css" href="./css/Style.css" />
<title>Formulaire connexion</title>
</head>



<body>
	<form method="post" action="./Authentification">
		<input type="text" name="login"> </input> <input type="password"
			name="password"> </input> <input type="submit" name="valider"
			value="Valider"> </input>
	</form>

</body>
</html>