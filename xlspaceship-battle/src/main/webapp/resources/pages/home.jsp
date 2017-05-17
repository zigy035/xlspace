<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h2>Products</h2>
<div ng-app="productApp" ng-controller="productController">
		
	<div class="productGrid">
		<div class="productItem" ng-repeat="product in products">
			<img src="resources/images/product_na.gif"/>
			<p>{{product.name}}</p>
			<p>{{product.price}}</p>
			<sec:authorize access="isAuthenticated()">
				<form id="addItemForm" ng-submit="addCartItem(product.id)" method="post">
					<a class="btn btn_link" href="javascript:void(0)" ng-click="addCartItem(product.id)">Add to Cart</a>
				</form>
			</sec:authorize>
			<sec:authorize access="isAnonymous()">
				<c:url var="loginURL" value="/login"/>
				<a class="btn btn_link" href="${loginURL}">Add to Cart</a>
			</sec:authorize>
		</div>
	</div>
	
	<script src="resources/js/controllers/ProductController.js"></script>
	
</div>
