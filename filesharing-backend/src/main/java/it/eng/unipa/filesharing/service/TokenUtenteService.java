package it.eng.unipa.filesharing.service;

import it.eng.unipa.filesharing.dto.TokenUtenteDTO;

import java.util.List;

public interface TokenUtenteService {
    List<TokenUtenteDTO> utentiRichiestaNotifiche();

    void crateToken(TokenUtenteDTO tokenUtenteDTO);

    String getToken(String email);

    boolean verfifyToken(String token);

    String save(TokenUtenteDTO tokenUtenteDTO);
}
