<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Modifica dati Annuncio</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    <style>	.error_field {
				color: red;
			}
	</style>
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
		<%-- alert con lista errori --%>
		<spring:hasBindErrors name="utente_registration_attribute" >
			<%-- alert errori --%>
			<div class="alert alert-danger " role="alert">Attenzione!! Sono
				presenti errori di validazione</div>
		</spring:hasBindErrors>
	
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errorMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Modifica i dati dell'annuncio</h5> 
		    </div>
		    <div class='card-body'>
		    
		    		<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>

					<form:form modelAttribute="edit_articolo_attr" method="post" action="${pageContext.request.contextPath}/areaprivata/saveupdate" novalidate="novalidate">
						<input type="hidden" name="id" id="id" value="${edit_articolo_attr.id}" >
						
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Testo annuncio</label>
								<spring:bind path="testoAnnuncio">
								<input type="text" name="testoAnnuncio" id="testoAnnuncio" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire il testo dell'annuncio" value="${edit_articolo_attr.testoAnnuncio }"  >
								</spring:bind>
								<form:errors path="testoAnnuncio" cssClass="error_field" />
							</div>
							
							<div class="form-group col-md-6">
								<label for="prezzo">Prezzo</label>
								<spring:bind path="prezzo">
									<input type="number" class="form-control ${status.error ? 'is-invalid' : ''}" name="prezzo" id="prezzo" placeholder="Inserire l'importo" value="${edit_articolo_attr.prezzo }" required>
								</spring:bind>
								<form:errors  path="prezzo" cssClass="error_field" />
							</div>
						</div>
						
						<div class="form-row">	
						  Categorie:
						  <div class="form-check">
						  <spring:bind path="categorie">
						   <c:forEach items="${list_categorie}" var="categoriaItem">
						     <input name="categorie" class="form-check-input" type="checkbox" value="${categoriaItem.id}" id="defaultCheck${categoriaItem.id}" ${edit_articolo_attr.categorie.contains(categoriaItem)?"checked":"" }>
						     <label class="form-check-label" for="defaultCheck${categoriaItem.id}">
						       ${categoriaItem.descrizione}
						     </label>
						     <br/>
						   </c:forEach>
						  </spring:bind>
						  </div>
							
						</div>
						
							
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Modifica</button>
						<a href="${pageContext.request.contextPath}/areaprivata/list" class='btn btn-outline-secondary' >
			            	<i class='fa fa-chevron-left'></i> Back
			            </a>
							
					</form:form>

		    
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>