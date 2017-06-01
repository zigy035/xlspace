package com.xlspaceship.battle.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.xlspaceship.battle.dao.ShotDAO;
import com.xlspaceship.battle.model.Shot;

public class ShotDAOImpl implements ShotDAO {
	
	private EntityManager entityManager;
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public Shot getShot(String gameId, String playerId, Integer row, Integer col) {
		try {
			return (Shot) entityManager.createQuery("FROM Shot s "
					+ "WHERE s.game.id = :gameId AND s.player.id = :playerId "
					+ "AND s.row = :row AND s.col = :col")
					.setParameter("gameId", gameId)
					.setParameter("playerId", playerId)
					.setParameter("row", row)
					.setParameter("col", col)
					.getSingleResult();
		} catch (NoResultException nre){
			//Ignore this because as per your logic this is ok!
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Shot> getShots(String gameId, String playerId) {
		return entityManager.createQuery("FROM Shot s "
				+ "WHERE s.game.id = :gameId AND s.player.id = :playerId ")
				.setParameter("gameId", gameId)
				.setParameter("playerId", playerId)
				.getResultList();
	}
	
	@Override
	public void addShot(Shot shot) {
		entityManager.persist(shot);
	}

	@Override
	public void updateShot(Shot shot) {
		entityManager.merge(shot);
	}

	@Override
	public void removeShot(Shot shot) {
		entityManager.remove(shot);
	}

}
