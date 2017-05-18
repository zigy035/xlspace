package com.xlspaceship.battle.dao;

import javax.persistence.EntityManager;

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
}
