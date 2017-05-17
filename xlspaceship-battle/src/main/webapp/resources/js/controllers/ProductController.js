'use strict';

var productApp = angular.module('productApp', []);

productApp.controller('gameController', function($scope, $http) {

	$scope.loadProducts = function() {	
		$http({method: 'GET', url: "product/rest"}).
		then(function(response) {
			console.log(response);
			$scope.products = response.data;
		});
	};
	
	$scope.createNewGame = function(productId) {
		$http({method: 'POST', url: "cart/rest/add/" + productId}).
		then(function(response) {
			
			console.log(response);
			
			$.colorbox({
				transition: 'fade',
//				href: '#addProductInfo', 
				href: 'resources/html/addProductInfo.jsp', 
				open: true,
				onComplete: function() {
					$.colorbox.resize();
					$('#prodName').html('<b>Product:</b>&nbsp;&nbsp;&nbsp;' + response.data.productName);
					$('#cartQty').html('<b>Quantity:</b>&nbsp;&nbsp;' + response.data.quantity);
				},
				onClosed: function() {
					
				}
			});
			
		});
    };
    
	$scope.loadProducts();
	
});

productApp.controller('productController', function($scope, $http) {

	$scope.loadProducts = function() {	
		$http({method: 'GET', url: "product/rest"}).
		then(function(response) {
			console.log(response);
			$scope.products = response.data;
		});
	};
	
	$scope.addCartItem = function(productId) {
		$http({method: 'POST', url: "cart/rest/add/" + productId}).
		then(function(response) {
			
			console.log(response);
			
			$.colorbox({
				transition: 'fade',
//				href: '#addProductInfo', 
				href: 'resources/html/addProductInfo.jsp', 
				open: true,
				onComplete: function() {
					$.colorbox.resize();
					$('#prodName').html('<b>Product:</b>&nbsp;&nbsp;&nbsp;' + response.data.productName);
					$('#cartQty').html('<b>Quantity:</b>&nbsp;&nbsp;' + response.data.quantity);
				},
				onClosed: function() {
					
				}
			});
			
		});
    };
    
	$scope.loadProducts();
	
});