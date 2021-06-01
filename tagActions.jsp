<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
<!-- 	Just some stuff you need -->
	<header>
	  <div class="container">
		  
	<c:choose>
	<c:when test="${not empty message}">
	  <p class="alert ${messageClass}">${message}</p>
	<%
	  session.setAttribute("message", null);
	  session.setAttribute("messageClass", null);
	%>
	</c:when>
	</c:choose>
	
		<h1>PUBHUB <small>Add/Remove Tags</small></h1>
		<hr class="book-primary">
				
		<form action="AddTag" method="post" class="form-horizontal" enctype="multipart/form-data">
		 
		 <div class="form-group">
		  <label for="name" class="col-sm-4 control-label">ISBN13</label>	  
		  <div class="col-sm-5">
		   <input type="text" class="form-control" id="isbn13" name="isbn13" placeholder="ISBN13" required="required" value="${param.isbn13 }" />		   	  
		  </div>
		 </div>
		 
		 <div class="form-group">
		  <label for="name" class="col-sm-4 control-label">Name</label>	  
		  <div class="col-sm-5">
		   <input type="text" class="form-control" id="name" name="name" placeholder="Name" required="required" value="${param.name }" />		   	  
		  </div>
		 </div>
		   		    	
		 <div class="form-group">
		  <div class="col-sm-offset-4 col-sm-1">
		   <button type="submit" class="btn btn-info">Add Tag</button>
		  </div>
		 </div>
		</form> 
		
		<hr class="tag-primary">
			
		<form action="RemoveTag" method="post" class="form-horizontal" enctype="multipart/form-data">
		
		<div class="form-group">
		  <label for="name" class="col-sm-4 control-label">ISBN13</label>	  
		  <div class="col-sm-5">
		   <input type="text" class="form-control" id="isbn13" name="isbn13" placeholder="ISBN13" required="required" value="${param.isbn13 }" />		   	  
		  </div>
		 </div>
		
		 <div class="form-group">
		  <label for="name" class="col-sm-4 control-label">Name</label>	  
		  <div class="col-sm-5">
		   <input type="text" class="form-control" id="name" name="name" placeholder="Name" required="required" value="${param.name }" />		   	  
		  </div>
		 </div>
		   		    	
		 <div class="form-group">
		  <div class="col-sm-offset-4 col-sm-1">
		   <button type="submit" class="btn btn-info">Remove Tag</button>
		  </div>
		 </div>
		</form> 	

	 </div>
	</header>

	<!-- Footer -->
	<jsp:include page="footer.jsp" />
	