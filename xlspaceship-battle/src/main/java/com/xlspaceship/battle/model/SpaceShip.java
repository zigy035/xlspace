package com.xlspaceship.battle.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="spaceship")
public class SpaceShip {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne(targetEntity = Player.class)
	@JoinColumn(name = "player_id", nullable = false)
	private Player player;
	
	@Column(name = "row")
	private Integer row;
	
	@Column(name = "col")
	private Integer col;
	
	public SpaceShip() {}
	
	public SpaceShip(Integer row, Integer col) {
		this.row = row;
		this.col = col;
	}
	
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
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
	
}
