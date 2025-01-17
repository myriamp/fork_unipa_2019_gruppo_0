package it.eng.unipa.filesharing.service;

import it.eng.unipa.filesharing.dto.TokenUtenteDTO;
import it.eng.unipa.filesharing.dto.VerificaDTO;

import java.util.List;

public interface TokenUtenteService {

    TokenUtenteDTO addToken();

    String verifyToken(String token,String chatId);

    List<String> getChatsId(List<String> emails);

    void delete(String email);

    VerificaDTO verificaChatId(String mail);
}