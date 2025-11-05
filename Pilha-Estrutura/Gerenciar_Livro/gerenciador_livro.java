/*1. Crie um programa em java para gerenciar os livros que deverão ser lidos
durante as férias e para a solução do problema utilize o conceito de pilhas,
tenha as seguintes opções:
1) Inserir na Pilha
2) Consultar na Pilha
3) Remover na Pilha
4) Esvaziar na Pilha
5) Sair.*/

import java.util.Scanner;
import java.util.Stack;

public class gerenciador_livro {
    public static void main(String[] args) {
        Stack<String> genren_pilha = new Stack<>();
        Scanner ler = new Scanner(System.in);
        int num;
        do {
            System.out.println("Escolha uma das opções \n 1) Inserir na Pilha\n" +
                    "2) Consultar na Pilha\n" +
                    "3) Remover na Pilha\n" +
                    "4) Esvaziar na Pilha\n" +
                    "5) Sair");
            num = ler.nextInt();
            switch (num) {
                case 1:
                    ler.nextLine();
                    System.out.println(" Insira um livro na Pilha");
                    String livro = ler.nextLine();
                    genren_pilha.push(livro);
                    break;
                case 2:
                    if (genren_pilha.isEmpty()) {
                        System.out.println("ESTÁ VAZIA");
                    } else{
                        System.out.println("Consultando a lista " + genren_pilha.peek());
                    }
                    break;
                case 3:
                    if (genren_pilha.isEmpty()){
                        System.out.println("ESTÁ VAZIA");
                    }else {
                        System.out.println("Remover item da Pilha que está no topo " + genren_pilha.pop());
                    }
                    break;
                case 4:
                    System.out.println("Esvaziando a Pilha");
                    genren_pilha.clear();
                    break;
                case 5:
                    System.out.println(" SAINDOOOOO....");
                    break;
                default:
                    System.out.println("Opção Invalida");
            }
        } while (num != 5);


    }
}
