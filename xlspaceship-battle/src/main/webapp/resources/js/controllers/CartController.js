'use strict';

var cartApp = angular.module('cartApp', []);
cartApp.controller('cartController', function($scope, $http) {
	
	$scope.loadCartData = function() {	
		$http({method: 'GET', url: "cart/rest"}).
		then(function(response) {
			console.log('Cart data!!!');
			console.log(response.data);
			
			$scope.cartItems = response.data.items;
			$scope.subtotal = response.data.subtotal;
			$scope.deliveryCost = response.data.deliveryCost;
			$scope.total = response.data.total;
		});
	};

    $scope.updateCartItem = function(itemId, quantity) {
		$http({method: 'GET', url: "cart/rest/update/" +  itemId + "/" + quantity}).
		then(function(response) {
			$scope.loadCartData();
		},
		function(error) {
			console.log(error);
			$scope.loadCartData();
		});
    };

    $scope.deleteCartItem = function(itemId) {
		$http({method: 'GET', url: "cart/rest/delete/" +  itemId}).
		then(function(response) {
			$scope.loadCartData();
		});
    };

    $scope.loadCartData();
    
});