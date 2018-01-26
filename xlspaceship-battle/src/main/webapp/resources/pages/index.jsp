<h2>XL Battle Game</h2>

<!-- <div ng-app="gameApp" ng-controller="gameController">
	
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
		
		<textarea id="qual" rows="16" cols="32" style="resize:none" disabled="disabled">{{game.table}}</textarea>
		
		<form id="fireForm" ng-submit="fireSalvo()">
			<input type="hidden" name="gameId" ng-model="game.gameId" required/>
		    
		    <div ng-repeat="data in salvoForm.salvo track by $index">
		    	<input ng-model="salvoForm.salvo[$index]" type="text" style="width:50px" placeholder="RxC" required/>
		    </div>
        	
        	<input class="btn btn_link" type="submit" value="Fire" style="width:150px" />
		</form>
		
		<form id="receiveForm" ng-submit="receiveSalvo()">
			<input type="hidden" name="gameId" ng-model="game.gameId" required/>
        	<input class="btn btn_link" type="submit" value="Receive" style="width:150px" />
		</form>
		
	</div>
	
	<script src="resources/js/controllers/GameController.js"></script>
</div> -->

<!-- Book CRUD -->
	
<div ng-app="bookApp" ng-controller="bookController">
	
	<form id="saveBook" ng-submit="saveBook()">
		<table>
			<tr>
				<td><label for="bookId">Book Id <b style="color:red">*</b></label></td>
				<td><input ng-model="bookForm.bookId" type="text"/></td>
			</tr>
			<tr>
				<td><label for="title">Title<b style="color:red">*</b></label></td>
				<td><input ng-model="bookForm.title" type="text" required/></td>
			</tr>
			<tr>
				<td><label for="author">Author<b style="color:red">*</b></label></td>
				<td><input ng-model="bookForm.author" type="text" required/></td>
			</tr>
			<tr>
				<td><label for="category">Category<b style="color:red">*</b></label></td>
				<td><input ng-model="bookForm.category" type="text" required/></td>
			</tr>
		</table>
		<p>
			<input class="btn btn_link" type="submit" value="Save Book" style="width: 150px" />
		</p>
	</form>

	<div ng-show="books.length > 0">
		<table class="stripeMe sample">
			<thead>
				<tr>
					<th>Book ID</th>
					<th>Title</th>
					<th>Author</th>
					<th>Category</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="book in books">
					<td>{{book.bookId}}</td>
					<td>{{book.title}}</td>
					<td>{{book.author}}</td>
					<td>{{book.category}}</td>
					<td>
						<a href="javascript:void(0)" ng-click="editBook(book.bookId)">Edit</a>
						<a href="javascript:void(0)" ng-click="deleteBook(book.bookId)">Delete</a>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<script src="resources/js/controllers/BookController.js"></script>
</div>
	
