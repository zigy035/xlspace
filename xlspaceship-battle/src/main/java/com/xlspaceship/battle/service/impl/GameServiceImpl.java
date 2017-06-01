package com.xlspaceship.battle.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xlspaceship.battle.dao.GameDAO;
import com.xlspaceship.battle.dao.PlayerDAO;
import com.xlspaceship.battle.dao.ShotDAO;
import com.xlspaceship.battle.dao.SpaceShipDAO;
import com.xlspaceship.battle.enumeration.ShotStatus;
import com.xlspaceship.battle.model.Game;
import com.xlspaceship.battle.model.Player;
import com.xlspaceship.battle.model.Shot;
import com.xlspaceship.battle.model.SpaceShip;
import com.xlspaceship.battle.service.GameService;

@Transactional
public class GameServiceImpl implements GameService {
	
	GameDAO gameDAO;
	
	PlayerDAO playerDAO;
	
	SpaceShipDAO spaceShipDAO;
	
	ShotDAO shotDAO; 
	
	@Override
	public Game getGameInfo(String gameId) {
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
		game.setPlayerTurn(playerOne);
		gameDAO.addGame(game);
	}

	@Override
	public List<Player> getAvailableOpponents() {
//		return playerDAO.getAvailableOpponents();
		return Arrays.asList(new Player[] { new Player("opponent-1"), new Player("opponent-2"), new Player("opponent-3")});
	}
	
	@Override
	public void shootSalvo(List<Shot> shots, String gameId, String playerOneId, String playerTwoId) {
		List<SpaceShip> spaceShips = spaceShipDAO.getPlayerSpaceShips(playerTwoId);
		for (Shot shot : shots) {
			for (SpaceShip spaceShip : spaceShips) {
				shot.setStatus(ShotStatus.MISSED);
				if (spaceShip.getRow().equals(shot.getRow()) && spaceShip.getCol().equals(shot.getCol())) {
					// mark shot as hit and delete ship 
					shot.setStatus(ShotStatus.HIT);
					spaceShipDAO.deleteSpaceShip(spaceShip);
					break;
				}
			}
			saveShot(shot);
		}
		Game game = gameDAO.getGameInfo(gameId);
		game.setPlayerTurn(playerDAO.getPlayer(playerTwoId));
		gameDAO.updateGame(game);
	}
	
	private void saveShot(Shot shot) {
		Shot existShot = shotDAO.getShot(shot.getGame().getId(), shot.getPlayer().getId(), shot.getRow(), shot.getCol());
		if (existShot == null) {
			shotDAO.addShot(shot);
		} else {
			existShot.setStatus(shot.getStatus());
			shotDAO.updateShot(existShot);
		}
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

	public void setShotDAO(ShotDAO shotDAO) {
		this.shotDAO = shotDAO;
	}

}
