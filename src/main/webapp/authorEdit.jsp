<%-- 
    Document   : authorEdit
    Created on : Feb 22, 2017, 11:29:38 AM
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
        <title>Edit Author</title>
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
            <h2>Add Author</h2>
            <form name="addAuthor" method="POST" action="AuthorController">
                <div class="row">
                    <div class="form-group col-xs-5">
                        <label for="authorId">Author ID</label>
                        <input type="text" class="form-control" id="authorId" name="authorId" value="${author_id}" readonly="readonly">
                        <label for="authorName">Author Name</label>
                        <input type="text" class="form-control" id="authorName" name="authorName" value="${author_name}">
                        <label for="authorDate">Date Added</label>
                        <input type="text" class="form-control" id="authorDate" name="authorDate" value="${date_added}" disabled>
                    </div>
                </div>
                <div class="row">
                    <button type="submit" class="btn btn-primary">Cancel</button>
                    <button type="submit" class="btn btn-primary" name="submit" value="editSave">Save</button>
                </div>
            </form>
            <br>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>

    </html>
