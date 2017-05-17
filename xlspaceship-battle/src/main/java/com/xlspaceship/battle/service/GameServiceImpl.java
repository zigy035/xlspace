package com.xlspaceship.battle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.xlspaceship.battle.dao.GameDAO;
import com.xlspaceship.battle.dao.PlayerDAO;
import com.xlspaceship.battle.model.Game;
import com.xlspaceship.battle.model.Player;

@Transactional
public class GameServiceImpl implements GameService {
	
	@Autowired
	GameDAO gameDAO;
	
	@Autowired
	PlayerDAO playerDAO;
	
	@Override
	public Game getGameInfo(Integer gameId) {
		return gameDAO.getGameInfo(gameId);
	}

	@Override
	public void addGame(Game game, Player player) {
		playerDAO.addPlayer(player);
		gameDAO.addGame(game);
	}

	@Override
	public List<Player> getAvailableOpponents() {
		return playerDAO.getAvailableOpponents();
	}
	public void setGameDAO(GameDAO gameDAO) {
		this.gameDAO = gameDAO;
	}
	
	public void setPlayerDAO(PlayerDAO playerDAO) {
		this.playerDAO = playerDAO;
	}
	
}
