<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>File Upload</title>
<link rel="stylesheet" href="./jquery/jquery.ui.theme.css" />
<link rel="stylesheet" href="./css/cssReset.css" />
<script src="./jquery/jquery-1.9.1.js"></script>
<script src="./jquery/jquery-ui-1.10.3.custom.js"></script>
<script src="./jquery/jquery.leanModal.min.js"></script>
<link rel="stylesheet" type="text/css" href="./css/Login.css" />

</head>
<body>
	<jsp:include page="./header.jsp"></jsp:include>
	<form id="authentification" method="post" action="upload"
		enctype="multipart/form-data">
		<h1>Import utilisateurs </h1></br>
			(n'utilisez que les fichiers xls
			conformes voir document utilisateur)
		<fieldset id="inputs">
			<input type="file" name="file" id="file" /> <br /> <br />
		</fieldset>
		<fieldset id="actions">
			<input type ="submit" value="Upload" name="upload" id="upload" />
		</fieldset>

	</form>
</body>
</html> 