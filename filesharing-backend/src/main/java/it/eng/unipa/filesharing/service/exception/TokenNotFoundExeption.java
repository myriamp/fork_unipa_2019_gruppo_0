package it.eng.unipa.filesharing.service.exception;

public class TokenNotFoundExeption extends NotFoundException {
    public TokenNotFoundExeption(String token) {
        super("Token "+token+" non trovato");
        // TODO Auto-generated constructor stub
    }
}