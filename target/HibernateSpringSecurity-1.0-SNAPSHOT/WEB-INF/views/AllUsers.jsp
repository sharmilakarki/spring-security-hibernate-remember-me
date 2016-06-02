<%-- 
    Document   : AllUsers
    Created on : May 22, 2016, 12:38:46 PM
    Author     : sharmila
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../views/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All User Page</title>
        <link href="${URL}/static/css/myForm.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="pull-left">
            <p>
                <a href="#" class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--colored"></a>
            </p>
        </div>
        <div class="container">
            <table class="mdl-data-table mdl-js-data-table mdl-data-table-selectable mdl-shadow--2dp" id="tblData" align="center">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>USER NAME</th>
                        <th>EMAIL</th>
                        <th>ADDRESS</th>                   
                        <th>ADDED DATE</th>                   
                        <th>MODIFIED DATE</th>
                        <th>STATUS</th>
                        <th>ACTION</th>
                    </tr>
                </thead>
                <tbody>


                    <c:forEach var="user" items="${user}">
                        <tr>
                            <td class="mdl-data-table__cell--non-numeric">${user.id}</td>
                            <td class="mdl-data-table__cell--non-numeric">${user.userName}</td>
                            <td class="mdl-data-table__cell--non-numeric">${user.email}</td>
                            <td class="mdl-data-table__cell--non-numeric">${user.address}</td>

                            <td class="mdl-data-table__cell--non-numeric">${user.addedDate}</td>
                            <td class="mdl-data-table__cell--non-numeric">${user.modifiedDate}</td>
                            <td class="mdl-data-table__cell--non-numeric">${user.status}</td>

                            <td class="mdl-data-table__cell--non-numeric"><a href="${URL}/edit?id=${user.id}" class="btn btn-sm btn-success"><span class="glyphicon glyphicon-edit">Edit  </span></a>
                                &nbsp;&nbsp;
                                <a href="${URL}/delete?id=${user.id}" id="deleteBtn" class="btn btn-sm btn-danger" onclick="return check();"><span class="glyphicon glyphicon-remove">Delete</span></a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>

            </table>
        </div>
    </body>
</html>
