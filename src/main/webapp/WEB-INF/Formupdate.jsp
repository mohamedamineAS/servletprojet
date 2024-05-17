<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="<%=request.getContextPath()%>/Homme.jsp">User Management</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/users">Users</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <form action="<%=request.getContextPath()%>/UpdateServlet" method="post">
                <caption>
                    <h2>Edit User</h2>
                </caption>

                <fieldset class="form-group">
                    <label>User ID</label> 
                    <input type="text" value="<c:out value='${user.id}' />" class="form-control" name="id" required="required" readonly>
                </fieldset>

                <fieldset class="form-group">
                    <label>Username</label> 
                    <input type="text" value="<c:out value='${user.uname}' />" class="form-control" name="uname" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>Password</label> 
                    <input type="password" value="<c:out value='${user.upwd}' />" class="form-control" name="upwd" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>Email</label> 
                    <input type="email" value="<c:out value='${user.uemail}' />" class="form-control" name="uemail" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>Mobile</label> 
                    <input type="text" value="<c:out value='${user.umobile}' />" class="form-control" name="umobile" required="required">
                </fieldset>

                <button type="submit" class="btn btn-success">Save</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
