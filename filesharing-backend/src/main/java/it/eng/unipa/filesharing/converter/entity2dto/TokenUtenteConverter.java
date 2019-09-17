package it.eng.unipa.filesharing.converter.entity2dto;

import it.eng.unipa.filesharing.dto.TokenUtenteDTO;
import it.eng.unipa.filesharing.model.TokenUtente;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TokenUtenteConverter implements Converter<TokenUtente, TokenUtenteDTO> {

    @Override
    public TokenUtenteDTO convert(TokenUtente tokenUtente) {
        TokenUtenteDTO tokenUtenteDTO = null;
        if(tokenUtenteDTO !=null) {
            tokenUtenteDTO = new TokenUtenteDTO();
            tokenUtenteDTO.setEmail(tokenUtente.getEmail());
            tokenUtenteDTO.setToken(tokenUtente.getToken());
            tokenUtenteDTO.setIdTelegram(tokenUtente.getIdTelegram());
        }
        return tokenUtenteDTO;
    }
}