<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel=stylesheet type=text/css href=./css/cssReset.css />
<link rel="stylesheet" href="./jquery/jquery.ui.theme.css" />
<script src="./jquery/jquery-1.9.1.js"></script>
<script src="./jquery/jquery-ui-1.10.3.custom.js"></script>
<script src="./liste.js"></script>
<link rel=stylesheet type=text/css href=./css/Style.css />
<title>Insert title here</title>
</head>
<body>

	<div class="conteneur">
		<div>
			<table>
				<c:forEach var="competence" items="${listecompetence}">

					<c:if test="${competence.niveauParent == 0}">
						<tr>
							<td><c:out value="${competence.libelle}"></c:out> <c:out
									value="${competence.chemin}"></c:out> <c:out
									value="${competence.niveauParent}"></c:out></td>
						</tr>
					</c:if>

					<c:if test="${competence.niveauParent == 1}">
						<tr>
							<td></td>
							<td><c:out value="${competence.libelle}"></c:out> <c:out
									value="${competence.chemin}"></c:out> <c:out
									value="${competence.niveauParent}"></c:out></td>
						</tr>
					</c:if>

					<c:if test="${competence.niveauParent == 2}">
						<tr>
							<td></td>
							<td></td>
							<td><c:out value="${competence.libelle}"></c:out> <c:out
									value="${competence.chemin}"></c:out> <c:out
									value="${competence.niveauParent}"></c:out></td>
						</tr>
					</c:if>

					<c:if test="${competence.niveauParent == 3}">
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><c:out value="${competence.libelle}"></c:out> <c:out
									value="${competence.chemin}"></c:out> <c:out
									value="${competence.niveauParent}"></c:out></td>
						</tr>
					</c:if>

					<br />
				</c:forEach>
			</table>


		</div>
	</div>
</body>
</html>