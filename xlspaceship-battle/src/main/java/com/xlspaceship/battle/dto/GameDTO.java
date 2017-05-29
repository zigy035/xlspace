package com.xlspaceship.battle.dto;

public class GameDTO {
	
	private String playerId;//: "player",
	private String fullName; //": "Assessment Player",
	private String gameId; //": "match-1",
	private String starting; //": "xebialabs-1"
	private String table;
	private String error;
	private String playerTurn;
	private Long playerTurnShipCount;
	
	public String getPlayerId() {
		return playerId;
	}
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public String getStarting() {
		return starting;
	}
	public void setStarting(String starting) {
		this.starting = starting;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getPlayerTurn() {
		return playerTurn;
	}
	public void setPlayerTurn(String playerTurn) {
		this.playerTurn = playerTurn;
	}
	public Long getPlayerTurnShipCount() {
		return playerTurnShipCount;
	}
	public void setPlayerTurnShipCount(Long playerTurnShipCount) {
		this.playerTurnShipCount = playerTurnShipCount;
	}
	
}
