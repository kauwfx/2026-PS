import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaLivros extends JFrame {

    private final LivroDAO dao = new LivroDAO();

    private final DefaultTableModel modelo;
    private final JTable tabela;

    // Campos de texto para ID e Título
    private final JTextField campoId = new JTextField(5);
    private final JTextField campoTitulo = new JTextField(12);

    private final JLabel status = new JLabel("Pronto.");

    public TelaLivros() {

        setTitle("BiblioTech - Consulta de Livros");
        setSize(950, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));

        // Modelo da tabela desabilitando edição direta nas células
        modelo = new DefaultTableModel(
            new Object[]{"ID", "Título", "Autor", "Ano"}, 0
        ) {
            @Override
            public boolean isCellEditable(int linha, int coluna) {
                return false;
            }
        };

        tabela = new JTable(modelo);

        add(new JScrollPane(tabela), BorderLayout.CENTER);

        // Painel superior com os controles de busca por ID e por Título
        JPanel controles = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));

        JButton buscarId = new JButton("Buscar por ID");
        JButton buscarTitulo = new JButton("Buscar por título");
        JButton atualizar = new JButton("Atualizar listagem");
        JButton mostrarTodos = new JButton("Mostrar todos");
        JButton limpar = new JButton("Limpar");

        // Área de busca por ID
        controles.add(new JLabel("ID:"));
        controles.add(campoId);
        controles.add(buscarId);

        // Área de busca por Título (Parte 18)
        controles.add(new JLabel("Título:"));
        controles.add(campoTitulo);
        controles.add(buscarTitulo);

        // Botões auxiliares
        controles.add(limpar);
        controles.add(atualizar);
        controles.add(mostrarTodos);

        add(controles, BorderLayout.NORTH);
        add(status, BorderLayout.SOUTH);

        // Eventos dos botões de busca
        buscarId.addActionListener(e -> buscarLivro());
        buscarTitulo.addActionListener(e -> buscarPorTitulo());

        // Eventos para executar a busca ao pressionar a tecla Enter nos campos
        campoId.addActionListener(e -> buscarLivro());
        campoTitulo.addActionListener(e -> buscarPorTitulo());

        // Eventos dos botões de listagem geral
        atualizar.addActionListener(e -> carregarLivros());
        mostrarTodos.addActionListener(e -> carregarLivros());

        // Evento do botão Limpar (Limpa ambos os campos de busca)
        limpar.addActionListener(e -> {
            campoId.setText("");
            campoTitulo.setText("");
            campoTitulo.requestFocus();
        });

        carregarLivros();
    }

    private void carregarLivros() {
        modelo.setRowCount(0);

        List<Livro> livros = dao.listarLivros();

        for (Livro livro : livros) {
            adicionarLinha(livro);
        }

        status.setText("Livros apresentados: " + livros.size());
    }

    private void adicionarLinha(Livro livro) {
        modelo.addRow(new Object[]{
            livro.getId(),
            livro.getTitulo(),
            livro.getAutor(),
            livro.getAno()
        });
    }

    private void buscarLivro() {
        String texto = campoId.getText().trim();

        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Digite um ID para realizar a busca.",
                "Campo vazio",
                JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        int id;

        try {
            id = Integer.parseInt(texto);

            if (id <= 0) {
                throw new NumberFormatException();
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Informe um ID inteiro positivo.",
                "Entrada inválida",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        Livro livro = dao.buscarLivroPorId(id);

        modelo.setRowCount(0);

        if (livro == null) {
            status.setText("Nenhum livro encontrado para o ID " + id);
            JOptionPane.showMessageDialog(this, "Livro não encontrado.");
        } else {
            adicionarLinha(livro);
            status.setText("Livro encontrado: " + livro.getTitulo());
        }
    }

    // Método da Parte 18: Busca de livros utilizando o método LIKE do DAO
    private void buscarPorTitulo() {
        String titulo = campoTitulo.getText().trim();

        if (titulo.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Informe o título do livro.",
                "Campo obrigatório",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        List<Livro> livros = dao.buscarLivrosPorTitulo(titulo);

        modelo.setRowCount(0);

        for (Livro livro : livros) {
            adicionarLinha(livro);
        }

        status.setText("Livros encontrados: " + livros.size());

        if (livros.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Nenhum livro encontrado.",
                "Resultado da busca",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaLivros().setVisible(true);
        });
    }
}