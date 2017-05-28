package com.xlspaceship.battle.exception;

public class PlayerTwoTurnException extends Exception {

	private static final long serialVersionUID = 1L;

	public PlayerTwoTurnException() {
		super("Player 2 plays now!");
	}
}
