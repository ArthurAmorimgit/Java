
import java.util.Scanner;

public class ativ1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int homem = 0, mulher = 0;
        int resp = 0, cont = 0;
        int servico = 0;
        int total = 0;
        while (resp != 5) {
            System.out.println("Boa Tarde por favor digite o motiva da sua vinda para o shopping");
            System.out.println("Digite [1] para Compras, Digite [2] para Serviços, Digite [3] para Lazer, Digite [4] para Alimentação, Digite [5] para Sair");
            resp = sc.nextInt();
            if (resp != 5) {
                System.out.println("Qual é seu sexo [6] para Homem [7] para Mulher");
                int sexo = sc.nextInt();
                if (sexo == 6 && sexo != 5) {
                    homem++;
                } else {
                    mulher++;
                }
                if (resp == 2) {
                    servico++;

                } else {
                    cont++;
                }
                total = servico + cont;
            }
        }
        if (homem > mulher) {
            System.out.println("homens frequentam mais que mulheres");
        } else {
            System.out.println("mulheres frequentam mais que homens");
        }
        total = (servico * 100)/total;
        System.out.println("a porcentagem que escolheram serviço é " + total);
    }
}
