public class TesteLivroDAO {

    public static void main(String[] args) {

        LivroDAO dao = new LivroDAO();

        // Inserção
        dao.adicionarLivro("O Pequeno Príncipe", "Antoine de Saint-Exupéry", 1943);
        dao.adicionarLivro("1984", "George Orwell", 1949);
        dao.adicionarLivro("Dom Casmurro", "Machado de Assis", 1899);

        // Atualização e Exclusão (ajuste o ID 1 e 2 se no seu banco for diferente)
        dao.atualizarLivro(1, "O Pequeno Príncipe - Edição Revisada", "Antoine de Saint-Exupéry", 1943);
        dao.excluirLivro(2);

        // Teste de registro inexistente
        boolean atualizou = dao.atualizarLivro(999999, "Livro Inexistente", "Autor Desconhecido", 2026);
        boolean excluiu = dao.excluirLivro(999999);

        System.out.println("Atualização: " + atualizou);
        System.out.println("Exclusão: " + excluiu);
    }
}