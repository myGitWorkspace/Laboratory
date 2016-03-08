<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<jsp:include page="../blocks/header.jsp" />

<h1><spring:message code="question.add.title"/> ${currentQuestion} )</h1>
	<br />

	<spring:url value="/question/add" var="questionActionUrl" />

	<form:form name='questionForm' modelAttribute="questionForm" action="${questionActionUrl}" method='POST'>
		<div class="row">
		<div class="column1 form-group">
			<label for="question" class="col-sm-2 control-label">
				<spring:message code="question.add.question.label"/>
			</label>
		</div>
		<div class="column2 form-group">
			<form:textarea path="questionText" rows="7" cols="70" cssClass="form-control" id="questionText" />
			<form:errors path="questionText" cssStyle="color: red;"/>
		</div>
		</div>
		
		<br><br>
		
		<div id="answers">
		<c:forEach items="${questionForm.answer}" var="answer" varStatus="status">
		<div class="row">
		<div class="column1 form-group">
			<label for="answer" class="col-sm-2 control-label">
			<spring:message code="question.add.answer.label"/><c:out value='${status.index+1}' />
			</label>
		</div>
		<div class="column2 form-group">
  			<div class="answer" > 
    			<form:textarea path="answer[${status.index}].answerText" rows="3" cols="70" cssClass="form-control"/>    
			</div>
  		<div class="checkbox form-group">
  			<label>
  				<form:checkbox path="answer[${status.index}].isCorrect" id="answer[${status.index}].isCorrect" />
  			</label>    
  		</div>
  		
		</div>
		</div>
		</c:forEach>
		</div>
 
 		<div class="clear"></div>
 		
 		<div class="row">
		<div class="button1">
			<button type="button" class="btn-lg btn-primary pull-left" onClick="add_answer()"><spring:message code="question.add.button.addanswer"/></button>
		</div>
		<div class="button2">
			<button type="button" class="btn-lg btn-primary pull-left" id="delete_button" onClick="delete_answer()"><spring:message code="question.add.button.deleteanswer"/></button>
		</div>		
		<div class="button2">
			<button type="button" class="btn-lg btn-primary pull-left" onClick="leave_page=false;next_question()"><spring:message code="question.add.button.addquestion"/></button>
		</div>
		<div class="button2">
			<spring:url value="/tests/save" var="testSaveActionUrl" />
			<button type="button" class="btn-lg btn-primary pull-left" onClick="leave_page=false;getElementById('questionForm').action='${testSaveActionUrl}';getElementById('questionForm').submit();"><spring:message code="question.add.button.savetest"/></button>
		</div>
		</div>
 		
 		<div class="clear"></div>
 		
 		<input type="hidden" name="questionNumb" value="${currentQuestion}" />
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

	</form:form>


<jsp:include page="../blocks/footer.jsp" />
</html>