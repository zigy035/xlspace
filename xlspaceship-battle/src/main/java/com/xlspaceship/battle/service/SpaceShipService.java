package com.xlspaceship.battle.service;

import java.util.List;

import com.xlspaceship.battle.model.SpaceShip;

public interface SpaceShipService {
	
	List<SpaceShip> getPlayerSpaceShips(Integer playerId);
}
