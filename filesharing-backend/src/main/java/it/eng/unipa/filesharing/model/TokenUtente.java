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

    public TokenUtente(String email) {
        this.email = email;
    }

    public TokenUtente(String email, String token, String idTelegram) {
        this.email = email;
        this.token = token;
        this.idTelegram = idTelegram;
    }

    public TokenUtente(String email,String token) {
        this.email = email;
        this.token = token;
        this.idTelegram = "nullo";
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

    public void setIdTelegram(String idTelegram) {
        this.idTelegram = idTelegram;
    }
}