package com.xlspaceship.battle.dao;

import java.util.List;

import com.xlspaceship.battle.model.Shot;

public interface ShotDAO {
	
	Shot getShot(Integer gameId, Integer playerId, Integer row, Integer col);
	
	List<Shot> getShots(Integer gameId, Integer playerId);
	
	void addShot(Shot shot);

	void updateShot(Shot shot);
	
	void removeShot(Shot shot);

}
