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
            <a class="navbar-brand" href="<%=request.getContextPath()%>/Homme.jsp">Pharmacy Management</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/medicine">Medicins</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/Flight">Flights</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/Tickets">Tickets</a>
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
            <c:if test="${medicine != null}"> 
                <form action="<%=request.getContextPath()%>/UpdateServlet" method="post">
            </c:if>
            <c:if test="${medicine == null}"> 
                <form action="<%=request.getContextPath()%>/InserServlet" method="post">
            </c:if>

            <caption>
    <h2>
        <c:if test="${medicine != null}"> 
            Edit Medicine
        </c:if>
        <c:if test="${medicine == null}"> 
            Add New Medicine 
        </c:if>
    </h2>
</caption>

<c:if test="${medicine != null}"> 
    <input type="hidden" name="id" value="<c:out value='${medicine.id}' />" />
</c:if>

<fieldset class="form-group">
    <label>Medicine ID</label> 
    <input type="text" value="<c:out value='${medicine.med_id}' />" class="form-control" name="med_id" required="required">
</fieldset>

<fieldset class="form-group">
    <label>Medicine Name</label> 
    <input type="text" value="<c:out value='${medicine.med_name}' />" class="form-control" name="med_name" required="required">
</fieldset>

<fieldset class="form-group">
    <label>Medicine Company Name</label> 
    <input type="text" value="<c:out value='${medicine.med_comp_name}' />" class="form-control" name="med_comp_name" required="required">
</fieldset>

<fieldset class="form-group">
    <label>Medicine Quantity</label> 
    <input type="number" value="<c:out value='${medicine.med_quantity}' />" class="form-control" name="med_quantity" required="required">
</fieldset>

<fieldset class="form-group">
    <label>Medicine Price</label> 
    <input type="number" value="<c:out value='${medicine.med_price}' />" class="form-control" name="med_price" required="required">
</fieldset>

<button type="submit" class="btn btn-success">Save</button>
</form>

        </div>
    </div>
</div>
</body>
</html>
