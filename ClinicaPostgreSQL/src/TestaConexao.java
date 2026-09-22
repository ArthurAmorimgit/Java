import utill.ConectaPostgre;

import javax.swing.JOptionPane;

public class TestaConexao {

    public static void main(String[] args) {

        // 🔧 Cria um objeto da classe ConectaPostgre
        ConectaPostgre banco = new ConectaPostgre();

        // 🧩 Define as informações de conexão
        String url = "jdbc:postgresql://localhost:5432/ClinicaMedica"; // troque "clinica" pelo nome do seu banco
        String usuario = "postgres";  // seu usuário do PostgreSQL
        String senha = "tttt4020";    // sua senha do PostgreSQL

        // 🚀 Tenta conectar
        banco.Conectar(url, usuario, senha);

        // 🔌 Desconecta do banco (opcional, apenas para teste)
        banco.Desconectar();

        JOptionPane.showMessageDialog(null, "Programa finalizado!");
    }
}
