package com.xlspaceship.battle.service;

import java.util.List;

import com.xlspaceship.battle.model.Player;
import com.xlspaceship.battle.model.SpaceShip;

public interface PlayerService {
	
	Player getPlayer(String id);
	
	void addPlayer(Player player);

	List<SpaceShip> getSpaceships(Integer playerId, Integer gameId);
}
