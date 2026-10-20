public class TesteLivroDAO {

    public static void main(String[] args) {
        LivroDAO dao = new LivroDAO();

        // 1. Cadastra "O Pequeno Príncipe"
        boolean l1 = dao.adicionarLivro("O Pequeno Príncipe", "Antoine de Saint-Exupéry", 1943);
        System.out.println("Cadastrou O Pequeno Príncipe: " + l1);

        // 2. Cadastra outro livro de sua escolha
        boolean l2 = dao.adicionarLivro("1984", "George Orwell", 1949);
        System.out.println("Cadastrou 1984: " + l2);

        // 4. Atualiza o título do primeiro livro cadastrado no desafio (id = 2 ou conforme o seu banco)
        // Dica: ajuste o ID se necessário conforme o retorno do banco
        boolean alt = dao.atualizarLivro(2, "O Pequeno Príncipe - Edição Revisada", "Antoine de Saint-Exupéry", 1943);
        System.out.println("Atualizou título: " + alt);

        // 5. Exclui o segundo livro cadastrado (id = 3 ou conforme seu banco)
        boolean exc = dao.excluirLivro(3);
        System.out.println("Excluiu segundo livro: " + exc);
    }
}