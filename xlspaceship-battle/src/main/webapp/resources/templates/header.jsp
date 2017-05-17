<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="nav">
	<h1>CoolShop</h1>
	<div class="content_right">
		<a href="<%=request.getContextPath()%>/">Home</a> |
		<sec:authorize access="isAnonymous()">
			<a href="<%=request.getContextPath()%>/login">Login</a> |
			<a href="<%=request.getContextPath()%>/register">Register</a> |
			<a href="<%=request.getContextPath()%>/product">Catalog</a>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			<a href="<%=request.getContextPath()%>/myaccount">My Account</a> | 
			<a href="<%=request.getContextPath()%>/cart">Cart</a> | 
		    <c:url var="logoutURL" value="/login/logout" />
		    <a href="${logoutURL}">Logout</a>
		</sec:authorize>
	</div>
</div>