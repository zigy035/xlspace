package com.xlspaceship.battle.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import com.xlspaceship.battle.dao.PlayerDAO;
import com.xlspaceship.battle.model.Player;
import com.xlspaceship.battle.model.Shot;

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

}
