<%-- 
    Document   : index
    Created on : Apr 9, 2017, 5:39:39 PM
    Author     : Mike
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <title>Home</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="style.css">
</head>

<body>
    <jsp:include page="headerFragment.jsp" />
    <div class="container">
        <h2>Admin Console</h2>
        <hr />
        <h3>
            <!-- Use <%= response.encodeURL("AuthorController") %> when page updated to jsp -->
            <p>
                <a href="AuthorController" style="text-decoration:none;">Author List</a>
            </p>
            <p>
                <a href="BookController" style="text-decoration:none;">Book List</a>
            </p>
            
        </h3>
        <br>
        <br> 
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>
