package it.eng.unipa.filesharing.container;

import it.eng.unipa.filesharing.dto.TokenUtenteDTO;
import it.eng.unipa.filesharing.service.TokenUtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tokenutente")
public class TokenUtenteController {

    private TokenUtenteService tokenUtenteService;

    public TokenUtenteController(@Autowired TokenUtenteService tokenUtenteService) {
        this.tokenUtenteService = tokenUtenteService;
    }

    //crea token e lo salva insieme alla mail
    @GetMapping("/{email}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void save(@PathVariable("email")String email){
        TokenUtenteDTO tokenUtenteDTO = new tokenUtenteDTO();
        tokenUtenteDTO.setEmail(email);
        tokenUtenteService.addToken(tokenUtenteDTO);
    }
}