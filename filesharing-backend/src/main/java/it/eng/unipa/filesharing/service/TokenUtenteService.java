package it.eng.unipa.filesharing.service;

import it.eng.unipa.filesharing.dto.TokenUtenteDTO;

import java.util.List;

public interface TokenUtenteService {

    void addToken(TokenUtenteDTO tokenUtenteDTO);

    void insertChatID( String chatID, String token );

    String getToken(String email);

    boolean verifyToken(String token);



}