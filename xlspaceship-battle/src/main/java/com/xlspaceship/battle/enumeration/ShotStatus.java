package com.xlspaceship.battle.enumeration;

public enum ShotStatus {
	MISSED (0),
	HIT (1),
	KILL (2);
	
	private Integer code;
	
	private ShotStatus(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}
	
}
