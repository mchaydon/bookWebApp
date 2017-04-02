<%-- 
    Document   : bookList
    Created on : Apr 2, 2017, 2:39:42 AM
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
            <title>Author List</title>
        </head>

        <body>
            <jsp:include page="headerFragment.jsp" />
            <div class="container">
                <h2>Author List</h2>
                <form id="bookTable" name="bookForm" method="POST" action="BookController">
                    <table class="table">
                        <tr>
                            <th>ID</th>
                            <th>Book Name</th>
                        </tr>


                        <c:forEach items="${books}" var="book" varStatus="rowCount">
                            <c:choose>
                                <c:when test="${rowCount.count % 2 == 0}">
                                    <tr style="background-color: white">
                                    </c:when>
                                    <c:otherwise>
                                    <tr style="background-color: #e1faea;">
                                    </c:otherwise>
                                </c:choose>
                                
                            </tr>
                        </c:forEach>
                    </table>
                    <div class="row">
                        <div class="col-xs-5">
                            <button type="submit" class="btn btn-primary" name="submit" value="add">Add</button>
                            <button type="submit" class="btn btn-primary" name="submit" value="edit">Edit</button>
                            <button type="submit" class="btn btn-primary" name="submit" value="delete">Delete</button>
                        </div>
                    </div>
                </form>
            </div>

            <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        </body>

    </html>
