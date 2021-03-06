package com.xlspaceship.battle.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import com.xlspaceship.battle.dao.SpaceShipDAO;
import com.xlspaceship.battle.model.SpaceShip;

public class SpaceShipDAOImpl implements SpaceShipDAO {
	
	private EntityManager entityManager;
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void addSpaceShip(SpaceShip spaceShip) {
		entityManager.persist(spaceShip);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SpaceShip> getPlayerSpaceShips(String playerId) {
		return (List<SpaceShip>)entityManager.createQuery("FROM SpaceShip s "
				+ "WHERE s.player.id = :playerId")
				.setParameter("playerId", playerId)
				.getResultList();
	}
	
	@Override
	public Long getPlayerSpaceShipsCount(String playerId) {
		return (Long)entityManager.createQuery("SELECT COUNT(s.id) FROM SpaceShip s "
				+ "WHERE s.player.id = :playerId")
				.setParameter("playerId", playerId)
				.getSingleResult();
	}

	@Override
	public void deleteSpaceShip(SpaceShip spaceShip) {
		entityManager.remove(spaceShip);
	}

}
