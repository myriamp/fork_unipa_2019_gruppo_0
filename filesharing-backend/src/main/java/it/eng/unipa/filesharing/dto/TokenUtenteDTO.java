package it.eng.unipa.filesharing.dto;

public class TokenUtenteDTO {

    private String token;

    public TokenUtenteDTO() {
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }


    @Override
    public String toString() {
        return "TokenUtenteDTO [ token=" + token + "]";
    }
}