public class AgenteTexto extends AgenteIA {
    
    public AgenteTexto(String nome) {
        super(nome);
    }
    
    @Override
    public void processarRequisicao(String input) 
        throws FalhaProcessamentoAgenteException, PromptInadequadoException, ErroComunicacaoIAException {
        
        System.out.println("\n📝 AgenteTexto [" + nome + "] iniciando processamento...");
        this.status = "PROCESSING";
        
        // Passo 1: Conectar ao servidor
        conectarServidor();
        
        // Passo 2: Validação específica do AgenteTexto - tamanho do prompt
        if (input == null || input.isEmpty()) {
            throw new FalhaProcessamentoAgenteException("Prompt não pode ser vazio ou nulo");
        }
        
        if (input.length() > 500) {
            throw new FalhaProcessamentoAgenteException(
                "Estouro de Contexto: Prompt com " + input.length() + 
                " caracteres excede limite de 500"
            );
        }
        
        // Passo 3: Processamento bem-sucedido
        System.out.println("   💬 Agente de Texto [" + nome + "] gerando resposta para: \"" + input + "\"");
        this.status = "IDLE";
    }
}