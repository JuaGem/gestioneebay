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
	  <div class="jumbotron" >
	    <div class="container">
	       <h2 class="display-3">Benvenuto nella Tua Area Privata</h2>
	       <h3> Credito Residuo: ${utente_registration_attribute.creditoResiduo }</h3>
	    </div>
	  </div>
	  
	  <div class="container">
	  
	  	<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errorMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
	  
	    <!-- Example row of columns -->
	    <div class="row">
	      <div class="col-md-6">
	        <h2>Gestione Account </h2>
	        <p>Questa funzionalità è realtiva alla Gestione dei Dati del tuo Account</p>
	        <p><a class="btn btn-primary" href="areaprivata/show" role="button">Vai alla Funzionalità &raquo;</a></p>
	      </div>
	     
	      <div class="col-md-6">
	        <h2>I miei Acquisti</h2>
	        <p>Visualizza i tuoi acquisti</p>
	        <p><a class="btn btn-primary" href="${pageContext.request.contextPath}/acquisti/list" role="button">Vai alla Funzionalità &raquo;</a></p>
	      </div>
	     
	      <div class="col-md-6">
	        <h2>I miei Annunci</h2>
	        <p>Gestisci i tuoi annunci</p>
	        <p><a class="btn btn-primary" href="areaprivata/list" role="button">Vai alla Funzionalità &raquo;</a></p>
	      </div>
	    </div>
	    <hr>
	    	<a class="btn btn-outline-primary" style="color: blue !important; float:right;" href="${pageContext.request.contextPath}/annuncio/insert" role="button">Add new Annuncio</a>
	
	  </div> <!-- /container -->
	
	</main>
	
	<jsp:include page="../footer.jsp" />
  </body>
</html>