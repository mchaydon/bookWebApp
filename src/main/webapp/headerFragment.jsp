<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="language" value="${pageContext.request.locale}" scope="session" />
<fmt:setBundle basename="edu.wctc.mch.bookwebapp.i18n.messages" />
<!DOCTYPE html>
<html lang="${language}">
    <nav class="navbar navbar-inverse navbar-static-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="index.html">
                    <span class="glyphicon glyphicon-home" aria-hidden="true"></span> <fmt:message key="page.header.title"/>
                </a>
            </div>
        </div>
    </nav>
</html>