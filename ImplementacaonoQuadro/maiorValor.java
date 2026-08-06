package ImplementacaonoQuadro;

public class maiorValor {
    public static int MaiorValor(int[] numeros) {
        int maior = numeros[0];
        for (int n : numeros) {
            if (n > maior) {
                maior = n;
            }
        }
        return maior;
    }
}