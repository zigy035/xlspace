package com.xlspaceship.battle.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="protocol")
public class Protocol {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	
	
	/*public Protocol(String hostname, Integer port) {
		this.hostname = hostname;
		this.port = port;
	}*/
}
