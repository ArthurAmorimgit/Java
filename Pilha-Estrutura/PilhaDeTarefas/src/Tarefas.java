
/*3. Crie um programa que gerencie uma PILHA de TAREFAS a serem
cumpridas. As tarefas são strings que descrevem uma ação a ser executada. O
usuário deverá ter duas opções:
a) Inserir tarefa na pilha;
b) Obter a próxima tarefa da pilha.*/

import java.util.Scanner;
import java.util.Stack;

public class Tarefas {
    public static void main(String[] args) {
        Stack<String> tare = new Stack<>();
        Scanner ler = new Scanner(System.in);
        int op;
        do {
            System.out.println("Digite 1) Inserir tarefas na pilha \n 2) Proxima tarefa \n 3) sair");
            op = ler.nextInt();
            switch (op){
                case 1:
                    System.out.println("Diga o nome da tarefa");
                    String tarefa = ler.nextLine();
                    tare.push(tarefa);
                    System.out.println("Adicionando tarefa");
                    break;
                case 2:
                    if (tare.isEmpty()){
                        System.out.println(" A pilha de tarefa está vazia");
                    }else {
                        System.out.println("Mudando para a proxima tarefa");
                        String proxima = tare.pop();
                        System.out.println("Proxima tarefa " + proxima);
                    }
                    break;
                case 3:
                    System.out.println(" SAINDOOOO ....");
                    break;
                default:
                    System.out.println(" opção invalida");
            }
        }while (op != 3);
    }
}
