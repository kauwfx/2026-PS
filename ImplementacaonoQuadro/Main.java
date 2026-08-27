package ImplementacaonoQuadro;

public class Main {
    public static void main(String[] args) {
        int[] valores = {8, 3, 10, 5, 12};

        System.out.println(calculaSoma.CalculaSoma(valores));
        System.out.println(calculaMedia.CalculaMedia(valores));
        System.out.println(menorValor.MenorValor(valores));
        System.out.println(maiorValor.MaiorValor(valores));
        System.out.println(contarAcima.ContarAcima(valores, 6));
    }
    // Teste aqui os cinco métodos.
}