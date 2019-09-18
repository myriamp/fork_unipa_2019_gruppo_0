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
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class TokenUtenteImpl implements TokenUtenteService {

    private TokenUtenteRepository tokenUtenteRepository;

    private ConversionService conversionService;

    public TokenUtenteImpl(@Autowired TokenUtenteRepository tokenUtenteRepository, @Autowired ConversionService conversionService) {
        this.tokenUtenteRepository = tokenUtenteRepository;
        this.conversionService = conversionService;
    }

    @Override
    public void addToken(TokenUtenteDTO tokenUtenteDTO){//richiamato dal controller , genera il token e lo aggiunge al dto
        String email = tokenUtenteDTO.getEmail();

        Optional<TokenUtente> findById = tokenUtenteRepository.findById(email);
        if(findById.isPresent() == false ){
            SecureRandom token = new SecureRandom();
            long longToken = Math.abs(token.nextLong());
            String token = Long.toString(longToken,16);

            TokenUtente newToken = new TokenUtente(email, token);

            tokenUtenteRepository.save(newToken);

        }
    }

    public String getToken(String email){//ritorna il token relativo a una email per mostrarla a video e poi inserirla su telegram
        Optional<TokenUtente> findById = tokenUtenteRepository.findById(email);
        if(findById.isPresent() == true ){
                TokenUtente token = findById.get();

                String codice = token.getToken();
        }
    }

    @Override
    public TokenUtenteDTO insertChatID(TokenUtenteDTO tokenUtenteDTO, String chatID ){
        tokenUtenteDTO.setIdTelegram(chatID);

    }

    @Override
    public  boolean verifyToken(String token){//Richiamato per verificare se il token nserito su telegram e' presente nella
        //repository

        Optional<TokenUtente> findBy = tokenUtenteRepository.findAll();




    }

    @Override
    public String save(TokenUtenteDTO tokenUtenteDTO){

    }
}