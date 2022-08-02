<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link href="./css/login.css" rel="stylesheet">
</head>

<body>
<div id="login">
    <c:if test="${error}">
        <div class="alert alert-danger" role="alert">
                ${error}
        </div>
    </c:if>
    <h3 class="text-center text-white pt-5">Bienvenido a Linkify!</h3>
    <div class="container">
        <div id="login-row" class="row justify-content-center align-items-center">
            <div id="login-column" class="col-md-6">
                <div id="login-box" class="col-md-12">
                    <form id="login-form" class="form" action="j_security_check" method="post">
                        <h3 class="text-center text-info">Login</h3>
                        <div class="form-group">
                            <label for="username" class="text-info">Usuario</label><br>
                            <input type="text" name="j_username" id="username" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="password" class="text-info">Contraseña</label><br>
                            <input type="password" name="j_password" id="password" class="form-control">
                        </div>
                        <div class="form-group"><br>
                            <input type="submit" name="submit" class="btn btn-info btn-md" value="Iniciar sesión">
                        </div>
                        <div id="register-link" class="text-right">
                            <a href="#" class="text-info">Register here</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</body>
</html>