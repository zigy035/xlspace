package com.xlspaceship.battle.dto;

public class GameDTO {
	
	private String playerId;//: "player",
	private String fullName; //": "Assessment Player",
	private String gameId; //": "match-1",
	private String starting; //": "xebialabs-1"
	private String table;
	
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
	
}
