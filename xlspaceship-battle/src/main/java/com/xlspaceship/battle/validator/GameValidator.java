package com.xlspaceship.battle.validator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.xlspaceship.battle.dto.GameDTO;
import com.xlspaceship.battle.form.FireSalvoForm;
import com.xlspaceship.battle.model.Game;
import com.xlspaceship.battle.model.Shot;

public class GameValidator {
	
	private static final String HEX_CHARS = "0123456789abcdef";
	
	public List<Shot> fireSalvoValidate(GameDTO gameDTO, FireSalvoForm fireSalvoForm, Game game) {
		
		// da li je na njega red da gadja (playerTurn) - OVO!!!
		
		//salvo should be in format RxC-RxC-...-RxC
		String salvo = fireSalvoForm.getSalvo();
		List<Shot> shots = new ArrayList<>();
		if (StringUtils.isBlank(salvo)) {
			return null;
		} else {
			String[] rcArr = salvo.trim().split("-");
			if (rcArr == null || rcArr.length == 0) {
				return null;
			} else {
				for (int i=0; i<rcArr.length; i++) {
					if (rcArr[i].length() != 3) {
						return null;
					} else {
						char[] charr = rcArr[i].toCharArray();
						if (HEX_CHARS.indexOf(charr[0]) == -1 || 
								charr[1] != 'x' || HEX_CHARS.indexOf(charr[2]) == -1) {
							return null;
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
				}
			}
		}
		
		return shots;
		
		
		// proveri da li ne gadja svoje brodove :)
		// da li je vec gadjao tu (shot exist)
	}
}
