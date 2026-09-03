public class Produto {

    // Atributos privados: encapsulamento
    private int codigo;
    private String nome;
    private double preco;

    public Produto(int codigo, String nome, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
    }

    // Getter e setter do código
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    // Getter e setter do nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter e setter do preço
    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    // Altera o preço normalmente
    public void alterarPreco(double preco) {
        this.preco = preco;
    }

    // Sobrecarga: altera o preço aplicando desconto
    public void alterarPreco(double preco, double desconto) {
        this.preco = preco - (preco * desconto / 100);
    }

    // Representação do produto
    @Override
    public String toString() {
        return codigo + " - " + nome + " - R$ "
                + String.format("%.2f", preco);
    }
}
