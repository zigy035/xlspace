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
	
	@Column(name = "x")
	private Integer x;
	
	@Column(name = "y")
	private Integer y;
	
	public SpaceShip(Integer x, Integer y) {
		this.x = x;
		this.x = y;
	}

	public Integer getX() {
		return x;
	}

	public Integer getY() {
		return y;
	}
	
}
