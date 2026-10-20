public class TesteLivroDAO {

    public static void main(String[] args) {

        LivroDAO dao = new LivroDAO();

       
        dao.atualizarLivro(1, "O Pequeno Príncipe - Edição Revisada", "Antoine de Saint-Exupéry", 1943);
        dao.excluirLivro(2);

       
        boolean atualizou = dao.atualizarLivro(999999, "Livro Inexistente", "Autor Desconhecido", 2026);
        boolean excluiu = dao.excluirLivro(999999);

        System.out.println("Atualização: " + atualizou);
        System.out.println("Exclusão: " + excluiu);
    }
}