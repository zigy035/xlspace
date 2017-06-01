package com.xlspaceship.battle.dao.impl;

import javax.persistence.EntityManager;

import com.xlspaceship.battle.dao.GameDAO;
import com.xlspaceship.battle.model.Game;

public class GameDAOImpl implements GameDAO {
	
	private EntityManager entityManager;
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public Game getGameInfo(String gameId) {
		return (Game) entityManager
				.createQuery("FROM Game g WHERE g.id = :gameId")
				.setParameter("gameId", gameId)
				.getSingleResult();
	}

	@Override
	public void addGame(Game game) {
		entityManager.persist(game);
	}

	@Override
	public void updateGame(Game game) {
		entityManager.merge(game);
	}
	
}
