import java.util.Scanner;
import java.util.Stack;
/*5. Projete uma estrutura de pilha para guardar passo a passo de como efetuar uma determinada tarefa.
a) Escreva a função criar a pilha
b) Adicione tarefas na pilha
c) Remova tarefas da pilha
d) Indique se a tarefa for concluída quando a pilha estiver vazia*/
public class PassosTrefas {
    static Stack<String>  passosTf = new Stack<>();
    public static void criarPilha(){
        passosTf.clear();
        System.out.println("Criando pilha");
    }
    public static void adicionar(String passo){
        passosTf.push(passo);
        System.out.println("Adicionando passo " + passo);
    }
    public static void remover(){
        if (passosTf.isEmpty()){
            System.out.println("Está vazia");
        }else {
            String removido = passosTf.pop();
            System.out.println(" Removendo " + removido);
        }
    }
    public static void mostarProximo(){
      if (passosTf.isEmpty()){
          System.out.println(" Está vazia");
      }else {
          System.out.println(" Proximo passo " + passosTf.peek());
      }
    }

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        int op;
        criarPilha();
        do {
            System.out.println("Escolha 1)  Adicionar na Pilha\n" +
                    "2) Remova tarefas da pilha\n" +
                    "3) Mostrar proximo passo\n" +
                    "4) Indique se a tarefa for concluída quando a pilha estiver vazio" +
                    "5 sair");
            op = ler.nextInt();
            switch (op){
                case 1:
                    System.out.println(" Escreva a Tarefa");
                    String passo = ler.nextLine();
                    adicionar(passo);
                    break;
                case 2:
                    remover();
                    break;
                case 3:
                    mostarProximo();
                    break;
                case 4 :
                    criarPilha();
                    break;
                case 5 :
                    System.out.println("saindooo...");
            }
        }while (op != 5);
    }
}
