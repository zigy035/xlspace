package com.xlspaceship.battle.dao;

import java.util.List;

import com.xlspaceship.battle.model.Player;
import com.xlspaceship.battle.model.Shot;

public interface PlayerDAO {
	
	Player getPlayer(String id);
	
	void addPlayer(Player player);

	List<Player> getAvailableOpponents();
	
	List<Shot> getPlayerShots();
}
