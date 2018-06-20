<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
<c:forEach items="${products}" var="product">
<br>
<div class="container">
       <a href="<c:url value="productDesc/${product.id}"/>">
			<img src = "<c:url value="resources/${product.id}.jpg"/>"class="img-rounded" width="204" height="336" alt = "Cinque Terre">
			</a>
  
</div>
 
<br>
<pre>
${product.name}<br>

${product.description}<br>


Price: Rs ${product.price}<br>


</pre>


</c:forEach>




</body>

</html>