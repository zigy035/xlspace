package com.xlspaceship.battle.service.impl;

import java.util.List;

import com.xlspaceship.battle.dao.ShotDAO;
import com.xlspaceship.battle.model.Shot;
import com.xlspaceship.battle.service.ShotService;

public class ShotServiceImpl implements ShotService {
	
	private ShotDAO shotDAO;
	
	@Override
	public List<Shot> getShots(String gameId, String playerId) {
		return shotDAO.getShots(gameId, playerId);
	}

	// Inject DAO
	public void setShotDAO(ShotDAO shotDAO) {
		this.shotDAO = shotDAO;
	}
	
}
