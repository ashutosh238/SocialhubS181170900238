<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <title>Complaint Page</title>
  
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>


<div class="container">
  <h2>Complaint details</h2>          
  <table class="table">
    <thead>
      <tr>
        <th>EmailID</th>
        <th>Customer Name</th>
        <th>Subject</th>
        <th>Complaint/Message</th>
        
      </tr>
    </thead>
    <tbody>
    <c:forEach var="p" items="${listProducts}" >
      <tr class="danger">
       
<td>${p.emailID}</td>
<td>${p.name}</td>
<td>${p.subject}</td>
<td>${p.message}</td>




 </tr>


</c:forEach>
     
    </tbody>
  </table>
  
</div>



</body>

</html>

