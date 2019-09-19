package it.eng.unipa.filesharing.dto;

public class VerificaDTO {

    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public VerificaDTO() {
    }

    public VerificaDTO(boolean status) {
        this.status = status;
    }
}
