package com.xlspaceship.battle.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "player")
public class Player {
	
	@Id
	@GenericGenerator(name = "player_keygen", strategy = "com.xlspaceship.battle.util.PlayerKeyGenerator")
    @GeneratedValue(generator = "player_keygen")
	@Column(name = "id")
	private String id;
	
	@Column(name = "full_name")
	private String fullName;
	
	@Transient
	private List<SpaceShip> spaceships;
	
	public Player() {}
	
	public Player(String fullName) {
		this.fullName = fullName;
	}

	public String getId() {
		return id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public List<SpaceShip> getSpaceships() {
		return spaceships;
	}

	public void setSpaceships(List<SpaceShip> spaceships) {
		this.spaceships = spaceships;
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
		Player other = (Player) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
