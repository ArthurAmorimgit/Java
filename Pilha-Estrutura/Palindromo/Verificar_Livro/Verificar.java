import java.util.Scanner;
import java.util.Stack;
/*2. Escreva um programa que utiliza um objeto Pilha para determinar se uma
string é um Palíndromo (isto é, a string é escrita identicamente de trás para
frente). O programa deve ignorar espaços e pontuação.*/

public class Verificar {
    public static void main(String[] args) {
        Stack<Character> letras = new Stack<>();
        Scanner ler =  new Scanner(System.in);
        System.out.println("Digite uma  frase ou palavra");
        String entrada = ler.nextLine();
        String limpa = entrada.replaceAll("[^a-zA-Z0-9]", " ").toLowerCase();

        for (int i = 0; i < limpa.length(); i++) {
            letras.push(limpa.charAt(i));
        }
        String invertida = "";
        while (!letras.isEmpty()) {
            invertida += letras.pop();
        }
        if (limpa.equals(invertida)) {
            System.out.println( entrada + " é um palíndromo!");
        } else {
            System.out.println( entrada +  "não é um palíndromo.");
        }
    }
}
