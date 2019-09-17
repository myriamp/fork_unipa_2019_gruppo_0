package it.eng.unipa.filesharing.service.exception;

import java.util.UUID;

public class TeamNotFoundException extends NotFoundException {
	
	public TeamNotFoundException(UUID uuid) {
		super("Impossibile recuperare il team con UUID:"+uuid);
		// TODO Auto-generated constructor stub
	}

}