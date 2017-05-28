package com.xlspaceship.battle.exception;

public class ShotCountExceededException extends Exception {

	private static final long serialVersionUID = 1L;

	public ShotCountExceededException() {
		super("You have more shots that remaining battle ships!");
	}
}
