package utill;

import java.sql.*;
import javax.swing.JOptionPane;

public class ConectaPostgre {

    private Connection con = null;
    public Statement stmt;
    public ResultSet rs;
    private String endereco;
    private String usuario;
    private String senha;

    public void Conectar(String strEnd, String strUsuario, String strSenha) {
        endereco = strEnd;
        usuario = strUsuario;
        senha = strSenha;
        JOptionPane.showMessageDialog(null, "Tentando realizar conexão só apresentando...");
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(endereco, usuario, strSenha);
            stmt = con.createStatement();
            JOptionPane.showMessageDialog(null, "Banco conectado com sucesso");
        } catch (ClassNotFoundException cnfe) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar o driver");
            cnfe.printStackTrace();
        } catch (SQLException sqlex) {
            JOptionPane.showMessageDialog(null, "erro na query");
            sqlex.printStackTrace();
        }
    }

    public void Desconectar() {
        try {
            con.close();
        } catch (SQLException onConClose) {
            JOptionPane.showMessageDialog(null, "Erro ao desconectar o banco");
            onConClose.printStackTrace();
        }
    }
}
