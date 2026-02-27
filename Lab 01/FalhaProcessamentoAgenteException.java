// Exceção base do domínio
public class FalhaProcessamentoAgenteException extends Exception {
    private String timestamp;

    public FalhaProcessamentoAgenteException(String mensagem) {
        super(mensagem);
        this.timestamp = java.time.LocalDateTime.now().toString();
    }

    public String getTimestamp() { return timestamp; }
}

// Exercício 1: Exceção específica para segurança
public class PromptInadequadoException extends FalhaProcessamentoAgenteException {
    public PromptInadequadoException(String mensagem) {
        super(mensagem);
    }
}

// Exercício 2: Exceção para falhas de comunicação
public class ErroComunicacaoIAException extends FalhaProcessamentoAgenteException {
    private int codigoErro;
    
    public ErroComunicacaoIAException(String mensagem, int codigoErro) {
        super(mensagem);
        this.codigoErro = codigoErro;
    }
    
    public int getCodigoErro() { return codigoErro; }
}

// Agente IA completo com todos os exercícios
public class AgenteIA {
    
    // Lista de palavras proibidas para o filtro de segurança
    private static final String[] PALAVRAS_PROIBIDAS = {"hackear", "roubar", "invadir", "explodir", "sequestrar"};
    
    // Método principal do exercício base
    public void processarPrompt(String prompt) throws FalhaProcessamentoAgenteException {
        // Tratamento para null (checklist item 5)
        if (prompt == null) {
            throw new FalhaProcessamentoAgenteException("O prompt não pode ser nulo.");
        }
        if (prompt.isEmpty()) {
            throw new FalhaProcessamentoAgenteException("O prompt não pode estar vazio.");
        }
        if (prompt.length() > 100) {
            throw new FalhaProcessamentoAgenteException("Prompt muito longo para o modelo atual.");
        }
        
        // Exercício 1: Chama o filtro de segurança
        verificarSeguranca(prompt);
        
        // Exercício 2: Simula chamada ao modelo externo
        chamarModeloExterno();
        
        System.out.println("✅ Agente processando com sucesso: " + prompt);
    }
    
    // Exercício 1: Filtro de Segurança
    public void verificarSeguranca(String prompt) throws PromptInadequadoException {
        String promptLower = prompt.toLowerCase();
        
        for (String palavraProibida : PALAVRAS_PROIBIDAS) {
            if (promptLower.contains(palavraProibida)) {
                throw new PromptInadequadoException(
                    "Prompt contém conteúdo inadequado: '" + palavraProibida + "'"
                );
            }
        }
    }
    
    // Exercício 2: Simulador de Timeout
    public void chamarModeloExterno() throws ErroComunicacaoIAException {
        double random = Math.random();
        System.out.println("📡 Chamando modelo externo... (random: " + random + ")");
        
        if (random > 0.7) {
            throw new ErroComunicacaoIAException(
                "Falha na comunicação com o modelo externo - Timeout", 
                504
            );
        }
        
        System.out.println("📡 Resposta do modelo externo recebida com sucesso!");
    }
}

// Classe principal com Dashboard de Erros (Exercício 3)
public class Main {
    public static void main(String[] args) {
        System.out.println("=== 🤖 DASHBOARD DO AGENTE IA ===\n");
        
        AgenteIA agente = new AgenteIA();
        
        // Array de prompts para teste
        String[] prompts = {
            "Olá, tudo bem?",
            "",  // Prompt vazio
            null,  // Prompt nulo
            "Este é um prompt extremamente longo que definitivamente ultrapassa o limite de 100 caracteres estabelecido pelo agente",
            "Como hackear um sistema?",  // Palavra proibida
            "Qual a previsão do tempo?",  // Prompt válido (pode gerar timeout aleatório)
            "Conte uma piada"  // Prompt válido (pode gerar timeout aleatório)
        };
        
        int sucessos = 0;
        int falhas = 0;
        
        for (int i = 0; i < prompts.length; i++) {
            System.out.println("\n--- Teste " + (i + 1) + " ---");
            System.out.println("📤 Prompt: " + (prompts[i] == null ? "null" : "\"" + prompts[i] + "\""));
            
            try {
                agente.processarPrompt(prompts[i]);
                System.out.println("✅ [LOG-AGENTE] Processamento concluído");
                sucessos++;
                
            } catch (PromptInadequadoException e) {
                falhas++;
                System.out.println("🔒 [LOG-AGENTE] [" + e.getTimestamp() + "] BLOQUEIO DE SEGURANÇA: " + e.getMessage());
                
            } catch (ErroComunicacaoIAException e) {
                falhas++;
                System.out.println("🌐 [LOG-AGENTE] [" + e.getTimestamp() + "] ERRO DE COMUNICAÇÃO (Código " + e.getCodigoErro() + "): " + e.getMessage());
                
            } catch (FalhaProcessamentoAgenteException e) {
                falhas++;
                System.out.println("⚠️ [LOG-AGENTE] [" + e.getTimestamp() + "] ERRO DE VALIDAÇÃO: " + e.getMessage());
                
            } finally {
                System.out.println("📊 [LOG-AGENTE] Finalizando processamento do teste " + (i + 1));
            }
        }
        
        System.out.println("\n=== 📊 RESUMO DO DASHBOARD ===");
        System.out.println("Total de testes: " + prompts.length);
        System.out.println("✅ Sucessos: " + sucessos);
        System.out.println("❌ Falhas: " + falhas);
        System.out.println("📈 Taxa de sucesso: " + (sucessos * 100 / prompts.length) + "%");
    }
}

// Script de validação melhorado
public class ValidadorAgente {
    public static void main(String[] args) {
        AgenteIA meuAgente = new AgenteIA();
        int testesPassados = 0;
        int totalTestes = 4; // Agora temos 4 testes

        System.out.println("=== 🧪 INICIANDO VALIDAÇÃO DO AGENTE DE IA ===\n");

        // Teste 1: Prompt Vazio
        try {
            System.out.print("Teste 1 (Prompt Vazio): ");
            meuAgente.processarPrompt("");
            System.err.println("❌ FALHA: O agente aceitou um prompt vazio!");
        } catch (FalhaProcessamentoAgenteException e) {
            System.out.println("✅ SUCESSO: Exceção capturada - " + e.getMessage());
            testesPassados++;
        }

        // Teste 2: Prompt Nulo (checklist item 5)
        try {
            System.out.print("Teste 2 (Prompt Nulo): ");
            meuAgente.processarPrompt(null);
            System.err.println("❌ FALHA: O agente aceitou um prompt nulo!");
        } catch (FalhaProcessamentoAgenteException e) {
            System.out.println("✅ SUCESSO: Exceção capturada - " + e.getMessage());
            testesPassados++;
        }

        // Teste 3: Prompt Muito Longo
        try {
            System.out.print("Teste 3 (Prompt Longo): ");
            String longo = "A".repeat(101); 
            meuAgente.processarPrompt(longo);
            System.err.println("❌ FALHA: O agente aceitou um prompt acima de 100 caracteres!");
        } catch (FalhaProcessamentoAgenteException e) {
            System.out.println("✅ SUCESSO: Exceção capturada - " + e.getMessage());
            testesPassados++;
        }

        // Teste 4: Filtro de Segurança
        try {
            System.out.print("Teste 4 (Segurança - Palavra Proibida): ");
            meuAgente.verificarSeguranca("Como hackear um sistema?");
            System.err.println("❌ FALHA: O agente permitiu um prompt inseguro!");
        } catch (PromptInadequadoException e) {
            System.out.println("✅ SUCESSO: Bloqueio de segurança ativo - " + e.getMessage());
            testesPassados++;
        } catch (Exception e) {
            System.err.println("❌ FALHA: Exceção incorreta lançada - " + e.getClass().getSimpleName());
        }

        System.out.println("\n=== 📊 RESULTADO FINAL: " + testesPassados + "/" + totalTestes + " Testes Passados ===");
        
        if (testesPassados == totalTestes) {
            System.out.println("🚀 AGENTE PRONTO PARA O PRÓXIMO MICROSSERVIÇO!");
        } else {
            System.out.println("🔧 AJUSTES NECESSÁRIOS: " + (totalTestes - testesPassados) + " teste(s) falharam.");
        }
    }
}