package dao;

import utill.ConectaPostgre;
import model.Recepcionista;

import java.util.ArrayList;
import java.util.List;

public class RecepcionistaDAO {
    private ConectaPostgre banco;

    public RecepcionistaDAO(ConectaPostgre banco) { this.banco = banco; }

    public boolean inserir(Recepcionista r) {
        try {
            String nome = r.getNome_recepcionista().replace("'", "''");
            String cpf = r.getCpf().replace("'", "''");
            String sql = "INSERT INTO clinica.recepcionista (nome_recepcionista, cpf) VALUES ('" + nome + "','" + cpf + "')";
            banco.stmt.executeUpdate(sql);
            return true;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public List<Recepcionista> listarTodos() {
        List<Recepcionista> lista = new ArrayList<>();
        try {
            String sql = "SELECT cod_recepcionista, nome_recepcionista, cpf FROM clinica.recepcionista ORDER BY nome_recepcionista";
            banco.rs = banco.stmt.executeQuery(sql);
            while (banco.rs.next()) {
                Recepcionista r = new Recepcionista();
                r.setCod_recepcionista(banco.rs.getInt("cod_recepcionista"));
                r.setNome_recepcionista(banco.rs.getString("nome_recepcionista"));
                r.setCpf(banco.rs.getString("cpf"));
                lista.add(r);
            }
            banco.rs.close();
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }

    public boolean excluir(int id) {
        try {
            String sql = "DELETE FROM clinica.recepcionista WHERE cod_recepcionista = " + id;
            banco.stmt.executeUpdate(sql);
            return true;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }
}
