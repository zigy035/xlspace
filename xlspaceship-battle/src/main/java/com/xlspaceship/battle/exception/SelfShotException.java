package com.xlspaceship.battle.exception;

public class SelfShotException extends Exception {

	private static final long serialVersionUID = 1L;

	public SelfShotException() {
		super("You are shooting you own battle ships!");
	}
}
