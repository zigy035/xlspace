package com.xlspaceship.battle.dao;

import com.xlspaceship.battle.model.Game;

public interface GameDAO {
	
	Game getGameInfo(String gameId);
	
	void addGame(Game game);
	
	void updateGame(Game game);
}
