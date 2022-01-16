<%-- 
    Document   : SignUp
    Created on : Jan 16, 2022, 6:57:06 PM
    Author     : Edwars
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="signup" method="post">
            <div class="form-group form-focus">
                                                <input name="email" type="text" class="form-control floating" value="${email}" required=""/>
                                                <label class="focus-label">Email</label>
            </div>
            <div class="form-group form-focus">
                                                <input name="pass" type="password" class="form-control floating" value="${pass}" required=""/>
                                                <label class="focus-label">Create Password</label>
            </div>
            <div class="form-group form-focus">
                                                <input name="repass" type="password" class="form-control floating" value="${repass}" required=""/>
                                                <label class="focus-label">Confirm Password</label>
            </div>
            <div class="text-right">
                                                <a class="forgot-link" href="login.jsp" >Already have an account?</a>
            </div>
            <button class="btn btn-primary btn-block btn-lg login-btn" type="submit">
                                                Signup
            </button>
                                        </form>
    </body>
</html>
