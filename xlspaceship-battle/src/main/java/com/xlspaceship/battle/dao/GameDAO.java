package com.xlspaceship.battle.dao;

import com.xlspaceship.battle.model.Game;

public interface GameDAO {
	
	Game getGameInfo(Integer gameId);
	
	void addGame(Game game);
	
	void updateGame(Game game);
}
