<%@ page session="false"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>

<html>

<jsp:include page="blocks/header.jsp" />

	<div class="container">

		<h1><spring:message code="error.title"/></h1>
		<br><br>
		
		<p><b><spring:message code="error.cause.label"/> : </b>${exception}</p>
		<p><b><spring:message code="error.target.label"/> : </b>${url}</p>

	</div>

<jsp:include page="blocks/footer.jsp" />

</html>

