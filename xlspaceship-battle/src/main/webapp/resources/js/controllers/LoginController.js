'use strict';

var LoginController = function($scope, $http, $location) {
	
	$scope.validateUser = function() {	
		$http({method: 'GET', url: "login"}).
		then(function(response) {
//			console.log(response);
			console.log('User logged in!');
			$location.path('/cart');
		},
		function(error) {
			console.log('Annonymous user!');
			$location.path('/login');
		});
	};
	
	$scope.login = function() {
		$http({method: 'POST', url: "login/" + $scope.username + "/" + $scope.password}).
		then(function(response) {
			$scope.customer = response;
			$location.path('/cart');
		},
		function(error) {
			console.log(error);
			$scope.errorMessage = "Invalid Email and/or Password!";
		});
    };
    
    $scope.validateUser();
		
};