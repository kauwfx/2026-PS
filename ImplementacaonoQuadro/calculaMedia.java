package ImplementacaonoQuadro;

public class calculaMedia {
    public static double CalculaMedia(int[] numeros) {
        double soma = 0;
        for (int i = 0; i < numeros.length; i++) {
            soma += numeros[i];
        }
        return soma / numeros.length;
    }
}