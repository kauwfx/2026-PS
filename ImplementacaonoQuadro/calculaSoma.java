package ImplementacaonoQuadro;

public class calculaSoma {
    public static int CalculaSoma(int[] numeros) {
        int soma = 0;
        for (int n : numeros) {
            soma += n;
        }
        return soma;
    }
}