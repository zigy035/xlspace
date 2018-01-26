'use strict';

var bookApp = angular.module('bookApp', []);
bookApp.controller('bookController', function($scope, $http) {
	
	$scope.getBooks = function() {	
		$http({
			method: 'GET', 
			url: "book"
		}).then(function(response) {
			console.log("BOOK_LIST:" + response.data);
			$scope.books = response.data;
		});
	};
	
	$scope.getBook = function(bookId) {	
		$http({
			method: 'GET', 
			url: "book/" + bookId
		}).then(function(response) {
			console.log(response);
			$scope.books = response.data;
		});
	};
	
	$scope.addBook = function() {    	
		$http({
			method: 'POST', 
			url: "book/add", 
			data: {
	    		title : $scope.bookForm.title,
	    		author : $scope.bookForm.author,
	    		category : $scope.bookForm.category
	        }, 
		}).then(function(response) {
			console.log("BOOK_ADDED: " + response.data);
			$scope.getBooks();
			$scope.resetBookForm();
		});
    };
    
    $scope.updateBook = function() {    	
		$http({
			method: 'PUT', 
			url: "book/update", 
			data: {
				bookId : $scope.bookForm.bookId,
				title : $scope.bookForm.title,
	    		author : $scope.bookForm.author,
	    		category : $scope.bookForm.category
	        }, 
		}).then(function(response) {
			console.log("BOOK_UPDATED: " + response.data);
			$scope.getBooks();
			$scope.resetBookForm();
		});
    };
    
    $scope.deleteBook = function(bookId) {	
		$http({
			method: 'DELETE', 
			url: "book/" + bookId
		}).then(function(response) {
			console.log("BOOK_DELETED: " + response.data);
			$scope.getBooks();
			$scope.resetBookForm();
		});
	};
	
	$scope.editBook = function(bookId) {	
		$http({
			method: 'GET', 
			url: "book/" + bookId
		}).then(function(response) {
			console.log("BOOK_EDIT: " + response.data);
			$scope.bookForm = {
				bookId : response.data.bookId,
				title : response.data.title,
				author : response.data.author,
				category : response.data.category
			};
		});
	};
    
	$scope.saveBook = function() {
		console.log("BOOK_SAVE - bookID: " + $scope.bookForm.bookId);
		var bookID = $scope.bookForm.bookId;
		if (bookID === null) {
			$scope.addBook();
		} else {
			$scope.updateBook();
		}
	};
	
    $scope.resetBookForm = function() {
    	
    	$scope.bookForm = {
			bookId : null,
			title : null,
			author : null,
			category : null
		};
    };
    
    $scope.resetBookForm();
    $scope.getBooks();
	
});