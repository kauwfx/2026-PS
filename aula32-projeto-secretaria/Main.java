import java.util.ArrayList;
import java.util.Scanner;

/**
 * Projeto Secretaria - Classe Main
 * Autor: Kauê
 * Data: 2026.08.27
 */
public class Main {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ArrayList<Aluno> lista = new ArrayList<>();

        while (true) {
            System.out.println("\n==========================================");
            System.out.println("   SECRETARIA DO CAMPUS - por Kauê");
            System.out.println("==========================================");
            System.out.println("[1] Cadastrar aluno");
            System.out.println("[2] Listar alunos");
            System.out.println("[3] Buscar por matricula");
            System.out.println("[4] Atualizar curso");
            System.out.println("[5] Remover aluno");
            System.out.println("[6] Relatorio");
            System.out.println("[7] Buscar por nome"); // Melhoria (a) para Conceito A
            System.out.println("[0] Sair");
            System.out.print("Sua escolha: ");

            String opcao = teclado.nextLine();

            if (opcao.equals("0")) {
                System.out.println("Secretaria fechada. Ate a proxima!");
                break;
            } else if (opcao.equals("1")) {
                cadastrar(teclado, lista);
            } else if (opcao.equals("2")) {
                listar(lista);
            } else if (opcao.equals("3")) {
                buscar(teclado, lista);
            } else if (opcao.equals("4")) {
                atualizar(teclado, lista);
            } else if (opcao.equals("5")) {
                remover(teclado, lista);
            } else if (opcao.equals("6")) {
                relatorio(lista, teclado);
            } else if (opcao.equals("7")) {
                buscarPorNomeMenu(teclado, lista);
            } else {
                System.out.println("Opcao invalida! Vale 0, 1, 2, 3, 4, 5, 6 ou 7.");
            }
        }
        teclado.close();
    }

    // --- MÉTODOS AUXILIARES ---

    public static Aluno buscarPorMatricula(ArrayList<Aluno> lista, String matricula) {
        for (int i = 0; i < lista.size(); i++) {
            Aluno a = lista.get(i);
            if (a.getMatricula().equals(matricula)) {
                return a;
            }
        }
        return null;
    }

    // --- MÉTODOS DO MENU ---

    public static void cadastrar(Scanner teclado, ArrayList<Aluno> lista) {
        System.out.print("Matricula: ");
        String matricula = teclado.nextLine();

        if (buscarPorMatricula(lista, matricula) != null) {
            System.out.println("Ja existe ficha com a matricula " + matricula + "!");
            return;
        }

        System.out.print("Nome: ");
        String nome = teclado.nextLine();
        System.out.print("Curso: ");
        String curso = teclado.nextLine();
        System.out.print("Cidade: ");
        String cidade = teclado.nextLine();

        Aluno novo = new Aluno(nome, matricula, curso, cidade);
        lista.add(novo);
        System.out.println("Ficha de " + nome + " arquivada!");
    }

    public static void listar(ArrayList<Aluno> lista) {
        if (lista.isEmpty()) {
            System.out.println("Gaveteiro vazio.");
            return;
        }
        System.out.println("--- FICHAS NO GAVETEIRO: " + lista.size() + " ---");
        for (int i = 0; i < lista.size(); i++) {
            Aluno a = lista.get(i);
            System.out.println(a); // Chama toString() implicitamente
        }
    }

    public static void buscar(Scanner teclado, ArrayList<Aluno> lista) {
        System.out.print("Matricula procurada: ");
        String matricula = teclado.nextLine();

        Aluno a = buscarPorMatricula(lista, matricula);

        if (a == null) {
            System.out.println("Nenhuma ficha com a matricula " + matricula + ".");
            return;
        }

        System.out.println("Achei: " + a);
    }

    public static void atualizar(Scanner teclado, ArrayList<Aluno> lista) {
        System.out.print("Matricula da ficha a atualizar: ");
        String matricula = teclado.nextLine();

        Aluno a = buscarPorMatricula(lista, matricula);

        if (a == null) {
            System.out.println("Nenhuma ficha com a matricula " + matricula + ".");
            return;
        }

        System.out.print("Novo curso de " + a.getNome() + ": ");
        String novoCurso = teclado.nextLine();
        a.setCurso(novoCurso);

        System.out.println("Ficha atualizada: " + a);
    }

    public static void remover(Scanner teclado, ArrayList<Aluno> lista) {
        System.out.print("Matricula da ficha a remover: ");
        String matricula = teclado.nextLine();

        Aluno a = buscarPorMatricula(lista, matricula);

        if (a == null) {
            System.out.println("Nenhuma ficha com a matricula " + matricula + ".");
            return;
        }

        System.out.print("Tem certeza que remove " + a.getNome() + "? (s/n): ");
        String resposta = teclado.nextLine();

        if (resposta.equalsIgnoreCase("s")) {
            lista.remove(a);
            System.out.println("Ficha removida.");
        } else {
            System.out.println("Remocao cancelada.");
        }
    }

    public static void relatorio(ArrayList<Aluno> lista, Scanner teclado) {
        System.out.println("--- RELATORIO DA SECRETARIA ---");
        System.out.println("Total de fichas: " + lista.size());

        System.out.print("Contar alunos de qual curso? ");
        String cursoProcurado = teclado.nextLine();

        // preparar -> percorrer -> usar
        int contador = 0; // preparar
        for (int i = 0; i < lista.size(); i++) { // percorrer
            if (lista.get(i).getCurso().equalsIgnoreCase(cursoProcurado)) {
                contador++;
            }
        }

        // usar
        System.out.println("Alunos de " + cursoProcurado + ": " + contador);
    }

    // Melhoria (a): Busca por Nome
    public static void buscarPorNomeMenu(Scanner teclado, ArrayList<Aluno> lista) {
        System.out.print("Nome procurado: ");
        String nomeProcurado = teclado.nextLine();

        boolean encontrou = false;
        for (int i = 0; i < lista.size(); i++) {
            Aluno a = lista.get(i);
            if (a.getNome().equalsIgnoreCase(nomeProcurado)) {
                System.out.println("Achei: " + a);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum aluno encontrado com o nome " + nomeProcurado + ".");
        }
    }
}