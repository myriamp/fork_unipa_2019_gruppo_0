package it.eng.unipa.filesharing.dto;

public class TokenUtenteDTO {

    private String email;
    private String token;
    private String idTelegram;

    public TokenUtenteDTO() {
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public String getIdTelegram() {
        return idTelegram;
    }
    public void setIdTelegram(String idTelegram) {
        this.idTelegram = idTelegram;
    }

    @Override
    public String toString() {
        return "TokenUtenteDTO [email=" + email + ", token=" + token + ", idTelegram=" + idTelegram + "]";
    }
}