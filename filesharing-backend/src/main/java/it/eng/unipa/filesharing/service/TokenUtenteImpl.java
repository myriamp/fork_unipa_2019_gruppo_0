package it.eng.unipa.filesharing.service;

import it.eng.unipa.filesharing.dto.*;
import it.eng.unipa.filesharing.model.TokenUtente;
import it.eng.unipa.filesharing.repository.TokenUtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class TokenUtenteImpl implements TokenUtenteService {

    private TokenUtenteRepository tokenUtenteRepository;9

    private ConversionService conversionService;

    public TokenUtenteImpl(@Autowired TokenUtenteRepository tokenUtenteRepository, @Autowired ConversionService conversionService) {
        this.tokenUtenteRepository = tokenUtenteRepository;
        this.conversionService = conversionService;
    }

    @Override
    public TokenUtenteDTO crateToken(TokenUtenteDTO tokenUtenteDTO){
        SecureRandom token = new SecureRandom();
        long longToken = Math.abs(token.nextLong());
        String token = Long.toString(longToken,16);
        tokenUtenteDTO.setToken(token);

        return tokenUtenteDTO
    }

    @Override
    public String getToken(String email){
        TokenUtente tokenUtente = tokenUtente(email);

        return conversionService.convert(tokenUtente, String.class);
    }

    @Override
    public  boolean verfifyToken(String token){

    }

    @Override
    public String save(TokenUtenteDTO tokenUtenteDTO){

    }
}