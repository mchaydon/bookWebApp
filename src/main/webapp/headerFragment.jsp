<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="language" value="${pageContext.request.locale}" scope="session" />
<fmt:setBundle basename="edu.wctc.mch.bookwebapp.i18n.messages" />
<!DOCTYPE html>
<html lang="${language}">
    <header>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="/bookWebApp/index.jsp" style="text-align: center">
                        <img alt="Brand" src="images/logo.png">
                    </a>
                </div>
            </div>
        </nav>
    </header>
</html>