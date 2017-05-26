package com.xlspaceship.battle.service.impl;

import com.xlspaceship.battle.dao.PlayerDAO;
import com.xlspaceship.battle.model.Player;
import com.xlspaceship.battle.service.PlayerService;

public class PlayerServiceImpl implements PlayerService {
	
	PlayerDAO playerDAO;
	
	@Override
	public Player getPlayer(Integer id) {
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
