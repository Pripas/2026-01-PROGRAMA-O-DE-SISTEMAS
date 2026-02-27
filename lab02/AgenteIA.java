public abstract class AgenteIA {
    protected String nome;
    protected String status;
    
    public AgenteIA(String nome) {
        this.nome = nome;
        this.status = "IDLE";
    }
    
    public String getNome() {
        return nome;
    }
    
    public void conectarServidor() throws ErroComunicacaoIAException {
        System.out.println("   🔌 " + nome + " conectando ao servidor...");
        ModuloConexao.validarLink();
        this.status = "CONNECTED";
    }
    
    // Método abstrato - cada filho implementa sua própria regra
    public abstract void processarRequisicao(String input) 
        throws FalhaProcessamentoAgenteException, PromptInadequadoException, ErroComunicacaoIAException;
}