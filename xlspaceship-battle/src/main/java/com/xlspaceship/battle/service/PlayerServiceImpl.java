package com.xlspaceship.battle.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.xlspaceship.battle.dao.PlayerDAO;
import com.xlspaceship.battle.model.Player;

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
	
}
