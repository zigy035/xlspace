<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="addProductInfo">
	<div class="popupHeader">
		<h3>Product Info</h3>
		<a href="javascript:void(0)" onclick="$().colorbox.close();">
			<img src="resources/css/images/close.png" alt="CLOSE">
		</a>
	</div>
	<div class="popupBody">
		<img src="resources/images/product_na.gif" alt="N/A"/>
		<p id="prodName"></p>
		<p id="cartQty"></p>
		
		<c:url var="cartURL" value="/cart" />
		<a class="btn btn_link" href="${cartURL}">Check your cart</a>
	</div>
</div>

