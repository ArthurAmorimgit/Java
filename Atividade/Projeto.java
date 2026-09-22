//Implemente uma função que recebe por
//parâmetro um vetor de números inteiros
//e a quantidade de elementos do mesmo
//e informe a posição do maior número.

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

// Arthur Ferreira de Amorim
public class Projeto {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 10;
        int Vet[] = new int[n];

        for (int i = 0; i <n; i++) {
            System.out.println("Digite 10 numeros para colocar no vetor");
            Vet[i] = sc.nextInt();
        }
            Arrays.sort(Vet);
            System.out.println("aqui estao os numeros " + Arrays.toString(Vet) + " ");
            

        System.out.println("esse é o valor maior " + maior(Vet));

    }
    public static int maior(int[] vetor){
        int M = vetor[0];
        for (int i = 1; i < vetor.length; i++) {
            if (vetor[i]>M ){
                M = vetor[i];
            }
        }
        return M;

    }
}
