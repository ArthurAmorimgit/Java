package dao;

import utill.ConectaPostgre;
import model.Medico;

import java.util.ArrayList;
import java.util.List;

public class MedicoDAO {
    private ConectaPostgre banco;

    public MedicoDAO(ConectaPostgre banco) { this.banco = banco; }

    public boolean inserir(Medico m) {
        try {
            String nome = m.getNome().replace("'", "''");
            String crm = m.getCrm() == null ? "" : m.getCrm().replace("'", "''");
            String esp = (m.getCod_especialidade() == null ? "NULL" : m.getCod_especialidade().toString());
            String sql = "INSERT INTO clinica.medico (nome_medico, crm, cod_especialidade) VALUES ('" + nome + "','" + crm + "'," + esp + ")";
            banco.stmt.executeUpdate(sql);
            return true;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public Medico buscaporID(int id) {
        try {
            String sql = "SELECT cod_medico, nome_medico, crm, cod_especialidade FROM clinica.medico WHERE cod_medico = " + id;
            banco.rs = banco.stmt.executeQuery(sql);
            if (banco.rs.next()) {
                Medico m = new Medico();
                m.setCod_medico(banco.rs.getInt("cod_medico"));
                m.setNome(banco.rs.getString("nome_medico"));
                m.setCrm(banco.rs.getString("crm"));
                int esp = banco.rs.getInt("cod_especialidade");
                if (!banco.rs.wasNull()) m.setCod_especialidade(esp);
                banco.rs.close();
                return m;
            }
            banco.rs.close();
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public List<Medico> listarTodos() {
        List<Medico> lista = new ArrayList<>();
        try {
            String sql = "SELECT cod_medico, nome_medico, crm, cod_especialidade FROM clinica.medico ORDER BY nome_medico";
            banco.rs = banco.stmt.executeQuery(sql);
            while (banco.rs.next()) {
                Medico m = new Medico();
                m.setCod_medico(banco.rs.getInt("cod_medico"));
                m.setNome(banco.rs.getString("nome_medico"));
                m.setCrm(banco.rs.getString("crm"));
                int esp = banco.rs.getInt("cod_especialidade");
                if (!banco.rs.wasNull()) m.setCod_especialidade(esp);
                lista.add(m);
            }
            banco.rs.close();
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }

    public boolean atualizar(Medico m) {
        try {
            String nome = m.getNome().replace("'", "''");
            String crm = m.getCrm() == null ? "" : m.getCrm().replace("'", "''");
            String esp = (m.getCod_especialidade() == null ? "NULL" : m.getCod_especialidade().toString());
            String sql = "UPDATE clinica.medico SET nome_medico='" + nome + "', crm='" + crm + "', cod_especialidade=" + esp + " WHERE cod_medico=" + m.getCod_medico();
            banco.stmt.executeUpdate(sql);
            return true;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public boolean excluir(int id) {
        try {
            String sql = "DELETE FROM clinica.medico WHERE cod_medico = " + id;
            banco.stmt.executeUpdate(sql);
            return true;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    // listar com JOIN
    public List<String> listarMedicosComEspecialidade() {
        List<String> out = new ArrayList<>();
        try {
            String sql = "SELECT m.cod_medico, m.nome_medico, e.nome_especialidade FROM clinica.medico m LEFT JOIN clinica.especialidade e ON m.cod_especialidade = e.cod_especialidade ORDER BY m.nome_medico";
            banco.rs = banco.stmt.executeQuery(sql);
            while (banco.rs.next()) {
                out.add(banco.rs.getInt("cod_medico") + " - " + banco.rs.getString("nome_medico") + " (" + banco.rs.getString("nome_especialidade") + ")");
            }
            banco.rs.close();
        } catch (Exception e) { e.printStackTrace(); }
        return out;
    }
}
