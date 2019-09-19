package it.eng.unipa.filesharing.service;

import it.eng.unipa.filesharing.context.SecurityContext;
import it.eng.unipa.filesharing.dto.*;
import it.eng.unipa.filesharing.model.TokenUtente;
import it.eng.unipa.filesharing.repository.TokenUtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.List;
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
    public TokenUtenteDTO addToken(){//richiamato dal controller , genera il token e lo aggiunge al dto
        String email = SecurityContext.getEmail();
        TokenUtenteDTO tokenUtenteDTO = new TokenUtenteDTO();
        Optional<TokenUtente> findById = tokenUtenteRepository.findById(email);
        if(!findById.isPresent()){
            SecureRandom token = new SecureRandom();
            long longToken = Math.abs(token.nextLong());
            String codice = Long.toString(longToken,16);

            TokenUtente newToken = new TokenUtente(email, codice);

            tokenUtenteRepository.save(newToken);

            tokenUtenteDTO.setToken(codice);

        }else{
            tokenUtenteDTO.setToken(findById.get().getToken());
        }

        return tokenUtenteDTO;
    }

    public String getToken(String email){//ritorna il token relativo a una email per mostrarla a video e poi inserirla su telegram
        Optional<TokenUtente> findById = tokenUtenteRepository.findById(email);
        if(findById.isPresent()){
                TokenUtente token = findById.get();

                String codice = token.getToken();

                return codice;
        }
        return "email non presente";
    }

    @Override
    public void insertChatID(  String chatID, String token ){//faccio il delete e ricarico nella repository
        //con il cambio chatid aggiunto



        List<TokenUtente> findByToken = tokenUtenteRepository.findAll();
        for (TokenUtente temp : findByToken){
            if (temp.getToken().equals(token)){//cerchiamo il corrispondente di quel token , lo  eliminiamo e lo
                //reinseriamo con il chat id
                int index = findByToken.indexOf(temp);
                TokenUtente prova = findByToken.get(index);
                tokenUtenteRepository.delete(prova);

                TokenUtente newToken = new TokenUtente(prova.getEmail(), token,chatID);

                tokenUtenteRepository.save(newToken);//agggiunto con il relativo idChat

            }


        }





    }

    @Override
    public  boolean verifyToken(String token){//Richiamato per verificare se il token nserito su telegram e' presente nella
        //repository

        List<TokenUtente> findBy = tokenUtenteRepository.findAll();

        for (TokenUtente temp : findBy){
            if (temp.getToken().equals(token)){
                return true;
            }
            else{
                return false;
            }

        }
        return false;

    }


}