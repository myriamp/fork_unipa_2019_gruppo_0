package it.eng.unipa.filesharing.service;

import it.eng.unipa.filesharing.dto.*;
import it.eng.unipa.filesharing.model.TokenUtente;
import it.eng.unipa.filesharing.repository.TeamRepository;
import it.eng.unipa.filesharing.resource.BucketType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class TokenUtenteImpl implements TokenUtenteService {
    private List<TokenUtenteDTO> utentiRichiestaNotifiche();

    public TeamServiceImpl(@Autowired TeamRepository teamRepository, @Autowired ConversionService conversionService, @Autowired List<BucketType> allBucketType) {
        this.teamRepository = teamRepository;
        this.conversionService = conversionService;
        this.allBucketType = allBucketType;
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

    }

    @Override
    public  boolean verfifyToken(String token){

    }

    @Override
    public String save(TokenUtenteDTO tokenUtenteDTO){

    }
}