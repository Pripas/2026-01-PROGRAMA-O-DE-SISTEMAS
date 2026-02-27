import java.util.ArrayList;
import java.util.List;

public class Main {
    
    // Método auxiliar para repetir strings (compatível com Java 8+)
    private static String repeatString(String str, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
    
    // Exercício 2: Método polimórfico para processar fila de agentes
    public static void processarFila(List<AgenteIA> lista, String comando) {
        System.out.println("\n=== 🚀 PROCESSANDO FILA DE AGENTES ===");
        System.out.println("Comando: \"" + comando + "\"");
        System.out.println("Total de agentes na fila: " + lista.size());
        
        for (int i = 0; i < lista.size(); i++) {
            AgenteIA agente = lista.get(i);
            System.out.println("\n--- Processando Agente " + (i + 1) + ": " + agente.getNome() + " ---");
            
            try {
                // Polimorfismo em ação! Cada agente executa sua própria versão
                agente.processarRequisicao(comando);
                System.out.println("   ✅ Processamento concluído com sucesso!");
                
            } catch (PromptInadequadoException e) {
                System.out.println("   🔒 [SEGURANÇA] [" + e.getTimestamp() + "] " + e.getMessage());
                
            } catch (ErroComunicacaoIAException e) {
                System.out.println("   🌐 [REDE] [" + e.getTimestamp() + "] " + e.getMessage());
                
            } catch (FalhaProcessamentoAgenteException e) {
                System.out.println("   ⚠️ [ERRO] [" + e.getTimestamp() + "] " + e.getMessage());
                
            } finally {
                System.out.println("   📊 Finalizando processamento do agente " + agente.getNome());
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== 🤖 LABORATÓRIO DE AGENTES IA - VERSÃO EVOLUÍDA ===\n");
        
        // Exercício 2: Criando lista polimórfica de agentes
        List<AgenteIA> orquestrador = new ArrayList<>();
        
        // Adicionando diferentes tipos de agentes (polimorfismo)
        orquestrador.add(new AgenteTexto("GPT-4"));
        orquestrador.add(new AgenteImagem("DALL-E 3"));
        orquestrador.add(new AgenteTexto("Claude-3"));
        orquestrador.add(new AgenteImagem("Midjourney"));
        
        // Teste 1: Comando normal
        System.out.println("\n📌 TESTE 1 - Comando Normal");
        processarFila(orquestrador, "Crie uma imagem de um gato astronauta");
        
        // Teste 2: Comando com termo sensível (deve ser bloqueado pelo AgenteImagem)
        System.out.println("\n\n📌 TESTE 2 - Comando com Termo Sensível");
        processarFila(orquestrador, "Como hackear um sistema de segurança");
        
        // Teste 3: Comando muito longo (deve falhar no AgenteTexto)
        System.out.println("\n\n📌 TESTE 3 - Comando Muito Longo");
        // CORREÇÃO: Substituído "x".repeat(450) pelo método repeatString
        String textoLongo = "Este é um texto extremamente longo que vai ultrapassar o limite de 500 caracteres. " +
                           "Repetindo: " + repeatString("x", 450);
        processarFila(orquestrador, textoLongo);
        
        // Teste 4: Comando vazio
        System.out.println("\n\n📌 TESTE 4 - Comando Vazio");
        processarFila(orquestrador, "");
        
        System.out.println("\n=== 🏁 LABORATÓRIO CONCLUÍDO ===");
    }
}