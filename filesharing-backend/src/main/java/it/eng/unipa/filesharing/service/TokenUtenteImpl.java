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
            int min = 100000;
            int max = 999999;

           int val = token.nextInt(max - min + 1) +min;
            String codice = ""+val;

            TokenUtente newToken = new TokenUtente(email, codice);

            tokenUtenteRepository.save(newToken);

            tokenUtenteDTO.setToken(codice);

        }else{
            tokenUtenteDTO.setToken(findById.get().getToken());
        }

        return tokenUtenteDTO;
    }

    @Override
    public String verifyToken(  String token,String chatID ){//faccio il delete e ricarico nella repository
        //con il cambio chatid aggiunto



        Optional<TokenUtente> opt= tokenUtenteRepository.utentiRichiestaNotifiche(token);
        if(opt.isPresent() && opt.get().getIdTelegram()==null){
        TokenUtente tokenUtente = opt.get();
        tokenUtente.setIdTelegram(chatID);
        tokenUtenteRepository.save(tokenUtente);//agggiunto con il relativo idChat
        return tokenUtente.getEmail();
            }

        return null;


        }

    @Override
    public List<String> getChatsId(List<String> emails) {
        return tokenUtenteRepository.getChatIds(emails);
    }

    @Override
    public void delete(String email){
        tokenUtenteRepository.delete(new TokenUtente(email));
//        Optional<TokenUtente> t = tokenUtenteRepository.findById(email);
//        if(t.isPresent()){
//            t.get().getIdTelegram();
//        }da fare nella funzione di verifica
    }

    @Override
    public String verificaChatId(String email){
        return null;
    }
}