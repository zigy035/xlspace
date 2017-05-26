<h2>XL Battle Game</h2>

<div ng-app="gameApp" ng-controller="gameController">
	
	<form id="newGame" ng-submit="createNewGame()" method="post">
		<table>
			<tr>
				<td><label for="playerId">Player Id <b style="color:red">*</b></label></td>
				<td><input ng-model="gameForm.playerId" type="text" required/></td>
			</tr>
			<tr>
				<td><label for="fullName">Full Name <b style="color:red">*</b></label></td>
				<td><input ng-model="gameForm.fullName" type="text" required/></td>
			</tr>
			<tr>
				<td><label for="formation">Format (X,L,A,B or S) <b style="color:red">*</b></label></td>
				<td><input ng-model="gameForm.formation" type="text" required/></td>
			</tr>
			<tr>
				<td><label for="hostname">Hostname <b style="color:red">*</b></label></td>
				<td><input ng-model="gameForm.hostname" type="text" required/></td>
			</tr>
			<tr>
				<td><label for="port">Port <b style="color:red">*</b></label></td>
				<td><input ng-model="gameForm.port" type="text" required/></td>
			</tr>
		</table>
		<p>
			<input class="btn btn_link" type="submit" value="New Game" style="width: 150px" />
		</p>
	</form>
	
	<br/>
	
	<div ng-show="game">
		<p>Game ID: {{game.gameId}}</p>
		<p>Opponent Full Name: {{game.fullName}}</p>
		<p>Opponent Player ID: {{game.playerId}}</p>
		<p ng-show="game.starting">Starting: {{game.starting}}</p>
		<p ng-show="game.playerTurn">Player Turn: {{game.playerTurn}}</p>
		<p style="color:red" ng-show="game.error">{{game.error}}</p>
		
		<textarea id="qual" rows="16" cols="40" style="resize:none">{{game.table}}</textarea>
		
		<form id="fireForm" ng-submit="fireSalvo()" method="post">
			<table>
				<tr>
					<td><label for="gameId">Game ID <b style="color:red">*</b></label></td>
					<td><input ng-model="fireSalvoForm.gameId" type="text" value="{{game.gameId}}" required/></td>
				</tr>
				<tr>
					<td><label for="salvo">Salvo shots <b style="color:red">*</b></label></td>
					<td><input ng-model="fireSalvoForm.salvo" type="text" placeholder="RxC-RxC-...-RxC" required/></td>
				</tr>
			</table>
			<p>
				<input class="btn btn_link" type="submit" value="Fire" style="width: 150px" />
			</p>
		</form>
	</div>
	
	<script src="resources/js/controllers/GameController.js"></script>
</div>