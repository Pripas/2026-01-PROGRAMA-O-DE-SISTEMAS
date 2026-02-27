public class ModuloConexao {
    public static void validarLink() throws ErroComunicacaoIAException {
        double random = Math.random();
        System.out.println("   📡 Testando conexão... (random: " + random + ")");
        
        if (random > 0.8) {
            throw new ErroComunicacaoIAException(
                "Falha na comunicação com a GPU - Rede instável (timeout)"
            );
        }
        System.out.println("   ✅ Conexão estabelecida com sucesso!");
    }
}