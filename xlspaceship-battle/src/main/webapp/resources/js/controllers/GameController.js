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
			
//			$scope.game = response.data;
			
			$scope.game = {
				gameId : response.data.gameId,
				fullName : response.data.fullName,
				playerId : response.data.playerId,
				starting : response.data.starting,
				table : response.data.table
			};
			
		});
    };
    
    $scope.fireSalvo = function() {  
    	
    	console.log("GAME_ID: ");
    	
		$http({
			method: 'POST', 
//			url: "protocol/user/game/" + gameId + "/fire", 
			url: "protocol/user/game/fire", 
			data: {
	    		gameId : $scope.fireSalvoForm.gameId,
	    		salvo : $scope.fireSalvoForm.salvo
	        }, 
		}).then(function(response) {
			
			console.log("FIRE_SALVO: " + response.data);
			
			$scope.game = {
				gameId : response.data.gameId,
				fullName : response.data.fullName,
				playerId : response.data.playerId,
				table : response.data.table,
				playerTurn : response.data.playerTurn
			};
		});
    };
    
    /*$scope.fireSalvo = function(gid) {
        var data = $.param({
            salvo: $scope.salvo
        });

        $http.put("protocol/user/game/" + gid + "/fire")
        .success(function (data, status, headers) {
        	$scope.table = response.data.table;
        });
        
    };*/
    
	
});