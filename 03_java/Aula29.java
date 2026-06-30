import java.util.ArrayList; 
import java.util.Scanner;   
public class Aula29 { // Classe principal do programa


// Aqui eu calculo a média das notas da turma
public static double calcularMedia(double[] notas) {

    double soma = 0; // Vou usar isso pra somar todas as notas

    for (double nota : notas) { // Aqui eu passo por cada nota da turma
        soma += nota; // Vou somando uma por uma
    }

    return soma / notas.length; // Aqui eu divido pela quantidade de alunos pra achar a média
}

// Aqui eu conto quantos alunos passaram (nota >= 6)
public static int contarAprovados(double[] notas) {

    int aprovados = 0; // começo com zero aprovados

    for (double nota : notas) { // passo por cada nota

        if (nota >= 6.0) { // se a nota for boa o suficiente
            aprovados++; // conto mais um aprovado
        }
    }

    return aprovados; // retorno o total de aprovados
}

// Aqui eu adiciono um produto na lista
public static void adicionarProduto(ArrayList<String> lista, String nome) {

    lista.add(nome); // simplesmente jogo o produto dentro da lista
}

// Aqui eu mostro os produtos na tela
public static void listarProdutos(ArrayList<String> lista) {

    for (int i = 0; i < lista.size(); i++) { // vou passando item por item

        System.out.println((i + 1) + " - " + lista.get(i)); // mostro bonitinho numerado
    }
}

// Aqui eu descubro o maior número de um array
public static int maiorValor(int[] valores) {

    int maior = valores[0]; // começo assumindo que o primeiro já é o maior

    for (int i = 1; i < valores.length; i++) { // vou comparando com os outros

        if (valores[i] > maior) { // se achar um maior
            maior = valores[i]; // atualizo ele
        }
    }

    return maior; // retorno o maior que encontrei
}

// Aqui comparo dois números e vejo qual é maior
public static int maiorValor(int a, int b) {

    return (a > b) ? a : b; // se a for maior, retorno ele, senão retorno b
}

// Aqui eu junto tudo e mostro o boletim da turma
public static void exibirBoletim(double[] notas) {

    double media = calcularMedia(notas); // pego a média já pronta
    int aprovados = contarAprovados(notas); // pego quantos passaram

    System.out.println("\n--- BOLETIM DA TURMA ---");

    System.out.println("Média da turma: " + media); // mostro a média

    System.out.println("Alunos aprovados: " + aprovados); // mostro aprovados

    if (media >= 6.0) { // vejo se a turma foi bem

        System.out.println("Situação: APROVADA"); // passou

    } else {

        System.out.println("Situação: EM RECUPERAÇÃO"); // precisa melhorar
    }
}

// Aqui começa o programa de verdade (onde tudo acontece)
public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in); // crio o leitor do teclado

    System.out.print("Quantos alunos tem a turma? "); // pergunto quantidade
    int quantidade = scanner.nextInt(); // leio o número

    double[] notas = new double[quantidade]; // crio o array do tamanho certo

    for (int i = 0; i < quantidade; i++) { // vou pedindo nota por nota

        System.out.print("Digite a nota do aluno " + (i + 1) + ": ");
        notas[i] = scanner.nextDouble(); // guardo a nota digitada
    }

    exibirBoletim(notas); // mostro o resultado final

    scanner.close(); // fecho o scanner
}


}
