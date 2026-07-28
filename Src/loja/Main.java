package Src.loja; // Mesmo pacote da classe Produto

public class Main {
    public static void main(String[] args) {
       
        Produto produto1 = new Produto("Teclado", 120.00, 8); // dia nascimento = 8
        Produto produto2 = new Produto("Mouse", 45.50, 15);
        Produto produto3 = new Produto("Monitor", 890.00, 5);

        System.out.println("=== ESTADO INICIAL ===");
        System.out.println(produto1.exibirResumo());
        System.out.println(produto2.exibirResumo());
        System.out.println(produto3.exibirResumo() + "\n");

        System.out.println("=== ALTERAÇÃO VÁLIDA ===");
        produto1.adicionarEstoque(5);
        produto2.setPreco(50.00);
        System.out.println(produto1.exibirResumo());
        System.out.println(produto2.exibirResumo() + "\n");

       
        System.out.println("=== ALTERAÇÕES INVÁLIDAS (serão recusadas) ===");
        produto1.setPreco(-50); 
        produto3.setNome("");   // Nome vazio
        boolean removeu = produto3.removerEstoque(10); // Mais do que tem em estoque
        System.out.println("Tentativa de remover 10 do Monitor: " + (removeu ? "Sucesso" : "Falha"));
        System.out.println(produto3.exibirResumo() + "\n");

    
        System.out.println("=== TESTES OBRIGATÓRIOS ===");
        System.out.println("1. Produto criado: " + produto1.getNome());
        System.out.println("2. Nome vazio recusado: " + produto3.getNome());
        System.out.println("3. Preço negativo recusado: " + produto1.getPreco());
        System.out.println("4. Adição de estoque funcionou: " + produto1.getQuantidade());
        System.out.println("5. Remoção impossível preservou estoque: " + produto3.getQuantidade());
    }
}
