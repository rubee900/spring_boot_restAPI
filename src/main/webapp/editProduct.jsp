<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <title>Edit Page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<img alt="" src="">

<form action="" method="post">
<div class="mb-3 mt-3">
      <label for="name">Name:</label>
      <input type="text" class="form-control" id="name" value="" name="name">
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
      <input type="text" class="form-control" id="price" value="" name="price">
    </div>
    <div class="mb-3">
      <label for="photo">Photo:</label>
      <input type="text" class="form-control" id="photo" value="" name="photo">
    </div>  
        <button type="submit" class="btn btn-primary">Update</button>
        <button type="reset" class="btn btn-danger">Reset</button>
        
          </form>
</body>
</html>