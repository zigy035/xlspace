package com.xlspaceship.battle.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.xlspaceship.battle.enumeration.ShotStatus;

@Entity
@Table(name = "shot", uniqueConstraints={@UniqueConstraint(columnNames = {"game_id", "player_id", "row", "col"})})
public class Shot {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne(targetEntity = Game.class)
	@JoinColumn(name = "game_id", nullable = true)
	private Game game;
	
	@ManyToOne(targetEntity = Player.class)
	@JoinColumn(name = "player_id", nullable = true)
	private Player player;
	
	@Column(name = "row")
	private Integer row;
	
	@Column(name = "col")
	private Integer col;
	
	@Column(name = "status")
	@Enumerated(EnumType.ORDINAL)
	private ShotStatus status;
	
	public Shot() {}
	
	/*public Shot(Integer row, Integer col) {
		this.row = row;
		this.col = col;
	}*/
	
	public Integer getId() {
		return id;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getCol() {
		return col;
	}

	public void setCol(Integer col) {
		this.col = col;
	}

	public ShotStatus getStatus() {
		return status;
	}

	public void setStatus(ShotStatus status) {
		this.status = status;
	}
	
}
