package it.eng.unipa.filesharing.container;

import it.eng.unipa.filesharing.context.SecurityContext;
import it.eng.unipa.filesharing.dto.TokenUtenteDTO;
import it.eng.unipa.filesharing.dto.VerificaDTO;
import it.eng.unipa.filesharing.service.TokenUtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tokenutente")
public class TokenUtenteController {

    private int statusCount = 1;

    private TokenUtenteService tokenUtenteService;

    public TokenUtenteController(@Autowired TokenUtenteService tokenUtenteService) {
        this.tokenUtenteService = tokenUtenteService;
    }

    //crea token e lo salva insieme alla mail
    @GetMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public TokenUtenteDTO save(){

        TokenUtenteDTO dto = tokenUtenteService.addToken();
        return dto;
    }

    @GetMapping("/checkStatus")
    @ResponseStatus(value = HttpStatus.CREATED)
    public VerificaDTO checkStatus(){


        return new VerificaDTO(++statusCount>4);
    }

    @DeleteMapping("")
    public void notificheOff(){
        tokenUtenteService.delete(SecurityContext.getEmail());
    }
}