'use strict';

var gameApp = angular.module('gameApp', ['ngCookies', 'ngDialog']);
gameApp.controller('gameController', function($scope, $http, $cookieStore, ngDialog) {
    
    $scope.createNewGame = function() {    	
		$http({
			method: 'POST', 
			url: "protocol/game/new", 
			data: {
	    		playerId : $scope.gameForm.playerId,
	    		hostname : $scope.gameForm.hostname,
	    		port : $scope.gameForm.port
	        }, 
		}).then(function(response) {
			
			console.log(response.data);
			
			$scope.game = response.data;
		});
    };
	
});