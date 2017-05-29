package com.xlspaceship.battle.validator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.xlspaceship.battle.dao.ShotDAO;
import com.xlspaceship.battle.dao.SpaceShipDAO;
import com.xlspaceship.battle.exception.InvalidFormatException;
import com.xlspaceship.battle.exception.PlayerTwoTurnException;
import com.xlspaceship.battle.exception.SelfShotException;
import com.xlspaceship.battle.exception.ShotCountExceededException;
import com.xlspaceship.battle.exception.ShotExistsException;
import com.xlspaceship.battle.form.FireSalvoForm;
import com.xlspaceship.battle.model.Game;
import com.xlspaceship.battle.model.Shot;
import com.xlspaceship.battle.model.SpaceShip;

public class GameValidator {
	
	private static final String HEX_CHARS = "0123456789ABCDEF";
	
	private ShotDAO shotDAO;
	
	private SpaceShipDAO spaceShipDAO;
	
	public List<Shot> fireSalvoValidate(FireSalvoForm fireSalvoForm, Game game) throws Exception {
		
		// da li je na njega red da gadja (playerTurn) - OVO!!!
		if (!game.getPlayerOne().getId().equals(game.getPlayerTurn().getId())) {
			throw new PlayerTwoTurnException();
		}
		
		List<String> salvo = fireSalvoForm.getSalvo();
		
		List<Shot> shots = new ArrayList<>();
		if (CollectionUtils.isEmpty(salvo)) {
			throw new InvalidFormatException();
		}
		
		for (String salvoItem : salvo) {
			if (StringUtils.isBlank(salvoItem) || salvoItem.length() != 3) {
				throw new InvalidFormatException();
			}
			
			char[] charr = salvoItem.toCharArray();
			if (HEX_CHARS.indexOf(charr[0]) == -1 || charr[1] != 'x' || HEX_CHARS.indexOf(charr[2]) == -1) {
				throw new InvalidFormatException();
			}
			int row = Integer.valueOf(String.valueOf(charr[0]), 16);
			int col = Integer.valueOf(String.valueOf(charr[2]), 16);
			
			Shot shot = new Shot();
			shot.setGame(game);
			shot.setPlayer(game.getPlayerOne());
			shot.setRow(row);
			shot.setCol(col);
			shots.add(shot);
		}
		
		// broj shotova == broj ship-ova
		List<SpaceShip> spaceShips = spaceShipDAO.getPlayerSpaceShips(game.getPlayerOne().getId());
		if (shots.size() > spaceShips.size()) {
			throw new ShotCountExceededException();
		}

		// proveri da li ne gadja svoje brodove :)
		for (Shot shot : shots) {
			for (SpaceShip spaceShip : spaceShips) {
				if (shot.getRow().equals(spaceShip.getRow()) && shot.getCol().equals(spaceShip.getCol())) {
					throw new SelfShotException();
				}
			}
		}
		
		
		// da li je vec gadjao tu (shot exist)
		List<Shot> existShots = shotDAO.getShots(game.getId(), game.getPlayerOne().getId());
		for (Shot shot : shots) {
			for (Shot exShot : existShots) {
				if (shot.getRow().equals(exShot.getRow()) && shot.getCol().equals(exShot.getCol())) {
					throw new ShotExistsException();
				}
			}
		}
				
		return shots;
	}

	public void setShotDAO(ShotDAO shotDAO) {
		this.shotDAO = shotDAO;
	}

	public void setSpaceShipDAO(SpaceShipDAO spaceShipDAO) {
		this.spaceShipDAO = spaceShipDAO;
	}
	
}
