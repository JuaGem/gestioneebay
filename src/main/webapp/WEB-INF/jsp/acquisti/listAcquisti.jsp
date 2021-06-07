<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="it">
	<head>
		<jsp:include page="../header.jsp" />
		<title>Pagina acquisti</title>
		
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
			        <h5>Lista dei risultati dei acquisti</h5> 
			    </div>
			    <div class='card-body'>
			    	<a href="${pageContext.request.contextPath}/public/listAll" class='btn btn-primary' >
			            <i class='fa fa-chevron-left'></i> Acquista altro
			        </a>
			        
			        <a href="${pageContext.request.contextPath}/areaprivata" class='btn btn-outline-secondary' >
			            <i class='fa fa-chevron-left'></i> Torna al Menù
			        </a>
			    
			        <div class='table-responsive'>
			            <table class='table table-striped ' >
			                <thead>
			                    <tr>
			                        <th>Descrizione acquisto</th>
			                        <th>Prezzo</th>
			                        <th>Data acquisto</th>
			                        <th>Azioni</th>
			                    </tr>
			                </thead>
			                <tbody>
			              <c:forEach items="${list_acquisti_attr }" var="acquistoItem">
                    		  <td>${acquistoItem.descrizione }</td>
                    		  <td>${acquistoItem.prezzo }</td>
                              <td><fmt:formatDate type="date" value="${acquistoItem.dateAcquisto}"/></td>


                              <td>
                        		<a class="btn  btn-sm btn-outline-secondary"
                           			href="${pageContext.request.contextPath }/acquisti/dettaglio/${acquistoItem.id }">Dettaglio</a>
                        		<%-- <a class="btn  btn-sm btn-outline-success ml-2 mr-2" 
                           			href="${pageContext.request.contextPath }/acquisti/compra/${annuncioItem.id }">Acquista prodotto</a> --%>
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