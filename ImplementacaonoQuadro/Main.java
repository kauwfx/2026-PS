package ImplementacaonoQuadro;

public class Main {
    public static void main(String[] args) {
        int[] valores = {8, 3, 10, 5, 12};

        System.out.println(calculaSoma.calculaSoma(valores));
        System.out.println(calculaMedia.calculaMedia(valores));
        System.out.println(menorValor.menorValor(valores));
        System.out.println(maiorValor.maiorValor(valores));
        System.out.println(contarAcima.contarAcima(valores, 6));
    }
    // Teste aqui os cinco métodos.
}