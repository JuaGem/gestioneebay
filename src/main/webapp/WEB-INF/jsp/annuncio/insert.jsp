<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Inserisci nuovo</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath }/assets/css/global.css" rel="stylesheet">
    <style>
	    .error_field {
	        color: red; 
	    }
	</style>
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
		<%-- se l'attributo in request ha errori --%>
		<spring:hasBindErrors  name="insert_annuncio_attr">
			<%-- alert errori --%>
			<div class="alert alert-danger " role="alert">
				Attenzione!! Sono presenti errori di validazione
			</div>
		</spring:hasBindErrors>
	
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errorMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Inserisci nuovo annuncio</h5> 
		    </div>
		    <div class='card-body'>
		    
		    		<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>

					<form:form modelAttribute="insert_annuncio_attr" method="post" action="save" novalidate="novalidate" >
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label><b> Descrizione annuncio </b> <span class="text-danger">*</span></label>
								<spring:bind path="testoAnnuncio">
									<input type="text" name="testoAnnuncio" id="testoAnnuncio" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire il testo dell'annuncio" value="${insert_annuncio_attr.testoAnnuncio }" required>
								</spring:bind>
								<form:errors  path="testoAnnuncio" cssClass="error_field" />
							</div>
							
							<div class="form-group col-md-6">
								<label for="prezzo"><b> Prezzo </b><span class="text-danger">*</span></label>
								<spring:bind path="prezzo">
									<input type="number" class="form-control ${status.error ? 'is-invalid' : ''}" name="prezzo" id="prezzo" placeholder="Inserire il prezzo del prodotto" value="${insert_annuncio_attr.prezzo }">
								</spring:bind>
								<form:errors  path="prezzo" cssClass="error_field" />
							</div>
						</div>
						
						
						 <b> Categorie: </b>
						  <div class="form-check">
						  <spring:bind path="categorie">
						   <c:forEach items="${list_categorie}" var="categoriaItem">
						     <input name="categorie" class="form-check-input" type="checkbox" value="${categoriaItem.id}" id="defaultCheck1">
						     <label class="form-check-label" for="defaultCheck1">
						       ${categoriaItem.descrizione}
						     </label>
						     <br/>
						   </c:forEach>
						     </spring:bind>
						  </div>
							
						
							
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>

					</form:form>
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>