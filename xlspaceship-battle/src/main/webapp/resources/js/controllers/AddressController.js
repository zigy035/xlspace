'use strict';

var addressApp = angular.module('addressApp', ['ngCookies']);
addressApp.controller('addressController', function($scope, $http, $cookieStore) {

	$scope.loadTitles = function() {
		$http({method: 'GET', url: "address/rest/titles"}).
		then(function(response) {
			$scope.titles = response.data;
			console.log('Titleees: ' + response.data);
			
			var title = $cookieStore.get('title');
			console.log('Current Title: ' + title);
			
			if (title != undefined) {
				$scope.title = title;
			} else {
				$scope.title = '';
			}
		});
	};
	
	$scope.storeTitleData = function(title) {
		console.log('Changed Title to: ' + title);
		$cookieStore.put('title', title);
	};
	
	// Shipping Country Region
	$scope.loadShippingCountries = function() {	
		$http({method: 'GET', url: "address/rest/countries"}).
		then(function(response) {

			$scope.shippingCountries = response.data;
			
			var shippingCountry = $cookieStore.get('shippingCountry');
			
			if (shippingCountry != null) {
				$scope.shippingCountry = shippingCountry;
				$scope.loadShippingRegions(shippingCountry);
			} else {
				$scope.shippingCountry = '';
			}
			
		});
	};
	
	$scope.loadShippingRegions = function(shippingCountry) {	
		if (shippingCountry != 'US' && shippingCountry != 'CA') {
			$scope.shippingCountry = '';
			$scope.shippingRegions = null;
			$scope.shippingRegion = '';
			
			$cookieStore.remove('shippingCountry');
			$cookieStore.remove('shippingRegion');
			
		} else {
			$http({method: 'GET', url: "address/rest/regions/" + shippingCountry}).
			then(function(response) {
				
				var shippingRegion = $cookieStore.get('shippingRegion');
				if (shippingRegion != null) {
					$scope.shippingRegion = shippingRegion;
				} else {
					$scope.shippingRegions = null;
					$scope.shippingRegion = '';
				}
				
				$cookieStore.put('shippingCountry', shippingCountry);
				$scope.shippingRegions = response.data;
			});
		}
	};
	
	$scope.storeShippingRegionData = function(shippingRegion) {
		$cookieStore.put('shippingRegion', shippingRegion);
	};
	
	
	// Billing Country Region
	$scope.loadBillingCountries = function() {	
		$http({method: 'GET', url: "address/rest/countries"}).
		then(function(response) {
			
			$scope.billingCountries = response.data;
			
			var billingCountry = $cookieStore.get('billingCountry');
			
			if (billingCountry != null) {
				$scope.billingCountry = billingCountry;
				$scope.loadBillingRegions(billingCountry);
			} else {
				$scope.billingCountry = '';
			}
			
		});
	};
	
	$scope.loadBillingRegions = function(billingCountry) {	
		if (billingCountry != 'US' && billingCountry != 'CA') {
			$scope.billingCountry = '';
			$scope.billingRegions = null;
			$scope.billingRegion = '';
			
			$cookieStore.remove('billingCountry');
			$cookieStore.remove('billingRegion');
			
		} else {
			$http({method: 'GET', url: "address/rest/regions/" + billingCountry}).
			then(function(response) {
				
				var billingRegion = $cookieStore.get('billingRegion');
				if (billingRegion != null) {
					$scope.billingRegion = billingRegion;
				} else {
					$scope.billingRegions = null;
					$scope.billingRegion = '';
				}
				
				$cookieStore.put('billingCountry', billingCountry);
				$scope.billingRegions = response.data;
			});
		}
	};
	
	$scope.storeBillingRegionData = function(billingRegion) {
		$cookieStore.put('billingRegion', billingRegion);
	};
	
	
	// Load Countries/Regions	
	$scope.loadTitles();
	$scope.loadShippingCountries();
	$scope.loadBillingCountries();
	
});