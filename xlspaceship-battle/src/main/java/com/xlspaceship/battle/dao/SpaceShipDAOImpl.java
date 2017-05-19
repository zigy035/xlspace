package com.xlspaceship.battle.dao;

import java.util.List;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<SpaceShip> getPlayerSpaceShips(Integer playerId, Integer gameId) {
		return (List<SpaceShip>)entityManager.createQuery("FROM SpaceShip s "
				+ "JOIN Player p ON s.playerId = p.id "
				+ "JOIN Game g ON g.playerOne.id = p.id "
				+ "WHERE s.playerId = :playerId AND p.gameId = :gameId")
				.getResultList();
	}

	@Override
	public void deleteSpaceShip(SpaceShip spaceShip) {
		entityManager.remove(spaceShip);
	}
}
