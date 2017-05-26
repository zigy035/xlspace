package com.xlspaceship.battle.service;

import com.xlspaceship.battle.model.Player;

public interface PlayerService {
	
	Player getPlayer(Integer id);
	
	void addPlayer(Player player);
}
