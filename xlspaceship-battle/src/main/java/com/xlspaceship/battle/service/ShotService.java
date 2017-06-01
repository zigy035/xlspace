package com.xlspaceship.battle.service;

import java.util.List;

import com.xlspaceship.battle.model.Shot;

public interface ShotService {
	
	List<Shot> getShots(String gameId, String playerId);
}
