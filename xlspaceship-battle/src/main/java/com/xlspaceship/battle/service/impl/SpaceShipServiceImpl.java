package com.xlspaceship.battle.service.impl;

import java.util.List;

import com.xlspaceship.battle.dao.SpaceShipDAO;
import com.xlspaceship.battle.model.SpaceShip;
import com.xlspaceship.battle.service.SpaceShipService;

public class SpaceShipServiceImpl implements SpaceShipService {
	
	private SpaceShipDAO spaceShipDAO;
	
	@Override
	public List<SpaceShip> getPlayerSpaceShips(String playerId) {
		return spaceShipDAO.getPlayerSpaceShips(playerId);
	}

	@Override
	public Long getPlayerSpaceShipsCount(String playerId) {
		return spaceShipDAO.getPlayerSpaceShipsCount(playerId);
	}
	
	// Inject DAO
	public void setSpaceShipDAO(SpaceShipDAO spaceShipDAO) {
		this.spaceShipDAO = spaceShipDAO;
	}

}
