<%-- 
    Document   : Signup
    Created on : May 26, 2016, 9:53:21 AM
    Author     : sharmila
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../views/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Signup Page</title>
        <link href="${URL}/static/css/myForm.css" rel="stylesheet" type="text/css"/>
        <script src="${URL}/static/extraJS/FormValidation.js" type="text/javascript"></script>
    </head>
    <body>
        <script>
            var un = document.getElementById("username");
            var pa = document.getElementById("password");
            var pa2 = document.getElementById("password2");
            var em = document.getElementById("email");
            var add = document.getElementById("address");
            var p = document.getElementById("para");

            function checkVal(form) {
                re = /^\w+$/;
                if (!re.test(form.username.value)) {
                    alert("Error: Username must contain only letters, numbers and underscores!");
                    form.username.focus();
                    return false;
                }
                if((form.password.value!=form.password2.value)){
                    alert("Error: The password should be matching ");
                    form.password.focus();
                    return false;
                }
            }
        </script>
        <div class="addUserForm">
            <p id="para"></p>
            <c:url var="add" value="/admin/user/add"/>
            <form action="${add}"  name="signupForm" class="mdl-layout" modelAttribute="userAdd" method="POST" onsubmit="return checkVal(this);">
                <h6>Register User</h6>
                <div class="mdl-layout mdl-textfield mdl-js-textfield" >
                    <label class="mdl-textfield__label" for="userName">Username</label>
                    <input class="mdl-textfield__input" type="text" id="username" name="userName" required="true"/>
                </div>

                <div class="mdl-layout mdl-textfield mdl-js-textfield" >
                    <label class="mdl-textfield__label" for="password">password</label>
                    <input class="mdl-textfield__input" type="password" id="password" name="password" required="true"/>
                </div>
                <div class="mdl-layout mdl-textfield mdl-js-textfield" >
                    <label class="mdl-textfield__label" for="password2">password</label>
                    <input class="mdl-textfield__input" type="password" id="password2" required="true"/>
                </div>

                <div class="mdl-layout mdl-textfield mdl-js-textfield" >
                    <label class="mdl-textfield__label" for="email">email</label>
                    <input class="mdl-textfield__input" type="email" id="email" name="email" required="true"/>
                </div>

                <div class="mdl-layout mdl-textfield mdl-js-textfield" >
                    <label class="mdl-textfield__label" for="address">address</label>
                    <input class="mdl-textfield__input" type="text" id="address" name="address" required="true"/>
                </div>



                <div class="mdl-layout mdl-textfield mdl-js-textfield" >
                    <label class="mdl-textfield__label" for="status">status</label>
                    <input class="mdl-textfield__input" type="checkbox" id="status" value="1" name="status" required="true"/>
                </div>
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>

                <div>
                    <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent" >
                        Add
                    </button>
                </div>

            </form>
        </div>

    </body>
</html>
