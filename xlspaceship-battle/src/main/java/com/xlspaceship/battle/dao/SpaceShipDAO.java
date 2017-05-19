package com.xlspaceship.battle.dao;

import java.util.List;

import com.xlspaceship.battle.model.SpaceShip;

public interface SpaceShipDAO {
	
	void addSpaceShip(SpaceShip spaceShip);
	
	List<SpaceShip> getPlayerSpaceShips(Integer playerId, Integer gameId);

	void deleteSpaceShip(SpaceShip spaceShip);
	
}