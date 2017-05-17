<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3>Login</h3>
<br/>

<c:if test="${error}">
	<p class="error">Incorrect email or password</p>
</c:if>

<div class="form_content">
	<c:url var="loginURL" value="/login"/>
	<form action="${loginURL}" method="post">
		<p>
			<label for="username">Email</label> 
			<input id="username" name="username" type="text"/>
		</p>
		<p>
			<label for="password">Password</label> 
			<input id="password" name="password" type="password"/>
		</p>
		<input type="submit" value="Login"/>
	</form>
</div>