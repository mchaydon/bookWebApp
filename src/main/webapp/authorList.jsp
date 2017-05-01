<%-- 
    Document   : authorList
    Created on : Feb 7, 2017, 9:26:37 PM
    Author     : Mike
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
                <form id="authorTable" name="rectForm" method="POST" action="AuthorController">
                    <table class="table">
                        <tr>
                            <th>ID</th>
                            <th>Author Name</th>
                            <th>Date Added</th>
                        </tr>


                        <c:forEach items="${authors}" var="author" varStatus="rowCount">
                            <tr id="${author.authorId}">
                                <td>
                                    <label><input type="radio" id="authorSelected" class="authorSelected" name="authorSelected" value="${author.authorId}"> <c:out value="${author.authorId}" /></label>
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
                    <div class="row" style="padding-bottom: 1em;">
                        <div class="col-xs-5">
                            <button type="submit" class="btn btn-primary" name="submit" value="add">Add</button>
                            <button type="submit" class="btn btn-primary" name="submit" value="edit">Edit</button>
                            <button type="button" class="btn btn-primary" name="submit" id="authorDelete" value="delete">Delete</button>
                        </div>
                    </div>
                </form>
                <div class="container-fluid">
                    <div class="row" style="padding-bottom: 1em; float:right; text-align: right;">
                        <p>Visitor Count: ${counter}</p>
                        <p>Current Time: ${date}</p>
                    </div>
                </div>
            </div>

            <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
            <script src="js/main.js"></script>
        </body>

    </html>