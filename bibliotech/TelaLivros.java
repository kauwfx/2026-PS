import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class TelaLivros extends JFrame {

    private JTextField txtId;
    private JTextField txtTitulo;
    private JPanel painelGridLivros;
    private JLabel lblTotal;
    private Livro livroSelecionado;
    private JPanel cardSelecionado;

    private LivroDAO livroDAO;

    private final Color COR_AZUL_BANNER = new Color(11, 43, 73);
    private final Color COR_AZUL_DETALHE = new Color(22, 68, 112);
    private final Color COR_FUNDO_GERAL = new Color(242, 244, 248);
    private final Color COR_FUNDO_CONTEUDO = Color.WHITE;
    private final Color COR_TEXTO_ESCURO = new Color(30, 35, 45);
    private final Color COR_TEXTO_CINZA = new Color(115, 122, 135);
    private final Color COR_BOTAO_ESCURO = new Color(24, 28, 36);
    private final Color COR_BOTAO_ROXO = new Color(124, 58, 237);

    public TelaLivros() {
        livroDAO = new LivroDAO();

        setTitle("Catálogo BiblioTech");
        setSize(1160, 740);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painelRaiz = new JPanel(new BorderLayout(0, 14));
        painelRaiz.setBackground(COR_FUNDO_GERAL);
        painelRaiz.setBorder(new EmptyBorder(10, 14, 12, 14));
        setContentPane(painelRaiz);

        // ================= BANNER AZUL MARINHO =================
        JPanel bannerSuperior = new JPanel(new BorderLayout(15, 14)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int w = getWidth();
                int h = getHeight();
                int r = 65;

                Path2D caminho = new Path2D.Double();
                caminho.moveTo(0, 0);
                caminho.lineTo(w, 0);
                caminho.lineTo(w, h - r);
                caminho.quadTo(w, h, w - r, h);
                caminho.lineTo(0, h);
                caminho.closePath();

                g2.setColor(COR_AZUL_BANNER);
                g2.fill(caminho);

                g2.setColor(COR_AZUL_DETALHE);
                g2.setStroke(new BasicStroke(2f));
                for (int x = w - 340; x < w - 40; x += 65) {
                    for (int y = 20; y < h - 30; y += 65) {
                        g2.drawRoundRect(x, y, 42, 42, 14, 14);
                    }
                }
                g2.dispose();
            }
        };
        bannerSuperior.setOpaque(false);
        bannerSuperior.setBorder(new EmptyBorder(24, 32, 24, 36));

        JPanel painelTextosBanner = new JPanel();
        painelTextosBanner.setLayout(new BoxLayout(painelTextosBanner, BoxLayout.Y_AXIS));
        painelTextosBanner.setOpaque(false);

        JLabel lblTitulo = new JLabel("Catálogo");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 34));
        lblTitulo.setForeground(Color.WHITE);

        JLabel lblSubtitulo = new JLabel("Faça uma busca e navegue pelos livros disponíveis na biblioteca.");
        lblSubtitulo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblSubtitulo.setForeground(new Color(215, 226, 238));

        painelTextosBanner.add(lblTitulo);
        painelTextosBanner.add(Box.createVerticalStrut(6));
        painelTextosBanner.add(lblSubtitulo);
        bannerSuperior.add(painelTextosBanner, BorderLayout.NORTH);

        JPanel painelControlesBanner = new JPanel(new BorderLayout(15, 10));
        painelControlesBanner.setOpaque(false);

        JPanel barraPesquisaBranca = new JPanel(new BorderLayout(8, 0)) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        barraPesquisaBranca.setOpaque(false);
        barraPesquisaBranca.setBorder(new EmptyBorder(6, 14, 6, 10));
        barraPesquisaBranca.setPreferredSize(new Dimension(540, 44));

        txtTitulo = new JTextField();
        txtTitulo.setBorder(null);
        txtTitulo.setOpaque(false);
        txtTitulo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtTitulo.setForeground(COR_TEXTO_ESCURO);
        txtTitulo.setToolTipText("Busque por título do livro");

        JButton btnLupa = new JButton("🔍 Buscar");
        btnLupa.setBackground(COR_AZUL_BANNER);
        btnLupa.setForeground(Color.WHITE);
        btnLupa.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnLupa.setFocusPainted(false);
        btnLupa.setBorder(new EmptyBorder(6, 14, 6, 14));
        btnLupa.setCursor(new Cursor(Cursor.HAND_CURSOR));

        barraPesquisaBranca.add(txtTitulo, BorderLayout.CENTER);
        barraPesquisaBranca.add(btnLupa, BorderLayout.EAST);

        JPanel linhaSuperiorBusca = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        linhaSuperiorBusca.setOpaque(false);
        linhaSuperiorBusca.add(barraPesquisaBranca);

        JPanel linhaInferiorBanner = new JPanel(new BorderLayout());
        linhaInferiorBanner.setOpaque(false);

        JPanel painelFiltros = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 4));
        painelFiltros.setOpaque(false);

        JLabel lblId = new JLabel("ID:");
        lblId.setForeground(Color.WHITE);
        lblId.setFont(new Font("SansSerif", Font.BOLD, 13));

        txtId = new JTextField(4);
        txtId.setBorder(new EmptyBorder(4, 6, 4, 6));

        JButton btnBuscarId = criarBotao("Buscar ID", COR_AZUL_DETALHE);
        JButton btnMostrarTodos = criarBotao("Mostrar todos", COR_AZUL_DETALHE);
        JButton btnLimpar = criarBotao("Limpar", COR_AZUL_DETALHE);

        painelFiltros.add(lblId);
        painelFiltros.add(txtId);
        painelFiltros.add(btnBuscarId);
        painelFiltros.add(btnMostrarTodos);
        painelFiltros.add(btnLimpar);

        JPanel painelAcoesDireita = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 4));
        painelAcoesDireita.setOpaque(false);

        JButton btnMeuPerfil = criarBotao("Meu Perfil", COR_AZUL_DETALHE);
        JButton btnSelecionarCapa = criarBotao("Selecionar capa", COR_BOTAO_ROXO);
        JButton btnNovoLivro = criarBotao("+ Novo Livro", COR_BOTAO_ESCURO);

        painelAcoesDireita.add(btnMeuPerfil);
        painelAcoesDireita.add(btnSelecionarCapa);
        painelAcoesDireita.add(btnNovoLivro);

        linhaInferiorBanner.add(painelFiltros, BorderLayout.WEST);
        linhaInferiorBanner.add(painelAcoesDireita, BorderLayout.EAST);

        painelControlesBanner.add(linhaSuperiorBusca, BorderLayout.NORTH);
        painelControlesBanner.add(linhaInferiorBanner, BorderLayout.SOUTH);
        bannerSuperior.add(painelControlesBanner, BorderLayout.CENTER);

        painelRaiz.add(bannerSuperior, BorderLayout.NORTH);

        // ================= ÁREA BRANCA INFERIOR =================
        JPanel painelCorpo = new JPanel(new BorderLayout(0, 12)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COR_FUNDO_CONTEUDO);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 28, 28);
                g2.dispose();
            }
        };
        painelCorpo.setOpaque(false);
        painelCorpo.setBorder(new EmptyBorder(18, 24, 18, 24));

        lblTotal = new JLabel("Mostrando 0 resultados.");
        lblTotal.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblTotal.setForeground(COR_TEXTO_ESCURO);
        painelCorpo.add(lblTotal, BorderLayout.NORTH);

        painelGridLivros = new JPanel(new GridLayout(0, 5, 22, 24));
        painelGridLivros.setBackground(COR_FUNDO_CONTEUDO);

        JPanel wrapperGrid = new JPanel(new BorderLayout());
        wrapperGrid.setBackground(COR_FUNDO_CONTEUDO);
        wrapperGrid.add(painelGridLivros, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(wrapperGrid);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(COR_FUNDO_CONTEUDO);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        painelCorpo.add(scroll, BorderLayout.CENTER);
        painelRaiz.add(painelCorpo, BorderLayout.CENTER);

        // ================= EVENTOS =================
        btnLupa.addActionListener(e -> buscarPorTitulo());
        txtTitulo.addActionListener(e -> buscarPorTitulo());
        btnBuscarId.addActionListener(e -> buscarPorId());
        txtId.addActionListener(e -> buscarPorId());

        btnMostrarTodos.addActionListener(e -> listarTodosComTratamento());
        btnLimpar.addActionListener(e -> {
            txtId.setText("");
            txtTitulo.setText("");
            listarTodosComTratamento();
        });

        btnMeuPerfil.addActionListener(e -> {
            TelaPerfilUsuario telaPerfil = new TelaPerfilUsuario(this);
            telaPerfil.setVisible(true);
        });

        btnSelecionarCapa.addActionListener(e -> selecionarCapaParaLivro());

        btnNovoLivro.addActionListener(e -> {
            TelaCadastroLivro telaCadastro = new TelaCadastroLivro(this, livroDAO);
            telaCadastro.setVisible(true);
        });

        listarTodosComTratamento();
    }

    private JButton criarBotao(String texto, Color cor) {
        JButton btn = new JButton(texto);
        btn.setBackground(cor);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(new EmptyBorder(7, 14, 7, 14));
        return btn;
    }

    public void listarTodosComTratamento() {
        try {
            carregarGrade(livroDAO.listarLivros());
        } catch (PersistenciaException ex) {
            lblTotal.setText("Erro ao consultar o banco.");
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                this,
                "Não foi possível carregar os livros. Verifique a conexão com o MariaDB.",
                "Erro de conexão",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void carregarTabela(List<Livro> livros) {
        carregarGrade(livros);
    }

    public void carregarGrade(List<Livro> livros) {
        painelGridLivros.removeAll();
        livroSelecionado = null;
        cardSelecionado = null;

        for (int i = 0; i < livros.size(); i++) {
            Livro l = livros.get(i);
            JPanel card = criarCardLivro(l);
            painelGridLivros.add(card);
            if (i == 0) {
                selecionarCard(card, l);
            }
        }

        lblTotal.setText("Mostrando " + livros.size() + " resultados.");
        painelGridLivros.revalidate();
        painelGridLivros.repaint();
    }

    private JPanel criarCardLivro(Livro l) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(new EmptyBorder(8, 8, 10, 8));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel lblCapa = new JLabel("Sem capa", SwingConstants.CENTER);
        lblCapa.setPreferredSize(new Dimension(170, 235));
        lblCapa.setMaximumSize(new Dimension(170, 235));
        lblCapa.setMinimumSize(new Dimension(170, 235));
        lblCapa.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblCapa.setOpaque(true);
        lblCapa.setBackground(new Color(238, 241, 246));
        lblCapa.setForeground(COR_TEXTO_CINZA);
        lblCapa.setBorder(new LineBorder(new Color(225, 228, 234), 1));

        if (l.getCapa() != null && !l.getCapa().isEmpty() && new File(l.getCapa()).exists()) {
            ImageIcon icon = new ImageIcon(l.getCapa());
            Image img = icon.getImage().getScaledInstance(170, 235, Image.SCALE_SMOOTH);
            lblCapa.setIcon(new ImageIcon(img));
            lblCapa.setText("");
        }

        JLabel lblTitulo = new JLabel("<html><div style='width:165px;'>" + l.getTitulo() + "</div></html>");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblTitulo.setForeground(COR_TEXTO_ESCURO);
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblAutor = new JLabel("<html><div style='width:165px;'>" + l.getAutor() + "</div></html>");
        lblAutor.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblAutor.setForeground(COR_TEXTO_CINZA);
        lblAutor.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblAno = new JLabel(l.getAno() + "  •  ID #" + l.getId());
        lblAno.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblAno.setForeground(COR_TEXTO_CINZA);
        lblAno.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(lblCapa);
        card.add(Box.createVerticalStrut(8));
        card.add(lblTitulo);
        card.add(Box.createVerticalStrut(3));
        card.add(lblAutor);
        card.add(Box.createVerticalStrut(3));
        card.add(lblAno);

        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selecionarCard(card, l);
            }
        });

        return card;
    }

    private void selecionarCard(JPanel card, Livro l) {
        if (cardSelecionado != null) {
            cardSelecionado.setBackground(Color.WHITE);
            cardSelecionado.setBorder(new EmptyBorder(8, 8, 10, 8));
        }
        cardSelecionado = card;
        livroSelecionado = l;
        card.setBackground(new Color(243, 246, 255));
        card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COR_AZUL_BANNER, 2, true),
                new EmptyBorder(6, 6, 8, 6)
        ));
    }

    private void buscarPorId() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());

            if (id <= 0) {
                throw new IllegalArgumentException("O ID deve ser positivo.");
            }

            Livro l = livroDAO.buscarLivroPorId(id);
            if (l != null) {
                carregarGrade(List.of(l));
            } else {
                JOptionPane.showMessageDialog(this, "Nenhum livro encontrado com o ID " + id);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Informe um número inteiro válido.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (PersistenciaException ex) {
            lblTotal.setText("Erro ao consultar o banco.");
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                this,
                "Não foi possível buscar o livro. Verifique a conexão com o MariaDB.",
                "Erro de conexão",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void buscarPorTitulo() {
        try {
            String termo = txtTitulo.getText().trim();
            carregarGrade(livroDAO.buscarLivrosPorTitulo(termo));
        } catch (PersistenciaException ex) {
            lblTotal.setText("Erro ao consultar o banco.");
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                this,
                "Não foi possível pesquisar os livros. Verifique a conexão com o MariaDB.",
                "Erro de conexão",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void selecionarCapaParaLivro() {
        if (livroSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Clique em um livro no catálogo primeiro.");
            return;
        }

        JFileChooser chooser = new JFileChooser(new File("."));
        chooser.setDialogTitle("Selecione a capa para: " + livroSelecionado.getTitulo());
        chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imagens JPG e PNG", "jpg", "jpeg", "png"));

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                File arquivoOrigem = chooser.getSelectedFile();
                String novoCaminho = ImagemLivro.salvar(arquivoOrigem.toPath(), livroSelecionado.getId());
                livroDAO.atualizarCapa(livroSelecionado.getId(), novoCaminho);
                JOptionPane.showMessageDialog(this, "Capa salva com sucesso!");
                listarTodosComTratamento();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Não foi possível salvar a imagem.", "Erro", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            } catch (PersistenciaException ex) {
                JOptionPane.showMessageDialog(this, "Não foi possível atualizar a capa no banco de dados.", "Erro de conexão", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaLivros().setVisible(true));
    }
}   