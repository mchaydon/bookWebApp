<%-- 
    Document   : authorAdd
    Created on : Feb 22, 2017, 11:29:38 AM
    Author     : Mike
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="language" value="${pageContext.request.locale}" scope="session" />
<fmt:setBundle basename="edu.wctc.mch.bookwebapp.i18n.messages" />
<!DOCTYPE html>
<html lang="${language}">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Add Book</title>
    </head>

    <body>
        <jsp:include page="headerFragment.jsp" />
        <div class="container">
            <h2>Add Book</h2>
            <hr />
            <form name="addBook" method="POST" action="BookController">
                <div class="row">
                    <div class="form-group col-xs-5">
                        <label for="bookName">Book Name</label>
                        <input type="text" class="form-control" id="bookName" name="bookName" placeholder="">
                        <label for="bookIsbn">ISBN</label>
                        <input type="text" class="form-control" id="bookIsbn" name="bookIsbn" placeholder="">
                        <label for="bookName">Book Name</label>
                        <input type="text" class="form-control" id="bookName" name="bookName" placeholder="">
                        <label>Select list</label>
                        <select id="authorList" name="authorList">
                            <c:forEach items="${authors}" var="author">
                                <option value = "${author.authorId}">${author.authorName}</option>
                            </c:forEach>
                        </select>
                        
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-5">
                        <button type="submit" class="btn btn-primary">Cancel</button>
                        <button type="submit" class="btn btn-primary" name="submit" value="addSave">Add</button>
                    </div>
                </div>
            </form>
            <br>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>

</html>
