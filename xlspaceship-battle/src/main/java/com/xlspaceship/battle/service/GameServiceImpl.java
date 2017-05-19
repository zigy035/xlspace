package com.xlspaceship.battle.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.xlspaceship.battle.dao.GameDAO;
import com.xlspaceship.battle.dao.PlayerDAO;
import com.xlspaceship.battle.dao.SpaceShipDAO;
import com.xlspaceship.battle.enumeration.ShotStatus;
import com.xlspaceship.battle.model.Game;
import com.xlspaceship.battle.model.Player;
import com.xlspaceship.battle.model.Shot;
import com.xlspaceship.battle.model.SpaceShip;

@Transactional
public class GameServiceImpl implements GameService {
	
	@Autowired
	GameDAO gameDAO;
	
	@Autowired
	PlayerDAO playerDAO;
	
	@Autowired
	SpaceShipDAO spaceShipDAO;
	
	@Override
	public Game getGameInfo(Integer gameId) {
		return gameDAO.getGameInfo(gameId);
	}

	@Override
	public void addGame(Game game, Player playerOne, Player playerTwo) {
		playerDAO.addPlayer(playerOne);
		for (SpaceShip spaceShip : playerOne.getSpaceships()) {
			spaceShip.setPlayer(playerOne);
			spaceShipDAO.addSpaceShip(spaceShip);
		}
		playerDAO.addPlayer(playerTwo);
		for (SpaceShip spaceShip : playerTwo.getSpaceships()) {
			spaceShip.setPlayer(playerTwo);
			spaceShipDAO.addSpaceShip(spaceShip);
		}
		game.setPlayerOne(playerOne);
		game.setPlayerTwo(playerTwo);
		gameDAO.addGame(game);
	}

	@Override
	public List<Player> getAvailableOpponents() {
//		return playerDAO.getAvailableOpponents();
		return Arrays.asList(new Player[] { new Player("opponent-1"), new Player("opponent-2"), new Player("opponent-3")});
	}
	
	@Override
	public List<Shot> shotAndGetResults(List<Shot> shots, Integer gameId) {
		Game game = gameDAO.getGameInfo(gameId);
		List<SpaceShip> spaceShips = spaceShipDAO.getPlayerSpaceShips(game.getPlayerTwo().getId(), gameId);
		for (Shot shot : shots) {
			for (SpaceShip spaceShip : spaceShips) {
				if (spaceShip.getRow().equals(shot.getRow()) && spaceShip.getCol().equals(shot.getCol())) {
					//delete ship + mark shot as hit
					spaceShipDAO.deleteSpaceShip(spaceShip);
					shot.setStatus(ShotStatus.HIT);
				} else {
					// mark shot as miss
					shot.setStatus(ShotStatus.MISSED);
				}
			}
			
		}
		return shots;
	}
	
	public void setGameDAO(GameDAO gameDAO) {
		this.gameDAO = gameDAO;
	}
	
	public void setPlayerDAO(PlayerDAO playerDAO) {
		this.playerDAO = playerDAO;
	}

	public void setSpaceShipDAO(SpaceShipDAO spaceShipDAO) {
		this.spaceShipDAO = spaceShipDAO;
	}

}
