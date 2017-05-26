package com.xlspaceship.battle.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import com.xlspaceship.battle.dao.PlayerDAO;
import com.xlspaceship.battle.model.Player;
import com.xlspaceship.battle.model.Shot;
import com.xlspaceship.battle.model.SpaceShip;

public class PlayerDAOImpl implements PlayerDAO {
	
	private EntityManager entityManager;
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public Player getPlayer(String playerId) {
		return (Player) entityManager
				.createQuery("FROM Player p WHERE id = :playerId")
				.setParameter("playerId", playerId)
				.getSingleResult();
	}

	@Override
	public void addPlayer(Player player) {
		entityManager.persist(player);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Player> getAvailableOpponents() {
		return (List<Player>) entityManager
				.createQuery("FROM Player p "
						+ "WHERE p.id NOT IN "
						+ "(SELECT g.playerOne FROM Game g)")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Shot> getPlayerShots() {
		return (List<Shot>)entityManager.createQuery("FROM Shot s "
				+ "WHERE s.playerId = :playerId AND s.gameId = :gameId")
				.getResultList();
	}

	/*@SuppressWarnings("unchecked")
	@Override
	public List<SpaceShip> getPlayerSpaceShips(Integer playerId, Integer gameId) {
		return (List<SpaceShip>)entityManager.createQuery("FROM SpaceShip s "
				+ "JOIN Player p ON s.playerId = p.id "
				+ "JOIN Game g ON g.playerOne.id = p.id "
				+ "WHERE s.playerId = :playerId AND p.gameId = :gameId")
				.setParameter("playerId", playerId)
				.setParameter("gameId", gameId)
				.getResultList();
	}*/

}
