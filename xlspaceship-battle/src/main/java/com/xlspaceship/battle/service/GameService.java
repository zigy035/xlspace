package com.xlspaceship.battle.service;

import java.util.List;

import com.xlspaceship.battle.model.Game;
import com.xlspaceship.battle.model.Player;

public interface GameService {
	
	Game getGameInfo(Integer gameId);
	
	List<Player> getAvailableOpponents();
	
	void addGame(Game game, Player player);
}
