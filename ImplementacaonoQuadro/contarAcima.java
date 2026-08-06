package ImplementacaonoQuadro;

public class contarAcima {

    public static int ContarAcima(int[] numeros, int limite) {
        int quantidade = 0;
        for (int numero : numeros) {
            if (numero > limite) {
                quantidade++;
            }
        }
        return quantidade;
    }

}