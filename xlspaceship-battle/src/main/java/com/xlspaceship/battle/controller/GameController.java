package com.xlspaceship.battle.controller;

import java.util.List;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xlspaceship.battle.form.GameForm;
import com.xlspaceship.battle.model.Game;
import com.xlspaceship.battle.model.Player;
import com.xlspaceship.battle.service.GameService;

@Controller
@RequestMapping("/")
public class GameController {
	
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
	public Game startNewGame(@RequestBody GameForm gameForm) {
		// create game with user1, when a new user hit this req, add him to the game
		
		Player playerOne = new Player(gameForm.getFullName());
		Game myGame = new Game();
		myGame.setPlayerOne(playerOne);
		
		List<Player> opponents = gameService.getAvailableOpponents();
		if (CollectionUtils.isNotEmpty(opponents)) {
			Random random = new Random();
			myGame.setPlayerTwo(opponents.get(random.nextInt(opponents.size())));
		}
		
		myGame.setHostname(gameForm.getHostname());
		myGame.setPort(gameForm.getPort());
		
		gameService.addGame(myGame, playerOne);
		
		return myGame;
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
	
}
