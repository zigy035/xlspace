<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%-- <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h3>Register</h3>

<div ng-app="addressApp" ng-controller="addressController">
	<c:url var="registerURL" value="/register" />
	<form:form commandName="registerForm" action="${registerURL}" method="post">
		<div class="form_content form_content_border">
			<h3>Profile Information</h3>
			<p>
				<form:label path="title">Title <b style="color:red">*</b></form:label>
				
				<form:select path="title" ng-model="title" ng-change="storeTitleData(title)" 
					ng-options="title.code as title.name for title in titles">
					<form:option value="">Select Title</form:option>
				</form:select>
				
				<form:errors path="title" cssErrorClass="error"/><br/>
			</p>
			<p>
				<form:label path="firstName">First Name <b style="color:red">*</b></form:label> 
				<form:input path="firstName"/>
				<form:errors path="firstName" cssErrorClass="error"/><br/>
			</p>
			<p>
				<form:label path="lastName">Last Name <b style="color:red">*</b></form:label>
				<form:input path="lastName"/>
				<form:errors path="lastName" cssErrorClass="error"/><br/>
			</p>
			<p>
				<form:label path="email">Email <b style="color:red">*</b></form:label>
				<form:input path="email"/>
				<form:errors path="email" cssErrorClass="error"/><br/>
			</p>
			<p>
				<form:label path="password">Password <b style="color:red">*</b></form:label>
				<form:password path="password" showPassword="true"/>
				<form:errors path="password" cssErrorClass="error"/><br/>
			</p>
			<p>
				<form:label path="confirmPassword">Confirm password <b style="color:red">*</b></form:label>
				<form:password path="confirmPassword" showPassword="true"/>
				<form:errors path="confirmPassword" cssErrorClass="error"/><br/>
			</p> 
		</div>
		<div class="form_content form_content_border">
			<h3>Shipping Address</h3>		
			<p>
				<form:label path="shippingAddressLine">Address line <b style="color:red">*</b></form:label>
				<form:input path="shippingAddressLine"/>
				<form:errors path="shippingAddressLine" cssErrorClass="error"/><br/>
			</p>
			<p>
				<form:label path="shippingCountry">Country <b style="color:red">*</b></form:label>
				
				<form:select path="shippingCountry" ng-model="shippingCountry" 
					ng-options="shipCountry.code as shipCountry.name for shipCountry in shippingCountries"
					ng-change="loadShippingRegions(shippingCountry)">
					<form:option value="">Select Country</form:option>
				</form:select>
				
				<form:errors path="shippingCountry" cssErrorClass="error"/><br/>
			</p>
			<p ng-show="shippingRegions">
				<form:label path="shippingRegion">Region <b style="color:red">*</b></form:label>
	 			
	 			<form:select path="shippingRegion" ng-model="shippingRegion" 
	 				ng-options="shipRegion.code as shipRegion.name for shipRegion in shippingRegions"
	 				ng-change="storeShippingRegionData(shippingRegion)">
	 				<form:option value="">Select Region</form:option>
				</form:select>
	 			
				<form:errors path="shippingRegion" cssErrorClass="error"/><br/>
			</p>
			<p>
				<form:label path="shippingCity">City <b style="color:red">*</b></form:label>
				<form:input path="shippingCity"/>
				<form:errors path="shippingCity" cssErrorClass="error"/><br/>
			</p>
			<p>
				<form:label path="shippingPostcode">Postcode <b style="color:red">*</b></form:label>
				<form:input path="shippingPostcode"/>
				<form:errors path="shippingPostcode" cssErrorClass="error"/><br/>
			</p>
			<p>
				<form:label path="shippingPhone">Phone</form:label>
				<form:input path="shippingPhone"/>
				<form:errors path="shippingPhone" cssErrorClass="error"/><br/>
			</p>
		</div>
		<div class="form_content form_content_last">
			<h3>Billing Address</h3>		
			<p>
				<form:label path="billingAddressLine">Address line <b style="color:red">*</b></form:label>
				<form:input path="billingAddressLine"/>
				<form:errors path="billingAddressLine" cssErrorClass="error"/><br/>
			</p>
			<p>
				<form:label path="billingCountry">Country <b style="color:red">*</b></form:label>
				
				<form:select path="billingCountry" ng-model="billingCountry" 
					ng-options="billCountry.code as billCountry.name for billCountry in billingCountries"
					ng-change="loadBillingRegions(billingCountry)">
					<form:option value="">Select Country</form:option>
				</form:select>
				
				<form:errors path="billingCountry" cssErrorClass="error"/><br/>
			</p>
			<p ng-show="billingRegions">
				<form:label path="billingRegion">Region <b style="color:red">*</b></form:label>
				
				<form:select path="billingRegion" ng-model="billingRegion" 
	 				ng-options="billRegion.code as billRegion.name for billRegion in billingRegions"
	 				ng-change="storeBillingRegionData(billingRegion)">
	 				<form:option value="">Select Region</form:option>
				</form:select>
				
				<form:errors path="billingRegion" cssErrorClass="error"/><br/>
			</p>
			<p>
				<form:label path="billingCity">City <b style="color:red">*</b></form:label>
				<form:input path="billingCity"/>
				<form:errors path="billingCity" cssErrorClass="error"/><br/>
			</p>
			<p>
				<form:label path="billingPostcode">Postcode <b style="color:red">*</b></form:label>
				<form:input path="billingPostcode"/>
				<form:errors path="billingPostcode" cssErrorClass="error"/><br/>
			</p>
			<p>
				<form:label path="billingPhone">Phone</form:label>
				<form:input path="billingPhone"/>
				<form:errors path="billingPhone" cssErrorClass="error"/><br/>
			</p>
		</div>
		<div class="register_btn">
			<form:checkbox id="acceptTerms" path="acceptTerms"/> 
			<label for="acceptTerms">Accept Terms of Usage</label><br/>
			<form:errors path="acceptTerms" cssErrorClass="error"/><br/><br/>
			<input type="submit" value="Register"/>
		</div>
	</form:form>
</div>
<script src="resources/js/controllers/AddressController.js"></script>
