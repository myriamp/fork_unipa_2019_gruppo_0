package it.eng.unipa.filesharing.model;

import javax.persistence.*;

@Entity
public class TokenUtente {

    @Id
    private String email;

    private String token;

    private String idTelegram;


    public TokenUtente() {
    }

    public TokenUtente(String email,String token,String idTelegram) {
        this.email = email;
        this.token = token;
        this.idTelegram = idTelegram;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public String getIdTelegram() {
        return idTelegram;
    }
}