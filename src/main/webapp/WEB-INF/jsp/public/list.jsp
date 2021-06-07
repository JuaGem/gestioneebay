<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="it">
	<head>
		<jsp:include page="../header.jsp" />
		<title>Pagina dei risultati</title>
		
		<!-- style per le pagine diverse dalla index -->
	    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	    
	</head>
	<body>
		<jsp:include page="../navbar.jsp" />
		
		<main role="main" class="container">
		
			<div class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}" role="alert">
			  ${successMessage}
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
			</div>
			<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
			  ${errorMessage}
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
			</div>
		    <div class="alert alert-info alert-dismissible fade show ${notFoundMessage==null?'d-none': ''}" role="alert">
		      ${notFoundMessage}
		      <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		        <span aria-hidden="true">&times;</span>
		  	  </button>
		    </div>
			
			<div class='card'>
			    <div class='card-header'>
			        <h5>Lista dei risultati</h5> 
			    </div>
			    <div class='card-body'>
			    	<a class="btn btn-primary " href="${pageContext.request.contextPath}/annuncio/insert">Add New Annuncio</a>
			    	<a href="${pageContext.request.contextPath}/public/" class='btn btn-outline-secondary' >
			            <i class='fa fa-chevron-left'></i> Torna alla Ricerca
			        </a>
			    
			        <div class='table-responsive'>
			            <table class='table table-striped ' >
			                <thead>
			                    <tr>
			                        <th>Testo annuncio</th>
			                        <th>Prezzo</th>
			                        <th>Data pubblicazione</th>
			                     <!--    <th>Ruolo</th> -->
			                        <th>Azioni</th>
			                    </tr>
			                </thead>
			                <tbody>
			              <c:forEach items="${annunci_list_attribute }" var="annuncioItem">
                    		<c:if test="${annuncioItem.statoAnnuncio}">
                    		  <td>${annuncioItem.testoAnnuncio }</td>
                    		  <td>${annuncioItem.prezzo }</td>
                              <td><fmt:formatDate type="date" value="${annuncioItem.dataPubblicazione}"/></td>


                              <td>
                        		<a class="btn  btn-sm btn-outline-secondary"
                           			href="${pageContext.request.contextPath }/public/dettaglio/${annuncioItem.id }">Dettaglio</a>
                        		<a class="btn  btn-sm btn-outline-success ml-2 mr-2" 
                           			href="${pageContext.request.contextPath }/acquisti/compra/${annuncioItem.id }">Acquista prodotto</a>
                        	</c:if>
                        	<tr>


                        	</tr>


                        </c:forEach>
			                </tbody>
			            </table>
			        </div>
			    </div>
			    
			</div>	
		
		<!-- end container -->	
		</main>
		
		<jsp:include page="../footer.jsp" />
		
	</body>
</html>