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
			        <h5>Lista dei tuoi annunci</h5> 
			    </div>
			    <div class='card-body'>
			    	<a class="btn btn-primary " href="${pageContext.request.contextPath}/annuncio/insert">Add New Annuncio</a>
			    	<a href="${pageContext.request.contextPath}/areaprivata" class='btn btn-outline-secondary' >
			            <i class='fa fa-chevron-left'></i> Torna al Menù
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
                    		  <td>${annuncioItem.testoAnnuncio }</td>
                    		  <td>${annuncioItem.prezzo }</td>
                              <td><fmt:formatDate type="date" value="${annuncioItem.dataPubblicazione}"/></td>


                              <td>
                        		<a class="btn  btn-sm btn-outline-secondary"
                           			href="${pageContext.request.contextPath }/areaprivata/dettaglio/${annuncioItem.id }">Visualizza</a>
                           		<c:if test="${annuncioItem.statoAnnuncio}">
                        		   <a class="btn  btn-sm btn-outline-primary ml-2 mr-2" 
                           			  href="${pageContext.request.contextPath }/areaprivata/editAnnuncio/${annuncioItem.id }">Edit</a>
                           	   	   <a id="changeStatoLink_#_${annuncioItem.id }" class="btn btn-sm btn-outline-danger link-for-modal" data-toggle="modal" data-target="#confirmDeleteAnnuncioModal">Elimina</a>
                        	    </c:if>
                        	   
                        	    <c:if test = "${!annuncioItem.statoAnnuncio}">
                                    		<p style="color: green; display: inline-block">Annuncio venduto!</p>
                                </c:if>
                        	<tr>


                        	</tr>


                        </c:forEach>
			                </tbody>
			            </table>
			        </div>
			    </div>
			    
			</div>	
			
<div class="modal fade" id="confirmDeleteAnnuncioModal" tabindex="-1" role="dialog" aria-labelledby="confirmBuyModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="confirmBuyModalLabel">Conferma Eliminazione</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Vuoi procedere con l'archiviazione dell'articolo?
            </div>
            <form method="post" action="${pageContext.request.contextPath }/areaprivata/archivia" >
                <div class="modal-footer">
                    <input type="hidden" name="idAnnuncioForDelete" id="idAnnuncioForDelete">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Chiudi</button>
                    <input type="submit" value="Archivia"  class="btn btn-primary btn-danger"> 
                </div>
            </form>
        </div>
    </div>
</div>
<!-- end Modal -->
	<script type="text/javascript">
    	$(".link-for-modal").click(function(){
        	<!-- mi prendo il numero che poi sarà l'id. Il 18 è perché 'changeStatoLink#' è appunto lungo 18  -->
        	var callerId = $(this).attr('id').substring(18);
        	<!-- imposto nell'hidden del modal l'id da postare alla servlet -->
        	$('#idAnnuncioForDelete').val(callerId);
    	});
	</script>
	
		
		<!-- end container -->	
		</main>
		
		<jsp:include page="../footer.jsp" />
		
	</body>
</html>