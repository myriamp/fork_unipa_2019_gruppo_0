package it.eng.unipa.filesharing.service;

import it.eng.unipa.filesharing.dto.TokenUtenteDTO;

import java.util.List;

public interface TokenUtenteService {

    TokenUtenteDTO addToken(TokenUtenteDTO tokenUtenteDTO);

    TokenUtenteDTO insertChatID(TokenUtenteDTO tokenUtenteDTO, String chatID );

    String getToken(String email);

    boolean verfifyToken(String token);

    String save(TokenUtenteDTO tokenUtenteDTO);

}