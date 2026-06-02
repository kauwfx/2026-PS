import java.util.Scanner;

public class CardapioRestaurante {

    public static void main(String[] args) {

        // Cria o Scanner para ler dados do teclado
        Scanner entrada = new Scanner(System.in);

        // Exibe o cardápio
        System.out.println("=================================");
        System.out.println("  AMAZONAS - CARDÁPIO ELETRÔNICO");
        System.out.println("=================================");
        System.out.println("1 - X-Burguer .......... R$ 18,00");
        System.out.println("2 - Pizza .............. R$ 35,00");
        System.out.println("3 - Suco Natural ....... R$ 8,00");
        System.out.println("4 - Café ............... R$ 5,00");
        System.out.println("5 - Refrigerante ....... R$ 4,00");
        System.out.println("=================================");

        // Pede a opção do usuário
        System.out.print("Escolha uma opção: ");
        int opcao = entrada.nextInt();

        // Variáveis para guardar o nome e o preço do produto
        String produto = "";
        double preco = 0;

        // Verifica qual item foi escolhido
        if (opcao == 1) {
            produto = "X-Burguer";
            preco = 18.0;
        } else if (opcao == 2) {
            produto = "Pizza";
            preco = 35.0;
        } else if (opcao == 3) {
            produto = "Suco Natural";
            preco = 8.0;
        } else if (opcao == 4) {
            produto = "Café";
            preco = 5.0;
        } else if (opcao == 5) {
            produto = "Refrigerante";
            preco = 4.0;
        } else {
            // Caso a opção não exista
            System.out.println("Opção inválida!");
            entrada.close();
            return;
        }

        // Pergunta a quantidade desejada
        System.out.print("Digite a quantidade: ");
        int quantidade = entrada.nextInt();

        // Calcula o valor total
        double total = preco * quantidade;

        // Mostra o resumo do pedido
        System.out.println("\n===== RESUMO DO PEDIDO =====");
        System.out.println("Produto: " + produto);
        System.out.println("Preço: R$ " + preco);
        System.out.println("Quantidade: " + quantidade);
        System.out.println("Total: R$ " + total);

        // Fecha o Scanner
        entrada.close();
    }
}