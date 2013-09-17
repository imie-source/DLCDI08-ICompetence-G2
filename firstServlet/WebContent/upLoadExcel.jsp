<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>File Upload</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
    <jsp:include page="./header.jsp"></jsp:include>
        <form method="POST" action="upload" enctype="multipart/form-data" >
            File:
            <input type="file" name="file" id="file" /> <br/>
           
            <br/>
            <input type="submit" value="Upload" name="upload" id="upload" />
        </form>
    </body>
</html>