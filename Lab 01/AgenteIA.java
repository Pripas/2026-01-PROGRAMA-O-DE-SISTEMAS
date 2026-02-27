public class AgenteIA {
    
    // Exercício 1: Lista de palavras proibidas (constante interna)
    private static final String[] PALAVRAS_PROIBIDAS = {"hackear", "roubar"};
    
    // Método principal com todas as validações
    public void processarPrompt(String prompt) throws FalhaProcessamentoAgenteException {
        // Validação inicial (incluindo null - Checklist item 5)
        if (prompt == null) {
            throw new FalhaProcessamentoAgenteException("O prompt não pode ser nulo.");
        }
        if (prompt.trim().isEmpty()) {
            throw new FalhaProcessamentoAgenteException("O prompt não pode estar vazio.");
        }
        if (prompt.length() > 100) {
            throw new FalhaProcessamentoAgenteException("Prompt muito longo para o modelo atual.");
        }
        
        // Exercício 1: Filtro de Segurança (implementado dentro do mesmo método)
        String promptLower = prompt.toLowerCase();
        for (String palavra : PALAVRAS_PROIBIDAS) {
            if (promptLower.contains(palavra)) {
                throw new FalhaProcessamentoAgenteException(
                    "Prompt inadequado: contém palavra proibida '" + palavra + "'"
                );
            }
        }
        
        // Exercício 2: Simulador de Timeout
        chamarModeloExterno();
        
        System.out.println("Agente processando: " + prompt);
    }
    
    // Exercício 2: Método que simula chamada externa
    public void chamarModeloExterno() throws FalhaProcessamentoAgenteException {
        double random = Math.random();
        System.out.println("📡 Random gerado: " + random);
        
        if (random > 0.7) {
            throw new FalhaProcessamentoAgenteException(
                "Erro de comunicação com a IA - Timeout na chamada externa"
            );
        }
        
        System.out.println("📡 Modelo externo respondeu com sucesso!");
    }
}