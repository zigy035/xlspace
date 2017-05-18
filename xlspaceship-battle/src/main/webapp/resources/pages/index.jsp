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
	
	<div ng-show="gameId">
		<p>Game ID: {{gameId}}</p>
		<p>Opponent Full Name: {{fullName}}</p>
		<p>Opponent Player ID: {{playerId}}</p>
		<p>Starting: {{starting}}</p>
		<p></p>
		<input type="textarea" value="{{table}}" style="width: 200px;"/>
		
<!-- 		<a href=""></a> -->
	</div>
	
	<script src="resources/js/controllers/GameController.js"></script>
</div>