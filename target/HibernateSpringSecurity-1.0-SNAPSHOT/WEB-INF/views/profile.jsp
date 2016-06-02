<%-- 
    Document   : profile
    Created on : May 21, 2016, 8:55:39 PM
    Author     : sharmila
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../views/header.jsp" %>
<%@taglib  prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
    </head>
    <body>
        <h1>Hello</h1>
        <sec:authorize access="hasRole('ROLE_USER')">
            <h1>User profile</h1>
            <c:url value="/login?logout" var="logout"/>
                <form action="${logout}" method="POST" id="logoutForm">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
                <script>
                    function formSubmit(){
                        $(document).getElementById("#logoutForm").submit();
                    }
                    
                </script>
                <c:if test="${pageContext.request.userPrincipal.name!=null} ">
                    Hello! ${pageContext.request.userPrincipal.name},
                    <a href="javascript:formSubmit()" class="btn btn-info">Logout</a>
                </c:if>
        </sec:authorize>
    </body>
</html>
