'use strict';

var gameApp = angular.module('gameApp', []);
gameApp.controller('gameController', function($scope, $http) {
    
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
			
			console.log("NEW_GAME: " + response.data);
			
			$scope.game = {
				gameId : response.data.gameId,
				fullName : response.data.fullName,
				playerId : response.data.playerId,
				starting : response.data.starting,
				table : response.data.table,
//				playerTurnShipCount: response.data.playerTurnShipCount
			};
			
			var salvoArr = [];
			for (var i = 0; i < response.data.playerTurnShipCount; i++) {
				salvoArr.push('');
			}
			
			$scope.salvoForm = { "salvo": salvoArr };
//			$scope.salvoForm = { "salvo": ["0x0", "8x4", "DxA", "AxA", "7xF"] };
			
		});
    };
    
    $scope.fireSalvo = function() {  
    	
    	console.log("SALVO SHOOTS: " + $scope.salvoForm.salvo[0]);
    	
		$http({
			method: 'PUT', 
			url: "protocol/user/game/" + $scope.game.gameId + "/fire", 
			data: {
	    		salvo : $scope.salvoForm.salvo
	        }, 
		}).then(function(response) {
			
			console.log("FIRE_SALVO: " + response.data);
			
			$scope.game = {
				gameId : response.data.gameId,
				fullName : response.data.fullName,
				playerId : response.data.playerId,
				table : response.data.table,
				error : response.data.error,
				playerTurn : response.data.playerTurn
			};
			
			var salvoArr = [];
			for (var i = 0; i < response.data.playerTurnShipCount; i++) {
				salvoArr.push('');
			}
			
			$scope.salvoForm = { "salvo": salvoArr };
			
		});
    };
    
    $scope.receiveSalvo = function() {  
    	
    	console.log("GAME_ID: " + $scope.game.gameId);
    	
		$http({
			method: 'PUT', 
			url: "protocol/game/receive/" + $scope.game.gameId, 
//			data: {
//	    		gameId : $scope.fireSalvoForm.gameId,
//	    		salvo : $scope.fireSalvoForm.salvo
//	        }, 
		}).then(function(response) {
			
			console.log("RECEIVE_SALVO: " + response.data);
			
			$scope.game = {
				gameId : response.data.gameId,
				fullName : response.data.fullName,
				playerId : response.data.playerId,
				table : response.data.table,
				error : response.data.error,
				playerTurn : response.data.playerTurn
			};
			
			var salvoArr = [];
			for (var i = 0; i < response.data.playerTurnShipCount; i++) {
				salvoArr.push('');
			}
			
			$scope.salvoForm = { "salvo": salvoArr };
			
		});
    };
    
});