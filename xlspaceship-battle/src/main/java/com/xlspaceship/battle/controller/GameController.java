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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xlspaceship.battle.dto.GameDTO;
import com.xlspaceship.battle.enumeration.ShotStatus;
import com.xlspaceship.battle.form.FireSalvoForm;
import com.xlspaceship.battle.form.GameForm;
import com.xlspaceship.battle.model.Game;
import com.xlspaceship.battle.model.Player;
import com.xlspaceship.battle.model.Shot;
import com.xlspaceship.battle.model.SpaceShip;
import com.xlspaceship.battle.service.GameService;
import com.xlspaceship.battle.service.PlayerService;
import com.xlspaceship.battle.service.ShotService;
import com.xlspaceship.battle.service.SpaceShipService;
import com.xlspaceship.battle.validator.GameValidator;

@Controller
@RequestMapping("/")
public class GameController {
	
	private static final String NEXT_LINE = "\n";

	private static final String HEX_CHARS = "0123456789abcdef";

	private static final String EMPTY = ".";

	private static final String SPACESHIP_MARK = "*";
	private static final String SPACESHIP_HIT = "X";
	private static final String SPACESHIP_MISS = "-";
	private static final Object SPACE = " ";
	
	private static final String XLABS = "XLABS";
	private static final String ERROR_CODE = "404";
	
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
	private static final Map<Integer, QuarterOffset> quarterOffsetMap = new HashMap<>();

	static {
		battleshipFormMap.put('X', WINGER_FORM);
		battleshipFormMap.put('L', ANGLE_FORM);
		battleshipFormMap.put('A', A_CLASS_FORM);
		battleshipFormMap.put('B', B_CLASS_FORM);
		battleshipFormMap.put('S', S_CLASS_FORM);
		
		/* QUARTERS
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
		quarterOffsetMap.put(1, new QuarterOffset(0, 0));
		quarterOffsetMap.put(2, new QuarterOffset(0, 8));
		quarterOffsetMap.put(3, new QuarterOffset(8, 0));
		quarterOffsetMap.put(4, new QuarterOffset(8, 8));
	}
	
	@Autowired
	GameService gameService;

	@Autowired
	PlayerService playerService;
	
	@Autowired
	SpaceShipService spaceShipsService;
	
	@Autowired
	ShotService shotService;
	
	@Autowired
	GameValidator gameValidator;

	
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	/*	
	1) add salvo input fields
	2) PUT method should be applied
	3) refactoring (GameDTO - Game relation)...
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
			playerTwo.setSpaceships(generateSpaceShips(ch2, 2));
		}
		
		Game game = new Game();
		game.setHostname(gameForm.getHostname());
		game.setPort(gameForm.getPort());
		gameService.addGame(game, playerOne, playerTwo);
		
		GameDTO gameDTO = new GameDTO();
		gameDTO.setGameId(String.valueOf(game.getId()));
		gameDTO.setFullName(game.getPlayerTwo().getFullName());
		gameDTO.setPlayerId(String.valueOf(game.getPlayerTwo().getId()));
		gameDTO.setStarting(String.valueOf(game.getPlayerOne().getId()));
		gameDTO.setPlayerTurn(game.getPlayerOne().getFullName());
		gameDTO.setTable(generateTable(playerOne.getSpaceships(), playerTwo.getSpaceships()));
		
		gameDTO.setPlayerTurnShipCount(new Long(playerOne.getSpaceships().size()));
		
		return gameDTO;
	}
	
	@RequestMapping(value = "/protocol/user/game/{gid}/fire", method = RequestMethod.POST)
	@ResponseBody
	public GameDTO fireSalvo(@PathVariable("gid") String gid, @RequestBody FireSalvoForm form) {
		
		Integer gameId = Integer.valueOf(gid);
		Game game = gameService.getGameInfo(gameId);
		Integer playerOneId = game.getPlayerOne().getId();
		Integer playerTwoId = game.getPlayerTwo().getId();
		
		// mozda GameDTO treba da ima samo game, error i table
		GameDTO gameDTO = new GameDTO();
		gameDTO.setGameId(String.valueOf(game.getId()));
		gameDTO.setFullName(game.getPlayerTwo().getFullName());
		gameDTO.setPlayerId(String.valueOf(playerTwoId));
		gameDTO.setStarting(String.valueOf(playerOneId));
		gameDTO.setPlayerTurn(game.getPlayerOne().getFullName());
		
		//validate input		
		List<Shot> shots;
		try {
			shots = gameValidator.fireSalvoValidate(form, game);
		} catch (Exception e) {
			gameDTO.setError(e.getMessage());
			List<SpaceShip> playerOneShips = spaceShipsService.getPlayerSpaceShips(playerOneId);
			List<SpaceShip> playerTwoShips = spaceShipsService.getPlayerSpaceShips(playerTwoId);
			List<Shot> p1Shots = shotService.getShots(gameId, playerOneId);
			gameDTO.setTable(generateTable(playerOneShips, playerTwoShips, p1Shots));
			
			return gameDTO;
		}
		
		// perform shoot: add/update shots and remove ships that are hit
		gameService.shootSalvo(shots, gameId, playerOneId, playerTwoId);
		List<Shot> shotResult = shotService.getShots(gameId, playerOneId);
		List<SpaceShip> playerOneShips = spaceShipsService.getPlayerSpaceShips(playerOneId);
		List<SpaceShip> playerTwoShips = spaceShipsService.getPlayerSpaceShips(playerTwoId);
		
		gameDTO.setPlayerTurn(game.getPlayerTwo().getFullName());
		gameDTO.setTable(generateTable(playerOneShips, playerTwoShips, shotResult));
		
		Long p2ShipsCount = spaceShipsService.getPlayerSpaceShipsCount(playerTwoId);
		gameDTO.setPlayerTurnShipCount(p2ShipsCount);
		
		return gameDTO;
	}
	
	@RequestMapping(value = "/protocol/game/receive/{gid}", method = RequestMethod.PUT)
	@ResponseBody
	public GameDTO receiveSalvoShots(@PathVariable("gid") String gid) {
		
		Integer gameId = Integer.valueOf(gid);
		Game game = gameService.getGameInfo(gameId);
		Integer playerOneId = game.getPlayerOne().getId();
		Integer playerTwoId = game.getPlayerTwo().getId();
		
		// mozda GameDTO treba da ima samo game, error i table
		GameDTO gameDTO = new GameDTO();
		gameDTO.setGameId(String.valueOf(game.getId()));
		gameDTO.setFullName(game.getPlayerTwo().getFullName());
		gameDTO.setPlayerId(String.valueOf(playerTwoId));
		gameDTO.setPlayerTurn(game.getPlayerTwo().getFullName());
		
		// get number of p2 battle ships and generate random shots
		// random first time (when no p2 shots), after might be some logic 
		// to create shot near HIT place(s)
		
		Long playerTwoShipsCount = spaceShipsService.getPlayerSpaceShipsCount(playerTwoId);
		Random random = new Random();
		List<Shot> shots = new ArrayList<>();
		for (int i=0; i<playerTwoShipsCount; i++) {
			int rowIndex = random.nextInt(HEX_CHARS.length()-1);
			int colIndex = random.nextInt(HEX_CHARS.length()-1);
			Shot shot = new Shot();
			shot.setGame(game);
			shot.setPlayer(game.getPlayerTwo());
			shot.setRow(Integer.valueOf(String.valueOf(HEX_CHARS.charAt(rowIndex)), 16));
			shot.setCol(Integer.valueOf(String.valueOf(HEX_CHARS.charAt(colIndex)), 16));
			shots.add(shot);
		}
		
		// perform shoot: add/update shots and remove ships that are hit
		gameService.shootSalvo(shots, gameId, playerTwoId, playerOneId);
		List<Shot> shotResult = shotService.getShots(gameId, playerTwoId);
		List<SpaceShip> playerOneShips = spaceShipsService.getPlayerSpaceShips(playerOneId);
		List<SpaceShip> playerTwoShips = spaceShipsService.getPlayerSpaceShips(playerTwoId);
		
		gameDTO.setPlayerTurn(game.getPlayerOne().getFullName());
		gameDTO.setTable(generateTable(playerOneShips, playerTwoShips, shotResult));
		
		Long p1ShipsCount = spaceShipsService.getPlayerSpaceShipsCount(playerOneId);
		gameDTO.setPlayerTurnShipCount(p1ShipsCount);
		
		return gameDTO;
	}
	
	private List<SpaceShip> generateSpaceShips(Character ch, int quarter) {
		Random random = new Random();
		String[] positions = battleshipFormMap.get(ch);
		List<SpaceShip> spaceShips = new ArrayList<>();
		int randomRowColOffset = random.nextInt(3);
		int quarterRowOffset = quarterOffsetMap.get(quarter).getRowOffset();
		int quarterColOffset = quarterOffsetMap.get(quarter).getColOffset();
		for (int i=0; i<positions.length; i++) {
			String[] rowcol = positions[i].split(":");
			int row = Integer.valueOf(rowcol[0]) + randomRowColOffset + quarterRowOffset;
			int col = Integer.valueOf(rowcol[1]) + randomRowColOffset + quarterColOffset;
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
	
	private Shot getShot(int row, int col, List<Shot> shots) {
		for (Shot shot : shots) {
			if (shot.getRow().equals(row) && shot.getCol().equals(col)) {
				return shot;
			}
		}
		return null;
	}
	
	private String generateTable(List<SpaceShip> p1Ships, List<SpaceShip> p2Ships) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<16; i++) {
			for (int j=0; j<16; j++) {
				if (doesShipExist(i, j, p1Ships)) {
					sb.append(SPACESHIP_MARK);
				} else if (doesShipExist(i, j, p2Ships)) {
					// should display only destroyed (or hit) ships
					sb.append(SPACESHIP_MARK);
				} else {
					sb.append(EMPTY);
				}
				sb.append(SPACE);
			}
			sb.append(NEXT_LINE);
		}
		return sb.toString().trim();
	}
	
	private String generateTable(List<SpaceShip> p1Ships, List<SpaceShip> p2Ships, List<Shot> shotResult) {
		// 'X' means hit, '-' means missed
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<16; i++) {
			for (int j=0; j<16; j++) {
				if (doesShipExist(i, j, p1Ships)) {
					sb.append(SPACESHIP_MARK);
				} else if (doesShipExist(i, j, p2Ships)) {
					sb.append(SPACESHIP_MARK);
				} else {
					Shot shot = getShot(i, j, shotResult);
					if (shot != null) {
						sb.append(shot.getStatus().equals(ShotStatus.HIT) ? SPACESHIP_HIT : SPACESHIP_MISS);
					} else {
						sb.append(EMPTY);
					}
				}
				sb.append(SPACE);
			}
			sb.append(NEXT_LINE);
		}
		return sb.toString().trim();
	}

	
}

class QuarterOffset {
	Integer rowOffset;
	Integer colOffset;
	
	public QuarterOffset(Integer rowOffset, Integer colOffset) {
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


