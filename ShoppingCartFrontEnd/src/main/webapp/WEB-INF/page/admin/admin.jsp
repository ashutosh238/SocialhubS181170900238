<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<title>Admin Page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
 
  <a href="manage_categories" class="btn btn-info" role="button">Manage Categories</a>
  <a href="manage_suppliers" class="btn btn-info" role="button">Manage Suppliers</a>
  <a href="manage_products" class="btn btn-info" role="button">Manage Produts</a>
  <a href="chk_query" class="btn btn-info" role="button">Check Complaints</a>
</div>

	
	<c:if test="${isAdminClickedManageSupplires==true}">
		<jsp:include page="supplier.jsp"></jsp:include>
	</c:if>

	<c:if test="${isAdminClickedManageProducts==true}">
		<jsp:include page="product.jsp"></jsp:include>
		<jsp:include page="category.jsp"></jsp:include>
	</c:if>

	<c:if test="${isAdminClickedManageCategories==true}">
		<jsp:include page="category.jsp"></jsp:include>
	</c:if>



</body>
</html>