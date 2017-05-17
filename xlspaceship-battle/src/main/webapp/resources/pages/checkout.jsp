<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h2>Checkout</h2>
<div ng-app="checkoutApp" ng-controller="checkoutController">
	
	<c:url var="placeOrderURL" value="/placeOrder" />
	<form:form commandName="checkoutForm" action="${placeOrderURL}">
	
	<div class="addressGrid">
		<div class="addressItem">
			<h3>Payment Address</h3>
			<table class="checkout_address">
				<tr>
					<td>Address line</td>
					<td>{{billingAddress.addressLine}}</td>
				</tr>
				<tr>
					<td>Country</td>
					<td>{{billingAddress.countryLocName}}</td>
				</tr>
				<tr>
					<td>Region</td>
					<td>{{billingAddress.regionLocName}}</td>
				</tr>
				<tr>
					<td>City</td>
					<td>{{billingAddress.city}}</td>
				</tr>
				<tr>
					<td>Postcode</td>
					<td>{{billingAddress.postcode}}</td>
				</tr>
				<tr>
					<td>Phone</td>
					<td>{{billingAddress.phone}}</td>
				</tr>
			</table>
			<a class="btn btn_edit_address" href="javascript:void(0)" 
				ng-click="editAddress(billingAddress.id)">Edit Address</a>
		</div>
		<div class="addressItem">
			<h3>Delivery Address</h3>
			<table class="checkout_address">
				<tr>
					<td>Address line</td>
					<td>{{shippingAddress.addressLine}}</td>
				</tr>
				<tr>
					<td>Country</td>
					<td>{{shippingAddress.countryLocName}}</td>
				</tr>
				<tr>
					<td>Region</td>
					<td>{{shippingAddress.regionLocName}}</td>
				</tr>
				<tr>
					<td>City</td>
					<td>{{shippingAddress.city}}</td>
				</tr>
				<tr>
					<td>Postcode</td>
					<td>{{shippingAddress.postcode}}</td>
				</tr>
				<tr>
					<td>Phone</td>
					<td>{{shippingAddress.phone}}</td>
				</tr>
			</table>
			<a class="btn btn_edit_address" href="javascript:void(0)" 
				ng-click="editAddress(shippingAddress.id)">Edit Address</a>
		</div>
		
		<div class="delTypeItem">
			<h3>Delivery Type</h3>
			<c:forEach items="${deliveryTypes}" var="deliveryType">
			<p>
				<form:radiobutton id="delType${deliveryType.code}" path="deliveryType" 
					value="${deliveryType.code}" ng-model="delType" ng-change="updateDeliveryCost(delType)" 
					ng-checked="${checkoutForm.deliveryType eq deliveryType.code ? true : false}"/>
				<label for="delType${deliveryType.code}">
					${deliveryType.name} - ${deliveryType.cost eq 0 ? 'FREE' : deliveryType.formattedCost} 
					(${deliveryType.description})
				</label>
			</p>
			</c:forEach>
		</div>
		<div class="delTypeItem">
			<h3>Payment Method</h3>
			<c:forEach items="${paymentMethods}" var="paymentMethod">
			<p>
				<form:radiobutton id="payMethod${paymentMethod.code}" path="paymentMethod" value="${paymentMethod.code}" 
					ng-checked="${checkoutForm.paymentMethod eq paymentMethod.code}"/>
				<label for="payMethod${paymentMethod.code}">${paymentMethod.name}</label>
			</p>
			</c:forEach>
		</div>
	</div>
	
	<!-- 
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
				<td width="15%"><div> {{cartItem.quantity}}</div></td>
				<td width="15%"><div>$ {{cartItem.total}}</div></td>
			</tr>
		</tbody>
	</table>
	 -->	
	
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
	
	<div class="cart_content">
		<input class="btn btn_checkout" type="submit" value="Place Order"/>
	</div>
	
	</form:form>
	
	<script type="text/ng-template" id="checkoutEditAddress">
	
	<div id="editAddress">
		<div class="popupHeader">
			<h3>Edit Address</h3>
		</div>
		
		<div class="popupBody">
			<form id="addressForm" ng-submit="updateAddress()" method="post">
				<table>
					<tr>
						<td></td>
						<td><input ng-model="address.addressId" type="hidden"/></td>
					</tr>
					<tr>
						<td><label for="addressLine">Address Line <b style="color:red">*</b></label></td>
						<td><input ng-model="address.addressLine" type="text" required/></td>
					</tr>
					<tr>
						<td><label for="countryIso">Country <b style="color:red">*</b></label></td>
						<td>
							<select ng-model="address.countryIso" 
								ng-options="country.code as country.name for country in countries"
								ng-change="loadRegions(address.countryIso)" required>
								<option value="">Select Country</option>
							</select>
						</td>
					</tr>
					<tr>
						<td><label for="regionIso">Region <b style="color:red">*</b></label></td>
						<td>
							<select ng-model="address.regionIso" 
								ng-options="region.code as region.name for region in regions"
		 						ng-change="storeRegionData(address.regionIso)" required>
								<option value="">Select Region</option>
							</select>
						</td>
					</tr>
					<tr>
						<td><label for="city">City <b style="color:red">*</b></label></td>
						<td><input ng-model="address.city" type="text" required/></td>
					</tr>
					<tr>
						<td><label for="postcode">Postcode <b style="color:red">*</b></label></td>
						<td><input ng-model="address.postcode" type="text" required/></td>
					</tr>
					<tr>
						<td><label for="phone">Phone </label></td>
						<td><input ng-model="address.phone" type="text"/></td>
					</tr>
				</table>
				<p>
					<input class="btn btn_link" type="submit" value="Update Address" style="width: 150px" />
					<a class="btn btn_link" href="javascript:void(0)" style="float:right;"
						ng-click="closeThisDialog()">Cancel</a>
				</p>
			</form>
		</div>
	</div>
	</script>
	
	<script src="resources/js/controllers/CheckoutController.js"></script>
</div>
