package com.xlspaceship.battle.exception;

public class ShotExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public ShotExistsException() {
		super("You have already shot here!");
	}
}
