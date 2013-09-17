<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>
	<%-- Récupération du document XML. --%>
 	<c:import url="./XML/fichiertest.xml" varReader="documentXML" /> 
 	ok
<%-- 	<%-- Analyse du document XML récupéré. --%> 
 	<x:parse doc="${documentXML}" var="parsedDoc" />



	
		<%-- Parcours du document parsé pour y récupérer chaque nœud "record". --%>
<%-- 		<x:forEach var="parsedDoc" select="$documentXML/records/"> --%>
<%-- <%-- 				<x:set select="parsedDoc/id" var=$listuser > --%> 
<%-- <%-- 			<c:set var="nom" target="$listuser/nom"  > --%> 
<%-- 				<x:out select="$parsedDoc/@id" /> --%>
<%-- <%-- 				</x:set> --%> 
<%-- <%-- 			</c:set> --%> 
<!-- 			<br />yugyuguguygygui -->
<%-- <%-- 			<c:set var="prenom"> --%>
<%-- <%-- 				<x:out select="$record/prenom" /> --%> 
<%-- <%-- 			</c:set> --%> 
<!-- 			<br /> -->
<%-- 		</x:forEach> --%>


	

</body>
</html>