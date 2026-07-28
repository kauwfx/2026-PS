public class Main {

    public static void main(String[] args) {

        Produto produto1 = new Produto("Teclado", 120.00, 8);

        System.out.println("Nome: " + produto1.getNome());
        System.out.println("Preço: " + produto1.getPreco());
        System.out.println("Quantidade: " + produto1.getQuantidade());

        System.out.println();

        produto1.adicionarEstoque(5);

        System.out.println("Depois de adicionar:");
        System.out.println("Quantidade: " + produto1.getQuantidade());

        System.out.println();

        produto1.removerEstoque(3);

        System.out.println("Depois de remover:");
        System.out.println("Quantidade: " + produto1.getQuantidade());

        System.out.println();

        produto1.setPreco(150.00);

        System.out.println("Novo preço: " + produto1.getPreco());

        System.out.println("Valor total do estoque: " + produto1.valorTotalEstoque());
    }
}