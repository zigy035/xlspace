package com.xlspaceship.battle.service;

import com.xlspaceship.battle.model.Player;

public interface PlayerService {
	
	Player getPlayer(String id);
	
	void addPlayer(Player player);
}
