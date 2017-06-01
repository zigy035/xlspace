package com.xlspaceship.battle.service;

import java.util.List;

import com.xlspaceship.battle.model.Game;
import com.xlspaceship.battle.model.Player;
import com.xlspaceship.battle.model.Shot;

public interface GameService {
	
	Game getGameInfo(String gameId);
	
	List<Player> getAvailableOpponents();
	
	void addGame(Game game, Player playerOne, Player playerTwo);
	
	void shootSalvo(List<Shot> shots, String gameId, String playerOneId, String playerTwoId);
}
