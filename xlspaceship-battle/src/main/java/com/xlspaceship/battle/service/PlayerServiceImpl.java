package com.xlspaceship.battle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xlspaceship.battle.dao.PlayerDAO;
import com.xlspaceship.battle.model.Player;
import com.xlspaceship.battle.model.SpaceShip;

public class PlayerServiceImpl implements PlayerService {
	
	@Autowired
	PlayerDAO playerDAO;
	
	@Override
	public Player getPlayer(String id) {
		return playerDAO.getPlayer(id);
	}

	@Override
	public void addPlayer(Player player) {
		playerDAO.addPlayer(player);
 	}

	public void setPlayerDAO(PlayerDAO playerDAO) {
		this.playerDAO = playerDAO;
	}

	@Override
	public List<SpaceShip> getSpaceships(Integer playerId, Integer gameId) {
		return playerDAO.getPlayerSpaceShips(playerId, gameId);
	}
	
}
