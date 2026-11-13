import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ImagemUsuario {

    private static final String PASTA_USUARIOS = "imagens/usuarios";

    public static String salvar(Path arquivoOrigem, int idUsuario) throws IOException {
        File pasta = new File(PASTA_USUARIOS);
        if (!pasta.exists()) {
            pasta.mkdirs();
        }

        String nomeOriginal = arquivoOrigem.getFileName().toString();
        String extensao = ".png";
        int ponto = nomeOriginal.lastIndexOf('.');
        if (ponto > 0) {
            extensao = nomeOriginal.substring(ponto).toLowerCase();
        }

        String nomeDestino = "usuario_" + idUsuario + extensao;
        Path destino = Paths.get(PASTA_USUARIOS, nomeDestino);

        Files.copy(arquivoOrigem, destino, StandardCopyOption.REPLACE_EXISTING);
        return PASTA_USUARIOS + "/" + nomeDestino;
    }

    public static String buscarFotoExistente(int idUsuario) {
        String[] extensoes = {".png", ".jpg", ".jpeg"};
        for (String ext : extensoes) {
            File f = new File(PASTA_USUARIOS + "/usuario_" + idUsuario + ext);
            if (f.exists()) {
                return f.getPath();
            }
        }
        return null;
    }
}