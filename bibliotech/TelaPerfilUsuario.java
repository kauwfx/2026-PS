import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TelaPerfilUsuario extends JDialog {

    private JPanel painelAvatarCircular;
    private JLabel lblNome;
    private JLabel lblStatusFoto;
    private Image imagemPerfil;
    private final int idUsuario = 1;

    private final Color COR_AZUL_BANNER = new Color(11, 43, 73);
    private final Color COR_AZUL_BOTAO = new Color(36, 82, 145);
    private final Color COR_FUNDO_GERAL = new Color(242, 244, 248);
    private final Color COR_TEXTO_ESCURO = new Color(30, 35, 45);
    private final Color COR_TEXTO_CINZA = new Color(115, 122, 135);
    private final Color COR_BORDA = new Color(220, 224, 232);

    public TelaPerfilUsuario(JFrame parent) {
        super(parent, "Meu Perfil - BiblioTech", true);
        setSize(620, 440);
        setLocationRelativeTo(parent);

        JPanel painelRaiz = new JPanel(new BorderLayout(0, 16));
        painelRaiz.setBackground(COR_FUNDO_GERAL);
        painelRaiz.setBorder(new EmptyBorder(16, 20, 18, 20));
        setContentPane(painelRaiz);

        // ================= CABEÇALHO AZUL MARINHO =================
        JPanel bannerTopo = new JPanel(new BorderLayout());
        bannerTopo.setBackground(COR_AZUL_BANNER);
        bannerTopo.setBorder(new EmptyBorder(16, 22, 16, 22));

        JLabel lblTituloBanner = new JLabel("Perfil do Leitor");
        lblTituloBanner.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTituloBanner.setForeground(Color.WHITE);
        bannerTopo.add(lblTituloBanner, BorderLayout.WEST);

        painelRaiz.add(bannerTopo, BorderLayout.NORTH);

        // ================= CARTÃO BRANCO "MEU PERFIL" =================
        JPanel cardPerfil = new JPanel(new BorderLayout(20, 16));
        cardPerfil.setBackground(Color.WHITE);
        cardPerfil.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COR_BORDA, 1, true),
                new EmptyBorder(20, 24, 22, 24)
        ));

        JLabel lblSecao = new JLabel("Meu Perfil");
        lblSecao.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblSecao.setForeground(COR_AZUL_BANNER);
        cardPerfil.add(lblSecao, BorderLayout.NORTH);

        JPanel painelConteudo = new JPanel(new BorderLayout(26, 0));
        painelConteudo.setBackground(Color.WHITE);

        // JPanel circular com tamanho fixo garantido (124x124)
        painelAvatarCircular = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(124, 124);
            }

            @Override
            public Dimension getMinimumSize() {
                return new Dimension(124, 124);
            }

            @Override
            public Dimension getMaximumSize() {
                return new Dimension(124, 124);
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

                int d = 122;
                Ellipse2D.Double circulo = new Ellipse2D.Double(1, 1, d, d);
                g2.setClip(circulo);

                if (imagemPerfil != null) {
                    g2.drawImage(imagemPerfil, 1, 1, d, d, this);
                } else {
                    g2.setColor(new Color(224, 232, 244));
                    g2.fillRect(1, 1, d, d);

                    g2.setColor(new Color(90, 102, 120));
                    g2.fillOval(d / 2 - 20, 24, 42, 42);
                    g2.fillArc(d / 2 - 36, 72, 74, 58, 0, 180);
                }

                g2.setClip(null);
                g2.setColor(COR_BORDA);
                g2.setStroke(new BasicStroke(2f));
                g2.drawOval(1, 1, d, d);
                g2.dispose();
            }
        };
        painelAvatarCircular.setBackground(Color.WHITE);

        JPanel wrapperFoto = new JPanel(new GridBagLayout());
        wrapperFoto.setBackground(Color.WHITE);
        wrapperFoto.add(painelAvatarCircular);

        // Dados do Usuário + Botões
        JPanel painelInfo = new JPanel();
        painelInfo.setLayout(new BoxLayout(painelInfo, BoxLayout.Y_AXIS));
        painelInfo.setBackground(Color.WHITE);

        lblNome = new JLabel(carregarNomeSalvo());
        lblNome.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblNome.setForeground(COR_TEXTO_ESCURO);
        lblNome.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblTipo = new JLabel("Leitor(a) cadastrado(a)");
        lblTipo.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblTipo.setForeground(COR_TEXTO_CINZA);
        lblTipo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblEmprestimos = new JLabel("Livros emprestados: 2");
        lblEmprestimos.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblEmprestimos.setForeground(COR_TEXTO_CINZA);
        lblEmprestimos.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel painelBotoesPerfil = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        painelBotoesPerfil.setBackground(Color.WHITE);
        painelBotoesPerfil.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnAlterarFoto = criarBotao("Alterar fotografia", COR_AZUL_BOTAO);
        JButton btnEditarNome = criarBotao("Editar nome", new Color(45, 55, 72));

        painelBotoesPerfil.add(btnAlterarFoto);
        painelBotoesPerfil.add(Box.createHorizontalStrut(10));
        painelBotoesPerfil.add(btnEditarNome);

        painelInfo.add(Box.createVerticalStrut(6));
        painelInfo.add(lblNome);
        painelInfo.add(Box.createVerticalStrut(4));
        painelInfo.add(lblTipo);
        painelInfo.add(Box.createVerticalStrut(3));
        painelInfo.add(lblEmprestimos);
        painelInfo.add(Box.createVerticalStrut(16));
        painelInfo.add(painelBotoesPerfil);

        painelConteudo.add(wrapperFoto, BorderLayout.WEST);
        painelConteudo.add(painelInfo, BorderLayout.CENTER);
        cardPerfil.add(painelConteudo, BorderLayout.CENTER);

        painelRaiz.add(cardPerfil, BorderLayout.CENTER);

        // ================= RODAPÉ =================
        JPanel painelRodape = new JPanel(new BorderLayout());
        painelRodape.setBackground(COR_FUNDO_GERAL);

        lblStatusFoto = new JLabel("Pasta de destino: imagens/usuarios/");
        lblStatusFoto.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblStatusFoto.setForeground(COR_TEXTO_CINZA);

        JButton btnFechar = criarBotao("Voltar ao Catálogo", COR_AZUL_BANNER);

        painelRodape.add(lblStatusFoto, BorderLayout.WEST);
        painelRodape.add(btnFechar, BorderLayout.EAST);
        painelRaiz.add(painelRodape, BorderLayout.SOUTH);

        carregarFotoSalva();

        btnAlterarFoto.addActionListener(e -> escolherNovaFoto());
        btnEditarNome.addActionListener(e -> editarNomeUsuario());
        btnFechar.addActionListener(e -> dispose());
    }

    private JButton criarBotao(String texto, Color cor) {
        JButton btn = new JButton(texto);
        btn.setBackground(cor);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(new EmptyBorder(9, 15, 9, 15));
        return btn;
    }

    private void carregarFotoSalva() {
        String caminho = ImagemUsuario.buscarFotoExistente(idUsuario);
        if (caminho != null) {
            aplicarImagem(caminho);
            lblStatusFoto.setText("Foto carregada: " + caminho);
        }
    }

    private void aplicarImagem(String caminho) {
        ImageIcon icon = new ImageIcon(caminho);
        if (icon.getIconWidth() > 0 && icon.getIconHeight() > 0) {
            imagemPerfil = icon.getImage();
            painelAvatarCircular.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Formato de imagem não suportado pelo Java Swing. Use JPG ou PNG válido.");
        }
    }

    private void escolherNovaFoto() {
        JFileChooser chooser = new JFileChooser(new File("."));
        chooser.setDialogTitle("Selecione a fotografia do perfil");
        chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imagens JPG e PNG", "jpg", "jpeg", "png"));

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                File arquivoOrigem = chooser.getSelectedFile();
                String caminhoSalvo = ImagemUsuario.salvar(arquivoOrigem.toPath(), idUsuario);
                aplicarImagem(caminhoSalvo);
                lblStatusFoto.setText("Salvo em: " + caminhoSalvo);
                JOptionPane.showMessageDialog(this, "Fotografia atualizada com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar fotografia: " + ex.getMessage());
            }
        }
    }

    private void editarNomeUsuario() {
        String novoNome = JOptionPane.showInputDialog(
                this,
                "Digite o novo nome do usuário:",
                lblNome.getText()
        );

        if (novoNome != null && !novoNome.trim().isEmpty()) {
            String nomeLimpo = novoNome.trim();
            lblNome.setText(nomeLimpo);
            salvarNome(nomeLimpo);
        }
    }

    private String carregarNomeSalvo() {
        try {
            Path arqNome = Paths.get("imagens/usuarios/nome_" + idUsuario + ".txt");
            if (Files.exists(arqNome)) {
                String conteudo = Files.readString(arqNome).trim();
                if (!conteudo.isEmpty()) return conteudo;
            }
        } catch (Exception ignored) {}
        return "Ana Silva";
    }

    private void salvarNome(String nome) {
        try {
            File pasta = new File("imagens/usuarios");
            if (!pasta.exists()) pasta.mkdirs();
            Files.writeString(Paths.get("imagens/usuarios/nome_" + idUsuario + ".txt"), nome);
        } catch (Exception ignored) {}
    }
}