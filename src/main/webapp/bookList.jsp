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
            <title>Book List</title>
        </head>

        <body>
            <jsp:include page="headerFragment.jsp" />
            <div class="container">
                <h2>Book List</h2>
                <form id="bookTable" name="bookForm" method="POST" action="BookController">
                    <table class="table">
                        <tr>
                            <th>ID</th>
                            <th>Book Name</th>
                            <th>ISBN</th>
                            <th>Author</th>
                        </tr>


                        <c:forEach items="${books}" var="book" varStatus="rowCount">
                                <td>
                                    <label><input type="radio" id="bookSelected" name="bookSelected" value="${book.bookId}"> <c:out value="${book.bookId}" /></label>
                                </td>
                                <td>
                                    <c:out value="${book.title}" />
                                </td>
                                <td>
                                    <c:out value="${book.isbn}" />
                                </td>
                                <td>
                                    <c:out value="${book.getAuthorId().authorName}" />
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div class="row" style="padding-bottom: 1em;">
                        <div class="col-xs-5">
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
