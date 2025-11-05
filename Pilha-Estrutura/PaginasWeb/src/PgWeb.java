/*4. Projete uma estrutura do tipo pilha para guardar informações de páginas da
web visitadas.
a) Escreva a função inicializarPilha
b) Escreva a função push para adicionar um elemento à pilha.
c) Escreva a função pop para remover*/


import java.util.Scanner;
import java.util.Stack;

public class PgWeb {
     static Stack<String> pilhaWeb = new Stack<>();
    public static void iniciar(){
        pilhaWeb.clear();
        System.out.println("Inicializando Pilha");
    }
    public static void push(String info){
        pilhaWeb.push(info);
        System.out.println("Adicionando " + info);
    }
    public static void pop(){
        if (pilhaWeb.isEmpty()){
            System.out.println(" Está vazia");
        }else {
            String remover = pilhaWeb.pop();
            System.out.println("Removendo : " + remover);
        }
    }

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        int op;
        iniciar();
        do {
            System.out.println("Escolha sua ação \n 1) Iniciar\n" +
                    "2) voltar.\n" +
                    "3)Reiniciar\n  " +
                    "4 sair");
            op = ler.nextInt();
            ler.nextLine();
            switch (op){
                case 1 :
                    System.out.println("Digite para inserção na pilha");
                    String pagina = ler.nextLine();
                    push(pagina);
                    break;
                case 2:
                    pop();
                    break;
                case 3:
                    iniciar();
                    break;
                case 4 :
                    System.out.println(" SAINDOOOO.....");
                    break;
                default:
                    System.out.println("opção invalida");
            }
        }while (op != 4);

    }

}
