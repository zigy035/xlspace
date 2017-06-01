package com.xlspaceship.battle.dao;

import java.util.List;

import com.xlspaceship.battle.model.Shot;

public interface ShotDAO {
	
	Shot getShot(String gameId, String playerId, Integer row, Integer col);
	
	List<Shot> getShots(String gameId, String playerId);
	
	void addShot(Shot shot);

	void updateShot(Shot shot);
	
	void removeShot(Shot shot);

}
