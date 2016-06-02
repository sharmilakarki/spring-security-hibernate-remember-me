<%-- 
    Document   : editUser
    Created on : May 22, 2016, 4:49:41 PM
    Author     : sharmila
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../views/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit User Page</title>
        <link href="${URL}/static/css/myForm.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="mdl-layout mdl-layout__content ">
            <c:url var="edit" value="/admin/user/add"/>
            <form action="${edit}"  class="mdl-layout" modelAttribute="userAdd" method="POST" >
                <h6>Edit User</h6>
                <input type="hidden" name="id" value="${user.id}"/>
               

                <div class="mdl-layout mdl-textfield mdl-js-textfield" >
                    <input class="mdl-textfield__input" type="text" id="username" name="userName" value="${user.userName}"/>

                </div>
               
                <div class="mdl-layout mdl-textfield mdl-js-textfield" >
                    <input class="mdl-textfield__input" type="email" id="email" name="email" value="${user.email}"/>
                </div>


                <div class="mdl-layout mdl-textfield mdl-js-textfield" >
                    <input class="mdl-textfield__input" type="text" id="address" name="address" value="${user.address}"/>

                </div>
                <div class="mdl-layout mdl-textfield mdl-js-textfield" >
                    <input class="mdl-textfield__input" type="text" id="status"  name="status" value="${user.status}"/>
                </div>
                <div>
                    <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent" type="submit">
                        Edit
                    </button>
                </div>

            </form>
        </div>
    </body>
</html>
