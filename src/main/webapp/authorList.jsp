<%-- 
    Document   : authorList
    Created on : Feb 7, 2017, 9:26:37 PM
    Author     : Mike
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Author List</title>
    </head>

    <body>
        <nav class="navbar navbar-inverse navbar-static-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="index.html">
                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    </a>
                </div>
            </div>
        </nav>
        <div class="container">
            <h2>Author List</h2>
            <table class="table table-condensed">
                <tr>
                    <th></th>
                    <th>ID</th>
                    <th>Author Name</th>
                    <th>Date Added</th>
                </tr>


                <c:forEach items="${authors}" var="author" varStatus="rowCount">
                    <c:choose>
                        <c:when test="${rowCount.count % 2 == 0}">
                            <tr style="background-color: white">
                        </c:when>
                        <c:otherwise>
                            <tr style="background-color: #e1faea;">
                        </c:otherwise>
                    </c:choose>
                    <td>
                        <div class="checkbox">
                            <label><input type="checkbox" value="${author.authorId}"></label>
                        </div>
                    </td>
                    <td>
                        <c:out value="${author.authorId}" />
                    </td>
                    <td>
                        <c:out value="${author.authorName}" />
                    </td>
                    <td>
                        <fmt:formatDate pattern="MM/dd/yyyy" value="${author.dateAdded}" />
                    </td>
                    </tr>
                </c:forEach>
            </table>
            <div class="row">
                <a href="AuthorController?action=addAuthor"><button type="button" class="btn btn-primary col-xs-1 col-xs-offset-1">Add</button></a>
                <button type="button" class="btn btn-primary col-xs-1 col-xs-offset-1">Delete</button>
            </div>
            <br>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>

    </html>