public class AgenteImagem extends AgenteIA {
    
    private static final String[] TERMOS_SENSIVEIS = {"hackear", "roubar", "biométrico", "violento", "explosivo"};
    
    public AgenteImagem(String nome) {
        super(nome);
    }
    
    @Override
    public void processarRequisicao(String input) 
        throws FalhaProcessamentoAgenteException, PromptInadequadoException, ErroComunicacaoIAException {
        
        System.out.println("\n🎨 AgenteImagem [" + nome + "] iniciando processamento...");
        this.status = "PROCESSING";
        
        // Passo 1: Conectar ao servidor
        conectarServidor();
        
        // Passo 2: Validação específica do AgenteImagem - termos sensíveis
        if (input == null || input.isEmpty()) {
            throw new FalhaProcessamentoAgenteException("Prompt não pode ser vazio ou nulo");
        }
        
        String inputLower = input.toLowerCase();
        for (String termo : TERMOS_SENSIVEIS) {
            if (inputLower.contains(termo)) {
                throw new PromptInadequadoException(
                    "BLOQUEIO DE SEGURANÇA: Termo sensível '" + termo + "' detectado no prompt"
                );
            }
        }
        
        // Passo 3: Processamento bem-sucedido
        System.out.println("   🖼️ Agente de Imagem [" + nome + "] sintetizando pixels para: \"" + input + "\"");
        this.status = "IDLE";
    }
}