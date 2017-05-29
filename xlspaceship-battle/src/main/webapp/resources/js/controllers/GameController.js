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
			
			$scope.salvo = {
				fields : ['a', 'b', 'c']
			};
			
			$scope.salvoForm = { "salvo": ["0x0", "8x4", "DxA", "AxA", "7xF"] };
			
			/*
				{
					"salvo": ["0x0", "8x4", "DxA", "AxA", "7xF"]
				}
			 */
			
		});
    };
    
    $scope.fireSalvo = function() {  
    	
    	console.log("SALVO SHOOTS: " + $scope.salvoForm.salvo[0]);
    	
		$http({
			method: 'POST', 
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
		});
    };
    
    /*
     var data = $.param({
                firstName: $scope.firstName,
                lastName: $scope.lastName,
                age: $scope.age
            });

            $http.put('/api/Default?'+ data)
            .success(function (data, status, headers) {
                $scope.ServerResponse = data;
            })
            .error(function (data, status, header, config) {
                $scope.ServerResponse =  htmlDecode("Data: " + data +
                    "\n\n\n\nstatus: " + status +
                    "\n\n\n\nheaders: " + header +
                    "\n\n\n\nconfig: " + config);
            });
     */
    
});