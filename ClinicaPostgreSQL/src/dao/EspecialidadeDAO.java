package dao;

import utill.ConectaPostgre;
import model.Especialidade;

import java.util.ArrayList;
import java.util.List;

public class EspecialidadeDAO {
    private ConectaPostgre banco;

    public EspecialidadeDAO(ConectaPostgre banco) { this.banco = banco; }

    public boolean inserir(Especialidade e) {
        try {
            String nome = e.getNome_especialidade().replace("'", "''");
            String desc = e.getDescricao_especialidade() == null ? "" : e.getDescricao_especialidade().replace("'", "''");
            String sql = "INSERT INTO clinica.especialidade (nome_especialidade, descricao_especialidade) VALUES ('" + nome + "','" + desc + "')";
            banco.stmt.executeUpdate(sql);
            return true;
        } catch (Exception ex) { ex.printStackTrace(); return false; }
    }

    public List<Especialidade> listarTodos() {
        List<Especialidade> lista = new ArrayList<>();
        try {
            String sql = "SELECT cod_especialidade, nome_especialidade, descricao_especialidade FROM clinica.especialidade ORDER BY nome_especialidade";
            banco.rs = banco.stmt.executeQuery(sql);
            while (banco.rs.next()) {
                Especialidade e = new Especialidade();
                e.setCod_especialidade(banco.rs.getInt("cod_especialidade"));
                e.setNome_especialidade(banco.rs.getString("nome_especialidade"));
                e.setDescricao_especialidade(banco.rs.getString("descricao_especialidade"));
                lista.add(e);
            }
            banco.rs.close();
        } catch (Exception ex) { ex.printStackTrace(); }
        return lista;
    }

    public boolean excluir(int id) {
        try {
            String sql = "DELETE FROM clinica.especialidade WHERE cod_especialidade = " + id;
            banco.stmt.executeUpdate(sql);
            return true;
        } catch (Exception ex) { ex.printStackTrace(); return false; }
    }
}
