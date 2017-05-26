package com.xlspaceship.battle.service.impl;

import java.util.List;

import com.xlspaceship.battle.dao.SpaceShipDAO;
import com.xlspaceship.battle.model.SpaceShip;
import com.xlspaceship.battle.service.SpaceShipService;

public class SpaceShipServiceImpl implements SpaceShipService {
	
	private SpaceShipDAO spaceShipDAO;
	
	@Override
	public List<SpaceShip> getPlayerSpaceShips(Integer playerId) {
		return spaceShipDAO.getPlayerSpaceShips(playerId);
	}

	// Inject DAO
	public void setSpaceShipDAO(SpaceShipDAO spaceShipDAO) {
		this.spaceShipDAO = spaceShipDAO;
	}

}
