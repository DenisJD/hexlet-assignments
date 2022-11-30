<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<!DOCTYPE html>
<html>
    <head>
        <title>Users</title>
        <meta charset="UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
        crossorigin="anonymous">
    </head>
    <body>
        <h5>\\User card//</h3>
            <h6><b>User ID: </b>${user.get("id")}</h6>
            <h6><b>Firstname: </b>${user.get("firstName")}</h6>
            <h6><b>Lastname: </b>${user.get("lastName")}</h6>
            <h6><b>Email: </b>${user.get("email")}</h6>
            <hr>
            <a href="/users/delete?id=${user.get("id")}">Удалить пользователя</a>
    </body>
</html>
<!-- END -->