/*
 * Disciplina: 2026-PS
 * Projeto   : bibliotech
 * Arquivo   : Leitor.java
 * Autor     : Kauê
 * Descricao : Leitor E UM TIPO DE Usuario: herda nome, matricula e entrar().
 */
public class Leitor extends Usuario {

    // Se o que a caixa leitor acrescenta. Nome e matricula ja vem de Usuario.
    private int limiteEmprestimos;
    private int livrosEmMaos; // nao estava na caixa: o codigo pediu

    public Leitor(String nome, String matricula, int limiteEmprestimos) {
        super(nome, matricula); // primeira linha: preenche a parte de cima primeiro
        this.limiteEmprestimos = limiteEmprestimos;
        this.livrosEmMaos = 0;
    }

    public int getLimiteEmprestimos() {
        return limiteEmprestimos;
    }

    public int getLivrosEmMaos() {
        return livrosEmMaos;
    }

    // OPERACAO DA CAIXA: podePegarEmprestado().
    public boolean podePegarEmprestado() {
        return livrosEmMaos < limiteEmprestimos;
    }

    // Os dois metodos que o emprestimo vai usar na Aula 38.
    public void pegouLivro() {
        this.livrosEmMaos = this.livrosEmMaos + 1;
    }

    public void devolveuLivro() {
        this.livrosEmMaos = this.livrosEmMaos - 1;
    }

    public String toString() {
        return "Leitor " + getNome() + " (" + getMatricula() + ") - "
               + livrosEmMaos + " de " + limiteEmprestimos + " livros";
    }
}