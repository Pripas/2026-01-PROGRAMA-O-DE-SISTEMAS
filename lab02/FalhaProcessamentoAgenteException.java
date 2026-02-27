import java.time.LocalDateTime;

public class FalhaProcessamentoAgenteException extends Exception {
    private String timestamp;

    public FalhaProcessamentoAgenteException(String mensagem) {
        super(mensagem);
        this.timestamp = LocalDateTime.now().toString();
    }

    public String getTimestamp() { 
        return timestamp; 
    }
}