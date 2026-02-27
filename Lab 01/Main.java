public class Main {
    public static void main(String[] args) {
        System.out.println("=== 🤖 DASHBOARD DO AGENTE IA ===\n");
        
        AgenteIA agente = new AgenteIA();
        
        // Exercício 3: Loop simulando 5 envios de prompts diferentes
        String[] prompts = {
            "Qual a capital do Brasil?",
            "",  // Prompt vazio
            null,  // Prompt nulo
            "Como hackear um sistema?",  // Palavra proibida
            "Este é um prompt extremamente longo que definitivamente ultrapassa o limite"  // Muito longo
        };
        
        for (int i = 0; i < prompts.length; i++) {
            System.out.println("\n--- Teste " + (i + 1) + " ---");
            System.out.println("📤 Enviando: " + (prompts[i] == null ? "null" : "\"" + prompts[i] + "\""));
            
            try {
                agente.processarPrompt(prompts[i]);
                
            } catch (FalhaProcessamentoAgenteException e) {
                // Log formatado com timestamp
                System.out.println(
                    "[LOG-AGENTE] [" + e.getTimestamp() + "] Erro: " + e.getMessage()
                );
                
            } finally {
                System.out.println("[LOG-AGENTE] Finalizando processamento do teste " + (i + 1));
            }
        }
        
        System.out.println("\n=== 📊 PROCESSAMENTO CONCLUÍDO ===");
    }
}