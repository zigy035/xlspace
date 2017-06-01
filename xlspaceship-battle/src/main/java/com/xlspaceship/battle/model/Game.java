package com.xlspaceship.battle.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "game")
public class Game {
	
	@Id
	@GenericGenerator(name = "game_keygen", strategy = "com.xlspaceship.battle.util.GameKeyGenerator")
    @GeneratedValue(generator = "game_keygen")
	@Column(name = "id")
	private String id;
	
	@ManyToOne(targetEntity = Player.class)
	@JoinColumn(name = "player_one_id", nullable = false)
	private Player playerOne;
	
	@ManyToOne(targetEntity = Player.class)
	@JoinColumn(name = "player_two_id", nullable = false)
	private Player playerTwo;

	@ManyToOne(targetEntity = Player.class)
	@JoinColumn(name = "player_turn_id", nullable = false)
	private Player playerTurn;

	@Column(name = "hostname")
	private String hostname;
	
	@Column(name = "port")
	private String port;

	public String getId() {
		return id;
	}

	public Player getPlayerOne() {
		return playerOne;
	}

	public void setPlayerOne(Player playerOne) {
		this.playerOne = playerOne;
	}

	public Player getPlayerTwo() {
		return playerTwo;
	}

	public void setPlayerTwo(Player playerTwo) {
		this.playerTwo = playerTwo;
	}

	public Player getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(Player playerTurn) {
		this.playerTurn = playerTurn;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}


