import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TelaCadastroLivro extends JDialog {

    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtAno;
    private JLabel lblPrevia;
    private File arquivoCapaSelecionado;

    private TelaLivros telaPrincipal;
    private LivroDAO livroDAO;

    private final Color COR_AZUL_BANNER = new Color(11, 43, 73);
    private final Color COR_FUNDO = Color.WHITE;
    private final Color COR_TEXTO_ESCURO = new Color(30, 35, 45);
    private final Color COR_TEXTO_CINZA = new Color(115, 122, 135);
    private final Color COR_BOTAO_ROXO = new Color(124, 58, 237);
    private final Color COR_BORDA = new Color(220, 224, 232);

    public TelaCadastroLivro(TelaLivros parent, LivroDAO dao) {
        super(parent, "Cadastrar Novo Livro - BiblioTech", true);
        this.telaPrincipal = parent;
        this.livroDAO = dao;

        setSize(580, 440);
        setLocationRelativeTo(parent);

        JPanel painelRaiz = new JPanel(new BorderLayout(0, 15));
        painelRaiz.setBackground(COR_FUNDO);
        setContentPane(painelRaiz);

        JPanel topoAzul = new JPanel(new BorderLayout());
        topoAzul.setBackground(COR_AZUL_BANNER);
        topoAzul.setBorder(new EmptyBorder(16, 22, 16, 22));

        JLabel lblHeader = new JLabel("Novo Livro no Catálogo");
        lblHeader.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblHeader.setForeground(Color.WHITE);
        topoAzul.add(lblHeader, BorderLayout.WEST);
        painelRaiz.add(topoAzul, BorderLayout.NORTH);

        JPanel painelCentro = new JPanel(new GridLayout(1, 2, 18, 0));
        painelCentro.setBackground(COR_FUNDO);
        painelCentro.setBorder(new EmptyBorder(5, 22, 5, 22));

        JPanel painelCampos = new JPanel();
        painelCampos.setLayout(new BoxLayout(painelCampos, BoxLayout.Y_AXIS));
        painelCampos.setBackground(COR_FUNDO);

        txtTitulo = criarCampo();
        txtAutor = criarCampo();
        txtAno = criarCampo();

        painelCampos.add(criarLabel("Título do Livro:"));
        painelCampos.add(Box.createVerticalStrut(4));
        painelCampos.add(txtTitulo);
        painelCampos.add(Box.createVerticalStrut(12));

        painelCampos.add(criarLabel("Autor:"));
        painelCampos.add(Box.createVerticalStrut(4));
        painelCampos.add(txtAutor);
        painelCampos.add(Box.createVerticalStrut(12));

        painelCampos.add(criarLabel("Ano de Publicação:"));
        painelCampos.add(Box.createVerticalStrut(4));
        painelCampos.add(txtAno);
        painelCampos.add(Box.createVerticalStrut(16));

        JButton btnEscolherCapa = criarBotao("Selecionar capa", COR_BOTAO_ROXO);
        btnEscolherCapa.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelCampos.add(btnEscolherCapa);

        JPanel painelPrevia = new JPanel(new BorderLayout());
        painelPrevia.setBackground(new Color(245, 247, 250));
        lblPrevia = new JLabel("Prévia da Capa", SwingConstants.CENTER);
        lblPrevia.setForeground(COR_TEXTO_CINZA);
        lblPrevia.setBorder(new LineBorder(COR_BORDA, 1));
        painelPrevia.add(lblPrevia, BorderLayout.CENTER);

        painelCentro.add(painelCampos);
        painelCentro.add(painelPrevia);
        painelRaiz.add(painelCentro, BorderLayout.CENTER);

        JPanel painelRodape = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 12));
        painelRodape.setBackground(COR_FUNDO);
        painelRodape.setBorder(new EmptyBorder(0, 22, 8, 22));
        JButton btnCancelar = criarBotao("Cancelar", new Color(110, 118, 130));
        JButton btnSalvar = criarBotao("Salvar Livro", COR_AZUL_BANNER);
        painelRodape.add(btnCancelar);
        painelRodape.add(btnSalvar);
        painelRaiz.add(painelRodape, BorderLayout.SOUTH);

        btnEscolherCapa.addActionListener(e -> escolherCapa());
        btnCancelar.addActionListener(e -> dispose());
        btnSalvar.addActionListener(e -> salvarLivro());
    }

    private JLabel criarLabel(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setForeground(COR_TEXTO_ESCURO);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 12));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }

    private JTextField criarCampo() {
        JTextField txt = new JTextField();
        txt.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        txt.setBackground(COR_FUNDO);
        txt.setForeground(COR_TEXTO_ESCURO);
        txt.setAlignmentX(Component.LEFT_ALIGNMENT);
        txt.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COR_BORDA, 1),
                new EmptyBorder(4, 8, 4, 8)
        ));
        return txt;
    }

    private JButton criarBotao(String texto, Color cor) {
        JButton btn = new JButton(texto);
        btn.setBackground(cor);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(new EmptyBorder(7, 15, 7, 15));
        return btn;
    }

    private void escolherCapa() {
        JFileChooser chooser = new JFileChooser(new File("."));
        chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imagens JPG e PNG", "jpg", "jpeg", "png"));
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            arquivoCapaSelecionado = chooser.getSelectedFile();
            ImageIcon icon = new ImageIcon(arquivoCapaSelecionado.getAbsolutePath());
            Image img = icon.getImage().getScaledInstance(160, 225, Image.SCALE_SMOOTH);
            lblPrevia.setIcon(new ImageIcon(img));
            lblPrevia.setText("");
        }
    }

    private void salvarLivro() {
        try {
            String titulo = txtTitulo.getText().trim();
            String autor = txtAutor.getText().trim();
            int ano = Integer.parseInt(txtAno.getText().trim());

            ValidadorLivro.validar(titulo, autor, ano);

            int idGerado = livroDAO.adicionarLivroRetornandoId(titulo, autor, ano);

            if (idGerado > 0 && arquivoCapaSelecionado != null) {
                String caminhoCapa = ImagemLivro.salvar(arquivoCapaSelecionado.toPath(), idGerado);
                livroDAO.atualizarCapa(idGerado, caminhoCapa);
            }

            JOptionPane.showMessageDialog(this, "Livro cadastrado com sucesso!");
            telaPrincipal.listarTodosComTratamento();
            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "O ano deve ser um número inteiro.");
        } catch (LivroInvalidoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (PersistenciaException ex) {
            JOptionPane.showMessageDialog(this, "Não foi possível salvar o livro no banco de dados.", "Erro de conexão", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Livro salvo, mas houve erro ao copiar a imagem da capa.", "Erro de imagem", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}