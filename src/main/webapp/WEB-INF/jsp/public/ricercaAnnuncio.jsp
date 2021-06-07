<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">
  <head>
    
    <jsp:include page="../header.jsp" />
    
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet" type="text/css">
    <style type="text/css">
    	body {
		  padding-top: 3.5rem;
		}	
    </style>
    
    <title>Ebay</title>
  </head>
  <body>
  
	<jsp:include page="../navbar.jsp" />
  
  
	<main role="main">

	  <!-- Main jumbotron for a primary marketing message or call to action -->
	   <div class="jumbotron">
       	 <div class="container">
            <h1 class="display-3">Piattaforma di e-commerce</h1>
            <h4 class="display-4">Grandi Sconti sull'elettronica!</h4>
            <br>
            <h4>Fino a -60% su smartphone, tv e molto altro!</h4>
        </div>
       </div>

	  
	  <div class="container">
	  
	  	<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errorMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
	  
	   <div class='card-body'>

					<form method="post" action="${pageContext.request.contextPath }/public/list" >
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label><b>Annuncio</b></label>
								<input type="text" name="testoAnnuncio" id="testoAnnuncio" class="form-control" placeholder="Vespa,IPhone,Bilocale..." >
							</div>
							
							<div class="form-group col-md-6">
								<label><b>Prezzo</b></label>
								<input type="text" name="prezzo" id="prezzo" class="form-control" placeholder="500" >
							</div>
						</div>
						
				 		<br/>
						<b>Categorie:</b>
						<div class="form-check">
						<c:forEach items="${list_categorie}" var="categoriaItem">
						  <input name="categorie" class="form-check-input" type="checkbox" value="${categoriaItem.id}" id="defaultCheck1">
						  <label class="form-check-label" for="defaultCheck1">
						    ${categoriaItem.descrizione}
						  </label>
						  <br/>
						  </c:forEach>
						</div>
						
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Cerca</button>
						<input class="btn btn-outline-warning" type="reset" value="Ripulisci">
						
						<a class="btn btn-outline-primary" style="color: blue !important; float:right;" href="${pageContext.request.contextPath}/areaprivata" role="button">Area privata</a>
						
				   </form>
					
		</div>			
	    
	    <hr>
	
	  </div> <!-- /container -->
	
	</main>
	
	<jsp:include page="../footer.jsp" />
  </body>
</html>