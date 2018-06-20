<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-inverse">

 

  <div class="container-fluid">
 
     
    
    <ul class="nav navbar-nav navbar-right">
       <c:if test="${!sessionScope.loggedIn}">
      <li class="active"><a href="<c:url value="/"/>">Home</a></li>
      <li><a href="login">LOGIN</a></li>
      <li><a href="register">REGISTER</a></li>
      <li><a href="contact">CONTACT US</a></li>
      <li><a href="aboutus">ABOUT US</a></li>
      </c:if>
      <c:if test="${sessionScope.loggedIn}">
      	<c:if test="${sessionScope.role=='A'}">
      		<li><a href="<c:url value="/"/>">Home</a></li>
      		<li><a href="manage_products">MANAGE PRODUCT</a></li>
      	</c:if>
      	<c:if test="${sessionScope.role=='C'}">
      		<li><a href="<c:url value="/"/>">Home</a></li>
      		<li><a href="<c:url value="/productPage"/>">Products</a></li>
      	</c:if>
      </c:if>

    </ul>
    <ul class="nav navbar-nav navbar-left">
     <li><a href="productPage"><span class="glyphicon glyphicon-th"></span> Product</a></li>
     
  <form class="navbar-form navbar-left" action="search">

      <div class="form-group">

        <input type="text" class="form-control" placeholder="Search" name="category_name">

      </div>

      <button type="submit" class="btn btn-default">Submit</button>

    </form>
     </ul>
     <div class="nav nav-bar navbar-right">
    	<c:if test="${sessionScope.loggedIn}">
    	<a href="<c:url value="/showCart"/>">
          <span class="glyphicon glyphicon-shopping-cart"></span>
        </a>
    	<font color="White" face="calibri" size='3'>${CartItems}&nbsp;Welcome ${sessionScope.username}</font>
    	<a href="perform_logout">LOGOUT</a>
    	</c:if>
    </div>
  </div>
</nav>
  

</body>
</html>
