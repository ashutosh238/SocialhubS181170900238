<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <title>Bootstrap Example</title>
  
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>


<nav class="navbar navbar-inverse">
 

 <div class="container-fluid">
     <c:forEach var="category" items="${categories}">
    <ul class="nav navbar-nav">
   
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">${category.name}  <span class="caret"></span></a>
        <ul class="dropdown-menu">
        <li> <c:forEach var="product" items="${category.products}">
					<div class="dropdown-content">
						<a href="productDesc/${product.id}">${product.name}</a>

					</div>
					
					
</c:forEach>
</li>
        </ul>
      </li>
    </ul>
  
  </c:forEach>
  </div>
</nav>	
<br><br>
<c:forEach items="${listProducts}" var="product">

		<div class = "col-sm-3 col-md-3">
			<a href="<c:url value="productDesc/${product.id}"/>">
			<img src = "<c:url value="resources/${product.id}.jpg"/>" width="100" height="150" alt = "Generic thumbnail">
			</a>
 			
      	 		<h4>${product.name}</h4>
      	 		<h4>Rs ${product.price}</h4>
		</div>
</c:forEach>


</body>

</html>

