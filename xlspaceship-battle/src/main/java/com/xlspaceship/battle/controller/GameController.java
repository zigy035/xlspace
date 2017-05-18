package com.xlspaceship.battle.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xlspaceship.battle.dto.GameDTO;
import com.xlspaceship.battle.form.GameForm;
import com.xlspaceship.battle.model.Game;
import com.xlspaceship.battle.model.Player;
import com.xlspaceship.battle.model.SpaceShip;
import com.xlspaceship.battle.service.GameService;

@Controller
@RequestMapping("/")
public class GameController {
	
	private static final String XLABS = "XLABS";
	
	// Winger (X) Form
	private static final String [] WINGER_FORM = {"0:0", "0:2", "1:0", "1:2", "2:1", "3:0", "3:2", "4:0", "4:2"};
	// Angle (L) Form
	private static final String [] ANGLE_FORM = {"0:0", "1:0", "2:0", "3:0", "3:1", "3:2"};
	// A-CLass (A) Form
	private static final String [] A_CLASS_FORM = {"0:1", "1:0", "1:2", "2:0", "2:1", "2:2", "3:0", "3:2"};
	// B-Class (B) Form
	private static final String [] B_CLASS_FORM = {"0:0", "0:1", "1:0", "1:2", "2:0", "2:1", "3:0", "3:2", "4:0", "4:1"};
	// S-Class (S) Form
	private static final String [] S_CLASS_FORM = {"0:1", "0:2", "1:0", "2:1", "2:2", "3:3", "4:1", "4:2"};
	
	private static final Map<Character, String[]> battleshipFormMap = new HashMap<>();
	private static final Map<Integer, QuaterOffset> quaterOffsetMap = new HashMap<>();
	
	static {
		battleshipFormMap.put('X', WINGER_FORM);
		battleshipFormMap.put('L', ANGLE_FORM);
		battleshipFormMap.put('A', A_CLASS_FORM);
		battleshipFormMap.put('B', B_CLASS_FORM);
		battleshipFormMap.put('S', S_CLASS_FORM);
		
		/* QUATERS
		 * FIRST			SECOND
		0 0 0 0 0 0 0 0   0 0 0 0 0 0 0 0 
		0 0 0 0 0 0 0 0   0 0 0 0 0 0 0 0 
		0 0 0 0 0 0 0 0   0 0 0 0 0 0 0 0 
		0 0 0 0 0 0 0 0   0 0 0 0 0 0 0 0 
		0 0 0 0 0 0 0 0   0 0 0 0 0 0 0 0 
		0 0 0 0 0 0 0 0   0 0 0 0 0 0 0 0 
		0 0 0 0 0 0 0 0   0 0 0 0 0 0 0 0 
		0 0 0 0 0 0 0 0   0 0 0 0 0 0 0 0 
		   THIRD			FOURTH
		0 0 0 0 0 0 0 0   0 0 0 0 0 0 0 0 
		0 0 0 0 0 0 0 0   0 0 0 0 0 0 0 0 
		0 0 0 0 0 0 0 0   0 0 0 0 0 0 0 0 
		0 0 0 0 0 0 0 0   0 0 0 0 0 0 0 0 
		0 0 0 0 0 0 0 0   0 0 0 0 0 0 0 0 
		0 0 0 0 0 0 0 0   0 0 0 0 0 0 0 0 
		0 0 0 0 0 0 0 0   0 0 0 0 0 0 0 0 
		0 0 0 0 0 0 0 0   0 0 0 0 0 0 0 0 
		 */
		quaterOffsetMap.put(1, new QuaterOffset(0, 0));
		quaterOffsetMap.put(2, new QuaterOffset(0, 8));
		quaterOffsetMap.put(3, new QuaterOffset(8, 0));
		quaterOffsetMap.put(4, new QuaterOffset(8, 8));
	}
	
	
	
	
	@Autowired
	GameService gameService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	/*
	XLSS-1: As a player, I want to receive a new simulation request for a simulation with
	another player.	This first story is all about setting up a game with another player. 
	On the endpoint we define here, your XL Spaceship instance will be challenged by another 
	XL Spaceship instance. The challenging instance will indicate on which address/port it is 
	reachable for playing the game. The response should contain the unique game_id of this match. 
	It also contains the starting player, determined at random.
	
	 {
		"user_id": "xebialabs-1",
		"full_name": "XebiaLabs Opponent",
		"spaceship_protocol": {
		"hostname": "127.0.0.1",
		"port": 9001
		}
		}
		JSON
		{
		"user_id": "player",
		"full_name": "Assessment Player",
		"game_id": "match-1",
		"starting": "xebialabs-1"
	}

	
	 */
	@RequestMapping(value = "/protocol/game/new", method = RequestMethod.POST)
	@ResponseBody
	public GameDTO startNewGame(@RequestBody GameForm gameForm) {
		// create game with user1, when a new user hit this req, add him to the game
		
		Player playerOne = new Player(gameForm.getFullName());
		Character ch1;
		if (StringUtils.isBlank(gameForm.getFormation()) || gameForm.getFormation().length() > 1) {
			ch1 = 'X'; 
		} else {
			ch1 = gameForm.getFormation().charAt(0);
			if (XLABS.indexOf(ch1) == -1) {
				ch1 = 'X';
			}
		}
		// set spaceships for player one - will be located inside first quarter
		playerOne.setSpaceships(generateSpaceShips(ch1, 1));
		
		Random random = new Random();
		List<Player> opponents = gameService.getAvailableOpponents();
		Player playerTwo = null;
		if (CollectionUtils.isNotEmpty(opponents)) {
			playerTwo = opponents.get(random.nextInt(opponents.size()-1));
			Character ch2 = XLABS.charAt(random.nextInt(XLABS.length()-1));
			playerTwo.setSpaceships(generateSpaceShips(ch2 ,2));
		}
		
		Game game = new Game();
		game.setHostname(gameForm.getHostname());
		game.setPort(gameForm.getPort());
		gameService.addGame(game, playerOne, playerTwo);
		System.out.println("GAME_ID: " + game.getId());
		
		GameDTO gameDTO = new GameDTO();
		gameDTO.setGameId(String.valueOf(game.getId()));
		gameDTO.setFullName(game.getPlayerTwo().getFullName());
		gameDTO.setPlayerId(String.valueOf(game.getPlayerTwo().getId()));
		gameDTO.setStarting(String.valueOf(game.getPlayerOne().getId()));
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<16; i++) {
			for (int j=0; j<16; j++) {
				if (doesShipExist(i, j, playerOne.getSpaceships())) {
					sb.append(playerOne.getId()).append(" ");
				} else if (doesShipExist(i, j, playerTwo.getSpaceships())) {
					sb.append(playerTwo.getId()).append(" ");
				} else {
					sb.append("0 ");
				}
			}
			sb.append("\n");
		}
		gameDTO.setTable(sb.toString());
		
		return gameDTO;
	}
	
	@RequestMapping(value = "/protocol/game/{game_id}", method = RequestMethod.PUT)
	@ResponseBody
	public String receiveSalvoShots() {
		return "";
	}
	
	@RequestMapping(value = "/protocol/user/game/{game_id}/fire", method = RequestMethod.PUT)
	@ResponseBody
	public String fireSalvoShots() {
		return "";
	}
	
	private List<SpaceShip> generateSpaceShips(Character ch, int quater) {
		Random random = new Random();
		String[] positions = battleshipFormMap.get(ch);
		List<SpaceShip> spaceShips = new ArrayList<>();
		int randomRowColOffset = random.nextInt(3);
		int quaterRowOffset = quaterOffsetMap.get(quater).getRowOffset();
		int quaterColOffset = quaterOffsetMap.get(quater).getColOffset();
		for (int i=0; i<positions.length; i++) {
			String[] rowcol = positions[i].split(":");
			int row = Integer.valueOf(rowcol[0]) + randomRowColOffset + quaterRowOffset;
			int col = Integer.valueOf(rowcol[1]) + randomRowColOffset + quaterColOffset;
			spaceShips.add(new SpaceShip(row, col));
		}
		return spaceShips;
	}
	
	private boolean doesShipExist(int row, int col, List<SpaceShip> spaceShips) {
		for (SpaceShip spaceShip : spaceShips) {
			if (spaceShip.getRow().equals(row) && spaceShip.getCol().equals(col)) {
				return true;
			}
		}
		return false;
	}
	
}

class QuaterOffset {
	Integer rowOffset;
	Integer colOffset;
	
	public QuaterOffset(Integer rowOffset, Integer colOffset) {
		this.rowOffset = rowOffset;
		this.colOffset = colOffset;
	}

	public Integer getRowOffset() {
		return rowOffset;
	}

	public Integer getColOffset() {
		return colOffset;
	}
	
}


