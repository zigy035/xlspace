'use strict';

var gameApp = angular.module('gameApp', ['ngCookies', 'ngDialog']);
gameApp.controller('gameController', function($scope, $http, $cookieStore, ngDialog) {
    
    $scope.createNewGame = function() {    	
		$http({
			method: 'POST', 
			url: "protocol/game/new", 
			data: {
	    		playerId : $scope.gameForm.playerId,
	    		fullName : $scope.gameForm.fullName,
	    		formation : $scope.gameForm.formation,
	    		hostname : $scope.gameForm.hostname,
	    		port : $scope.gameForm.port
	        }, 
		}).then(function(response) {
			
			console.log(response.data);
			
			$scope.gameId = response.data.gameId;
			$scope.fullName = response.data.fullName;
			$scope.playerId = response.data.playerId;
			$scope.starting = response.data.starting;
			$scope.table = response.data.table;
		});
    };
    
    $scope.fireSalvo = function(gameId) {    	
		$http({
			method: 'PUT', 
			url: "protocol/user/game/" + gameId + "/fire", 
			data: {
	    		salvo : $scope.salvo
	        }, 
		}).then(function(response) {
			
			console.log(response.data);
			
			$scope.gameId = response.data.gameId;
			$scope.fullName = response.data.fullName;
			$scope.playerId = response.data.playerId;
			$scope.starting = response.data.starting;
			$scope.table = response.data.table;
		});
    };
    
    
	
});