<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Cart</h2>
<div ng-app="cartApp" ng-controller="cartController">
	
	<div class="cart_content">	
		<table class="striped cart_items" ng-show="cartItems">
			<thead>
				<tr>
					<th>Item</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Total</th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="cartItem in cartItems">
					<td width="55%">
						<img src="resources/images/product_na.gif" alt=""/>
						<div>
							<p>{{cartItem.productName}}</p>
							<p>Some loooooooong description</p>
						</div>
					</td>
					<td width="15%"><div>$ {{cartItem.productPrice}}</div></td>
					<td width="15%">
						<div>
							<input type="text" class="qty" ng-model="cartItem.quantity" 
								ng-blur="updateCartItem(cartItem.id, cartItem.quantity)"/>
							<br/>
							<a class="removeItem" href="javascript:void(0)" ng-click="deleteCartItem(cartItem.id)">Remove</a>
						</div>
					</td>
					<td width="15%"><div>$ {{cartItem.total | number:2}}</div></td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div class="cart_content">
		<table class="striped order_total" ng-show="cartItems">
			<thead>
				<tr><th>ORDER TOTALS</th><th></th></tr>
			</thead>
			<tbody>
				<tr><td>Subtotal</td><td>$ {{subtotal | number:2}}</td></tr>
				<tr><td>Delivery</td><td>$ {{deliveryCost | number:2}}</td></tr>
			</tbody>
			<tfoot>
				<tr><td>TOTAL</td><td>$ {{total | number:2}}</td></tr>
			</tfoot>
		</table>
		<p ng-show="!cartItems">Your cart is empty</p>
	</div>
	
	<div class="cart_content" ng-show="cartItems">
		<c:url var="checkoutURL" value="/checkout"/>
		<a class="btn btn_checkout" href="${checkoutURL}">Checkout</a>
	</div>
	
	<script src="resources/js/controllers/CartController.js"></script>
	
</div>