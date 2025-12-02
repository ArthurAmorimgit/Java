package dao;

import utill.ConectaPostgre;
import model.Convenio;

import java.util.ArrayList;
import java.util.List;

public class ConvenioDAO {
    private ConectaPostgre banco;

    public ConvenioDAO(ConectaPostgre banco) { this.banco = banco; }

    public boolean inserir(Convenio c) {
        try {
            String nome = c.getNome_convenio().replace("'", "''");
            String cov = c.getCobertura_convenio() == null ? "" : c.getCobertura_convenio().replace("'", "''");
            String tel = c.getTelefone_convenio() == null ? "" : c.getTelefone_convenio().replace("'", "''");
            String sql = "INSERT INTO clinica.convenio (nome_convenio, cobertura_convenio, telefone_convenio) VALUES ('" + nome + "','" + cov + "','" + tel + "')";
            banco.stmt.executeUpdate(sql);
            return true;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public List<Convenio> listarTodos() {
        List<Convenio> lista = new ArrayList<>();
        try {
            String sql = "SELECT cod_convenio, nome_convenio, cobertura_convenio, telefone_convenio FROM clinica.convenio ORDER BY nome_convenio";
            banco.rs = banco.stmt.executeQuery(sql);
            while (banco.rs.next()) {
                Convenio c = new Convenio();
                c.setCod_convenio(banco.rs.getInt("cod_convenio"));
                c.setNome_convenio(banco.rs.getString("nome_convenio"));
                c.setCobertura_convenio(banco.rs.getString("cobertura_convenio"));
                c.setTelefone_convenio(banco.rs.getString("telefone_convenio"));
                lista.add(c);
            }
            banco.rs.close();
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }

    public boolean excluir(int id) {
        try {
            String sql = "DELETE FROM clinica.convenio WHERE cod_convenio = " + id;
            banco.stmt.executeUpdate(sql);
            return true;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }
}
