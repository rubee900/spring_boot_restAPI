<%@page import="com.techtech.dto.ProductDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.techtech.enitity.ProductEntity"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
  <title>Product Page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<img alt="" src="https://th.bing.com/th/id/OIP.pIv8zNUXzZTI3Y1zCFeXdAAAAA?rs=1&pid=ImgDetMain">
<form action="addProduct" method="post">
<div class="mb-3 mt-3">
      <label for="name">Name:</label>
      <input type="text" class="form-control" id="name" placeholder="Enter name" name="name">
    </div>
    <div class="mb-3">
     <label for="category" class="form-label">Select Category (select one):</label>
    <select class="form-select" id="category" name="category">
      <option>Apple</option>
      <option>Samsung</option>
      <option>Google</option>
      <option>OnePlus</option>
    </select>
    </div>
    <div class="mb-3">
      <label for="price">Price:</label>
      <input type="text" class="form-control" id="price" placeholder="Enter price" name="price">
    </div>
    <div class="mb-3">
      <label for="photo">Photo:</label>
      <input type="text" class="form-control" id="photo" placeholder="Enter photo URL" name="photo">
    </div>  
        <button type="submit" class="btn btn-primary">Add Product</button>
        
          </form>
          <br>
          
       
          <form action="searchProduct" method="get">
      
      <input type="text" class="form-control" id="stext" name="stext" style="width:200px; display:inline;">
		<button type="submit" class="btn btn-success">Search</button>   
    </form>
    
    <a href="barChart.jsp">Bar Chart</a>
    
  
    
    
    <div class="mb-3">
    
    <br>
       
    <table class="table table-striped">
    <thead>
      <tr>
        <th>PId</th>
        <th><a href="sorting?attribute=name&orderBy=${orderBy}">
        Name </a>
         </th>
        <th><a href="sorting?attribute=category&orderBy=${orderBy}">
        Category </a>
        </th>
        <th>Price </th>
        <th>Photo</th>
        <th>Action</th>
        
      </tr>
    </thead>
    <tbody>
      <tr>
      <%
      List<ProductDTO> list = (List<ProductDTO>)request.getAttribute("list");
      if(list==null){
    	  list= new ArrayList<>();
      }
      int count = 1;
      for(ProductDTO item:list){
    
      
      %>
        <td><%=item.getPid()%></td>
        <td><%=item.getName()%></td>
        <td><%=item.getCategory()%></td>
        <td><%=item.getPrice()%></td>
        <td><img alt="" src="<%=item.getPhoto()%>" style="height:120px;"></td>
        <td> <a href="editPorduct" > Edit</a> | <a href="deleteProduct?pid=<%=item.getPid()%>" style="color: red;">Delete</a> </td>
        
      </tr>
      <%} %>
     
    </tbody>
  </table>
    </div>
    
    


</body>
</html>